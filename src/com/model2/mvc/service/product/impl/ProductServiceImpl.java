package com.model2.mvc.service.product.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;
import com.model2.mvc.service.product.ProductService;

@Repository("productServiceImpl")
public class ProductServiceImpl implements ProductService {

	@Autowired
	@Qualifier("productDAOImpl")
	ProductDAO productDAO;
	
	public void setProductDAO(ProductDAO productDAO){
		System.out.println("::"+getClass()+".setProductDAO call....");
		this.productDAO = productDAO;
	}
	
	/// Constructor
	public ProductServiceImpl(){
		System.out.println("::"+getClass()+"default Constructor call....");
	}
	
	public int insertProduct(Product product) throws Exception {
		return productDAO.insertProduct(product);
	}
	
	public Product findProduct(int prodNo) throws Exception{
		return productDAO.findProduct(prodNo);
	}
	
	public Map<String,Object> getProductList(Search search) throws Exception {
		return productDAO.getProductList(search);
	}
	
	public int updateProduct(Product product) throws Exception{
		return productDAO.updateProduct(product);
	}
}