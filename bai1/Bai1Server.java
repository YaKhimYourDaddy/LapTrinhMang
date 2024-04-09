package bai1;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Bai1Server {

    public static void main(String[] args) throws Exception {
        ServerSocket svsk = new ServerSocket(7766);
        System.out.println("Server is started");
        while(true) {
            Socket s = svsk.accept();
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            String time = new Date().toString();
            dos.writeUTF("Server return date time: " + time);
            s.close();
        }
    }
}
