package com.kaoni.ch08.fileClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ex2_fileCopy {
	public static void main(String[] args) {
		final String ORI_FILE_PATH = "C:\\Long\\Today_I_Learned\\Today-I-Learn\\JAVAProgramming0615_Long\\src\\com\\kaoni/ch08/resource/java.jpg"; 
		final String NEW_FILE_PATH = "C:\\Long\\Today_I_Learned\\Today-I-Learn\\JAVAProgramming0615_Long\\src\\com\\kaoni/ch08/resource/javaClone.jpg"; 
		
		File oriFile = new File(ORI_FILE_PATH);  		
		
		System.out.println("파일 복사 입니다.");
		fileCopy(oriFile, NEW_FILE_PATH);
	}
	
	
	public static void fileCopy(File file, String copyPath) {
		final int FILE_READ_END = -1;
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			fis = new FileInputStream(file);
			fos = new FileOutputStream(copyPath);
			byte[] fRead = new byte[(int) file.length()];

			int readInt = 0;
			while(readInt != FILE_READ_END) {
				readInt = fis.read(fRead);
				if(readInt == -1) break;
				fos.write(fRead);
			}
			System.out.println("복사 성공");
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException : "+e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException : "+e.getMessage());
		} finally {
			try {
				if(fos != null) fos.close();
				if(fis != null) fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
