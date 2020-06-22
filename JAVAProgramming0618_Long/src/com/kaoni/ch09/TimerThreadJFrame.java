package com.kaoni.ch09;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * 
 *
 * desc : 타이머를 위한 JFrame
 * 
 * @Company : KAONI
 * @author : 김영롱
 * @Date : 2020. 6. 18.
 * @Version : 1.0.0
 */
public class TimerThreadJFrame extends JFrame {
	private static final long serialVersionUID = -8102393181290708523L;
	private final int FRAME_WIDTH = 300;
	private final int FRAME_HEIGHT = 170;
	private Container container;
	private JLabel timerLabel;

	public TimerThreadJFrame() {
		this.setTitle(this.getName() + "의 타이머");
		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x누르면 종료
		container = getContentPane();
		container.setLayout(new FlowLayout());
		timerLabel = new JLabel();
		timerLabel.setFont(new Font("Gothic", Font.ITALIC, 80));
		container.add(timerLabel);
	}

	public JLabel getTimerLabel() {
		return timerLabel;
	}

	public void setTimerLabel(JLabel timerLabel) {
		this.timerLabel = timerLabel;
	}

}
