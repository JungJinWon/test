package com.model2.mvc.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDAO;

@Repository("userDAOImpl")
public class UserDAOImpl implements UserDAO{
	
	/// Field
	private SqlSession sqlSession;
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::" + getClass() + ".setSqlSession() Call...");
		this.sqlSession = sqlSession;
	}

	/// Constructor
	public UserDAOImpl() {
		System.out.println("::" + getClass() + "default Constructor Call...");
	}

	public int insertUser(User user) throws Exception {
		
		return sqlSession.insert("UserMapper.insertUser",user);
	}

	public User findUser(String userId) throws Exception {
		
		return (User)sqlSession.selectOne("UserMapper.findUser",userId);
	}

	public Map<String,Object> getUserList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = sqlSession.selectList("UserMapper.getUserList", search);
		int total = this.getTotal(search);
		System.out.println("UserDAO :: total :: "+total);
		
		map.put("total", new Integer(total));
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());

		return map;
	}

	public int updateUser(User user) throws Exception {
		
		return sqlSession.update("UserMapper.updateUser", user);
	}
	
	private int getTotal(Search search) throws Exception {
		
		return sqlSession.selectOne("UserMapper.getTotal", search);
	}
}
