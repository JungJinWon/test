package com.model2.mvc.test;

import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

public class TestApp05 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context =
				new ClassPathXmlApplicationContext(	new String[] {	"/com/model2/mvc/resources/commonservice.xml" } );
		
		System.out.println("\n");
		UserService userService = (UserService)context.getBean("userServiceImpl");
		
		User user = new User("user22", "test", "2222", "010-2222-3333", "서울시", "a@a.a");
		user.setSsn("");
		System.out.println(":: 1. addUser(INSERT) ?");
		System.out.println("::"+ userService.addUser(user));
		System.out.println("\n");
		System.out.println(":: 2. getUser(SELECT) ?");
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
		search.setSearchKeyword("user22");
		System.out.println(search);
		System.out.println(":: List<User> 내용 : "+ userService.getUserList(search));
		
		System.out.println("::END:: SqlSession 닫기");
		
	}

}