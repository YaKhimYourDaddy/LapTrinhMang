package string_handle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2222);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string: ");
        String sendData = scanner.nextLine();
        dos.writeUTF(sendData);
        dos.flush(); // Ensure data is sent immediately

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String receiveData;
        while ((receiveData = dis.readUTF()) != null) {
            StringBuilder respond;
        }
        System.out.println("Receive from Server: " + receiveData);
        socket.close();
    }
}
