package cn.platform.com.auth.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.platform.com.auth.model.request.SelectedFileRequest;
import cn.platform.com.auth.service.FileService;
import cn.platform.com.auth.util.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description
 * @Author lih
 * @Data 2023-12-22 21:52
 */
@Tag(name = "附件管理")
@RestController
@RequestMapping("file")
public class FileController {

    @Resource
    private FileService fileService;

    @Operation(summary = "文件上传")
    @PostMapping("/upload/{module}")
    public ResponseData upload(@PathVariable("module")String module, MultipartFile file) throws Exception{
        return ResponseData.buildSuccess(fileService.upload(module, file));
    }

    @Operation(summary = "文件下载")
    @GetMapping("/down/{id}")
    public void down(@PathVariable("id")String id, HttpServletRequest request, HttpServletResponse response) throws Exception{
        fileService.down(id, request, response);
    }

    @Operation(summary = "临时文件下载")
    @GetMapping("/temp/down")
    public void tempDown (
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam("module") String module,
            @RequestParam("createTime") String createTime,
            @RequestParam("id") String id,
            @RequestParam("extend") String extend) throws Exception{
        DateTime dateTime = DateUtil.parse(createTime);
        fileService.tempDown(request, response, module, dateTime, id, extend);
    }

    @PutMapping("/selectedFile")
    public ResponseData selectedFile(@RequestBody @Valid SelectedFileRequest request){
        fileService.selectedFile(request);
        return ResponseData.buildSuccess();
    }

    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable("id")String id){
        fileService.delete(id);
        return ResponseData.buildSuccess();
    }
}
