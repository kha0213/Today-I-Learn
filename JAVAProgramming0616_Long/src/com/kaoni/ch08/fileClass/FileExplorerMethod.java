package com.kaoni.ch08.fileClass;

import java.io.File;
import java.util.Scanner;

/**
 * 
 *
 * desc : FileExplorer 소스
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 15.
 * @Version : 1.0.0
 */
public class FileExplorerMethod {
	private File currentFilePath;

	/**
	 * 
	 * 
	 *
	 * desc : 기본 파일객체 생성
	 * 
	 * @Company : KAONI
	 * @author : 김영롱
	 * @Date : 2020. 6. 16.
	 * @Version : 1.0.0
	 */
	public FileExplorerMethod() {
		// 기본 파일객체 생성
		currentFilePath = new File(FileConstant.START_DIR); // START_DIR = "C:/Long"
	}

	/**
	 * 
	 * desc : 파일 관리자 프로그램 시작
	 * 
	 * @Method startProgram
	 */
	public void startProgram() {
		Scanner sc = new Scanner(System.in);
		System.out.println("파일 관리자 시스템 시작합니다.");
		while (true) {
			printFileList(currentFilePath); // 파일 리스트들 출력
			String command = userCommand(sc); // 유저 명령어 받기
			commandExecute(command); // 유저 커멘드 실행
		}
	}

	/**
	 * 
	 * desc : 파일 리스트 출력
	 * 
	 * @Method printFileList
	 * @param : File
	 */
	public void printFileList(File dir) {
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("현재위치 : [" + dir.getPath() + "]");
		System.out.println("파일 상태\t파일크기\t\t\t파일이름");

		File[] subFile = dir.listFiles(); // 파일 리스트들 리턴
		//dir과 비슷하게 fileSize메소드, 효과 변경
		for (int i = 0; i < subFile.length; i++) {
			String booleanDir = subFile[i].isDirectory() ? "dir" : "file"; // dir인지 file인지
			String fileSize = subFile[i].length() >= 1000 ? "" : "\t"; // console 창 출력위한 메소드이름
			System.out.println(
					booleanDir + "\t" + subFile[i].length() + "바이트\t\t" + fileSize + subFile[i].getName() + "\t\t");
		}
		System.out.println(subFile.length
				+ "개의 파일이 있습니다.---------------------------------------------------------------------------");
	}

	/**
	 * 
	 * desc : 유저 커멘드 입력
	 * 
	 * @Method userCommand
	 * @param Scanner
	 * @return String
	 */
	public String userCommand(Scanner sc) {
		System.out.println("명령어를 입력해주세요.");
		System.out.println(
				"[..] 상위폴더로\t[dir이름] 해당 디렉터리 이동\t[rename A B] A파일을 B이름으로 변경\t[mkdir A] A라는 디렉토리 생성\t[그만] 시스템종료");
		System.out.print(">>");
		String userCommand = sc.nextLine(); // 유저 명령어
		System.out.println("\n\n\n\n");
		return userCommand;
	}

	/**
	 * 
	 * desc : 커멘드 실행
	 * 예외처리 하자
	 * @Method commandExecute
	 * @param String
	 */
	public void commandExecute(String userCommand) {
		String[] command = userCommand.split(" ");
		try {
			switch (command[0]) {
			case "..":
				previousDir(); // 상위 디렉터리 가는 메소드
				break;
			case "mkdir":
				mkDir(command[1]); // 하위 디렉토리 생성 메소드
				break;
			case "rename":
				fileRename(command[1], command[2]); // 파일 || 디렉토리 이름 변경 메소드
				break;
			case "그만":
				System.out.println("★★★★시스템을 종료합니다.★★★★");
				System.exit(FileConstant.EXIT); // 시스템 종료

				// 디렉토리 이동 or 잘못된 명령어
			default:
				goDir(userCommand);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("★★★★명령어를 잘못 입력하셨습니다. 다시 입력해주세요.★★★★");
		}
	}

	/**
	 * 
	 * desc : 상위 디렉토리로 이동
	 * 
	 * @Method previousDir
	 */
	public void previousDir() {
		File parentFilStr = currentFilePath.getParentFile();
		if (parentFilStr == null) {
			System.out.println("★★★★상위 디렉토리가 없습니다. 다시 입력해주세요★★★★");
		} else {
			System.out.println("★★★★상위 디렉토리로 이동합니다.★★★★");
			currentFilePath = parentFilStr;
		}
	}

	/**
	 * 
	 * desc : 입력한 디렉토리로 이동
	 * 
	 * @Method goDir
	 */
	public void goDir(String userCommand) {
		// 임시 파일 객체 생성
		File tempFile = new File(currentFilePath.getPath() + "/" + userCommand);
		if (!tempFile.exists()) { // 파일이 존재하지 않으면
			System.out.println("★★★★파일이 존재하지 않습니다. 명령어를 다시 입력해주세요.★★★★");
		} else if (!tempFile.isDirectory()) { // 파일이 존재하나 디렉토리가 아니면
			System.out.println("★★★★해당 파일은 디렉토리가 아니어서 이동할 수 없습니다. 명령어를 다시 입력해주세요★★★★");
		} else { // 파일이 디렉토리이면
			currentFilePath = tempFile; // 현재 파일 위치를 변경
			System.out.println("★★★★" + tempFile.getName() + "으로 이동하였습니다.★★★★");
		}
	}

	/**
	 * 
	 * desc : 새로운 디렉토리 생성
	 * 
	 * @Method mkDir
	 * @param String
	 */
	public void mkDir(String dirName) {
		// 임시 파일 객체 생성
		File tempFile = new File(currentFilePath.getPath() + "/" + dirName);
		if (tempFile.exists()) { // 파일이 존재하면
			System.out.println("★★★★해당 파일이 이미 존재합니다. 명령어를 다시 입력해주세요.★★★★");
		} else if (tempFile.mkdir()) { // 파일 생성 mkdir 메소드
			System.out.println("★★★★" + tempFile.getPath() + "에 디렉토리가 생성되었습니다.★★★★");
		} else { // 여기로 빠지면 오류 입니다.
			System.out.println("★★★★디렉토리가 생성되지 않았습니다. 관리자에게 문의해주세요.★★★★");
		}
	}

	/**
	 * 
	 * desc : 파일이름 변경
	 * File.separator
	 * @Method fileRename
	 * @param String, String
	 */
	public void fileRename(String oriFileName, String newFileName) {
		// 임시 파일 객체 생성 (원래 파일은 존재해야하고 새로운 파일명은 존재하지 않아야 한다.)
		File tempOriFile = new File(currentFilePath.getPath() + File.separator + oriFileName);
		File tempNewFile = new File(currentFilePath.getPath() + File.separator + newFileName);

		if (!tempOriFile.exists()) { // 파일이 존재하지 않으면
			System.out.println("★★★★기존 파일이 존재하지 않습니다. 명령어를 다시 입력해주세요.★★★★");
		} else if (tempNewFile.exists()) { // 바꾸려는 파일명이 중복일 경우
			System.out.println("★★★★변경하려는 파일명이 중복입니다.명령어를 다시 입력해주세요.★★★★");
		} else {
			tempOriFile.renameTo(tempNewFile); //boolean 체크
			System.out.println("★★★★기존파일 : [" + oriFileName + "] 에서 [" + newFileName + "] 으로 변경하였습니다.★★★★");
		}
	}
}
