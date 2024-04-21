package UDP.UniqueClient.String;

import MyHelpingLibrary.StringResponder;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerString {
    public static void main(String[] args) throws Exception {
        int port = 1111;
        DatagramSocket datagramSocket = new DatagramSocket(port);
        System.out.println("Server is started...");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);

            String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Receive from Client: " + receiveString);

            String sendString = StringResponder.getRespond(receiveString);
            sendData = sendString.getBytes();

            InetAddress ipAddress = receivePacket.getAddress();
            int clientPort = receivePacket.getPort();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, clientPort);
            datagramSocket.send(sendPacket);
        }
//        datagramSocket.close();

    }
}
