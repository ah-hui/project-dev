package com.alibaba.datax.my.client.service.impl;

import com.alibaba.datax.my.client.job.Job;
import com.alibaba.datax.my.client.job.content.Content;
import com.alibaba.datax.my.client.reader.Reader;
import com.alibaba.datax.my.client.reader.mysqlreader.MysqlReader;
import com.alibaba.datax.my.client.reader.mysqlreader.MysqlReaderConnection;
import com.alibaba.datax.my.client.service.DataxService;
import com.alibaba.datax.my.client.writer.Writer;
import com.alibaba.datax.my.client.writer.mysqlwriter.MysqlWriter;
import com.alibaba.datax.my.client.writer.mysqlwriter.MysqlWriterConnection;
import com.alibaba.datax.my.server.constant.Constant;
import com.alibaba.datax.my.server.domain.DataxTask;
import com.alibaba.datax.my.server.domain.TaskStats;
import com.alibaba.datax.my.server.utils.HttpUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * datax服务实现
 *
 * @author lgh
 */
@Service
public class DataxServiceImpl implements DataxService {

    @Override
    public String receiveTaskStats(TaskStats taskStats) {
        String result = JSONObject.toJSONString(taskStats);
        System.out.println("#######################################");
        System.out.println("Client.receiveTaskStats is running!");
        System.out.println("isSuccess: " + taskStats.getIsSuccess());
        System.out.println("all: " + result);
        System.out.println("#######################################");
        return result;
    }

    @Override
    public String mysqlToMysql(Long taskId, String username, String password, String[] table, String[] srcJdbc, String targetJdbc) {
        //
        MysqlReaderConnection readerConn = new MysqlReaderConnection(table, srcJdbc);
        MysqlWriterConnection writerConn = new MysqlWriterConnection(table, targetJdbc);
        MysqlReader mysqlReader = new MysqlReader(username, password, readerConn);
        mysqlReader.setColumn(new String[]{
                "id",
                "created_by",
                "date_created",
                "deleted",
                "last_updated",
                "remarks",
                "updated_by",
                "version",
                "account_expired",
                "email",
                "hashed_password",
                "login_name",
                "nick_name",
                "phone",
                "token",
        });
        MysqlWriter mysqlWriter = new MysqlWriter(username, password, writerConn);
        mysqlWriter.setWriteMode("insert");
        mysqlWriter.setColumn(new String[]{
                "id",
                "created_by",
                "date_created",
                "deleted",
                "last_updated",
                "remarks",
                "updated_by",
                "version",
                "account_expired",
                "email",
                "hashed_password",
                "login_name",
                "nick_name",
                "phone",
                "token",
        });
        Reader reader = new Reader("mysqlreader", mysqlReader);
        Writer writer = new Writer("mysqlwriter", mysqlWriter);
        Content content = new Content(reader, writer);
        Job job = new Job(content);
        Map<String, Job> map = new HashMap<>(8);
        map.put("job", job);
        DataxTask task = new DataxTask(taskId, map);
        String result = sendTask(task);
        System.out.println("#######################################");
        System.out.println("Client.mysqlToMysql is running!");
        System.out.println("all: " + result);
        System.out.println("#######################################");
        return result;
    }

    private String sendTask(DataxTask task) {
        JSONObject taskJson = (JSONObject) JSONObject.toJSON(task);
        JSONObject json = HttpUtil.httpPost(Constant.SERVER_URL_4_TEST + Constant.SERVER_TASK_URL, taskJson, false);
        // 此处应有异常处理
        return json == null ? null : json.toJSONString();
    }
}
