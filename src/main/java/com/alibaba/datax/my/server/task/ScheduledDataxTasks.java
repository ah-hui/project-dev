package com.alibaba.datax.my.server.task;

import com.alibaba.datax.core.Engine;
import com.alibaba.datax.my.server.constant.Constant;
import com.alibaba.datax.my.server.domain.DataxTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务集合
 *
 * @author lgh
 * @since 2017-12-21
 */
@Component
public class ScheduledDataxTasks {
    private static final Logger LOG = LoggerFactory.getLogger(ScheduledDataxTasks.class);
    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每10秒执行 - 定时扫描datax任务队列
     */
    @Scheduled(cron = "0/10 * * * * ? ")
    public void dataxTaskScan() {
        // 记录日志
        System.out.println("[ ###datax定时扫描### " + dataFormat.format(new Date()) + " ] 任务队列任务数：" + Constant.TASK_QUEUE.size() + "；datax是否正在执行任务：" + Constant.TASK_IS_RUNNING);
        if (Constant.TASK_QUEUE.size() > 0) {
            if (!Constant.TASK_IS_RUNNING) {
                Constant.TASK_IS_RUNNING = true;
                DataxTask task = Constant.TASK_QUEUE.poll();
                Engine engine = new Engine();
                try {
                    if (task != null) {
                        engine.entry(task);
                    }
                } catch (Throwable throwable) {
                    Constant.TASK_IS_RUNNING = false;
                    // 记录日志
                    throwable.printStackTrace();
                }

            }
        }
    }

}
