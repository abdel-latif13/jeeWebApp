<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div>
    <h2>Forgot Password</h2>
</div>

<div if="${error != null}">
    <p class="text-danger">[[${error}]]</p>
</div>
<div if="${message != null}">
    <p class="text-warning">[[${message}]]</p>
</div>

<form action="forgot_password" method="post" >
    <div class="border border-secondary rounded p-3">
        <div>
            <p>We will be sending a reset password link to your email.</p>
        </div>
        <div>
            <p>
                <input type="email" name="email" class="form-control" placeholder="Enter your e-mail" required autofocus/>
            </p>
            <p class="text-center">
                <input type="submit" value="Send" class="btn btn-primary" />
            </p>
        </div>
    </div>
</form>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>
