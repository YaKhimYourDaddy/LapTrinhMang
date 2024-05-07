package TCP.MultiClient.String;

import MyHelpingLibrary.StringResponder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerString {
    private int serverPort;
    public static ArrayList<Socket> listSocket;
    public ServerString(int serverPort) { this.serverPort = serverPort; }

    private void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(serverPort);
        System.out.println("Server is listening...");
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Connected with " + socket);
            this.listSocket.add(socket);
            StringClientHandler clientHandler = new StringClientHandler(socket);
            clientHandler.start();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerString.listSocket = new ArrayList<>();
        ServerString server = new ServerString(1111);
        server.execute();
    }
}

class StringClientHandler implements Runnable {
    private Socket socket;
    private Thread thread;

    public StringClientHandler(Socket socket) {
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
                String respond = StringResponder.getRespond(sms);
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
