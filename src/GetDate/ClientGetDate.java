package GetDate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClientGetDate {
    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        int port = 1111;
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        sendData = "getDate".getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
        datagramSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        datagramSocket.receive(receivePacket);
//        ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
//        BufferedReader br = new BufferedReader(new InputStreamReader(bais));
//        String receiveString = br.readLine();
//        DataInputStream dis = new DataInputStream(bais);
        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Receive from Server: " + receiveString);

        datagramSocket.close();
    }
}