package TCP.UniqueClient.Calculate;

import MyHelpingLibrary.ExpressionEvaluator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerCalculate {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        System.out.println("Server is started.");

        while (true) {
            Socket socket = serverSocket.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String receiveData;
            receiveData = dis.readUTF();
            System.out.println("Receive math expression from Client: " + receiveData);

            String respond = Double.toString(ExpressionEvaluator.evaluate(receiveData));
            dos.writeUTF(respond);
            dos.flush();
        }
//        socket.close();
    }
}
