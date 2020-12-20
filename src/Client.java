import java.io.*;
import java.lang.reflect.Parameter;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = null;

        OutputStream outputStream = null;

        // 소켓의 입력스트림을 얻는 과정
        InputStream inputStream = null;;

        Scanner sc = new Scanner(System.in);

        try {
            // 소켓을 생성 및 연결 요청, 이때 연결할 서버의 ip와 포트번호 입력
            socket = new Socket("localhost", 8080);
            System.out.println(socket.getInetAddress());
            System.out.println("클라이언트 접속 성공");


            outputStream = socket.getOutputStream();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));

            // 소켓의 입력스트림을 얻는 과정
            inputStream = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));

            bw.write("POST /calculator?a=3&b=4 HTTP/1.1\r\n" +
                    "Content-Type: text/html\r\n\r\n" +
                    "a=3&b=4 \r\n\r\n");



            String answer = "";
            while (!(answer=br.readLine()).isBlank()){
                System.out.println(answer);
            }

            bw.flush();
            bw.close();



        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream !=null){outputStream.close();}
            if (inputStream !=null){inputStream.close();}
            socket.close();
            System.out.println("통신 종료");
        }
    }
}
