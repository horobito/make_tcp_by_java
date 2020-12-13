import java.io.*;
import java.net.Socket;


public class Client {
    public static void main(String[] args) {

        Socket socket = null;

        OutputStream outputStream = null;


        try {
            // 소켓을 생성 및 연결 요청, 이때 연결할 서버의 ip와 포트번호 입력
            socket = new Socket("localhost", 8080);

            OutputStream outputStream1 = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // 소켓의 입력스트림을 얻는 과정
            InputStream inputStream = socket.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            System.out.println(dataInputStream.readUTF());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
