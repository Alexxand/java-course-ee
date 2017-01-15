<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set value="${sessionScope.locale}" var="locale" />
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="i18n.forms" var="forms" />

<jsp:useBean id="user" type="model.User" beanName="model.User" scope="request" />

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>
<body>

<fmt:message bundle="${forms}" key="forms.message" var="message"/>
<fmt:message bundle="${forms}" key="forms.logout.button" var="logOut"/>
<fmt:message bundle="${forms}" key="forms.select.lang.en" var="en"/>
<fmt:message bundle="${forms}" key="forms.select.lang.ru" var="ru"/>

<form action="." method="post">
    <select size="1" name="locale" onchange="index = this.selectedIndex; if (this.options[index].text != '${locale}') this.form.submit()">
        <option value="en" <c:if test="${locale == 'en'}">selected</c:if> >${en}</option>
        <option value="ru" <c:if test="${locale == 'ru'}">selected</c:if> >${ru}</option>
    </select>
</form>
<h2>${message}, ${user.name} ${user.lastName}!</h2>
<form action="." method="post">
    <input type="hidden" name="logOut">
    <input type="submit" value="${logOut}" />
</form>
</body>
</html>
