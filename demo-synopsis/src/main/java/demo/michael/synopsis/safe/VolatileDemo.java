package demo.michael.synopsis.safe;

/**
 * @className: VolatileDemo
 * @description: volatile 关键字仅仅保证变量的可见行，并不保证原子性。
 * 使用场景:
 *  1. 作为线程开关
 *  2. 单例，修饰对象实例，禁止指令重排序
 * @author: michael
 * @date: 12/22/21
 **/
public class VolatileDemo implements Runnable {

    private static volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    /**
     * @description: 线程1读取之后，修改了变量的值，修改后的值对线程2来说是可见的
     * @return: void
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new VolatileDemo());
        thread.setName("volatile-thread-1");
        thread.start();
        Thread.sleep(2000L);
        flag = false;

        Thread thread2 = new Thread(new VolatileDemo());
        thread2.setName("volatile-thread-2");
        thread2.start();
    }
}
