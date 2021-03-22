package com.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dengxiaolin
 * @since 2020/07/30
 */
public class BioPoolServer {

    private static ServerSocket serverSocket;
    private static Socket socket;
    /**
     * 并行线程池
     */
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(
            // 最小值 ， 最大值， 存活时间
            1, 1, 600, TimeUnit.SECONDS,

            // 阻塞队列大小
            new LinkedBlockingDeque<>(50),

            // 创建工厂，指定名称，方便查询问题
            new ThreadFactory() {
                final AtomicInteger threadNumber = new AtomicInteger(1);

                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "agent apply auto audit thread-" + threadNumber.getAndIncrement());
                }
            },

            // 拒绝策略
            new ThreadPoolExecutor.CallerRunsPolicy()
    );

    static {
        System.out.println("server begin……");
        try {
            // 等待客户端连接
            serverSocket = new ServerSocket(5555);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        // 这就是bio同步阻塞
        while (true) {
            socket = serverSocket.accept();
            executor.execute(new ConnectionHandler(socket));
        }
    }

    static class ConnectionHandler implements Runnable {
        private Socket socket;

        public ConnectionHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted() && !socket.isClosed()) {
                try {
                    InputStream inputStream = socket.getInputStream();
                    while (true) {
                        try {
                            byte[] buffer = new byte[1024];
                            // 这里阻塞获取数据
                            int result = inputStream.read(buffer);
                            String str = new String(buffer, 0, result, "utf-8");
                            System.out.println("receive client msg:" + str.trim());
                            Charset cs = Charset.forName("utf-8");
                            TimeUnit.MILLISECONDS.sleep(100);
                            byte[] bytes = str.getBytes(cs);
                            socket.getOutputStream().write(bytes);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
