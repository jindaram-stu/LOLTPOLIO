package com.loltpolio.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class TestConnection {
	String api_key = "RGAPI-17e77547-a725-44dd-a85f-8849bf3964af";
	String test_id = "MVFoDxlAAJy101Qq4NdIxqNha7aIXwI5-BW3cHbDzt0W5g";
	String test_puuid = "Buy6e_3xNcKyi9LTnUVmcQhdDX2tEoKQ6zYZIeZMQFUY3Xogxk5rNdzk_s2jvnOsj86A6Al_oYouvg";
	
	ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	
	public static void main(String[] args) throws IOException, ParseException {
		TestConnection tc = new TestConnection();
		// tc.getSummonerInfo();
		tc.getSukryun();
		/*
		Long timeStamp = 1638722377624L;
		Date date = new Date(timeStamp);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT+9"));
		String formattedDate = sdf.format(date);
		
		System.out.println(formattedDate);
		*/
		
		
	}
	public void getSummonerInfo() throws UnsupportedEncodingException {
		try {
			TestSummonerDTO dto = new TestSummonerDTO();
			JSONParser parser = new JSONParser();
			JSONObject result = null;
			String url1 = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-name/";
			String url2 = "https://kr.api.riotgames.com/lol/league/v4/entries/by-summoner/";
			String summonerName = "진다람";
			String EncodingSN = URLEncoder.encode(summonerName,"UTF-8");
			String responseData1 = "";
			String responseData2 = "";
			String AllResponseData1 = "";
			String AllResponseData2 = "";
	
			URL requestUrl1 = new URL(url1+EncodingSN+"?api_key="+api_key);
			HttpURLConnection conn = (HttpURLConnection) requestUrl1.openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("GET");
			
			conn.connect();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			while ((responseData1 = br.readLine()) != null) {
				AllResponseData1 += responseData1;
			}
			
			result = (JSONObject) parser.parse(AllResponseData1);
			dto.setSummonerName(result.get("name").toString());
			dto.setSummonerLevel(result.get("summonerLevel").toString());
			
			String summonerId = (String) result.get("id");
			
			URL requestUrl2 = new URL(url2 + summonerId + "?api_key=" + api_key);
			conn = (HttpURLConnection) requestUrl2.openConnection();
			
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("GET");
			
			conn.connect();
			
			br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			while ((responseData2 = br.readLine()) != null) {
				AllResponseData2 += responseData2;
			}
			
			JSONArray result2 = (JSONArray) parser.parse(AllResponseData2);
			JSONObject obj = null;
			for (int i=0; i<result2.size(); i++) {
				obj = (JSONObject) result2.get(i);
				if (obj.get("queueType").equals("RANKED_SOLO_5x5")) {
					break;
				}
			}
			dto.setQueueType(obj.get("queueType").toString());
			dto.setSummonerTier((obj.get("tier") + " " + obj.get("rank") + " " + obj.get("leaguePoints") + "LP").toString());
			dto.setWins(obj.get("wins").toString());
			dto.setLosses(obj.get("losses").toString());
			System.out.println("--------------------");
			System.out.println("닉네임 : " + dto.getSummonerName());
			System.out.println("레벨 : "  + dto.getSummonerLevel());
			System.out.println("큐 타입 : [" + dto.getQueueType() + "]");
			System.out.println("티어 : " + dto.getSummonerTier());
			System.out.println(dto.getWins() + "승 " + dto.getLosses() + "패" );
			System.out.println("--------------------");
			
		} catch (MalformedURLException e) {
			System.out.println("");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
				
				
	}
	
	
	
	public void getSukryun() throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		
		String responseData = "";
		String jsonString = "";
		String sukryun =  "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-summoner/";
		sukryun += test_id + "?api_key=" + api_key;
		
		URL url = new URL(sukryun);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestMethod("GET");
		
		conn.connect();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
		
		while ((responseData = br.readLine()) != null) {
			jsonString += responseData;
		}
		
		TestSukryunDTO[] dto = mapper.readValue(jsonString, TestSukryunDTO[].class);
		System.out.println(dto[0].toString());
		System.out.println(dto[1].toString());
		System.out.println(dto[2].toString());
		System.out.println(dto[3].toString());
		System.out.println(dto[4].toString());
		
		
	}
	
	public void fg() {
		String jsonString = "{\"id\":\"JSON\",\"pwd\":\"abc\"}";
		
		try {
			User user1 = mapper.readValue(jsonString, User.class);
			System.out.println(user1);
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void getRecentTrend() {
		
		try {
			String matchId = "KR_5608833741";
			String requestUrl = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + api_key;
			String tempResponse = "";
			String jsonString = "";
			URL url = new URL(requestUrl);
			
			int wins;
			int losses;
			int winRate;

			// 포지션
			int top;
			int jungle;
			int middle;
			int bottom;
			int support;
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestMethod("GET");
			
			conn.connect();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			
			while ((tempResponse = br.readLine()) != null) {
				jsonString += tempResponse;
			}
			
			JSONParser parser = new JSONParser();
			JSONObject obj = (JSONObject) parser.parse(jsonString);
			JSONObject info = (JSONObject) obj.get("info");
			JSONArray parti = (JSONArray) info.get("participants");
			for (int i=0; i<10; i++) {
				JSONObject obj2 = (JSONObject) parti.get(i);
				if (obj2.get("summonerName").equals("진 다람")) {
					System.out.println("얏호 내 아이디를 찾았당!");
					System.out.println("소환사 이름 : " + obj2.get("summonerName"));
					System.out.println("챔피언 : " + obj2.get("championName"));
					System.out.println("포지션 : " + obj2.get("teamPosition"));
					System.out.println("KDA : " + obj2.get("kills") + "/" + obj2.get("deaths") + "/" + obj2.get("assists"));
					if ((boolean) obj2.get("win")) {
						System.out.println("승리");
					} else {
						System.out.println("패배");
					}
					break;
				} else {
					continue;
				}
			}		
		} catch (MalformedURLException e) {
			e.printStackTrace();
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
		
		
		
	}

	
}
