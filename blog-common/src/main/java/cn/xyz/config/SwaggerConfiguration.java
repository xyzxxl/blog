package cn.xyz.user.config;

import io.swagger.annotations.Api;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.*;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;


/**
 * @Description: Swagger接口文档配置
 * @Author: Neuronet
 * @Date: 2023/10/1 20:28
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    /*
        swagger的API扫描提供了四种方式，分别如下：
        1、RequestHandlerSelectors.any() 匹配任何controller的接口
        2、RequestHandlerSelectors.withClassAnnotation() 扫描含有类注解的
        3、RequestHandlerSelectors.withMethodAnnotation() 扫描含有方法注解的
        3、RequestHandlerSelectors.basePackage() 扫描指定包路径
     */

    /**
     * @Description: swagger核心配置
     * @Author: Neuronet
     * @Date: 2023/10/1 20:18
     * @Return:
     **/
    @Bean
    public Docket getDocket(){
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("《源码博客》接口文档说明")
                .description("此文档详细说明了源码博客后台接口规范")
                .version("v1.0.0")
                .contact(new Contact("Neuronet","https://itsource.cn","18780084485@163.com"));
        ApiInfo apiInfo = apiInfoBuilder.build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .paths(PathSelectors.any())
                .build()
                .globalRequestParameters(getGlobalRequestParameters())
                .globalResponses(HttpMethod.GET, getGlobalResponseMessage())
                .globalResponses(HttpMethod.POST, getGlobalResponseMessage());
        return docket;
    }

    /**
     * @Description: 生成全局通用请求参数
     * @Author: Neuronet
     * @Date: 2023/10/1 20:22
     * @Return:
     **/
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("Authorization")
                .description("登录令牌")
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .required(false)
                .build());
        return parameters;
    }


    /**
     * @Description: 生成通用的接口文档响应信息
     * @Author: Neuronet
     * @Date: 2023/10/1 20:25
     * @Return:
     **/
    private List<Response> getGlobalResponseMessage() {
        List<Response> responseList = new ArrayList<>();
        responseList.add(new ResponseBuilder().code("4xx").description("请求错误，根据code和msg检查").build());
        return responseList;
    }
}