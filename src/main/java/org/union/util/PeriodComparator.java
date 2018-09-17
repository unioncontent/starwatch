package org.union.util;

import java.util.Comparator;

import org.union.domain.PeriodMediaVO;

public class PeriodComparator implements Comparator<PeriodMediaVO> {

	@Override
	public int compare(PeriodMediaVO first, PeriodMediaVO second) {

		int firstValue = first.getAllCount();
		int secondValue = second.getAllCount();

		if(firstValue > secondValue) {
			return -1;
			
		}else if (firstValue < secondValue) {
			return 1;
		
		}else {
			return 0;
		}
	}

}
