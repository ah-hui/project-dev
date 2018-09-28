package ind.lgh.system.pool;

import ind.lgh.system.domain.JsonResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * 功能描述：可暂停可继续Java程序测试.
 * 暂停和继续所调用的方法已被废弃，有重大缺陷
 *
 * @author lgh
 * @since 2018-09-28
 */
@Path("/pauseResume")
@Service("pauseResumeTest")
@Transactional(rollbackFor = Exception.class)
public class PauseResumeTest {

    @Path("/start")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonResult start() {
        try {
            CustomThreadPoolExecutor exec = new CustomThreadPoolExecutor();
            exec.init();
            ExecutorService pool = exec.getCustomThreadPoolExecutor();
            pool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        long threadId = Thread.currentThread().getId();
                        for (int i = 0; i < 1000; i++) {
                            Thread.sleep(1000);
                            System.out.println("子线程[" + threadId + "] 正在执行...");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            JsonResult jr = JsonResult.createSuccess("启动成功！");
            jr.addData("去程序Console中看吧，除非使用【非匿名内部类】形式的Runnable，否则无法获取线程ID");
            return jr;
        } catch (Exception e) {
            e.printStackTrace();
            JsonResult jr = JsonResult.createFail("启动失败！未知异常");
            return jr;
        }
    }

    @Path("/pause/{threadId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonResult pause(@PathParam("threadId") long threadId) {
        try {
            //Give you set of Threads
            Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
            //Iterate over set to find yours
            for (Thread thread : setOfThread) {
                if (thread.getId() == threadId) {
                    thread.suspend();
                    break;
                }
            }
            JsonResult jr = JsonResult.createSuccess("暂停成功！");
            return jr;
        } catch (Exception e) {
            e.printStackTrace();
            JsonResult jr = JsonResult.createFail("暂停失败！");
            return jr;
        }
    }

    @Path("/resume/{threadId}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public JsonResult resume(@PathParam("threadId") long threadId) {
        try {
            //Give you set of Threads
            Set<Thread> setOfThread = Thread.getAllStackTraces().keySet();
            //Iterate over set to find yours
            for (Thread thread : setOfThread) {
                if (thread.getId() == threadId) {
                    thread.resume();
                    break;
                }
            }
            JsonResult jr = JsonResult.createSuccess("继续成功！");
            jr.addData(threadId);
            return jr;
        } catch (Exception e) {
            e.printStackTrace();
            JsonResult jr = JsonResult.createFail("继续失败！未知异常");
            return jr;
        }
    }
}
