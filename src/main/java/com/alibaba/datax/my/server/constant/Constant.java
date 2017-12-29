package com.alibaba.datax.my.server.constant;

import com.alibaba.datax.my.server.domain.DataxTask;
import org.springframework.stereotype.Component;

import java.util.concurrent.*;

/**
 * 常量类
 *
 * @author lgh
 */
@Component
public class Constant {

    /**
     * 测试用客户端HOST+IP
     * 测试时客户机就是服务器本机
     */
    public static String CLIENT_URL_4_TEST = "http://localhost:8080/";

    /**
     * 默认的客户端回调地址
     */
    public static String DEFAULT_CALL_BACK_URL = "/datax/receiveTaskStats";

    /**
     * 四种线程池之一：
     * 按需创建线程并缓存60秒，释放空闲线程（60秒未用）
     * Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
     * These pools will typically improve the performance of programs that execute many short-lived asynchronous tasks
     */
    public static ExecutorService CACHED_THREAD_POOL = Executors.newCachedThreadPool();

    /**
     * 线程安全的阻塞队列
     */
    public static BlockingQueue<DataxTask> TASK_QUEUE = new LinkedBlockingQueue<>();

    /**
     * 是否 任务正在执行
     */
    public static boolean TASK_IS_RUNNING = false;

}
