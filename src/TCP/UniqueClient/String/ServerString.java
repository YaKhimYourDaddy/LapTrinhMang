package TCP.UniqueClient.String;

import MyHelpingLibrary.StringResponder;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerString {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2222);
        System.out.println("Server is started.");

        while (true) {
            Socket socket = serverSocket.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String receiveData;
            receiveData = dis.readUTF();
            System.out.println("Receive from Client: " + receiveData);

            String respond = StringResponder.getRespond(receiveData);
            dos.writeUTF(respond);
            dos.flush();
        }
//        socket.close();
    }
}
