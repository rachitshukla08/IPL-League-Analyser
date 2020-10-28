package com.capgemini.iplleagueanalyser.adapter;

import com.capgemini.iplleagueanalyser.model.Batting;
import com.capgemini.iplleagueanalyser.model.Bowling;

public class IPLAnalyserAdapter implements IPLAnalyserAdapterInterface  {
	/**
	 *@return class for given fileType
	 */
	public <T> Class<T> getClass(String fileType) {
		if(fileType.equals("Batting"))
			return (Class<T>) Batting.class;
		else if(fileType.equals("Bowling"))
			return (Class<T>) Bowling.class;
		return null;
	}
}
