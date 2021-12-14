package com.loltpolio.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.loltpolio.builder.ChampionMasteryBuilder;
import com.loltpolio.builder.SummonerRecentRecordBuilder;
import com.loltpolio.dao.MemberDAO;
import com.loltpolio.dto.SettingDTO;
import com.loltpolio.riot.RiotConnection;
import com.loltpolio.riot.dto.SummonerInfoDTO;
import com.loltpolio.riot.dto.SummonerRankInfoDTO;
import com.loltpolio.riot.dto.SummonerRecentRecordDTO;
import com.loltpolio.riot.dto.SummonerTrendDTO;

public class SearchResultFrame {
	JFrame frame;
	RiotConnection riotConn = new RiotConnection();
	ChampionMasteryBuilder cmBuilder = new ChampionMasteryBuilder();
	SummonerRecentRecordBuilder ssrBuilder = new SummonerRecentRecordBuilder();
	MemberDAO dao;
	int accessPath;
	int isUser;

	
	String path = System.getProperty("user.dir");
	Font titleFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,43);
	Font queueFont = new Font("AppleSDGothicNeoEB00",Font.PLAIN,14);
	Font tierFont = new Font("AppleSDGothicNeoEB00",Font.PLAIN,16);
	Font otherInfoFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,14);
	Font bigPerFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,25);
	Font perFont = new Font("AppleSDGothicNeoM00",Font.PLAIN,13);
	Color transparent = new Color(255,0,0,0);
	
	public SearchResultFrame(String summonerName, int accessPath) throws Exception {				
		LoadingFrame loadingFrame = new LoadingFrame();
		loadingFrame.setLoadingText("소환사 티어 정보를 수집하고 있습니다.");
		dao = new MemberDAO();
		this.accessPath = accessPath;
		isUser = dao.summonerExist(summonerName);
		frame = new JFrame();
		JPanel background = new JPanel();
		background.setBackground(Color.white);
		background.setBounds(0,0,1200,800);
		
		init(summonerName, loadingFrame);
		
		// frame.setLocation(2100,200);
		
		frame.setLayout(null);
		frame.setResizable(false); // 창 크기 조절 잠금 (창 크기 고정) 
		frame.setUndecorated(true); // 윈도우 창 없애기
		frame.setSize(1200,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(background);
		loadingFrame.hideFrame();
		frame.setLocationRelativeTo(null);
		frame.setAlwaysOnTop(true);
		frame.setVisible(true);
		
	}
	
	public void init(String sName, LoadingFrame loadingFrame) throws Exception {
		String summonerName = sName;
		SummonerInfoDTO summonerInfoDTO = riotConn.getSummonerInfo(summonerName);
		SummonerRankInfoDTO summonerRankInfoDTO = riotConn.getSummonerRankInfo(summonerInfoDTO);
		HashMap<String,Object> map = riotConn.getGameDetailInfo(summonerName, riotConn.getGameList(summonerInfoDTO.getPuuid()));
		SummonerRecentRecordDTO[] srr = (SummonerRecentRecordDTO[]) map.get("SummonerRecentRecord");
		SummonerTrendDTO st = (SummonerTrendDTO) map.get("SummonerTrend");
		String tier = summonerRankInfoDTO.getTier();
		loadingFrame.setLoadingText("필요한 이미지 불러오는 중");
		loadingFrame.revalidate();
		ImageIcon smallLogo_imgico = new ImageIcon(path+"/img/result/small_logo.png");
		ImageIcon close_btn_imgico = new ImageIcon(path+"/img/result/close_btn.png");
		ImageIcon ex_header_imgico = new ImageIcon(path+"/img/result/ex_header.png");
		ImageIcon top_bar_imgico = new ImageIcon(path+"/img/result/top_bar.png");
		ImageIcon tier_background_imgico = new ImageIcon(path+"/img/result/tier_emblem/tier_bg.png");
		ImageIcon tier_imgico = new ImageIcon(path+"/img/result/tier_emblem/" + tier + ".png");
		ImageIcon summonerInfo_imgico = new ImageIcon(path + "/img/result/summonerInfo.png");
		ImageIcon line_imgico = new ImageIcon(path + "/img/result/line.png");
		ImageIcon champion_exp_imgico = new ImageIcon(path + "/img/result/champion_exp.png");
		ImageIcon top_imgico = new ImageIcon(path + "/img/result/position/TOP.png");
		ImageIcon jug_imgico = new ImageIcon(path + "/img/result/position/JUNGLE.png");
		ImageIcon mid_imgico = new ImageIcon(path + "/img/result/position/MIDDLE.png");
		ImageIcon bot_imgico = new ImageIcon(path + "/img/result/position/BOTTOM.png");
		ImageIcon sup_imgico = new ImageIcon(path + "/img/result/position/UTILITY.png");
		
		ImageIcon next_ico = new ImageIcon(path + "/img/next_btn.png");
		ImageIcon prev_ico = new ImageIcon(path + "/img/prev_btn.png");
		
		
		JLabel next = new JLabel(next_ico);
		next.setBounds(1147,500,29,29);
		next.setVisible(isUser == 1);
		
		
		
		
		JLabel prev = new JLabel(prev_ico);
		prev.setBounds(27,500,29,29);
		prev.setVisible(false);
		
		
		
		
		JLabel smallLogo = new JLabel(smallLogo_imgico);
		smallLogo.setBounds(17,8,65,26);
		
		JLabel close_btn = new JLabel(close_btn_imgico);
		close_btn.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				frame.dispose();
				if(accessPath == 0) {
				MainFrame mainFrame = new MainFrame();
				mainFrame.showFrame();
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
		close_btn.setBounds(1178,15,12,12);
		loadingFrame.setLoadingText("소환사 정보 불러오는 중");
		loadingFrame.repaint();
		JLabel ex_header = new JLabel(ex_header_imgico);
		ex_header.setBounds(0,44,1200,184);
		
		JPanel abc = new JPanel();
		abc.revalidate();
		JLabel title = new JLabel(summonerName+" 님의 롤력서");
		title.setForeground(Color.white);
		title.setBounds(17,174,1183,43);
		title.setFont(titleFont);
		
		// ----------------------------
		// 소환서 정보 및 숙련도 패널
		JPanel top_bar_panel = new JPanel();
		top_bar_panel.setLayout(null);
		top_bar_panel.setBackground(transparent);
		top_bar_panel.setBounds(81,241,1038,202);
		
		JLabel top_bar = new JLabel(top_bar_imgico);
		top_bar.setBounds(81,241,1038,202);
		
		JLabel summonerInfo_text = new JLabel(summonerInfo_imgico);
		summonerInfo_text.setBounds(28,(top_bar_panel.getHeight() - summonerInfo_imgico.getIconHeight())/2, summonerInfo_imgico.getIconWidth(),summonerInfo_imgico.getIconHeight());
		
		// 티어 상하좌우 정렬을 위한 패널
		JPanel tier_bg_panel = new JPanel();
		tier_bg_panel.setLayout(null);
		tier_bg_panel.setBackground(transparent);
		tier_bg_panel.setBounds(100,0,151,202);
		
		JLabel tier_ico = new JLabel(tier_imgico);
		// 패널과 사이즈와 티어 아이콘의 사이즈를 계산해서 중간값으로 설정
		tier_ico.setBounds((tier_bg_panel.getWidth()-tier_imgico.getIconWidth())/2
							,(tier_bg_panel.getHeight()-tier_imgico.getIconHeight())/2
							,tier_imgico.getIconWidth(),tier_imgico.getIconHeight());
		tier_bg_panel.add(tier_ico);
		
		
		// 티어 아이콘 그림자 배경
		JLabel tier_background = new JLabel(tier_background_imgico);
		tier_background.setBounds(100,(top_bar_panel.getHeight()-tier_background_imgico.getIconHeight())/2,tier_background_imgico.getIconWidth(),tier_background_imgico.getIconHeight());
		
		// 구분선
		JLabel line = new JLabel(line_imgico);
		line.setBounds(270,
				(top_bar_panel.getHeight() - line_imgico.getIconHeight())/2,
				line_imgico.getIconWidth(),line_imgico.getIconHeight());
		
		// 소환사 정보
		
		
		JLabel queueText = new JLabel("솔로랭크");
		queueText.setFont(queueFont);
		queueText.setForeground(new Color(137,137,137,255));
		queueText.setBounds(298,50,100,14);
		
		JLabel tierText = new JLabel(summonerRankInfoDTO.getTier() + " " + summonerRankInfoDTO.getRank());
		tierText.setFont(tierFont);
		tierText.setForeground(new Color(102,142,253,255));
		tierText.setBounds(298,73,200,16);
		
		JLabel pointText = new JLabel(summonerRankInfoDTO.getLeaguePoints() + "LP");
		pointText.setFont(otherInfoFont);
		pointText.setBounds(298,97,100,14);
		
		JLabel winLoseText = new JLabel(summonerRankInfoDTO.getWins() + "승 " + summonerRankInfoDTO.getLosses() + "패");
		winLoseText.setForeground(new Color(137,137,137,255));
		winLoseText.setFont(otherInfoFont);
		winLoseText.setBounds(298,120,100,14);
		
		JLabel winPercentText = new JLabel("승률 " + summonerRankInfoDTO.getWins()*100/(summonerRankInfoDTO.getWins()+summonerRankInfoDTO.getLosses())+ "%");
		winPercentText.setForeground(new Color(137,137,137,255));
		winPercentText.setFont(otherInfoFont);
		winPercentText.setBounds(298,143,100,14);
		
		// 챔피언 숙련도
		loadingFrame.setLoadingText("소환사 숙련도 불러오는 중");
		
		JLabel champion_exp = new JLabel(champion_exp_imgico);
		champion_exp.setBounds(469,(top_bar_panel.getHeight() - champion_exp_imgico.getIconHeight())/2,68,14);
		
		JPanel champion_exp_list = new JPanel();
		champion_exp_list.setLayout(null);
		champion_exp_list.setBackground(transparent);
		champion_exp_list.setBounds(564,0,460,202);
		champion_exp_list.setLayout(null);
		
		JPanel[] cmList = cmBuilder.getChampionMasteryArray(riotConn.getChampionMastery(summonerInfoDTO.getId()));
		
		champion_exp_list.add(cmList[0]);
		champion_exp_list.add(cmList[1]);
		champion_exp_list.add(cmList[2]);
		champion_exp_list.add(cmList[3]);
		champion_exp_list.add(cmList[4]);
		
		top_bar_panel.add(champion_exp_list);
		top_bar_panel.add(champion_exp);
		top_bar_panel.add(queueText);
		top_bar_panel.add(tierText);
		top_bar_panel.add(pointText);
		top_bar_panel.add(winLoseText);
		top_bar_panel.add(winPercentText);
		top_bar_panel.add(line);
		top_bar_panel.add(summonerInfo_text);
		top_bar_panel.add(tier_bg_panel);
		top_bar_panel.add(tier_background);
		
		loadingFrame.setLoadingText("소환사 최신동향 불러오는 중");
		
		JPanel bot_bar_panel = new JPanel();
		bot_bar_panel.setLayout(null);
		bot_bar_panel.setBounds(82,468,1037,302);
		bot_bar_panel.setBackground(transparent);
		
		
		
		ImageIcon recent_trend_ico = new ImageIcon(path + "/img/result/recent_trend.png");
		ImageIcon recent_record_ico = new ImageIcon(path + "/img/result/recent_record.png");
		
		JLabel rt = new JLabel(recent_trend_ico);
		rt.setBounds(122,15,47,14);
		JLabel rr = new JLabel(recent_record_ico);
		rr.setBounds(321,146,46,14);
		
		
		
		String WL = st.getWin() + "승 " +st.getLoss() + "패";
		String WLPercent = (st.getWin() * 100) / (st.getWin()+st.getLoss()) + "%";
		
		JLabel WL_label = new JLabel(WL);
		WL_label.setBounds(0,44,128,25);
		WL_label.setFont(bigPerFont);
		WL_label.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel WLPercent_label = new JLabel(WLPercent);
		WLPercent_label.setBounds(0,71,128,20);
		WLPercent_label.setFont(perFont);
		WLPercent_label.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel graph_panel = new JPanel();
		graph_panel.setLayout(null);
		graph_panel.setBackground(transparent);
		graph_panel.setBounds(83,62,128,132);
		
		String graph_type = st.getWin() + "" + st.getLoss();
		ImageIcon graph_type_imgico = new ImageIcon(path + "/img/result/graph/" + graph_type + ".png");
		JLabel graph = new JLabel(graph_type_imgico);
		graph.setBounds(0,0,126,126);
		
		graph_panel.add(WL_label);
		graph_panel.add(WLPercent_label);
		graph_panel.add(graph);
		
		// y 223
		JPanel top_panel = new JPanel();
		top_panel.setLayout(null);
		top_panel.setBackground(transparent);
		top_panel.setBounds(13,223,42,67);
		JLabel top_img = new JLabel(top_imgico);
		top_img.setBounds(0,26,42,37);
		top_img.setHorizontalAlignment(JLabel.CENTER);
		JLabel top_per = new JLabel(st.getTop() * 10 + "%");
		top_per.setBounds(0,5,42,10);
		top_per.setFont(perFont);
		top_per.setHorizontalAlignment(JLabel.CENTER);
		top_panel.add(top_per);
		top_panel.add(top_img);
		
		JPanel jug_panel = new JPanel();
		jug_panel.setLayout(null);
		jug_panel.setBackground(transparent);
		jug_panel.setBounds(76,223,42,67);
		JLabel jug_img = new JLabel(jug_imgico);
		jug_img.setBounds(0,26,42,38);
		jug_img.setHorizontalAlignment(JLabel.CENTER);
		JLabel jug_per = new JLabel(st.getJug() * 10 + "%");
		jug_per.setBounds(0,5,42,10);
		jug_per.setFont(perFont);
		jug_per.setHorizontalAlignment(JLabel.CENTER);
		jug_panel.add(jug_per);
		jug_panel.add(jug_img);
		
		
		JPanel mid_panel = new JPanel();
		mid_panel.setBackground(transparent);
		mid_panel.setLayout(null);
		mid_panel.setBounds(139,223,42,67);
		JLabel mid_img = new JLabel(mid_imgico);
		mid_img.setBounds(0,26,42,37);
		mid_img.setHorizontalAlignment(JLabel.CENTER);
		JLabel mid_per = new JLabel(st.getMid() * 10 + "%");
		mid_per.setBounds(0,5,42,10);
		mid_per.setFont(perFont);
		mid_per.setHorizontalAlignment(JLabel.CENTER);
		mid_panel.add(mid_per);
		mid_panel.add(mid_img);
		
		JPanel bot_panel = new JPanel();
		bot_panel.setBackground(transparent);
		bot_panel.setLayout(null);
		bot_panel.setBounds(202,223,42,67);
		JLabel bot_img = new JLabel(bot_imgico);
		bot_img.setBounds(0,26,42,37);
		bot_img.setHorizontalAlignment(JLabel.CENTER);
		JLabel bot_per = new JLabel(st.getBot() * 10 + "%");
		bot_per.setBounds(0,5,42,10);
		bot_per.setFont(perFont);
		bot_per.setHorizontalAlignment(JLabel.CENTER);
		bot_panel.add(bot_per);
		bot_panel.add(bot_img);
		
		JPanel sup_panel = new JPanel();
		sup_panel.setBackground(transparent);
		sup_panel.setLayout(null);
		sup_panel.setBounds(267,223,42,67);
		JLabel sup_img = new JLabel(sup_imgico);
		sup_img.setBounds(0,26,42,33);
		sup_img.setHorizontalAlignment(JLabel.CENTER);
		JLabel sup_per = new JLabel(st.getSup() * 10 + "%");
		sup_per.setBounds(0,5,42,10);
		sup_per.setFont(perFont);
		sup_per.setHorizontalAlignment(JLabel.CENTER);
		sup_panel.add(sup_per);
		sup_panel.add(sup_img);
		
		loadingFrame.setLoadingText("소환사 최근전적 불러오는 중");
		
		ssrBuilder.getRecentRecordArray(srr);
		
		for(int q=0; q<5; q++) {
			JPanel[] rra = ssrBuilder.getRecentRecordArray(srr);
			bot_bar_panel.add(rra[q]);
		}
		
		bot_bar_panel.add(rt);
		bot_bar_panel.add(rr);
		bot_bar_panel.add(top_panel);
		bot_bar_panel.add(jug_panel);
		bot_bar_panel.add(mid_panel);
		bot_bar_panel.add(bot_panel);
		bot_bar_panel.add(sup_panel);
		bot_bar_panel.add(graph_panel);
		
		JPanel panel_two = new JPanel();
		panel_two.setBackground(transparent);
		panel_two.setLayout(null);
		panel_two.setBounds(79,241,1039,531);
		panel_two.setVisible(false);
		
		if (isUser == 1) { // 유저가 존재한다면
			SettingDTO dto = dao.getSetting(summonerName);
			
			
			ImageIcon info_bg_ico = new ImageIcon(path + "/img/panel_two/info_bg.png");
			JLabel info_bg = new JLabel(info_bg_ico);
			info_bg.setBounds(128,122,229,287);
			
			ImageIcon intro_bg_ico = new ImageIcon(path + "/img/panel_two/intro_bg.png");
			JLabel intro_bg = new JLabel(intro_bg_ico);
			intro_bg.setBounds(373,122,543,287);
			
			ImageIcon group_ico = new ImageIcon(path + "/img/panel_two/group.png");
			JLabel group = new JLabel(group_ico);
			group.setBounds(149,159,36,145);
			
			JLabel mail = new JLabel(dto.getMail());
			mail.setBounds(175,187,500,20);
			mail.setFont(otherInfoFont);
			
			JLabel discord = new JLabel(dto.getDiscord());
			discord.setBounds(175,211,500,20);
			discord.setFont(otherInfoFont);
			
			JLabel sns = new JLabel(dto.getSns());
			sns.setBounds(175,235,500,20);
			sns.setFont(otherInfoFont);
			
			JLabel mP = new JLabel("메인 포지션 : " + dto.getMainPos());
			mP.setBounds(148,320,200,20);
			mP.setFont(otherInfoFont);
			
			JLabel sP = new JLabel("서브 포지션 : " + dto.getSubPos());
			sP.setBounds(148,350,200,20);
			sP.setFont(otherInfoFont);
			
			JLabel introduce = new JLabel(dto.getIntro());
			introduce.setBounds(373,122,543,287);
			introduce.setFont(otherInfoFont);
			introduce.setHorizontalAlignment(JLabel.CENTER);
			introduce.setVerticalAlignment(JLabel.CENTER);
			
			panel_two.add(introduce);
			panel_two.add(mP);
			panel_two.add(sP);
			panel_two.add(mail);
			panel_two.add(discord);
			panel_two.add(sns);
			panel_two.add(group);
			panel_two.add(intro_bg);
			panel_two.add(info_bg);
		} else {
			ImageIcon null_data_ico = new ImageIcon(path + "/img/panel_two/null_data.png");
			JLabel null_data = new JLabel(null_data_ico);
			null_data.setBounds(406,261,234,20);
			
			panel_two.add(null_data);
		}
		
		prev.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				bot_bar_panel.setVisible(true);
				top_bar_panel.setVisible(true);
				top_bar.setVisible(true);
				next.setVisible(true);
				prev.setVisible(false);
				panel_two.setVisible(false);
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
		
		next.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				bot_bar_panel.setVisible(false);
				top_bar_panel.setVisible(false);
				top_bar.setVisible(false);
				next.setVisible(false);
				prev.setVisible(true);
				panel_two.setVisible(true);
				
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
		
		
		frame.add(next);
		frame.add(prev);
		frame.add(panel_two);
		frame.add(bot_bar_panel);
		frame.add(top_bar_panel);
		frame.add(top_bar);
		frame.add(title);
		frame.add(ex_header);
		frame.add(close_btn);
		frame.add(smallLogo);
	}

}

