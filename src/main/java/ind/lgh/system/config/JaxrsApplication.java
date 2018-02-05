package ind.lgh.system.config;

import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * JAX-RS application.
 * RestEasy 的resources 和providers 只要声明为Spring 的Bean，它就是会自动注册，无需额外配置
 * 具体可配置项参考：https://github.com/paypal/resteasy-spring-boot/blob/master/mds/USAGE.md
 *
 * @author lgh
 * @since 2018-01-30
 */
@Component
/* 指定应用程序路径 */
@ApplicationPath("/")
public class JaxrsApplication extends Application {
}
