package com.kaoni.ch03;

import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * 
 * 
 *
 * desc 	: 랜덤 정수 정렬 출력
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 10.
 * @Version :
 */
public class Ex08InputNumber {

	public static void main(String[] args) {
		// 유저로 부터 숫자 입력 받기
		int inputNumber = inputNumber();
		
		// 받은 숫자만큼 랜덤 수 출력
		printNumber(inputNumber);
	}
	
	
	// 숫자 입력 받기
	public static int inputNumber() {
		System.out.println("1~100 사이의 정수를 입력하세요. 그 수만큼 랜덤 숫자가 중복 없이 정렬되어 출력합니다.");
		Scanner sc = new Scanner(System.in);
		int inputNumber = sc.nextInt();
		sc.close();
		return inputNumber;
	}
	
	
	// 숫자 정렬 TreeSet
	public static TreeSet<Integer> sortNumber(int numberCount) {
		TreeSet<Integer> numbers = new TreeSet<Integer>();
		Random random = new Random();
		for(int i=0; i<numberCount; i++) {
			// 중복 제거
			while(true) {
				if(numbers.add(random.nextInt(100)+1)) {
					break;
				}
			}
		}
		return numbers;
	}
	
	
	// 숫자 출력하는 함수
	public static void printNumber(int inputNumber) {
		// 입력한 숫자만큼 랜덤 정수(1~100) 뽑고 정렬
		TreeSet<Integer> numberSet = sortNumber(inputNumber);
		Iterator<Integer> iterator = numberSet.iterator();
		
		// 랜덤 정수 출력 5개마다 개행
		int count = 0;
		while(iterator.hasNext()) {
			System.out.print(iterator.next()+"  ");
			count++;
			if (count == 5) {
				System.out.println();
				count = 0;
			}
		}
	}
	
	
	
	
	
	
	
	
	
}
