<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="login"  value="/user/login" />
<c:url var="logout" value="/user/logout" />
<c:url var="show"   value="/user/show" />

<div class="container">
    <h1>User</h1>

    <c:if test="${message != null}">
        <div class="alert alert-success" role="alert">
            <c:out value="${message}" />
        </div>
    </c:if>


    <p>
        email :
        <c:out value="${user.email}" default="no email"/> |
        <a href="${show}">Show</a> | <a href="${login}">Login</a> |
        <a href="${logout}">Logout</a>
    </p>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>