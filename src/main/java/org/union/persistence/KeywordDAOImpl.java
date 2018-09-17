package org.union.persistence;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.union.domain.IssueVO;
import org.union.domain.KeywordListVO;
import org.union.domain.KeywordVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SwearwordVO;

@Repository
public class KeywordDAOImpl implements KeywordDAO {

	
	@Autowired
	@Resource(name="oneSqlSession")
	private SqlSession session1;
	
	@Autowired
	@Resource(name="twoSqlSession")
	private SqlSession session2;
	
	private static final String namespace = "org.union.mapper.KeywordMapper.";
	
	
	@Override
	public void create(KeywordVO vo) throws SQLException {
		try {
			session1.insert(namespace + "create", vo);
			session2.insert(namespace + "create", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public KeywordVO read(String keyword) throws SQLException {

		return session1.selectOne(namespace + "read", keyword);
	}

	@Override
	public void update(KeywordVO vo) throws SQLException {
		try {
			session1.update(namespace + "update", vo);
			session2.update(namespace + "update", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stateUpdateOn(String keyword_main) throws SQLException {
		try {
			session1.update(namespace + "stateUpdateOn", keyword_main);
			session2.update(namespace + "stateUpdateOn", keyword_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void stateUpdateOff(String keyword_main) throws SQLException {
		try {
			session1.update(namespace + "stateUpdateOff", keyword_main);
			session2.update(namespace + "stateUpdateOff", keyword_main);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(String keyword) throws SQLException {
		try {
			session1.delete(namespace + "delete", keyword);
			session2.delete(namespace + "delete", keyword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<KeywordVO> listByUser(Integer user_idx) throws SQLException {

		return session1.selectList(namespace + "listByUser", user_idx);
	}
	
	@Override
	public List<KeywordVO> getKeyword(String keyword_main) throws SQLException {

		return session1.selectList(namespace + "getKeyword", keyword_main);
	}

	@Override
	public KeywordVO readByKeyword(String keyword) throws SQLException {

		return session1.selectOne(namespace + "readByKeyword",keyword);
	}

	@Override
	public List<KeywordVO> listAll() throws SQLException {

		return session1.selectList(namespace + "listAll");
	}
	
	@Override
	public List<KeywordVO> showboxListAll() throws SQLException {

		return session1.selectList(namespace + "showboxListAll");
	}

	@Override
	public List<KeywordListVO> listPage(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "listPage", cri);
	}
	
	@Override
	public void createMain(KeywordListVO vo) throws SQLException {

		session1.insert(namespace + "createMain", vo);
		session2.insert(namespace + "createMain", vo);
	}

	@Override
	public List<KeywordVO> listByMain(String keyword_main) throws SQLException {

		return session1.selectList(namespace + "listByMain", keyword_main);
	}

	@Override
	public void createKeyword(KeywordVO vo) throws SQLException {

		session1.insert(namespace + "createKeyword", vo);
		session2.insert(namespace + "createKeyword", vo);
	}

	@Override
	public Integer checkMain(String keyword_main) throws SQLException {
		

		return session1.selectOne(namespace + "checkMain", keyword_main);
	}

	@Override
	public void deleteMain(String keyword_main) throws SQLException {

		session1.delete(namespace + "deleteMain", keyword_main);
		session2.delete(namespace + "deleteMain", keyword_main);
	}

	@Override
	public List<SwearwordVO> swearwordList() throws SQLException {

		return session1.selectList(namespace + "swearwordList");
	}

	@Override
	public void swearwordCreate(SwearwordVO vo) throws SQLException {
		try {
			session1.insert(namespace + "swearwordCreate", vo);
			session2.insert(namespace + "swearwordCreate", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void swearwordDelete(String swearword) throws SQLException {
		try {
			session1.delete(namespace + "swearwordDelete", swearword);
			session2.delete(namespace + "swearwordDelete", swearword);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Integer checkSwearword(String swearword) throws SQLException {
		return session1.selectOne(namespace + "checkSwearword", swearword);
	}

	@Override
	public List<KeywordVO> keywordGetList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "keywordGetList", cri);
	}

	@Override
	public void createIssue(IssueVO vo) throws SQLException {
		try {
			session1.insert(namespace + "createIssue", vo);
			session2.insert(namespace + "createIssue", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<IssueVO> issueList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "issueList", cri);
	}

	@Override
	public List<IssueVO> issueUpList(SearchCriteria cri) throws SQLException {

		return session1.selectList(namespace + "issueUpList", cri);
	}

	@Override
	public void issueUpdate(IssueVO vo) throws SQLException {
		try {
			session1.update(namespace + "issueUpdate", vo);
			session2.update(namespace + "issueUpdate", vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void issueDelete(Integer issue_idx) throws SQLException {
		try {
			session1.delete(namespace + "issueDelete", issue_idx);
			session2.delete(namespace + "issueDelete", issue_idx);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
