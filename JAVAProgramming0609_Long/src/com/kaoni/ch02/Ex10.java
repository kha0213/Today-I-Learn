package com.kaoni.ch02;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 원이 겹치는 지 알아보기
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 9.
 * @Version :
 */
public class Ex10 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("첫번째 원의 중심과 반지름을 입력하세요. (띄어쓰기로 구분하세요.) \nex) 10 10 6.5 --> 중심이 (10,10)이고 반지름 6.5");
		String circleStr1 = sc.nextLine();
		System.out.println("두번째 원의 중심과 반지름을 입력하세요. (띄어쓰기로 구분하세요.)");
		String circleStr2 = sc.nextLine();
		
		double[] circleInfo1 = inputCircle(circleStr1);
		double[] circleInfo2 = inputCircle(circleStr2);
		
		
		if(checkPoint(circleInfo1[0], circleInfo1[1], circleInfo1[2], circleInfo2[0], circleInfo2[1], circleInfo2[2])) {
			System.out.println("두 원이 겹칩니다.");
		} else {
			System.out.println("두 원이 겹치지 않습니다.");
		}
		
		sc.close();
		
		}
	
	// String을 원의 중심과 반지름으로 변환
	public static double[] inputCircle(String circleInfo) {
		double[] result = new double[3];
		String[] circleArr = circleInfo.split(" ");
		for(int i=0; i<result.length; i++) {
			result[i] = Double.parseDouble(circleArr[i]);
		}
		return result;
	}
	
	
	// 두 원이 겹치는지 확인하는 메소드
	public static boolean checkPoint(double circle1_X,double circle1_Y, double radius1, double circle2_X, double circle2_Y, double radius2) {
		// 원의 중심 사이의 거리, 반지름 사이 거리
		double pointDistance = Math.sqrt(Math.pow(circle1_X-circle2_X, 2) + Math.pow(circle1_Y-circle2_Y, 2));
		double radiusPlus = radius1+radius2;
		double radiusMinus = Math.abs(radius1-radius2);
		
		// 설명
		System.out.println("중심사이 거리 : "+pointDistance);
		System.out.println("반지름 차 : "+radiusMinus);
		System.out.println("반지름 합 : "+radiusPlus);
		
		if(radiusMinus <= pointDistance && pointDistance <= radiusPlus) {
			return true;
		} else {
			return false;
		}
	}
}
