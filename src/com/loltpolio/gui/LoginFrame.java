package com.loltpolio.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.loltpolio.dao.MemberDAO;
import com.loltpolio.gui.eventlistener.CloseFrameListener;
import com.loltpolio.gui.eventlistener.WindowMoveListener;

public class LoginFrame implements Frame {
	MemberDAO dao;
	JFrame frame = null;
	String path = System.getProperty("user.dir");
	Font defaultFont = new Font("Malgun Gothic",Font.PLAIN,14);
	Font tfapplefont = new Font("AppleSDGothicNeoM00",Font.PLAIN,14);
	
	public LoginFrame() {
		dao = new MemberDAO();
		frame = new JFrame();
		
		init();
		frame.setSize(450,700);
		// frame.setLocation(2400,200);
		
		frame.setLayout(null);
		frame.setResizable(false); // 창 크기 조절 잠금 (창 z크기 고정) 
    	frame.setUndecorated(true); // 윈도우 창 없애기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(false);
		
		
	}
	
	public void init() {
		JPanel background = new JPanel();
		ImageIcon login_text = new ImageIcon(path + "/img/login/login_logo.png");
		ImageIcon id_tf_imgico = new ImageIcon(path + "/img/login/login_id_textfield.png");
		ImageIcon pwd_tf_imgico = new ImageIcon(path + "/img/login/login_pwd_textfield.png");
		ImageIcon next_button_imgico = new ImageIcon(path + "/img/login/login_nextbutton.png");
		ImageIcon register_imgico = new ImageIcon(path + "/img/login/login_register.png");
		ImageIcon close_btn_imgico = new ImageIcon(path + "/img/login/login_close.png");
		
		JPanel windowMove = new JPanel();
		windowMove.setBackground(Color.white);
		windowMove.setBounds(0,0,420,30);
		windowMove.addMouseListener(new WindowMoveListener(frame));
		windowMove.addMouseMotionListener(new WindowMoveListener(frame));
		frame.add(windowMove);
		
		background.setBackground(Color.white);
		background.setBounds(0,0,450,700);
		
		JLabel login_logo = new JLabel(login_text);
		login_logo.setBounds(183,117,84,29);
		
		JLabel id_tf = new JLabel(id_tf_imgico);
		JTextField id_tf_real = new JTextField();
		id_tf_real.setFont(tfapplefont);
		id_tf_real.setBounds(55,263,340,35);
		id_tf_real.setBorder(null);
		id_tf_real.setBackground(Color.white);
		id_tf.setBounds(45,243,360,56);
		
		JLabel pwd_tf = new JLabel(pwd_tf_imgico);
		JPasswordField pwd_tf_real = new JPasswordField();
		pwd_tf_real.setFont(tfapplefont);
		pwd_tf_real.setBounds(55,337,340,35);
		pwd_tf_real.setBorder(null);
		pwd_tf_real.setBackground(Color.white);
	
		pwd_tf.setBounds(45,317,360,56);
		
		JLabel next_button = new JLabel(next_button_imgico);
		next_button.setBounds(171,462,107,106);
		
		next_button.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String id = id_tf_real.getText();
				String pwd = pwd_tf_real.getText();
				if (dao.login(id, pwd) == 1) {
					
					String summonerName = dao.getSummonerName(id);
					MyPageFrame mypageFrame = new MyPageFrame(summonerName);
					id_tf_real.setText("");
					pwd_tf_real.setText("");
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호가 틀렸습니다.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {}

			@Override
			public void mouseReleased(MouseEvent e) {}

			@Override
			public void mouseEntered(MouseEvent e) {
				JLabel button = (JLabel)e.getSource();
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));				
			}

			@Override
			public void mouseExited(MouseEvent e) {}
			
		});
		
		JLabel register = new JLabel(register_imgico);
		register.setBounds(192,650,65,12);
		
		JLabel close_btn = new JLabel(close_btn_imgico);
		close_btn.setBounds(430,10,11,11);
		close_btn.addMouseListener(new CloseFrameListener(frame));
		
		frame.add(close_btn);
		frame.add(next_button);
		frame.add(id_tf_real);
		frame.add(id_tf);
		frame.add(pwd_tf_real);
		frame.add(pwd_tf);
		frame.add(login_logo);
		frame.add(background);

		
	}
	

	@Override
	public void on() {
		frame.setVisible(true);
	}
	
	@Override
	public void off() {
		frame.setVisible(false);
	}
	
		
	
}

