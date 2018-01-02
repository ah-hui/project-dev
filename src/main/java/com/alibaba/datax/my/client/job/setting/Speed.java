package com.alibaba.datax.my.client.job.setting;

import lombok.Getter;
import lombok.Setter;

/**
 * Speed - job.setting.speed(流量控制)
 * Job支持用户对速度的自定义控制，channel的值可以控制同步时的并发数，byte的值可以控制同步时的速度
 * !!!注意：由于Speed的第二项是byte是保留字，无法声明变量，所以要对应修改源码:byte->speedByte
 *
 * @author lgh
 * @see com.alibaba.datax.core.util.container.CoreConstant line 90
 */
@Getter
@Setter
public class Speed {
    /**
     * 通道数量
     */
    private Integer channel;
    /**
     * 通道速度 byte/s
     * 如果单通道速度1MB，配置byte为1048576表示一个channel
     */
    private Integer speedByte;

    /**
     * 无参构造 - 默认channel为5
     */
    public Speed() {
        super();
        this.channel = 5;
    }

    public Speed(Integer channel) {
        super();
        this.channel = channel;
    }

    public Speed(Integer channel, Integer speedByte) {
        super();
        this.channel = channel;
        this.speedByte = speedByte;
    }

}
