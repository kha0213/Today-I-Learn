package com.kaoni.ch05;
/**
 * 
 * 
 *
 * desc 	: 게임 실행 프로그램
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public class Game {
	
	
	private Bear bear; // 곰 객체
	private Fish fish; // 생선 객체
	private int movingCount; // 이동한 횟수
	
	public Game() {
		bear = new Bear();
		fish = new Fish();
		movingCount = 0;
		
	}
	
	public void gameStart() {
		System.out.println("곰의 물고기 잡기 게임을 시작합니다. 물고기는 60%확률로 랜덤한 방향으로 한 칸 이동합니다.");
		System.out.println("곰 위치 : "+bear.x+","+bear.y);
		System.out.println("물고기 위치 : "+fish.x+","+fish.y);
		while(true) {
			printCoordinate();
			bear.movingRule();
			fish.movingRule();
			if(checkLocation() == GameConstant.MISSION_COMPLETE) {
				System.out.println("프로그램을 종료합니다. 감사합니다");
				break;
			}
		}
	}
	
	// 좌표 출력
	private void printCoordinate() {
		for(int i=0; i < 20; i++) {
			for(int j=0; j < 20; j++) {
				if(fish.x == i && fish.y == j) {
					System.out.print(fish.getShape());
				} else if (bear.x == i && bear.y == j) {
					System.out.print(bear.getShape());
				} else {
					System.out.print('■');
				}
			}
			System.out.println();
		}
		System.out.println("왼쪽 a 아래 s 오른쪽 d 위 w 를 입력하세요 (그 외의 문자는 제자리입니다.) >>");
		System.out.println("곰 위치 : "+bear.x+","+bear.y);
		System.out.println("물고기 위치 : "+fish.x+","+fish.y);
	}
	
	// 좌표 체크
	public boolean checkLocation() {
		if (bear.collide(fish)) {
			System.out.println("성공하셨습니다! 곰이 물고기를 잡았습니다. \nplay횟수 : "+movingCount+"번");
			return GameConstant.MISSION_COMPLETE;
		} 
		movingCount++;
		
		return GameConstant.IN_GAME;
	}
}
