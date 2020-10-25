package com.capgemini.iplleagueanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.indianstatecensusanalyser.service.CSVBuilderFactory;
import com.capgemini.indianstatecensusanalyser.service.ICSVBuilder;
import com.capgemini.iplleagueanalyser.model.Batting;
import com.capgemini.iplleagueanalyser.model.Bowling;
import com.google.gson.Gson;
import com.opencsv.exceptions.CsvException;

public class IPLAnalyser {
	List<Batting> battingList;
	List<Bowling> bowlingList;
	
	public int loadBattingData(String battingDataPath) throws IPLAnaylserException {
		try(Reader reader = Files.newBufferedReader(Paths.get(battingDataPath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			try{
				battingList = csvBuilder.getCSVFileList(reader, Batting.class);
			}catch(CsvException e) {
				throw new IPLAnaylserException("Invalid class",IPLAnaylserException.ExceptionType.INVALID_CLASS_TYPE);
			}
			BufferedReader br = new BufferedReader(new FileReader(battingDataPath));
			String line = "";
			int ctr = 0;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e) {
			throw new IPLAnaylserException("Invalid file location",IPLAnaylserException.ExceptionType.INVALID_FILE_PATH);
		} 
		return battingList.size();
	}
	
	public int loadBowlingData(String bowlingDataPath) throws IPLAnaylserException {
		try(Reader reader = Files.newBufferedReader(Paths.get(bowlingDataPath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			try{
				bowlingList = csvBuilder.getCSVFileList(reader, Bowling.class);
			}catch(CsvException e) {
				throw new IPLAnaylserException("Invalid class",IPLAnaylserException.ExceptionType.INVALID_CLASS_TYPE);
			}
			BufferedReader br = new BufferedReader(new FileReader(bowlingDataPath));
			String line = "";
			int ctr = 0;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return bowlingList.size();
	}
	
	public String battingAvgWiseSortedData() throws IPLAnaylserException {
		if(battingList==null||battingList.size()==0) {
			throw new IPLAnaylserException("No batting list data",IPLAnaylserException.ExceptionType.NO_DATA);
		}
		List<Double> sortedBattingList = battingList.stream()
													 .filter(obj->!obj.getAvg().contains("-"))
													 .map(obj->obj.getAvg())
													 .map(avg->Double.parseDouble(avg))
													 .sorted(Collections.reverseOrder())
													 .collect(Collectors.toList());
		String sortedBattingDataJson = new Gson().toJson(sortedBattingList);
		System.out.println(sortedBattingDataJson);
		return sortedBattingDataJson;
	}
}		
	