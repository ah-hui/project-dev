package com.alibaba.datax.my.client.writer.mysqlwriter;

import com.alibaba.datax.my.client.job.content.Parameter;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * MysqlWriter插件 - 写入数据到Mysql主库
 * 参考基本配置模板-mysqlwriter.json
 * 底层实现：MysqlWriter 通过 JDBC 连接远程 Mysql 数据库，并执行相应的 insert into ... 或者 replace into ... 的 sql 语句将数据写入 Mysql，内部会分批次提交入库，需要数据库本身采用 innodb 引擎
 *
 * @author lgh
 */
@Getter
@Setter
public class MysqlWriter extends Parameter {

    /**
     * 目的数据库用户名
     */
    private String username;
    /**
     * 目的数据库指定用户名的密码
     */
    private String password;
    /**
     * 目的表需要写入数据的字段,字段之间用英文逗号分隔
     * column配置项必须指定，不能留空，不能配置任何常量值
     */
    private String[] column;
    /**
     * 可选参数 by default = insert
     * 所有选项：insert/replace/update
     * 控制写入数据到目标表采用 insert into 或者 replace into 或者 ON DUPLICATE KEY UPDATE 语句
     */
    private String writeMode;
    /**
     * 可选参数
     * DataX在获取Mysql连接时，执行session指定的SQL语句，修改当前connection session属性
     * 例："set session sql_mode='ANSI'"
     */
    private String[] session;
    /**
     * 可选参数
     * 写入数据到目的表前，会先执行这里的标准语句
     * 请使用 @table 表示单个表名, 使用 "表名" 表示多个对应的表名
     * 例："delete from test"
     */
    private String[] preSql;
    /**
     * 可选参数
     * 写入数据到目的表后，会执行这里的标准语句
     * （原理同 preSql ）
     */
    private String[] postSql;
    /**
     * 可选参数 by default = 1024
     * 一次性批量提交的记录数大小
     * 该值可以极大减少DataX与Mysql的网络交互次数，并提升整体吞吐量。但是设置过大可能会造成DataX运行进程OOM
     */
    private String batchSize;
    /**
     * 数据源连接属性 - list
     */
    private List<MysqlWriterConnection> connection = new ArrayList<>();

    public MysqlWriter(String username, String password, String[] column, MysqlWriterConnection connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = column;
        this.connection.add(connection);
    }

    public MysqlWriter(String username, String password, String writeMode, MysqlWriterConnection connection) {
        super();
        this.username = username;
        this.password = password;
        this.writeMode = writeMode;
        this.column = new String[]{"*"};
        this.connection.add(connection);
    }

    public MysqlWriter(String username, String password, MysqlWriterConnection connection) {
        super();
        this.username = username;
        this.password = password;
        this.column = new String[]{"*"};
        this.connection.add(connection);
    }
}
