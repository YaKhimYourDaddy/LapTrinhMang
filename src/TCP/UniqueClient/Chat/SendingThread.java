package TCP.UniqueClient.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendingThread implements Runnable {
    private Thread thread;
    private Socket socket;
    private String sender;
    SendingThread(Socket socket, String sender) {

        this.socket = socket;
        this.sender = sender;
    }

    @Override
    public void run() {
        Scanner scanner = null;
        DataOutputStream dos = null;
        try {
            scanner = new Scanner(System.in);
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String sendString = this.sender + ": " + scanner.nextLine();
                dos.writeUTF(sendString);
                dos.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start() {
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
}
