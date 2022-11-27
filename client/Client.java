package client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            File file = new File("./Client.java");
            sendFile(file, new Socket("localhost", 8090));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void sendFile(File file, Socket socket) throws Exception {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        sendNameFile(file, out);
        sendFileContent(file, out);
        out.close();
        socket.close();
    }

    public static void sendNameFile(File file, OutputStream out) throws Exception {
        int lengthName = file.getName().getBytes().length;
        ((DataOutputStream) out).writeInt(lengthName);
        out.write(file.getName().getBytes());
    }
    
    public static void sendFileContent(File file, OutputStream out) throws Exception {
        FileInputStream input = new FileInputStream(file);
        byte[] content = new byte[(int) file.length()];
        input.read(content);
        out.write(content);
        input.close();
    }
}