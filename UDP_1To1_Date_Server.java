import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDP_1To1_Date_Server {
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
