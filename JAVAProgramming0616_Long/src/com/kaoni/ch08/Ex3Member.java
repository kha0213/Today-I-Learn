package com.kaoni.ch08;

import java.io.Serializable;
/**
 * 
 * 
 *
 * desc 	: 직렬화 MEMBER VO
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 15.
 * @Version : 1.0.0
 */
public class Ex3Member implements Serializable {
	private static final long serialVersionUID = 1L; //역직렬화 조건(없으면 자동생성이지만 필드 데이터 변경시 오류)
	private String name;
	private int age;
	private transient String gender; // 이 필드는 직렬화 되지 않음. (일시적)
	
	
	
	public Ex3Member(String name, int age, String gender) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
	}



	@Override
	public String toString() {
		return "이름 = " + name + ", 나이 = " + age + ", 성별= " + gender;
	}
	
	
}
