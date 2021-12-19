package demo.michael.synopsis.controller;

import java.util.concurrent.*;

/**
 * @className: CreateThreadController
 * @description: 1. 可以继承Thread; 2. 可以实现Runnable接口。
 * 推荐使用2，可以增加程序的健壮性、代码可以共享、代码与数据独立。
 * @author: michael
 * @date: 12/19/21
 **/
public class CreateThreadController implements Runnable {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // 方式一: 通过继承Runnable接口实现run方法创建线程
        createThread();

        // 方式二: 通过匿名内部类的方式创建线程，
        anonymousInnerClass();

        // 方式三: 通过Lambda表达式创建线程
        lambdaCreateThread();

        // 方式四: 通过线程池的方式创建线程
        threadPoolExecutor();
    }

    /**
     * @description: 通过继承Runnable接口实现run方法创建线程
     * @return: void
     */
    public static void createThread() {
        Thread thread = new Thread(new CreateThreadController());
        thread.setName("demo1");
        // 输出主线程的名字
        thread.run();
        // 输出当前线程的名字
        thread.start();
    }

    /**
     * @description: 通过匿名内部类的方式创建线程
     * @return: void
     */
    public static void anonymousInnerClass() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("demo2");
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread.start();
    }

    /**
     * @description: 通过Lambda表达式创建线程
     * @return: void
     */
    public static void lambdaCreateThread() {
        new Thread(() -> {
            Thread.currentThread().setName("demo3");
            System.out.println(Thread.currentThread().getName());
        }).start();
    }

    /**
     * @description: 通过线程池的方式创建线程
     * @return: void
     */
    public static void threadPoolExecutor() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> {
            System.out.println(Thread.currentThread().getName());
        });
    }
}
