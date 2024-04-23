package UDP.MultiClient.ChatRoom;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientChatRoom {
    private InetAddress host;
    private int port;

    public ClientChatRoom(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }
    private void execute() throws IOException {
        //Tạo Socket
        DatagramSocket client = new DatagramSocket();

        //Phần bổ sung
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập vào tên của bạn: ");
        String name = scanner.nextLine();
        client.send(createPacket(name + " đã tham gia vào phòng chat!"));

        ClientReceiveMessage read = new ClientReceiveMessage(client);
        read.start();
        ClientSendMessage write = new ClientSendMessage(client, host, port, name);
        write.start();
    }

    public static void main(String[] args) throws IOException {
        ClientChatRoom client = new ClientChatRoom(InetAddress.getLocalHost(), 15797);
        client.execute();
    }

    private DatagramPacket createPacket(String value) {
        byte[] arrData = value.getBytes();
        return new DatagramPacket(arrData, arrData.length, host, port);
    }
}

class ClientReceiveMessage extends Thread{
    private DatagramSocket client;

    public ClientReceiveMessage(DatagramSocket client) {
        this.client = client;
    }

    @Override
    public void run() {
        try {
            while(true) {
                String sms = recieveData(client);
                System.out.println(sms);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    private String recieveData(DatagramSocket client) throws IOException {
        byte[] temp = new byte[1024];
        DatagramPacket recieve_Packet = new DatagramPacket(temp, temp.length);
        client.receive(recieve_Packet);
        return new String(recieve_Packet.getData()).trim();
    }
}

class ClientSendMessage extends Thread{
    private DatagramSocket client;
    private InetAddress host;
    private int port;
    private String name;

    public ClientSendMessage(DatagramSocket client, InetAddress host, int port, String name) {
        this.client = client;
        this.host = host;
        this.port = port;
        this.name = name;
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            try {
                String sms = sc.nextLine();
                DatagramPacket DP = createPacket(name + ": " + sms);	//Đóng gói chuẩn bị gửi đi
                client.send(DP);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    private DatagramPacket createPacket(String value) {
        byte[] arrData = value.getBytes();
        return new DatagramPacket(arrData, arrData.length, host, port);
    }
}