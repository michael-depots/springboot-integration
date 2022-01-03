package demo.michael.synopsis.chapter8;

import java.util.concurrent.Exchanger;

/**
 * @className: ExchangerDemo
 * @description: Exchange 是成对的线程使用 exchange() 方法，当有一对线程达到了同步点，就会进行交换数据。因此该工具类的线程对象是成对存在的。
 * @author: charles
 * @date: 1/3/22
 **/
public class ExchangerDemo {

    public static void main(String[] args) {

        Exchanger<String> stringExchanger = new Exchanger<>();

        String str1 = "test1";
        String str2 = "test2";

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "初始值 ====== " + str1);
            try {
                String exchange = stringExchanger.exchange(str1);
                System.out.println(Thread.currentThread().getName() + "交换后的值 =====" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程1").start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "初始值 ====== " + str2);
            try {
                String exchange = stringExchanger.exchange(str2);
                System.out.println(Thread.currentThread().getName() + "交换后的值 =====" + exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "线程2").start();
    }

}
