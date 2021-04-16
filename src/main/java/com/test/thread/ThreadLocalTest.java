package com.test.thread;

import java.util.Stack;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengxiaolin
 * @since 2020/10/15
 */
public class ThreadLocalTest {

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            // corePoolSize如果是1的话，会一个一个执行，没有并行
            4,
            10,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingDeque<>(50),
            new ThreadFactory() {
                private AtomicInteger atomicInteger = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "thread-" + atomicInteger.getAndIncrement());
                }
            },
            new ThreadPoolExecutor.CallerRunsPolicy()

    );

    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("123");

        ThreadLocal<Stack<String>> threadLocal = new ThreadLocal<>();
        threadLocal.set(stack);
        System.out.println(stack);

        InheritableThreadLocal<Stack<String>> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(stack);

        CompletionService<Void> completionService = new ExecutorCompletionService<>(executor);
        completionService.submit(new TheadTest(stack, threadLocal, inheritableThreadLocal), null);

        try {
            completionService.take();
        }
        catch (Exception e) {

        }

        executor.shutdown();

    }


    public static class TheadTest implements Runnable {
        Stack<String> stack;
        ThreadLocal<Stack<String>> threadLocal;
        InheritableThreadLocal<Stack<String>> inheritableThreadLocal;

        public TheadTest(Stack<String> stack, ThreadLocal<Stack<String>> threadLocal, InheritableThreadLocal<Stack<String>> inheritableThreadLocal) {
            this.stack = stack;
            this.threadLocal = threadLocal;
            this.inheritableThreadLocal = inheritableThreadLocal;
        }

        @Override
        public void run() {
            // 说明直接使用的value， 因此不能使用InheritableThreadLocal来作为链路追踪的手段
            System.out.println(stack == inheritableThreadLocal.get());

        }
    }
}
