package com.loltpolio.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.loltpolio.dao.MemberDAO;
import com.loltpolio.dto.SettingDTO;
import com.loltpolio.gui.eventlistener.CloseFrameListener;

public class SettingFrame {
	JFrame frame;
	MemberDAO dao;
	
	
	String summonerName;
	String[] positionList = {"탑","정글","미드","원딜","서포터"};
	String path = System.getProperty("user.dir");
	Font otherInfoFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,14);
	public SettingFrame(String summonerName) {
		this.summonerName = summonerName;
		dao = new MemberDAO();
		SettingDTO dto = dao.getSetting(summonerName);
		frame = new JFrame();
		JPanel background = new JPanel();
		background.setBackground(Color.white);
		background.setBounds(0,0,450,700);
		
		ImageIcon setting_text_ico = new ImageIcon(path + "/img/setting/setting_text.png");
		ImageIcon test_ico = new ImageIcon(path + "/img/setting/TEST.png");
		ImageIcon save_ico = new ImageIcon(path + "/img/setting/save.png");
		ImageIcon cancel_ico = new ImageIcon(path + "/img/setting/cancel.png");
		// 51 233
		
		JLabel test = new JLabel(test_ico);
		test.setBounds(51,233,347,320);
		
		JLabel setting_text = new JLabel(setting_text_ico);
		setting_text.setBounds(152,120,148,30);
		// 연락처
		JLabel mail = new JLabel("이메일");
		JTextField mail_tf = new JTextField();
		mail_tf.setBounds(117,233,281,29);
		mail_tf.setFont(otherInfoFont);
		
		JLabel discord = new JLabel("디스코드");
		JTextField discord_tf = new JTextField();
		discord_tf.setBounds(117,277,281,29);
		discord_tf.setFont(otherInfoFont);
		
		JLabel sns = new JLabel("SNS");
		JTextField sns_tf = new JTextField();
		sns_tf.setBounds(117,321,281,29);
		sns_tf.setFont(otherInfoFont);
		
		
		JComboBox mainPos = new JComboBox(positionList);
		mainPos.setBounds(137,379,77,20);
		mainPos.setBackground(Color.white);
		mainPos.setFont(otherInfoFont);
		JComboBox subPos = new JComboBox(positionList);
		subPos.setBackground(Color.white);
		subPos.setBounds(321,379,77,20);
		subPos.setFont(otherInfoFont);
		
		JTextArea intro = new JTextArea();
		intro.setBounds(53,423,345,130);
		intro.setBorder(new LineBorder(new Color(146,146,146)));
		intro.setFont(otherInfoFont);
		
		JLabel save = new JLabel(save_ico);
		save.setBounds(127,599,87,26);
		
		JLabel cancel = new JLabel(cancel_ico);
		cancel.setBounds(236,599,87,26);
		
		save.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String mail = mail_tf.getText();
				String discord = discord_tf.getText();
				String sns = sns_tf.getText();
				String main = mainPos.getSelectedItem().toString();
				String sub = subPos.getSelectedItem().toString();
				String intr = intro.getText();
				
				System.out.println(mail + " " + discord + " " + sns  + " " + main  + " " + sub  + " " + intr);
				dao.save(summonerName, mail, discord, sns, main, sub, intr);
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
		
		cancel.addMouseListener(new CloseFrameListener(frame));
		
		mail_tf.setText(dto.getMail());
		discord_tf.setText(dto.getDiscord());
		sns_tf.setText(dto.getSns());
		
		mainPos.setSelectedItem(dto.getMainPos().toString());
		subPos.setSelectedItem(dto.getSubPos().toString());
		
		intro.setText(dto.getIntro());
		
		frame.add(save);
		frame.add(cancel);
		frame.add(mail_tf);
		frame.add(setting_text);
		frame.add(mail);
		frame.add(discord);
		frame.add(discord_tf);
		frame.add(sns);
		frame.add(sns_tf);
		frame.add(mainPos);
		frame.add(subPos);
		frame.add(intro);
		frame.add(test);
		frame.add(background);
		frame.setSize(450,700);
		frame.setLayout(null);
		frame.setResizable(false); // 창 크기 조절 잠금 (창 z크기 고정) 
    	frame.setUndecorated(true); // 윈도우 창 없애기
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		new SettingFrame("진 다람");
	}
}
