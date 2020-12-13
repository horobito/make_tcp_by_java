import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {

        ServerSocket serverSocket = null;
        Socket cliSocket = null;

        try{
            // 서버 소켓 생성 및, 포트(8080)와 결합
            serverSocket = new ServerSocket(8080);
            
            // 요청이 들어올 때까지 대기했다가,
            // 클라 요청 시 클라 소켓과 통신할 소켓 반환
            cliSocket = serverSocket.accept();


        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true){
            try {
                // 소켓의 출력 스트림 얻는 것
                OutputStream outputStream = cliSocket.getOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

                dataOutputStream.writeUTF("hello");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
