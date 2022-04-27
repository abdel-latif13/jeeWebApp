<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="edit" value="/person/edit" />
<c:url var="findPersons" value="/person/find"/>
<c:url var="editGroup" value="/group/edit" />

</br>
<form action="${findPersons}" method="post">
    </br>
    <div class="container">
        <p>
            <span style="margin-left: 30px;"></span>
            <input class="input" name="name" size="20" />
            <input class="btn btn-info" type="submit" value="Find" />
        </p>
    <sec:csrfInput />
    </div>
</form>
<h1>Persons </h1>

    <div class="container">
        <sec:authorize access="hasAuthority('ADMIN')">
            <p>
                <a class="btn btn-info" href="${edit}">Create new Person</a>
            </p>
        </sec:authorize>
    <table class="table table-hover">
        <thead>
        <tr>
            <th class="th-sm">FirstName
            </th>
            <th class="th-sm">LastName
            </th>
            <th class="th-sm">WebSite
            </th>
            <th class="th-sm">Group
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${persons}" var="person">
            <tr>
                <td><a href="${edit}?id=${person.id}"><c:out value="${person.firstName}" /></a></td>
                <td><c:out value="${person.lastName}" /></td>
                <td><c:out value="${person.webSite}" /></td>
                <td><c:if test="${person.group == null}">
                    <c:out value="PAS DE GROUPE"/>
                    </c:if>
                    <c:if test="${person.group!= null}">
                        <a href="${editGroup}?id=${person.group.id}">
                            <c:out value="${person.group.name}" />
                        </a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>


    </div>



<%@ include file="/WEB-INF/jsp/footer.jsp"%>