package com.kaoni.ch09;

import javax.swing.JLabel;

/**
 * 
 * 
 *
 * desc : Thread 연습
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 18.
 * @Version : 1.0.0
 */
public class TimerThread extends Thread {
	private TotalSecond total;
	private TimerThreadJFrame timerJFrame;

	/**
	 * 
	 * desc : 생성자함수 JLabel 세팅
	 * 
	 * @param JLabel
	 */
	public TimerThread(TimerThreadJFrame timerJFrame) {
		this.timerJFrame = timerJFrame; // 스윙
		this.setDaemon(true); // 데몬쓰레드로 변환
		total = TotalSecond.getTotalClass();
	}

	public void goTimer() {
		this.start();
	}

	/**
	 * 
	 * desc : 공유변수 totalCount 하나 증가
	 * 
	 * @Method totalUp :
	 */
	private synchronized void totalUp() { // 마지막
		int totalCount = total.getTotalSecond();
		totalCount++;
		total.setTotalSecond(totalCount);
	}

	/**
	 * 
	 * desc : 스레드 실행 (스윙안의 Timer 변경)
	 * 
	 * @Method run
	 */
	@Override
	public void run() {
		int printNumber = 0; // 
		while (true) {
			JLabel timerLabel = timerJFrame.getTimerLabel();
			timerLabel.setText(Integer.toString(printNumber));
			printNumber++;
			// 공유변수 +1
			totalUp();

			if (printNumber >= TCons.THREAD_SHUTDOWN) {
				interrupt();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(this.getName() + "쓰레드 종료, 현재 공유변수 [" + total.getTotalSecond() + "]");
				timerJFrame.dispose();
				return;
			}
		}
	}
}
