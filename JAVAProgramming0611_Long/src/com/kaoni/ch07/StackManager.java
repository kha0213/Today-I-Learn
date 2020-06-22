package com.kaoni.ch07;

import java.util.Scanner;
/**
 * 
 * 
 *
 * desc 	: 마이스택 프로그램 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public class StackManager {
	private final int EXIT = 0;
	
	public void startProgram() {
		MyStack<String> stack = new MyStack<String>();
		while(true) {
			System.out.println("-------------------------------------------------------------------------------------------");
			System.out.println("스택 구현 연습입니다. 원하시는 기능을 선택해주세요. | 1.push | 2.pop | 3.peek | 4.StackAllPop | 그 외 : 종료");
			System.out.println("-------------------------------------------------------------------------------------------");
			int userCommand = userCommand();
			execute(userCommand, stack);
			System.out.println("\n");
			
		}
	}
	

	// 유저 커맨드 입력
	private int userCommand() {
		Scanner sc = new Scanner(System.in);
		int userCommand = 0;
		try {
			userCommand = sc.nextInt();
		} catch (Exception e) {
			execute(EXIT, null); // 숫자 입력 안했을 때 종료
		}
		return userCommand;
	}
	
	
	// 메소드 실행
	private void execute(int command, MyStack<String> stack) {
		switch (command) {
		case 1: doPush(stack);
			break;
		case 2: doPop(stack);
			break;
		case 3: doPeek(stack);
			break;
		case 4: doStackAllPop(stack);
			break;
		default:
			System.out.println("시스템을 종료합니다.");
			System.exit(EXIT);
			break;
		}
	}
	
	
	// 유저 푸쉬할 메세지
	private String userPushMessage() {
		Scanner sc = new Scanner(System.in);
		System.out.println("스택에 저장할 문장을 입력해주세요");
		return sc.nextLine();
	}
	
	
	//push 명령
	private void doPush(MyStack<String> stack) {
		String message = userPushMessage();
		stack.push(message);
		System.out.println(message+"를 스택에 추가하였습니다. 스택 개수 : "+stack.getTop());
	}
	
	
	// peek 명령
	private void doPeek(MyStack<String> stack) {
		String content = stack.peek();
		if(content == null) {
			System.out.println("stack에 저장된 자료가 하나도 없습니다.");
		} else {
			System.out.println("가장 위에 있는 자료를 봅니다. \n자료: ["+content+"] \n스택 개수는 유지하여 "+stack.getTop()+"개 입니다.");
		}
	}
	
	
	// peek 명령
	private void doPop(MyStack<String> stack) {
		String content = stack.pop();
		if(content == null) {
			System.out.println("stack에 저장된 자료가 하나도 없습니다.");
		} else {
			System.out.println("가장 위에 있는 자료를 꺼냅니다.\n자료 : ["+content+"] \n스택 개수가 1 감소하여 "+stack.getTop()+"개 되었습니다.");
		}
	}
	
	
	// stackInfo 명령
	private void doStackAllPop(MyStack<String> stack) {
		System.out.println("스택 정보 보기 입니다. 스택 개수는 총 "+stack.getTop()+"개 이고 모든 자료를 위에서부터 순차적으로 꺼내겠습니다.");
		int count = 1;
		while(true) {
			String tempStr = stack.pop();
			if(tempStr == null) { // 스택에 저장된 값이 없을 때
				System.out.println("이상 모든 자료입니다.");
				break;
			}
			System.out.println(count+++"번 : "+tempStr);
		}
	}
		
	
}
