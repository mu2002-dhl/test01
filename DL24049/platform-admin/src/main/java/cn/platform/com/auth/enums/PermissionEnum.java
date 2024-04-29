package cn.platform.com.auth.enums;

import lombok.Getter;

/**
 * @Description 权限类型
 * @Author lih
 * @Data 2023/11/30 21:43
 */
@Getter
public enum PermissionEnum {

    MENU("菜单", (byte) 0),
    BUTTON("按钮", (byte) 1),
    Api("接口", (byte) 2);

    private String name;
    private byte type;

    PermissionEnum(String name, byte type){
        this.name = name;
        this.type = type;
    }

    public static PermissionEnum getPermissionEnum(byte type){
        for(PermissionEnum item : PermissionEnum.values()){
            if(item.getType() == type){
                return item;
            }
        }

        return null;
    }
}
