package com.programming.hunt;

public class Fish extends Location implements MoveMent {

	public Fish() {
		setShape('□');
		x = 8;
		y = 8;
	}

	public void runAwayFish() {
		int moveProbability = (int) (Math.random() * 10) + 1;
		if (moveProbability < 7) {
			moveProbability = (int) (Math.random() * 4) + 1;
			switch (moveProbability) {
				case 1 :
					if(y!=0)
					y -= 1;
					setShape('□');
					break;
				case 2 :
					if(y!=9)
					y += 1;
					setShape('□');
					break;
				case 3 :
					if(x!=0)
					x -= 1;
					setShape('□');
					break;
				case 4 :
					if(x!=9)
					x += 1;
					setShape('□');
					break;

			}
		}
	}
}
