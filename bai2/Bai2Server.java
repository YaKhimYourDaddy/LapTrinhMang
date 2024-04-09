package bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Bai2Server {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(7000);
        System.out.println("Server is started");
        Socket sk = ss.accept();
        DataInputStream dis = new DataInputStream(sk.getInputStream());
        DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
        Scanner sc = new Scanner(System.in);
        while(true) {
            String str = dis.readUTF();
            System.out.println(str);
            System.out.println("Server: ");
            String message = sc.nextLine();
            dos.writeUTF("Server: " + message);
            dos.flush();
            sc = sc.reset();
        }
    }
}
