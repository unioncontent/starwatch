package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.CommunityVO;
import org.union.domain.MediaVO;
import org.union.domain.MonitorVO;
import org.union.domain.PortalVO;
import org.union.domain.SNSVO;
import org.union.persistence.MonitorDAO;

@Service
public class MonitorServiceImpl implements MonitorService {

	
	private static final String String = null;
	@Autowired
	private MonitorDAO monitorDAO;
	
	
	@Override
	public void insert(MonitorVO vo) throws SQLException {

		monitorDAO.create(vo);
	}


	@Override
	public List<MonitorVO> pageAll() throws SQLException {

		List<MonitorVO> list = monitorDAO.pageAll();
		
		for (MonitorVO monitorVO : list) {
			
			MonitorVO vo = monitorDAO.checkLast(monitorVO.getMonitor_domain());
		
			if(vo != null) {
				monitorVO.setCheckCondition(true);
				monitorVO.setTitle(vo.getTitle());
				monitorVO.setUrl(vo.getUrl());
				monitorVO.setCreateDate(vo.getCreateDate());
				
			}else {
				monitorVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<PortalVO> portalBMonitor() throws SQLException {

		List<PortalVO> list = monitorDAO.portalBMonitor();
		for (PortalVO portalVO : list) {
			
			PortalVO vo = monitorDAO.checkLast2(portalVO.getPortal_type());
			
			
			if(vo != null) {
				portalVO.setCheckCondition(true);
				
				
			}else {
				portalVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<PortalVO> portalCMonitor() throws SQLException {
		List<PortalVO> list = monitorDAO.portalCMonitor();
		for (PortalVO portalVO : list) {
			
			PortalVO vo = monitorDAO.checkLast2(portalVO.getPortal_type());
			
			
			if(vo != null) {
				portalVO.setCheckCondition(true);
				
				
			}else {
				portalVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<PortalVO> portalWMonitor() throws SQLException {
		List<PortalVO> list = monitorDAO.portalWMonitor();
		for (PortalVO portalVO : list) {
			
			PortalVO vo = monitorDAO.checkLast2(portalVO.getPortal_type());
			
			
			if(vo != null) {
				portalVO.setCheckCondition(true);
				
				
			}else {
				portalVO.setCheckCondition(false);
			}
		}
		
		return list;
	}
	
	@Override
	public List<PortalVO> portalBMonitor2() throws SQLException {

		List<PortalVO> list = monitorDAO.portalBMonitor2();
		for (PortalVO portalVO : list) {
			
			PortalVO vo = monitorDAO.checkLast2(portalVO.getPortal_type());
			
			
			if(vo != null) {
				portalVO.setCheckCondition(true);
				
				
			}else {
				portalVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<PortalVO> portalCMonitor2() throws SQLException {
		List<PortalVO> list = monitorDAO.portalCMonitor2();
		for (PortalVO portalVO : list) {
			
			PortalVO vo = monitorDAO.checkLast2(portalVO.getPortal_type());
			
			
			if(vo != null) {
				portalVO.setCheckCondition(true);
				
				
			}else {
				portalVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<PortalVO> portalWMonitor2() throws SQLException {
		List<PortalVO> list = monitorDAO.portalWMonitor2();
		for (PortalVO portalVO : list) {
			
			PortalVO vo = monitorDAO.checkLast2(portalVO.getPortal_type());
			
			
			if(vo != null) {
				portalVO.setCheckCondition(true);
				
				
			}else {
				portalVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<SNSVO> snsMonitorf() throws SQLException {
		List<SNSVO> list = monitorDAO.snsMonitorf();
		for (SNSVO snsVO : list) {
			
			SNSVO vo = monitorDAO.checkLast3(snsVO.getSns_name());
			
			
			if(vo != null) {
				snsVO.setCheckCondition(true);
				
				
			}else {
				snsVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<SNSVO> snsMonitori() throws SQLException {
		List<SNSVO> list = monitorDAO.snsMonitori();
		for (SNSVO snsVO : list) {
			
			SNSVO vo = monitorDAO.checkLast3(snsVO.getSns_name());
			
			
			if(vo != null) {
				snsVO.setCheckCondition(true);
				
				
			}else {
				snsVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<SNSVO> snsMonitort() throws SQLException {
		List<SNSVO> list = monitorDAO.snsMonitort();
		for (SNSVO snsVO : list) {
			
			SNSVO vo = monitorDAO.checkLast3(snsVO.getSns_name());
			
			
			if(vo != null) {
				snsVO.setCheckCondition(true);
				
				
			}else {
				snsVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<MediaVO> mediaMonitor(String media) throws SQLException {
		
		List<MediaVO> list = monitorDAO.mediaMonitor(media);
		for (MediaVO mediaVO : list) {
			
			MediaVO vo = monitorDAO.checkLast4(mediaVO.getMedia_name());
			
			
			if(vo != null) {
				mediaVO.setCheckCondition(true);
				
				
			}else {
				mediaVO.setCheckCondition(false);
			}
		}
		
		return list;
	}


	@Override
	public List<CommunityVO> communityMonitor(String community) throws SQLException {
		
		List<CommunityVO> list = monitorDAO.communityMonitor(community);
		for (CommunityVO communityVO : list) {
			
			CommunityVO vo = monitorDAO.checkLast5(communityVO.getCommunity_name());
			
			
			if(vo != null) {
				communityVO.setCheckCondition(true);
				
				
			}else {
				communityVO.setCheckCondition(false);
			}
		}
		
		return list;
	}
}
