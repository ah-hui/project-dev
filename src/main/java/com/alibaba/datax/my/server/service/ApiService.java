package com.alibaba.datax.my.server.service;

import com.alibaba.datax.my.server.domain.DataxTask;
import com.alibaba.datax.my.server.domain.TaskStats;

/**
 * datax任务api接口服务
 *
 * @author lgh
 */
public interface ApiService {

    /**
     * 执行数据同步任务
     *
     * @param task
     */
    public void runTask(DataxTask task);

    /**
     * 发送任务状态统计信息到客户端
     *
     * @param taskStats
     */
    public void sendTaskStats(TaskStats taskStats);

}
