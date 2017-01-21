<%@tag pageEncoding="UTF-8" body-content="scriptless" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.delete.find" var="find"/>

<fmt:message bundle="${find}" key="delete.find.id.header" var="idHeader"/>
<fmt:message bundle="${find}" key="delete.find.id.field.name" var="idFieldName"/>
<fmt:message bundle="${find}" key="delete.find.id.submit" var="idSubmit"/>
<fmt:message bundle="${find}" key="delete.find.name.header" var="nameHeader"/>
<fmt:message bundle="${find}" key="delete.find.name.field.name" var="nameFieldName"/>
<fmt:message bundle="${find}" key="delete.find.name.submit" var="nameSubmit"/>

<div class="container col-lg-5">
    <div class="table-bordered">
        <div class="modal-header text-right">
            ${idHeader}
        </div>
        <form class="form-inline text-right" method="post">
            <div class="form-group">
                <label for="id">${idFieldName}</label>
                <input type="text" class="form-control" id="id">
            </div>
            <button type="submit" class="btn btn-success">${idSubmit}</button>
        </form>
    </div>
    <br />
    <div class="table-bordered">
        <div class="modal-header text-right">
            ${nameHeader}
        </div>
        <form class="form-inline text-right" method="post">
            <div class="form-group">
                <label for="name">${nameFieldName}</label>
                <input type="text" class="form-control" id="name">
            </div>
            <button type="submit" class="btn btn-success">${nameSubmit}</button>
        </form>
    </div>
</div>