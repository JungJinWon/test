package com.model2.mvc.view.purchase;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeAction extends Action {
	@Override
	public String execute(	HttpServletRequest request,
											HttpServletResponse response) throws Exception {
		String tranCode = request.getParameter("tranCode");
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Purchase purchaseVO = new Purchase();
		HttpSession session = request.getSession(); 
		User userVO = (User)session.getAttribute("user");
		purchaseVO = (Purchase)request.getAttribute("pvo");
		
		purchaseVO.setTranCode(tranCode);
		int tranNo = purchaseVO.getTranNo();
		PurchaseService service=new PurchaseServiceImpl();
		
		purchaseVO = service.findPurchase(tranNo);
		
		service.updateTranCode(purchaseVO);
		
		return "forward:/listProduct.do";
	}
}
