package com.programming.hunt;

public interface MoveMent {
	public static final char UP = 'w';
	public static final char DOWN = 's';
	public static final char LEFT = 'a';
	public static final char RIGHT = 'd';
	default void chaseFish(char inputValue) {};
	public static char runAway() {return 0;};
	
	
	default boolean cheakHunt(Fish fish,Bear bear) {
		if(bear.x == fish.x && bear.y == fish.y) {
			return true;
		}else {
			return false;
		}
	}

}
