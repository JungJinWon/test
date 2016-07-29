package com.model2.mvc.view.user;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;

public class ListUserAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Search searchVO = new Search();
		
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		searchVO.setPageSize(pageSize);
		
		int page = 1;
		System.out.println("페이지수 : " + request.getParameter("currentPage"));
		if (request.getParameter("currentPage") != null) {
			page=Integer.parseInt(request.getParameter("currentPage"));
		}

		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

		UserService service = new UserServiceImpl();
		Map<String, Object> map = service.getUserList(searchVO);
		
		Page pageInfo =
				new Page(page, ((Integer)map.get("total")).intValue(), pageUnit, pageSize);
		System.out.println(pageInfo);
		

		request.setAttribute("list", map.get("list"));
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("pageInfo", pageInfo);

		return "forward:/user/listUser.jsp";
	}
}