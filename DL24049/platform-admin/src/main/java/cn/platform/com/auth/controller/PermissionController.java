package cn.platform.com.auth.controller;

import cn.platform.com.auth.entity.UmsPermissionEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.model.PermissionTreeNode;
import cn.platform.com.auth.model.request.PermissionAddRequest;
import cn.platform.com.auth.model.request.PermissionModifyRequest;
import cn.platform.com.auth.service.UmsPermissionService;
import cn.platform.com.auth.util.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Tag(name = "权限")
@RestController
@RequestMapping("/permission")
public class PermissionController extends BaseController {

    @Resource
    private UmsPermissionService umsPermissionService;

    @Operation(summary = "获取权限树")
    @GetMapping
    public ResponseData tree(){
        List<PermissionTreeNode> treeList = umsPermissionService.tree();
        return ResponseData.buildSuccess(treeList);
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@Valid @RequestBody PermissionAddRequest request){
        return buildAddResponseData(umsPermissionService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping
    public ResponseData modify(@Valid @RequestBody PermissionModifyRequest request){
        return buildModifyResponseData(umsPermissionService.modify(request));
    }

    @Operation(summary = "详情")
    @GetMapping("/detail/{id}")
    public ResponseData detail(@PathVariable("id")Long id){
        Optional<UmsPermissionEntity> detail = umsPermissionService.detail(id);
        if(detail.isEmpty()){
            return ResponseData.buildBizCodeEnum(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return ResponseData.buildSuccess(detail.get());
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Long id){
        return buildDeleteResponseData(umsPermissionService.delete(id));
    }
}
