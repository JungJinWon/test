package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class UpdatePurchaseAction extends Action {
	@Override
	public String execute(	HttpServletRequest request,
											HttpServletResponse response) throws Exception {
		
		int tranNo=Integer.parseInt(request.getParameter("tranNo"));
		System.out.println(tranNo);
		
		Purchase purchaseVO = new Purchase();
		Product productVO = new Product();
		User userVO = new User();
		userVO.setUserId(request.getParameter("buyerId"));
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setBuyer(userVO);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("divyDate"));
		purchaseVO.setOrderDate(purchaseVO.getOrderDate());
		purchaseVO.setTranCode(purchaseVO.getTranCode());
		
		PurchaseService service=new PurchaseServiceImpl();
		service.updatePurchase(purchaseVO);
		System.out.println(purchaseVO);
		
		HttpSession session=request.getSession();
		
		session.setAttribute("vo", purchaseVO);
		
		return "redirect:/getPurchase.do?tranNo="+tranNo;
	}
}
