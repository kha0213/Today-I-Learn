package com.kaoni.ch08;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
/**
 * 
 * 
 *
 * desc 	: 직렬화, 역직렬화 연습 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 15.
 * @Version : 1.0.0
 */
public class Ex3SerializationByteStream {
	public static void main(String[] args) {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		
		Ex3Member member = new Ex3Member("testMember1", 19, "남자");
		
		
		byte[] serialMember = null; // 직렬화 byte[]
		byte[] deserialMember = null; //역직렬화 byte[]
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(member);
			serialMember = bos.toByteArray();
			String base64Member = Base64.getEncoder().encodeToString(serialMember);
			System.out.println(base64Member); // 직렬화
			deserialMember = Base64.getDecoder().decode(base64Member);
			
			bis = new ByteArrayInputStream(deserialMember);
			ois = new ObjectInputStream(bis);
			//역직렬화된 Member객체를 읽어온다.
			Object objectMember = ois.readObject();
			Ex3Member tempMember = (Ex3Member) objectMember;
			System.out.println(tempMember);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
				try {
					if(ois != null) ois.close();
					if(bis != null) bis.close();
					if(oos != null) oos.close();
					if(bos != null) bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
}
