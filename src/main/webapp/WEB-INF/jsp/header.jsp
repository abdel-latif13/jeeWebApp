<!DOCTYPE html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:url var="bootstrap_css"
	value="/webjars/bootstrap/4.6.0-1/css/bootstrap.min.css" />
<c:url var="bootstrap_js"
	value="/webjars/bootstrap/4.6.0-1/js/bootstrap.min.js" />
<c:url var="jquery_js" value="/webjars/jquery/3.5.1/jquery.min.js" />
<c:url var="css" value="/style.css" />

<html>
	<head>
	<meta charset="UTF-8">
	<title>Spring boot application</title>
	<link rel="stylesheet" href="${css}">
	<link rel="stylesheet" href="${bootstrap_css}">
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" />
	<script src="${jquery_js}"></script>
	<script src="${bootstrap_js}"></script>
</head>
<body>


<c:url var="findPersons" value="/person/find"/>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<a class="navbar-brand" href="/">Home</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarNavDropdown">
		<ul class="navbar-nav  w-100">
			<li class="nav-item active">
				<a class="nav-link" href="/person/list">Persons <span class="sr-only">(current)</span></a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="/group/list">Groups</a>
			</li>
			<sec:authorize access="isAuthenticated()">
				<li class="nav-item dropdown ml-auto">
					<a class="nav-link dropdown-toggle" href="/person/edit" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						My account
					</a>

					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="/person/editMe">Edit</a>
						<a class="dropdown-item" href="/person/myGroup">My Group</a>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item badge-danger" href="/logout">Logout</a>
					</div>
				</li>
			</sec:authorize>
		</ul>
		<form class="form-inline my-2 my-lg-0">
			<sec:authorize access="!isAuthenticated()">
				<a class="btn btn-success" href="/login">login</a>
			</sec:authorize>
		</form>
	</div>
</nav>


