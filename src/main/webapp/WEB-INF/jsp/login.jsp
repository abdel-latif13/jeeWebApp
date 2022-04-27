<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/WEB-INF/jsp/header.jsp"%>

<div  class="d-flex justify-content-center">
    <form action="login" method="post">
        </br></br></br></br>
        <h2 style="text-align: center">Sign In</h2>
        <h5 style="border-block-color: red">${SPRING_SECURITY_LAST_EXCEPTION.message}</h5>
        <!-- Email input -->
        <div class="form-outline mb-4">
            <label class="form-label" for="username"><b>E-mail</b></label>
            <input id="username" type="text" class="form-control" name="username" required/>
        </div>
        <!--password input -->
        <div class="form-outline mb-4">
            <label class="form-label" for="password"><b>Password</b></label>
            <input class="form-control" id="password" type="password" name="password" required/>
        </div>
        <div class="row mb-4">
            <div class="col d-flex justify-content-center">
                <!-- Checkbox -->
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked />
                    <label class="form-check-label" for="form2Example31"> Remember me </label>
                </div>
            </div>
            <div class="col">
                <!-- Simple link -->
                <a href="/forgot_password">Forgot password ?</a>
            </div>
        </div>
        <button class="btn btn-success  btn-block mb-4" type="submit">Sign in </button>
    </form>
</div>
<%@ include file="/WEB-INF/jsp/footer.jsp"%>