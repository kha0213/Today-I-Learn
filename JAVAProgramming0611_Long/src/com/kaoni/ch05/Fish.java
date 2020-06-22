package com.kaoni.ch05;

import java.util.Random;
/**
 * 
 * 
 *
 * desc 	: 물고기 객체
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public class Fish extends GameObject {
	
	
	public Fish() {
		Random random = new Random();
		distance = GameConstant.FISH_START_DISTANCE;
		x = random.nextInt(20);
		y = random.nextInt(20);
	}


	@Override
	public char getShape() {
		return 'F';
	}


	@Override
	public void movingRule() {
		Random random = new Random();
		if(random.nextInt(10) < 6) { // 60% 확률로 이동 
			int moveInt = random.nextInt(4);
			switch (moveInt) {
			case 0: move('a');
				break;
			case 1: move('s');
				break;
			case 2: move('d');
				break;
			case 3: move('w');
			}
		} // 
	}

}
