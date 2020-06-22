package com.kaoni.ch06;

import java.util.Arrays;

public class EnglishHistogram {
	private final int ALPHABET_COUNT = 26; //상수 찾아보기
	private final int CHAR_TO_INT = 97; // 'a' 97
	private final int CHAR_SPACE = 65; 
	
	// charLowerA
	public int[] stringToIntArray(String longText) { // 영문, 스페이스
		int[] histogram = new int[ALPHABET_COUNT]; // a~z
		StringBuilder sb = new StringBuilder(longText.toLowerCase());
		for(int i=0;i<sb.length();i++) {
			if(CHAR_TO_INT - sb.charAt(i) != CHAR_SPACE) { // 띄어쓰기 제외
				histogram[sb.charAt(i)-CHAR_TO_INT]++; // a = 0 b = 1
			}
		}
		
		return histogram;
	}
	
	// 프린트하는 프로그램
	public void printHistogram(int[] histogram) {
		for(int i=0; i<histogram.length; i++) {
			System.out.print((char)(i+CHAR_TO_INT));
			for(int j=0; j<histogram[i]; j++) {
				System.out.print('■');
			}
			System.out.println(); // 개행
		}
		
		// X축 프린트 하기 위한 프로그램
		Arrays.sort(histogram);
		int maxLevel = histogram[histogram.length-1];
		printXAxis(maxLevel);
	}
	
	
	// X축 프린트
	public void printXAxis(int maxLevel) {
		System.out.println("각 영단어 갯수 히스토그램 입니다.");
		int i;
		for(i=0; i<maxLevel; i++) {
			if(i%10 == 0) {
				System.out.print(i);
				i += Math.floor((Math.log10(i+1)));
			} else {
				System.out.print("-");
			}
		}
		System.out.println(i+"(max)");
	}
	
}
