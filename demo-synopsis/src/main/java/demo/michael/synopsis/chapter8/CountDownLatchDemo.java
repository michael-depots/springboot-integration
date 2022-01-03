package demo.michael.synopsis.chapter8;

import java.util.concurrent.CountDownLatch;

/**
 * @className: CountDownLatchDemo
 * @description:  CountDownLatch 一般用于某个线程等待若干个其它线程执行完成之后，它才执行；不可以重复使用
 * @author: charles
 * @date: 1/3/22
 **/
public class CountDownLatchDemo {

    public static void main(String[] args) {

        // 有 8 个运动员参加比赛
        CountDownLatch countDownLatch = new CountDownLatch(8);

        // 有一个赛跑过程，然后等待
        new Thread(() -> {
            try {
                // 进入等待的状态
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("800 米比赛结束，准备清空跑道，开始跨栏比赛！！！");
        }).start();

        // 在每个运动员到达终点后都会减 1
        for (int i = 0; i < 8; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(finalI * 1000L);
                    System.out.println(Thread.currentThread().getName() + "到达终点！！！");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 计数器减一
                    countDownLatch.countDown();
                }
            }).start();
        }
    }

}
