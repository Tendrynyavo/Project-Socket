package serveur;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import fichier.Fichier;

public class Upload {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8090);
            Socket client = server.accept();
            getFileToServer(client);
            server.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void getFileToServer(Socket client) throws Exception {
        DataInputStream input = new DataInputStream(client.getInputStream());
        sendFile(input);
        input.close();
    }

    public static File getFile(InputStream input) throws Exception {
        int lengthName = ((DataInputStream) input).readInt();
        byte[] b = new byte[lengthName];
        ((DataInputStream) input).readFully(b, 0, lengthName);
        String name = new String(b);
        Fichier file = new Fichier("./serveur/" + name);
        file.save();
        return file;
    }

    public static void sendFile(InputStream input) throws Exception {
        Fichier file = (Fichier) getFile(input);
        byte[] content = new byte[4678];
        int count = input.read(content);
        int divide = count / 3;
        int off = 0;
        DataOutputStream out = null;
        Socket socket = null;
        for (int increment = 1; increment <= 3; increment++, off += divide) {
            socket = new Socket("localhost", 8090 + increment);
            out = new DataOutputStream(socket.getOutputStream());
            out.writeInt(file.getIdFile().getBytes().length);
            out.write(file.getIdFile().getBytes());
            if (increment == 3) divide += count - off - divide;
            out.write(content, off, divide);
            out.close();
            socket.close();
        }
    }
}