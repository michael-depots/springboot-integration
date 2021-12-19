package demo.michael.synopsis.controller;

/**
 * @className: DaemonThreadController
 * @description: 守护线程是用户线程的守护着，当用户线程结束，守护线程也会终止
 * 场景: jvm垃圾清理线程
 * <p>
 * 建议:
 * 1. 尽量少使用守护线程，不可控
 * 2. 不要在守护线程里进行读写操作、执行计算逻辑
 * @author: michael
 * @date: 12/19/21
 **/
public class DaemonThreadController implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName());
        }
    }

    /**
     * @description: 守护线程随着主线程的结束而结束
     * @return: void
     */
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new DaemonThreadController());
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(2000L);
    }
}
