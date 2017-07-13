package net;

/**
 * Created by liujunshi on 2017/6/27.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;


public class UDPClient {

    private static final int BUFFER_SIZE = 1024;
    private static final String SERVER_IP = "localhost";

    // todo Change Run 为客户端的监听端口，取值9001,9002,9003
    private static int clientListenPort = 9003;

    public static void main(String[] args) {
        new Thread(new ReceiveMsgThread(clientListenPort)).start();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true) {
                String msg = br.readLine();
                if ("shutdown".equals(msg)) {
                    System.out.println("客户端已退出！");
                    System.exit(-1);
                }
                sendToServer(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 客户端向服务端发送消息
     * @param msg
     * @throws IOException
     */
    private static void sendToServer(String msg) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        byte[] bytes = msg.getBytes();
        ds.send(new DatagramPacket(bytes, bytes.length, new InetSocketAddress(SERVER_IP, UDPServer.SERVER_PORT)));
        ds.close();
    }

    /**
     * 用来监听服务器端发送的信息
     */
    private static class ReceiveMsgThread implements Runnable {
        private int listenPort;

        private ReceiveMsgThread(int listenPort) {
            this.listenPort = listenPort;
        }


        public void run() {
            System.out.println("客户端已经启动，监听端口："+clientListenPort);
            try {
                DatagramSocket ds = new DatagramSocket(listenPort);
                while (true) {
                    DatagramPacket recv = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
                    ds.receive(recv);
                    System.out.println("收到：" + new String(recv.getData(), 0, recv.getLength()));
                }
            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
