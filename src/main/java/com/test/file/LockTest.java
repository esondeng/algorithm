package com.test.file;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author dengxiaolin
 * @since 2020/07/01
 */
public class LockTest {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    private void test() {
        lock.readLock().lock();
        System.out.println(1);
        lock.readLock().unlock();
    }

    public static void main(String[] args) {
        LockTest test = new LockTest();



    }
}
