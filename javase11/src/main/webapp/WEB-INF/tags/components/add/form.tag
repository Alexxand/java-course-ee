<%@tag pageEncoding="UTF-8" body-content="scriptless" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set value="${pageContext.request.contextPath}" var="contextPath"/>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="i18n.create.form" var="find"/>

<fmt:message bundle="${find}" key="create.form.name" var="name"/>
<fmt:message bundle="${find}" key="create.form.caliber" var="caliber"/>
<fmt:message bundle="${find}" key="create.form.rate" var="rate"/>
<fmt:message bundle="${find}" key="create.form.image" var="image"/>
<fmt:message bundle="${find}" key="create.form.add" var="add"/>


<form class="form-horizontal col-lg-6" method="post" enctype="multipart/form-data">
    <div class="form-group">
        <label for="name" class="col-lg-5 control-label">${name}</label>
        <div class="col-lg-5">
            <input type="text" class="form-control" id="name">
        </div>
    </div>
    <div class="form-group">
        <label for="caliber" class="col-lg-5 control-label">${caliber}</label>
        <div class="col-lg-5">
            <input type="text" class="form-control" id="caliber">
        </div>
    </div>
    <div class="form-group">
        <label for="rate" class="col-lg-5 control-label">${rate}</label>
        <div class="col-lg-5">
            <input type="text" class="form-control" id="rate">
        </div>
    </div>
    <div class="form-group">
        <label for="image" class="col-lg-5 control-label">${image}</label>
        <div class="col-lg-5">
            <input type="file" accept="image/*" data-filename-placement="inside" class="form-control" id="image">
        </div>
    </div>
    <div class="form-group">
        <div class="col-lg-10">
            <button type="submit" class="btn btn-success pull-right">${add}</button>
        </div>
    </div>
</form>
