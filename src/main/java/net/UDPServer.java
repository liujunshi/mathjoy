package net;

import java.io.IOException;
import java.net.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * User: liujunshi
 * Date: 16-6-27
 * Time: 下午12:47
 */
public class UDPServer {

    public static final int SERVER_PORT = 20202;
    private static final int BUFFER_SIZE = 1024;
    private static final List<InetSocketAddress> clientAddressList = new LinkedList<InetSocketAddress>();

    static {
        clientAddressList.add(new InetSocketAddress("localhost", 9001));
        clientAddressList.add(new InetSocketAddress("localhost", 9002));
        clientAddressList.add(new InetSocketAddress("localhost", 9003));
    }

    public static void main(String[] args) {
        System.out.println("服务器已经启动，监听端口："+SERVER_PORT);
        try {
            DatagramSocket ds = new DatagramSocket(SERVER_PORT);
            while (true) {
                // 从客户端接收到的内容，即客户端的请求
                DatagramPacket dp = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
                ds.receive(dp);
                String msg = new String(dp.getData(), 0, dp.getLength());
                System.out.println("收到客户端消息：" + msg);
                if ("shutdown server".equals(msg)) {
                    System.out.println("服务器已经被关闭！");
                    break;
                }
                sendToClients(msg, dp.getSocketAddress());
            }
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 服务器端向客户端群发消息
     * @param msg 消息
     * @throws IOException
     */
    private static void sendToClients(String msg, SocketAddress fromAddress) throws IOException {
        DatagramSocket ds = new DatagramSocket();
        byte[] bytes = (msg+", 经服务器转发，服务器时间 "+new Date()).getBytes();
        for (InetSocketAddress clientAddress : clientAddressList) {
            // todo 判断如果是发送者，可以跳过消息转发，根据ip地址判断
            ds.send(new DatagramPacket(bytes, bytes.length, clientAddress));
        }
        ds.close();
    }
}