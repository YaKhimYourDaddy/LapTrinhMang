package UDP.UniqueClient.Calculate;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientCalculate {
    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        int port = 1111;
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a math expression: ");
        String sendString = scanner.nextLine();

        sendData = sendString.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
        datagramSocket.send(sendPacket);

        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        datagramSocket.receive(receivePacket);
        String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
        System.out.println("Receive result from Server: " + receiveString);
        datagramSocket.close();
    }
}
