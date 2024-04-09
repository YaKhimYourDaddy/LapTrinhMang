package get_date;

import java.net.*;

public class Client {
    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("localhost");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        sendData = "getDate".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9876);
        datagramSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        datagramSocket.receive(receivePacket);
        String receiveString = new String(receivePacket.getData());
        System.out.println("Receive from Server: " + receiveString);
        datagramSocket.close();
    }
}
