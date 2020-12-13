import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket c_Socket = null;

        try{
            serverSocket = new ServerSocket(8080);
            System.out.println("클라이언트 접속 준비 완료");

            c_Socket = serverSocket.accept();
            System.out.println("클라이언트 접속 완료");
            System.out.println("client : " + c_Socket);



        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(c_Socket !=null){
                    c_Socket.close();
                }

                if(serverSocket!=null){
                    serverSocket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
