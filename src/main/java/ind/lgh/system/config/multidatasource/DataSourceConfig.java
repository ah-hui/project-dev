package ind.lgh.system.config.multidatasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * JPA支持的多数据源配置
 * 1.启用多数据源配置-spring.profiles.active=multi-datasource
 * 2.多数据源=2个数据源，请确保有两个数据库并修改配置文件对应
 * 3.单元测试会在主库创建一张表插入两条数据，在从库创建一张表插入两条数据
 *
 * 0.主分支代码不使用多数据源，如果要测试，请打开下方全部注解
 *
 * @author lgh
 */
//@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")
    @Qualifier("primaryDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "secondaryDataSource")
    @Qualifier("secondaryDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }
}
