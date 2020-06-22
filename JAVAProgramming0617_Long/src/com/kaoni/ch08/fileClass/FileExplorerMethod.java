package com.kaoni.ch08.fileClass;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * 
 *
 * desc : FileExplorer 소스
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 16.
 * @Version : 1.0.0
 */
public class FileExplorerMethod {
	private File currentFilePath;

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
	private void printFileList(File dir) {
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------");
		System.out.println("현재위치 : [" + dir.getPath() + "]");
		System.out.println("파일상태\t\t읽기여부\t\t\t파일 크기\t\t\t수정 날짜\t\t\t\t파일 이름");

		File[] subFile = dir.listFiles(); // 파일 리스트들 리턴
		FilesPrint filesPrint = new FilesPrint(subFile);
		filesPrint.printFilesInfo();
	}

	/**
	 * 
	 * desc : 유저 커멘드 입력
	 * 
	 * @Method userCommand
	 * @param Scanner
	 * @return String
	 */
	private String userCommand(Scanner sc) {
		System.out.println("\n명령어를 입력해주세요.");
		System.out.println(
				"[..] 상위폴더로\t[파일이름] 해당 디렉터리 이동 or txt 파일 실행\t[rename A B] A파일을 B이름으로 변경\t[mkdir A] A라는 디렉토리 생성\t[그만] 시스템종료");
		System.out.print(">>");
		String userCommand = sc.nextLine(); // 유저 명령어
		System.out.println("\n\n\n\n");
		return userCommand;
	}

	/**
	 * 
	 * desc : 커멘드 실행 예외처리 하자
	 * 
	 * @Method commandExecute
	 * @param String
	 */
	private void commandExecute(String userCommand) {
		StringTokenizer tokenizer = new StringTokenizer(userCommand);
		ArrayList<String> command = new ArrayList<String>();
		while (tokenizer.hasMoreTokens()) {
			command.add(tokenizer.nextToken());
		}

		try {
			switch (command.get(0)) {
			case "..":
				previousDir(); // 상위 디렉터리 가는 메소드
				break;
			case "mkdir":
				if(command.size() == 1) { //mkdir 폴더 mkdir a 
					goDirOrReadFile(userCommand);
					break;
				}
				String fileName = command.get(1);
				boolean existSymbol1 = checkSymbolInFile(fileName);
				if (existSymbol1) {
					System.out.println("★★★★파일 이름에 특수기호를 사용할 수 없습니다.★★★★");
				} else {
					mkDir(command.get(1)); // 하위 디렉토리 생성 메소드
				}
				break;
			case "rename":
				if(command.size() == 1) {
					goDirOrReadFile(userCommand);
					break;
				}
				String fileRename = command.get(2);
				boolean existSymbol2 = checkSymbolInFile(fileRename);
				if (existSymbol2) {
					System.out.println("★★★★파일 이름에 특수기호를 사용할 수 없습니다.★★★★");
				} else {
					fileRename(command.get(1), fileRename); // 파일 || 디렉토리 이름 변경 메소드
				}
				break;
			case "그만":
				System.out.println("★★★★시스템을 종료합니다.★★★★");
				System.exit(FileConstant.EXIT); // 시스템 종료

				// 디렉토리 이동 or 잘못된 명령어
			default:
				goDirOrReadFile(userCommand);
			}
		} catch (Exception e) {
			System.out.println("★★★★명령어를 잘못 입력하셨습니다. 다시 입력해주세요.★★★★");
		}
	}

	/**
	 * 
	 * desc : 파일이름 변경 or 디렉토리 생성시 특수기호 여부 확인
	 * 
	 * @Method checkSymbolInFile
	 * @param String
	 * @return boolean
	 */
	private boolean checkSymbolInFile(String content) {
		boolean existSymbol = content.matches(FileConstant.REGEX_EXIST_SYMBOL);
		return existSymbol;
	}

	/**
	 * 
	 * desc : 상위 디렉토리로 이동
	 * 
	 * @Method previousDir
	 */
	private void previousDir() {
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
	 * desc : 입력한 디렉토리로 이동 or txt파일 읽기
	 * 
	 * @Method goDirOrReadFile
	 */
	private void goDirOrReadFile(String userCommand) {
		// 임시 파일 객체 생성
		File tempFile = new File(currentFilePath.getPath() + "/" + userCommand);
		if (!tempFile.exists()) { // 파일이 존재하지 않으면
			System.out.println("★★★★파일이 존재하지 않습니다. 명령어를 다시 입력해주세요.★★★★");
			return;
		}
		if (tempFile.isDirectory()) { // 디렉토리 이면
			currentFilePath = tempFile; // 현재 파일 위치를 변경
			System.out.println("★★★★" + tempFile.getName() + "으로 이동하였습니다.★★★★");
		} else { // 파일이면
			boolean isTxtFile = isTxtFile(userCommand);
			if (isTxtFile) {
				System.out.println("★★★★[" + tempFile.getName() + "]파일을 읽겠습니다.★★★★");
				readFile(tempFile);
			} else {
				System.out.println("★★★★해당 파일은 txt파일이 아니어서 읽을 수 없습니다. 명령어를 다시 입력해주세요.★★★★");
			}
		}
	}

	/**
	 * 
	 * desc : 파일 이름으로 txt확장자인지 구별해주는 메소드
	 * 
	 * @Method isTxtFile
	 * @param String fileName
	 * @return boolean
	 */ // bat log csv 확장자 체크 명령어?
	private boolean isTxtFile(String fileName) {
		boolean result = false;
		
		int nameLength = fileName.length();
		if (nameLength > FileConstant.FILE_MIN_LENGTH) { // 파일이 최소 4 이상이어야 .txt확인 가능
			String fileExtension = fileName.substring(nameLength - FileConstant.FILE_MIN_LENGTH);
			if (fileExtension.equals(".txt")) { // 확장자가 txt인지 확인
				result = true;
			}
		}
		return result;
	}

	/**
	 * 
	 * desc : txt파일 일 경우 콘솔에 출력해주는 메소드
	 * 
	 * @Method readFile
	 * @param File
	 */
	private void readFile(File file) {
		Reader reader = null;
		try {
			reader = new FileReader(file);
			char[] cbuf = new char[(int) file.length()];
			while (true) {
				int readInt = reader.read(cbuf);
				if (readInt == -1) {
					break;
				}
			}
			System.out.println(String.valueOf(cbuf));
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : " + e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException : " + e.getMessage());
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 
	 * desc : 새로운 디렉토리 생성
	 * 
	 * @Method mkDir
	 * @param String
	 */
	private void mkDir(String dirName) {
		// 임시 파일 객체 생성
		File tempFile = new File(currentFilePath.getPath() + File.separator + dirName);
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
	 * desc : 파일이름 변경 File.separator
	 * 
	 * @Method fileRename
	 * @param String, String
	 */
	private void fileRename(String oriFileName, String newFileName) {
		// 임시 파일 객체 생성 (원래 파일은 존재해야하고 새로운 파일명은 존재하지 않아야 한다.)
		File tempOriFile = new File(currentFilePath.getPath() + File.separator + oriFileName);
		File tempNewFile = new File(currentFilePath.getPath() + File.separator + newFileName);

		if (!tempOriFile.exists()) { // 파일이 존재하지 않으면
			System.out.println("★★★★기존 파일이 존재하지 않습니다. 명령어를 다시 입력해주세요.★★★★");
		} else if (tempNewFile.exists()) { // 바꾸려는 파일명이 중복일 경우
			System.out.println("★★★★변경하려는 파일명이 중복입니다.명령어를 다시 입력해주세요.★★★★");
		} else {
			tempOriFile.renameTo(tempNewFile); // boolean 체크
			System.out.println("★★★★기존파일 : [" + oriFileName + "] 에서 [" + newFileName + "] 으로 변경하였습니다.★★★★");
		}
	}
}
