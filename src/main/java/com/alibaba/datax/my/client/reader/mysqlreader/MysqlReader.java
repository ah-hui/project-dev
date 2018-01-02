package com.alibaba.datax.my.client.reader.mysqlreader;

import com.alibaba.datax.my.client.job.content.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * MysqlReader插件 - 从Mysql读取数据
 * 参考基本配置模板-mysqlreader.json
 * 底层实现：通过JDBC连接器连接到远程的Mysql数据库，并根据用户配置的信息生成查询SELECT SQL语句，然后发送到远程Mysql数据库，并将该SQL执行返回结果使用DataX自定义的数据类型拼装为抽象的数据集，并传递给下游Writer处理
 *
 * @author lgh
 */
@Getter
@Setter
public class MysqlReader extends Parameter {

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
    /**
     * 数据源连接属性 - list
     */
    private List<MysqlReaderConnection> connection = new ArrayList<>();

    public MysqlReader(String username, String password, String[] column, MysqlReaderConnection connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = column;
        this.connection.add(connection);
    }

    public MysqlReader(String username, String password, MysqlReaderConnection connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = new String[]{"*"};
        this.connection.add(connection);
    }

    public MysqlReader(String username, String password, String[] column, List<MysqlReaderConnection> connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = column;
        this.connection = connection;
    }

}
