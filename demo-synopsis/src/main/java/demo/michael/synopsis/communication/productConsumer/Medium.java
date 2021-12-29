package demo.michael.synopsis.communication.productConsumer;

/**
 * @className: Medium
 * @description: 中间件，负责接受生产消息和获取消费消息
 * @author: charles
 * @date: 12/28/21
 **/
public class Medium {

    private int num = 0;
    private static final int TOTAL = 20;

    /**
     * @description: 接受生产数据
     * @return: void
     */
    public synchronized void put() {
        // 判断当前的库存，是否已经是最大的库存容量
        if (num < TOTAL) {
            // 如果不是，通知生产者继续生产
            System.out.println(Thread.currentThread().getName() + " 新增库存 ====> 当前库存" + ++num);
            notifyAll();
        } else {
            // 如果是，则通知生产者进行等待
            try {
                System.out.println(Thread.currentThread().getName() + " 库存已满" + num);
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @description: 获取消费数据
     * @return: void
     */
    public synchronized void take() {
        // 判断当前库存是否不足
        if (num > 0) {
            // 如果充足，在消费完成后通知生产者进行生产
            System.out.println(Thread.currentThread().getName() + " 消费库存 ======> 当前库存容量" + --num);
            notifyAll();
        } else {
            // 如果不足，通知消费者暂停消费
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
