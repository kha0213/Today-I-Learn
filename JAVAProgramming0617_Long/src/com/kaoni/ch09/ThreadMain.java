package com.kaoni.ch09;

import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 17.
 * @Version : 1.0.0
 */
public class ThreadMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ThreadProgram program = new ThreadProgram();
		program.startProgram(sc);
	}
}
