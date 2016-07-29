package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Search searchVO = new Search();
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		searchVO.setPageSize(pageSize);
		HttpSession session = request.getSession(); 
		User userVO = (User)session.getAttribute("user");
		
		if(searchVO == null)
			searchVO = (Search)request.getAttribute("searchVO");
		
		int page = 1;
		System.out.println("페이지수 : " + request.getParameter("page"));
		if (request.getParameter("page") != null) 
			page = Integer.parseInt(request.getParameter("page"));

			searchVO.setPage(page);
			
			searchVO.setSearchCondition(request.getParameter("searchCondition"));
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
			
			PurchaseService service = new PurchaseServiceImpl();
			Map<String, Object> map = service.getPurchaseList(searchVO, userVO.getUserId());
			
			Page pageInfo =
					new Page(page, ((Integer)map.get("total")).intValue(), pageUnit, pageSize);
			System.out.println(pageInfo);
			
			request.setAttribute("list", map.get("list"));
			request.setAttribute("searchVO", searchVO);
			request.setAttribute("pageInfo", pageInfo);
		

		return "forward:/purchase/listPurchase.jsp";
	}
}
