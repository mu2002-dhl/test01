package cn.platform.com.auth.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.parameters.HeaderParameter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description
 * @Author lih
 * @Data 2023/10/20 20:58
 */
@Configuration
@Slf4j
public class Knife4jConfig {

    private MessageSource messageSource;

    public Knife4jConfig(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Bean
    public GroupedOpenApi createRestApi2() {
        return GroupedOpenApi.builder().group("default").packagesToScan(new String[]{"cn.platform.com"}).addOperationCustomizer(this.customerGlobalHeaderOpenApiCustomizer()).build();
    }

    @Bean
    public OperationCustomizer customerGlobalHeaderOpenApiCustomizer() {
        return (operation, handlerMethod) ->
                operation
                        .addParametersItem((new HeaderParameter()).name("Authorization").description("公共参数:认证token").required(false));
    }

    @Bean
    public OpenApiCustomizer sortTagsAlphabetically() {
        return (openApi) -> openApi.setTags((List)openApi.getTags().stream().sorted(Comparator.comparing((tag) -> StringUtils.stripAccents(tag.getName()))).collect(Collectors.toList()));
    }

    private Info apiInfo() {
        return (new Info()).title("在线接口文档").description("后台管理在线接口文档").version("V1.0.0");
    }

    @Bean
    public OpenAPI springOpenAPI() {
        return (new OpenAPI()).
                info(this.apiInfo()).
                externalDocs(new ExternalDocumentation());
    }
}
