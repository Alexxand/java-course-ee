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

<c:url value="display" var="displayPath"/>

<nav aria-label="Page navigation" class="text-center">
    <ul class="pagination">
        <li>
            <a href="${displayPath}?page=1" aria-label="Previous">
                <span aria-hidden="true">&laquo;</span>
            </a>
        </li>
        <c:forEach var="page" begin="${requestScope.firstInPaginator}" end="${requestScope.firstInPaginator + 4}">
            <li><a href="${displayPath}?page=${page}" class="disabled">${page}</a></li>
        </c:forEach>
        <li>
            <a href="${displayPath}?page=${requestScope.last}" aria-label="Next">
                <span aria-hidden="true">&raquo;</span>
            </a>
        </li>
    </ul>
</nav>
<div class="container col-lg-offset-3 col-lg-6">
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
        <c:forEach var="gun" items="${requestScope.gunList}">
            <tr>
                <td>${gun.name}</td>
                <td>${gun.caliber}</td>
                <td>${gun.rate}</td>
                <td><img src="${contextPath}/image?id=${gun.id}" class="img-responsive"></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>