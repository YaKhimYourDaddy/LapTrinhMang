
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_1To1_String_Server {
    public static String getRespond(String originalString) {
        StringBuilder result = new StringBuilder();
        result.append("Reversed string: ").append(reverseString(originalString)).append("\n");
        result.append("Uppercase string: ").append(uppercaseString(originalString)).append("\n");
        result.append("Lowercase string: ").append(lowercaseString(originalString)).append("\n");
        result.append("Toggled case string: ").append(toggleCaseString(originalString)).append("\n");
        result.append("Word count: ").append(countWords(originalString)).append("\n");
        int[] countVowels = {0, 0, 0, 0, 0};
        countVowels(originalString, countVowels);
        result.append("Vowel 'a' count: ").append(Integer.toString(countVowels[0])).append("\n");
        result.append("Vowel 'e' count: ").append(Integer.toString(countVowels[1])).append("\n");
        result.append("Vowel 'i' count: ").append(Integer.toString(countVowels[2])).append("\n");
        result.append("Vowel 'o' count: ").append(Integer.toString(countVowels[3])).append("\n");
        result.append("Vowel 'u' count: ").append(Integer.toString(countVowels[4])).append("\n");
        return result.toString();
    }

    private static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    private static String uppercaseString(String str) {
        return str.toUpperCase();
    }

    private static String lowercaseString(String str) {
        return str.toLowerCase();
    }

    private static String toggleCaseString(String str) {
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

    private static int countWords(String str) {
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

    private static void countVowels(String str, int[] countVowels){
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
    }

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

            String respond = getRespond(receiveData);
            dos.writeUTF(respond);
            dos.flush();
        }
//        socket.close();
    }
}
