package demo.michael.synopsis.chapter9;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @className: CallableAndFutureDemo
 * @description: Callable 与 Runnable 功能相似，Callable 的 call 有返回值，可以返回给客户端，而 Runnable 没有返回值。
 * 一般情况下，Callable 与 FutureTask 一起使用，或者通过线程池的 submit 方法返回相应的 Feature.
 * @author: charles
 * @date: 1/3/22
 **/
public class CallableAndFutureDemo implements Callable<String> {

    @Override
    public String call() throws Exception {
        Thread.sleep(3000L);
        System.out.println(Thread.currentThread().getName());
        return "this is call method";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CallableAndFutureDemo callableDemo = new CallableAndFutureDemo();
        FutureTask<String> stringFutureTask = new FutureTask<>(callableDemo);
        new Thread(stringFutureTask).start();
        stringFutureTask.get();
        System.out.println(stringFutureTask.get());
    }

}
