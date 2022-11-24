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
            OutputStream out = socket.getOutputStream();
            File file = new File("compile.sh");
            FileInputStream fileInput = new FileInputStream(file);
            byte[] content = new byte[(int) file.length()];
            int number = fileInput.read(content);
            out.write(content);
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
