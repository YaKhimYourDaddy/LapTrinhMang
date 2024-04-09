package get_date;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client1 {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 1111);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        DataInputStream dis = new DataInputStream(socket.getInputStream());

        String sendData = "getDate";
        dos.writeUTF(sendData);
        dos.flush(); // Ensure data is sent immediately

        String receiveData = dis.readUTF();
        System.out.println("Receive from Server: " + receiveData);
        socket.close();
    }
}
