package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDAO;

@Repository("productDAOImpl")
public class ProductDAOImpl implements ProductDAO {
	
	private SqlSession sqlSession;
	
	@Autowired
	@Qualifier("sqlSessionTemplate")
	public void setSqlSession(SqlSession sqlSession) {
		System.out.println("::" + getClass() + ".setSqlSession() Call...");
		this.sqlSession = sqlSession;
	}

	/// Constructor
	public ProductDAOImpl() {
		System.out.println("::" + getClass() + "default Constructor Call...");
	}
	
	public int insertProduct(Product product) throws Exception {

		return sqlSession.insert("ProductMapper.insertProduct", product);
	}

	public Product findProduct(int prodNo) throws Exception {

		/*Product productVO = null;
		while (rs.next()) {
			productVO = new Product();
			productVO.setProdNo(rs.getInt("prod_no"));
			productVO.setProdName(rs.getString("prod_name"));
			productVO.setProdDetail(rs.getString("prod_detail"));
			productVO.setManuDate(rs.getString("manufacture_day"));
			productVO.setPrice(rs.getInt("price"));
			productVO.setFileName(rs.getString("image_file"));
			productVO.setRegDate(rs.getDate("REG_DATE"));
			productVO.setProTranCode(rs.getString("tran_status_code"));
		}*/

		return (Product)sqlSession.selectOne("ProductMapper.findProduct", prodNo);
	}

	public Map<String, Object> getProductList(Search search) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();
		List<String> list = sqlSession.selectList("ProductMapper.getProductList", search);
		int total = this.getTotal(search);
		System.out.println(search);
		System.out.println("ProductDAO :: total :: "+total);
		
		map.put("total", new Integer(total));
		System.out.println("list.size() : " + list.size());
		map.put("list", list);
		System.out.println("map().size() : " + map.size());

		return map;
	}
	
	public int updateProduct(Product product) throws Exception{
		
		return sqlSession.update("ProductMapper.updateProduct", product);
	}
	
	private int getTotal(Search search) throws Exception {
		
		return sqlSession.selectOne("ProductMapper.getTotal", search);
	}
	
}
