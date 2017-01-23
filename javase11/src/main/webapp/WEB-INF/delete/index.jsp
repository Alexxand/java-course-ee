<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="mytags" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components/delete" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<mytags:main page="DELETE">
    <c:set value="${requestScope.result}" var="result" />
    <c:choose>
        <c:when test="${result == 'FIND' || result == 'SUCCESS' || result == 'FAIL'}">
            <comp:find />
        </c:when>
        <c:when test="${result == 'CONFIRM'}">
            <comp:confirm />
        </c:when>
    </c:choose>
</mytags:main>