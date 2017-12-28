package ind.lgh.system.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 常量集合
 * <p>
 * 1.借此说一下Log4j和Slf4j的比较
 * 2.SLF4J(Simple logging Facade for Java)是为各种loging APIs提供一个简单统一的接口，Log4j是实现
 * 3.slf4j并不是一种具体的日志系统，而是一个用户日志系统的facade，允许用户在部署最终应用时方便的变更其日志系统
 *
 * @author lgh
 * @since 2017-12-26
 */
@Component
public class Constants {

    private static final Logger logger = LoggerFactory.getLogger(Constants.class);

    /**
     * 多环境配置之当前活动配置
     */
    public static String SPRING_PROFILES_ACTIVE;

    /**
     * 初始化静态常量
     * 方法可以被@PostConstruct修饰，实现这样的功能：
     * 在依赖注入完成之后执行，通常用来做一些初始化工作
     */
    @PostConstruct
    public void init() {
        SPRING_PROFILES_ACTIVE = springProfilesActive;
        logger.info("#######################  Constants.init() successfully");
    }

    @Value("${spring.profiles.active}")
    private String springProfilesActive;

}
