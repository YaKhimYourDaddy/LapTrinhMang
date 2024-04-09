package bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Bai2Client {
    public static void main(String[] args) throws Exception{
        Socket sk = new Socket("localhost", 7000);
        DataInputStream dis = new DataInputStream(sk.getInputStream());
        DataOutputStream dos = new DataOutputStream(sk.getOutputStream());
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Client: ");
            String message = sc.nextLine();
            dos.writeUTF("Client: " + message);
            dos.flush();
            String str = dis.readUTF();
            System.out.println(str);
            sc = sc.reset();
        }
    }
}
