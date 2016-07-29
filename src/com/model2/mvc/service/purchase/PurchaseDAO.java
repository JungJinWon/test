package com.model2.mvc.service.purchase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class PurchaseDAO {

	public PurchaseDAO(){
	}
	
	public Purchase findPurchase(int tranNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		String sql = "select * from TRANSACTION where TRAN_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, tranNo);

		ResultSet rs = stmt.executeQuery();
		Purchase purchaseVO = null;
		
		while (rs.next()) {
			Product productVO = new Product();
			productVO.setProdNo(rs.getInt("prod_no"));
			User userVO = new User();
			userVO.setUserId(rs.getString("buyer_id"));
			purchaseVO = new Purchase();
			purchaseVO.setTranNo(tranNo);
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setBuyer(userVO);
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
		}
		
		con.close();
		
		return purchaseVO;
	}
	
		public Purchase findPurchase2(int prodNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		String sql = "select * from TRANSACTION where PROD_NO=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, prodNo);

		ResultSet rs = stmt.executeQuery();
		Purchase purchaseVO = null;
		
		while (rs.next()) {
			Product productVO = new Product();
			productVO.setProdNo(prodNo);
			User userVO = new User();
			userVO.setUserId(rs.getString("buyer_id"));
			purchaseVO = new Purchase();
			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setBuyer(userVO);
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
		}
		
		con.close();
		
		return purchaseVO;
	} 
	
	public Map<String,Object> getPurchaseList(Search searchVO, String buyerId) throws Exception {
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		System.out.println(buyerId);
		Connection con = DBUtil.getConnection();
		String sql = "select * from TRANSACTION ";
		sql += " where buyer_id = '"+ buyerId +"'";
		sql += " order by PROD_NO";
		
		int total = this.getTotal(sql);
		System.out.println("getPurchaseList-total :: "+total);
		sql = makeCurrentPageSql(sql, searchVO);
		PreparedStatement stmt = 
				con.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		System.out.println(searchVO);
		
		ArrayList<Purchase> list = new ArrayList<Purchase>();
		while(rs.next()) {
				ProductService service=new ProductServiceImpl();
				Product productVO = new Product();
				productVO.setProdNo(rs.getInt("prod_no"));
				productVO = (Product)service.findProduct(productVO.getProdNo());
				User userVO = new User();
				userVO.setUserId(rs.getString("buyer_id"));
				
				Purchase vo = new Purchase();
				vo.setTranNo(rs.getInt("tran_no"));
				vo.setPurchaseProd(productVO);
				vo.setBuyer(userVO);
				vo.setPaymentOption(rs.getString("payment_option"));
				vo.setReceiverName(rs.getString("receiver_name"));
				vo.setReceiverPhone(rs.getString("receiver_phone"));
				vo.setDivyAddr(rs.getString("demailaddr"));
				vo.setDivyRequest(rs.getString("dlvy_request"));
				vo.setTranCode(rs.getString("tran_status_code"));
				vo.setDivyDate(rs.getString("dlvy_date"));
				vo.setOrderDate(rs.getDate("order_data"));
				
				list.add(vo);
			
		}
		map.put("total", new Integer(total));
		System.out.println("list.size() : "+ list.size());
		map.put("list", list);
		System.out.println("map().size() : "+ map.size());
	
		rs.close();
		stmt.close();
		con.close();
			
		return map;
	}
	
	public HashMap<String,Object> getSaleList(Search searchVO) throws Exception {
		return null;
	}
	
	public void addPurchase(Purchase purchaseVO) throws Exception{
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into TRANSACTION values(seq_transaction_tran_no.nextval,?,?,?,?,?,?,?,?,sysdate,?)";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		stmt.setObject(2, purchaseVO.getBuyer().getUserId());
		stmt.setString(3, purchaseVO.getPaymentOption());
		stmt.setString(4, purchaseVO.getReceiverName());
		stmt.setString(5, purchaseVO.getReceiverPhone());
		stmt.setString(6, purchaseVO.getDivyAddr());
		stmt.setString(7, purchaseVO.getDivyRequest());
		stmt.setString(8, purchaseVO.getTranCode());
		stmt.setString(9, purchaseVO.getDivyDate());
		stmt.executeUpdate();
		
		con.close();
	}
	
	public void updatePurchase(Purchase purchaseVO) throws Exception{
		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTION set buyer_id=?,payment_option=?,receiver_name=?,receiver_phone=?,demailaddr=?,dlvy_request=?,dlvy_date=? where tran_no=?";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getBuyer().getUserId());
		stmt.setString(2, purchaseVO.getPaymentOption());
		stmt.setString(3, purchaseVO.getReceiverName());
		stmt.setString(4, purchaseVO.getReceiverPhone());
		stmt.setString(5, purchaseVO.getDivyAddr());
		stmt.setString(6, purchaseVO.getDivyRequest());
		stmt.setString(7, purchaseVO.getDivyDate());
		stmt.setInt(8, purchaseVO.getTranNo());
		stmt.executeUpdate();
		
		con.close();
	}
	
	public void updateTranCode(Purchase purchaseVO) throws Exception{
		Connection con = DBUtil.getConnection();

		String sql = "update TRANSACTION set tran_status_code=? where prod_no=? ";
		
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, purchaseVO.getTranCode());
		stmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		stmt.executeUpdate();
		
		con.close();
	}
	
	private int getTotal(String sql) throws Exception {
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int total = 0;
		if( rs.next() ){
			total = rs.getInt(1);
			System.out.println("total 개수 : "+total);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		return total;
	}
	
	// 게시판 currentPage Row 만  return 
	private String makeCurrentPageSql(String sql , Search searchVO){
		sql = 	"SELECT * "+ 
					"FROM (		SELECT inner_table. * ,  ROWNUM AS row_seq " +
									" 	FROM (	"+sql+" ) inner_table "+
									"	WHERE ROWNUM <="+searchVO.getPage()*searchVO.getPageSize()+" ) " +
					"WHERE row_seq BETWEEN "+((searchVO.getPage()-1)*searchVO.getPageSize()+1) +" AND "+searchVO.getPage()*searchVO.getPageSize();
		
		System.out.println("ProductDAO :: make SQL :: "+ sql);	
		
		return sql;
	}
}
