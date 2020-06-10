package com.kaoni.ch03;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 컴퓨터와 가위바위보 게임
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 10.
 * @Version :
 */
public class Ex16RPSGame {

	public static void main(String[] args) {
		playGame();
	}
	
	
	// 숫자 입력 받는 것
	public static void playGame() {
		
		final String[] RPS = {"[가위]","[바위]","[보]"};
		final String[] Winner = {"유저 승.","비김.","컴퓨터 승"};
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("컴퓨터와 가위 바위 보 게임을 합니다. [그만]을 입력하시면 종료됩니다.");
			int userPick = userInput(sc);
			int comPick = computerInput();
			int result = checkWinner(userPick, comPick);
			
			System.out.println("--------------------------------------------");
			System.out.println("당신 : "+RPS[userPick]+", 컴퓨터 : "+RPS[comPick]);
			System.out.println(Winner[result]);
			System.out.println("--------------------------------------------");
			
			
		}
	}
	
	
	// 유저 가위 바위 보 입력 받는 것 [0:가위, 1:바위, 2:보]
	public static int userInput(Scanner sc) {
		String userPick = sc.next();
		switch (userPick) {
		case "가위":
			return 0;
		case "바위":
			return 1;
		case "보":
			return 2;
		case "그만":
			System.out.println("시스템을 종료합니다.");
			sc.close();
			System.exit(0);
		default: 
			System.out.println("유효한 입력이 아닙니다. 다시 입력하세요");
			// 재귀
			userInput(sc);
		}
		
		// 여기로 나오는 경우는 없습니다.
		return 0;
	}
	
	
	// 컴퓨터 가위 바위 보 [0:가위, 1:바위, 2:보] 
	public static int computerInput() {
		return (int)(Math.random()*3);
	}
	
	
	// [0:가위, 1:바위, 2:보] 로 누가 이겼는지 체크
	public static int checkWinner(int user,int com) {
		int result = 0;
		switch (user-com) {
		case -1:case 2:
			result = 2;
			break;
		case 0:
			result = 1;
			break;
		case -2:case 1:
			result = 0;
			break;
		}
		// 리턴 [0:유저 승, 1:비김, 2:컴퓨터 승]
		return result;
	}
}
