package bai1;

import java.io.DataInputStream;
import java.net.Socket;

public class Bai1Client {
    public static void main(String[] args) throws Exception{
        Socket sk = new Socket("localhost", 7766);
        DataInputStream dis = new DataInputStream(sk.getInputStream());
        String time = dis.readUTF();
        System.out.println(time);
        sk.close();
    }
}

