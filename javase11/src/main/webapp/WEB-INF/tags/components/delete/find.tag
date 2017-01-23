<%@tag pageEncoding="UTF-8" body-content="scriptless" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.delete.find" var="find"/>

<fmt:message bundle="${find}" key="delete.find.id.header" var="idHeader"/>
<fmt:message bundle="${find}" key="delete.find.id.field.name" var="idFieldName"/>
<fmt:message bundle="${find}" key="delete.find.id.submit" var="idSubmit"/>
<fmt:message bundle="${find}" key="delete.find.alert.fail" var="fail"/>
<fmt:message bundle="${find}" key="delete.find.alert.success" var="success"/>

<c:if test="${requestScope.result == 'SUCCESS'}">
    <div class="alert alert-success" role="alert">${success}</div>
</c:if>
<c:if test="${requestScope.result == 'FAIL'}">
    <div class="alert alert-danger" role="alert">${fail}</div>
</c:if>

<div class="container col-lg-5">
    <div class="table-bordered">
        <div class="modal-header text-right">
            ${idHeader}
        </div>
        <form class="form-inline text-right" method="get">
            <div class="form-group">
                <label for="id">${idFieldName}</label>
                <input type="text" class="form-control" id="id" name="id">
            </div>
            <button type="submit" class="btn btn-success">${idSubmit}</button>
        </form>
    </div>
</div>