package TCP.UniqueClient.Chat;

import java.io.DataInputStream;
import java.net.Socket;

public class ReceivingThread implements Runnable{
    private Thread thread;
    private Socket socket;
    ReceivingThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(socket.getInputStream());
            while (true) {
                String receiveString = dis.readUTF();
                System.out.println(receiveString);
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
