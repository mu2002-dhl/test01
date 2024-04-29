package cn.platform.com.business.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.mapper.UmsCommentMapper;
import cn.platform.com.business.model.request.UmsCommentPageRequest;
import cn.platform.com.business.model.request.UmsCommentRequest;
import cn.platform.com.business.service.UmsCommentService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsComment")
@RestController
@RequestMapping("/umsComment")
public class UmsCommentController extends BaseController{

    @Resource
    private UmsCommentService umsCommentService;
    @Resource
    private UmsCommentMapper umsCommentMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsCommentPageRequest request){
        return ResponseData.buildSuccess(umsCommentService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsCommentMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsCommentRequest request){
        return buildAddResponseData(umsCommentService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsCommentRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsCommentService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsCommentService.delete(id));
    }
}
