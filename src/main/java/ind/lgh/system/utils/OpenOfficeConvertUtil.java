package ind.lgh.system.utils;

import com.google.common.util.concurrent.*;
import ind.lgh.system.pool.CustomThreadPoolExecutor;
import org.artofsolving.jodconverter.OfficeDocumentConverter;
import org.artofsolving.jodconverter.office.DefaultOfficeManagerConfiguration;
import org.artofsolving.jodconverter.office.OfficeManager;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * OpenOffice转换功能
 * 并发下三种实现：Runnable、Callable、ListenableFuture
 *
 * @author lgh
 */
public class OpenOfficeConvertUtil {

    /**
     * args
     */
    private static OfficeManager officeManager;
    private static String OFFICE_HOME = "D:/OpenOffice 4/";
    private static int port[] = {8100};

    public void convert2PDF(String inputFile, String outputFile) throws FileNotFoundException {
        startService();
        long totalsStartTime = System.currentTimeMillis();
        System.out.println("进行文档转换转换:" + inputFile + " --> " + outputFile);
        OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
        converter.convert(new File(inputFile), new File(outputFile));
        long totalEndTime = System.currentTimeMillis();
        System.out.println("===转换耗时：" + (totalEndTime - totalsStartTime) / 1000 + "s");
        stopService();
        System.out.println();
    }

    /**
     * Runnable实现并发下的转换
     *
     * @param inputFiles
     * @param outputFiles
     * @throws Exception
     */
    public void convert2PDF1(List<String> inputFiles, List<String> outputFiles) throws Exception {
        startService();
        if (inputFiles == null || outputFiles == null || inputFiles.size() != outputFiles.size()) {
            throw new Exception("什么玩意儿，参数别瞎比传行吗");
        }
        // 自定义线程池
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        exec.init();
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        for (int i = 0; i < inputFiles.size(); i++) {
            String inputFile = inputFiles.get(i);
            String outputFile = outputFiles.get(i);
            System.out.println("进行文档转换转换:" + inputFile + " --> " + outputFile);
            // 第一种办法：Runnable
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(">>>task is running=====" + Thread.currentThread().getId());
                    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
                    converter.convert(new File(inputFile), new File(outputFile));
                }
            });
        }
        // 必须阻塞，等待子线程执行完成
        // 第一种办法：Runnable只能预估时长，sleep等待子线程
        TimeUnit.SECONDS.sleep(60);
        exec.destroy();
        stopService();
        System.out.println();
    }

    /**
     * Callable实现并发下的转换
     *
     * @param inputFiles
     * @param outputFiles
     * @throws Exception
     */
    public void convert2PDF2(List<String> inputFiles, List<String> outputFiles) throws Exception {
        startService();
        if (inputFiles == null || outputFiles == null || inputFiles.size() != outputFiles.size()) {
            throw new Exception("什么玩意儿，参数别瞎比传行吗");
        }
        // 自定义线程池
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        exec.init();
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        // 第二种办法变量：Future列表
        List<Future<Boolean>> tasks = new ArrayList<>();
        for (int i = 0; i < inputFiles.size(); i++) {
            String inputFile = inputFiles.get(i);
            String outputFile = outputFiles.get(i);
            System.out.println("进行文档转换转换:" + inputFile + " --> " + outputFile);
            // 第二种办法：Callable
            Future<Boolean> submit = pool.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println(">>>task is running=====" + Thread.currentThread().getId());
                    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
                    converter.convert(new File(inputFile), new File(outputFile));
                    return true;
                }
            });
            tasks.add(submit);
        }
        // 必须阻塞，等待子线程执行完成
        // 第二种办法：Callable，get方法时阻塞的，依次获取可能第一个非常慢，效率低。
        // 缺点：获取批量任务结果很蛋疼
        for (Future<Boolean> future : tasks) {
            future.get();
        }
        exec.destroy();
        stopService();
        System.out.println();
    }

    /**
     * ListenableFuture实现并发下的转换
     *
     * @param inputFiles
     * @param outputFiles
     * @throws Exception
     */
    public void convert2PDF3(List<String> inputFiles, List<String> outputFiles) throws Exception {
        startService();
        if (inputFiles == null || outputFiles == null || inputFiles.size() != outputFiles.size()) {
            throw new Exception("什么玩意儿，参数别瞎比传行吗");
        }
        // 自定义线程池
        CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
        exec.init();
        ExecutorService pool = exec.getCustomThreadPoolExecutor();
        // 第三种办法变量：
        ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);
        for (int i = 0; i < inputFiles.size(); i++) {
            String inputFile = inputFiles.get(i);
            String outputFile = outputFiles.get(i);
            System.out.println("进行文档转换转换:" + inputFile + " --> " + outputFile);
            // 第三种办法：Guava的ListenableFuture
            ListenableFuture<Boolean> task = service.submit(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    System.out.println(">>>task is running=====" + Thread.currentThread().getId());
                    OfficeDocumentConverter converter = new OfficeDocumentConverter(officeManager);
                    converter.convert(new File(inputFile), new File(outputFile));
                    return true;
                }
            });
            Futures.addCallback(task, new FutureCallback<Boolean>() {
                @Override
                public void onSuccess(@Nullable Boolean aBoolean) {
                    System.out.println("========执行成功：" + aBoolean);
                }

                @Override
                public void onFailure(Throwable throwable) {
                }
            });
        }
        // 必须阻塞，等待子线程执行完成
        // 第三种办法：ListenableFuture异步非阻塞，回调方式获取执行结果而不需要轮询任务状态
        // 如何shutdown参考：https://www.programcreek.com/java-api-examples/?class=com.google.common.util.concurrent.ListeningExecutorService&method=shutdown
        // 这里采用awaitTermination，该方法设置一个超时时间，满足三个条件之一，直接执行shutdown：
        // 1.执行超过超时时间；2.所有任务完成；3.当前线程被interrupted
        service.shutdown(); // 第三种办法要shutdown service，而不是被装饰的pool了哟
        service.awaitTermination(3, TimeUnit.MINUTES);
        // 别忘记销毁自定义线程池，否则一直占用主线程不停（第一、第二种办法使用）
        stopService();
        System.out.println();
    }

    /**
     * 启动服务
     */
    public static void startService() {
        DefaultOfficeManagerConfiguration configuration = new DefaultOfficeManagerConfiguration();
        try {
            System.out.println("准备启动服务....");
            // 设置OpenOffice.org安装目录
            configuration.setOfficeHome(OFFICE_HOME);
            // 设置转换端口，默认为8100
            configuration.setPortNumbers(port);
            // 设置任务执行超时为5分钟
            configuration.setTaskExecutionTimeout(1000 * 60 * 5L);
            // 设置任务队列超时为24小时
            configuration.setTaskQueueTimeout(1000 * 60 * 60 * 24L);
            officeManager = configuration.buildOfficeManager();
            officeManager.start(); // 启动服务
            System.out.println("office转换服务启动成功!");
        } catch (Exception ce) {
            System.out.println("office转换服务启动失败!详细信息:" + ce);
        }
    }

    /**
     * 停止服务
     */
    public static void stopService() {
        System.out.println("关闭office转换服务....");
        if (officeManager != null) {
            officeManager.stop();
        }
        System.out.println("关闭office转换成功!");
    }

    /**
     * main函数测试并发下转换
     * 测试方式：对大文件进行转换，耗时较长，可以确保并发执行
     * 测试结果：openoffice并发下并不会转换失败，但是实际上会排队转换，如果转一个耗时30秒，
     * 那么转3个耗时为30*3秒。另外要注意的是，开子线程(Runnable)转换时，主线程必须等待，否则无法成功转换
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        long totalsStartTime = System.currentTimeMillis();
        String path = "C:/Users/BJQT/Desktop/威宁相关文档/";
        OpenOfficeConvertUtil opc = new OpenOfficeConvertUtil();
        /** opc.convert2PDF(path + "1.xlsx", path + "1.pdf");*/
        List<String> inputFiles = new ArrayList<>();
        List<String> outputFiles = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            inputFiles.add(path + i + ".xlsx");
            outputFiles.add(path + i + ".pdf");
        }
        opc.convert2PDF3(inputFiles, outputFiles);
        long totalEndTime = System.currentTimeMillis();
        System.out.println("===##共计转换耗时：" + (totalEndTime - totalsStartTime) / 1000 + "s");
    }

}
