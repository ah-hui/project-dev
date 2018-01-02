package com.alibaba.datax.my.server.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

/**
 * datax任务实体类
 *
 * @author lgh
 */
@Getter
@Setter
public class DataxTask implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    /**
     * for server 相当于taskDetail(Map) toString()
     */
    private String job;

    /**
     * for client Map<String, Job>
     */
    private Map taskDetail;

    public DataxTask() {
    }

    public DataxTask(long id, Map taskDetail) {
        super();
        this.id = id;
        this.taskDetail = taskDetail;
    }

    public String toJson() {
        return JSONObject.toJSONString(this);
    }

}
