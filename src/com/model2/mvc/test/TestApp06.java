package com.model2.mvc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;

public class TestApp06 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context =
				new ClassPathXmlApplicationContext(	new String[] {	"/com/model2/mvc/resources/commonservice.xml" } );
		
		System.out.println("\n");
		UserService userService = (UserService)context.getBean("userServiceImpl");
		
		User user = new User("user22", "test", "2222", "010-2222-3333", "서울시", "a@a.a");
		user.setSsn("");
		
		try{
			System.out.println("\n::::::::::::::::::: Test 결과확인.... start  :::::::::::::::::::::::::::");
			System.out.println(":: 회원가입 요청함....");
			userService.addUser(user);
			System.out.println(":: 회원가입 종료함....");
			System.out.println("::::::::::::::::::::: Test 결과확인.... end  ::::::::::::::::::::::::::::");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
