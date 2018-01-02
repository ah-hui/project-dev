package com.alibaba.datax.my.server.controller;

import com.alibaba.datax.my.client.job.Job;
import com.alibaba.datax.my.server.domain.DataxTask;
import com.alibaba.datax.my.server.service.ApiService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * datax服务暴露
 *
 * @author lgh
 */
@RestController
@RequestMapping("/server/datax")
public class ApiController {

    @Autowired
    private ApiService apiService;

    /**
     * 接收ETL任务
     *
     * @param s
     * @param request
     */
    @RequestMapping(value = "/runTask", method = RequestMethod.POST)
    public String runTask(@RequestBody String s, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>(8);
        try {
            JSONObject parsed = JSONObject.parseObject(s);
            long id = parsed.getLong("id");
            String job = parsed.getString("taskDetail");
            DataxTask task = new DataxTask();
            task.setId(id);
            task.setJob(job);
            System.out.println(job);
            apiService.runTask(task);
            map.put("success", true);
            map.put("code", 0);
            map.put("data", "任务接收成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSONObject.toJSONString(map);
    }

}
