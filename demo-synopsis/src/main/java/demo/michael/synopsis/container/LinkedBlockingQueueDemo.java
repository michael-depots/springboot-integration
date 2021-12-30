package demo.michael.synopsis.container;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @className: LinkedBlockingQueueDemo
 * @description: 在并发编程中，LinkedBlockingQueue 使用非常频繁。
 * 因为 put() 和 take() 会阻塞和等待，非常适用于做生产者消费者的中间商。
 * @author: charles
 * @date: 12/30/21
 **/
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<String> strings = new LinkedBlockingQueue<>(2);

        // 往队列里存元素
        strings.add("111");       // add() 实际调用的是 offer()，在队列满的时候，add会报异常
        strings.offer("222");  // 队列满了，则直接入队失败
        strings.put("333");    // 在队列满的时候，会进入阻塞状态

        // 从队列中取元素
        String remove = strings.remove(); // remove() 实际上调用的是 poll()，remove 会抛出异常
        String poll = strings.poll(); // poll() 在队列为空的时候直接返回空
        strings.take(); // 在队列为空的时候会进入等待的状态
        strings.take(); // 进入等待
    }
}
