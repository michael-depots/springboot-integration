package demo.michael.synopsis.lock.customer;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @className: CustomerLock
 * @description: 继承 Lock，实现自定义的锁
 * @author: michael
 * @date: 12/23/21
 **/
public class CustomerLock implements Lock {

    private boolean isHoldLock = false;

    private Thread holdLockThread = null;

    private int reentryCount = 0;

    /**
     * @description: 同一时刻，能且仅能有一个线程获取锁。其它线程，只能等待该线程释放锁之后才能获取锁
     * @return: void
     */
    @Override
    public synchronized void lock() {
        if (isHoldLock && Thread.currentThread() != holdLockThread) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        holdLockThread = Thread.currentThread();
        isHoldLock = true;
        reentryCount++;
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public synchronized void unlock() {
        // 判断当前线程是否是持有锁的线程，是，重入次数减去1，不是就不做处理
        if (Thread.currentThread() == holdLockThread) {
            reentryCount--;
            if (reentryCount == 0) {
                notify();
                isHoldLock = false;
            }
        }
    }

    @Override
    public Condition newCondition() {
        return null;
    }
}
