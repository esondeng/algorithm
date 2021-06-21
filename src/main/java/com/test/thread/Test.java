package com.test.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Stream;

/**
 * @author dengxiaolin
 * @since 2020/08/04
 */
public class Test {

    private ReentrantLock lock = new ReentrantLock();
    private Condition charCondition = lock.newCondition();
    private Condition counterCondition = lock.newCondition();

    private volatile boolean charPrinted = false;

    private Thread charPrint = new Thread() {
        private char s = 'A';

        @Override
        public void run() {
            Stream.of(1, 2, 3, 4, 5).forEach(t -> {
                lock.lock();
                try {
                    if (charPrinted) {
                        try {
                            // 等待数字打印的条件队列
                            counterCondition.await();
                        }
                        catch (Exception e) {
                        }
                    }

                    System.out.print(s++);
                    charPrinted = true;
                    charCondition.signalAll();
                }
                finally {
                    lock.unlock();
                }

            });
        }

    };

    private Thread counter = new Thread() {
        private int i = 1;

        @Override
        public void run() {
            Stream.of(1, 2, 3, 4, 5).forEach(t -> {
                lock.lock();
                try {
                    if (!charPrinted) {
                        try {
                            // 等待字母打印的条件队列
                            charCondition.await();
                        }
                        catch (Exception e) {
                        }
                    }
                    System.out.print(i++);
                    charPrinted = false;
                    counterCondition.signalAll();

                }
                finally {
                    lock.unlock();
                }
            });
        }
    };

    public static void main(String[] args) {
        Test test = new Test();
        test.counter.start();
        test.charPrint.start();


        Stream.iterate(0, t -> t + 1).limit(2).forEach(System.out::println);
    }

}
