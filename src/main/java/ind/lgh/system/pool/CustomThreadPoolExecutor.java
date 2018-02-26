package ind.lgh.system.pool;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 功能描述：自定义线程池.
 *
 * @author lgh
 * @since 2018-02-26
 */
public class CustomThreadPoolExecutor {

    private ThreadPoolExecutor pool = null;

    public ExecutorService getCustomThreadPoolExecutor() {
        return this.pool;
    }

    /**
     * 线程池初始化方法.
     * <p>
     * corePoolSize 核心线程池大小----10 - 看任务量，保留几个核心线程数 - 线程池的基本大小（if 提交新任务&&任务数<corePoolSize 时，不管是否有空闲线程，都创建线程直到达到基本大小；也可以通过调用prestartAllCoreThreads线程池会提前创建并启动所有基本线程）
     * maximumPoolSize 最大线程池大小----30 - 最大线程数一般设为2N+1最好，N是CPU核数
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * TimeUnit keepAliveTime时间单位----TimeUnit.MINUTES
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maximumPoolSize+workQueue之和时,
     * 即当提交第41个任务时(前面线程都没有执行完,此测试方法中用sleep(100)),
     * 任务会交给RejectedExecutionHandler来处理
     * <p>
     * TODO: 获取执行结果？
     * 如果要获取任务执行结果，用CompletionService，但是注意，获取任务的结果的要重新开一个线程获取，
     * 如果在主线程获取，就要等任务都提交后才获取，就会阻塞大量任务结果，队列过大OOM，所以最好异步开个线程获取结果
     */
    public void init() {
        pool = new ThreadPoolExecutor(
                10,
                30,
                30,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<Runnable>(10),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler()
        );
    }

    /**
     * 池销毁
     */
    public void destroy() {
        if (pool != null) {
            pool.shutdownNow();
        }
    }

    /**
     * 自定义线程工厂类
     */
    private class CustomThreadFactory implements ThreadFactory {

        /**
         * 线程安全的Integer count
         * AtomicInteger是使用非阻塞算法实现并发控制，在一些高并发程序中非常适合
         */
        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = CustomThreadPoolExecutor.class.getSimpleName() + count.addAndGet(1);
            System.out.println(threadName);
            t.setName(threadName);
            return t;
        }
    }

    /**
     * 饱和策略 - 当提交任务数超过maximumPoolSize与workQueue之和时，执行以下处理方法
     * 多线程之BlockingQueue中的take/offer/put/add
     * BlockingQueue对于不能立即满足但可能在将来某一时刻可以满足的操作，以四种不同方式处理
     * 1.抛出一个异常
     * 2.返回一个特殊值（null或false）
     * 3.在操作可以成功前，无限期阻塞当前线程
     * 4.在放弃前，只在指定的时间限制内阻塞
     * 如下表：
     * -----------------------------------------------------------
     * |      | 抛出异常 | 返回特定值 | 阻塞   | 超时              |
     * | 插入 | add(e)   | offer(e)  | put(e) | off(e,time,unit) |
     * | 移除 | remove() | poll()    | take() | poll(time,unit)  |
     * | 检查 | element()| peek()    | 无     |  无              |
     * -----------------------------------------------------------
     *
     * @see java.util.concurrent.ThreadPoolExecutor line 1369
     * 如果任务提交超量，workQueue再继续添加元素（使用offer）会返回false，就会进入本handler，
     * 我们要做的事情就是，用put()方法排队等待插入，这将会阻塞当前线程直到插入成功。
     * offer: 将指定元素插入此队列中（如果立即可行且不会违反容量限制），成功时返回 true，如果当前没有可用的空间，则返回 false，不会抛异常
     * put: 将指定元素插入此队列中，将等待可用的空间.通俗点说就是>maxSize 时候，阻塞，直到能够有空间插入元素
     */
    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                // 由blockingQueue的offer改成put阻塞方法
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 测试线程池 main()
     */
    public static void main(String[] args) {
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        exec.init();
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        Integer times = 51;
        for (int i = 1; i < times; i++) {
            System.out.println("提交第" + i + "个任务！");
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(">>>task is running=====");
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        // 销毁--此处不能销毁,因为任务没有提交执行完,如果销毁线程池,任务也就无法执行了
        // exec.destroy();
    }

}
