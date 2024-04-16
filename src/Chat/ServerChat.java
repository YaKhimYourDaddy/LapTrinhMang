package Chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class ServerChat {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1111);
        System.out.println("Server is started.");

        while (true) {
            Socket socket = serverSocket.accept();
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            String sendData, receiveData;
            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Check if data is available from server
                if (dis.available() > 0) {
                    receiveData = dis.readUTF();
                    System.out.println("Client: " + receiveData);
                }

                if (System.in.available() > 0) {
                    sendData = scanner.nextLine();
                    dos.writeUTF(sendData);
                    dos.flush();
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
