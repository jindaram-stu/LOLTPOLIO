package com.loltpolio.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MyPageFrame {
	JFrame frame;
	String summonerName;
	Font otherInfoFont = new Font("AppleSDGothicNeoEB00",Font.PLAIN,12);
	Font tfapplefont = new Font("AppleSDGothicNeoM00",Font.PLAIN,14);
	String path = System.getProperty("user.dir");
	
	public MyPageFrame(String summonerName) {
		this.summonerName = summonerName;
		JPanel background = new JPanel();
		background.setBackground(Color.white);
		background.setBounds(0,0,450,700);
		frame = new JFrame();
		
		init();
		
		frame.setSize(450,700);
		frame.setLayout(null);
		frame.setResizable(false); // 창 크기 조절 잠금 (창 z크기 고정) 
    	frame.setUndecorated(true); // 윈도우 창 없애기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(background);
		frame.setVisible(true);
	}
	
	public void init() {
		ImageIcon main_text_ico = new ImageIcon(path + "/img/mypage/mypage_text.png");
		ImageIcon search_tf_ico = new ImageIcon(path + "/img/mypage/search_tf.png");
		ImageIcon search_btn_ico = new ImageIcon(path + "/img/mypage/search_btn.png");
		ImageIcon setting_ico = new ImageIcon(path + "/img/mypage/setting.png");
		ImageIcon logout_ico = new ImageIcon(path + "/img/mypage/logout.png");
		ImageIcon close_ico = new ImageIcon(path + "/img/mypage");
		
		JLabel main_text = new JLabel(main_text_ico);
		main_text.setBounds(155,120,139,30);
		
		JLabel search_tf = new JLabel(search_tf_ico);
		search_tf.setBounds(39,243,308,56);
		
		JTextField s_tf = new JTextField();
		s_tf.setFont(otherInfoFont);
		s_tf.setBounds(49,265,288,30);
		s_tf.setBorder(null);
		
		JLabel search_btn = new JLabel(search_btn_ico);
		search_btn.setBounds(332,243,67,56);
		
		search_btn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					new SearchResultFrame(s_tf.getText(),1);
				} catch(Exception e1) {
					e1.printStackTrace();
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel button = (JLabel)e.getSource();
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		JLabel setting = new JLabel(setting_ico);
		setting.setBounds(39,326,360,56);
		
		setting.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println(summonerName);
				new SettingFrame(summonerName);
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel button = (JLabel)e.getSource();
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		JLabel logout = new JLabel("로그아웃");
		logout.setBounds(0,640,450,27);
		logout.setHorizontalAlignment(JLabel.CENTER);
		logout.setFont(otherInfoFont);
		
		logout.addMouseListener(new MouseListener() {

			
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel button = (JLabel)e.getSource();
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
		frame.add(s_tf);
		frame.add(main_text);
		frame.add(search_tf);
		frame.add(search_btn);
		frame.add(setting);
		frame.add(logout);
	}
	
	
}
