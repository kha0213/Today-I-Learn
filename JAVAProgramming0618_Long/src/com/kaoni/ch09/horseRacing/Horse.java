package com.kaoni.ch09.horseRacing;

import java.util.Collections;

public class Horse extends Thread {
	private int horseNum; // 말 번호
	private int speed;
	private int runDistance; // 주행거리
	private int targetDistance; // 목표거리
	private int horseRank; // 등수

	public Horse(int horseNum, int targetDistance) {
		this.horseNum = horseNum;
		this.targetDistance = targetDistance;
	}

	@Override
	public void run() {
		while (true) {
			speed = (int) (Math.random() * 10) + 1; // 턴마다 스피드 랜덤 1~10
			runDistance += speed; // 주행거리 추가
			if (runDistance >= targetDistance) { // 주행거리가 경기장 거리보다 크면
				runDistance = targetDistance;
				this.interrupt();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				horseRank = getRank(); // 등수 저장
				printEnd(); // 말 도착 출력
				return;
			}
			printRunning();

		}
	}

	/**
	 * 
	 * desc : 말 달린거리 프린트
	 * 
	 * @Method printRunning :
	 */
	private void printRunning() {
		System.out.println((String.join("", Collections.nCopies(runDistance, "-"))) + "[" + horseNum + "]");
	}

	private void printEnd() {
		System.out.println((String.join("", Collections.nCopies(runDistance, "-"))) + "[" + horseNum + "]끝");
	}

	/**
	 * 
	 * desc : 스레드 공유변수 (int) 가져오기
	 * 
	 * @Method getRank
	 * @return int
	 */
	private synchronized int getRank() {
		Racetrack.RANK++;
		return Racetrack.RANK;
	}

	// getter
	public int getHorseRank() {
		return horseRank;
	}

	public int getHorseNum() {
		return horseNum;
	}
}
