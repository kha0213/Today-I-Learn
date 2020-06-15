package com.kaoni.ch06;

import java.util.Scanner;
/**
 * 
 * 
 *
 * desc 	: String 프로그램 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public class StringBuilderProgram {
	public static void main(String[] args) {
		// 스트링 로테이션 프로그램 실행
		StringRotation();
	}
	
	
	// 글자 순환 출력 프로그램
	public static void StringRotation() {
		Scanner sc = new Scanner(System.in);
			System.out.println("문자열을 입력하세요. 빈칸이 있어도 되고 영어 한글 모두 됩니다.");
			String sentence = userInput(sc); // 유저한테 입력 받고
			
			// 출력 프로그램 실행
			long time1 = System.nanoTime();
			printStringBuilder(sentence); 
			long time2 = System.nanoTime();
			printStringBuffer(sentence);
			long time3 = System.nanoTime();
			printString(sentence);
			long time4 = System.nanoTime();
			
			System.out.println("걸린시간-------------");
			System.out.println("StringBuilder : "+(time2-time1));
			System.out.println("StringBuffer"+(time3-time2));
			System.out.println("String"+(time4-time3));
			
	}
	
	// 유저에게 문장 받기
	public static String userInput(Scanner sc) {
		String userInput = sc.nextLine();
		return userInput;
	}
	
	// StringBuilder을 사용하여 문자 출력
	public static void printStringBuilder(String sentence) {
		StringBuilder sb = new StringBuilder(sentence);
		System.out.println("StringBuilder---------------------------------------------------");
		for(int i=0;i<=sentence.length();i++) {
			System.out.println(sb.substring(i)+sb.substring(0, i));
		}
		System.out.println("---------------------------------------------------");
	}
	
	
	// StringBuilder을 사용하여 문자 출력
	public static void printStringBuffer(String sentence) {
		StringBuffer sf = new StringBuffer(sentence);
		System.out.println("StringBuffer---------------------------------------------------");
		for(int i=0;i<=sentence.length();i++) {
			System.out.println(sf.substring(i)+sf.substring(0, i));
		}
		System.out.println("---------------------------------------------------");
	}
	
	
	// String을 사용하여 문자 출력
	public static void printString(String sentence) {
		String s = new String(sentence);
		System.out.println("String---------------------------------------------------");
		for(int i=0;i<=sentence.length();i++) {
			System.out.println(s.substring(i)+s.substring(0, i));
		}
		System.out.println("---------------------------------------------------");
	}
	
}
