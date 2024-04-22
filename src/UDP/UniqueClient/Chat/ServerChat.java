package UDP.UniqueClient.Chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class ServerChat {
    public static void main(String[] args) throws Exception {
        int port = 1111;
        DatagramSocket datagramSocket = new DatagramSocket(port);
        System.out.println("Server is started...");
        Scanner scanner = new Scanner(System.in);

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        DatagramPacket sendPacket = null;
        DatagramPacket receivePacket = null;
        InetAddress clientInetAddress = null;
        int clientPort = -9999;

        while (true) {
            try {
                datagramSocket.setSoTimeout(100);
                receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);
                clientInetAddress = receivePacket.getAddress();
                clientPort = receivePacket.getPort();

                String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client: " + receiveString);
            } catch (SocketTimeoutException e) {
                // do nothing
            }



            if (System.in.available() > 0 && clientInetAddress != null && clientPort != -9999) {
                String sendString = scanner.nextLine();
                sendData = sendString.getBytes();
                sendPacket = new DatagramPacket(sendData, sendData.length, clientInetAddress, clientPort);
                datagramSocket.send(sendPacket);
            }

        }

    }
}