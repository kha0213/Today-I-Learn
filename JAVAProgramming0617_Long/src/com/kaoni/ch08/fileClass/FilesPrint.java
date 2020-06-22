package com.kaoni.ch08.fileClass;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 
 * 
 *
 * desc 	: file들의 정보를 출력하는 클래스
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 17.
 * @Version : 1.0.0
 */
public class FilesPrint {
	private File[] files;
	
	
	public FilesPrint(File[] files) {
		this.files = files;
	}

	/**
	 * 
	 * desc : 객체안의 files 정보 출력
	 * @Method printFilesInfo 
	 */
	public void printFilesInfo() {
		for (int i = 0; i < files.length; i++) {
			String fileInfo = fileInfo(files[i]);
			System.out.println(fileInfo);
		}
		System.out.println(files.length	+ "개의 파일이 있습니다.-----------------------------------------------------------------------------------------------");
	}
	
	/**
	 * 
	 * desc : 파일 하나 정보를 String으로 출력
	 * @Method fileInfo
	 * @param File
	 * @return String
	 * @throws
	 */
	private String fileInfo(File file) {
		// 파일 정보 꺼내서 String으로 변환
		boolean isDir = file.isDirectory();
		boolean canRead = file.canRead();
		long fileSize = file.length();
		long fileModifiedDay = file.lastModified();
		String fileName = file.getName();
		String fileInfo = fileInfoToString(isDir, canRead, fileSize, fileModifiedDay, fileName); 
		return fileInfo;
	}
	
	/**
	 * 
	 * desc : 파일들의 정보를 String으로 바꿔주는 메소드
	 * @Method fileInfoToString
	 * @param boolean isDir, boolean canRead, long fileSize, 
	 * 		  long fileModifiedDay,	String fileName
	 * @return String fileInfo
	 */
	private String fileInfoToString(boolean isDir, boolean canRead, long fileSize, long fileModifiedDay,
			String fileName) {
		String fileStatus; // 파일이 dir인지 file인지
		char fileCanRead; // 파일이 읽을 수 있는지
		if (isDir) {
			fileStatus = FileConstant.STATUS_DIR;
		} else {
			fileStatus = FileConstant.STATUS_FILE;
		}
		if (canRead) {
			fileCanRead = FileConstant.CAN_READ_FILE;
		} else {
			fileCanRead = FileConstant.CANNOT_READ_FILE;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(FileConstant.DAY_PATTERN);
		Date lastModifiedDate = new Date(fileModifiedDay);
		String fileModifiedDate = dateFormat.format(lastModifiedDate);
		String fileInfo = String.format("%-4s\t%12c%22d바이트\t\t%20s\t\t\t%-45s", fileStatus, fileCanRead, fileSize,
				fileModifiedDate, fileName);
		return fileInfo;
	}
}
