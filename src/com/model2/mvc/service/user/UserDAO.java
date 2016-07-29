package com.model2.mvc.service.user;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

public interface UserDAO {

	public int insertUser(User user) throws Exception;
	
	public User findUser(String userId) throws Exception;
	
	public Map<String, Object> getUserList(Search search) throws Exception;
	
	public int updateUser(User user) throws Exception;
	
}
