package org.union.domain;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMakerFv {

		private int totalCount;
		private int startPage;
		private int endPage;
		private boolean prev;
		private boolean next;
		
		private int displayPageNum = 10;
		
		private Criteria cri;

		
		public void setDisplayPageNum(int displayPageNum) {
			this.displayPageNum = displayPageNum;
		}

		public void setCri(Criteria cri) {
			this.cri = cri;
		}

		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
			
			calcData();
		}

		private void calcData() {
			
			endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum ) * displayPageNum);

			startPage = (endPage - displayPageNum) + 1;
		
			int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));
			
			if(endPage > tempEndPage){
				endPage = tempEndPage;
			}
		
			prev = startPage ==1 ? false : true;
			
			next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
			
		}

		public int getTotalCount() {
			return totalCount;
		}

		public int getStartPage() {
			return startPage;
		}

		public int getEndPage() {
			return endPage;
		}

		public boolean isPrev() {
			return prev;
		}

		public boolean isNext() {
			return next;
		}

		public int getDisplayPageNum() {
			return displayPageNum;
		}

		public Criteria getCri() {
			return cri;
		}
		
		public String makeQuery(int page){
			
			UriComponents uriComponents =
		            UriComponentsBuilder.newInstance()
		            .queryParam("page", page)
		            .queryParam("perPageNum", cri.getPerPageNum())
		            .build();	            
			
			return uriComponents.toUriString();
		}

		public String makeSearch(int page){
			
			UriComponents uriComponents =
		            UriComponentsBuilder.newInstance()
		            .queryParam("url", ((SearchCriteria)cri).getUrl())
		            .queryParam("content", ((SearchCriteria)cri).getPortal_name())
		            .queryParam("page", page)
		            .queryParam("perPageNum", cri.getPerPageNum())
		            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
		            .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
		            .queryParam("selectKey", ((SearchCriteria)cri).getSelectKey())
		            .queryParam("startDate", ((SearchCriteria)cri).getStartDate())
		            .queryParam("endDate", ((SearchCriteria)cri).getEndDate())
		            .queryParam("company", ((SearchCriteria)cri).getCompany())
		            .queryParam("textType", ((SearchCriteria)cri).getTextType())
		            .build();	            
			
			return uriComponents.toUriString();
		}
		
		public String makeSearchMobile(int page){
			
			UriComponents uriComponents =
		            UriComponentsBuilder.newInstance()
		            .queryParam("url", ((SearchCriteria)cri).getUrl())
		            .queryParam("content", ((SearchCriteria)cri).getPortal_name())
		            .queryParam("page", page)
		            .queryParam("perPageNum", cri.getPerPageNum())
		            .queryParam("searchType", ((SearchCriteria)cri).getSearchType())
		            .queryParam("keyword", ((SearchCriteria)cri).getKeyword())
		            .queryParam("company", ((SearchCriteria)cri).getCompany())
		            .queryParam("selectKey", ((SearchCriteria)cri).getSelectKey())
		            .queryParam("startDate", ((SearchCriteria)cri).getStartDate())
		            .queryParam("endDate", ((SearchCriteria)cri).getEndDate())
		            .queryParam("hour", ((SearchCriteria)cri).getHour())
		            .build();	            
			
			return uriComponents.toUriString();
		}
		
		@Override
		public String toString() {
			return "PageMaker [totalCount=" + totalCount + ", startPage="
					+ startPage + ", endPage=" + endPage + ", prev=" + prev
					+ ", next=" + next + ", displayPageNum=" + displayPageNum
					+ ", cri=" + cri + "]";
		}
	
}
