package com.example.somecode.swagger2;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 1. swagger配置类
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi2() {
        return new Docket(DocumentationType.SWAGGER_2) // SWAGGER_2
                .groupName("后端管理平台2")
                .apiInfo(apiInfo())
                .select()
                // 此处自行修改为自己的 Controller 包路径
                .apis(RequestHandlerSelectors.basePackage("com.xxx.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setGlobalParameters());
    }

    @Bean
    public Docket createRestApi() {
        Predicate<RequestHandler> adminPredicate = RequestHandlerSelectors.basePackage("com.xx.admin.controller");
        Predicate<RequestHandler> articlePredicate = RequestHandlerSelectors.basePackage("com.xx.article.controller");

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("后端管理平台1")
                .apiInfo(apiInfo())
                //是否开启 (true 开启  false隐藏。生产环境建议隐藏)
                //.enable(false)
                .select()
                //扫描的路径包,设置basePackage会将包下的所有被@Api标记类的所有方法作为api
                .apis(RequestHandlerSelectors.basePackage("com.example.somecode.swagger2"))
//                .apis(Predicates.or(adminPredicate, articlePredicate))
                //指定路径处理PathSelectors.any()代表所有的路径
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(setGlobalParameters());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //设置文档标题(API名称)
                .title("SpringBoot中使用Swagger2接口规范")
                //文档描述
                .description("接口说明")
                //服务条款URL
                .termsOfServiceUrl("http://localhost:8080/")
                //版本号
                .version("1.0.0")
                .contact(new Contact("author", "https://baidu.com", "xxxx@163.com"))
                .build();
    }

    /**
     * 设置全局参数
     *
     * @return
     */
    private List<Parameter> setGlobalParameters() {
        List<Parameter> globalParameterList = new ArrayList<>();

        //Header中必需 token参数。非必填，传空也可以，一般业务登录拦截器校验 token是否合法
        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name("token").description("用户 TOKEN参数")
                .required(false)// 非必填
                .modelRef(new ModelRef("string"))
                .parameterType("header");
        globalParameterList.add(tokenBuilder.build());

        return globalParameterList;
    }
}
