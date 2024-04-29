package cn.platform.com.auth.controller;

import cn.platform.com.auth.entity.UmsDeptEntity;
import cn.platform.com.auth.enums.CommonBizCodeEnum;
import cn.platform.com.auth.model.DeptTreeNode;
import cn.platform.com.auth.model.request.DeptAddRequest;
import cn.platform.com.auth.model.request.DeptModifyRequest;
import cn.platform.com.auth.service.UmsDeptService;
import cn.platform.com.auth.util.ResponseData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/27 22:01
 */
@Tag(name = "部门")
@RestController
@RequestMapping("dept")
public class DeptController extends BaseController {

    @Resource
    private UmsDeptService umsDeptService;

    @Operation(summary = "获取部门详情")
    @GetMapping("/detail/{id}")
    public ResponseData detail(@PathVariable("id")Long id){
        Optional<UmsDeptEntity> detailOptional = umsDeptService.detail(id);
        if(detailOptional.isEmpty()){
            return ResponseData.buildBizCodeEnum(CommonBizCodeEnum.OPERATOR_DATA_NOT_EXIST);
        }

        return ResponseData.buildSuccess(detailOptional.get());
    }

    @Operation(summary = "获取部门树形菜单")
    @GetMapping()
    public ResponseData tree(){
        List<DeptTreeNode> treeList = umsDeptService.tree();
        return ResponseData.buildSuccess(treeList);
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@Valid @RequestBody DeptAddRequest request){
        return buildAddResponseData(umsDeptService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping
    public ResponseData modify(@Valid @RequestBody DeptModifyRequest request){
        return buildModifyResponseData(umsDeptService.modify(request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("/{id}")
    public ResponseData delete(@PathVariable("id")Long id){
        return buildModifyResponseData(umsDeptService.delete(id));
    }
}
