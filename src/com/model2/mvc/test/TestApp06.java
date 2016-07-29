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
		
		User user = new User("user22", "test", "2222", "010-2222-3333", "�����", "a@a.a");
		user.setSsn("");
		
		try{
			System.out.println("\n::::::::::::::::::: Test ���Ȯ��.... start  :::::::::::::::::::::::::::");
			System.out.println(":: ȸ������ ��û��....");
			userService.addUser(user);
			System.out.println(":: ȸ������ ������....");
			System.out.println("::::::::::::::::::::: Test ���Ȯ��.... end  ::::::::::::::::::::::::::::");
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
