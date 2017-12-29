package com.alibaba.datax.my.server.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 任务状态统计信息
 *
 * @author lgh
 */
@Getter
@Setter
public class TaskStats {

    /**
     * 任务id
     */
    private long id;
    /**
     * 任务启动时刻
     */
    private String startTime;
    /**
     * 任务结束时刻
     */
    private String endTime;
    /**
     * 任务总计耗时
     */
    private String totalCosts;
    /**
     * 任务平均流量
     */
    private String byteSpeedPerSecond;
    /**
     * 记录写入速度
     */
    private String recordSpeedPerSecond;
    /**
     * 读出记录总数
     */
    private String totalReadRecords;
    /**
     * 读写失败总数
     */
    private String totalErrorRecords;
    private Boolean isSuccess;

    @Override
    public String toString() {
        return "TaskStats [id=" + id + ", 任务启动时刻=" + startTime + ", 任务结束时刻=" + endTime + ", 任务总计耗时="
                + totalCosts + "s, 任务平均流量=" + byteSpeedPerSecond + "/s, 记录写入速度="
                + recordSpeedPerSecond + "rec/s, 读出记录总数=" + totalReadRecords + ", 读写失败总数="
                + totalErrorRecords + ",是否成功：" + isSuccess + "]";
    }


}
