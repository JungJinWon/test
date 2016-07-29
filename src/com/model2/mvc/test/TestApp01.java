package com.model2.mvc.test;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;

public class TestApp01 {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		SqlSession sqlSession = SqlSessionFactoryBean.getSqlSession();
		System.out.println("\n");
		
		User user = new User("user22", "test", "2222", "010-2222-3333", "서울시", "a@a.a");
		user.setSsn(new String());
		System.out.println(":: 1. insertUser(INSERT) ?");
		System.out.println("::"+ sqlSession.insert("UserMapper.insertUser",user));
		System.out.println("\n");
		System.out.println(":: 2. findUser(SELECT) ?");
		System.out.println("::"+ sqlSession.selectOne("UserMapper.findUser",user.getUserId()));
		System.out.println("\n");
		user.setUserName("test1");
		user.setPassword("222");
		System.out.println(":: 3. updateUser(UPDATE) ?");
		System.out.println("::"+ sqlSession.update("UserMapper.updateUser",user));
		System.out.println("\n");
		System.out.println(":: 4. getUserList(SELECT) ? id/password 확인");
		System.out.println("::"+ sqlSession.selectOne("UserMapper.findUser",user.getUserId()));
		System.out.println("\n"); 
		
		Search search = new Search();
		System.out.println(":: 1. getUserList01(SELECT) ?");
		SqlSessionFactoryBean.printList(sqlSession.selectList("UserMapper.getUserList",search));
		search.setSearchCondition("0");
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("user01");
		search.setUserId(arrayList);
		System.out.println(":: 2. getUserList01(SELECT) ?"+search);
		SqlSessionFactoryBean.printList(sqlSession.selectList("UserMapper.getUserList",search));
		
		System.out.println("::END:: SqlSession 닫기");
		sqlSession.close();
		
		
		
		

	}

}
