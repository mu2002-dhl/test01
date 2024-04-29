package cn.platform.com.auth.controller;

import cn.platform.com.auth.entity.UmsDictItemEntity;
import cn.platform.com.auth.mapper.UmsDictItemMapper;
import cn.platform.com.auth.model.request.UmsDictItemPageRequest;
import cn.platform.com.auth.model.request.UmsDictItemRequest;
import cn.platform.com.auth.service.UmsDictItemService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsDictItem")
@RestController
@RequestMapping("/umsDictItem")
public class UmsDictItemController extends BaseController{

    @Resource
    private UmsDictItemService umsDictItemService;
    @Resource
    private UmsDictItemMapper umsDictItemMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsDictItemPageRequest request){
        return ResponseData.buildSuccess(umsDictItemService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        LambdaQueryWrapper<UmsDictItemEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.orderByAsc(UmsDictItemEntity :: getSort);
        return ResponseData.buildSuccess(umsDictItemMapper.selectList(wrapper));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsDictItemRequest request){
        return buildAddResponseData(umsDictItemService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsDictItemRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsDictItemService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsDictItemService.delete(id));
    }

    @Operation(summary = "获取字典的字典项")
    @GetMapping("/dict/{dictCode}")
    public ResponseData selectByDictId(@PathVariable("dictCode")String dictCode){
        return ResponseData.buildSuccess(umsDictItemService.selectByDictCode(dictCode));
    }
}
