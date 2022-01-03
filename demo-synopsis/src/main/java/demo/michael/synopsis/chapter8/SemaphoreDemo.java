package demo.michael.synopsis.chapter8;

import java.util.concurrent.Semaphore;

/**
 * @className: SemaphoreDemo
 * @description: Semaphore 控制并发数量
 * 使用场景: 接口限流
 * @author: charles
 * @date: 1/3/22
 **/
public class SemaphoreDemo {

    public static void main(String[] args) {

        Semaphore semaphore = new Semaphore(8);

        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "开始执行！");
                    Thread.sleep(5000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            }).start();
        }
    }
}
