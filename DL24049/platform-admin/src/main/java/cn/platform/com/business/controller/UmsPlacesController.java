package cn.platform.com.business.controller;

import cn.hutool.core.collection.CollUtil;
import cn.platform.com.auth.controller.BaseController;
import cn.platform.com.business.entity.UmsPlacesEntity;
import cn.platform.com.business.mapper.UmsPlacesMapper;
import cn.platform.com.business.model.request.UmsPlacesPageRequest;
import cn.platform.com.business.model.request.UmsPlacesRequest;
import cn.platform.com.business.service.UmsPlacesService;
import cn.platform.com.auth.util.ResponseData;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "UmsPlaces")
@RestController
@RequestMapping("/umsPlaces")
public class UmsPlacesController extends BaseController{

    @Resource
    private UmsPlacesMapper placesMapper;

    @Resource
    private UmsPlacesService umsPlacesService;
    @Resource
    private UmsPlacesMapper umsPlacesMapper;

    @Operation(summary = "分页查询")
    @PostMapping("page")
    public ResponseData page(@RequestBody UmsPlacesPageRequest request){
        return ResponseData.buildSuccess(umsPlacesService.page(request));
    }

    @Operation(summary = "查询所有")
    @GetMapping("all")
    public ResponseData all(){
        return ResponseData.buildSuccess(umsPlacesMapper.selectList(Wrappers.emptyWrapper()));
    }

    @Operation(summary = "新增")
    @PostMapping
    public ResponseData add(@RequestBody @Valid UmsPlacesRequest request){
        return buildAddResponseData(umsPlacesService.add(request));
    }

    @Operation(summary = "修改")
    @PutMapping("/{id}")
    public ResponseData modify(@RequestBody @Valid UmsPlacesRequest request, @PathVariable("id")Integer id){
        return buildModifyResponseData(umsPlacesService.modify(id, request));
    }

    @Operation(summary = "删除")
    @DeleteMapping("{id}")
    public ResponseData delete(@PathVariable("id")Integer id){
        return buildDeleteResponseData(umsPlacesService.delete(id));
    }

    @GetMapping("/{id}")
    public ResponseData detail(@PathVariable("id")Integer id){
        return ResponseData.buildSuccess(umsPlacesService.detail(id));
    }


    @GetMapping("statistics")
    public ResponseData statistics(){
        List<String> placeNameList = new ArrayList<>();
        List<String> placeValueList = new ArrayList<>();
        List<String> ageNameList = new ArrayList<>();
        List<String> ageValueList = new ArrayList<>();
        List<UmsPlacesEntity> umsPlacesEntities = placesMapper.searchRank();
        if(CollUtil.isNotEmpty(umsPlacesEntities)){
            placeNameList = umsPlacesEntities.stream()
                    .filter(entity->entity.getSearchCount()!=null && entity.getSearchCount()!=0)
                    .map(entity-> entity.getName()).collect(Collectors.toList());
            placeValueList = umsPlacesEntities.stream()
                    .filter(entity->entity.getSearchCount()!=null && entity.getSearchCount()!=0)
                    .map(entity-> String.valueOf(entity.getSearchCount())).collect(Collectors.toList());
        }

        List<Map<String, String>> ageList = placesMapper.ageRank();
        if(CollUtil.isNotEmpty(ageList)){
            ageList.forEach(age-> ageNameList.add(age.get("age")));
            ageList.forEach(age-> ageValueList.add(age.get("num")));
        }

        Map<String, Object> map = Map.of(
                "placeNameList", placeNameList,
                "placeValueList", placeValueList,
                "ageNameList", ageNameList,
                "ageValueList", ageValueList);
        return ResponseData.buildSuccess(map);
    }
}
