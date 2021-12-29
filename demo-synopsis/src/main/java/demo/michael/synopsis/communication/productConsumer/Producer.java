package demo.michael.synopsis.communication.productConsumer;

/**
 * @className: Producer
 * @description: 模拟生产者生产消息
 * @author: charles
 * @date: 12/28/21
 **/
public class Producer implements Runnable {

    private Medium medium;

    public Producer(Medium medium) {
        this.medium = medium;
    }

    @Override
    public void run() {
        while (true) {
            medium.put();
        }
    }
}
