package cn.platform.com.auth.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2024-01-17 15:09
 */
@Data
public class SelectedFileRequest {
    @NotNull
    private Integer orderId;
    private List<String> selectedFileList;
}
