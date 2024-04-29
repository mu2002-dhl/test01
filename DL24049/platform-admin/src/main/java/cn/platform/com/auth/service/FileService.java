package cn.platform.com.auth.service;

import cn.platform.com.auth.entity.SysFileEntity;
import cn.platform.com.auth.model.request.SelectedFileRequest;
import cn.platform.com.auth.model.response.FileResponse;
import cn.platform.com.auth.model.request.FileRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-23 20:49
 */
public interface FileService {

    FileResponse upload(String module, MultipartFile file) throws Exception;
    int add(FileRequest request);

    Optional<SysFileEntity> detail(String id);

    int delete(String id);

    void down(String id, HttpServletRequest request, HttpServletResponse response) throws Exception;

    void tempDown(HttpServletRequest request, HttpServletResponse response, String module, Date createTime, String id, String extend) throws Exception;

    List<SysFileEntity> selectByIdIn(Set<String> fileIdSet);

    void selectedFile(SelectedFileRequest request);
}
