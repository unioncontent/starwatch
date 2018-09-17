package org.union.util;

import java.util.Comparator;

import org.union.domain.ExtractVO;

public class ExtractComparator implements Comparator<ExtractVO> {

	@Override
	public int compare(ExtractVO o1, ExtractVO o2) {

		return o2.getCreateDate().compareTo(o1.getCreateDate());
	}

}
