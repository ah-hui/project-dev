package com.alibaba.datax.my.client.service;

import com.alibaba.datax.my.server.domain.TaskStats;

/**
 * datax服务
 *
 * @author lgh
 */
public interface DataxService {

    public String receiveTaskStats(TaskStats taskStats);

    public String mysqlToMysql(Long taskId, String username, String password, String[] table, String[] srcJdbc, String targetJdbc);

    public String postgresqlToMysql(Long taskId, String srcUsername, String srcPassword, String[] table, String[] srcJdbc, String targetUsername, String targetPassword, String targetJdbc);
}
