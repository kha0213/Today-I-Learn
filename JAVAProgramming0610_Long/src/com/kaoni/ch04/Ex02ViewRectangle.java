package com.kaoni.ch04;

import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;

/**
 * 
 * 
 *
 * desc 	: Swing
 * @Company : KAONI
 * @author  : 김영롱
 * @Date	: 2020. 6. 10.
 * @Version : 1.0.0
 */

public class Ex02ViewRectangle extends JFrame {

	public Ex02ViewRectangle(Rectangle userInputRectangle, Rectangle comInputRectangle) {
		final int JFRAME_WIDTH = 500;
		final int JFRAME_HEIGHT = 500;
		setTitle("컴퓨터와 유저 사각형 그리기");
		setSize(JFRAME_WIDTH, JFRAME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container contentPane = getContentPane();
		// 배치관리 제거
		contentPane.setLayout(null);
		
		// 보더 생성
		Border blackline = BorderFactory.createLineBorder(Color.black);
		Border blueline = BorderFactory.createLineBorder(Color.BLUE);
		
		
		
		// 유저와 컴퓨터 사각형 라벨 생성
		JLabel userRect = new JLabel("userRect");
		userRect.setSize(userInputRectangle.getSize());
		userRect.setLocation(userInputRectangle.getLocation());
		userRect.setBorder(blackline);
		userRect.setHorizontalAlignment(JLabel.CENTER);
		userRect.setVerticalAlignment(JLabel.CENTER);
		JLabel comRect = new JLabel("comRect");
		comRect.setSize(comInputRectangle.getSize());
		comRect.setLocation(comInputRectangle.getLocation());
		comRect.setBorder(blueline);
		comRect.setHorizontalAlignment(JLabel.CENTER);
		comRect.setVerticalAlignment(JLabel.CENTER);
		
		// 컨텐트 팬에 배치
		contentPane.add(comRect);
		contentPane.add(userRect);
	}
}
