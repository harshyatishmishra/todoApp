package com.s.practice.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;
import com.s.practice.constants.PracticeConstants.Separators;
import com.s.practice.mapper.DetailsMapper;
import com.s.practice.model.Details;

public class PracticeUtils {

	public static List<Details> readFile(File file, Separators Separator) {
		try {
			
			CSVReader reader = new CSVReader(new FileReader(file), Separator.getSeparator());
			List<Details> listOfDetails = DetailsMapper.getDetail(reader);
			listOfDetails.forEach(System.out::println);
			return listOfDetails;
		} catch (IOException e) {
			System.err.println("Error Occured while reading the file "+ e.getLocalizedMessage());
			// Throw exception
		}
		return null;
	}
}
