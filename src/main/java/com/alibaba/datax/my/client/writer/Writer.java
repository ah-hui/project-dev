package com.alibaba.datax.my.client.writer;

import com.alibaba.datax.my.client.job.content.Parameter;
import lombok.Getter;
import lombok.Setter;

/**
 * Writer
 * 参考Job.json基本配置模板
 *
 * @author lgh
 */
@Getter
@Setter
public class Writer {

    private String name;

    private Parameter parameter;

    public Writer(String name, Parameter parameter) {
        super();
        this.name = name;
        this.parameter = parameter;
    }
}
