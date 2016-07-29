<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%-- <%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- 
	UserVO uvo=(UserVO)session.getAttribute("user");
--%>
<c:set var="uvo" value="${user}" scope="session" />
<c:set var="pageInfo" value="${pageInfo}" scope="request" />
<c:set var="searchVO" value="${searchVO}" scope="request" />
<c:set var="list" value="${list}" scope="request" />
<%--
	Page pageInfo = (Page)request.getAttribute("pageInfo");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	List<ProductVO> list = (List<ProductVO>)request.getAttribute("list");
	
--%>

<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetList(currentPage,searchCondition,searchKeyword){
	document.getElementById("currentPage").value = currentPage;
	document.getElementById("searchCondition").value = searchCondition;
	document.getElementById("searchKeyword").value = searchKeyword;
	document.detailForm.submit();
}

</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listProduct.do?menu=${param.menu}" method="post">

<input type="hidden" id="menu" name="menu" value=""/>

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
						<%--if(request.getParameter("menu").equals("manage")){ --%>
							<c:if test="${param.menu == 'manage'}">
							상품 관리
							</c:if>
						<%--}else{ --%>
							<c:if test="${param.menu != 'manage'}">
							상품 목록조회
							</c:if>
						<%--} --%>
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<%--
		if(searchVO.getSearchCondition() != null) {
	--%>
	<c:if test="${!empty searchVO.searchCondition }">
		<td align="right">
			<select id="searchCondition" name="searchCondition" class="ct_input_g" style="width:80px">
		<%--
				if(searchVO.getSearchCondition().equals("0")){
		--%>
			<c:if test="${searchVO.searchCondition == '0'}">		
				<option value="0" selected>상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</c:if>	
		<%--
				}else if(searchVO.getSearchCondition().equals("1")) {
		--%>
			<c:if test="${searchVO.searchCondition == '1'}">
				<option value="0">상품번호</option>
				<option value="1" selected>상품명</option>
				<option value="2">상품가격</option>
			</c:if>	
		<%--
				}else {
		--%>
			<c:if test="${searchVO.searchCondition == '2' }">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2" selected>상품가격</option>
		<%--
				}
		--%>		
			</c:if>		
			</select>
			<input id="searchKeyword" type="text" name="searchKeyword" value="${searchVO.searchKeyword}" 
						class="ct_input_g" style="width:200px; height:19px" />
		</td>
	</c:if>
		
		
	<%--
		}else{
	--%>
	
	<c:if test="${empty searchVO.searchCondition}">
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">상품번호</option>
				<option value="1">상품명</option>
				<option value="2">상품가격</option>
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
		</td>
	<%--
		}
	--%>
	</c:if>
		
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
				
						<a href="javascript:fncGetList('1');">검색
						</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td colspan="11" >전체  ${pageInfo.total} 건수, 현재 ${pageInfo.currentPage} 페이지 <br/></td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	
	<%-- 	
		for(int i=0; i<list.size(); i++) {
			ProductVO vo = (ProductVO)list.get(i);
	--%>
	<c:set var="i" value="0" />
	<c:forEach var="vo" items="${list}"  >
		<c:set var="i" value="${i+1}" />
	<tr class="ct_list_pop">
		<td align="center">${i}</td>
		<td></td>
				
		<td align="left">
			<%--if(request.getParameter("menu").equals("manage")){ --%>
			<c:choose>
				<c:when test="${param.menu == 'manage'}" >
					<a href="updateProductView.do?prodNo=${vo.prodNo}&menu=manage">${vo.prodName}</a>
				</c:when>
			<%--}else{ --%>
				<c:when test="${param.menu != 'manage' && vo.proTranCode != '0'}">
					${vo.prodName}
				</c:when>
				<c:otherwise>
					<a href="getProduct.do?prodNo=${vo.prodNo}&menu=search">${vo.prodName}</a>
				</c:otherwise>
			<%--} --%>
			</c:choose>
		</td>
		
		<td></td>
		<td align="left">${vo.price}</td>
		<td></td>
		<td align="left">${vo.regDate}</td>
		<td></td>
		<td align="left">
		<%--if( (uvo.getUserId().equals("admin")) && vo.getProTranCode().trim().equals("1") ) { --%>
		
			<c:if test="${param.menu == 'manage' && vo.proTranCode.trim() == '1'}" >
					구매완료&nbsp;&nbsp;&nbsp;
			
					<%-- if(request.getParameter("menu").equals("manage")){--%>
					<a href="updateTranCodeByProd.do?prodNo=${vo.prodNo}&menu=manage&tranCode=2">배송하기</a>
					<%--}--%>
			</c:if>
			<c:if test="${param.menu != 'manage' && vo.proTranCode.trim() == '1'}" >
			<%-- }else if( !(uvo.getUserId().equals("admin")) && vo.getProTranCode().trim().equals("1") ) {--%>
					재고 없음
			</c:if>
					
			<c:if test="${vo.proTranCode == '2'}" >
			<%--}else if(vo.getProTranCode().trim().equals("2")){--%>
					배송중
			</c:if>		
			
			<c:if test="${vo.proTranCode == '3'}" >
			<%--}else if(vo.getProTranCode().trim().equals("2")){--%>
					재고 없음
			</c:if>
					
			<c:if test="${(empty vo.proTranCode) || vo.proTranCode == '0'}" > 
			<%--}else if(vo.getProTranCode().trim().equals("0")){--%>
					판매중
			</c:if>
					
		 
		<%--} --%>
		</td>
	</tr>
	</c:forEach>	 
<%--} --%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
			<%--if(pageInfo.getCurrentPage() <= pageInfo.getPageUnit()) {--%>
				<%--}else if(request.getParameter("menu").equals("manage")){ --%>
				<%--}else{ --%>
			<%--} --%>
		<%--
			for(int i=pageInfo.getBeginPage();i<=pageInfo.getEndPage();i++){
		--%>
			<%--if(request.getParameter("menu").equals("manage")){ --%>
			<%--}else{ --%>
			<%--} --%>
		<%--}--%>
			<%--if(pageInfo.getEndPage() >= pageInfo.getTotalPage()) {--%>
			<%--}else if(request.getParameter("menu").equals("manage")){ --%>
			<%--}else{ --%>
			<%--} --%>
			
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>
