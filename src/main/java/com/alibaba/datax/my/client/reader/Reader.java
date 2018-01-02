package com.alibaba.datax.my.client.reader;

import com.alibaba.datax.my.client.job.content.Parameter;
import lombok.Getter;
import lombok.Setter;

/**
 * Reader
 * 参考Job.json基本配置模板
 *
 * @author lgh
 */
@Getter
@Setter
public class Reader {

    private String name;

    private Parameter parameter;

    public Reader(String name, Parameter parameter) {
        super();
        this.name = name;
        this.parameter = parameter;
    }
}
