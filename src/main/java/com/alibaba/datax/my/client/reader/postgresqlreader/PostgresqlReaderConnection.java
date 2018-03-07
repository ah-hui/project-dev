package com.alibaba.datax.my.client.reader.postgresqlreader;

import lombok.Getter;
import lombok.Setter;

/**
 * Mysql连接参数
 * 参考基本配置模板-mysqlreader.json和mysqlwriter.json
 *
 * @author lgh
 */
@Getter
@Setter
public class PostgresqlReaderConnection {

    /**
     * 描述：所选取的需要同步的表。使用JSON的数组描述，因此支持多张表同时抽取。
     * 当配置为多张表时，用户自己需保证多张表是同一schema结构，PostgresqlReader不予检查表是否同一逻辑表。
     * 注意，table必须包含在connection配置单元中。
     */
    private String[] table;
    /**
     * 到对端数据库的JDBC连接信息，使用JSON的数组描述，并支持一个库填写多个连接地址
     * jdbcUrl按照PostgreSQL官方规范，并可以填写连接附件控制信息
     */
    private String[] jdbcUrl;
    /**
     * 可选
     * 可以通过该配置型来自定义筛选SQL
     * querySql优先级大于table、column、where选项，当配置了querySql，DataX系统就会忽略table和column
     */
    private String querySql;

    public PostgresqlReaderConnection(String[] table, String[] jdbcUrl) {
        super();
        this.table = table;
        this.jdbcUrl = jdbcUrl;
    }
}
