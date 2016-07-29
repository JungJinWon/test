<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	
	
	<c:choose>
		<c:when test="${pageInfo.currentPage <= pageInfo.pageUnit}" >
			이전&nbsp;◀
		</c:when>
		<c:when test="${param.menu == 'manage'}" >
			<a href="javascript:fncGetList('${ pageInfo.currentPage-1}','${searchVO.searchCondition}','${searchVO.searchKeyword}')">이전&nbsp;◀</a>
		</c:when>
		<c:when test="${param.menu == 'search'}" >
			<a href="javascript:fncGetList('${ pageInfo.currentPage-1}','${searchVO.searchCondition}','${searchVO.searchKeyword}')">이전&nbsp;◀</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:fncGetList('${ pageInfo.currentPage-1}')">이전&nbsp;◀</a>
		</c:otherwise>
	</c:choose>
	
	<c:forEach var="i"  begin="${pageInfo.beginPage}" end="${pageInfo.endPage}" step="1">
		<c:if test="${param.menu == 'manage'}" >
		<a href="javascript:fncGetList('${ i }','${searchVO.searchCondition}','${searchVO.searchKeyword}');">${ i }</a>
		</c:if>
		<c:if test="${param.menu  == 'search'}">
		<a href="javascript:fncGetList('${ i }','${searchVO.searchCondition}','${searchVO.searchKeyword}');">${ i }</a>
		</c:if>
		<c:if test="${empty param.menu}">
		<a href="javascript:fncGetList('${ i }');">${ i }</a>
		</c:if>
	</c:forEach>
	
	<c:choose>
		<c:when test="${ pageInfo.endPage >= pageInfo.totalPage }">
			▶&nbsp;다음
		</c:when>
		<c:when test="${param.menu == 'manage'}">
			<a href="javascript:fncGetList('${pageInfo.endPage+1}','${searchVO.searchCondition}','${searchVO.searchKeyword}')">▶&nbsp;다음</a>
		</c:when>
		<c:when test="${param.menu  == 'search'}">
			<a href="javascript:fncGetList('${pageInfo.endPage+1}','${searchVO.searchCondition}','${searchVO.searchKeyword}')">▶&nbsp;다음</a>
		</c:when>
		<c:otherwise>
			<a href="javascript:fncGetList('${pageInfo.endPage+1}')">▶&nbsp;다음</a>
		</c:otherwise>
	</c:choose>