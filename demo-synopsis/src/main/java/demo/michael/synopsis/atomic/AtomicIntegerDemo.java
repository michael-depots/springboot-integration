package demo.michael.synopsis.atomic;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: AtomicIntegerDemo
 * @description: AtomicInteger 使用
 * @author: charles
 * @date: 12/29/21
 **/
public class AtomicIntegerDemo {

    private static AtomicInteger sum = new AtomicInteger(0);

    public static void inCreate() {
        sum.incrementAndGet();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inCreate();
                    System.out.println(sum);
                    try {
                        Thread.sleep(200L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
