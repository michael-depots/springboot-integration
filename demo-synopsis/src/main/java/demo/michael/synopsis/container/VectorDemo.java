package demo.michael.synopsis.container;

import java.util.Iterator;
import java.util.Vector;

/**
 * @className: VectorDemo
 * @description: 同步容器
 * 缺点: 在单独使用里面的方法的时候，可以保证线程安全，但是在多线程操作需要额外加锁来保证线程安全。
 * @author: charles
 * @date: 12/30/21
 **/
public class VectorDemo {

    public static void main(String[] args) {

        // 在并发容器中添加元素
        Vector<String> stringVector = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            stringVector.add("demo" + i);
        }

        // 错误遍历
//        stringVector.forEach(e -> {
//            if (e.equals("demo5")) {
//                stringVector.remove(e);
//            }
//            System.out.println(e);
//        });

        // 单线程正确迭代
//        Iterator<String> stringIterator = stringVector.iterator();
//        while (stringIterator.hasNext()) {
//            String next = stringIterator.next();
//            if (next.equals("demo5")) {
//                stringIterator.remove();
//            }
//        }

        // 多线程错误迭代
//        Iterator<String> stringIterator = stringVector.iterator();
//        for (int i = 0; i < 4; i++) {
//            new Thread(() -> {
//                while (stringIterator.hasNext()) {
//                    String next = stringIterator.next();
//                    if (next.equals("demo2")) {
//                        stringIterator.remove();
//                    }
//                }
//            }).start();
//        }

        // 多线程正确迭代
        Iterator<String> stringIterator = stringVector.iterator();
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
               synchronized (stringIterator) {
                   while (stringIterator.hasNext()) {
                       String next = stringIterator.next();
                       if (next.equals("demo2")) {
                           stringIterator.remove();
                       }
                   }
               }
            }).start();
        }
    }
}
