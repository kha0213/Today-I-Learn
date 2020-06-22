package com.kaoni.bakjoon;

import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeSet;

public class Ex1671 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] shark = new int[N][3];
		for(int i=0; i<N; i++) {
			String[] sharkStr = new String[3];
			String tempStr = sc.nextLine();
			sharkStr = tempStr.split(" ");
			for(int j=0; j<3;j++) {
				shark[i][j] = Integer.parseInt(sharkStr[j]);
			}
		}
		TreeSet<Integer> sharkNum = new TreeSet<Integer>();
		for(int i=1;i<=N;i++) {
			sharkNum.add(i);
		}
		int exitCount = sharkNum.size();
		al : while(true) {
			Iterator<Integer> checkShark = sharkNum.iterator();
			int remove = 0;
			all : while(checkShark.hasNext()) {
				for(int i=0; i<sharkNum.size(); i++) {
					for(int j=i+1;j<sharkNum.size();j++) {
						int tempInt = checkShark.next();
						
						int[] shark1 = shark[tempInt];
						if(checkShark.hasNext()) break all;
						int tempInt2 = checkShark.next();
						int[] shark2 = shark[tempInt2];
						if(vsShark(shark1, shark2)) {
							sharkNum.remove(tempInt2);
							if(remove == 1) {
								sharkNum.remove(tempInt);
								continue al;
							}
							remove++;
						}
					}
				}
			}
			if(exitCount == sharkNum.size()) {
				System.out.println(sharkNum.size());
				break al;
			}else {
				exitCount = sharkNum.size();
			}
			
		}

		
	}
	public static boolean vsShark(int[] shark1, int[] shark2) {
		return shark1[0] >= shark2[0] && shark1[1] >= shark2[1] && shark1[2] >= shark2[2];
	}
	
	
		
}
