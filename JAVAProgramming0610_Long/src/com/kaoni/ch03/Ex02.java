package com.kaoni.ch03;

/**
 * 
 * 
 *
 * desc 	: 2차원 배열 출력하기 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 9.
 * @Version :
 */
public class Ex02 {
	public static void main(String[] args) {
		int arr [][] = {{1}, {1,2,3}, {1}, {1,2,3,4}, {1,2}};
		
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			// 개행
			System.out.println();
		}
		
	}
}
