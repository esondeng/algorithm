package com.test.completable;

import java.util.concurrent.CompletableFuture;

/**
 * @author dengxiaolin
 * @since 2020/09/04
 */
public class CompletableFutureTest {

    public static void main(String[] args) {
        applyToEither();
    }

    /**
     * 关心第上一个结果
     */
    private static void thenAccept() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenAccept(s -> System.out.println(s + " world"));
    }


    /**
     * 不关心第上一个结果
     */
    private static void thenRun() {
        CompletableFuture.supplyAsync(() -> "hello")
                .thenRun(() -> System.out.println("world"));
    }

    /**
     * 关心前面两个结果
     */
    private static void combineTest() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }


    /**
     * 关心前面两个结果进行消费
     */
    private static void acceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2)).join();
    }

    /**
     * 不关心前面的结果，前面两个执行完之后，再执行逻辑
     */
    private static void runAfterBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s1");
            return "s1";
        }).runAfterBothAsync(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("s2");
            return "s2";
        }), () -> System.out.println("hello world")).join();
    }

    /**
     * 获取谁先执行完的结果
     */
    private static void applyToEither() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s1";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello world";
        }), s -> s).join();
        System.out.println(result);
    }
}
