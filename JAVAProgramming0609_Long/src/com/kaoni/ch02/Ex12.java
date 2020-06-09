package com.kaoni.ch02;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 계산기 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 9.
 * @Version :
 */

public class Ex12 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("계산기 입니다. 연산자는 +, =, *, / 네 가지 입니다. (입력은 띄어쓰기로 구분해주세요)");
		String calculator = sc.nextLine();

		String[] calArr = calculator.split(" ");
		double num1 = Double.parseDouble(calArr[0]);
		String symbol = calArr[1];
		double num2 = Double.parseDouble(calArr[2]);
		
		if(num2 == 0 && symbol.equals("/")) {
			System.out.println("0으로 나눌 수 없습니다.");
		} else {
			System.out.println(calculator+" 계산결과는 "+doCalculation(num1, num2, symbol));
		}
		sc.close();

	}

	public static double doCalculation(double num1, double num2, String symbol) {
		double answer = 0;
		
		switch (symbol) {
		case "+": answer = num1+num2; break;
		case "-": answer = num1-num2; break;
		case "*": answer = num1*num2; break;
		case "/": answer = num1/num2; 
		}
		
		return answer;
	}
}
