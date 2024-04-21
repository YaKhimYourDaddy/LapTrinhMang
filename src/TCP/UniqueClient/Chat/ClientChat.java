package TCP.UniqueClient.Chat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class ClientChat {
    private int port;
    private InetAddress host;
    public ClientChat(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    private void execute() throws IOException {
        Socket socket = new Socket(host, port);
        ReceivingThread receivingThread = new ReceivingThread(socket);
        receivingThread.start();
        SendingThread sendingThread = new SendingThread(socket, "Client");
        sendingThread.start();
    }

    public static void main(String[] args) throws IOException {
        ClientChat client = new ClientChat(InetAddress.getLocalHost(), 1111);
        client.execute();
    }
}
