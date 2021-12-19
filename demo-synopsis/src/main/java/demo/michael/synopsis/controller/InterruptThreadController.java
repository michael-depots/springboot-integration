package demo.michael.synopsis.controller;

import static java.lang.Thread.sleep;

/**
 * @className: InterruptThreadController
 * @description: 终止线程的执行
 * @author: michael
 * @date: 12/19/21
 **/
public class InterruptThreadController implements Runnable {

    private static int i = 0;
    private static int j = 0;

    /**
     * @description: 使用关键字volatile定义标志作为终止线程条件
     */
    private static volatile boolean FLAG = true;

    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + "正在执行");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 2s后终止线程的执行，stop()方法已废弃
        stopThreadExecute();

        // 模拟使用stop()方法存在线程不安全问题: 子线程执行时间 > 主线程执行时间，但主线程被强制终止，结果不符合预期
        imitateStopMethodIsUnsafe();

        // 模拟使用interrupt()结合isInterrupted()通过标记的方式终止线程
        imitateInterruptMethodStopThread();

        // 使用关键字volatile作为终止线程条件
        imitateStopThreadWithVolatileFlag();

    }

    /**
     * @description: 2s后使用stop()终止线程的执行
     * @return: void
     */
    public static void stopThreadExecute() throws InterruptedException {
        Thread thread = new Thread(new InterruptThreadController());
        thread.start();
        sleep(1000L);
        thread.stop();
    }

    /**
     * @description: 子线程执行时间 > 主线程执行时间，主线程被终止，结果不符合预期
     * @return: void
     */
    public static Thread stopThreadUnsafe() throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                i++;
                try {
                    sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                j++;
            }
        });

        return thread;
    }

    /**
     * @description: 主线程的执行时间是1s，子线程可能业务复杂，执行2s，但被强制终止，导致线程安全性问题
     * @return: void
     */
    public static void imitateStopMethodIsUnsafe() throws InterruptedException {
        Thread thread = stopThreadUnsafe();
        thread.start();
        sleep(1000L);
        thread.stop();

        System.out.println("i的值=" + i);
        System.out.println("j的值=" + j);
    }

    /**
     * @description: TODO
     * @return: void
     */
    public static void imitateInterruptMethodStopThread() throws InterruptedException {
        Thread thread = stopThreadWithInterrupt();
        thread.start();
        Thread.sleep(1000L);
        thread.interrupt();
    }

    /**
     * @description: 使用interrupt来终止线程
     * @return: java.lang.Thread
     */
    public static Thread stopThreadWithInterrupt() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                // 如果线程标记为已终止，则不进入方法
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        return thread;
    }

    /**
     * @description: 使用关键字volatile的标记作为终止线程条件
     * @return: void
     */
    public static void imitateStopThreadWithVolatileFlag() throws InterruptedException {
        Thread thread = stopThreadWithVolatileFlag();
        thread.start();
        Thread.sleep(1000L);
        FLAG = false;
    }

    /**
     * @description: 根据关键字判断线程是否终止
     * @return: java.lang.Thread
     */
    public static Thread stopThreadWithVolatileFlag() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (FLAG) {
                    System.out.println(Thread.currentThread().getName());
                }
            }
        });

        return thread;
    }
}
