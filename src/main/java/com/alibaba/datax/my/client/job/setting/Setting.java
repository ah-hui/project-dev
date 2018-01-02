package com.alibaba.datax.my.client.job.setting;

import lombok.Getter;
import lombok.Setter;

/**
 * Setting
 * 参考Job.json基本配置模板
 * 1.job.setting.speed(流量控制)
 * 2.job.setting.errorLimit(脏数据控制)
 *
 * @author lgh
 * @see com.alibaba.datax.my.client.job.setting.Speed
 * @see com.alibaba.datax.my.client.job.setting.ErrorLimit
 */
@Getter
@Setter
public class Setting {

    /**
     * 流量控制
     */
    private Speed speed;
    /**
     * 脏数据控制
     */
    private ErrorLimit errorLimit;

    public Setting() {
        super();
        this.speed = new Speed();
        this.errorLimit = new ErrorLimit();
    }

    public Setting(Speed speed, ErrorLimit errorLimit) {
        super();
        this.speed = speed;
        this.errorLimit = errorLimit;
    }

    public Setting(Speed speed) {
        super();
        this.speed = speed;
    }

    public Setting(ErrorLimit errorLimit) {
        super();
        this.errorLimit = errorLimit;
    }

}
