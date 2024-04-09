package bai3;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Bai3Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for clients...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected.");

                DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

                String inputLine;
                while ((inputLine = in.readUTF()) != null) {
                    // In ra chuỗi đảo ngược
                    out.writeUTF(new StringBuilder(inputLine).reverse().toString());

                    // Chuỗi in hoa
                    out.writeUTF(inputLine.toUpperCase());

                    // Chuỗi in thường
                    out.writeUTF(inputLine.toLowerCase());

                    // Đổi chữ hoa thành chữ thường và ngược lại
                    out.writeUTF(swapCase(inputLine));

                    // Đếm số từ và số lần xuất hiện của mỗi nguyên âm
                    out.writeInt(countWords(inputLine));
                    countVowels(inputLine, out);
                }

                in.close();
                out.close();
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String swapCase(String input) {
        StringBuilder result = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (Character.isUpperCase(c)) {
                result.append(Character.toLowerCase(c));
            } else if (Character.isLowerCase(c)) {
                result.append(Character.toUpperCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    private static int countWords(String input) {
        String[] words = input.split("\\s+");
        return words.length;
    }

    private static void countVowels(String input, DataOutputStream out) throws IOException {
        String vowels = "aeiouAEIOU";
        int[] counts = new int[5];
        for (char c : input.toCharArray()) {
            if (vowels.contains(String.valueOf(c))) {
                char lowercaseChar = Character.toLowerCase(c);
                switch (lowercaseChar) {
                    case 'a':
                        counts[0]++;
                        break;
                    case 'e':
                        counts[1]++;
                        break;
                    case 'i':
                        counts[2]++;
                        break;
                    case 'o':
                        counts[3]++;
                        break;
                    case 'u':
                        counts[4]++;
                        break;
                }
            }
        }
        for (int count : counts) {
            out.writeInt(count);
        }
    }
}
