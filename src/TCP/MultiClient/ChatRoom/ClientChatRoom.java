package TCP.MultiClient.ChatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientChatRoom {
    private InetAddress host;
    private int port;

    public ClientChatRoom(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    private void execute() throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input your name: ");
        String name = sc.nextLine();

        Socket client = new Socket(host, port);
        ClientChatRoomReceiver receiver = new ClientChatRoomReceiver(client);
        receiver.start();
        ClientChatRoomSender sender = new ClientChatRoomSender(client, name);
        sender.start();
    }


    public static void main(String[] args) throws IOException {
        ClientChatRoom client = new ClientChatRoom(InetAddress.getLocalHost(), 1111);
        client.execute();
    }
}



class ClientChatRoomReceiver implements Runnable {
    private Socket client;
    private Thread thread;

    public ClientChatRoomReceiver(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(client.getInputStream());
            while(true) {
                String sms = dis.readUTF();
                System.out.println(sms);
            }
        } catch (Exception e) {
            try {
                dis.close();
                client.close();
            } catch (IOException ex) {
                System.out.println("Disconnected with Server");
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}

class ClientChatRoomSender implements Runnable {
    private Socket client;
    private String name;
    private Thread thread;

    public ClientChatRoomSender(Socket client, String name) {
        this.client = client;
        this.name = name;
    }

    @Override
    public void run() {
        DataOutputStream dos = null;
        Scanner scanner = null;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            scanner = new Scanner(System.in);
            while(true) {
                String sms = scanner.nextLine();
                dos.writeUTF(name + ": " + sms);
            }
        } catch (Exception e) {
            try {
                dos.close();
                client.close();
            } catch (IOException ex) {
                System.out.println("Disconnected with Server");
            }
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

}