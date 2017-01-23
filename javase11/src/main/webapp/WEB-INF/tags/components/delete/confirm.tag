<%@tag pageEncoding="UTF-8" body-content="scriptless" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.add.form" var="find"/>
<fmt:setBundle basename="i18n.delete.confirm" var="confirm"/>

<fmt:message bundle="${find}" key="add.form.name" var="name"/>
<fmt:message bundle="${find}" key="add.form.caliber" var="caliber"/>
<fmt:message bundle="${find}" key="add.form.rate" var="rate"/>
<fmt:message bundle="${find}" key="add.form.image" var="image"/>

<fmt:message bundle="${confirm}" key="delete.confirm.alert" var="alert"/>
<fmt:message bundle="${confirm}" key="delete.confirm.cancel" var="cancel"/>
<fmt:message bundle="${confirm}" key="delete.confirm.confirm" var="confirm"/>


<div class="container col-lg-offset-3 col-lg-6">
    <p class="text-center">${alert}</p>
    <form action="delete" method="get">

    <table class="table table-bordered table-hover">
        <thead>
        <tr>
            <td><b>${name}</b></td>
            <td><b>${caliber}</b></td>
            <td><b>${rate}</b></td>
            <td><b>${image}</b></td>
        </tr>
        </thead>
        <tbody>

        <jsp:useBean id="gun" type="model.Gun" beanName="model.Gun" scope="request"/>
        <tr>
                <td>${gun.name}</td>
                <td>${gun.caliber}</td>
                <td>${gun.rate}</td>
                <td><img src="${contextPath}/image?id=${gun.id}" class="img-responsive"></td>
        </tr>
        </tbody>
    </table>
        <input type="hidden" name="id" value="${gun.id}">
        <input type="hidden" name="fromPage" value="id_commit">
        <div class="col-lg-offset-4 col-lg-2">
            <button type="submit" name="action" value="confirm" class="btn btn-success pull-right">${confirm}</button>
        </div>
        <div class="col-lg-2">
            <button type="submit" name="action" value="cancel" class="btn btn-success pull-right">${cancel}</button>
        </div>
    </form>
</div>