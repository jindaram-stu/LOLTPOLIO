package com.loltpolio.riot.dto;

public class SummonerRecentRecordDTO {
	private String champion;
	private int kill;
	private int death;
	private int assist;
	private String rating;
	private boolean isWin;
	private String date;
	public int getKill() {
		return kill;
	}
	public void setKill(int kill) {
		this.kill = kill;
	}
	public int getDeath() {
		return death;
	}
	public void setDeath(int death) {
		this.death = death;
	}
	public int getAssist() {
		return assist;
	}
	public void setAssist(int assist) {
		this.assist = assist;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public boolean isWin() {
		return isWin;
	}
	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		String result = "";
		result += kill + "/" + death + "/" + assist; 
		return result;
	}
	public String getChampion() {
		return champion;
	}
	public void setChampion(String champion) {
		this.champion = champion;
	}
}
