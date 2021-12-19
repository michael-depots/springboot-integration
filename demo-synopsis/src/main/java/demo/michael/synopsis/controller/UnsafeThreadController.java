package demo.michael.synopsis.controller;

import java.util.concurrent.CountDownLatch;

/**
 * @className: UnsafeThreadController
 * @description: 线程不安全操作示例
 * 线程上线文切换，导致对数据的获取不太准确，因为计算值 <> 1000
 * @author: michael
 * @date: 12/19/21
 **/
public class UnsafeThreadController {

    private static int num = 0;

    private static CountDownLatch countDownLatch = new CountDownLatch(10);

    /**
     * @description: 每次调用对num进行++操作
     * @return: void
     */
    public static void inCreate() {
        num++;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    inCreate();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                // 每个线程执行完成后，调用countDownLatch
                countDownLatch.countDown();
            }).start();
        }

        while (true) {
            if (countDownLatch.getCount() == 0) {
                System.out.println(num);
                break;
            }
        }
        System.out.println("最终得到的num: " + num);
    }
}
