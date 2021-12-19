package demo.michael.synopsis.controller;

/**
 * @className: DeadLockController
 * @description: 模拟线程相互请求资源造成死锁
 * @author: michael
 * @date: 12/19/21
 **/
public class DeadLockController {

    private static final Object HAIR_A = new Object();
    private static final Object HAIR_B = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (HAIR_A) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (HAIR_B) {
                    System.out.println("A成功的抓住了B的头发");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (HAIR_B) {
                synchronized (HAIR_A) {
                    System.out.println("B成功的抓住了A的头发");
                }
            }
        }).start();
    }
}
