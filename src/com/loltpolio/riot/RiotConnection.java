package com.loltpolio.riot;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loltpolio.riot.dto.ChampionMasteryDTO;
import com.loltpolio.riot.dto.SummonerInfoDTO;
import com.loltpolio.riot.dto.SummonerRankInfoDTO;
import com.loltpolio.riot.dto.SummonerRecentRecordDTO;
import com.loltpolio.riot.dto.SummonerTrendDTO;



public class RiotConnection {
	private final String api_key = "RGAPI-5402e4c2-e9a1-4e6e-8845-dc19c7b22fd1";
	
	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	JSONParser parser = new JSONParser();
	
	// 파라미터로 받은 URL를 바탕으로 HttpConnection 객체 획득
	public HttpURLConnection getConnection(URL url) {
		try {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");
		
		conn.connect();
		return conn;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ChampionMasteryDTO[] getChampionMastery(String id) {
		try {
			String tempString = "";
			String jsonString = "";
			String championMasteryURL =  "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/";
			championMasteryURL += id + "?api_key=" + api_key;
		
			URL url = new URL(championMasteryURL);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(getConnection(url).getInputStream(), "UTF-8"));
			
			while ((tempString = br.readLine()) != null) {
				jsonString += tempString;
			}
			
			ChampionMasteryDTO[] dto = mapper.readValue(jsonString, ChampionMasteryDTO[].class);

			for(int i=0; i<5; i++) {
				dto[i].setChampionName(getChampionNameById(dto[i].getChampionId()));
			}
			
			return dto;
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// ChampionID 값으로 챔피언명을 받아옴 (67 -> "Vayne")
	public String getChampionNameById(int championId) {
		String championName = "Unknown";
		try {
		Reader reader = new FileReader(System.getProperty("user.dir")+"/src/com/loltpolio/json/champion_data.json");
		JSONObject result = (JSONObject) parser.parse(reader);
		JSONArray arr = (JSONArray) result.get("data");
		
		for(int i=0; i<157; i++) {
			JSONObject obj = (JSONObject) arr.get(i);
			if (obj.get("key").equals(""+championId)) {
				championName = (String)obj.get("id");
				break;
			}
		}
		System.out.println(championName);
		
		return championName;
		
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return championName;
	}
	
	
	public String[] getGameList(String puuid) {
		try {
			String tempString = "";
			String jsonString = "";
			String[] gameList = new String[10];
		
			URL url = new URL("https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?type=ranked&start=0&count=10&api_key="+api_key);
			BufferedReader br = new BufferedReader(new InputStreamReader(getConnection(url).getInputStream(), "UTF-8"));
			
			while ((tempString = br.readLine()) != null) {
				jsonString += tempString;
			}
			
			JSONArray objArr = (JSONArray) parser.parse(jsonString);
			for(int i=0; i<10; i++) {
				gameList[i] = (String) objArr.get(i);
			}
			return gameList;
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public HashMap<String,Object> getGameDetailInfo(String summonerName, String[] gameList) {
		HashMap<String,Object> returnList = new HashMap<String,Object>();
		SummonerRecentRecordDTO[] srrDTO = new SummonerRecentRecordDTO[5];
		SummonerTrendDTO stDTO = new SummonerTrendDTO(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		String tempString = "";
		String jsonString = "";
		try {
			
			for(int i=0; i<10; i++) {
				System.out.println("https://asia.api.riotgames.com/lol/match/v5/matches/" + gameList[i] + "?api_key=" + api_key);
				URL url = new URL("https://asia.api.riotgames.com/lol/match/v5/matches/" + gameList[i] + "?api_key=" + api_key);
				BufferedReader br = new BufferedReader(new InputStreamReader(getConnection(url).getInputStream(), "UTF-8"));
				
				while ((tempString = br.readLine()) != null) {
					jsonString += tempString;
				}
				JSONObject obj = (JSONObject) parser.parse(jsonString);
				JSONObject info = (JSONObject) obj.get("info");
				JSONArray parti = (JSONArray) info.get("participants");
				
				Long gameCreation = Long.parseLong(info.get("gameCreation").toString());
				Date date = new Date(gameCreation);
				String formatDate = sdf.format(date);
				
				
				for(int k=0; k<10; k++) {
					JSONObject obj2 = (JSONObject) parti.get(k);
					if (obj2.get("summonerName").equals(summonerName)) {
						if (i<5) { // 게임 내 챔피언 킬뎃 등 최근 전적에 사용될 정보를 가져온다.
							srrDTO[i] = new SummonerRecentRecordDTO();
							srrDTO[i].setChampion(obj2.get("championName").toString());
							srrDTO[i].setDate(formatDate);
							srrDTO[i].setKill(Integer.parseInt(obj2.get("kills").toString()));
							srrDTO[i].setDeath(Integer.parseInt(obj2.get("deaths").toString()));
							srrDTO[i].setAssist(Integer.parseInt(obj2.get("assists").toString()));
							srrDTO[i].setWin((boolean)obj2.get("win"));
							srrDTO[i].setRating(String.format("%.2f", (double)(srrDTO[i].getKill() + srrDTO[i].getAssist())/srrDTO[i].getDeath()));
						}
						if ((boolean)obj2.get("win")) {
							stDTO.setWin(stDTO.getWin() + 1);
						} else {
							stDTO.setLoss(stDTO.getLoss() + 1);
						}
						System.out.println(obj2.get("teamPosition").toString());
						stDTO.positionCase(obj2.get("teamPosition").toString());
						
						break;
					}
					continue;
				}
				tempString = "";
				jsonString = "";
			}
			for (SummonerRecentRecordDTO a : srrDTO) {
				System.out.println(a.toString());
			}
			System.out.println(stDTO.toString());
			returnList.put("SummonerRecentRecord", srrDTO);
			returnList.put("SummonerTrend", stDTO);
			return returnList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public SummonerInfoDTO getSummonerInfo(String summonerName) {
		try {
			String tempString = "";
			String jsonString = "";
			String EncodingSummonerName;
		
			EncodingSummonerName = URLEncoder.encode(summonerName,"UTF-8");
			URL url = new URL("https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/" + EncodingSummonerName + "?api_key=" + api_key);
			BufferedReader br = new BufferedReader(new InputStreamReader(getConnection(url).getInputStream(),"UTF-8"));
			while ((tempString = br.readLine()) != null) {
				jsonString += tempString;
			}
			
			SummonerInfoDTO dto = mapper.readValue(jsonString, SummonerInfoDTO.class);
			
			return dto;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public SummonerRankInfoDTO getSummonerRankInfo(SummonerInfoDTO summonerInfoDTO) {
		try {
			String tempString = "";
			String jsonString = "";
			String id = summonerInfoDTO.getId();
			URL url = new URL("https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/" + id + "?api_key=" + api_key);
			
			BufferedReader br = new BufferedReader(new InputStreamReader(getConnection(url).getInputStream(),"UTF-8"));
			
			while ((tempString = br.readLine()) != null) {
				jsonString += tempString;
			}
			
			SummonerRankInfoDTO dto = new SummonerRankInfoDTO();
			
			JSONArray objArr = (JSONArray) parser.parse(jsonString);
			for (int i=0; i<objArr.size(); i++) {
				JSONObject obj = (JSONObject) objArr.get(i);
				if (obj.get("queueType").equals("RANKED_SOLO_5x5")) {
					dto.setSummonerName(obj.get("summonerName").toString());
					dto.setTier(obj.get("tier").toString());
					dto.setRank(obj.get("rank").toString());
					dto.setLeaguePoints(Integer.parseInt(obj.get("leaguePoints").toString()));
					dto.setWins(Integer.parseInt(obj.get("wins").toString()));
					dto.setLosses(Integer.parseInt(obj.get("losses").toString()));
					break;
				}
				continue;
			}
			return dto;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
}

