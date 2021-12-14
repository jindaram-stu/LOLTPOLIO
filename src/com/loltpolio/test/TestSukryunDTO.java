package com.loltpolio.test;

public class TestSukryunDTO {
	int championId;
	int championLevel;
	int championPoints;
	
	public int getChampionId() {
		return championId;
	}
	public void setChampionId(int championId) {
		this.championId = championId;
	}
	public int getChampionLevel() {
		return championLevel;
	}
	public void setChampionLevel(int championLevel) {
		this.championLevel = championLevel;
	}
	public int getChampionPoints() {
		return championPoints;
	}
	public void setChampionPoints(int championPoints) {
		this.championPoints = championPoints;
	}
	
	@Override
	public String toString() {
		String result;
		result = "[챔피언:" + championId + ",레벨:" + championLevel + ",포인트:" + championPoints + "]";
		return result;
	}
}
