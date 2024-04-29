package cn.platform.com.auth.generate;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.platform.com.auth.generate.model.TableStructureInfo;
import cn.platform.com.auth.generate.util.GenerateUtils;

import java.util.*;

/**
 * @Description
 * @Author lih
 * @Data 2024-01-28 14:01
 */
public class GenerateMain {
    private static String tableName = "um_wxyy";
    private static String rootPath = "D:\\generate\\dl24049\\"+System.currentTimeMillis();
    //page查询
    private static List<String> pageQueryList = List.of("search");
    //page显示列
    private static List<String> pageColumnList = List.of("search", "create_time");
    private static Map<String, String> pageDictColumnMap = Map.of();

    public static void main(String[] args) throws Exception{
        //生成后端代码
        generateBackend();
        //生成前端代码
        generateFrontend();
    }

    private static void generateBackend() throws Exception{
        //生成Entity
        generateEntity();
        //生成Mapper.java
        generateMapper();
        //生成Mapper.xml
        generateMapperXML();
        //生成Request
        generateModelRequest();
        //生成Response
        generateModelResponse();
        //生成PageRequest
        generateModePageRequest();
        //生成Service
        generateService();
        //生成ServiceImpl
        generateServiceImpl();
        //生成Controller
        generateController();
    }

    private static void generateFrontend() throws Exception{
        generateApi();
        generateVue();
    }

    private static void generateEntity() throws Exception{
        List<TableStructureInfo> tableStructureInfoList = GenerateUtils.getTableFiledInfo(tableName, pageQueryList, pageColumnList, pageDictColumnMap);
        String fileName = GenerateUtils.toCamelCase(tableName);
        Map<String, Object> replaceMap = Map.of(
                "tableName", tableName,
                "fileName", fileName,
                "tableStructureInfoList", tableStructureInfoList
                );
        GenerateUtils.generate(rootPath, "entity", "vms/backend/Entity.vm", fileName+"Entity.java", replaceMap);
    }

    private static void generateMapper() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName
        );

        GenerateUtils.generate(rootPath, "mapper", "vms/backend/Mapper.vm", fileName+"Mapper.java", replaceMap);
    }

    private static void generateMapperXML() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName
        );

        GenerateUtils.generate(rootPath, "mapper\\xml", "vms/backend/MapperXml.vm", fileName+"Mapper.xml", replaceMap);
    }

    private static void generateModelRequest() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        List<TableStructureInfo> tableStructureInfoList = GenerateUtils.getTableFiledInfo(tableName, pageQueryList, pageColumnList, pageDictColumnMap);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName,
                "tableStructureInfoList", tableStructureInfoList
        );

        GenerateUtils.generate(rootPath, "model\\request", "vms/backend/Request.vm", fileName+"Request.java", replaceMap);
    }

    private static void generateModelResponse() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        List<TableStructureInfo> tableStructureInfoList = GenerateUtils.getTableFiledInfo(tableName, pageQueryList, pageColumnList, pageDictColumnMap);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName,
                "tableStructureInfoList", tableStructureInfoList
        );

        GenerateUtils.generate(rootPath, "model\\response", "vms/backend/Response.vm", fileName+"Response.java", replaceMap);
    }

    private static void generateModePageRequest() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        List<TableStructureInfo> tableStructureInfoList = GenerateUtils.getTableFiledInfo(tableName, pageQueryList, pageColumnList, pageDictColumnMap);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName,
                "tableStructureInfoList", tableStructureInfoList
        );

        GenerateUtils.generate(rootPath, "model\\request", "vms/backend/PageRequest.vm", fileName+"PageRequest.java", replaceMap);
    }

    private static void generateService() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName
        );

        GenerateUtils.generate(rootPath, "service", "vms/backend/Service.vm", fileName+"Service.java", replaceMap);
    }

    private static void generateServiceImpl() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        String variableName = StrUtil.toCamelCase(tableName);
        List<TableStructureInfo> tableStructureInfoList = GenerateUtils.getTableFiledInfo(tableName, pageQueryList, pageColumnList, pageDictColumnMap);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName,
                "variableName", variableName,
                "tableStructureInfoList", tableStructureInfoList
        );

        GenerateUtils.generate(rootPath, "service\\impl", "vms/backend/ServiceImpl.vm", fileName+"ServiceImpl.java", replaceMap);
    }

    private static void generateController() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        String variableName = StrUtil.toCamelCase(tableName);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName,
                "variableName", variableName
        );

        GenerateUtils.generate(rootPath, "controller", "vms/backend/Controller.vm", fileName+"Controller.java", replaceMap);
    }

    private static void generateApi() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        String variableName = StrUtil.toCamelCase(tableName);
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName,
                "variableName", variableName
        );

        GenerateUtils.generate(rootPath, "frontend", "vms/fronted/Api.vm", variableName+".js", replaceMap);
    }

    private static void generateVue() throws Exception{
        String fileName = GenerateUtils.toCamelCase(tableName);
        String variableName = StrUtil.toCamelCase(tableName);
        List<TableStructureInfo> tableStructureInfoList = GenerateUtils.getTableFiledInfo(tableName, pageQueryList, pageColumnList, pageDictColumnMap);
        Map<String, String> dictMap = new HashMap<>();
        if(CollUtil.isNotEmpty(pageDictColumnMap)){
            pageDictColumnMap.forEach((k, v)->dictMap.put(v, StrUtil.toCamelCase(v)));
        }
        Map<String, Object> replaceMap = Map.of(
                "fileName", fileName,
                "variableName", variableName,
                "tableStructureInfoList", tableStructureInfoList,
                "dictMap", dictMap
        );

        GenerateUtils.generate(rootPath, "frontend", "vms/fronted/Vue.vm", fileName+".vue", replaceMap);
    }
}
