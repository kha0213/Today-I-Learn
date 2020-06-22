package com.kaoni.ch09;

import javax.swing.JLabel;
/**
 * 
 * 
 *
 * desc 	: Thread 연습 
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 17.
 * @Version : 1.0.0
 */
public class TimerThread extends Thread {
	private TimerThreadJFrame timerJFrame;
	/**
	 * 
	 * desc : 생성자함수 JLabel 세팅
	 * @param JLabel
	 */
	public TimerThread(TimerThreadJFrame timerJFrame) {
		this.timerJFrame = timerJFrame; // 스윙
		this.setDaemon(true); // 데몬쓰레드로 변환
	}
	
	public void goTimer() {
		timerJFrame.setTitle(this.getName() + "의 타이머");
		timerJFrame.setSize(300, 170);
		timerJFrame.setVisible(true);
		this.start();
	}
	
	
	/**
	 * 
	 * desc : 스레드 실행
	 * @Method run
	 */
	@Override
	public void run() {
		int n = 0;
		while(true) {
			JLabel timerLabel = timerJFrame.getTimerLabel();
			timerLabel.setText(Integer.toString(n));
			n++;
			if(n>ThreadProgramCons.THREAD_SHUTDOWN) {
				interrupt();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.out.println(this.getName()+"쓰레드 종료");
				timerJFrame.dispose();
				return;
			}
		}
	}
}
