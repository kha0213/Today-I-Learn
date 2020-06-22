package com.programming.hunt;

import java.util.Scanner;

public class Bear extends Location implements MoveMent {
	
	
	public Bear() {
		setShape('▼');
		x = 1;
		y = 1;
	}

	@Override
	public void chaseFish(char inputValue) {
		switch (inputValue) {
			case UP :
				y -= 1;
				setShape('↑');
				break;
			case DOWN :
				y += 1;
				setShape('↓');
				break;
			case LEFT :

				x -= 1;
				setShape('←');
				break;
			case RIGHT :
				x += 1;
				setShape('→');
				break;


			default :
				System.out.println("잘못 입력 하셨습니다.");
				break;
		}
	}
	
	
	
}
