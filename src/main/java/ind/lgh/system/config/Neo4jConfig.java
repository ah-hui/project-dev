package ind.lgh.system.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Neo4j配置
 * 提供了三种方式（bolt/http/嵌入式）连接Neo4j数据库
 *
 * @author lgh
 * @since 2018-01-25
 */
@Configuration
@EnableNeo4jRepositories(basePackages = {"ind.lgh.system.repository"})
@EnableTransactionManagement
public class Neo4jConfig {

    @Bean
    public SessionFactory getSessionFactory() {
        return new SessionFactory(boltConfiguration(), "ind.lgh.system.domain.neo4j");
    }

    /**
     * Bolt连接
     * 究竟本质是什么鬼不知道，但是，bolt协议返回的数据多封装了数据库自带的属性，比http协议连接获得更多的数据
     */
    @Bean
    public org.neo4j.ogm.config.Configuration boltConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("bolt://localhost")
                .credentials("neo4j", "root")
                .build();
        return config;
    }

    /**
     * Http连接
     */
    @Bean
    public org.neo4j.ogm.config.Configuration httpConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("http://localhost:7474")
                .credentials("neo4j", "root")
                .build();
        return config;
    }

    /**
     * 嵌入式连接
     * 一般用作测试，不依赖外部Neo4j库，但停机数据丢失
     */
    @Bean
    public org.neo4j.ogm.config.Configuration embeddedConfiguration() {
        /**
         * 使用外部配置文件方式：
         * ConfigurationSource properties = new ClasspathConfigurationSource("ogm.properties");
         * org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration.Builder(properties).build();
         */
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration.Builder()
                .uri("file:/D:/embedded-neo4j/graph.db")
                .credentials("neo4j", "root")
                .build();
        return config;
    }

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    /**
     * 嵌入式库专用 - 取得交互句柄
     * http和bolt方式没有提供
     */
    @Bean
    public GraphDatabaseService graphDatabaseService() {
//        EmbeddedDriver embeddedDriver = (EmbeddedDriver) Components.driver();
//        return embeddedDriver.getGraphDatabaseService();
        return null;
    }
}
