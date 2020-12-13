import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args) throws IOException {

        Socket socket = null;

        OutputStream outputStream = null;

        DataOutputStream dataOutputStream = null;;

        // 소켓의 입력스트림을 얻는 과정
        InputStream inputStream = null;;
        DataInputStream dataInputStream = null;;
        Scanner sc = new Scanner(System.in);

        try {
            // 소켓을 생성 및 연결 요청, 이때 연결할 서버의 ip와 포트번호 입력
            socket = new Socket("localhost", 8080);
            System.out.println("클라이언트 접속 성공");

            outputStream = socket.getOutputStream();
             dataOutputStream = new DataOutputStream(outputStream);

            // 소켓의 입력스트림을 얻는 과정
            inputStream = socket.getInputStream();
            dataInputStream = new DataInputStream(inputStream);



            while (true){
                String inputByClient = sc.nextLine();

                dataOutputStream.writeUTF(inputByClient);
                dataOutputStream.flush();



                System.out.println("서버 응답 : " + dataInputStream.readUTF());

                if(inputByClient.equals("closeAndFinish")){
                    System.out.println("통신 종료 시작");
                    break;
                }


            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(dataOutputStream !=null){ dataOutputStream.close();}
            if(outputStream !=null){outputStream.close();}
            if(dataInputStream !=null){dataInputStream.close();}
            if (inputStream !=null){inputStream.close();}
            socket.close();
            System.out.println("통신 종료");
        }
    }
}
