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
        ClientReceiveMessage read = new ClientReceiveMessage(client);
        read.start();
        ClientSendMessage write = new ClientSendMessage(client, name);
        write.start();
    }


    public static void main(String[] args) throws IOException {
        ClientChatRoom client = new ClientChatRoom(InetAddress.getLocalHost(), 1111);
        client.execute();
    }
}



class ClientReceiveMessage implements Runnable {
    private Socket client;
    private Thread thread;

    public ClientReceiveMessage(Socket client) {
        this.client = client;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(client.getInputStream());
            while(true) {
                String receiveString = dis.readUTF();
                System.out.println(receiveString);
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

class ClientSendMessage implements Runnable {
    private Socket client;
    private String name;
    private Thread thread;

    public ClientSendMessage(Socket client, String name) {
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
                String sendString = scanner.nextLine();
                dos.writeUTF(name + ": " + sendString);
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
