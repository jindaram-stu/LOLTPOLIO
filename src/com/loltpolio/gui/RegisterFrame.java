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

public class RegisterFrame implements Frame {
	JFrame frame = null;
	
	MemberDAO dao;
	String path = System.getProperty("user.dir");
	Font tfapplefont = new Font("AppleSDGothicNeoM00",Font.PLAIN,14);
	
	public RegisterFrame() {
		dao = new MemberDAO();
		JPanel background = new JPanel();
		background.setBackground(Color.white);
		background.setBounds(0,0,450,700);
		frame = new JFrame();
		init();
		frame.setSize(450,700);
		// frame.setLocation(2600,200);
		frame.setLocationRelativeTo(null);
		frame.setLayout(null);
		frame.setResizable(false); // 창 크기 조절 잠금 (창 크기 고정) 
    	frame.setUndecorated(true); // 윈도우 창 없애기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(background);
	}
	
	public void init() {
		ImageIcon register_logo_imgico = new ImageIcon(path + "/img/register/register_logo.png");
		ImageIcon register_close_imgico = new ImageIcon(path + "/img/register/register_close.png");
		ImageIcon register_id_tf_imgico = new ImageIcon(path + "/img/register/register_id_textfield.png");
		ImageIcon register_pwd_tf_imgico = new ImageIcon(path + "/img/register/register_pwd_textfield.png");
		ImageIcon register_summonerName_tf_imgico = new ImageIcon(path + "/img/register/register_summonerName_textfield.png");
		ImageIcon register_nextbutton_imgico = new ImageIcon(path + "/img/register/register_nextbutton.png");
		
		JPanel windowMove = new JPanel();
		windowMove.setBackground(Color.white);
		windowMove.setBounds(0,0,420,30);
		windowMove.addMouseListener(new WindowMoveListener(frame));
		windowMove.addMouseMotionListener(new WindowMoveListener(frame));
		frame.add(windowMove);
		
		JLabel register_logo = new JLabel(register_logo_imgico);
		register_logo.setBounds(170,117,111,30);
		
		JLabel register_close = new JLabel(register_close_imgico);
		register_close.setBounds(430,10,11,11);
		register_close.addMouseListener(new CloseFrameListener(frame));
		
		JLabel register_id_tf_bg = new JLabel(register_id_tf_imgico);
		register_id_tf_bg.setBounds(45,243,360,56);
		JTextField id_tf = new JTextField();
		id_tf.setFont(tfapplefont);
		id_tf.setBounds(55,267,342,27);
		id_tf.setBorder(null);
		
		JLabel register_pwd_tf_bg = new JLabel(register_pwd_tf_imgico);
		register_pwd_tf_bg.setBounds(45,317,360,56);
		JPasswordField pwd_tf = new JPasswordField();
		pwd_tf.setFont(tfapplefont);
		pwd_tf.setBounds(55,341,342,27);
		pwd_tf.setBorder(null);
		
		JLabel register_summonerName_bg = new JLabel(register_summonerName_tf_imgico);
		register_summonerName_bg.setBounds(45,390,360,78);
		JTextField summonerName_tf = new JTextField();
		summonerName_tf.setFont(tfapplefont);
		summonerName_tf.setBounds(55,414,342,27);
		summonerName_tf.setBorder(null);
		
		JLabel register_nextbutton = new JLabel(register_nextbutton_imgico);
		register_nextbutton.setBounds(171,526,107,106);
		
		register_nextbutton.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String id = id_tf.getText();
				String pwd = pwd_tf.getText();
				String summonerName = summonerName_tf.getText();
				if (dao.idExist(id) == 1 || dao.summonerNameExist(summonerName) == 1) {
					JOptionPane.showMessageDialog(null, "아이디 혹은 소환사 이름이 중복되었습니다.","ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					dao.join(id,pwd,summonerName);
					JOptionPane.showMessageDialog(null, "회원가입을 성공했습니다.");
					frame.dispose();
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
		
		frame.add(id_tf);
		frame.add(pwd_tf);
		frame.add(summonerName_tf);
		
		frame.add(register_close);
		frame.add(register_nextbutton);
		frame.add(register_id_tf_bg);
		frame.add(register_pwd_tf_bg);
		frame.add(register_summonerName_bg);
		frame.add(register_logo);
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


