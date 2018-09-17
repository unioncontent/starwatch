package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.CommunityVO;
import org.union.domain.MediaVO;
import org.union.domain.MonitorVO;
import org.union.domain.PortalVO;
import org.union.domain.SNSVO;

public interface MonitorService {
	
	
	public void insert(MonitorVO vo) throws SQLException;
	
	public List<MonitorVO> pageAll() throws SQLException;
	
	public List<PortalVO> portalBMonitor() throws SQLException;
	public List<PortalVO> portalCMonitor() throws SQLException;
	public List<PortalVO> portalWMonitor() throws SQLException;
	public List<PortalVO> portalBMonitor2() throws SQLException;
	public List<PortalVO> portalCMonitor2() throws SQLException;
	public List<PortalVO> portalWMonitor2() throws SQLException;
	
	public List<SNSVO> snsMonitorf() throws SQLException;
	public List<SNSVO> snsMonitori() throws SQLException;
	public List<SNSVO> snsMonitort() throws SQLException;
	
	public List<MediaVO> mediaMonitor(String media) throws SQLException;
	
	public List<CommunityVO> communityMonitor(String community) throws SQLException;

}
