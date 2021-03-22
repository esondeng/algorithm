package com.test.io;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author dengxiaolin
 * @since 2020/07/30
 */
public class BioClient {
    public BioClient() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("client begin");
        Socket socket = new Socket("127.0.0.1", 5555);
        Thread sendThread = new ReadThread(socket);
        Thread printThread = new WriteThread(socket);
        sendThread.start();
        printThread.start();
        sendThread.join();
        printThread.join();

    }

    static class WriteThread extends Thread {
        private Socket socket;

        public WriteThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                while (true) {
                    BufferedReader bufReader = new BufferedReader(new InputStreamReader(System.in));
                    try {
                        String msgToSend = bufReader.readLine();
                        dataOutputStream.writeUTF(msgToSend);
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
                        System.out.println(" server: " + str.trim());
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
