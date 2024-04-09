package get_date;

import java.net.*;
import java.util.Date;

public class Server {
    public static void main(String[] args) throws Exception {
        DatagramSocket datagramSocket = new DatagramSocket(9876);
        System.out.println("Server is started...");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            datagramSocket.receive(receivePacket);

            InetAddress ipAddress = receivePacket.getAddress();
            int port = receivePacket.getPort();
            String request = new String(receivePacket.getData());
            System.out.println("Receive from Client: " + request);

            if (request.trim().equals("getDate"))
                sendData = new Date().toString().getBytes();
            else sendData = "Server didn't get the right request!".getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
            datagramSocket.send(sendPacket);
        }
    }
}
