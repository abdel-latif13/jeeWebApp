<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<div class="container">

    <h1>Edit Person</h1>

    <form:form method="POST" modelAttribute="person">

        <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div class="form-group">
            <label for="firstName">First Name:</label>
            <form:input placeholder="FirstName..." class="form-control" path="firstName" />
            <form:errors path="firstName" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <form:input placeholder="LastName..." class="form-control" path="lastName" />
            <form:errors path="lastName" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <label for="email">email:</label>
            <form:input placeholder="email..." class="form-control" path="email" />
            <form:errors path="email" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <label for="password">password:</label>
            <form:input type="password" placeholder="password..." class="form-control" path="password"/>
            <form:errors path="password" cssClass="alert alert-warning" element="div" />
        </div>

        <div class="">
            <label for="birthday">Birthday:</label>
            <form:input placeholder="dd/MM/yyyy..." class="form-control" path="birthday" />
            <form:errors path="birthday" cssClass="alert alert-warning" element="div" />

        </div>

        <div class="form-group">
            <label for="webSite">webSite:</label>
            <form:input placeholder="web Site..." class="form-control" path="webSite" />
            <form:errors path="webSite" cssClass="alert alert-warning" element="div" />
        </div>
        <div class="form-group">
            <label for="group">Group:</label>
            <form:input placeholder="Group..." class="form-control" path="group" value="${person.group.name}" disabled="true"/>
            <form:errors path="group" cssClass="alert alert-warning" element="div" />
        </div>

        <sec:authorize access="hasAuthority('ADMIN')">
            <div class="form-group">
                <button type="submit" class="btn btn-info">Submit</button>
            </div>
        </sec:authorize>

    </form:form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>