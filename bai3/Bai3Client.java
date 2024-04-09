package bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Bai3Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter a string:");
            String userInputLine = scanner.nextLine();
            out.writeUTF(userInputLine);

            // Đọc và in ra kết quả từ Server
            String serverResponse;
            while ((serverResponse = in.readUTF()) != null) {
                System.out.println("Server response:");
                System.out.println("Reversed string: " + serverResponse);
                System.out.println("Uppercase string: " + in.readUTF());
                System.out.println("Lowercase string: " + in.readUTF());
                System.out.println("Swap case string: " + in.readUTF());
                System.out.println("Word count: " + in.readInt());
                System.out.println("Vowel counts:");
                for (int i = 0; i < 5; i++) {
                    System.out.println((char) ('a' + i) + ": " + in.readInt());
                }
                break;
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
