package com.kaoni.ch09;

/**
 * 
 * 
 *
 * desc : 상수 모음
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 18.
 * @Version : 1.0.0
 */
public class TCons {
	public static final boolean SYSTEM_CLOSE = false;
	public static final int THREAD_SHUTDOWN = 5; // 타이머 종료할 단위(초)
	public static final int THREAD_COUNT_IN_THREAD_POOL = 3; // 쓰레드 풀 안의 쓰레드 갯수 
	public static final int WARKING_COUNT = 10; // 동시 작업 수
	public static final String TIMER_COMMAND = "1";
	public static final String THREAD_POOL_COMMAND = "2";
	public static final String EXIT_COMMAND = "그만";
}
