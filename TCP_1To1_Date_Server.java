
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCP_1To1_Date_Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(1111);
        System.out.println("Server is started.");

        while (true) {
            Socket socket = serverSocket.accept();

            DataInputStream dis = new DataInputStream(socket.getInputStream());
            String receiveData = dis.readUTF();
            System.out.println("Message from Client: " + receiveData);

            String sendData;
            if (receiveData.trim().equals("getDate"))
                sendData = new Date().toString();
            else sendData = "Server didn't get the right request!";

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            dos.writeUTF(sendData);
            dos.flush(); // Ensure data is sent immediately

            socket.close();
        }
    }
}
