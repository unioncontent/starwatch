package org.union.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.union.domain.ExtractVO;
import org.union.domain.IssueVO;
import org.union.domain.KeywordListVO;
import org.union.domain.KeywordVO;
import org.union.domain.SearchCriteria;
import org.union.domain.SwearwordVO;
import org.union.domain.UserVO;
import org.union.persistence.KeywordDAO;
import org.union.persistence.UserDAO;

@Service
public class KeywordServiceImpl implements KeywordService{

	
	@Autowired
	private KeywordDAO keywordDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Override
	public List<KeywordVO> listByUser(Integer user_idx) throws SQLException {

		return keywordDAO.listByUser(user_idx);
	}
	
	@Override
	public List<KeywordVO> getKeyword(String keyword_main) throws SQLException {

		return keywordDAO.getKeyword(keyword_main);
	}

	@Override
	public List<ExtractVO> viewByKeyword(List<ExtractVO> list) throws SQLException {
		
		try {
			for(int i = 0; i < list.size(); i++) {

				KeywordVO keywordVO = keywordDAO.readByKeyword(list.get(i).getKeyword());
				
				if(keywordVO != null) {
					UserVO userVO = userDAO.read(keywordVO.getUser_idx());
					
					if(userVO != null) {
						list.get(i).setCompany(userVO.getUser_name());
						
					}else {
						list.get(i).setCompany(" ");
					}

				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}

		return list;
	}

	@Override
	public List<KeywordVO> listAll() throws SQLException {

		List<KeywordVO> keywordList = keywordDAO.listAll();

		try {
			
			
			for (KeywordVO keywordVO : keywordList) {
				keywordVO.setCompany_name(userDAO.read(keywordVO.getUser_idx()).getUser_name());
			}
			
			/*ArrayList<String> arr = new ArrayList<String>();
			String name = null;
			
			for (KeywordVO keywordVO : keywordList) throws SQLException {
				UserVO userVO = userDAO.read(keywordVO.getKeyword_idx());
				System.out.println(keywordMap.get(userVO.getUser_name()));
				if(keywordMap.get(userVO.getUser_name()) == null) throws SQLException {
					arr.add(keywordVO.getKeyword());
					name = userVO.getUser_name();
					System.out.println("arr: " + arr);
					System.out.println("name: " + name);
				
				}else throws SQLException {
					System.out.println("else");
					keywordMap.put(name, arr);
					
					arr = null;
					name = null;
				}
			}*/
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return keywordList;
	}
	
	@Override
	public List<KeywordVO> showboxListAll() throws SQLException {

		List<KeywordVO> keywordList = keywordDAO.showboxListAll();

		try {
			
			
			for (KeywordVO keywordVO : keywordList) {
				keywordVO.setCompany_name(userDAO.read(keywordVO.getUser_idx()).getUser_name());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return keywordList;
	}

	@Override
	public List<KeywordListVO> listPage(SearchCriteria cri) throws SQLException {

		return keywordDAO.listPage(cri);
	}
	
	@Override
	public void insertMain(KeywordListVO vo) throws SQLException {

		keywordDAO.createMain(vo);
	}

	@Override
	public List<KeywordVO> listByMain(String keyword_main) throws SQLException {

		return keywordDAO.listByMain(keyword_main);
	}

	@Override
	public void insertKeyword(KeywordVO vo) throws SQLException {

		keywordDAO.createKeyword(vo);
	}

	@Override
	public Integer checkMain(String keyword_main) throws SQLException {

		return keywordDAO.checkMain(keyword_main);
	}

	@Override
	public void removeMain(String keyword_main) throws SQLException {

		keywordDAO.deleteMain(keyword_main);
	}

	@Override
	public void remove(String keyword) throws SQLException {

		keywordDAO.delete(keyword);
	}

	@Override
	public KeywordVO view(String keyword) throws SQLException {

		return keywordDAO.read(keyword);
	}

	@Override
	public List<SwearwordVO> swearwordList() throws SQLException {

		return keywordDAO.swearwordList();
	}

	@Override
	public void swearwordCreate(SwearwordVO vo) throws SQLException {

		keywordDAO.swearwordCreate(vo);
	}

	@Override
	public void swearwordDelete(String swearword) throws SQLException {
		keywordDAO.swearwordDelete(swearword);
	}

	@Override
	public Integer checkSwearword(String swearword) throws SQLException {

		return keywordDAO.checkSwearword(swearword);
	}

	@Override
	public void stateUpdateOn(String keyword_main) throws SQLException {
		keywordDAO.stateUpdateOn(keyword_main);
		
	}

	@Override
	public void stateUpdateOff(String keyword_main) throws SQLException {
		keywordDAO.stateUpdateOff(keyword_main);
		
	}

	@Override
	public List<KeywordVO> keywordGetList(SearchCriteria cri) throws SQLException {

		return keywordDAO.keywordGetList(cri);
	}

	@Override
	public void createIssue(IssueVO vo) throws SQLException {

		keywordDAO.createIssue(vo);
	}

	@Override
	public List<IssueVO> issueList(SearchCriteria cri) throws SQLException {

		return keywordDAO.issueList(cri);
	}

	@Override
	public List<IssueVO> issueUpList(SearchCriteria cri) throws SQLException {

		return keywordDAO.issueUpList(cri);
	}

	@Override
	public void issueUpdate(IssueVO vo) throws SQLException {

		keywordDAO.issueUpdate(vo);
	}

	@Override
	public void issueDelete(Integer issue_idx) throws SQLException {

		keywordDAO.issueDelete(issue_idx);
	}
}
