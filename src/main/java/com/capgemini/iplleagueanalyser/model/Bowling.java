package com.capgemini.iplleagueanalyser.model;

import com.opencsv.bean.CsvBindByName;

public class Bowling {
	
	@CsvBindByName(column = "POS")
	private String pos;
	@CsvBindByName(column = "PLAYER")
	private String player;
	@CsvBindByName(column = "Mat")
	private String matches;
	@CsvBindByName(column = "Inns")
	private String innings;
	@CsvBindByName(column = "Ov")
	private String overs;
	@CsvBindByName(column = "Runs")
	private String runs;
	@CsvBindByName(column = "Wkts")
	private String wickets;
	@CsvBindByName(column = "BBI")
	private String bbi;
	@CsvBindByName(column = "Avg")
	private String avg;
	@CsvBindByName(column = "Econ")
	private String economy;
	@CsvBindByName(column = "SR")
	private String strikeRate;
	@CsvBindByName(column = "4w")
	private String fourWickets;
	@CsvBindByName(column = "5w")
	private String fiveWickets;

	@Override
	public String toString() {
		return "Bowling [pos=" + pos + ", player=" + player + ", matches=" + matches + ", innings=" + innings
				+ ", overs=" + overs + ", runs=" + runs + ", wickets=" + wickets + ", bbi=" + bbi + ", avg=" + avg
				+ ", economy=" + economy + ", strikeRate=" + strikeRate + ", fourWickets=" + fourWickets
				+ ", fiveWickets=" + fiveWickets + "]";
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	public String getMatches() {
		return matches;
	}

	public void setMatches(String matches) {
		this.matches = matches;
	}

	public String getInnings() {
		return innings;
	}

	public void setInnings(String innings) {
		this.innings = innings;
	}

	public String getOvers() {
		return overs;
	}

	public void setOvers(String overs) {
		this.overs = overs;
	}

	public String getRuns() {
		return runs;
	}

	public void setRuns(String runs) {
		this.runs = runs;
	}

	public String getWickets() {
		return wickets;
	}

	public void setWickets(String wickets) {
		this.wickets = wickets;
	}

	public String getBbi() {
		return bbi;
	}

	public void setBbi(String bbi) {
		this.bbi = bbi;
	}

	public String getAvg() {
		return avg;
	}

	public void setAvg(String avg) {
		this.avg = avg;
	}

	public String getEconomy() {
		return economy;
	}

	public void setEconomy(String economy) {
		this.economy = economy;
	}

	public String getStrikeRate() {
		return strikeRate;
	}

	public void setStrikeRate(String strikeRate) {
		this.strikeRate = strikeRate;
	}

	public String getFourWickets() {
		return fourWickets;
	}

	public void setFourWickets(String fourWickets) {
		this.fourWickets = fourWickets;
	}

	public String getFiveWickets() {
		return fiveWickets;
	}

	public void setFiveWickets(String fiveWickets) {
		this.fiveWickets = fiveWickets;
	}
}
