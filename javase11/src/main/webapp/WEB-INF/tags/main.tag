<%@ tag pageEncoding="UTF-8" body-content="scriptless" %>
<%@attribute name="page" type="pages.Pages" required="true" %>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="comp" tagdir="/WEB-INF/tags/components" %>



<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<html>
<head>
    <link rel="stylesheet" href="${contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min.css" />
    <%--<link rel="stylesheet" href="${contextPath}/webjars/bootstrap/3.3.7-1/css/bootstrap.min-jsf.css" />--%>
</head>
<body>

<comp:menu active="${page}" />

<jsp:doBody />

</body>
</html>