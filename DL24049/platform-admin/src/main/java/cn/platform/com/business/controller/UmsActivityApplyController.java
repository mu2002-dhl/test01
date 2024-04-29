package cn.platform.com.business.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.mapper.UmsActivityApplyMapper;
import cn.platform.com.business.model.request.UmsActivityApplyPageRequest;
import cn.platform.com.business.model.request.UmsActivityApplyRequest;
import cn.platform.com.business.service.UmsActivityApplyService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsActivityApply")
@RestController
@RequestMapping("/umsActivityApply")
public class UmsActivityApplyController extends BaseController{

    @Resource
    private UmsActivityApplyService umsActivityApplyService;
    @Resource
    private UmsActivityApplyMapper umsActivityApplyMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsActivityApplyPageRequest request){
        return ResponseData.buildSuccess(umsActivityApplyService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("/all/{activityId}")
    public ResponseData all(@PathVariable("activityId")Integer activityId){
        return ResponseData.buildSuccess(umsActivityApplyMapper.selectByActivityId(activityId));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsActivityApplyRequest request){
        return buildAddResponseData(umsActivityApplyService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsActivityApplyRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsActivityApplyService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsActivityApplyService.delete(id));
    }
}
