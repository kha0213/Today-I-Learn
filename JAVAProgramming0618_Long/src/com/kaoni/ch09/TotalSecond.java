package com.kaoni.ch09;

public class TotalSecond {
	private static TotalSecond TotalSecond = new TotalSecond();
	private int totalSecond;

	private TotalSecond() {
		totalSecond = 0;
	} // 싱글톤 객체위한 생성자

	public static TotalSecond getTotalClass() {
		return TotalSecond;
	}

	public int getTotalSecond() {
		return totalSecond;
	}

	public void setTotalSecond(int totalSecond) {
		this.totalSecond = totalSecond;
	}

}
