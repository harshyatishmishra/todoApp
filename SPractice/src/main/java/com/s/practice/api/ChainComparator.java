package com.s.practice.api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.s.practice.model.Details;

public class ChainComparator implements Comparator<Details> {

	List<Comparator<Details>> listOfComparator;
	
	
	public ChainComparator(Comparator<Details>... listOfComparator) {
		this.listOfComparator = Arrays.asList(listOfComparator);
	}


	@Override
	public int compare(Details o1, Details o2) {
		for(Comparator<Details> comparator : listOfComparator) {
			int result = comparator.compare(o1, o2);
			if(result !=0) {
				return result;
			}
		}
		return 0;
	}

}
