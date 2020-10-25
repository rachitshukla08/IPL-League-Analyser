package com.capgemini.iplleagueanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.capgemini.indianstatecensusanalyser.service.CSVBuilderFactory;
import com.capgemini.indianstatecensusanalyser.service.ICSVBuilder;
import com.capgemini.iplleagueanalyser.model.Batting;
import com.capgemini.iplleagueanalyser.model.Bowling;
import com.capgemini.iplleagueanalyser.service.FlexibleSort;
import com.capgemini.iplleagueanalyser.service.FlexibleSort.Order;
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
			
		} catch (IOException e1) {
			throw new IPLAnaylserException("Invalid file location",IPLAnaylserException.ExceptionType.INVALID_FILE_PATH);
		}
		return bowlingList.size();
	}
	
	public List<Batting> getSortedList(FlexibleSort.Order order) throws IPLAnaylserException {
		if(battingList==null||battingList.size()==0) {
			throw new IPLAnaylserException("No batting list data",IPLAnaylserException.ExceptionType.NO_DATA);
		}
		FlexibleSort flexibleSort = new FlexibleSort(order);
		List<Batting> sortedBattingList = battingList;
		Collections.sort(sortedBattingList, flexibleSort);
		System.out.println(sortedBattingList);
		return sortedBattingList;
	}
}		
	