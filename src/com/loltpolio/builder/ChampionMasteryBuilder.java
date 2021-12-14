package com.loltpolio.builder;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.loltpolio.riot.dto.ChampionMasteryDTO;

public class ChampionMasteryBuilder {
	Font pointFont = new Font("AppleSDGothicNeoB00",Font.PLAIN,14);
	Font levelFont = new Font("AppleSDGothicNeoEB00",Font.PLAIN,14);
	DecimalFormat format = new DecimalFormat("###,###");
	String path = System.getProperty("user.dir");
	
	public JPanel[] getChampionMasteryArray(ChampionMasteryDTO[] dto) throws IOException {
		JPanel[] cmList = new JPanel[5];
		
		
		for(int i=0; i<5; i++) {
			JPanel champion_exp = new JPanel();
			champion_exp.setLayout(null);
			champion_exp.setBounds(i*92,0,92,202);
			champion_exp.setBackground(new Color(255,0,0,0));
			// 초상화
			URL url2 = new URL("https://ddragon.leagueoflegends.com/cdn/11.24.1/img/champion/"+ dto[i].getChampionName() +".png");
			Image img= ImageIO.read(url2);
			Image img_re = img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			JLabel champion = new JLabel(new ImageIcon(img_re));
			champion.setBounds((champion_exp.getWidth()-64)/2,20,64,64);
			
			// 숙련도 휘장
			ImageIcon mastery_emblem = new ImageIcon(path + "/img/result/mastery/"+dto[i].getChampionLevel()+".png");
			JLabel mastery_emblem_label = new JLabel(mastery_emblem);
			mastery_emblem_label.setBounds(0,68,92,100);
			mastery_emblem_label.setHorizontalAlignment(JLabel.CENTER);
			
			// 숙련도 레벨
			JLabel mastery_level = new JLabel(dto[i].getChampionLevel()+"레벨");
			mastery_level.setBounds(0,125,92,50);
			mastery_level.setFont(levelFont);
			mastery_level.setHorizontalAlignment(JLabel.CENTER);
			
			// 포인트
			String point_str = format.format(dto[i].getChampionPoints());
			JLabel point = new JLabel(point_str + "점");
			point.setBounds(0,150,92,50);
			point.setFont(pointFont);
			point.setHorizontalAlignment(JLabel.CENTER);
			
			champion_exp.add(mastery_level);
			champion_exp.add(mastery_emblem_label);
			champion_exp.add(point);	
			champion_exp.add(champion);	
			
			cmList[i] = champion_exp;
		}
		
		return cmList;
	}
	
	
}
