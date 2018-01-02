package com.alibaba.datax.my.client.writer.mysqlwriter;

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
public class MysqlWriterConnection {

    /**
     * 所选取的需要同步的表
     * 使用JSON的数组描述，支持多张表同时抽取，但需保证多张表是同一schema结构
     */
    private String[] table;
    /**
     * 目的数据库的 JDBC 连接信息
     * 在一个数据库上只能配置一个 jdbcUrl 值。这与 MysqlReader 支持多个备库探测不同，因为此处不支持同一个数据库存在多个主库的情况(双主导入数据情况)
     */
    private String jdbcUrl;
    /**
     * 可选
     * 可以通过该配置型来自定义筛选SQL
     * querySql优先级大于table、column、where选项，当配置了querySql，DataX系统就会忽略table和column
     */
    private String querySql;

    public MysqlWriterConnection(String[] table, String jdbcUrl) {
        super();
        this.table = table;
        this.jdbcUrl = jdbcUrl;
    }
}
