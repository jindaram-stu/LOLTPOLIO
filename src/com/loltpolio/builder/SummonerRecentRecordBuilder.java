package com.loltpolio.builder;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.loltpolio.riot.dto.SummonerRecentRecordDTO;

public class SummonerRecentRecordBuilder {
	String path = System.getProperty("user.dir");
	
	Font kdaFont = new Font("AppleSDGothicNeoE00",Font.PLAIN,18);
	Font dateFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,14);
	Font rateFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,14);
	public JPanel[] getRecentRecordArray(SummonerRecentRecordDTO[] dto) {
		JPanel[] rrList = new JPanel[5];
		
		for(int i=0; i<5; i++) {
			JPanel recordPanel = new JPanel();
			recordPanel.setLayout(null);
			recordPanel.setBounds(392 + i*129,0,129,303);
			ImageIcon champ_imgico = new ImageIcon(path + "/img/result/record/" + dto[i].getChampion()+".jpg");
			JLabel champ_label = new JLabel(champ_imgico);
			champ_label.setBounds(0,0,129,303);
			
			JLabel kda = new JLabel(dto[i].getKill() + "/" + dto[i].getDeath() + "/" + dto[i].getAssist());
			kda.setForeground(Color.white);
			kda.setBounds(0,276,129,20);
			kda.setHorizontalAlignment(JLabel.CENTER);
			kda.setFont(kdaFont);
			
			JLabel rate = new JLabel(dto[i].getRating());
			rate.setForeground(Color.white);
			rate.setHorizontalAlignment(JLabel.CENTER);
			rate.setBounds(0,255,129,16);
			rate.setFont(rateFont);
			
			JLabel date = new JLabel(dto[i].getDate());
			date.setForeground(Color.white);
			date.setBounds(0,5,129,16);
			date.setHorizontalAlignment(JLabel.CENTER);
			date.setFont(dateFont);
			
			ImageIcon isWin;
			if(dto[i].isWin()) {
				isWin = new ImageIcon(path + "/img/result/win.png");
			} else {
				isWin = new ImageIcon(path + "/img/result/lose.png");
			}
			
			JLabel winIcon = new JLabel(isWin);
			winIcon.setBounds(0,230,129,22);
			winIcon.setHorizontalAlignment(JLabel.CENTER);
			
			
			ImageIcon shadow_imgico = new ImageIcon(path + "/img/result/recent_shadow.png");
			JLabel shadow = new JLabel(shadow_imgico);
			shadow.setBounds(0,0,130,303);
			
			recordPanel.add(winIcon);
			recordPanel.add(date);
			recordPanel.add(rate);
			recordPanel.add(kda);
			
			recordPanel.add(shadow);
			recordPanel.add(champ_label);
			
			rrList[i] = recordPanel;
		}
		
		return rrList;
	}
}
