package com.kaoni.ch09;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressEx {
	public static void main(String[] args) {
		try {
			InetAddress localhost = InetAddress.getLocalHost();
			System.out.println("[local]getHostAddress : "+localhost.getHostAddress());
			System.out.println("[local]getHostName : "+localhost.getHostName());
			
			InetAddress[] naverInet = InetAddress.getAllByName("www.naver.com");
			for(InetAddress naver : naverInet) {
				System.out.println("네이버 IP주소 : " + naver.getHostAddress());
				System.out.println("네이버 HostName : " + naver.getHostName());
			}
			
			InetAddress[] daumInet = InetAddress.getAllByName("www.daum.net");
			for(InetAddress daum : daumInet) {
				System.out.println("다음 IP주소 : " + daum.getHostAddress());
				System.out.println("다음 HostName : " + daum.getHostName());
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
}
