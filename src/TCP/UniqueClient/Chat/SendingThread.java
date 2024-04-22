package TCP.UniqueClient.Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class SendingThread implements Runnable {
    private Thread thread;
    private Socket socket;
    private String senderName;
    SendingThread(Socket socket, String senderName) {

        this.socket = socket;
        this.senderName = senderName;
    }

    @Override
    public void run() {
        Scanner scanner = null;
        DataOutputStream dos = null;
        try {
            scanner = new Scanner(System.in);
            dos = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String sendString = this.senderName + ": " + scanner.nextLine();
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
