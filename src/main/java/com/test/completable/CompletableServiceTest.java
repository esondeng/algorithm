package com.test.completable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author dengxiaolin
 * @since 2020/08/14
 */
public class CompletableServiceTest {
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

    static {
        executor.prestartAllCoreThreads();
        // executor.allowCoreThreadTimeOut(true);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // test1();

        test2();
    }


    public static void test1() {
        List<Callable<String>> task = IntStream.range(0, 5)
                .boxed()
                .map(CompletableServiceTest::toTask
                ).collect(Collectors.toList());
        List<Future<String>> futures = new ArrayList<>();
        task.forEach(r -> futures.add(executor.submit(r)));

        futures.forEach(t -> {
            try {
                System.out.println(t.get());
            }
            catch (Exception e) {
                e.printStackTrace();
            }

        });

        executor.shutdown();
    }


    public static void test2() throws ExecutionException, InterruptedException {
        // stream流不会自动装箱
        List<Callable<String>> task = IntStream.range(0, 5)
                .boxed()
                .map(CompletableServiceTest::toTask)
                .collect(Collectors.toList());

        CompletionService<String> completionService = new ExecutorCompletionService<>(executor);
        task.forEach(completionService::submit);

        IntStream.range(0, 5).forEach(t -> {
            try {
                System.out.println(completionService.take().get());
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(20);
        System.out.println("线程池线程个数:" + executor.getActiveCount());

        executor.shutdown();

    }


    private static Callable<String> toTask(int i) {
        return () -> {
            System.out.println("task  [" + i + "] will be executed");
            TimeUnit.SECONDS.sleep(5 - i);
            System.out.println("task  [" + i + "]  executes done");
            return "task result - " + i;
        };

    }
}
