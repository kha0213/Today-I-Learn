package com.programming.hunt;

abstract class Location {
	int x;
	int y;
	char printShape;
	public int getX() {
		return x;
	}
	public void setX(int x) {
		if(x>0) {
			this.x = x;
		}
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		if(y>0) {
			this.y = y;			
		}
	}
	public char getPrintShape() {
		return printShape;
	}
	public void setShape(char printShape) {
		this.printShape = printShape;
	}
	
	
	
	
	
}
