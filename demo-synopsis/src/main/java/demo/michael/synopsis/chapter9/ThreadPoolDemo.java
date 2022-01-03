package demo.michael.synopsis.chapter9;

import java.util.concurrent.*;

/**
 * @className: ThreadPoolDemo
 * @description: Future 是对于具体的 Runnable 或 Callable 任务的执行结果进行取消、查询是否完成、获取结果、设置结果操作。get 方法会阻塞，直到任务返回结果。
 * @author: charles
 * @date: 1/3/22
 **/
public class ThreadPoolDemo {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        LinkedBlockingQueue<Runnable> objects = new LinkedBlockingQueue<>();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 3000L, TimeUnit.SECONDS, objects);

        Future<String> submit = null;
        for (int i = 0; i < 100; i++) {
            submit = threadPoolExecutor.submit(new CallableAndFutureDemo());
        }

        for (int i = 0; i < 100; i++) {
            System.out.println(submit.get());
        }
    }

}
