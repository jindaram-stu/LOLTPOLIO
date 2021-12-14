package com.loltpolio.riot.dto;

public class SummonerTrendDTO {
	private int win = 0;
	private int loss = 0;
	private int top = 0;
	private int jug = 0;
	private int mid = 0;
	private int bot= 0;
	private int sup= 0;
	
	public int getWin() {
		return win;
	}
	public void setWin(int win) {
		this.win = win;
	}
	public int getLoss() {
		return loss;
	}
	public void setLoss(int loss) {
		this.loss = loss;
	}
	public int getTop() {
		return top;
	}
	public void setTop(int top) {
		this.top = top;
	}
	public int getJug() {
		return jug;
	}
	public void setJug(int jug) {
		this.jug = jug;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public int getBot() {
		return bot;
	}
	public void setBot(int bot) {
		this.bot = bot;
	}
	public int getSup() {
		return sup;
	}
	public void setSup(int sup) {
		this.sup = sup;
	}
	
	public void positionCase(String position) {
		switch(position) {
		case "TOP" :
			this.top += 1;
			break;
		case "JUNGLE" :
			this.jug += 1;
			break;
		case "MIDDLE" :
			this.mid += 1;
			break;
		case "BOTTOM" :
			this.bot += 1;
			break;
		case "UTILITY" :
			this.sup += 1;
			break;
		}
	}
	
	public String toString() {
		String result = "";
		result += win +"승 " + loss + "패" + "\n";
		result += top + " " + jug + " " + mid + " " + bot + " " + sup;
		return result;
	}
}
