package demo.michael.synopsis.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @className: ReentrantReadWriteLockDemo
 * @description: ReentrantReadWriteLock
 * 特性: 写写互斥、读写互斥、读读共享
 * 降级锁: 写线程获取写入锁后可以获取读取锁，然后释放写入锁，这样就从写入锁变成了读取锁，从而实现了锁降级的特性。
 * @author: michael
 * @date: 12/25/21
 **/
public class ReentrantReadWriteLockDemo {

    private int i = 0;
    private int j = 0;

    private ReadWriteLock reentrantLock = new ReentrantReadWriteLock();
    Lock readLock = reentrantLock.readLock();
    Lock writeLock = reentrantLock.writeLock();

    /**
     * @description: 线程写入操作
     * @return: void
     */
    public void inCreate() {
        writeLock.lock();
        try {
            i++;
            Thread.sleep(500L);
            j++;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * @description: 线程读取操作
     * @return: void
     */
    public void out() {
        readLock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "-> i的值=" + i + ", j的值=" + j);
        } finally {
            readLock.unlock();
        }
    }

    public static void main(String[] args) {

        // 实例化 ReentrantReadWriteLockDemo
        ReentrantReadWriteLockDemo reentrantReadWriteLockDemo = new ReentrantReadWriteLockDemo();

        // 模拟多线程写写互斥
        doubleWriteMutualExclusion(reentrantReadWriteLockDemo);

        // 模拟多线程读写互斥
        readAndWriteMutualExclusion(reentrantReadWriteLockDemo);

        // 模拟多线程读读共享
        doubleReadMutualShare(reentrantReadWriteLockDemo);
    }

    /**
     * @description: 写写互斥，第一个线程 RUNNING，其它线程 WAIT
     * @return: void
     */
    public static void doubleWriteMutualExclusion(ReentrantReadWriteLockDemo reentrantReadWriteLockDemo) {
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                reentrantReadWriteLockDemo.inCreate();
                reentrantReadWriteLockDemo.out();
            }).start();
        }
    }

    /**
     * @description: 读写互斥
     * @return: void
     */
    public static void readAndWriteMutualExclusion(ReentrantReadWriteLockDemo reentrantReadWriteLockDemo) {
        new Thread(() -> {
            reentrantReadWriteLockDemo.inCreate();
        }, "write-thread").start();

        new Thread(() -> {
            reentrantReadWriteLockDemo.out();
        }, "read-thread").start();
    }

    /**
     * @description: 读读共享
     * @return: void
     */
    public static void doubleReadMutualShare(ReentrantReadWriteLockDemo reentrantReadWriteLockDemo) {
        new Thread(() -> {
            reentrantReadWriteLockDemo.out();
        }, "read-thread-0").start();

        new Thread(() -> {
            reentrantReadWriteLockDemo.out();
        }, "read-thread-1").start();
    }
}
