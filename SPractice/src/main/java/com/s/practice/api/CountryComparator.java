package com.s.practice.api;

import java.util.Comparator;

import com.s.practice.model.Details;

public class CountryComparator implements Comparator<Details> {

	@Override
	public int compare(Details detail_1, Details detail_2) {
		return detail_1.getCountry().compareTo(detail_2.getCountry());
	}

}
