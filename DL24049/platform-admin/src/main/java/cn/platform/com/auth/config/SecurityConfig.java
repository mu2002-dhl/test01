package cn.platform.com.auth.config;

import cn.platform.com.auth.handler.AuthenticationEntryPointHandler;
import cn.platform.com.auth.handler.AuthenticationTokenFilterHandler;
import cn.platform.com.auth.handler.AuthenticationAccessDeniedHandler;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/12 21:16
 */
@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Resource
    private AuthenticationTokenFilterHandler authenticationTokenFilterHandler;
    @Resource
    private AuthenticationEntryPointHandler authenticationEntryPointHandler;
    @Resource
    private AuthenticationAccessDeniedHandler authenticationAccessDeniedHandler;

    /**替换默认密码加密器
     * 我们一般使用SpringSecurity为我们提供的BCryptPasswordEncoder（内部会生成一个随机的盐，保证每次加密的结果都不一样）
     * */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception {
        http
                //关闭csrf攻击
                .csrf(csrf->csrf.disable())
                //不通过Session获取SecurityContext
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                //设置请求路径是否需要认证
                .authorizeHttpRequests(authorize->
                        authorize
                                .requestMatchers(
                                        "/auth/login",
                                        "/notify/captcha",
                                        "/doc.html",
                                        "/webjars/**",
                                        "/v3/api-docs/**",
                                        "/app/noSignIn/**",
                                        "/file/down/*",
                                        "/system/info",
                                        "/user/register",
                                        "/role/all"
                                ).permitAll()
                                .anyRequest().authenticated()
                )
                //将authenticationTokenFilterHandler放在UsernamePasswordAuthenticationFilter之前执行
                .addFilterBefore(authenticationTokenFilterHandler, UsernamePasswordAuthenticationFilter.class)
                //设置未授权及未认证通用返回结果
                .exceptionHandling(exceptionHandler->
                        exceptionHandler
                                .authenticationEntryPoint(authenticationEntryPointHandler)
                                .accessDeniedHandler(authenticationAccessDeniedHandler)
                )
                //允许跨域
                .cors(cors->{

                });
        return http.build();
    }
}
