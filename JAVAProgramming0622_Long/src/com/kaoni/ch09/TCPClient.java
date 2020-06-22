package com.kaoni.ch09;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;
/**
 * 
 * 
 *
 * desc 	: 클라이언트 프로그램
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 22.
 * @Version : 1.0.0
 */
public class TCPClient {
	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket();
			System.out.println("[Client] 연결 요청");
			socket.connect(new InetSocketAddress("localhost", TCP_Cons.PORTNUM));
			System.out.println("[Client] 연결 성공");
			
			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();
			sendMessage(os); //메세지 보내기
			readMessage(is); //메세지 받기

			if (os != null) {
				os.close();
			}
			if (is != null) {
				is.close();
			}
		} catch (ConnectException e) {
			System.out.println("[ConnectException] : 서버가 닫혀 있어요");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (socket != null) {
					socket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void sendMessage(OutputStream os) throws IOException {
		System.out.print(">>");
		String message = "Hello Server";
		byte[] bytes = message.getBytes("UTF-8");
		os.write(bytes);
		os.flush();
		System.out.println("[Client] 데이터 보내기 성공");
	}
	
	private static void readMessage(InputStream is) throws IOException {
		byte[] bytes = null;
		bytes = new byte[TCP_Cons.READ_BY_COUNT];
		while(true) {
			int readByteCount = is.read(bytes);
			if(readByteCount == -1) {
				break;
			}
			String message = new String(bytes, 0, readByteCount, "UTF-8");
			System.out.println("[데이터 받기]: " + message);
		}
	}

}
