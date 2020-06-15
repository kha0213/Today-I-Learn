package com.kaoni.ch07;
/**
 * 
 * 
 *
 * desc 	: Stack 인터페이스 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public interface IStack<T> {
	T pop();
	T peek();
	void push(T ob);
}
