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

import com.loltpolio.gui.eventlistener.CloseButtonListener;
import com.loltpolio.gui.eventlistener.FrameOnListener;
import com.loltpolio.gui.eventlistener.SearchButtonListener;
import com.loltpolio.gui.eventlistener.WindowMoveListener;

public class MainFrame{
	RegisterFrame registerFrame = new RegisterFrame();
	LoginFrame loginFrame = new LoginFrame();
	
	JFrame frame;
	String path = System.getProperty("user.dir");
	Font applefont = new Font("AppleSDGothicNeoM00",Font.PLAIN,13);
	Font tfapplefont = new Font("AppleSDGothicNeoB00",Font.PLAIN,14);
	Color mainColor = new Color(102,142,253,250);
	
	public MainFrame() {
		frame = new JFrame();
		
		JPanel background = new JPanel();
		background.setBackground(mainColor);
		background.setBounds(0,0,450,700);
		init();
		frame.setSize(450,700);
		frame.setLayout(null);
		frame.setResizable(false); // 창 크기 조절 잠금 (창 크기 고정) 
		frame.setUndecorated(true); // 윈도우 창 없애기
    	
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	
		frame.add(background);
		// frame.setLocation(2100,200);
		frame.setLocationRelativeTo(null);
		
	}
	
	public void init() {
		ImageIcon logo_ico = new ImageIcon(path + "/img/index/logo.png");
		ImageIcon sublogo_ico = new ImageIcon(path + "/img/index/sublogo.png");
		ImageIcon search_tf_bg_ico = new ImageIcon(path + "/img/index/search_tf.png");
		ImageIcon search_button_ico = new ImageIcon(path + "/img/index/search_button.png");
		ImageIcon login_button_ico = new ImageIcon(path + "/img/index/login_button.png");
		ImageIcon register_button_ico = new ImageIcon(path + "/img/index/register_button.png");
		ImageIcon copyright_ico = new ImageIcon(path + "/img/index/copyright.png");
		ImageIcon close_button_ico = new ImageIcon(path + "/img/index/close_button.png");
		
		JPanel windowMove = new JPanel();
		windowMove.setBackground(mainColor);
		windowMove.setBounds(0,0,420,30);
		windowMove.addMouseListener(new WindowMoveListener(frame));
		windowMove.addMouseMotionListener(new WindowMoveListener(frame));
		frame.add(windowMove);
		
		JLabel logo = new JLabel(logo_ico);
		logo.setBounds(64,151,322,125);
		
		JLabel sublogo = new JLabel(sublogo_ico);
		sublogo.setBounds(124,294,202,14);
		
		JLabel search_tf_bg = new JLabel(search_tf_bg_ico);
		
		JTextField search_tf = new JTextField();
		search_tf.setBackground(Color.white);
		search_tf.setBorder(null);
		search_tf.setBounds(52,405,350,30);
		search_tf.setFont(applefont);
		search_tf_bg.setBounds(45,388,360,49);
		
		JLabel search_button = new JLabel(search_button_ico);
		search_button.setBounds(147,449,155,39);
		
		search_button.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {	
				try {
					frame.setVisible(false);
					new SearchResultFrame(search_tf.getText(),0);
				} catch (Exception e1) {
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
		
		JLabel login_button = new JLabel(login_button_ico);
		login_button.setBounds(124,550,75,27);
		login_button.addMouseListener(new FrameOnListener(loginFrame,frame));
		
		JLabel register_button = new JLabel(register_button_ico);
		register_button.setBounds(242,550,75,27);
		register_button.addMouseListener(new FrameOnListener(registerFrame,frame));
		
		JLabel copyright = new JLabel(copyright_ico);
		copyright.setBounds(65,638,320,31);
		
		JLabel close_button = new JLabel(close_button_ico);
		close_button.setBounds(430,10,11,11);
		close_button.addMouseListener(new CloseButtonListener());
		
		frame.add(logo);
		frame.add(sublogo);
		frame.add(search_tf);
		frame.add(search_tf_bg);
		frame.add(search_button);
		frame.add(login_button);
		frame.add(register_button);
		frame.add(copyright);
		frame.add(close_button);
	}
	
	public void showFrame() {
		frame.setVisible(true);
	}
	
	public void hideFrame() {
		frame.setVisible(false);
	}
	
	
	
	public static void main(String[] args) {
		MainFrame mainFrame = new MainFrame();
		mainFrame.showFrame();
	}
}
