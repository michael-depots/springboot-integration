package demo.michael.synopsis.controller;

/**
 * @className: ThreadPriorityController
 * @description: 线程优先级的执行，Thread类中的setPriority()方法配置线程的优先级
 * 注意: 1. 不要自定义设置优先级； 2. windows和linux平台优先级执行不同； 3. 不会完全按照优先级，只是大部分被优先执行
 * 场景:
 *  任务快速处理: 设置高的优先级
 *  任务慢慢处理: 设置低的优先级
 * @author: michael
 * @date: 12/19/21
 **/
public class ThreadPriorityController {

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
           while (true) {
               System.out.println(Thread.currentThread().getName());
           }
        }, "线程1");

        Thread thread2 = new Thread(() -> {
           while (true) {
               System.out.println(Thread.currentThread().getName());
           }
        }, "线程2");

        thread1.start();
        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.start();
        thread2.setPriority(Thread.MAX_PRIORITY);
    }
}
