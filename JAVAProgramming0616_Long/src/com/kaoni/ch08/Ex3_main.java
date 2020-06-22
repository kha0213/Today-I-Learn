package com.kaoni.ch08;
/**
 * 
 * 
 *
 * desc 	: main
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 15.
 * @Version : 1.0.0
 */
public class Ex3_main {
	public static void main(String[] args) {
		// 저장할 파일 경로, 임시 객체 데이터
		final String OUT_FILE_PATH = "C:\\Long\\Today_I_Learned\\Today-I-Learn\\JAVAProgramming0615_Long\\src\\com\\kaoni\\ch08\\resource/seri.txt";
		final Ex3Member MEMBER = new Ex3Member("testMember3", 28, "여자");
		
		// 밖으로 빼낼 파일경로
		final String IN_FILE_PATH = "C:/Long/Today_I_Learned/Today-I-Learn/JAVAProgramming0615_Long/src/com/kaoni/ch08/resource/seri.txt";
		
		
		Ex3SerializationDeserializationFile ex3Test = new Ex3SerializationDeserializationFile();
		// 멤버 객체 파일 밖으로 빼기
		System.out.println("--------------------------------------------");
		System.out.println("objectOutput에서 goExportFile메소드 실행입니다.");
		System.out.println("--------------------------------------------");
		ex3Test.goExportFile(OUT_FILE_PATH, MEMBER);
		System.out.println("--------------------------------------------");
		System.out.println("objectInput에서 goImportFile메소드 실행입니다.");
		System.out.println("--------------------------------------------");
		ex3Test.goImportFile(IN_FILE_PATH);
	}
}
