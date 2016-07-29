<%@ page contentType="text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.user.vo.*" %>
<%@ page import="com.model2.mvc.common.*" %>--%>

<%--
	SearchVO searchVO=(SearchVO)request.getAttribute("searchVO");
	Page pageInfo = (Page)request.getAttribute("pageInfo");
	List<UserVO> list = (List<UserVO>)request.getAttribute("list");

--%>
<c:set var="searchVO" value="${searchVO}" scope="request" />
<c:set var="pageInfo" value="${pageInfo}" scope="request" />
<c:set var="list" value="${list}" scope="request" />

<html>
<head>
<title>ȸ�� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

function fncGetList(currentPage){
	document.getElementById("currentPage").value = currentPage;
	document.detailForm.submit();
}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">

<form name="detailForm" action="/listUser.do" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37">
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">ȸ�� �����ȸ</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37">
		</td>
	</tr>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<%--
		if(searchVO.getSearchCondition() != null) {
	--%>
	<c:choose>
	<c:when test="${!empty searchVO.searchCondition}" >
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
		<%--
				if(searchVO.getSearchCondition().equals("0")){
		--%>
		<c:choose>
		<c:when test="${searchVO.searchCondition == '0'}" >
				<option value="0" selected>ȸ��ID</option>
				<option value="1">ȸ����</option>
		</c:when>
		<%--
				}else {
		--%>
		<c:otherwise>
				<option value="0">ȸ��ID</option>
				<option value="1" selected>ȸ����</option>
		<%--
				}
		--%>
		</c:otherwise>
		</c:choose>
			</select>
			<input 	type="text" name="searchKeyword"  value="${searchVO.searchKeyword}" 
							class="ct_input_g" style="width:200px; height:19px" >
		</td>
	</c:when>
	<c:otherwise>
	<%--
		}else{
	--%>
		<td align="right">
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0">ȸ��ID</option>
				<option value="1">ȸ����</option>
			</select>
			<input type="text" name="searchKeyword"  class="ct_input_g" style="width:200px; height:19px" >
		</td>
	</c:otherwise>
	<%--
		}
	--%>
	</c:choose>
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetList('1');">�˻�</a>
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
		<td colspan="11" >��ü  ${pageInfo.total} �Ǽ�, ���� ${pageInfo.currentPage} ������</br></td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ��ID</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="150">ȸ����</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b">�̸���</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%-- 	
		for(int i=0; i<list.size(); i++) {
			UserVO vo = (UserVO)list.get(i);
	--%>
	<c:set var="i" value="0" />
	<c:forEach var="vo" items="${list}" >
		<c:set var="i" value="${i+1}" />
	<tr class="ct_list_pop">
		<td align="center">${ i }</td>
		<td></td>
		<td align="left">
			<a href="/getUser.do?userId=${vo.userId}">${vo.userId}</a>
		</td>
		<td></td>
		<td align="left">${vo.userName}</td>
		<td></td>
		<td align="left">${vo.email}
		</td>		
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>
	<%-- } --%>
	</c:forEach>
</table>

<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<%--if(pageInfo.getCurrentPage() <= pageInfo.getPageUnit()) {--%>
		<%--}else{ --%>
		<%--} --%>
		<%--
			for(int i=pageInfo.getBeginPage();i<=pageInfo.getEndPage();i++){
		--%>
		<%--
			}
		--%>
		<%--if(pageInfo.getEndPage() >= pageInfo.getTotalPage()) {--%>
		<%--}else{ --%>
		<%--} --%>
			<input type="hidden" id="currentPage" name="currentPage" value=""/>
			
			<jsp:include page="../common/pageNavigator.jsp"/>
    	</td>
	</tr>
</table>
<!--  ������ Navigator �� -->
</form>
</div>

</body>
</html>