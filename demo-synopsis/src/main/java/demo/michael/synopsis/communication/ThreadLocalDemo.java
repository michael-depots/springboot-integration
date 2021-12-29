package demo.michael.synopsis.communication;

/**
 * @className: ThreadLocalDemo
 * @description: 线程变量，是一个以 ThreadLocal 对象为键，任意对象为值的存储结构。一个线程可以根据一个ThreadLocal对象查询到绑定在这个线程上的一个值。
 * 参考: https://www.liaoxuefeng.com/wiki/1252599548343744/1306581251653666
 * @author: charles
 * @date: 12/29/21
 **/
public class ThreadLocalDemo {

    ThreadLocal<Integer> num = ThreadLocal.withInitial(() -> 0);

    /**
     * @description: 自增并输出num的值
     * @return: void
     */
    public void create() {
        Integer myNum = num.get();
        myNum++;
        System.out.println(Thread.currentThread().getName() + " ====>" + myNum);
        num.set(myNum);
    }

    public static void main(String[] args) {
        ThreadLocalDemo threadLocalDemo = new ThreadLocalDemo();
        for (int i = 1; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                while (true) {
                    threadLocalDemo.create();
                    try {
                        Thread.sleep(finalI * 1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}
