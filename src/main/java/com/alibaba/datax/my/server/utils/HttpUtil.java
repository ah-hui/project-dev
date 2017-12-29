package com.alibaba.datax.my.server.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Http请求工具类
 * 输入输出都依赖于json-lib
 *
 * @author lgh
 */
public class HttpUtil {

    /**
     * 发送Http POST请求 - 接收响应信息
     *
     * @param url       请求地址
     * @param jsonParam 请求参数
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam) {
        return httpPost(url, jsonParam, false);
    }

    /**
     * 发送Http POST请求 - 可选是否接收响应信息
     *
     * @param url            请求地址
     * @param jsonParam      请求参数
     * @param noNeedResponse 是否-不需要返回结果
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
        // 发送POST请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonResult = null;
        // 合并参数到httpPost - 同时解决中文乱码问题
        if (null != jsonParam) {
            StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
            entity.setContentEncoding("UTF-8");
            entity.setContentType("application/json");
            httpPost.setEntity(entity);
        }
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            // 请求发送成功，得到响应，并处理结果
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String str = EntityUtils.toString(response.getEntity());
                if (noNeedResponse) {
                    return null;
                }
                jsonResult = JSONObject.parseObject(str);
            }
            // 释放资源 - 关闭请求和响应
            response.close();
            httpClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

    /**
     * 发送GET请求
     *
     * @param url 请求的url
     * @return
     */
    public static JSONObject httpGet(String url) {
        // 发送GET请求
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonResult = null;
        try {
            CloseableHttpResponse result = httpClient.execute(httpGet);
            if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                String str = EntityUtils.toString(result.getEntity());
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(str);
                result.close();
                httpClient.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonResult;
    }

}
