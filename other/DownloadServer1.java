package other;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class DownloadServer1 {
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(8092);
            Socket client = server.accept();
            DataInputStream input = new DataInputStream(client.getInputStream());
            byte[] buff = new byte[input.readInt()];
            input.read(buff);
            String name = new String(buff);
            FileInputStream file = new FileInputStream(new File("./other/" + name + " part 2"));
            byte[] content = new byte[4574];
            file.read(content);
            Socket mainServer = new Socket("locahost", 8089);
            DataOutputStream out = new DataOutputStream(mainServer.getOutputStream());
            out.write(content);
            server.close();
            mainServer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
}
