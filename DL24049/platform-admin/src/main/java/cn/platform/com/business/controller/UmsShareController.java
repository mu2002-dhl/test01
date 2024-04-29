package cn.platform.com.business.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.mapper.UmsShareMapper;
import cn.platform.com.business.model.request.UmsSharePageRequest;
import cn.platform.com.business.model.request.UmsShareRequest;
import cn.platform.com.business.service.UmsShareService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsShare")
@RestController
@RequestMapping("/umsShare")
public class UmsShareController extends BaseController{

    @Resource
    private UmsShareService umsShareService;
    @Resource
    private UmsShareMapper umsShareMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsSharePageRequest request){
        return ResponseData.buildSuccess(umsShareService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsShareMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsShareRequest request){
        return buildAddResponseData(umsShareService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsShareRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsShareService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsShareService.delete(id));
    }
}
