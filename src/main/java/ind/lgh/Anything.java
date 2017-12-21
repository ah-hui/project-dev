package ind.lgh;


import java.util.concurrent.*;

/**
 * 在这里随便玩
 *
 * @author lgh
 * @since 2017-12-21
 */
public class Anything {

    public static void main(String[] args) {
        multiThreadTest();
    }

    /**
     * 多线程测试
     * 1.Future接口就是对于具体的Runnable或者Callable任务的执行结果进行取消、查询是否完成、获取结果。
     * 2.必要时可以通过get方法获取执行结果，该方法会阻塞直到任务返回结果。
     * 3.与FutureTask区别：后者是前者的唯一实现类
     */
    public static void multiThreadTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        MyTask task = new MyTask();
        Future<Integer> result = executorService.submit(task);
        executorService.shutdown();

        System.out.println("=== 主线程开始执行其他任务 ===");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("=== 主线程执行其他任务完毕 ===");

        try {
            System.out.println("=== task运行结果：" + result.get() + " ===");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("=== 所有任务执行完毕 ===");

    }
}

/**
 * 多线程任务类
 * <p>
 * 关于同级类：
 * 1.一个.java文件中可以有多个同级类
 * 2.其修饰符只可以public/abstract/final和无修饰符，不能是其他的protected/private等修饰符
 * 3.不使用任何修饰符声明的属性和方法，对同一个包内的类是可见的
 * 4.public修饰的只能有一个,且必须要与文件名相同
 * 5.编译时一个java文件会生成多个class文件
 */
class MyTask implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("=== 子线程执行 ===");
        Thread.sleep(3000);
        return 100;
    }
}
