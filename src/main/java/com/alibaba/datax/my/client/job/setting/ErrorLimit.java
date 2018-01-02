package com.alibaba.datax.my.client.job.setting;

import lombok.Getter;
import lombok.Setter;

/**
 * ErrorLimit - job.setting.errorLimit(脏数据控制)
 * Job支持用户对于脏数据的自定义监控和告警，包括对脏数据最大记录数阈值（record值）或者脏数据占比阈值（percentage值），
 * 当Job传输过程出现的脏数据大于用户指定的数量/百分比，DataX Job报错退出。
 *
 * @author lgh
 */
@Getter
@Setter
public class ErrorLimit {

    private Long record;
    private Double percentage;

    public ErrorLimit() {
        super();
    }

    public ErrorLimit(Long record, Double percentage) {
        super();
        this.record = record;
        this.percentage = percentage;
    }
}
