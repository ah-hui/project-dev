package ind.lgh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Spring主程序.
 * 启用定时任务 @EnableScheduling
 * 启用异步调用 @EnableAsync
 *
 * @author lgh
 * @since 2017-12-21
 */
/* 启用注解事务管理，之后在访问数据库的Service方法上添加注解@Transactional即可 */
@EnableTransactionManagement
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
