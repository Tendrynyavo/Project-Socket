package serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8090);
            Socket client = server.accept();
            setFile(client);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setFile(Socket client) throws Exception {
        DataInputStream input = new DataInputStream(client.getInputStream());
        int lengthName = input.readInt();
        byte[] b = new byte[lengthName];
        input.readFully(b, 0, lengthName);
        String name = new String(b);
        byte[] content = new byte[4069];
        int divide = input.read(content) / 3;
        System.out.println(divide);
        int count = divide;
        DataOutputStream out = new DataOutputStream(new Socket("localhost",8090).getOutputStream());
        out.write(content, 0, count);
        System.out.println(input.read(content));
//        DataOutputStream out = null;
//        for (int increment = 0;  increment < 3; increment++) {
//            out = new DataOutputStream(new Socket("localhost",8090 + increment).getOutputStream());
//            out.write(content, 0, count);
//            count += divide;
//        }
//        while ((count = input.read(content)) > -1)
//            out.write(content, 0, count);
    }
}