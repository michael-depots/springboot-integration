package demo.michael.synopsis.communication;

/**
 * @className: ThreadWaitAndNotifyDemo
 * @description: 使用 wait 和 notify 或 notifyAll 控制线程的执行顺序
 * wait 和 sleep 的区别:
 *  wait 会通过 notify 释放所有的锁，而 sleep 不会，sleep 只是让线程在指定的时间内，不去抢占 cpu 的资源。
 * 注意点:
 *  wait notify 必须放在同步代码块中，且必须拥有当前对象的锁，即不能取得A对象的锁，而调用B对象的wait。
 * 哪个对象 wait，就要调用哪个对象的 notify
 * @author: charles
 * @date: 12/28/21
 **/
public class ThreadWaitAndNotifyDemo {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        Object object = new Object();

        new Thread(() -> {
            synchronized (object) {
                while (!flag) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " flag is false...");
                }
            }
            System.out.println(Thread.currentThread().getName() + " flag is true ...");
        }).start();

        Thread.sleep(1000L);

        new Thread(() -> {
            synchronized (object) {
                flag = true;
                object.notify();
                System.out.println(Thread.currentThread().getName() + " change flag status...");
            }
        }).start();
    }
}
