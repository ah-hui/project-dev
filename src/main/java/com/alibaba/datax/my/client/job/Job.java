package com.alibaba.datax.my.client.job;

import com.alibaba.datax.my.client.job.content.Content;
import com.alibaba.datax.my.client.job.setting.Setting;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * datax作业
 * 参考Job.json基本配置模板
 *
 * @author lgh
 */
@Getter
@Setter
public class Job {

    private Setting setting;
    private List<Content> content = new ArrayList<>();

    public Job(Content content) {
        super();
        this.setting = new Setting();
        this.content.add(content);
    }

    public Job(List<Content> content) {
        super();
        this.setting = new Setting();
        this.content = content;
    }

    public Job(Setting setting, List<Content> content) {
        super();
        this.setting = setting;
        this.content = content;
    }

}
