package demo.michael.synopsis.communication.productConsumerCondition;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: Medium
 * @description: 中间件，负责接受生产消息和获取消费消息
 * @author: charles
 * @date: 12/28/21
 **/
public class Medium {

    private int num = 0;
    private static final int TOTAL = 20;

    private Lock lock = new ReentrantLock();
    private Condition consumerCondition = lock.newCondition();
    private Condition producerCondition = lock.newCondition();

    /**
     * @description: 接受生产数据
     * @return: void
     */
    public void put() {
        lock.lock();
        try {
            // 判断当前的库存，是否已经是最大的库存容量
            if (num < TOTAL) {
                // 如果不是，通知生产者继续生产
                System.out.println(Thread.currentThread().getName() + " 新增库存 ====> 当前库存" + ++num);
                // 生产完成后，通知消费者进行消费
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                consumerCondition.signalAll();
            } else {
                // 如果是，则通知生产者进行等待
                try {
                    System.out.println(Thread.currentThread().getName() + " 库存已满" + num);
                    producerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    /**
     * @description: 获取消费数据
     * @return: void
     */
    public void take() {
        lock.lock();
        try {
            // 判断当前库存是否不足
            if (num > 0) {
                // 如果充足，在消费完成后通知生产者进行生产
                System.out.println(Thread.currentThread().getName() + " 消费库存 ======> 当前库存容量" + --num);
                try {
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                producerCondition.signalAll();
            } else {
                // 如果不足，通知消费者暂停消费
                try {
                    System.out.println(Thread.currentThread().getName() + " 消费库存 =====> 库存不足" + num);
                    consumerCondition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            lock.unlock();
        }
    }
}
