package com.kaoni.ch04;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 사각형 다시
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 10.
 * @Version : 1.0.0
 */
public class Ex02Rectangle {
	public static void main(String[] args) {
		checkRectanglesOverlap();
	}
	
	// 사각형 겹치는 지 확인하는 프로그램
	public static void checkRectanglesOverlap() {
		System.out.println("두 점을 입력하세요, (100,100), (200,200) 으로 이루어 진 직사각형과 충돌하는지 판별합니다.\n"
				+ " 띄어쓰기로 구분합니다. ex) 150 110 120 130  --> (150,110), (120,130)으로 이루어진 직사각형");
		// point는 직사각형의 좌상단 점 좌표 dimension은 직사각형의 너비와 높이
		Rectangle comRect = new Rectangle(new Point(100, 200), new Dimension(100, 100));
		String userStr = userInputString(); // 유저에게 String받고
		Rectangle userRect = userRectangle((userStr)); // 유저 사각형
		printOverlap(userRect, comRect); //겹치는지 확인
	}
	
	
	
	// 유저 String 정보 입력받기
	public static String userInputString() {
		Scanner sc = new Scanner(System.in);
		String userStr = sc.nextLine();
		sc.close();
		return userStr;
	}
	
	
	
	// 유저 입력한 String을 int[]로 변환
	public static int[] stringToIntR(String userStr) {
		String[] tempStr = userStr.split(" ");
		int[] userInt = new int[4];
		for(int i=0; i < userInt.length; i++) {
			userInt[i] = Integer.parseInt(tempStr[i]);
		}
		return userInt;
	}
	
	
	// 유저가 입력한 정보로 사각형 만들기
	public static Rectangle userRectangle(String userStr) {
		// String을 int[]로 변환
		int[] userInt = stringToIntR(userStr);
		
		// x, y좌표는 사각형의 좌상단 꼭짓점
		int pointX = Math.min(userInt[0], userInt[2]);
		int pointY = Math.max(userInt[1], userInt[3]);
		int width = Math.abs(userInt[0] - userInt[2]);
		int height = Math.abs(userInt[1] - userInt[3]);
		
		return new Rectangle(new Point(pointX, pointY), new Dimension(width, height));
	}
	
	
	// 두 사각형 정보와 겹치는지 확인
	public static void printOverlap(Rectangle userRect, Rectangle comRect) {
		System.out.println("유저 사각형 : " + userRect);
		System.out.println(userRect.getLocation());
		System.out.println("컴퓨터 사각형 : " + comRect);
		System.out.println(comRect.getLocation());
		
		// 영역이 겹침 여부 (주어진 메소드)
		boolean checklap = comRect.intersects(userRect);
		System.out.println(checklap?"영역이 겹칩니다.":"영역이 안 겹칩니다.");
		
		// 사각형 스윙보이기
		Ex02ViewRectangle view = new Ex02ViewRectangle(userRect, comRect);
	}
	
}
