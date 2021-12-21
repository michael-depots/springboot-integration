package demo.michael.schedule.demo;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @className: TimerTaskDemo
 * @description: 使用 Java 自带的 java.util.Timer 类，调度一个 java.util.TimerTaskDemo 任务
 * 存在问题:
 * 1. 任务执行时间长影响其他任务；
 * 2. 任务异常影响其他任务。
 * @author: charles
 * @date: 12/21/21
 **/
@Deprecated
public class TimerTaskDemo {

    public static void main(String[] args) {

        // 使用JDK自带的Timer类，调度TimerTask
        //timerTaskRun();

        // 问题一：任务执行时间长影响其他任务
        //timeTaskConflict();

        // 问题二: 任务异常影响其他任务
        timeTaskException();

    }

    /**
     * @description: Timer是JDK 自带的
     * @return: void
     */
    protected static void timerTaskRun() {
        // 定义一个任务
        java.util.TimerTask timerTask = new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("Run timerTask：" + new Date());
            }
        };
        // 计时器
        Timer timer = new Timer();
        // 添加执行任务（延迟 1s 执行，每 3s 执行一次）
        timer.schedule(timerTask, 1000, 3000);
    }

    /**
     * @description: 任务 1 运行时间超过设定的间隔时间时，任务 2 也会延迟执行
     * @return: void
     */
    protected static void timeTaskConflict() {

        // 定义任务 1
        java.util.TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("进入 timerTask 1：" + new Date());
                try {
                    // 休眠 5 秒
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Run timerTask 1：" + new Date());
            }
        };

        // 定义任务 2
        java.util.TimerTask timerTask2 = new java.util.TimerTask() {
            @Override
            public void run() {
                System.out.println("Run timerTask 2：" + new Date());
            }
        };

        // 计时器
        Timer timer = new Timer();
        // 添加执行任务（延迟 1s 执行，每 3s 执行一次）
        timer.schedule(timerTask, 1000, 3000);
        timer.schedule(timerTask2, 1000, 3000);
    }

    /**
     * @description: 当一个任务抛出异常，其他任务也会终止运行
     * @return: void
     */
    protected static void timeTaskException() {

        // 定义任务 1
        java.util.TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("进入 timerTask 1：" + new Date());
                // 模拟异常
                int num = 8 / 0;
                System.out.println("Run timerTask 1：" + new Date());
            }
        };

        // 定义任务 2
        java.util.TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Run timerTask 2：" + new Date());
            }
        };

        // 计时器
        Timer timer = new Timer();
        // 添加执行任务（延迟 1s 执行，每 3s 执行一次）
        timer.schedule(timerTask, 1000, 3000);
        timer.schedule(timerTask2, 1000, 3000);
    }
}
