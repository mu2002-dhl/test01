package cn.platform.com.auth.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.auth.mapper.UmsDictMapper;
import cn.platform.com.auth.model.request.UmsDictPageRequest;
import cn.platform.com.auth.model.request.UmsDictRequest;
import cn.platform.com.auth.service.UmsDictService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsDict")
@RestController
@RequestMapping("/umsDict")
public class UmsDictController extends BaseController{

    @Resource
    private UmsDictService umsDictService;
    @Resource
    private UmsDictMapper umsDictMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsDictPageRequest request){
        return ResponseData.buildSuccess(umsDictService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsDictMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsDictRequest request){
        return buildAddResponseData(umsDictService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsDictRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsDictService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsDictService.delete(id));
    }
}
