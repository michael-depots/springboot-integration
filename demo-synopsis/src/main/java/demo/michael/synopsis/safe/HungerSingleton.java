package demo.michael.synopsis.safe;

/**
 * @className: HungerSingleton
 * @description: 恶汉式单例，在类加载的时候，就已经进行实例化，无论之后用不用到
 * 问题: 如果该类比较占内存，之后又没用到，就白白浪费了资源
 * @author: michael
 * @date: 12/22/21
 **/
public class HungerSingleton {

    private static HungerSingleton hungerSingleton = new HungerSingleton();

    /**
     * @description: 在单例模式中，构造方法一定要私有化
     * @return:
     */
    private HungerSingleton() {
    }

    public static HungerSingleton getInstance() {
        return hungerSingleton;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                System.out.println(HungerSingleton.getInstance());
            }).start();
        }
    }
}
