package com.kaoni.ch08;

public class HangManVO {
	
	
	private String word;
	private int[] hidden;
	private int count;
	
	
	public HangManVO(String word, int[] hidden) {
		this.word = word;
		this.hidden = hidden;
		count = 0;
	}
	
	public String getWord() {
		return word;
	}

	public int[] getHidden() {
		return hidden;
	}

	public void setHidden(int[] hidden) {
		this.hidden = hidden;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
	
}
