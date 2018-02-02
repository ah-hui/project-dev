package ind.lgh.system.config;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.jdbc.http.HttpDriver;
import org.neo4j.ogm.drivers.bolt.driver.BoltDriver;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.service.Components;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;

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

    @Bean
    public Neo4jTransactionManager transactionManager() throws Exception {
        return new Neo4jTransactionManager(getSessionFactory());
    }

    /**
     * Bolt连接
     * 究竟本质是什么鬼不知道，但是，bolt协议返回的数据多封装了数据库自带的属性，比http协议连接获得更多的数据
     */
    @Bean
    public org.neo4j.ogm.config.Configuration boltConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.bolt.driver.BoltDriver")
                .setURI("bolt://localhost")
                .setCredentials("neo4j", "root");
        return config;
    }

    /**
     * Http连接
     */
    @Bean
    public org.neo4j.ogm.config.Configuration httpConfiguration() {
        org.neo4j.ogm.config.Configuration config = new org.neo4j.ogm.config.Configuration();
        config.driverConfiguration()
                .setDriverClassName("org.neo4j.ogm.drivers.http.driver.HttpDriver")
                .setURI("http://localhost:7474")
                .setCredentials("neo4j", "root");
        return config;
    }
}
