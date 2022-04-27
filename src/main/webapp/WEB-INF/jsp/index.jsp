<%@ include file="/WEB-INF/jsp/header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

</br></br></br>
<div class = "container">
    <h1 style="text-align: center"> Bienvenue sur notre annuaire de personnes </h1>
    </br>
    <a id ="index1" type="button" class="btn btn-outline-dark btn-lg btn-block" href="/person/list"> Les Personnes </a>
    </br>
    <a id ="index2" type="button" class="btn btn-outline-dark btn-lg btn-block" href="/group/list"> Les Groups </a>
    </br>
    <a id ="index3" type="button" class="btn btn-outline-dark btn-lg btn-block" href="/person/myGroup"> Mon Group </a>
    </br>
    <a id ="index4" type="button" class="btn btn-outline-dark btn-lg btn-block" href="/person/editMe"> Mon Profile </a>
</div>

<%@ include file="/WEB-INF/jsp/footer.jsp"%>