package cn.platform.com.business.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.mapper.UmWxyyMapper;
import cn.platform.com.business.model.request.UmWxyyPageRequest;
import cn.platform.com.business.model.request.UmWxyyRequest;
import cn.platform.com.business.model.request.UmsWxyyAskRequest;
import cn.platform.com.business.service.UmWxyyService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmWxyy")
@RestController
@RequestMapping("/umWxyy")
public class UmWxyyController extends BaseController{

    @Resource
    private UmWxyyService umWxyyService;
    @Resource
    private UmWxyyMapper umWxyyMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmWxyyPageRequest request){
        return ResponseData.buildSuccess(umWxyyService.page(request));
    }

    @PostMapping("ask")
    public ResponseData ask(@RequestBody UmsWxyyAskRequest request){
        return ResponseData.buildSuccess(umWxyyService.ask(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umWxyyMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmWxyyRequest request){
        return buildAddResponseData(umWxyyService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmWxyyRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umWxyyService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umWxyyService.delete(id));
    }
}
