<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import="com.model2.mvc.service.purchase.vo.*" %>
<%@ page import="com.model2.mvc.service.product.vo.*" %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>

<%
	PurchaseVO vo = (PurchaseVO)request.getAttribute("purchaseVO");
%>
<% 
	ProductVO pvo = (ProductVO)request.getAttribute("pvo");
%>
<%
	UserVO uvo = (UserVO)request.getAttribute("uvo");
%>--%>
<c:set var="vo" value="${purchaseVO}" scope="request" />
<c:set var="pvo" value="${pvo}" scope="request"/>
<c:set var="uvo" value="${uvo}" scope="request" />

<html>
<head>
<title>구매요청</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=${vo.tranNo}&tranCode=${vo.tranCode}" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${vo.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${vo.buyer.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
		<%--if(vo.getPaymentOption() == "1"){ --%>
		<c:if test="${vo.paymentOption == '1'}">
			현금구매
		</c:if>
		<%--}else{ --%>
		<c:if test="${vo.paymentOption == '2'}">
			신용구매
		</c:if>
		<%--} --%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${vo.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${vo.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${vo.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${vo.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${vo.divyDate}</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>