package org.union.persistence;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.CommunityVO;
import org.union.domain.MediaVO;
import org.union.domain.MonitorVO;
import org.union.domain.PortalVO;
import org.union.domain.SNSVO;


@Repository
public class MonitorDAOImpl implements MonitorDAO{

	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mapper.MonitorMapper.";
	
	@Override
	public void create(MonitorVO vo) throws SQLException {

		try {
			session1.insert(namespace + "create", vo);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MonitorVO> pageAll() throws SQLException {

		return session1.selectList(namespace + "pageAll");
	}

	@Override
	public MonitorVO checkLast(String domain) throws SQLException {

		return session1.selectOne(namespace + "checkLast", domain);
	}
	
	@Override
	public PortalVO checkLast2(String portal_type) throws SQLException {

		return session1.selectOne(namespace + "checkLast2", portal_type);
	}
	
	@Override
	public SNSVO checkLast3(String sns_name) throws SQLException {
		
		return session1.selectOne(namespace + "checkLast3", sns_name);
	}
	
	@Override
	public MediaVO checkLast4(String media_name) throws SQLException {
		
		return session1.selectOne(namespace + "checkLast4", media_name);
	}
	
	@Override
	public CommunityVO checkLast5(String community_name) throws SQLException {
		
		return session1.selectOne(namespace + "checkLast5", community_name);
	}

	@Override
	public List<PortalVO> portalBMonitor() throws SQLException {
		return session1.selectList(namespace + "portalBMonitor");			
	}
	
	@Override
	public List<PortalVO> portalCMonitor() throws SQLException {
		return session1.selectList(namespace + "portalCMonitor");			
	}
	
	@Override
	public List<PortalVO> portalWMonitor() throws SQLException {
		return session1.selectList(namespace + "portalWMonitor");			
	}
	
	@Override
	public List<PortalVO> portalBMonitor2() throws SQLException {
		return session1.selectList(namespace + "portalBMonitor2");			
	}
	
	@Override
	public List<PortalVO> portalCMonitor2() throws SQLException {
		return session1.selectList(namespace + "portalCMonitor2");			
	}
	
	@Override
	public List<PortalVO> portalWMonitor2() throws SQLException {
		return session1.selectList(namespace + "portalWMonitor2");			
	}

	@Override
	public List<SNSVO> snsMonitorf() throws SQLException {
		return session1.selectList(namespace + "snsMonitorf");			
	}

	@Override
	public List<SNSVO> snsMonitori() throws SQLException {
		return session1.selectList(namespace + "snsMonitori");			
	}

	@Override
	public List<SNSVO> snsMonitort() throws SQLException {
		return session1.selectList(namespace + "snsMonitort");			
	}

	@Override
	public List<MediaVO> mediaMonitor(String media) throws SQLException {
		return session1.selectList(namespace + "mediaMonitor", media);
	}

	@Override
	public List<CommunityVO> communityMonitor(String community) throws SQLException {
		return session1.selectList(namespace + "communityMonitor", community);
	}
}
