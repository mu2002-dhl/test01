package cn.platform.com.auth.generate.model;

import lombok.Data;

/**
 * @Description
 * @Author lih
 * @Data 2024-01-28 13:42
 */
@Data
public class TableStructureInfo {
    private String fieldName;
    private String fieldCamelName;
    private String fieldType;
    private String fieldComment;
    private String filedKey;
    private boolean fieldRequired;
    private boolean pageQuery;
    private String queryWrapperName;
    private boolean pageColumn;
    private String dictCode;
    private String dictCalmName;
}
