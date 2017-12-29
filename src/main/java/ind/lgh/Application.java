package ind.lgh;

import com.alibaba.datax.core.Engine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring主程序.
 * 启用定时任务 @EnableScheduling
 * 启用异步调用 @EnableAsync
 *
 * @author lgh
 * @since 2017-12-21
 */
@EnableScheduling
@SpringBootApplication
public class Application {

    // 配置系统变量
    @Bean
    public String initProperty() {
        /**
         * 配置datax.home为resource目录 - 与datax提供的hook连接
         * @see com.alibaba.datax.core.job.JobContainer line 973
         */
        String path = Engine.class.getResource("/").getPath().toString();
        System.setProperty("datax.home", path);
        System.out.println("### 系统变量 ### [ datax.home : " + path + " ]");
        // 这里最好打印日志出来
        return null;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
