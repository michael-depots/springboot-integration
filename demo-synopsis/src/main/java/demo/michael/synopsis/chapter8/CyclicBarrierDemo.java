package demo.michael.synopsis.chapter8;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @className: CyclicBarrierDemo
 * @description: CountDownLatch 允许一组线程相互等待到达一个公共的障碍点，之后再继续执行。一般用于某个线程等待若干个其它线程执行完成之后，它才执行；不可以重复使用
 * @author: charles
 * @date: 1/3/22
 **/
public class CyclicBarrierDemo {

    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

        for (int i = 0; i < 8; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(finalI * 1000L);
                    System.out.println(Thread.currentThread().getName() + "准备就绪!");
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }

                System.out.println("开始比赛");

            }).start();
        }
    }

}
