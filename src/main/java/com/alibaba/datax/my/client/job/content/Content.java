package com.alibaba.datax.my.client.job.content;

import com.alibaba.datax.my.client.reader.Reader;
import com.alibaba.datax.my.client.writer.Writer;
import lombok.Getter;
import lombok.Setter;

/**
 * Content
 * 参考Job.json基本配置模板
 *
 * @author lgh
 */
@Getter
@Setter
public class Content {

    private Reader reader;
    private Writer writer;

    public Content(Reader reader, Writer writer) {
        super();
        this.reader = reader;
        this.writer = writer;
    }
}
