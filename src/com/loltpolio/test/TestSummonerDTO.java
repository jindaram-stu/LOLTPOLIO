package com.loltpolio.test;

public class TestSummonerDTO {
	private String summonerName;
	private String summonerLevel;
	private String summonerTier;
	private String wins;
	private String losses;
	private String queueType;
	
	public String getSummonerName() {
		return summonerName;
	}
	public void setSummonerName(String summonerName) {
		this.summonerName = summonerName;
	}
	public String getSummonerLevel() {
		return summonerLevel;
	}
	public void setSummonerLevel(String summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	public String getSummonerTier() {
		return summonerTier;
	}
	public void setSummonerTier(String summonerTier) {
		this.summonerTier = summonerTier;
	}
	public String getWins() {
		return wins;
	}
	public void setWins(String wins) {
		this.wins = wins;
	}
	public String getLosses() {
		return losses;
	}
	public void setLosses(String losses) {
		this.losses = losses;
	}
	public String getQueueType() {
		return queueType;
	}
	public void setQueueType(String queueType) {
		this.queueType = queueType;
	}
	
	public TestSummonerDTO (String summonerName,String summonerLevel,String summonerTier,
							String wins,String losses,String queueType) {
		this.summonerName = summonerName;
		this.summonerLevel = summonerLevel;
		this.summonerTier = summonerTier;
		this.wins = wins;
		this.losses = losses;
		this.queueType = queueType;
	}
	
	public TestSummonerDTO() {}
	
	
	
}
