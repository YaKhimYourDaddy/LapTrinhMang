package TCP.UniqueClient.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerChat {
    private int port;
    public ServerChat(int port) {
        this.port = port;
    }

    private void execute() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        ReceivingThread receivingThread = new ReceivingThread(socket);
        receivingThread.start();
        SendingThread sendingThread = new SendingThread(socket, "Server");
        sendingThread.start();
    }

    public static void main(String[] args) throws IOException {
        ServerChat server = new ServerChat(1111);
        server.execute();
    }
}
