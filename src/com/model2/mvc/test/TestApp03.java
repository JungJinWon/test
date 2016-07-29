package com.model2.mvc.test;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.impl.UserDAOImpl;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class TestApp03 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		
		UserDAOImpl userDAO = new UserDAOImpl();
		userDAO.setSqlSession(sqlSession);
		System.out.println("\n");
		
		UserServiceImpl userService = new UserServiceImpl();
		userService.setUserDAO(userDAO);
		System.out.println("\n");
		
		User user = new User("user22", "test", "2222", "010-2222-3333", "서울시", "a@a.a");
		user.setSsn("");
		System.out.println(":: 1. insertUser(INSERT) ?");
		System.out.println("::"+ userService.addUser(user));
		System.out.println("\n");
		System.out.println(":: 2. findUser(SELECT) ?");
		System.out.println("::"+ userService.getUser(user.getUserId()));
		System.out.println("\n");
		user.setUserName("test1");
		user.setPassword("222");
		System.out.println(":: 3. updateUser(UPDATE) ?");
		System.out.println("::"+ userService.updateUser(user));
		System.out.println("\n");
		System.out.println(":: 4. getUserList(SELECT) ?");
		Search search = new Search();
		search.setSearchCondition("0");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("user22");
		search.setUserId(arrayList);
		System.out.println(search);
		System.out.println(":: List<User> 내용 : "+ userService.getUserList(search));
		
		System.out.println("::END:: SqlSession 닫기");
		sqlSession.close();
		
	}

}
