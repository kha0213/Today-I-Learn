package com.kaoni.ch08;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 
 * 
 *
 * desc 	: 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 12.
 * @Version : 1.0.0
 */
public class HangManGame {
	
	private final boolean RIGHT_ANSWER = true;
	private final boolean INCORRECT_ANSWER = false;
	private final int HANGMAN_DEATH = 5;
	private final int EXIT = 0;
	private final int LEVEL_EASY = 1;
	private final int LEVEL_NORMAL = 2;
	private final int LEVEL_HARD = 3;
	private String hangManWord;
	private Scanner sc;
	
	public HangManGame() {
		sc = new Scanner(System.in);
	}
	
	
	public void startHangMan() {
		System.out.println("지금부터 행맨 게임을 시작합니다. 5회 이내로 맞추어야 성공입니다. 랜덤 단어를 뽑겠습니다.");
		while(true) {
			hangManWord = new HangManWord().randomWord(); // 랜덤 단어 생성
			HangManVO hangMan = createHangMan(hangManWord); // 행맨 객체 생성
			playingGame(hangMan); // 게임 진행
			closeSystem(); // 종료 묻기
		}
	}
	
	
	// 게임 진행
	private void playingGame(HangManVO hangMan) {
		if (hangMan.getCount()==5) {
			System.out.println(HangManMessage.END_MESSAGE);
			System.out.println("5번 실패하셔서 클리어 실패하셨습니다. ");
		} else if (hangMan.getHidden().length == 0) {
			System.out.println(hangMan.getCount()+"번민에 성공!! 뛰어나시네요.");
		} else {
			playingGame(userGaming(hangMan));
		}
	}
	
	// 유저가 맞추기
	private HangManVO userGaming(HangManVO hangMan) {
		printHangMan(hangMan);
		System.out.print(">>");
		char userInput = sc.next().charAt(0);
		return checkWord(hangMan, userInput);
	}
	
	// 행맨 맞춘지 확인
	private HangManVO checkWord(HangManVO hangMan, char ch) {
		int[] hidden = hangMan.getHidden(); //빈칸 idx 배열
		boolean checkAnswer = INCORRECT_ANSWER;
		for(int i=0; i<hidden.length; i++) {
			if(ch == hangMan.getWord().charAt(hidden[i])) {
				hangMan = correctWord(hangMan, i, ch); //정답 메소드
				System.out.println("정답입니다.");
				checkAnswer = RIGHT_ANSWER;
				break;
			}
		}
		if(checkAnswer == INCORRECT_ANSWER) {// 오답
			System.out.println("오답입니다.");
			hangMan.setCount(hangMan.getCount()+1); //실패 숫자 하나 증가
		}
		return hangMan;
	}
	
	// 한 글자 맞춤 이벤트 -- 정답 배열 바꾸고 hiddenWord 바꿈
	private HangManVO correctWord(HangManVO hangMan, int idx, char ch) {
		int[] oldHidden= hangMan.getHidden(); // 원래 빈칸 idx 배열
		int[] newHidden = new int[oldHidden.length-1]; //새로운 정답 배열
		// new Hidden 배열 만들기 (idx번째 빼고 복사)
		for(int i=0; i < newHidden.length; i++) {
			if(i < idx) {
				newHidden[i] = oldHidden[i];
			} else {
				newHidden[i] = oldHidden[i+1];
			}
		}
		hangMan.setHidden(newHidden);
		return hangMan;
		
	}
	
	// 행맨 상태 프린트
	private void printHangMan(HangManVO hangMan) {
		System.out.println(HangManMessage.START_MESSAGE);		
		System.out.println(HangManMessage.HANGMAN[hangMan.getCount()]);
		System.out.println("************************************************");
		System.out.println("\t\t"+makeHiddenWord(hangMan));
		System.out.println("************************************************");
		System.out.println("남은 횟수 : "+(HANGMAN_DEATH-hangMan.getCount())+"번");
	}
	
	// 행맨 단어 hidden으로 만들기
	private String makeHiddenWord(HangManVO hangMan) {
		String word = hangMan.getWord();
		int[] hidden = hangMan.getHidden();
		StringBuilder sBuilder = new StringBuilder(word);
		for(int i=0; i<hidden.length; i++) {
			sBuilder.setCharAt(hidden[i], '_');
		}
		return sBuilder.toString();
	}
	
	// 끝내기 묻기
	private void closeSystem() {
		System.out.println("그만 입력하시면 종료 (그 외 계속 진행)");
		String userInput = sc.next();
		if(userInput.equals("그만")) {
			System.exit(EXIT);
		}
	}
	
	
	// 행맨 객체 만들기
	private HangManVO createHangMan(String word) {
		int level = settingLevel(); // 유저가 레벨 설정 (level만큼 빈 칸)
		int[] hidden = new int[level];
		HashSet<Integer> checkDuplication = new HashSet<Integer>();
		int count = 0;
		while(count != level) {
			int ran = (int) (Math.random() * word.length()); // 랜덤 숫자 추출
			if(!checkDuplication.add(ran)) {
				continue;
			}
			hidden[count] = ran;
			count++;
		}
		return new HangManVO(word, hidden);
	}
	
	
	// 유저가 레벨 설정
	private int settingLevel() {
		System.out.println("난이도 설정해주세요 [1.EASY 2. NORMAL 3. HARD] (숫자로 입력해주세요)");
		String input = sc.next();
		switch (input) {
		case "1":
			System.out.println("쉬운 난이도 입니다. 빈 칸이 하나 입니다.\n\n");
			return LEVEL_EASY; 
		case "2": 
			System.out.println("중간 난이도 입니다. 빈 칸이 두 개 입니다.\n\n");
			return LEVEL_NORMAL;
		case "3": 
			System.out.println("어려운 난이도 입니다. 빈 칸이 세 개 입니다.\n\n");
			return LEVEL_HARD;
		default:
			System.out.println("잘못된 값을 입력하셨습니다. 1,2,3 중에 입력하세요");
			return settingLevel();
		}
	}
}
