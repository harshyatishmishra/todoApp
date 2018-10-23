package com.s.practice.main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.s.practice.api.ChainComparator;
import com.s.practice.api.CountryComparator;
import com.s.practice.api.GenderComparator;
import com.s.practice.constants.PracticeConstants;
import com.s.practice.constants.PracticeConstants.Separators;
import com.s.practice.model.Details;
import com.s.practice.utils.PracticeUtils;

public class FindTheAverage {

	public static void main(String[] args) throws IOException {

		String fileName = "files/inputs.csv";
		Separators separator = PracticeConstants.Separators.COMMA_SEPARATOR;
		ClassLoader classloader = new FindTheAverage().getClass().getClassLoader();
		File file = new File(classloader.getResource(fileName).getFile());
		List<Details> listOfDetails = PracticeUtils.readFile(file, separator);

		Collections.sort(listOfDetails, new ChainComparator(new CountryComparator(), new GenderComparator()));

		listOfDetails.forEach(System.out::println);

		Map<String, List<Details>> countryWiseMap = new ConcurrentHashMap<>();
		System.out.println();

		for (Details detail : listOfDetails) {
			if (countryWiseMap.containsKey(detail.getCountry())) {
				List<Details> temp = new ArrayList<>();
				temp = countryWiseMap.get(detail.getCountry());
				temp.add(detail);
				countryWiseMap.put(detail.getCountry(), temp);
			} else {
				List<Details> temp = new ArrayList<>();
				temp.add(detail);
				countryWiseMap.put(detail.getCountry(), temp);
			}
		}
		countryWiseMap.forEach((key, value) -> {
			System.out.println("Key : " + key + " Value : " + value);
		});

		for (String country : countryWiseMap.keySet()) {
			List<Details> list = countryWiseMap.get(country);
			float average = get(list);
		}
	}

	private static float get(List<Details> detail) {
		Float maleAverage = 0.0f, femaleAverage = 0.0f;Details male, female; int maleCount=0,femaleCount=0;
		for (Details det : detail) {
			if (det.getGender().equals("M")) {
				male = det;maleCount++;
				maleAverage += Float.parseFloat(det.getIncome());
			} else {
				female = det;femaleCount;
				femaleAverage += Float.parseFloat(det.getIncome());
			}
		}
		male.setIncome(maleAverage.toString());
		return maleAverage / detail.size();
	}

}
