package ind.lgh.system.config.swagger2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 为大家介绍RESTful API的重磅好伙伴!!Swagger2!!
 * 1.它可以轻松的整合到Spring Boot中，并与Spring MVC程序配合组织出强大RESTful API文档。
 * 2.它既可以减少我们创建文档的工作量，同时说明内容又整合入实现代码中，让维护文档和修改代码整合为一体，可以让我们在修改代码逻辑的同时方便的修改文档说明。
 * 3.另外Swagger2也提供了强大的页面测试功能来调试每个RESTful API.
 * 4.背景：构建RESTful API的目的通常都是由于多终端的原因，这些终端会共用很多底层业务逻辑，因此我们会抽象出这样一层来同时服务于多个移动端或者Web前端。这样一来，我们的RESTful API就有可能要面对多个开发人员或多个开发团队。为了减少与其他团队平时开发期间的频繁沟通成本...
 * 5.如何使用：在本配置里，配置扫描那些API给swagger展现，其实已经可以生产文档了（针对请求本身的信息）。
 * 6.更好的使用：直接生产文档对用户不友好，通常要通过注解丰富文档内容。通过@ApiOperation注解来给API增加说明、通过@ApiImplicitParams、@ApiImplicitParam注解来给参数增加说明。
 * 7.访问http://localhost:8080/swagger-ui.html看到结果
 * http://localhost:8080/v2/api-docs
 *
 * @author lgh
 * @since 2017-12-21
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                /* 扫描指定包下全部，暴露给swagger来展现 */
                // .apis(RequestHandlerSelectors.basePackage("ind.lgh.system.controller"))
                /* 扫描带有指定注解的类，暴露给swagger来展现 */
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关请关注：https://github.com/JesseyGone/project-dev/")
                .termsOfServiceUrl("https://github.com/JesseyGone/project-dev/")
                .contact("lgh")
                .version("1.0")
                .build();
    }
}
