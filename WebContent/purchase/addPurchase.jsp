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
<title>���ſ�û</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=${vo.tranNo}&tranCode=${vo.tranCode}" method="post">

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>
	<tr>
		<td>��ǰ��ȣ</td>
		<td>${vo.purchaseProd.prodNo}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ھ��̵�</td>
		<td>${vo.buyer.userId}</td>
		<td></td>
	</tr>
	<tr>
		<td>���Ź��</td>
		<td>
		<%--if(vo.getPaymentOption() == "1"){ --%>
		<c:if test="${vo.paymentOption == '1'}">
			���ݱ���
		</c:if>
		<%--}else{ --%>
		<c:if test="${vo.paymentOption == '2'}">
			�ſ뱸��
		</c:if>
		<%--} --%>
		</td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td>${vo.receiverName}</td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td>${vo.receiverPhone}</td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td>${vo.divyAddr}</td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td>${vo.divyRequest}</td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td>${vo.divyDate}</td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>