<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="container">

    <h1>Edit Group</h1>

    <form:form method="POST" modelAttribute="group">

    <form:errors path="*" cssClass="alert alert-danger" element="div" />

        <div sec:authorize>

        </div>
    <div class="form-group">
        <label for="name">Group Name:</label>
        <form:input id = "nameIn" placeholder="Group Name..." class="form-control" path="name"/>
        <form:errors path="name" cssClass="alert alert-warning" element="div" />
    </div>
    <sec:authorize access="hasAuthority('ADMIN')">
        <div class="form-group">
             <button type="submit" class="btn btn-info">Update Group</button>
        </div>
    </sec:authorize>

    <div class="form-group">
        <c:if test="${PersonsInTheGroup.size()>0}">
            <label>Persons already in the group :</label>
        </c:if>
        <c:if test="${PersonsInTheGroup.size()==0}">
            <h4>Ce Groupe est vide </h4>
        </c:if>
            <div class="table-wrapper-scroll-y my-custom-scrollbar">
                <table class="table table-bordered table-striped mb-0">
                    <c:forEach items="${PersonsInTheGroup}" var="person">
                        <tr><td><a href="/person/edit?id=${person.key}"><c:out value="${person.value}"/></a></td>
                            <sec:authorize access="hasAuthority('ADMIN')">
                            <td><a type="submit" class="btn btn-danger" href=" /group/removePersonFromGroup?id=${group.id}&pid=${person.key}">remove</a></td></tr>
                            </sec:authorize>
                    </c:forEach>
                </table>
            </div>
    </div>

        <sec:authorize access="hasAuthority('ADMIN')">
            <c:if test="${PersonsInTheGroup.size()>0}">
                <div class="form-group">
                    <button type="submit" class="btn btn-info">Submit Remove </button>
                </div>
            </c:if>
        </sec:authorize>

        <sec:authorize access="hasAuthority('ADMIN')">
            <div class="form-group">
               <label>Add Persons to the group :</label>
                <div class="table-wrapper-scroll-y my-custom-scrollbar">
                    <table class="table table-bordered table-striped mb-0">
                        <c:forEach items="${PersonsWithoutGroup}" var="person">
                            <tr><td><c:out value="${person.value}"/> </td>
                            <td><a type="button" class="btn btn-success" id="addPers" href = "/group/addPersonToGroup?id=${group.id}&pid=${person.key}">Add</a></td></tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
            <c:if test="${PersonsWithoutGroup.size()>0}">
                <div class="form-group">
                    <button type="submit" class="btn btn-info">Submit Add</button>
                </div>
            </c:if>
        </sec:authorize>
    </form:form>
</div>


<%@ include file="/WEB-INF/jsp/footer.jsp"%>