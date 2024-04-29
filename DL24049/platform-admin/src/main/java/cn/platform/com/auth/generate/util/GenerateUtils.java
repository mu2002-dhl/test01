package cn.platform.com.auth.generate.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Db;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.platform.com.auth.generate.model.TableStructureInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @Description
 * @Author lih
 * @Data 2024-01-28 13:26
 */
@Slf4j
public class GenerateUtils {

    private static  String driverClass;
    private static  String url;
    private static  String username;
    private static  String password;
    private static String tableSchema;
    private static Db db;

    public static Db getDb(){
        Yaml yaml = new Yaml();
        try(InputStream inputStream = GenerateUtils.class.getClassLoader().getResourceAsStream("application.yaml") ){
            Map<String, Object> yamlData = yaml.load(inputStream);
            Map<String, Map<String, String>> springMap = ( Map<String, Map<String, String>>)yamlData.get("spring");
            Map<String, String> dataSourceMap = springMap.get("datasource");
            driverClass = dataSourceMap.get("driver-class-name");
            url = dataSourceMap.get("url");
            username = dataSourceMap.get("username");
            password = dataSourceMap.get("password");
            tableSchema = getTableSchema();
            SimpleDataSource dataSource = new SimpleDataSource(url, username, password);
            return Db.use(dataSource, driverClass);
        }catch (Exception e){
            log.error("连接数据库异常", e);
            throw new RuntimeException(e);
        }
    }

    public synchronized static List<TableStructureInfo> getTableFiledInfo(
            String tableName, List<String> pageQueryList,
            List<String> pageColumnList, Map<String, String> pageDictColumnMap) throws Exception{
        if(db == null){
            db = getDb();
        }
        String sqlTmpl = """
                 SELECT
                    column_name AS fieldName,
                    Data_type AS fieldType,
                    column_key AS filedKey,
                    column_comment AS fieldComment,
                    CASE is_nullable WHEN is_nullable = 'YES' THEN TRUE ELSE FALSE END AS fieldRequired
                 FROM information_schema.COLUMNS
                 WHERE table_schema = '%s' AND table_name = '%s'
                 """;
        String sql = String.format(sqlTmpl, tableSchema, tableName);
        List<TableStructureInfo> query = db.query(sql, TableStructureInfo.class);
        query.forEach(tableStructureInfo -> {
            if(pageQueryList.contains(tableStructureInfo.getFieldName())){
                tableStructureInfo.setPageQuery(true);
                tableStructureInfo.setQueryWrapperName(toCamelCase(tableStructureInfo.getFieldName()));
            }
            if(pageColumnList.contains(tableStructureInfo.getFieldName())){
                tableStructureInfo.setPageColumn(true);
            }
            tableStructureInfo.setFieldCamelName(StrUtil.toCamelCase(tableStructureInfo.getFieldName()));
            tableStructureInfo.setFieldType(transferFieldType(tableStructureInfo.getFieldType()));

            if(pageDictColumnMap.containsKey(tableStructureInfo.getFieldName())){
                tableStructureInfo.setDictCode(pageDictColumnMap.get(tableStructureInfo.getFieldName()));
                tableStructureInfo.setDictCalmName(StrUtil.toCamelCase(tableStructureInfo.getDictCode()));
            }
        });
        return query;
    }

    public static String toCamelCase(String str){
        str = StrUtil.toCamelCase(str);
        byte[] bytes = str.getBytes();
        byte b = new String(new byte[]{bytes[0]}).toUpperCase().getBytes()[0];
        bytes[0] = b;
        return new String(bytes);
    }

    private static String getTableSchema(){
        String tableSchema = url.replaceAll("jdbc:mysql://localhost:3306/", "");
        if(tableSchema.contains("?")){
            tableSchema = tableSchema.substring(0, tableSchema.indexOf("?"));
        }
        return tableSchema;
    }

    private static String transferFieldType(String fieldType){
        if("varchar".equals(fieldType) || "text".equals(fieldType) || "char".equals(fieldType)){
            return "String";
        }else if("int".equals(fieldType)){
            return "Integer";
        }else if("bigint".equals(fieldType) || "timestamp".equals(fieldType)){
            return "Long";
        }else if("datetime".equals(fieldType) || "date".equals(fieldType)){
            return "Date";
        }else if("float".equals(fieldType)){
            return "Float";
        }else if("double".equals(fieldType)){
            return "Double";
        }else{
            return fieldType;
        }
    }

    public static void velocityInit(){
        //1.设置velocity的资源加载类
        Properties prop = new Properties();
        prop.put("resource.loader.file.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        //2.加载velocity引擎
        Velocity.init(prop);
    }

    public static void generate(String rootPath, String relativePath, String modelPath, String finalName ,Map<String, Object> replaceMap) throws Exception{
        //初始化velocity
        GenerateUtils.velocityInit();

        //设置替换的占位符
        VelocityContext context = new VelocityContext();
        for(String key : replaceMap.keySet()){
            context.put(key, replaceMap.get(key));
        }

        //读取模板
        Template template = Velocity.getTemplate(modelPath, "utf-8");

        //生成文件
        File rootFile = new File(rootPath + "\\" +relativePath);
        if(!rootFile.exists()){
            rootFile.mkdirs();
        }
        FileWriter fileWriter = new FileWriter(rootPath+ "\\" +relativePath+ "\\" +finalName, Charset.forName("UTF-8"));
        template.merge(context, fileWriter);
        fileWriter.close();
    }
}
