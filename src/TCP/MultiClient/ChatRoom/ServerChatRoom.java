package TCP.MultiClient.ChatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ServerChatRoom {
    private int serverPort;
    public static ArrayList<Socket> listSocket;

    public ServerChatRoom(int serverPort) {
        this.serverPort = serverPort;
    }

    private void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        ServerSendMessage write = new ServerSendMessage();
        write.start();
        System.out.println("Server is listening...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connected with " + socket);
            ServerChatRoom.listSocket.add(socket);
            ServerReceiveMessage read = new ServerReceiveMessage(socket);
            read.start();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerChatRoom.listSocket = new ArrayList<>();
        ServerChatRoom server = new ServerChatRoom(1111);
        server.execute();
    }

}

class ServerReceiveMessage implements Runnable {
    private Thread thread;
    private Socket socket;

    public ServerReceiveMessage(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            while (true) {
                String receiveString = dis.readUTF();
                if(receiveString.contains("exit")) {
                    ServerChatRoom.listSocket.remove(socket);
                    System.out.println("Disconnected with " + socket);
                    dis.close();
                    socket.close();
                    continue; // already disconnected
                }
                for (Socket item : ServerChatRoom.listSocket) {
                    if(item.getPort() != socket.getPort()) {
                        DataOutputStream dos = new DataOutputStream(item.getOutputStream());
                        dos.writeUTF(receiveString);
                    }
                }
                System.out.println(receiveString);
            }
        } catch (Exception e) {
            try {
                socket.close();
            } catch (IOException ex) {
                System.out.println("Disconnected  Server");
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

class ServerSendMessage extends Thread {

    @Override
    public void run() {
        DataOutputStream dos = null;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String sendString = scanner.nextLine();
            try {
                for (Socket item : ServerChatRoom.listSocket) {
                    dos = new DataOutputStream(item.getOutputStream());
                    dos.writeUTF("Server: " + sendString);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}