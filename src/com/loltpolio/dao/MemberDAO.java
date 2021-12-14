package com.loltpolio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.loltpolio.dto.SettingDTO;



public class MemberDAO {
	private static final String driver ="com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/world?useSSL=false&useUnicode=true&characterEncoding=utf8";
	private static final String user = "root";
	private static final String pwd = "1234";
	
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	
	private void connDB() {
		try {
			Class.forName(driver); 
			System.out.println("드라이버 로딩 성공");
			conn = DriverManager.getConnection(url, user, pwd);
			System.out.println("Connection 생성 성공");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		MemberDAO dao = new MemberDAO();
		dao.connDB();
		dao.login("jindaram","1234");
	}
	
	// 로그인
	
	public int login (String name, String pwd) {
		
		try {
			connDB();
			String sql = "SELECT count(*) FROM lol_member WHERE id=? and pwd=?";
			pstm = conn.prepareStatement(sql);
			
			pstm.setString(1,name);
			pstm.setString(2, pwd);
			
			rs = pstm.executeQuery();
			if (rs.next()) {
				int isExist = rs.getInt("count(*)");
				if (isExist == 1) {
					// 로그인 성공
					System.out.println("로그인 성공");
					return isExist;
				} else {
					// 로그인 실패
					System.out.println("로그인 실패");
					return isExist;
				}
				
			}
			rs.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 0;
	}

	public String getSummonerName(String id) {
		String sn = "";
		try {
			connDB();
			String sql = "SELECT summonerName FROM lol_member WHERE id = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			if (rs.next()) {
				sn = rs.getString("summonerName"); 
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return sn;
	}
	// 회원가입
	
	public void join (String id, String pwd, String summonerName) {
		try {
			connDB();
			String sql = "INSERT INTO lol_member(id,pwd,summonerName) VALUES (?,?,?)";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pwd);
			pstm.setString(3, summonerName);
			
			pstm.executeUpdate();
			
			String sql2 = "INSERT INTO lol_polio(summonerName,email,discord,sns,main_pos,sub_pos,introduce) VALUES (?,'','','','탑','탑','')";
			pstm = conn.prepareStatement(sql2);
			pstm.setString(1, summonerName);
			pstm.executeUpdate();
			
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int idExist(String id) {
		try {
			connDB();
			String sql = "SELECT count(*) FROM lol_member WHERE id =?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, id);
			rs = pstm.executeQuery();
			
			if (rs.next()) {
				int isExist = rs.getInt("count(*)");
				return isExist;
			}
			rs.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int summonerNameExist(String summonerName) {
		try {
			connDB();
			String sql = "SELECT count(*) FROM lol_member WHERE summonerName = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, summonerName);
			rs = pstm.executeQuery();
			
			if (rs.next()) {
				int isExist = rs.getInt("count(*)");
				return isExist;
			}
			rs.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// 이력서 설정
	
	public void save(String summonerName,String email,String discord,String sns, String main_pos, String sub_pos, String introduce) {
		try {
		connDB();
		String sql = "REPLACE INTO lol_polio(summonerName,email,discord,sns,main_pos,sub_pos,introduce) values (?,?,?,?,?,?,?)";
		pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, summonerName);
		pstm.setString(2, email);
		pstm.setString(3, discord);
		pstm.setString(4, sns);
		pstm.setString(5, main_pos);
		pstm.setString(6, sub_pos);
		pstm.setString(7, introduce);
		
		pstm.executeUpdate();
		
		pstm.close();
		conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public SettingDTO getSetting(String summonerName) {
		try {
			SettingDTO dto = new SettingDTO();
			connDB();
			String sql = "SELECT * FROM lol_polio WHERE summonerName = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, summonerName);
			rs = pstm.executeQuery();
			if (rs.next()) {
				dto.setSummonerName(rs.getString("summonerName"));
				dto.setMail(rs.getString("email"));
				dto.setDiscord(rs.getString("discord"));
				dto.setSns(rs.getString("sns"));
				dto.setMainPos(rs.getString("main_pos"));
				dto.setSubPos(rs.getString("sub_pos"));
				dto.setIntro(rs.getString("introduce"));
			}
			
			return dto;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int summonerExist(String summonerName) {
		try {
			connDB();
			String sql = "SELECT count(*) FROM lol_polio WHERE summonerName = ?";
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, summonerName);
			rs = pstm.executeQuery();
			
			if (rs.next()) {
				int isExist = rs.getInt("count(*)");
				return isExist;
			}
			rs.close();
			pstm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
