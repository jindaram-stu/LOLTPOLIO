package com.loltpolio.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoadingFrame extends JFrame{
	Color background = new Color(0,0,0,100);
	
	JLabel text = new JLabel("소환사 정보를 불러오고 있습니다.");
	
	String path = System.getProperty("user.dir");
	Font queueFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,21);
	ImageIcon logo = new ImageIcon(path + "/img/loading/logo.png");
	
	public LoadingFrame() {
		
		JLabel logo_label = new JLabel(logo);
		logo_label.setBounds(320,263,65,26);
		
		
		text.setFont(queueFont);
		text.setBounds(0,135,400,30);
		text.setHorizontalAlignment(JLabel.CENTER);
		text.setForeground(Color.white);
		setLayout(null);
		setResizable(false); // 창 크기 조절 잠금 (창 크기 고정) 
		setUndecorated(true); // 윈도우 창 없애기
		setBackground(background);
		setSize(400,300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(logo_label);
		add(text);
		setVisible(true);
		
		
	}
	
	public void setLoadingText(String drtext) {
		text.setText(drtext);
		System.out.println(drtext);
	}
	
	public void hideFrame() {
		dispose();
	}
	
	public void showFrame() {
		setVisible(true);
	}

	
	
}
