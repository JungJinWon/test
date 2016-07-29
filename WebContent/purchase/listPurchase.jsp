<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.purchase.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>

<%
	UserVO userVO = (UserVO)session.getAttribute("user");
%>

<%
	HashMap<String,Object> map=(HashMap<String,Object>)request.getAttribute("map");
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	
	int total=0;
	ArrayList<PurchaseVO> list=null;
	if(map != null){
		total=((Integer)map.get("count")).intValue();
		list=(ArrayList<PurchaseVO>)map.get("list");
	}
	
	int currentPage=searchVO.getPage();
	
	int totalPage=0;
	if(total > 0) {
		totalPage= total / searchVO.getPageUnit() ;
		if(total%searchVO.getPageUnit() >0)
			totalPage += 1;
	}
--%>
<c:set var="userVO" value="${user}" scope="session" />
<c:set var="searchVO" value="${searchVO}" scope="request" /> 
<c:set var="pageInfo" value="${pageInfo}" scope="request" />
<c:set var="list" value="${list}" scope="request" />
<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width: 98%; margin-left: 10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">구매 목록조회</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"	width="12" height="37"></td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td colspan="11">전체  ${pageInfo.total} 건수, 현재 ${pageInfo.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">상품명</td>
		<td class="ct_line02"></td>
		<!-- td class="ct_list_b" width="150">받으실분</td-->
		<!--td class="ct_line02"></td-->
		<td class="ct_list_b">주소</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송현황</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">요구사항</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">배송희망일</td>
		<td class="ct_line02"></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<%-- 	
		int no=list.size();
		for(int i=0; i<list.size(); i++) {
			PurchaseVO vo = (PurchaseVO)list.get(i);
	--%>
	<c:set var="i" value="0" />
	<c:forEach var="vo" items="${list}"  >
		<c:set var="i" value="${i+1}" />
	<tr class="ct_list_pop">
		<td align="center">
		<c:choose>
			<c:when test="${vo.tranCode.trim() == '2'}">
				${i}
			</c:when>
			<c:otherwise>
				<a href="/getPurchase.do?tranNo=${vo.tranNo}">${i}</a>
			</c:otherwise>
		</c:choose>
		</td>
		<td></td>
		<td align="center">
			<a href="/getProduct.do?prodNo=${vo.purchaseProd.prodNo}">${vo.purchaseProd.prodName}</a>
		</td>
		<td></td>
		<!--td align="center">${vo.receiverName}</td-->
		<!--td></td-->
		<td align="center">${vo.divyAddr}</td>
		<td></td>
		<td align="center">
					현재
			<%--if(vo.getTranCode().trim().equals("1")){ --%>
			<c:if test="${vo.tranCode.trim() == '1'}">
					구매완료
			</c:if>
			<%--}else if(vo.getTranCode().trim().equals("2")) { --%>
			<c:if test="${vo.tranCode.trim() == '2'}">		
					배송중
			</c:if>
			
			<c:if test="${vo.tranCode.trim() == '3'}">		
					배송완료
			</c:if>
			
			<%--} --%>
					상태 입니다.
			<c:if test="${vo.tranCode.trim() == '2'}">		
					&nbsp;&nbsp;<a href="updateTranCodeByProd.do?prodNo=${vo.purchaseProd.prodNo}&menu=manage&tranCode=3"> 
					배송완료</a>
			</c:if>
			</td>
		<td></td>
		<td align="left">
			${vo.divyRequest}
		</td>
		<td></td>
		<td align="center">
			${vo.divyDate}
		</td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	</c:forEach>	
	<%-- } --%>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 10px;">
	<tr>
		<td align="center">
		<c:choose>
			<c:when test="${pageInfo.currentPage <= pageInfo.pageUnit}" >
			이전&nbsp;◀
			</c:when>
			<c:otherwise>
				<a href="/listPurchase.do?page=${pageInfo.currentPage-1}&menu=search">이전&nbsp;&nbsp;◀</a>
			</c:otherwise>
		</c:choose>		
		<%--
			for(int i=1;i<=totalPage;i++){
		--%>
		<c:forEach var="i" begin="${pageInfo.beginPage}" end="${pageInfo.endPage}" step="1" >	
			<a href="/listPurchase.do?page=${i}&menu=search">${i}</a>
		</c:forEach>
		<%--
			}
		--%>
		<c:choose>
			<c:when test="${pageInfo.endPage >= pageInfo.totalPage}" >	
			
			▶&nbsp;다음
			</c:when>
			<c:otherwise>	
				<a href="/listPurchase.do?page=${pageInfo.endPage+1}&menu=search">▶&nbsp;다음</a>
			</c:otherwise>
		</c:choose>
		</td>
	</tr>
</table>

<!--  페이지 Navigator 끝 -->
</form>

</div>

</body>
</html>