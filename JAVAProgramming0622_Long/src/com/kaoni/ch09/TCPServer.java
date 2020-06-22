package com.kaoni.ch09;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * 
 * 
 *
 * desc 	: server 프로그램 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 22.
 * @Version : 1.0.0
 */
public class TCPServer {
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(TCP_Cons.PORTNUM);
//			serverSocket = new ServerSocket();
//			serverSocket.bind(new InetSocketAddress("localhost", TCP_Cons.PORTNUM));

			while (true) {
				System.out.println("[Server] 연결 기다림");
				Socket socket = serverSocket.accept(); // 클라이언트 연결 수락
				InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
				System.out.println("[Server] 연결 수락함 " + isa.getHostName());

				byte[] bytes = null;
				String message = null;

				InputStream is = socket.getInputStream();
				bytes = new byte[TCP_Cons.READ_BY_COUNT];
				int readByteCount = is.read(bytes);
				message = new String(bytes, 0, readByteCount, "UTF-8");
				System.out.println("[데이터 받기]: " + message);
				
				OutputStream os = socket.getOutputStream();
				message = "Hello Client";
				bytes = message.getBytes("UTF-8");
				os.write(bytes);
				os.flush();
				System.out.println("[Server] 데이터 보내기 성공");
				
				is.close();
				os.close();
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
}
