package com.kaoni.ch08;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * 
 * 
 *
 * desc 	: 직렬화, 역직렬화
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 15.
 * @Version : 1.0.0
 */
public class Ex3SerializationDeserializationFile {
	
	// final 객체를 파일에 저장
	public void goExportFile(String outFilePath, Ex3Member member) {
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(outFilePath); // outFilePath에 파일 추출
			oos = new ObjectOutputStream(fos);
			exportFile(fos, oos, member); // 객체를 파일에 저장
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 객체를 파일에 저장하는 메소드
	private void exportFile(FileOutputStream fos, ObjectOutputStream oos, Ex3Member member) throws IOException {
		System.out.println("파일 복사를 시작합니다.");
		System.out.println("복사할 객체 : "+member);
		oos.writeObject(member);
		oos.flush();
		try {
			if (oos != null)
				oos.close();
			if (fos != null)
				fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("파일 복사가 완료되었습니다.");
	}
	
	// 지정된 경로에서 파일 읽기
	public void goImportFile(String inFilePath) {
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream(inFilePath);
			ois = new ObjectInputStream(fis);
			importFile(fis, ois); // 파일에서 객체 추출
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 지정된 경로에서 파일 추출 메소드
	private void importFile(FileInputStream fis, ObjectInputStream ois) throws Exception {
		System.out.println("파일 추출을 시작합니다.");
		Ex3Member member = (Ex3Member) ois.readObject();
		System.out.println("추출된 객체자료 정보 출력합니다.");
		System.out.println(member);
		try {
			if(ois != null) ois.close();
			if(fis != null) fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
