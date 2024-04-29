package cn.platform.com.business.controller;

import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.mapper.UmsNoticeMapper;
import cn.platform.com.business.model.request.UmsNoticePageRequest;
import cn.platform.com.business.model.request.UmsNoticeRequest;
import cn.platform.com.business.service.UmsNoticeService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@Tag(name = "UmsNotice")
@RestController
@RequestMapping("/umsNotice")
public class UmsNoticeController extends BaseController{

    @Resource
    private UmsNoticeService umsNoticeService;
    @Resource
    private UmsNoticeMapper umsNoticeMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsNoticePageRequest request){
        return ResponseData.buildSuccess(umsNoticeService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsNoticeMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsNoticeRequest request){
        return buildAddResponseData(umsNoticeService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsNoticeRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsNoticeService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsNoticeService.delete(id));
    }
}
