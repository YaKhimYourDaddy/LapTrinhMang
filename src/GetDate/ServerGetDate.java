package GetDate;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class ServerGetDate {
    public static void main(String[] args) throws Exception {
        int port = 1111;
        DatagramSocket datagramSocket = new DatagramSocket(port);
        System.out.println("Server is started...");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);
//            ByteArrayInputStream bais = new ByteArrayInputStream(receivePacket.getData());
//            BufferedReader br = new BufferedReader(new InputStreamReader(bais));
//            String receiveString = br.readLine();
//            DataInputStream dis = new DataInputStream(bais);
            String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Receive from Client: " + receiveString);

            if (receiveString.trim().equals("getDate"))
                sendData = new Date().toString().getBytes();
            else sendData = "Server didn't get the right request!".getBytes();

            int clientPort = receivePacket.getPort();
            InetAddress ipAddress = receivePacket.getAddress();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, clientPort);
            datagramSocket.send(sendPacket);
        }
    }
}
