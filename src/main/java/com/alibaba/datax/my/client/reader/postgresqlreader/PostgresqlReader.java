package com.alibaba.datax.my.client.reader.postgresqlreader;

import com.alibaba.datax.my.client.job.content.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * PostgresqlReader插件 - 从Postgresql读取数据
 * 参考基本配置模板-PostgresqlReader.json
 * 底层实现：PostgresqlReader插件实现了从PostgreSQL读取数据。在底层实现上，PostgresqlReader通过JDBC连接远程PostgreSQL数据库，并执行相应的sql语句将数据从PostgreSQL库中SELECT出来。
 *
 * @author lgh
 */
@Getter
@Setter
public class PostgresqlReader extends Parameter{
    /**
     * 数据源用户名
     */
    private String username;
    /**
     * 数据源指定用户名的密码
     */
    private String password;
    /**
     * 所配置的表中需要同步的列名集合，使用JSON的数组描述字段信息。用户使用*代表默认使用所有列配置
     */
    private String[] column;
    /**
     * 可选参数
     * 代表用户希望使用指定的splitPk进行数据分片
     * DataX因此会启动并发任务进行数据同步，可以大大提供数据同步的效能
     * 推荐splitPk用户使用表主键，因为表主键通常情况下比较均匀，因此切分出来的分片也不容易出现数据热点。
     * 目前splitPk仅支持整形数据切分
     */
    private String splitPk;
    /**
     * 可选参数
     * where条件可以有效地进行业务增量同步
     * 如果不填写where语句，包括不提供where的key或者value，DataX均视作同步全量数据
     */
    private String where;

    // fetchSize
    // 描述：该配置项定义了插件和数据库服务器端每次批量数据获取条数，该值决定了DataX和服务器端的网络交互次数，能够较大的提升数据抽取性能。
    //注意，该值过大(>2048)可能造成DataX进程OOM。。
    //必选：否
    //默认值：1024

    /**
     * 数据源连接属性 - list
     */
    private List<PostgresqlReaderConnection> connection = new ArrayList<>();

    public PostgresqlReader(String username, String password, String[] column, PostgresqlReaderConnection connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = column;
        this.connection.add(connection);
    }

    public PostgresqlReader(String username, String password, PostgresqlReaderConnection connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = new String[]{"*"};
        this.connection.add(connection);
    }

    public PostgresqlReader(String username, String password, String[] column, List<PostgresqlReaderConnection> connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = column;
        this.connection = connection;
    }
}
