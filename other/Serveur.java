package other;

import java.io.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8091);
            Socket client = server.accept();
            DataInputStream input = new DataInputStream(client.getInputStream());
            setFile(input);
            server.close();
        } catch (Exception e) {
            System.out.println(e);
        }   
    }

    public static void setFile(InputStream input) throws Exception {
        String name = getName(input);
        FileOutputStream out = new FileOutputStream(new File("./other/" + name + "part " + 1));
        byte[] content = new byte[4675];
        int count = input.read(content);
        out.write(content, 0, count);
    }

    public static String getName(InputStream input) throws Exception {
        int lengthName = ((DataInputStream) input).readInt();
        byte[] b = new byte[lengthName];
        ((DataInputStream) input).readFully(b, 0, lengthName);
        return new String(b);
    }
}
