import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = null;
        Socket cliSocket = null;

        InputStream inputStream = null;
        DataInputStream dataInputStream = null;

        OutputStream outputStream = null;
        DataOutputStream dataOutputStream = null;



        try{
            // 서버 소켓 생성 및, 포트(8080)와 결합
            serverSocket = new ServerSocket(8080);
            System.out.println("클라이언트 접속 대기중 ");
            
            // 요청이 들어올 때까지 대기했다가,
            // 클라 요청 시 클라 소켓과 통신할 소켓 반환
            cliSocket = serverSocket.accept();


            inputStream = cliSocket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);

            // 소켓의 출력 스트림 얻는 것
            outputStream = cliSocket.getOutputStream();
            dataOutputStream = new DataOutputStream(outputStream);



            while (true){
                try {


                    String inputData = dataInputStream.readUTF();

                    if(inputData.equals("closeAndFinish")){
                        dataOutputStream.writeUTF("Your inputString is " + inputData + "\n"
                                + "and my answer is 'ByeBye'");
                        break;
                    }
                    dataOutputStream.writeUTF("Your inputString is " + inputData + "\n"
                            + "and my answer is 'hello'");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(dataOutputStream !=null){ dataOutputStream.close();}
            if(outputStream !=null){outputStream.close();}
            if(dataInputStream !=null){dataInputStream.close();}
            if (inputStream !=null){inputStream.close();}
            cliSocket.close();
            serverSocket.close();

        }


    }
}
