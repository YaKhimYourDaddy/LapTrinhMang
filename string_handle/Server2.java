package string_handle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 {
    public String getRespond(String originalString) {
        StringBuilder result = new StringBuilder();
        result.append("Reversed string: ").append(reverseString(originalString)).append("\n");
        result.append("Uppercase string: ").append(uppercaseString(originalString)).append("\n");
        result.append("Lowercase string: ").append(lowercaseString(originalString)).append("\n");
        result.append("Toggled case string: ").append(toggleCaseString(originalString)).append("\n");
        result.append("Word count: ").append(countWords(originalString)).append("\n");
        result.append("Vowel count: ").append(countVowels(originalString));

    }
    private String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private String uppercaseString(String str) {
        return str.toUpperCase();
    }

    private String lowercaseString(String str) {
        return str.toLowerCase();
    }

    private String toggleCaseString(String str) {
        StringBuilder toggled = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (Character.isUpperCase(c)) {
                toggled.append(Character.toLowerCase(c));
            } else {
                toggled.append(Character.toUpperCase(c));
            }
        }
        return toggled.toString();
    }

    private int countWords(String str) {
            /*
            int wordCount = 0;
            boolean inWord = false;

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);

                if (!Character.isWhitespace(c) && !inWord) {
                    wordCount++;
                    inWord = true;
                }

                if (Character.isWhitespace(c)) {
                    inWord = false;
                }
            }

            return wordCount;
             */

            /*
            line.split("\\s+"):
            This part of the line is splitting the string stored in the variable line
            into an array of substrings based on the regular expression "\s+".
            The "\s+" regular expression means "one or more whitespace characters",
            where "\s" matches any whitespace character (space, tab, newline, etc.)
            and "+" specifies one or more occurrences.
            Therefore, the split("\\s+") method separates the string into substrings
             it encounters one or more whitespace characters.
             */
        return str.split("\\s+").length;
    }

    private void countVowels(String str, int[] countVowels){
        String vowels = "aeiouAEIOU";
        for (char c : str.toCharArray()) {
            if (vowels.contains(String.valueOf(c))) {
                char lowercaseChar = Character.toLowerCase(c);
                switch (lowercaseChar) {
                    case 'a':
                        countVowels[0]++;
                        break;
                    case 'e':
                        countVowels[1]++;
                        break;
                    case 'i':
                        countVowels[2]++;
                        break;
                    case 'o':
                        countVowels[3]++;
                        break;
                    case 'u':
                        countVowels[4]++;
                        break;
                }
            }
        }
        for (int count : counts) {
            out.writeInt(count);
        }
    }
}

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1111);
        System.out.println("Server is started.");

        while (true) {
            Socket socket = serverSocket.accept();
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());

            String receiveData;
            while ((receiveData = dis.readUTF()) != null) {
                System.out.println("Receive from Client: " + receiveData);


                dos.writeUTF(result.toString());
                dos.flush();
            }

            String sendData;
            else sendData = "Server didn't get the right request!";
            dos.writeUTF(sendData);

            socket.close();
        }
    }
}
