package com.model2.mvc.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

public class TestApp05Product {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context =
				new ClassPathXmlApplicationContext(	new String[] {	"/com/model2/mvc/resources/commonservice.xml" } );
		
		System.out.println("\n");
		ProductService productService = (ProductService)context.getBean("productServiceImpl");
		
		Product product = new Product("20101010", "뮤츠", "전설포켓몬", 30000);
		product.setFileName("");
		System.out.println(":: 1. addUser(INSERT) ?");
		System.out.println("::"+ productService.insertProduct(product));
		System.out.println("\n");
		System.out.println(":: 2. getUser(SELECT) ?");
		System.out.println("::"+ productService.findProduct(product.getProdNo()));
		System.out.println("\n");
		product.setProdName("파이리");
		product.setProdDetail("불꽃 포켓몬");
		System.out.println(":: 3. updateUser(UPDATE) ?");
		System.out.println("::"+ productService.updateProduct(product));
		System.out.println("\n");
		System.out.println(":: 4. getUserList(SELECT) ?");
		Search search = new Search();
		search.setSearchCondition("0");
		search.setSearchKeyword("파이리");
		System.out.println(search);
		System.out.println(":: List<User> 내용 : "+ productService.getProductList(search));
		
		System.out.println("::END:: SqlSession 닫기");
		
	}

}
