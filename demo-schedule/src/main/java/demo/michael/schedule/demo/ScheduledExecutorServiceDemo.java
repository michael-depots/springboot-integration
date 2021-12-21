package demo.michael.schedule.demo;

import java.util.Date;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @className: ScheduledExecutorServiceDemo
 * @description: jdk 自带的一个类；是基于线程池设计的定时任务类,每个调度任务都会分配到线程池中的一个线程去执行。任务是并发执行，互不影响。
 * @author: charles
 * @date: 12/21/21
 **/
public class ScheduledExecutorServiceDemo {

    public static void main(String[] args) {

        // 使用 ScheduledExecutorService 执行定时任务
        ScheduledExecutorService();

        // 模拟任务超时执行测试
        imitateTaskTimeout();

        // 模拟任务异常执行测试
        imitateTaskException();

    }

    /**
     * @description: 使用 ScheduledExecutorService 执行定时任务
     * @return: void
     */
    protected static void ScheduledExecutorService() {

        // 创建任务队列，10 为线程数量
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);

        /**
         *  @param command the task to execute 任务体
         *  @param initialDelay the time to delay first execution 首次执行的延时时间
         *  @param period the period between successive executions 任务执行间隔
         *  @param unit the time unit of the initialDelay and period parameters 间隔时间单位
         */
        // 执行任务, 1s 后开始执行，每 3s 执行一次
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Run Schedule：" + new Date());
        }, 1, 3, TimeUnit.SECONDS);

    }

    /**
     * @description: 模拟一个任务执行时间过长，会不会对其他任务造成影响
     * @return: void
     */
    protected static void imitateTaskTimeout() {

        // 创建任务队列
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);

        // 执行任务 1，1s 后开始执行，每 3s 执行一次
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Enter Schedule：" + new Date());
            try {
                // 休眠 20 秒
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Run Schedule：" + new Date());
        }, 1, 3, TimeUnit.SECONDS);

        // 执行任务 2，1s 后开始执行，每 3s 执行一次
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Run Schedule2：" + new Date());
        }, 1, 3, TimeUnit.SECONDS);

    }

    /**
     * @description: 模拟在一个任务异常时，是否会对其他任务造成影响
     * @return: void
     */
    protected static void imitateTaskException() {

        // 创建任务队列
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(10);

        // 执行任务 1, 1s 后开始执行，每 3s 执行一次
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Enter Schedule：" + new Date());
            // 模拟异常
            int num = 8 / 0;
            System.out.println("Run Schedule：" + new Date());
        }, 1, 3, TimeUnit.SECONDS);

        // 执行任务 2, 1s 后开始执行，每 3s 执行一次
        executorService.scheduleAtFixedRate(() -> {
            System.out.println("Run Schedule2：" + new Date());
        }, 1, 3, TimeUnit.SECONDS);

    }
}
