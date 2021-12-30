package demo.michael.synopsis.container;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @className: CopyOnWriteDemo
 * @description: 并发容器
 * 使用: CopyOnWrite、Concurrent、BlockingQueue、LinkedBlockingQuaue
 * @author: charles
 * @date: 12/30/21
 **/
public class CopyOnWriteDemo {

    public static void main(String[] args) {
        CopyOnWriteArrayList<String> strings = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 1000; i++) {
            strings.add("Demo" + i);
        }

//        strings.forEach(e -> {
//            if (e.equals("Demo3")) {
//                strings.remove(e);
//            }
//        });

        // 错误示例，在 CopyOnWriteArrayList 内部不能移除操作
//        Iterator<String> iterator = strings.iterator();
//        while (iterator.hasNext()) {
//            String next = iterator.next();
//            if (next.equals("Demo3")) {
//                iterator.remove();
//            }
//        }

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                strings.forEach(e -> {
                    if (e.equals("Demo3")) {
                        strings.remove(e);
                    }
                });
            }).start();
        }
    }
}
