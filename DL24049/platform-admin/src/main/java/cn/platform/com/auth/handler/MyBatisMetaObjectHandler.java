package cn.platform.com.auth.handler;

import cn.platform.com.auth.model.LoginUser;
import cn.platform.com.auth.util.SecurityUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Description mybatis自动填充时间
 * @Author lih
 * @Data 2023/11/30 21:04
 */
@Component
public class MyBatisMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
       if( this.getFieldValByName("createTime", metaObject) == null ) {
           this.setFieldValByName("createTime", new Date(), metaObject);
       }
       if( this.getFieldValByName("modifyTime", metaObject) == null ) {
           this.setFieldValByName("modifyTime", new Date(), metaObject);
       }
       LoginUser currentLoginUser = SecurityUtil.getCurrentLoginUser();
       if(currentLoginUser!=null){
           if(this.getFieldValByName("creator", metaObject) == null) {
               this.setFieldValByName("creator", currentLoginUser.getUmsUserEntity().getId(), metaObject);
           }

           if(this.getFieldValByName("modifier", metaObject) == null) {
               this.setFieldValByName("modifier", currentLoginUser.getUmsUserEntity().getId(), metaObject);
           }
       }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("modifyTime", new Date(), metaObject);
        LoginUser currentLoginUser = SecurityUtil.getCurrentLoginUser();
        if(currentLoginUser!=null){
            this.setFieldValByName("modifier", currentLoginUser.getUmsUserEntity().getId(), metaObject);
        }
    }
}
