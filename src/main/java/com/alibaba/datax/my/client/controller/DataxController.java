package com.alibaba.datax.my.client.controller;

import com.alibaba.datax.my.client.service.DataxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * datax客户端 测试
 * 使用方法：启动项目，使用Postman或者任何测试工具访问数据同步服务的url即可
 *
 * @author lgh
 */
@RestController
@RequestMapping(value = "/client/datax")
public class DataxController {

    @Autowired
    private DataxService dataxService;

    @RequestMapping(value = "/testDatax", method = RequestMethod.POST)
    public String testDatax() {
        try {
            // 1.取得源库和目的库的地址                 ip               port          db_name
            String[] srcJdbc = {"jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "sys_db?useUnicode=true&characterEncoding=utf-8"};
            String targetJdbc = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "biz_db?useUnicode=true&characterEncoding=utf-8";
            // 2.添加一条datax任务记录，将id返回给下一步
            // 3.同步数据 - 测试只增量更新sys_user表
            dataxService.mysqlToMysql(1L, "root", "root", new String[]{"sys_user"}, srcJdbc, targetJdbc);
            // 4.更新id=id的datax任务记录，记录立即返回的结果
            // 5.这么严重的操作-要有操作记录表对应
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequestMapping(value = "/postgresqlToMysql", method = RequestMethod.POST)
    public String postgresqlToMysql() {
        try {
            // 1.取得源库和目的库的地址                 ip               port          db_name
            String[] srcJdbc = {"jdbc:postgresql://" + "172.16.51.11" + ":" + "5433" + "/" + "vr-goods"};
            String targetJdbc = "jdbc:mysql://" + "localhost" + ":" + "3306" + "/" + "vr-goods?useUnicode=true&characterEncoding=utf-8";
            // 2.添加一条datax任务记录，将id返回给下一步
            // 3.同步数据
            dataxService.postgresqlToMysql(1L, "njread", "x653qasw#", new String[]{
                    "dao_log"
//                    ,
//                    "goodsinfo_sjlh",
//                    "pub_appgoods_classset",
//                    "pub_appgoods_goodsset",
//                    "pub_barcode"
            }, srcJdbc, "root", "root", targetJdbc);
            // 4.更新id=id的datax任务记录，记录立即返回的结果
            // 5.这么严重的操作-要有操作记录表对应
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
