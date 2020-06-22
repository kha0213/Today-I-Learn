package com.kaoni.ch09;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc : 스레드 통합 프로그램
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 17.
 * @Version : 1.0.0
 */
public class ThreadProgram {
	private boolean programStatus; // while문 종료 변수
	public ThreadProgram() {
		programStatus = true;
	}
	
	/**
	 * 
	 * desc : 시작 프로그램
	 * 
	 * @Method startProgram
	 * @param Scanner
	 */
	public void startProgram(Scanner sc) {
		System.out.println("ThreadProgram을 시작합니다.");
		while (programStatus) {
			String command = userCommand(sc);
			programStatus = threadExecute(command);
		}
		sc.close();
		System.out.println("ThreadProgram을 종료합니다.");
	}

	/**
	 * 
	 * desc : 유저에게 String 받기
	 * 
	 * @Method userCommand
	 * @param Scanner
	 * @return String
	 */
	private String userCommand(Scanner sc) {
		System.out.println("[1] 타이머생성 [그만] 시스템 종료");
		String command = sc.nextLine();
		return command;
	}
	
	/**
	 * 
	 * desc : 쓰레드 실행 최종 종료를 위한 boolean 리턴
	 * 
	 * @Method threadExecute 
	 * @param String
	 * @return boolean
	 * @throws
	 */
	private boolean threadExecute(String command) {
		boolean programStatus = true;
		switch (command) {
		case "1":
			System.out.println("타이머를 생성합니다.");
			TimerThreadJFrame timerJFrame = new TimerThreadJFrame();
			TimerThread timer = new TimerThread(timerJFrame);
			timer.goTimer();
			break;
		case "그만":
			programStatus = ThreadProgramCons.SYSTEM_CLOSE;
			break;
		default:
			System.out.println("잘못된 명령어 입니다. 명령어를 다시 입력해주세요");
			break;
		}
		return programStatus;
	}
}
