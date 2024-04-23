package TCP.MultiClient.Calculate;

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
        while (true) {
            Socket socket = 
        }

    }


}

class ServerCalculateHandlingMultiClients implements Runnable {
    private Socket socket;
    private Thread thread;
}
