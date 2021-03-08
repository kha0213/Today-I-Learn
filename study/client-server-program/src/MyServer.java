import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 2021-03-03
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        try (
            ServerSocket serverSocket = new ServerSocket(12345);
            Socket socket = serverSocket.accept();
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        ) {
            String readMsg = br.readLine();

            System.out.println("client : " + readMsg);

            bw.write(readMsg);
            bw.flush();
        } catch (IOException e) {
            System.out.println("IOException : " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
    }
}
