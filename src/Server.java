import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        Socket cliSocket = null;

        BufferedReader br = null;

        BufferedWriter bw = null;



        try {
            serverSocket = new ServerSocket(8080);

//            serverSocket.bind(new InetSocketAddress("localhost", 8080));
            System.out.println("클라이언트 접속 준비중");

            cliSocket = serverSocket.accept();
            System.out.println("클라이언트 접속 완료");

            br = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));

            bw = new BufferedWriter(new OutputStreamWriter(cliSocket.getOutputStream()));

            String line = "";
//            line != null || !line.isBlank()

            line = br.readLine();
            System.out.println(line);
            String[] parsedUrl = line.split(" ");
            String method = parsedUrl[0];
            String sub = parsedUrl[1];
            System.out.println(sub);




            while (!(line=br.readLine()).isBlank()){
                System.out.println(line);
            }

            if(method.equals("GET")){
                if(sub.length()>2){

                }else {
                    bw.write("HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            "hello world!\r\n\r\n");
                }
            }

            bw.flush();

            bw.close();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                cliSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("통신 종료");


        }


    }
}
