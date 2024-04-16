package GetDate;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientGetDate {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1111);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        String sendData = "getDate";
        dos.writeUTF(sendData);
        dos.flush(); // Ensure data is sent immediately

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String receiveData = dis.readUTF();
        System.out.println("Receive from Server: " + receiveData);
        socket.close();
    }
}
