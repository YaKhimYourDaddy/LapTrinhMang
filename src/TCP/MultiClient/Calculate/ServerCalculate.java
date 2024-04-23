package TCP.MultiClient.Calculate;

import MyHelpingLibrary.ExpressionEvaluator;
import TCP.MultiClient.ChatRoom.ServerChatRoom;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCalculate {
    private int serverPort;
    public static ArrayList<Socket> listSocket;
    public ServerCalculate(int serverPort) { this.serverPort = serverPort; }

    private void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        System.out.println("Server is listening...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connected with " + socket);
            this.listSocket.add(socket);
            CalculateClientHandler clientHandler = new CalculateClientHandler(socket);
            clientHandler.start();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerCalculate.listSocket = new ArrayList<>();
        ServerCalculate server = new ServerCalculate(1111);
        server.execute();
    }
}

class CalculateClientHandler implements Runnable {
    private Socket socket;
    private Thread thread;

    public CalculateClientHandler(Socket socket) {
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
                if(sms.contains("exit")) {
                    ServerChatRoom.listSocket.remove(socket);
                    System.out.println("Disconnected with " + socket);
                    dis.close();
                    dos.close();
                    socket.close();
                    continue; // already disconnected
                }
                String respond = "Result of Expression 0 : " + Double.toString(ExpressionEvaluator.evaluate(sms));
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
