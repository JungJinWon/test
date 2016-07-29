package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdateTranCodeByProdAction extends Action {
	@Override
	public String execute(	HttpServletRequest request,
											HttpServletResponse response) throws Exception {
		String tranCode = request.getParameter("tranCode");
		System.out.println(tranCode);
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Purchase purchaseVO = null;
		
		PurchaseService service=new PurchaseServiceImpl();
		
		purchaseVO = service.findPurchase2(prodNo);
		purchaseVO.setTranCode(tranCode);
		service.updateTranCode(purchaseVO);
		
		request.setAttribute("pvo", purchaseVO);
		
		return "forward:/updateTranCode.do?tranCode=2&prodNo="+prodNo;
	}
}