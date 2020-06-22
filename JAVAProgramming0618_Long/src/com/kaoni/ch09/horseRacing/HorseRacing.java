package com.kaoni.ch09.horseRacing;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc :
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 19.
 * @Version : 1.0.0
 */
public class HorseRacing {

	public HorseRacing() {

	}

	/**
	 * 
	 * desc : user에게 String받기
	 * 
	 * @Method userCommand
	 * @param Scanner
	 * @return String
	 */
	private String userCommand(Scanner sc) {
		String command = sc.nextLine();
		return command;
	}

	/**
	 * 
	 * desc : 경기 세팅
	 * 
	 * @Method settingProgram
	 * @param Scanner
	 */
	public void settingProgram(Scanner sc) {
		try {
			System.out.println("경기장 목표거리를 입력해주세요 (말은 1턴당 1~10 사이로 이동합니다.)");
			int distance = Integer.parseInt(userCommand(sc));
			System.out.println("말 숫자를 입력해주세요");
			int horseCount = Integer.parseInt(userCommand(sc));
			Horse[] horses = new Horse[horseCount];
			for (int i = 0; i < horseCount; i++) {
				horses[i] = new Horse(i, distance);
			}

			runningHorses(horses);

		} catch (NumberFormatException e) {
			System.out.println("오류!!! 숫자만 입력해주세요");
			settingProgram(sc);
		}
	}

	/**
	 * 
	 * desc : 말 스레드 출발 시키는 메소드
	 * 
	 * @Method runningHorses
	 * @param Horse[]
	 */
	private void runningHorses(Horse[] horses) {
		System.out.println("\n\n~~~~경기 시작 하겠습니다~~~~\n\n");
		for (int i = 0; i < horses.length; i++) {
			horses[i].start();
		}
		try {
			for (int i = 0; i < horses.length; i++) {
				horses[i].join();
			}
		} catch (InterruptedException e) {
			System.out.println("runningHorses : " + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("\n\n~~~~경기 종료 되었습니다~~~~\n\n");
		printHorseRank(horses);
	}

	/**
	 * 
	 * desc : 최종 결과 출력 메소드
	 * 
	 * @Method printHorseRank
	 * @param Horse[]
	 */
	private void printHorseRank(Horse[] horses) {
		System.out.println("☆★☆★☆★ 등수 발표 ☆★☆★☆★");
		for (int i = 0; i < horses.length; i++) {
			System.out.println(horses[i].getHorseNum() + "번 말 : [" + horses[i].getHorseRank() + "] 등");
		}
	}
}
