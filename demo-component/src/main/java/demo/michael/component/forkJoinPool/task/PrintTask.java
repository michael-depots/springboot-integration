package demo.michael.component.forkJoinPool.task;

import java.util.concurrent.RecursiveAction;

/**
 * @className: PrintTask
 * @description: 继承RecursiveAction来实现“可分解”的任务，用多线程实现并行执行。
 * @author: michael
 * @date: 12/22/21
 **/
public class PrintTask extends RecursiveAction {

    /**
     * 最多只能打印50个数
     */
    private static final int THRESHOLD = 50;
    private int start;
    private int end;

    /**
     * @param: start 分割起点
     * @param: end   分割终点
     * @description: 打印任务
     * @return:
     */
    public PrintTask(int start, int end) {
        super();
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "打印i值>>>" + i);
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle, end);
            //并行执行两个“小任务”
            left.fork();
            right.fork();
        }
    }
}
