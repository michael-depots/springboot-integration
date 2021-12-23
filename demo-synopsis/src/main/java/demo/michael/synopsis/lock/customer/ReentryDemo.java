package demo.michael.synopsis.lock.customer;

import java.util.concurrent.locks.Lock;

/**
 * @className: ReentryDemo
 * @description: 进入方法A，然后进入方法B
 * @author: michael
 * @date: 12/23/21
 **/
public class ReentryDemo {

    public Lock lock = new CustomerLock();

    public void methodA() {
        lock.lock();
        try {
            System.out.println("进入方法A");
            methodB();
        } finally {
            lock.unlock();
        }
    }

    public void methodB() {
        lock.lock();
        try {
            System.out.println("进入方法B");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentryDemo reentryDemo = new ReentryDemo();
        reentryDemo.methodA();
    }
}
