package ind.lgh.system.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定时任务集合
 *
 * @author lgh
 */
@Component
public class ScheduledTasks {

    private static final SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 每五秒执行-输出当前时间
     * 等同于@Scheduled(fixedRate = 5000)
     * 第一次延迟设置@Scheduled(initialDelay=1000, fixedRate=5000)
     */
    @Scheduled(cron="*/5 * * * * *")
    public void reportCurrentTime(){
        System.out.println("现在时间："+dataFormat.format(new Date()));
    }

}
