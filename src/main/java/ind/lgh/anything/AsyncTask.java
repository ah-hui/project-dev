package ind.lgh.anything;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 异步调用
 * 0.确保在主程序用注解启用该功能
 * 1.任务类配合单元测试
 * 2.三个任务分别执行5秒
 * 3.返回由Future接管，就可以查看异步执行状态
 *
 * 实现原理：
 * spring在扫描bean时检查方法上是否包含@Async注解
 * 包含的，会为这个类生产一个子类，执行方法时就不会调用父类
 * 而是将这次方法执行放到spring维护的一个队列，等待线程池去读取并执行
 * 注：因为子类继承父类时，如果有静态方法，会将方法隐藏而不是重写（两个没有关系的方法）（只有普通的方法调用可以是多态的-相对于static）
 * 所以异步调用方法不能时static修饰的
 *
 * @author lgh
 * @since 2017-12-21
 */
@Component
public class AsyncTask {

    @Async
    public Future<String> doTaskOne() throws Exception {
        System.out.println("开始做任务一");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成任务一，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务一完成");
    }

    @Async
    public Future<String> doTaskTwo() throws Exception {
        System.out.println("开始做任务二");
        long start = System.currentTimeMillis();
        Thread.sleep(5000);
        long end = System.currentTimeMillis();
        System.out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        return new AsyncResult<>("任务二完成");
    }
}
