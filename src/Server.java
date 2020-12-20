import java.io.*;
import java.net.*;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;

        Socket cliSocket = null;

        BufferedReader br = null;

        BufferedWriter bw = null;



        try {
            int port = 8080;
            serverSocket = new ServerSocket(port);

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
            String url =  parsedUrl[1].substring(1, parsedUrl[1].length());
            System.out.println(url);
            String directory = url.split("\\?")[0];
            System.out.println(directory);


            System.out.println(method);
            System.out.println();

            while (!(line=br.readLine()).isBlank()){
                System.out.println(line);
            }

            String[] query = new URL("http://localhost:"+port+"/"+url).getQuery().split("&");
            for (String a : query){
            }

            HashMap<String, Integer> parameter = new HashMap<>();

            if(method.equals("POST")){

                if(directory.equals("calculator")){
                    System.out.println("hi i'm calculator");
                    int answer = 1;
                    for (String param : query){
                        String name = param.split("=")[0];
                        int value = Integer.parseInt(param.split("=")[1]);
                        answer *= value;
                    }
                    bw.write("HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            answer + "\r\n\r\n");
                }

            }

;


            if(method.equals("GET")){

                System.out.println(url);
                if(url.length()>1){
                    String name = url.split("=")[1];
                    bw.write("HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            "hello " + name + "\r\n\r\n");

                }else {
                    bw.write("HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html\r\n\r\n" +
                            "black coffee!\r\n\r\n");
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
