package demo.michael.synopsis.productConsumer;

/**
 * @className: Main
 * @description: Producer-consumer 生产者消费者模型
 * @author: charles
 * @date: 12/28/21
 **/
public class Main {

    public static void main(String[] args) {

        Medium medium = new Medium();

        new Thread(new Consumer(medium)).start();
        new Thread(new Consumer(medium)).start();
        new Thread(new Consumer(medium)).start();


        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();
        new Thread(new Producer(medium)).start();

    }
}
