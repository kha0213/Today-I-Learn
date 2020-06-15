package com.kaoni.ch05;
/**
 * 
 * 
 *
 * desc 	: 게임 말 객체
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public abstract class GameObject {
	
	protected int distance; //이동 거리
	protected int x; // 현재 위치(화면 맵 상의 위치)
	protected int y; // 현재 위치(화면 맵 상의 위치)

	public boolean collide(GameObject p) {
		return this.x == p.getX() && this.y == p.getY();
	}
	
	public void move(char command) {
		switch (command) {
		case GameConstant.UP: 
			x = Math.max(x-distance, GameConstant.MIN_COORDINATE); // 최솟값 0
			break;
		case GameConstant.LEFT:
			y = Math.max(y-distance, GameConstant.MIN_COORDINATE);
			break;
		case GameConstant.DOWN:
			x = Math.min(x+distance, GameConstant.MAX_COORDINATE); // 최댓값 19
			break;
		case GameConstant.RIGHT:
			y = Math.min(y+distance, GameConstant.MAX_COORDINATE);
		}
	}
	
	// 추상 메소드
	public abstract char getShape();
	public abstract void movingRule();
	
	
	
	// collide를 위한 getter
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	
}
