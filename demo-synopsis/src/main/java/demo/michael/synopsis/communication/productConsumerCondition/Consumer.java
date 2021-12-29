package demo.michael.synopsis.communication.productConsumerCondition;

/**
 * @className: Consumer
 * @description: 模拟消费者进行消费
 * @author: charles
 * @date: 12/28/21
 **/
public class Consumer implements Runnable {

    private Medium medium;

    public Consumer(Medium medium) {
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true) {
            medium.take();
        }
    }
}
