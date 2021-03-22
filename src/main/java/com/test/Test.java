package com.test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dengxiaolin
 * @since 2020/12/09
 */
public class Test {

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public void read(Lock lock) throws InterruptedException {
        lock.lock();
        lock.lock();
        Thread.sleep(1000);
        System.out.println("read");
        lock.unlock();
        lock.unlock();
    }

    public void write(Lock lock) throws InterruptedException {
        lock.lock();
        lock.lock();
        Thread.sleep(1000);
        System.out.println("write");
        lock.unlock();
        lock.unlock();
    }

    public static void main(String[] args) {
        Test test = new Test();
        Runnable read = () -> {
            try {
                test.read(readLock);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };
        Runnable write = () -> {
            try {
                test.write(writeLock);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(read).start();
        }

//        for (int i = 0; i < 1; i++) {
//            new Thread(write).start();
//        }
    }
}
