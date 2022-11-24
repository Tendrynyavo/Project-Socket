package serveur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Serveur {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8090);
            Socket client = serverSocket.accept();
            InputStream input = client.getInputStream();
            byte[] b = new byte[5000];
            int nbr = input.read(b);
            File file = new File("./compile.sh");
            FileOutputStream outFile =  new FileOutputStream(file);
            outFile.write(b);
            serverSocket.close();
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}