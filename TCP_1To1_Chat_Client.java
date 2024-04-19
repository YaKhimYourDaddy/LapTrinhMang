import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TCP_1To1_Chat_Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1111);
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        String sendData, receiveData;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Check if data is available from server
            if (dis.available() > 0) {
                receiveData = dis.readUTF();
                System.out.println("Server: " + receiveData);
            }

            // Check if user input is available
            if (System.in.available() > 0) {
                sendData = scanner.nextLine();
                dos.writeUTF(sendData);
                dos.flush();
            }

            // Reduce too many unnecessary while loop while waiting for text
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
//        socket.close();

    }
}
