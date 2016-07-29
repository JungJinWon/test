package com.model2.mvc.service.user.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserDAO;
import com.model2.mvc.service.user.UserService;

@Repository("userServiceImpl")
public class UserServiceImpl implements UserService{
	
	@Autowired
	@Qualifier("userDAOImpl")
	UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO){
		System.out.println("::"+getClass()+".setUserDao call....");
		this.userDAO = userDAO;
	}
	
	/// Constructor
	public UserServiceImpl(){
		System.out.println("::"+getClass()+"default Constructor call....");
	}

	public int addUser(User userVO) throws Exception {
		/////////////////////////////////////////////////
		//// AOP�� �̿��� Transaction ó���� ���� ������ �κ� ////
		return userDAO.insertUser(userVO);
		/////////////////////////////////////////////////
		//int result = 0;
		//System.out.println(">>>>>>>> 1��° Insert ===================");
		//result = userDAO.insertUser(userVO);
		//System.out.println(">>>>>>>> 1��° Insert ��� : "+result);
		//System.out.println(">>>>>>>> 2��° Insert ===================");
		//result = userDAO.insertUser(userVO);
		//System.out.println(">>>>>>>> 2��° Insert ���  : "+result);
		//System.out.println(">>>>>>>> ����� ?????? ===================");
		
		//return 0;
		/////////////////////////////////////////////////
	}

	public User loginUser(User userVO) throws Exception {
			User dbUser=userDAO.findUser(userVO.getUserId());

			if(! dbUser.getPassword().equals(userVO.getPassword()))
				throw new Exception("�α��ο� �����߽��ϴ�.");
			
			return dbUser;
	}

	public User getUser(String userId) throws Exception {
		return userDAO.findUser(userId);
	}

	public Map<String,Object> getUserList(Search searchVO) throws Exception {
		return userDAO.getUserList(searchVO);
	}

	public int updateUser(User userVO) throws Exception {
		return userDAO.updateUser(userVO);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User userVO=userDAO.findUser(userId);
		if(userVO != null) {
			result=false;
		}
		return result;
	}
}