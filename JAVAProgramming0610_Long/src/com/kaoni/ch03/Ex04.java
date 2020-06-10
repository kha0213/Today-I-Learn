package com.kaoni.ch03;

import java.util.Scanner;

/**
 * 
 * desc 	: 알파벳 출력
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 10.
 * @Version :
 */
public class Ex04 {
	public static void main(String[] args) {
		System.out.println("소문자 알파벳 하나를 입력하시오. 순서대로 출력합니다.");
		
		// 메서드 실행
		printWord(inputWord());
	}
	
	
	// 문자 입력 받는 것
	public static char inputWord() {
		Scanner sc = new Scanner(System.in);
		String inputWord = sc.next();
		sc.close();
		return inputWord.charAt(0);
	}
	
	// 재귀
	public static void printWord(char word) {
		if (word - 0 < 97 || word - 0 > 122) {
			System.out.println("끝");
		} else {
			for(int i = 97; i <= word-0; i++) {
				System.out.print((char)i);
			}
			System.out.println();
			printWord((char) (word - 1));
		}
	}
}
