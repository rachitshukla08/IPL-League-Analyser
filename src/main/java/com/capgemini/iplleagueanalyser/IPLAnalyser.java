package com.capgemini.iplleagueanalyser;

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
import com.opencsv.exceptions.CsvException;

public class IPLAnalyser {
	List<Batting> battingList;
	List<Bowling> bowlingList;

	public int loadBattingData(String battingDataPath) throws IPLAnaylserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(battingDataPath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			try {
				battingList = csvBuilder.getCSVFileList(reader, Batting.class);
			} catch (CsvException e) {
				throw new IPLAnaylserException("Invalid class", IPLAnaylserException.ExceptionType.INVALID_CLASS_TYPE);
			}
		} catch (IOException e) {
			throw new IPLAnaylserException("Invalid file location",
					IPLAnaylserException.ExceptionType.INVALID_FILE_PATH);
		}
		return battingList.size();
	}

	public int loadBowlingData(String bowlingDataPath) throws IPLAnaylserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(bowlingDataPath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			try {
				bowlingList = csvBuilder.getCSVFileList(reader, Bowling.class);
			} catch (CsvException e) {
				throw new IPLAnaylserException("Invalid class", IPLAnaylserException.ExceptionType.INVALID_CLASS_TYPE);
			}

		} catch (IOException e1) {
			throw new IPLAnaylserException("Invalid file location",
					IPLAnaylserException.ExceptionType.INVALID_FILE_PATH);
		}
		return bowlingList.size();
	}

	public <T> List<T> getSortedList(FlexibleSort.Order order, String playerType) throws IPLAnaylserException {
		FlexibleSort flexibleSort = new FlexibleSort(order);
		List<T> sortedList;
		if (playerType.equals("Batsman")) {
			if (battingList == null || battingList.size() == 0)
				throw new IPLAnaylserException("No batting list data", IPLAnaylserException.ExceptionType.NO_DATA);
			sortedList = (List<T>) battingList;
		}
		else if(playerType.equals("Bowler")) {
			if (bowlingList == null || bowlingList.size() == 0)
				throw new IPLAnaylserException("No bowling list data", IPLAnaylserException.ExceptionType.NO_DATA);
			sortedList = (List<T>) bowlingList;
		}
		else 
			throw new IPLAnaylserException("Wrong player type",IPLAnaylserException.ExceptionType.WRONG_PLAYER_TYPE);
		Collections.sort(sortedList, flexibleSort);
		sortedList = IPLAnalyser.filterList(sortedList,order);
		System.out.println(sortedList);
		return sortedList;
	}
	
	private static <T> List<T> filterList(List<T> list,FlexibleSort.Order order){
		if(list.get(0).getClass().equals(Batting.class)) {
			List<Batting> filteredList = (List<Batting>) list;
			if(order.equals(FlexibleSort.Order.ZERO_100AND50_BEST_AVG))
				return (List<T>) filteredList.stream().filter(batting->batting.getHundreds().equals("0")&&batting.getFifties().equals("0")).collect(Collectors.toList());
			else 
				return (List<T>) filteredList;
		}
		else {
			List<Bowling> filteredList = (List<Bowling>) list;
		}
		return list;
	}
}
