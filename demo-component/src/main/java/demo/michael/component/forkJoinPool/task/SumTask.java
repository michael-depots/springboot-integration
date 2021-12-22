package demo.michael.component.forkJoinPool.task;

import java.util.concurrent.RecursiveTask;

/**
 * @className: SumTask
 * @description: 继承抽象类RecursiveTask，通过返回的结果，来实现数组的多线程分段累累加
 * RecursiveTask: 具有返回值
 * @author: michael
 * @date: 12/22/21
 **/
public class SumTask extends RecursiveTask<Long> {

    private static final int THRESHOLD = 50; //每个小任务 最多只累加50个数
    private long[] numbers;
    private int start;
    private int end;

    /**
     * @description: 数组求和
     * @return:
     */
    public SumTask(long[] numbers, int start, int end) {
        super();
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0L;
        //当end与start之间的差小于threshold时，开始进行实际的累加
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += numbers[i];
            }
            return sum;
        } else {
            //当end与start之间的差大于threshold，即要累加的数超过50个时候，将大任务分解成小任务
            int middle = (start + end) / 2;
            SumTask left = new SumTask(numbers, start, middle);
            SumTask right = new SumTask(numbers, middle, end);
            //并行执行两个小任务
            left.fork();
            right.fork();
            //把两个小任务累加的结果合并起来
            return left.join() + right.join();
        }
    }
}
