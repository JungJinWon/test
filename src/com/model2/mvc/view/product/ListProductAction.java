package com.model2.mvc.view.product;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Search searchVO = new Search();
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		searchVO.setPageSize(pageSize);
		if(searchVO == null)
			searchVO = (Search)request.getAttribute("searchVO");
		
		int page = 1;
		System.out.println("페이지수 : " + request.getParameter("currentPage"));
		if (request.getParameter("currentPage") != null) 
			page = Integer.parseInt(request.getParameter("currentPage"));

			searchVO.setPage(page);
			
			searchVO.setSearchCondition(request.getParameter("searchCondition"));
			searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
			
			ProductService service = new ProductServiceImpl();
			Map<String, Object> map = service.getProductList(searchVO);
			
			Page pageInfo =
					new Page(page, ((Integer)map.get("total")).intValue(), pageUnit, pageSize);
			System.out.println(pageInfo);
			
			request.setAttribute("list", map.get("list"));
			request.setAttribute("searchVO", searchVO);
			request.setAttribute("pageInfo", pageInfo);
			
		return "forward:/product/listProduct.jsp";
	}
}
