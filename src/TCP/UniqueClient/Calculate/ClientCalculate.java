package TCP.UniqueClient.Calculate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ClientCalculate {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 2222);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a math expression: ");
        String sendData = scanner.nextLine();
        scanner.close();

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(sendData);
        dos.flush(); // Ensure data is sent immediately

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String receiveData = dis.readUTF();
        System.out.println("Receive result from Server: " + receiveData);
        socket.close();
    }
}
