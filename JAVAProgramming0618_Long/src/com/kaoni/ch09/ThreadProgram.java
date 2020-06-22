package com.kaoni.ch09;

import java.util.Collections;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 * 
 *
 * desc : 스레드 통합 프로그램
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 18.
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

	private void startThreadPool() {
		System.out.println("쓰레드 풀을 생성합니다.");
		System.out.println("쓰레드 세팅 수 : " + TCons.THREAD_COUNT_IN_THREAD_POOL);
		System.out.println("동시 작업 수 : " + TCons.WARKING_COUNT);
		ExecutorService executorService = Executors.newFixedThreadPool(TCons.THREAD_COUNT_IN_THREAD_POOL);
//		ExecutorService executorService = Executors.newCachedThreadPool(); // 초기값 0 이지만 작업 있을때마다 쓰레드 새로 생성
		for (int i = 0; i < TCons.WARKING_COUNT; i++) {
			Runnable runn = () -> {
//				String threadName = Thread.currentThread().getName();
//				System.out.println("[" + threadName + "] 쓰레드 시작");
				int n = 0; // 이름
				while (true) {
					System.out.println("[" + Thread.currentThread().getName() + "] n값 : [" + n + "]");
					if (n >= TCons.THREAD_SHUTDOWN) { // 쓰레드 종료 상수
						Thread.currentThread().interrupt();
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						System.out.println(Thread.currentThread().getName() + "쓰레드 종료");
						return;
					}
					n++;
				}
			};
			executorService.execute(runn);
		}

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
		System.out.println(String.join("", Collections.nCopies(50, "-")));
		System.out.println("[1] 타이머생성 [2] 쓰레드풀생성 [그만] 시스템 종료");
		System.out.println(String.join("", Collections.nCopies(50, "-")));
		String command = sc.nextLine();
		return command;
	}

	/**
	 * 
	 * desc : 쓰레드 실행 최종 종료를 위한 boolean 리턴
	 * 
	 * @Method threadExecute
	 * @param String
	 * @return boolean   EXEcuteThread -> 명령어 수정
	 */
	private boolean threadExecute(String command) {
		boolean programStatus = true;
		switch (command) {
		case TCons.TIMER_COMMAND: //
			System.out.println("타이머를 생성합니다.");
			TimerThreadJFrame timerJFrame = new TimerThreadJFrame();
			TimerThread timer = new TimerThread(timerJFrame);
			timer.goTimer();
			break;
		case TCons.THREAD_POOL_COMMAND:
			startThreadPool();
			break;
		case TCons.EXIT_COMMAND:
			programStatus = TCons.SYSTEM_CLOSE;
			break;
		default:
			System.out.println("잘못된 명령어 입니다. 명령어를 다시 입력해주세요");
			break;
		}
		return programStatus;
	}
}
