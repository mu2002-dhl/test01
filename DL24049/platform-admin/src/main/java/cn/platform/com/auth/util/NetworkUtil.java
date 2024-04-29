package cn.platform.com.auth.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @Description
 * @Author lih
 * @Data 2023/11/25 12:48
 */
@Slf4j
public class NetworkUtil {

    /***
     * 获取请求的ip
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        String ipAddress = null;
        try{
            ipAddress = request.getHeader("x-forwarded-for");
            if(ipAddress == null || ipAddress.length() == 0|| "unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("Proxy-Client-IP");
            }
            if(ipAddress == null || ipAddress.length() == 0|| "unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ipAddress == null || ipAddress.length() == 0|| "unknown".equalsIgnoreCase(ipAddress)){
                ipAddress = request.getRemoteAddr();
                if(ipAddress.equals("127.0.0.1")){
                    //根据网卡获取本机配置的ip
                    InetAddress inet = null;
                    try {
                        inet = InetAddress.getLocalHost();
                    }catch (UnknownHostException e){
                        e.printStackTrace();
                    }
                    ipAddress = inet.getHostAddress();
                }
            }
            //对于通过多个代理的情况，第一个IP为客户端真实IP，多个IP按照','分割
            if(ipAddress != null && ipAddress.length()>15){
                if(ipAddress.indexOf(",")>0){
                    ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
                }
            }
        }catch (Exception e){
            log.error("【获取IP失败】", e);
            ipAddress = "";
        }

        return ipAddress;
    }
}
