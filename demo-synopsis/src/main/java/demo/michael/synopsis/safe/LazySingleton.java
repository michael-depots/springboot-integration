package demo.michael.synopsis.safe;

/**
 * @className: LazySingleton
 * @description: 懒汉式单例，在需要的时候再实例化，是非线程安全的
 * 解决: 使用 synchronized 配合 volatile 进行修饰，防止指令重排序，以此来确保线程安全性
 * @author: michael
 * @date: 12/22/21
 **/
public class LazySingleton {

    // 使用 volatile 进行修饰，防止指令重排序
    private static volatile LazySingleton lazySingleton = null;

    private LazySingleton() {
    }

    /**
     * @description: 获取 LazySingleton 实例
     * @return: demo.michael.synopsis.safe.LazySingleton
     */
    public static LazySingleton getInstance() {
        // 判断实例是否为空，为空则实例化
        if (null == lazySingleton) {
            // 模拟实例化时耗时的操作
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Double check
            synchronized (LazySingleton.class) {
                if (null == lazySingleton) {
                    lazySingleton = new LazySingleton();
                }
            }
        }

        return lazySingleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(LazySingleton.getInstance());
            }).start();
        }
    }
}
