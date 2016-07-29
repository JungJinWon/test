package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseDAO;
import com.model2.mvc.service.purchase.PurchaseService;

public class PurchaseServiceImpl implements PurchaseService {

	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl(){
		purchaseDAO = new PurchaseDAO();
	}
	
	public Purchase findPurchase(int transNo) throws Exception{
		return purchaseDAO.findPurchase(transNo);
	}
	
	public Purchase findPurchase2(int prodNo) throws Exception{
		return purchaseDAO.findPurchase2(prodNo);
	}
	
	public Map<String,Object> getPurchaseList(Search searchVO, String buyerId) throws Exception {
		return purchaseDAO.getPurchaseList(searchVO, buyerId);
	}
	
	public HashMap<String,Object> getSaleList(Search searchVO) throws Exception {
		return purchaseDAO.getSaleList(searchVO);
	}
	
	public void addPurchase(Purchase purchaseVO) throws Exception {
		purchaseDAO.addPurchase(purchaseVO);
	}
	
	public void updatePurchase(Purchase purchaseVO) throws Exception {
		purchaseDAO.updatePurchase(purchaseVO);
	}
	
	public void updateTranCode(Purchase purchaseVO) throws Exception {
		purchaseDAO.updateTranCode(purchaseVO);
	}
}