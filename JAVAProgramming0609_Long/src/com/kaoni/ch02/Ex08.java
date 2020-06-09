package com.kaoni.ch02;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 직사각형 충돌인지 알아보기
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 9.
 * @Version :
 */

public class Ex08 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("두 점을 입력하세요, (100,100), (200,200) 으로 이루어 진 직사각형과 충돌하는지 판별합니다.\n 숫자만 입력하세요");
		// 사용자 입력 변수
		int userX1 = 0;
		int userY1 = 0;
		int userX2 = 0;
		int userY2 = 0;

		// 입력한 값이 숫자인지 판단하는 변수
		boolean inputNumber = true;

		try {
			System.out.println("첫 번째 점 X좌표");
			userX1 = Integer.parseInt(sc.next());
			System.out.println("첫 번째 점 Y좌표");
			userY1 = Integer.parseInt(sc.next());
			System.out.println("두 번째 점 X좌표");
			userX2 = Integer.parseInt(sc.next());
			System.out.println("두 번째 점 Y좌표");
			userY2 = Integer.parseInt(sc.next());
		} catch (Exception e) {
			System.out.println("숫자만 입력하세요.");
			inputNumber = false;
		}

		sc.close();

		if (inputNumber) {
			int cnt = 0;
			if (inRect(userX1, userY1, 100, 100, 200, 200)) {
				cnt++;
			}
			if (inRect(userX2, userY2, 100, 100, 200, 200)) {
				cnt++;
			}
			System.out.println(cnt > 0 ? "충돌입니다." : "충돌이 아닙니다.");
		}
	}

	// 점 x, y가 직사각형 (rectx1, recty1), (rectx2, recty2) 겹치는지 판단하는 메소드
	public static boolean overRap(int x, int y, int rectx1, int recty1, int rectx2, int recty2) {
		boolean stepA = (x == rectx1 || x == rectx2) && (recty1 <= y || y <= recty2);
		boolean stepB = (y == recty1 || y == recty2) && (rectx1 <= x || x <= rectx2);
		return stepA || stepB;
	}

	// 점 x, y가 직사각형 (rectx1, recty1), (rectx2, recty2) 안에 있는 지 판단하는 메소드
	public static boolean inRect(int x, int y, int rectx1, int recty1, int rectx2, int recty2) {
		return (x > rectx1 && x < rectx2) && (y > recty1 && y < recty2);
	}
}
