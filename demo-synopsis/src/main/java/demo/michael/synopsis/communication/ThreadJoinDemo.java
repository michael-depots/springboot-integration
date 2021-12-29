package demo.michael.synopsis.communication;

/**
 * @className: ThreadLocal
 * @description: 使用场景 -> 线程A执行到一半，需要一个数据，这个数据需要线程B去执行修改，只有B修改完成之后，A才能继续操作
 * @author: charles
 * @date: 12/29/21
 **/
public class ThreadJoinDemo {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 开始运行.");
            System.out.println(Thread.currentThread().getName() + " 运行结束.");
        }, "thread-1");

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + " 开始运行.");
            thread1.start();
            try {
                thread1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " 运行结束.");
        }, "thread-2").start();

    }
}
