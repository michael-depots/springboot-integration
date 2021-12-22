package demo.michael.synopsis.safe;

/**
 * @className: SynchronizedDemo
 * @description: synchronized 示例
 * 1. 修饰普通方法，锁住对象的实例
 * 2. 修饰静态方法，锁住整个类
 * 3. 修饰代码块，锁住一个对象， synchronized (look)，即括号里面的内容
 * @author: michael
 * @date: 12/22/21
 **/
public class SynchronizedDemo {

    public static void main(String[] args) {

        // synchronized 修饰普通方法
        outTest();

        // synchronized 修饰静态方法
        staticOutTest();

        // synchronized 锁住代码块
        codeBlockTest();
    }

    /**
     * @description: 测试使用 synchronized 修饰普通方法
     * @return: void
     */
    public static void outTest() {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();
        SynchronizedDemo synchronizedDemo1 = new SynchronizedDemo();

        new Thread(() -> {
            try {
                synchronizedDemo.out();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                synchronizedDemo1.out();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * @description: 修饰普通方法，锁住对象的示例，不会锁住类
     * @return: void
     */
    public synchronized void out() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000L);
    }

    /**
     * @description: 测试使用 synchronized 修饰静态方法
     * @return: void
     */
    public static void staticOutTest() {

        new Thread(() -> {
            try {
                staticOut();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                staticOut();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * @description: 修饰静态方法，锁住整个类，其它只能等待，是一个危险操作
     * @return: void
     */
    public static synchronized void staticOut() throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
        Thread.sleep(5000L);
    }

    /**
     * @description: 测试使用 synchronized 锁住代码块
     * @return: void
     */
    public static void codeBlockTest() {
        SynchronizedDemo synchronizedDemo = new SynchronizedDemo();

        new Thread(() -> {
            try {
                synchronizedDemo.codeBlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                synchronizedDemo.codeBlock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private Object look = new Object();

    /**
     * @description: 使用 synchronized 修饰代码块，锁住一个对象
     * @return: void
     */
    public void codeBlock() throws InterruptedException {
        synchronized (look) {
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(5000L);
        }
    }
}
