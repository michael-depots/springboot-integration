package demo.michael.schedule.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @className: ScheduledTaskDemo
 * @description: 使用 @Schedule 实现定时任务，三个定时任务都在同一个线程中执行
 * 存在问题: 当定时任务增多，如果一个任务卡死，会导致其他任务也无法执行
 * 推荐做法: 添加配置类 AsyncScheduledConfig，然后在定时任务的类或者方法上添加 @Async 注解，表示是异步事件的定时任务
 * @author: michael
 * @date: 12/21/21
 **/
@Component
@Async
public class ScheduledTaskDemo {

    private final static Logger logger = LoggerFactory.getLogger(ScheduledTaskDemo.class);

    /**
     * @description: 使用 fixedRate---任务执行时间
     * @return: void
     */
    @Scheduled(fixedRate = 5000)
    public void scheduledTask() {
        logger.info("fixedRate---任务执行时间：{}  线程名称：{}", LocalDateTime.now(), Thread.currentThread().getName());
    }

    /**
     * @description: 使用 fixedDelay---任务执行时间
     * @return: void
     */
    @Scheduled(fixedDelay = 5000)
    public void scheduledTask2() {
        logger.info("fixedDelay---任务执行时间：{}  线程名称：{}", LocalDateTime.now(), Thread.currentThread().getName());
    }

    /**
     * @description: 使用 cron---任务执行时间
     * @return: void
     */
    @Scheduled(cron = "0/5 * * * * *")
    public void scheduledTask3() {
        logger.info("cron---任务执行时间：{}  线程名称：{}", LocalDateTime.now(), Thread.currentThread().getName());
    }

}
