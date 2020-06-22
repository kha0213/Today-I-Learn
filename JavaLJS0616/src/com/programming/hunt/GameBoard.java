package com.programming.hunt;

import java.util.Scanner;

public class GameBoard {
	char[][] board = new char[10][10];

	public void pinrtBoard() {
		int lineCount = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j]);
				lineCount++;
				if (lineCount >= 10) {
					System.out.println();
					lineCount = 0;
				}
			}
		}
	}

	public void makeBoard(Bear bear,Fish fish) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				board[i][j] = '■';
			}
		}
		board[bear.y][bear.x] = bear.getPrintShape();
		board[fish.y][fish.x] = fish.getPrintShape();
	}
	
	public void bearMove(Bear bear) {
			board[bear.y][bear.x] = bear.getPrintShape();	
	}
	
	public void fishMove(Fish fish) {
		fish.setShape('□');
		board[fish.y][fish.x] = fish.getPrintShape();
	}
	
	public void bearShapeReset(Bear bear) {
			bear.setShape('■');
			board[bear.y][bear.x] = bear.getPrintShape();
	}
	
	public void fishShapeReset(Fish fish) {
		fish.setShape('■');
		board[fish.y][fish.x] = fish.getPrintShape();
}
	
	
	
	public static void main(String[] args) {
		Bear bear = new Bear();
		Fish fish = new Fish();
		GameBoard board = new GameBoard();
		Scanner sc = new Scanner(System.in);
		boolean isHunt = false;
		board.makeBoard(bear,fish);
		board.pinrtBoard();
		while (true) {
			
			try {
				System.out.println(
						"움직일 방향을 누르세요(UP : w , DOWN : s , LEFT : a , RIGHT : d)");
				char Defense = sc.next().charAt(0);
				board.bearShapeReset(bear);
				board.fishShapeReset(fish);
				bear.chaseFish(Defense);
				fish.runAwayFish();
				System.out.println("x : "+ fish.x+"/ y : "+fish.y);
				board.fishMove(fish);
				board.bearMove(bear);
				isHunt = bear.cheakHunt(fish, bear);
				board.pinrtBoard();
				if(isHunt) {
					System.out.println("물고기가 잡혔습니다.");
					break;
				}
			}catch (ArrayIndexOutOfBoundsException e) {
				System.out.println("막혔습니다");
			}
			
		}
	}

}
