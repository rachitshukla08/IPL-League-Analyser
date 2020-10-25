package com.capgemini.iplleagueanalyser;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.iplleagueanalyser.model.Batting;
import com.capgemini.iplleagueanalyser.model.Bowling;
import com.capgemini.iplleagueanalyser.service.FlexibleSort;

/**
 * Unit test for simple App.
 */
public class IPLAnalyserTest 
{
	private static final String BATTING_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\iplleagueanalyser\\resources\\IPL2019FactsheetMostRuns.csv";
	private static final String BOWLING_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\iplleagueanalyser\\resources\\IPL2019FactsheetMostWkts.csv"; 
	private IPLAnalyser iplAnalyser;
	List<Batting> sortedBattingList;
	List<Bowling> sortedBowlingList;
	
	@Before
	public void init() throws IPLAnaylserException {
		iplAnalyser = new IPLAnalyser();
		iplAnalyser.loadBattingData(BATTING_DATA_PATH);
		iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
	}
	
	@Test
    public void givenBattingDataCSVFile_ShouldLoadBattingData() throws IPLAnaylserException{
        int totalRecords = iplAnalyser.loadBattingData(BATTING_DATA_PATH);
        assertEquals(100, totalRecords);
    }
	
	@Test
	public void givenBowlingDataCSVFile_ShouldLoadBowlingData() throws IPLAnaylserException {
		int totalRecords = iplAnalyser.loadBowlingData(BOWLING_DATA_PATH);
		assertEquals(99, totalRecords);
	}
	
	@Test
	public void givenBattingData_WhenSortedByAvg_ShouldReturnHighestAvgFirst() throws IPLAnaylserException {
		sortedBattingList = iplAnalyser.getSortedList(FlexibleSort.Order.BAT_AVG,"Batsman");
		assertEquals("83.2", sortedBattingList.get(0).getAvg());
	}
	
	@Test
	public void givenBattingData_WhenSortedBySR_ShouldReturnHighestSRFirst() throws IPLAnaylserException {
		sortedBattingList = iplAnalyser.getSortedList(FlexibleSort.Order.BAT_SR,"Batsman");
		assertEquals("333.33", sortedBattingList.get(0).getStrikeRate());
	}
	
	@Test
	public void givenBattingData_WhenSortedByBoundaries_ShouldReturnHighestTotalBoundaries() throws IPLAnaylserException {
		sortedBattingList = iplAnalyser.getSortedList(FlexibleSort.Order.BOUNDARIES,"Batsman");
		int totalBoundaries = Integer.parseInt(sortedBattingList.get(0).getFours())+Integer.parseInt(sortedBattingList.get(0).getSixes());
		assertEquals(83, totalBoundaries);
	}
	
	@Test
	public void givenBattingData_WhenSortedByStrikeRateAndBoundaries_ShouldReturnSortedList() throws IPLAnaylserException {
		sortedBattingList = iplAnalyser.getSortedList(FlexibleSort.Order.SR_AND_BOUNDARIES,"Batsman");
		assertEquals("Ishant Sharma", sortedBattingList.get(0).getPlayer());
	}
	
	@Test
	public void givenBattingData_WhenSortedByAvgAndStrikeRate_ShouldReturnSortedList() throws IPLAnaylserException {
		sortedBattingList = iplAnalyser.getSortedList(FlexibleSort.Order.AVG_AND_SR,"Batsman");
		assertEquals("MS Dhoni", sortedBattingList.get(0).getPlayer());
	}
	
	@Test
	public void givenBattingData_WhenSortedByRunsAndAvg_ShouldReturnSortedList() throws IPLAnaylserException {
		sortedBattingList = iplAnalyser.getSortedList(FlexibleSort.Order.RUNS_AND_AVG,"Batsman");
		assertEquals("David Warner", sortedBattingList.get(0).getPlayer());
	}
	
	@Test
	public void givenBowlingData_WhenSortedByAvg_ShouldReturnBestAvgFirst() throws IPLAnaylserException {
		sortedBowlingList = iplAnalyser.getSortedList(FlexibleSort.Order.BOWL_AVG,"Bowler");
		assertEquals("11", sortedBowlingList.get(0).getAvg());
	}
}
