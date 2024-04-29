package cn.platform.com.auth.model.response;

import lombok.Data;

import java.util.List;

/**
 * @Description
 * @Author lih
 * @Data 2023/12/3 20:31
 */
@Data
public class PageResponse<T> {
    private List<T> records;
    private long total;
    private long size;
    private long totalNum;
    private long current;

    public PageResponse(List<T> records, long total, long size, long current){
        this.records = records;
        this.total = total;
        this.size = size;
        this.current = current;
    }

    public long getTotalNum(){
        if(total % size == 0){
            return total / size;
        }else{
            return total / size + 1;
        }
    }
}
