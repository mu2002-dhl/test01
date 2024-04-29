package cn.platform.com.business.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.mapper.UmsStoreMapper;
import cn.platform.com.business.model.request.UmsStorePageRequest;
import cn.platform.com.business.model.request.UmsStoreRequest;
import cn.platform.com.business.service.UmsStoreService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsStore")
@RestController
@RequestMapping("/umsStore")
public class UmsStoreController extends BaseController{

    @Resource
    private UmsStoreService umsStoreService;
    @Resource
    private UmsStoreMapper umsStoreMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsStorePageRequest request){
        return ResponseData.buildSuccess(umsStoreService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsStoreMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsStoreRequest request){
        return buildAddResponseData(umsStoreService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsStoreRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsStoreService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsStoreService.delete(id));
    }
}
