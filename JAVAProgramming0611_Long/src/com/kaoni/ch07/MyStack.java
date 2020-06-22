package com.kaoni.ch07;
/**
 * 
 * 
 *
 * desc 	: MyStack 구현 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 11.
 * @Version : 1.0.0
 */
public class MyStack<T> implements IStack<T> {
	private T[] myStackArr;
	private int top; // 스택안의 자료 갯수 
	
	
	public MyStack() {
		top=0;
	}

	@Override
	public T peek() {
		if(top == 0) {
			return null;
		}
		return myStackArr[top-1];
	}
	
	// pop다시 찾아보기 
	@Override
	public T pop() {
		if(top == 0) {
			return null;
		}
		T returnObj = myStackArr[top-1];
		T[] newArr = (T[]) new Object[top-1];
		
		// 마지막 자료 빼고 배열 복사
		for(int i=0; i<top-1; i++) {
			newArr[i] = myStackArr[i];
		}
		myStackArr = newArr;
		top--; // 스택 자료 갯수 감소
		return returnObj;
	}
	
	// push, 
	@Override
	public void push(T ob) {
		T[] newArr = (T[]) new Object[top+1];
		for(int i=0; i<top; i++) {
			newArr[i] = myStackArr[i];
		}
		newArr[top] = ob;
		top++; // 스택 안의 자료 갯수 증가
		myStackArr = newArr;
	}
	
	public int getTop() {
		return top;
	}
}
