package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import org.union.domain.CommunityVO;
import org.union.domain.MediaVO;
import org.union.domain.MonitorVO;
import org.union.domain.PortalVO;
import org.union.domain.SNSVO;

public interface MonitorDAO {

	
	public void create(MonitorVO vo) throws SQLException;
	
	public List<MonitorVO> pageAll() throws SQLException;
	
	public MonitorVO checkLast(String domain) throws SQLException;
	public PortalVO checkLast2(String portal_type) throws SQLException;
	public SNSVO checkLast3(String sns_name) throws SQLException;
	public MediaVO checkLast4(String media_name) throws SQLException;
	public CommunityVO checkLast5(String community_name) throws SQLException;
	
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
