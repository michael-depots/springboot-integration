package demo.michael.synopsis.atomic;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @className: AtomicIntegerArrayDemo
 * @description: AtomicIntegerArray 使用
 * @author: charles
 * @date: 12/29/21
 **/
public class AtomicIntegerArrayDemo {

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 7, 2, 9};
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(arr);
        int i = atomicIntegerArray.addAndGet(1, 2);
        System.out.println("i = " + i);

        // 自定义计算
        int j = atomicIntegerArray.accumulateAndGet(3, 5, ((left, right) ->
                left * right + 1
        ));
        System.out.println("j = " + j);
    }

}
