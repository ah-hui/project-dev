package com.alibaba.datax.my.server.service.impl;

import com.alibaba.datax.my.server.constant.Constant;
import com.alibaba.datax.my.server.domain.DataxTask;
import com.alibaba.datax.my.server.domain.TaskStats;
import com.alibaba.datax.my.server.service.ApiService;
import com.alibaba.datax.my.server.utils.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * datax任务api接口实现
 *
 * @author lgh
 */
@Service
public class ApiServiceImpl implements ApiService {

    /**
     * 执行task - 实质是向阻塞队列添加新元素
     *
     * @param task
     */
    @Override
    public void runTask(DataxTask task) {
        try {
            Constant.TASK_QUEUE.add(task);
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    /**
     * task回调 - 通知调用方执行结果
     *
     * @param taskStats
     */
    @Override
    public void sendTaskStats(TaskStats taskStats) {
        Constant.TASK_IS_RUNNING = false;
        Constant.CACHED_THREAD_POOL.execute(new SendTaskStatsTask(taskStats));
    }

    class SendTaskStatsTask implements Runnable {

        private TaskStats taskStats;

        public SendTaskStatsTask(TaskStats taskStats) {
            super();
            this.taskStats = taskStats;
        }

        @Override
        public void run() {
            try {
                // 发送任务状态统计信息到指定url
                JSONObject response = HttpUtil.httpPost(Constant.CLIENT_URL_4_TEST + Constant.DEFAULT_CALL_BACK_URL, (JSONObject) JSONObject.toJSON(taskStats));
                // 此处最好记录一条日志
                System.out.println("成功发送任务统计信息到：[ " + Constant.CLIENT_URL_4_TEST + Constant.DEFAULT_CALL_BACK_URL + " ]，内容是 [ " + JSONObject.toJSONString(taskStats) + " ]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
