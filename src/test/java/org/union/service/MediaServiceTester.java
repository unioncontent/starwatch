package org.union.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.union.domain.PeriodMediaVO;
import org.union.domain.ReporterVO;
import org.union.domain.SearchCriteria;
import org.union.persistence.MediaDAO;
import org.union.persistence.ReporterDAO;
import org.union.util.PeriodComparator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/spring/root-context.xml" })
public class MediaServiceTester {

	
	@Autowired
	private MediaDAO mediaDAO;
	
	@Autowired
	private ReporterDAO reporterDAO;
	
	@Autowired
	private MediaService mediaService;
	
	

	@Test
	public void test() throws SQLException {
		List<PeriodMediaVO> periodList = new ArrayList<PeriodMediaVO>();

		List<ReporterVO> reporterList = reporterDAO.listAll();

		for (int i = 0; i < reporterList.size(); i++) {
			PeriodMediaVO vo = new PeriodMediaVO();

			SearchCriteria criteria = new SearchCriteria();
			
			criteria.setKeyword(reporterList.get(i).getReporter_media_name());
			vo.setAllCount(mediaDAO.mediaGetTotalCount(criteria));
			vo.setMedia(reporterList.get(i).getReporter_media_name());
			vo.setReporter(reporterList.get(i).getReporter_name());
			
			periodList.add(vo);
		}
		
		for(int i = 0; i < periodList.size(); i++) {
			for(int j = 0; j < periodList.size(); j++) {
				if(i == j) {
				}else if(periodList.get(i).getMedia().equals(periodList.get(j).getMedia())) {
					int calcCount = periodList.get(i).getAllCount() + periodList.get(j).getAllCount();
					periodList.get(i).setAllCount(calcCount);
					periodList.remove(j);
				}
			}
		}
		
		PeriodComparator com = new PeriodComparator();
		
		Collections.sort(periodList, com);
		
		System.out.println(periodList);
			
			
	}
	
	
	
}
