
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class UDP_1To1_Chat_Client {
    public static void main(String[] args) throws Exception{
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress ipAddress = InetAddress.getByName("localhost");
        int port = 1111;

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        Scanner scanner = new Scanner(System.in);
        // DatagramSocket.receive() will wait until it receive any DatagramPacket,
        // and it has no way to check if there is any packet to receive
        // So we can only send 1 message before and after receive 1 message
        // as long as we don't use Thread for sending and receiving processes
        // Or we can use DatagramSocket.setSoTimeout() to move on next sending process
        while (true) {
            // Check if user input is available
            if (System.in.available() > 0) {
                String sendString = scanner.nextLine();
                sendData = sendString.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, port);
                datagramSocket.send(sendPacket);
            }

            try {
                datagramSocket.setSoTimeout(100);
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                datagramSocket.receive(receivePacket);
                String receiveString = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.println("Server:" + receiveString);
            } catch (SocketTimeoutException e) {
                // do nothing, just move onto next loop to send
                continue;
            }




        }
    }
}
