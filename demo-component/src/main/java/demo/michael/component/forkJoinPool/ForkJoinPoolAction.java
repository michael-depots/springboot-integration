package demo.michael.component.forkJoinPool;

import demo.michael.component.forkJoinPool.task.PrintTask;
import demo.michael.component.forkJoinPool.task.SumTask;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.TimeUnit;

/**
 * @className: ForkJoinPoolAction
 * @description: TODO 类描述
 * @author: charles
 * @date: 12/22/21
 **/
public class ForkJoinPoolAction {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 简单的打印0-500的数值
        ForkJoinPoolPrint();

        // 使用 ForkJoinPool 对一亿个数组元素求和
        ForkJoinPoolSum();

    }

    /**
     * @description: 使用ForkJoinPool完成一个任务的分段执行，简单的打印0-500的数值。
     * @return: void
     */
    public static void ForkJoinPoolPrint() throws InterruptedException {
        PrintTask task = new PrintTask(0, 500);
        //创建实例，开启2个线程并执行分割任务
        ForkJoinPool forkJoinPool = new ForkJoinPool(2);
        forkJoinPool.submit(task);
        //线程阻塞，等待所有任务完成
        forkJoinPool.awaitTermination(2, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }

    /**
     * @description: 对一亿个数组元素求和，耗时比较
     * @return: void
     */
    public static void ForkJoinPoolSum() throws InterruptedException, ExecutionException {
        long[] numbers = new long[100000000];
        Random random = new Random();
        long total = 0L;
        int len = numbers.length;
        //初始化10000000个数组元素
        for (int i = 0; i < len; i++) {
            int temp = random.nextInt(50);
            numbers[i] = Long.valueOf(temp).longValue();
        }
        System.out.println("数组初始化完成。。。");
        long start = System.currentTimeMillis();
        for (int i = 0; i < len; i++) {
            //对数组元素赋值，并将数组元素的值添加到sum总和中
            total += numbers[i];
        }
        long timeCost = System.currentTimeMillis() - start;
        System.out.println("End -------- for循环累加求和结果：" + total + ",耗时：" + timeCost + " ms");

        start = System.currentTimeMillis();
        SumTask task = new SumTask(numbers, 0, len);

        // 创建一个通用池，这个是jdk1.8提供的功能
        ForkJoinPool pool = ForkJoinPool.commonPool();
        // 提交分解的 SumTask 任务
        ForkJoinTask<Long> future = pool.submit(task);
        timeCost = System.currentTimeMillis() - start;
        System.out.println("End -------- 多线程执行求和结果：" + future.get() + ",耗时：" + timeCost + " ms");
        //关闭线程池
        pool.shutdown();
    }
}
