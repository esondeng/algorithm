package com.test.park;

import java.util.concurrent.locks.LockSupport;

/**
 * @author dengxiaolin
 * @since 2021/02/22
 */
public class Test {

    private static Thread mainThread;
    public static void main(String[] args) {
        ThreadA ta = new ThreadA("ta");
        // 获取主线程
        mainThread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+" start ta");
        ta.start();//断点一
        System.out.println(Thread.currentThread().getName()+" block");
        // 主线程阻塞
        LockSupport.park(Thread.currentThread());
        System.out.println(Thread.currentThread().getName()+" continue");
    }
    static class ThreadA extends Thread{
        public ThreadA(String name) {
            super(name);
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" wakup others");
            // 唤醒“主线程”
            LockSupport.unpark(mainThread);
            System.out.println("After wakup others");
        }
    }
}
