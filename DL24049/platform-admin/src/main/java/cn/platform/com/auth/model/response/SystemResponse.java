package cn.platform.com.auth.model.response;

import lombok.Data;

@Data
public class SystemResponse {
    private String name;

    private boolean loginEnable;
    private boolean loginRole;
}