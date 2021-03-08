import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 2021-03-03
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class MyClient {
    public static void main(String[] args) {
        Socket socket = null;            //Server와 통신하기 위한 Socket
        BufferedReader serverReader = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
        BufferedReader keyboardReader = null;        //키보드로부터 읽어들이기 위한 입력스트림
        BufferedWriter bw = null;            //서버로 내보내기 위한 출력 스트림
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();    //서버로 접속
            socket = new Socket(address,12345);
            serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            keyboardReader = new BufferedReader(new InputStreamReader(System.in));
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.println(socket.toString());
            System.out.print("서버로 보낼 메세지 : ");
            String data = keyboardReader.readLine();            //키보드로부터 입력
            bw.write(data);                        //서버로 데이터 전송
            bw.flush();

            String str2 = serverReader.readLine();            //서버로부터 되돌아오는 데이터 읽어들임
            System.out.println("서버로부터 되돌아온 메세지 : " + str2);
            socket.close();
        }catch(IOException e) {
            System.out.println("IOException : " + e.getMessage());
        }

    }
}
