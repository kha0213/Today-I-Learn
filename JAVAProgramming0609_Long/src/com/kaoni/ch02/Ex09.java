package com.kaoni.ch02;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 원 안의 점인지 판단하기
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 9.
 * @Version :
 */
public class Ex09 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("원의 중심과 반지름을 입력하세요. (띄어쓰기로 구분하세요.) \nex) 10 10 6.5 --> 중심이 (10,10)이고 반지름 6.5");
		String userCircle = sc.nextLine();
		System.out.println("다른 점을 입력하세요. (띄어쓰기로 구분하세요.)");
		String userPoint = sc.nextLine();
		
		// 사용자 입력 값을 원하는 값으로 분리
		String[] circle = userCircle.split(" ");
		String[] point = userPoint.split(" ");

		// 각각의 String을 double로 변환
		double circleX1 = Double.parseDouble(circle[0]);
		double circleY1 = Double.parseDouble(circle[1]);
		double radius = Double.parseDouble(circle[2]);
		double pointX1 = Double.parseDouble(point[0]);
		double pointY1 = Double.parseDouble(point[1]);
		
		if (checkPoint(pointX1, pointY1, circleX1, circleY1, radius)) {
			System.out.println("점이 원 내부에 있습니다.");
		} else {
			System.out.println("점이 원 외부에 있습니다.");
		}
		
		sc.close();
		
		}
	
	public static boolean checkPoint(double pointX1,double pointY1, double circleX1, double circleY1, double radius) {
		
		System.out.println("반지름 제곱 : " + Math.pow(radius, 2));
		System.out.println("중심사이의 거리 제곱 : " + (Math.pow(pointX1-circleX1, 2) + Math.pow(pointY1-circleY1, 2)));
		
		return Math.pow(radius, 2) > Math.pow(pointX1-circleX1, 2) + Math.pow(pointY1-circleY1, 2);
	}
}
