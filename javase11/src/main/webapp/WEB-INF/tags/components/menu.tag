<%@tag pageEncoding="UTF-8" body-content="scriptless" %>
<%@attribute name="active" required="true" type="pages.Pages" rtexprvalue="true" %>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.menu" var="menu"/>

<fmt:message bundle="${menu}" key="menu.add" var="add"/>
<fmt:message bundle="${menu}" key="menu.display" var="display"/>
<fmt:message bundle="${menu}" key="menu.delete" var="delete"/>

<c:set value="${pageContext.request.contextPath}" var="contextPath" />
<c:set value="${fn:toLowerCase(active)}" var="curRelativePath" />

<link rel="stylesheet" href="${contextPath}/webjars/flag-icon-css/2.4.0/css/flag-icon.min.css"/>

<nav class="navbar navbar-default">
    <div class="container">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
            <li role="presentation" <c:if test="${active == 'ADD'}">class="active"</c:if>><a href="add">${add}</a></li>
            <li role="presentation" <c:if test="${active == 'DISPLAY'}">class="active"</c:if>><a href="display">${display}</a></li>
            <li role="presentation" <c:if test="${active == 'DELETE'}">class="active"</c:if>><a href="delete">${delete}</a></li>
            </ul>
            <form class="navbar-form navbar-right" action="${contextPath}/${curRelativePath}" method="get">
                <input type="hidden" name="locale" value="ru" />
                <button type="submit" class="btn flag-icon flag-icon-ru"></button>
            </form>
            <form class="navbar-form navbar-right" action="${contextPath}/${curRelativePath}" method="get">
                <input type="hidden" name="locale" value="en" />
                <button type="submit" class="btn flag-icon flag-icon-gb"></button>
            </form>
        </div>
    </div>
</nav>