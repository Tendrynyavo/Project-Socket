import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8090);
            OutputStream out = socket.getOutputStream();
            String msg = "Aina avotra";
            out.write(msg.getBytes());
            out.close();
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
