package cn.platform.com.auth.controller;

import cn.platform.com.auth.entity.UmsRoleEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.model.request.RoleAddRequest;
import cn.platform.com.auth.model.request.RoleAuthPermissionRequest;
import cn.platform.com.auth.model.request.RoleModifyRequest;
import cn.platform.com.auth.model.request.RolePageRequest;
import cn.platform.com.auth.service.UmsPermissionRoleService;
import cn.platform.com.auth.service.UmsRoleService;
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
 * 角色表 前端控制器
 * </p>
 *
 * @author lee
 * @since 2023-10-10
 */
@Tag(name = "角色")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Resource
    private UmsRoleService umsRoleService;
    @Resource
    private UmsPermissionRoleService umsPermissionRoleService;

    @Operation(summary = "获取所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsRoleService.all());
    }

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody RolePageRequest request){
        return ResponseData.buildSuccess(umsRoleService.page(request));
    }

    @Operation(summary = "新增")
    @PostMapping()
    public ResponseData add(@Valid @RequestBody RoleAddRequest request){
        return buildAddResponseData(umsRoleService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping()
    public ResponseData modify(@Valid @RequestBody RoleModifyRequest request){
        return buildModifyResponseData(umsRoleService.modify(request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id") Long id){
        return buildDeleteResponseData(umsRoleService.delete(id));
    }

    @Operation(summary = "详情")
    @GetMapping("{id}")
    public ResponseData detail(@PathVariable("id") Long id){
        Optional<UmsRoleEntity> detailOptional = umsRoleService.detail(id);
        if(detailOptional.isEmpty()){
            return ResponseData.buildBizCodeEnum(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return ResponseData.buildSuccess(detailOptional.get());
    }

    @Operation(summary = "角色授权权限")
    @PutMapping("authPermission")
    public ResponseData roleAuthPermission(@Valid@RequestBody RoleAuthPermissionRequest request){
        return buildAddResponseData(umsPermissionRoleService.rolePermissionBinding(request.getRoleId(), request.getPermissionList()));
    }

    @Operation(summary = "角色授权权限")
    @GetMapping("/getRoleAuthPermission/{roleId}")
    public ResponseData getRoleAuthPermission(@PathVariable("roleId")Long roleId){
        List<Long> roleAuthPermission = umsPermissionRoleService.getRoleAuthPermission(roleId);
        return ResponseData.buildSuccess(roleAuthPermission);
    }
}
