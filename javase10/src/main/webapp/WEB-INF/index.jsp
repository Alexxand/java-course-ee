<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set value="${sessionScope.locale}" var="locale" />
<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="i18n.forms" var="forms"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
</head>
<body>

<fmt:message bundle="${forms}" key="forms.enter.name" var="enterName"/>
<fmt:message bundle="${forms}" key="forms.enter.last.name" var="enterLastName"/>
<fmt:message bundle="${forms}" key="forms.login.button" var="logIn"/>
<fmt:message bundle="${forms}" key="forms.select.lang.en" var="en"/>
<fmt:message bundle="${forms}" key="forms.select.lang.ru" var="ru"/>

<form action="." method="post">
    <select size="1" name="locale" onchange="index = this.selectedIndex; if (this.options[index].text != '${locale}') this.form.submit()">
        <option value="en" <c:if test="${locale == 'en'}">selected</c:if> >${en}</option>
        <option value="ru" <c:if test="${locale == 'ru'}">selected</c:if> >${ru}</option>
    </select>
</form>
<form action="home" method="get">
    <label>${enterName} <input type="text" name="name"></label><br/>
    <label>${enterLastName} <input type="text" name="lastName"></label><br/>
    <input type="submit" value="${logIn}"/>
</form>
<br/>
</body>
</html>

