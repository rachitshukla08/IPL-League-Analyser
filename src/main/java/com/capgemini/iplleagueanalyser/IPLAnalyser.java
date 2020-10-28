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
import com.capgemini.iplleagueanalyser.adapter.IPLAnalyserAdapter;
import com.capgemini.iplleagueanalyser.model.Batting;
import com.capgemini.iplleagueanalyser.model.Bowling;
import com.capgemini.iplleagueanalyser.service.FlexibleSort;
import com.opencsv.exceptions.CsvException;

public class IPLAnalyser <T> {
	List<T> statsList;

	/**
	 * @param dataPath
	 * @param fileType
	 * @return number of entries in loaded data
	 * @throws IPLAnaylserException
	 */
	public int loadData(String dataPath, String fileType) throws IPLAnaylserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(dataPath));) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
			try {
				statsList = csvBuilder.getCSVFileList(reader,new IPLAnalyserAdapter().getClass(fileType));
				return statsList.size();
			} catch (CsvException e) {
				throw new IPLAnaylserException("Invalid class", IPLAnaylserException.ExceptionType.INVALID_CLASS_TYPE);
			}

		} catch (IOException e) {
			throw new IPLAnaylserException("Invalid file location",
					IPLAnaylserException.ExceptionType.INVALID_FILE_PATH);
		}
	}

	/**
	 * @param order
	 * @param playerType
	 * @return sorted list
	 * @throws IPLAnaylserException
	 */
	public List<T> getSortedList(FlexibleSort.Order order, String playerType) throws IPLAnaylserException {
		FlexibleSort flexibleSort = new FlexibleSort(order);
		List<T> sortedList = (List<T>) statsList;
		Collections.sort(sortedList, flexibleSort);
		sortedList = IPLAnalyser.filterList(sortedList, order);
		System.out.println(sortedList);
		return sortedList;
	}

	/**
	 * @param <T>
	 * @param list
	 * @param order
	 * @return filtered list
	 */
	private static <T> List<T> filterList(List<T> list, FlexibleSort.Order order) {
		if (list.get(0).getClass().equals(Batting.class)) {
			List<Batting> filteredList = (List<Batting>) list;
			if (order.equals(FlexibleSort.Order.ZERO_100AND50_BEST_AVG))
				return (List<T>) filteredList.stream()
						.filter(batting -> batting.getHundreds().equals("0") && batting.getFifties().equals("0"))
						.collect(Collectors.toList());
			else
				return (List<T>) filteredList;
		} else {
			List<Bowling> filteredList = (List<Bowling>) list;
		}
		return list;
	}
}
