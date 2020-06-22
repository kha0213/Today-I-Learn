package com.kaoni.ch05;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 곰 객체
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public class Bear extends GameObject {

	// 초기값 이동거리 1, x위치 0, y위치 0
	public Bear() {
		distance = GameConstant.BEAR_START_DISTANCE;
		x = GameConstant.BEAR_START_X;
		y = GameConstant.BEAR_START_Y;
	}
	
	
	@Override
	public void movingRule() {
		char command = userCommand(); // 입력 받고
		move(command); // 이동 실행
	}
	
	// 유저에게 커멘드 입력받기
	private char userCommand() { // scanner 계속 만들지 말기
		Scanner sc = new Scanner(System.in);
		char userInput = sc.next().charAt(0);
		return userInput;
	}
	
	
	@Override
	public char getShape() {
		return 'B';
	}



}
