package client;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import fichier.Fichier;

public class Client {
    public static void main(String[] args) {
        try {
            Fichier file = new Fichier("compile.sh");
            file.insert(null);
        } catch (Exception e) {
            System.out.println(e);
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
