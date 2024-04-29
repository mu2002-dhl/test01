package cn.platform.com.auth.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.convert.FileConvert;
import cn.platform.com.auth.entity.SysFileEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.exception.BizCustomException;
import cn.platform.com.auth.mapper.SysFileMapper;
import cn.platform.com.auth.model.request.FileRequest;
import cn.platform.com.auth.model.request.SelectedFileRequest;
import cn.platform.com.auth.model.response.FileResponse;
import cn.platform.com.auth.service.FileService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-23 21:11
 */
@Service
public class FileServiceImpl implements FileService {

    @Resource
    private SysFileMapper fileMapper;

    @Value("${platform.filePath}")
    private String filePath;

    @Override
    public FileResponse upload(String module, MultipartFile file) throws Exception{
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(String.valueOf(UUID.randomUUID()).replaceAll("-", ""));
        fileResponse.setExtend(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
        fileResponse.setOriginName(file.getOriginalFilename());
        fileResponse.setSize(file.getSize());
        fileResponse.setModule(module);
        fileResponse.setCreateTime(new Date());

        String path = buildPath(fileResponse);
        File sourceFile = FileUtil.touch(filePath+"//"+ path);
        file.transferTo(sourceFile);

        return fileResponse;
    }

    @Override
    public int add(FileRequest request) {
        SysFileEntity sysFileEntity = FileConvert.instance.fileRequest2SysFileEntity(request);
        return fileMapper.insert(sysFileEntity);
    }

    @Override
    public Optional<SysFileEntity> detail(String id) {
        if(id == null){
            return Optional.empty();
        }

        SysFileEntity sysFileEntity = fileMapper.selectById(id);
        if(sysFileEntity == null){
            return Optional.empty();
        }

        return Optional.of(sysFileEntity);
    }

    @Override
    public int delete(String id) {
        if(StrUtil.isEmpty(id)){
            return 0;
        }

        SysFileEntity sysFileEntity = fileMapper.selectById(id);
        if(sysFileEntity == null){
            return 0;
        }

        FileResponse fileResponse = FileConvert.instance.sysFileEntity2FileResponse(sysFileEntity);
        String path = buildPath(fileResponse);
        String sourcePath = filePath + "//" + path;
        FileUtil.del(sourcePath);
        return fileMapper.deleteById(id);
    }

    @Override
    public void down(String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        SysFileEntity sysFileEntity = fileMapper.selectById(id);
        if(sysFileEntity == null){
            throw new BizCustomException(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        FileResponse fileResponse = FileConvert.instance.sysFileEntity2FileResponse(sysFileEntity);
        String path = buildPath(fileResponse);
        String sourcePath = filePath+"//"+ path;
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        cn.platform.com.auth.util.FileUtil.setFileDownloadHeader(request, sysFileEntity.getOriginName());
        cn.platform.com.auth.util.FileUtil.writeBytes(sourcePath, response.getOutputStream());
    }

    @Override
    public void tempDown(HttpServletRequest request, HttpServletResponse response, String module, Date createTime, String id, String extend) throws Exception{
        FileResponse fileResponse = new FileResponse();
        fileResponse.setId(id);
        fileResponse.setModule(module);
        fileResponse.setCreateTime(createTime);
        fileResponse.setExtend(extend);
        String path = buildPath(fileResponse);
        String sourcePath = filePath+"//"+ path;
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        cn.platform.com.auth.util.FileUtil.setFileDownloadHeader(request, "temp");
        cn.platform.com.auth.util.FileUtil.writeBytes(sourcePath, response.getOutputStream());
    }

    @Override
    public List<SysFileEntity> selectByIdIn(Set<String> fileIdSet) {
        if(CollUtil.isEmpty(fileIdSet)){
            return new ArrayList<>();
        }

        LambdaQueryWrapper<SysFileEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysFileEntity::getId, fileIdSet);
        return fileMapper.selectList(wrapper);
    }

    @Transactional
    @Override
    public void selectedFile(SelectedFileRequest request) {

        List<String> selectedFileList = request.getSelectedFileList();
        List<SysFileEntity> sysFileEntities = fileMapper.selectByObjId(request.getOrderId());
        Set<SysFileEntity> notSelectedFileIdSet = sysFileEntities.stream().filter(entity -> !selectedFileList.contains(entity.getId())).collect(Collectors.toSet());

        notSelectedFileIdSet.forEach(sysFileEntity ->{
            SysFileEntity fileEntity = fileMapper.selectById(sysFileEntity.getId());
            fileEntity.setUserSelect(0);
            fileMapper.updateById(fileEntity);
        });

        selectedFileList.forEach(fileId -> {
            SysFileEntity fileEntity = fileMapper.selectById(fileId);
            fileEntity.setUserSelect(1);
            fileMapper.updateById(fileEntity);
        });
    }


    private String buildPath(FileResponse fileResponse){
        return  fileResponse.getModule()+"//"+ DateUtil.formatDate(fileResponse.getCreateTime())+"//"+ fileResponse.getId()+fileResponse.getExtend();
    }
}
