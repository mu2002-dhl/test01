package cn.platform.com.business.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.mapper.UmsActivityMapper;
import cn.platform.com.business.model.request.UmsActivityPageRequest;
import cn.platform.com.business.model.request.UmsActivityRequest;
import cn.platform.com.business.service.UmsActivityService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsActivity")
@RestController
@RequestMapping("/umsActivity")
public class UmsActivityController extends BaseController{

    @Resource
    private UmsActivityService umsActivityService;
    @Resource
    private UmsActivityMapper umsActivityMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsActivityPageRequest request){
        return ResponseData.buildSuccess(umsActivityService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsActivityMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsActivityRequest request){
        return buildAddResponseData(umsActivityService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsActivityRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsActivityService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsActivityService.delete(id));
    }
}
