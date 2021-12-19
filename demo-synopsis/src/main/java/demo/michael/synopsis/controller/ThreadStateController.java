package demo.michael.synopsis.controller;

import java.io.IOException;

/**
 * @className: ThreadStateController
 * @description: 线程的状态及相互转换，使用JDK自带的jconsole查看
 * @author: michael
 * @date: 12/19/21
 **/
public class ThreadStateController {

    public static void main(String[] args) throws InterruptedException {
        // RUNNABLE STATE
        runnableState();

        // TIMED_WAITING STATE
        timedWaitingState();

        // WAITING STATE
        waitingState();
    }

    /**
     * @description: 线程RUNNABLE状态模拟
     * @return: void
     */
    public static void runnableState() {
        Thread thread = new Thread(() -> {
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        thread.start();
    }

    /**
     * @description: 线程TIMED_WAITING状态模拟
     * @return: void
     */
    public static void timedWaitingState() throws InterruptedException {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        Thread.sleep(2000L);

        // 调用blockedState方法，模拟线程BLOCKED状态
        blockedState(obj);
    }

    /**
     * @description: 线程BLOCKED模拟
     * @return: void
     */
    public static void blockedState(Object obj) {
        Thread thread2 = new Thread(() -> {
            synchronized (obj) {
            }
        });
        thread2.start();
    }

    /**
     * @description: 线程WAITING模拟
     * @return:
     */
    public static void waitingState() {
        Object obj = new Object();
        Thread thread = new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
