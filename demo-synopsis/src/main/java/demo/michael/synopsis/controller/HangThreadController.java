package demo.michael.synopsis.controller;

/**
 * @className: HangThreadController
 * @description: 挂起线程，让线程从RUNNABLE状态变为非RUNNABLE(BLOCKED、WAITING、WAITING_TIME)状态
 * 使用场景: 我等的船还不来，我等的人还不明白
 * @author: michael
 * @date: 12/19/21
 **/
public class HangThreadController implements Runnable {

    private static final Object obj = new Object();

    private static final Object waitObj = new Object();

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "执行run方法，准备调用suspend方法");
        Thread.currentThread().suspend();
        System.out.println(Thread.currentThread().getName() + "执行run方法，准备调用resume方法");
    }

    public static void main(String[] args) throws InterruptedException {
        // 使用suspend()和resume()模拟线程挂起和释放
        System.out.println("======== Thread-0: 使用suspend()和resume()模拟线程挂起和释放 ========");
        suspendAndResumeTest();

        // 模拟使用suspend()和resume()造成死锁，这两个方法已废弃
        System.out.println("======== Thread-1/2:模拟使用suspend()和resume()造成死锁，Thread-2未释放资源 ========");
        imitateThreadDeadLock();

        // 模拟使用wait()和notify()挂起线程并释放资源，推荐使用，notify()随即唤醒一个在等所的资源
        System.out.println("======== 模拟使用wait()和notify()挂起线程并释放资源，推荐使用 =======");
        imitateWaitAndNoticeThread();
    }

    /**
     * @description: 通过继承创建线程实现线程挂起和恢复
     * suspend()和resume()方法已废弃，原因：改方法不会释放线程所占用的资源，会引发线程安全问题，造成死锁。
     * @return: void
     */
    public static void suspendAndResumeTest() throws InterruptedException {
        Thread thread = new Thread(new HangThreadController());
        thread.start();

        // 线程挂起，3s后恢复线程
        Thread.sleep(3000L);
        thread.resume();
    }

    /**
     * @description: 模拟使用suspend因不释放资源
     * 在并发编程中，线程状态很难控制，引发线程安全造成死锁
     * @return: void
     */
    public static void imitateThreadDeadLock() throws InterruptedException {
        Thread thread1 = suspendCauseDeadLock();
        thread1.start();
        Thread.sleep(1000L);
        thread1.resume();

        // 线程2不加睡眠时间，线程进入RUNNABLE状态，分析: start() => resume() => suspend(), 线程进入挂起(RUNNABLE )，不会被唤醒
        Thread thread2 = suspendCauseDeadLock();
        thread2.start();
        thread2.resume();
    }

    /**
     * @description: 通过匿名内部类的方式创建线程，suspend()挂起资源，但不会释放
     * @return: void
     */
    public static Thread suspendCauseDeadLock() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + "占用资源");
                    Thread.currentThread().suspend();
                }
                System.out.println(Thread.currentThread().getName() + "释放资源");
            }
        });

        return thread;
    }

    /**
     * @description: 模拟使用wait()和和notify()来挂起线程并释放锁资源
     * 使用wait()和notify()必须对资源加锁，锁住的是调用wait()方法的资源
     * @return: void
     */
    public static void imitateWaitAndNoticeThread() throws InterruptedException {
        Thread thread3 = waitMethodMakeThreadHang();
        thread3.start();
        Thread.sleep(3000L);
        synchronized (waitObj) {
            // 释放被锁住的的资源
            waitObj.notify();
        }

        Thread thread4 = waitMethodMakeThreadHang();
        thread4.start();
        synchronized (waitObj) {
            // 释放被锁住的的资源
            waitObj.notify();
        }
    }

    /**
     * @description: 使用wait()方法来挂起线程
     * @return: java.lang.Thread
     */
    public static Thread waitMethodMakeThreadHang() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (waitObj) {
                    System.out.println(Thread.currentThread().getName() + "挂起线程");
                    try {
                        waitObj.wait(3000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(Thread.currentThread().getName() + "释放资源");
            }
        });

        return thread;
    }
}
