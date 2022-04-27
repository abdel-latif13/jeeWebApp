<%@ include file="/WEB-INF/jsp/header.jsp"%>

<c:url var="edit" value="/group/edit" />
<c:url var="findGroups" value="/group/find"/>
<c:url var="myGroup" value="/person/myGroup"/>




<div class="container">
    </br>
    <form action="${findGroups}" method="post">
        </br>
        <div class="container">
            <p>
                <span style="margin-left: 30px;"></span>
                <input name="name" size="20" />
                <input class="btn btn-info" type="submit" value="Find" />
            </p>
            <sec:csrfInput />
        </div>
    </form>
</div>

<div class="container">
    <h1>Groups </h1>
    <sec:authorize access="hasAuthority('ADMIN')">
        <p>
            <a class="btn btn-info" href="${edit}">Create new Group</a>
        </p>
    </sec:authorize>
    <table class="table table-hover">
        <thead>
        <tr>
            <th class="th-sm">Name
            </th>
            <th class="th-sm">Number of members
            </th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${groups}" var="group">
            <tr>
                <td><a href="${edit}?id=${group.id}">
                    <c:out value="${group.name}" />
                </a></td>
                <td><i>
                        <c:out value="${group.persons.size()}" /> <!-- hashmap size to add -->
                </i> person </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>