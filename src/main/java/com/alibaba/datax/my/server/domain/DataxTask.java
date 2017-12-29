package com.alibaba.datax.my.server.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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

    private String job;

    public String toJson() {
        return JSONObject.toJSONString(this);
    }

}
