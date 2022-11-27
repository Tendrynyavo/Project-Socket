package client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8090);
            File file = new File("compile.sh");
            sendFile(file, socket);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void sendFile(File file, Socket socket) throws Exception {
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
        FileInputStream fileInput = new FileInputStream(file);
        int length = (int) file.length();
        int lengthName = file.getName().getBytes().length;
        out.writeInt(lengthName);
        out.write(file.getName().getBytes());
        int count;
        byte[] content = new byte[length];
        while ((count = fileInput.read(content)) > -1)
            out.write(content, 0, count);
        out.close();
        socket.close();
    }
}
