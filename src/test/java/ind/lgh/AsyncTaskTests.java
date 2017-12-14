package ind.lgh;

import ind.lgh.anything.AsyncTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncTaskTests {

    @Autowired
    private AsyncTask task;

    @Test
    public void test() throws Exception {
        long start = System.currentTimeMillis();

        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();

        // future.get()方法会在主线程阻塞直到子任务执行完
        String result1 = task1.get();
        String result2 = task2.get();
        System.out.println(result1+"-"+result2);

        long end = System.currentTimeMillis();
        System.out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }
}
