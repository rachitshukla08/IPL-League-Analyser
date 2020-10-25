package com.capgemini.iplleagueanalyser;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.capgemini.iplleagueanalyser.model.Batting;
import com.google.gson.Gson;

/**
 * Unit test for simple App.
 */
public class IPLAnalyserTest 
{
	private static final String BATTING_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\iplleagueanalyser\\resources\\IPL2019FactsheetMostRuns.csv";
	private static final String BOWLING_DATA_PATH = ".\\src\\main\\java\\com\\capgemini\\iplleagueanalyser\\resources\\IPL2019FactsheetMostWkts.csv"; 
	private IPLAnalyser iplAnalyser;
	
	@Before
	public void init() {
		iplAnalyser = new IPLAnalyser();
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
		String sortedBattingData = "";
		iplAnalyser.loadBattingData(BATTING_DATA_PATH);
		sortedBattingData = iplAnalyser.battingAvgWiseSortedData();
		Double[] battingData = new Gson().fromJson(sortedBattingData, Double[].class);
		assertEquals(83.2, battingData[0].doubleValue(),0.0);
	}
}
