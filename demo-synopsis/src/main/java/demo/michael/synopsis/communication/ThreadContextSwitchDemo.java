package demo.michael.synopsis.communication;

/**
 * @className: ThreadContextSwitchDemo
 * @description: 在多线程环境下，如果一个线程的执行，依赖于另外一个线程的某种状态的改变
 * 问题: 线程的执行时间不可控，CPU分配时间片的时候会比较耗费资源
 * 解决: 参考 ThreadWaitAndNotifyDemo
 * @author: charles
 * @date: 12/28/21
 **/
public class ThreadContextSwitchDemo {

    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (!flag) {
                try {
                    Thread.sleep(10000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " flag is false...");
            }
            System.out.println(Thread.currentThread().getName() + " flag is true...");
        }).start();

        Thread.sleep(1000L);

        new Thread(() -> {
            flag = true;
            System.out.println(Thread.currentThread().getName() + " change flag status...");
        }).start();
    }
}
