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
            input.read(b);
            String msg = new String(b);
            System.out.println(msg);
            serverSocket.close();
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}