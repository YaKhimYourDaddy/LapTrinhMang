
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDP_1To1_Chat_Server {
    public static void main(String[] args) throws Exception {
        int port = 1111;
        DatagramSocket datagramSocket = new DatagramSocket(port);
        System.out.println("Server is started...");

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        Scanner scanner = new Scanner(System.in);
        int clientPort = -1;
        InetAddress ipAddress = null;
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

        while (true) {
            // Check if user input is available
            // and if server got packet from a client
            // because UDP is no connection protocol
            if (System.in.available() > 0 && clientPort != -1 && ipAddress != null) {
                String sendString = scanner.nextLine();
                sendData = sendString.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, clientPort);
                datagramSocket.send(sendPacket);
            }

            try {
                datagramSocket.setSoTimeout(100);
                datagramSocket.receive(receivePacket);
                String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Client:" + receiveString);
                clientPort = receivePacket.getPort();
                ipAddress = receivePacket.getAddress();
            } catch (SocketTimeoutException e) {
                // do nothing, just move onto next loop to send
                continue;
            }




        }
    }
}
