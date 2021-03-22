package com.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

/**
 * @author dengxiaolin
 * @since 2020/07/30
 */
public class BioServer {
    private static ServerSocket serverSocket;
    private static Socket socket;

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
            new ReadThread(socket).start();
        }
    }

    static class ReadThread extends Thread {
        private Socket socket;

        public ReadThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
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
