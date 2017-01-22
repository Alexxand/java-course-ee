<%@tag pageEncoding="UTF-8" body-content="scriptless" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.add.form" var="find"/>

<fmt:message bundle="${find}" key="add.form.name" var="name"/>
<fmt:message bundle="${find}" key="add.form.caliber" var="caliber"/>
<fmt:message bundle="${find}" key="add.form.rate" var="rate"/>
<fmt:message bundle="${find}" key="add.form.image" var="image"/>
<fmt:message bundle="${find}" key="add.form.add" var="add"/>
<fmt:message bundle="${find}" key="add.form.success.alert" var="successAlert"/>
<fmt:message bundle="${find}" key="add.form.error.alert" var="errorAlert"/>

<c:if test="${!requestScope.isNew}">
    <c:if test="${requestScope.success}">
        <div class="alert alert-success" role="alert">${successAlert}</div>
    </c:if>
    <c:if test="${!requestScope.success}">
        <div class="alert alert-danger" role="alert">${errorAlert}</div>
    </c:if>
</c:if>

<form class="form-horizontal col-lg-6" action="add" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="name" class="col-lg-5 control-label">${name}</label>
        <div class="col-lg-5">
            <input type="text" class="form-control" id="name" name="name">
        </div>
    </div>
    <div class="form-group">
        <label for="caliber" class="col-lg-5 control-label">${caliber}</label>
        <div class="col-lg-5">
            <input type="text" class="form-control" id="caliber" name="caliber">
        </div>
    </div>
    <div class="form-group">
        <label for="rate" class="col-lg-5 control-label">${rate}</label>
        <div class="col-lg-5">
            <input type="text" class="form-control" id="rate" name="rate">
        </div>
    </div>
    <div class="form-group">
        <label for="image" class="col-lg-5 control-label">${image}</label>
        <div class="col-lg-5">
            <input type="file" accept="image/*" data-filename-placement="inside" class="form-control" id="image"
                   name="image">
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-10">
            <button type="submit" class="btn btn-success pull-right">${add}</button>
        </div>
    </div>
</form>
