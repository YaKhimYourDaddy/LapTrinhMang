package TCP.MultiClient.Date;

import MyHelpingLibrary.ExpressionEvaluator;
import TCP.MultiClient.ChatRoom.ServerChatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;

public class ServerDate {
    private int serverPort;
    public static ArrayList<Socket> listSocket;
    public ServerDate(int serverPort) { this.serverPort = serverPort; }

    private void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        System.out.println("Server is listening...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connected with " + socket);
            this.listSocket.add(socket);
            DateClientHandler clientHandler = new DateClientHandler(socket);
            clientHandler.start();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerDate.listSocket = new ArrayList<>();
        ServerDate server = new ServerDate(1111);
        server.execute();
    }
}

class DateClientHandler implements Runnable {
    private Socket socket;
    private Thread thread;

    public DateClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        DataOutputStream dos = null;
        String sms = null;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
            while (true) {
                sms = dis.readUTF();
                System.out.println(sms);
                String respond;
                if(sms.contains("exit")) {
                    ServerChatRoom.listSocket.remove(socket);
                    System.out.println("Disconnected with " + socket);
                    dis.close();
                    dos.close();
                    socket.close();
                    continue; // already disconnected
                }
                else if (sms.contains("getDate")) {
                    String today = new Date().toString();
                    respond = "Receive date from server : " + today;
                }
                else respond = "Server didn't get the right request!";
                dos.writeUTF(respond);
                dos.flush();
            }
        } catch (Exception e) {
            try {
                dis.close();
                dos.close();
                System.out.println("Disconnected with " + socket);
                socket.close();
            } catch (Exception ex) {
                System.out.println("Closed Server");
            }
        }

    }

}
