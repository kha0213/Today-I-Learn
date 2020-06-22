package com.kaoni.ch08.fileClass;

import java.io.File;
import java.io.IOException;
import java.util.Date;
/**
 * 
 * 
 *
 * desc 	: File 객체 공부
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 15.
 * @Version :
 */
public class Ex1_File {
	public static void main(String[] args) {
		final boolean NON_EXIST = false;
		
		final String FILE_PATH = "C:\\Long\\Today_I_Learned\\Today-I-Learn\\JAVAProgramming0615_Long\\src\\com\\kaoni\\ch08/resource";
		final String CREATE_FILE = "C:\\Long\\Today_I_Learned\\Today-I-Learn\\JAVAProgramming0615_Long\\src\\com\\kaoni\\ch08/resource/file1.txt";
		File dir = new File(FILE_PATH);
		File txtFile = new File(CREATE_FILE);

		
		if(txtFile.exists() == NON_EXIST) {
			System.out.println("txtFile이 존재하지 않습니다. 파일을 생성합니다.");
			try {
				txtFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		printFileInfo(txtFile);
		printFileInfo(dir);
		
	}
	
	public static void printFileInfo(File file) {
		System.out.println("파일 정보 안내입니다.");
		System.out.println("파일명 : "+file.getName());
		System.out.println("파일경로 : "+file.getPath());
		System.out.println("파일 존재여부 : "+file.exists());
		System.out.println("파일 디렉토리여부 : "+file.isDirectory());
		System.out.println("파일 크기 : "+file.length());
		System.out.println("마지막 수정 날짜 : "+new Date(file.lastModified()));
		System.out.println("------------------------------");
	}
	
	
}
