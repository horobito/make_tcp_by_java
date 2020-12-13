import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {

        Socket clientSocket = null;

        OutputStream outputStream = null;


        try {
            clientSocket = new Socket("localhost", 8080);
            System.out.println("서버 연결");
            System.out.println("socket : " + clientSocket);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
