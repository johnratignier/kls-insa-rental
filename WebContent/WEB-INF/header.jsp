<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>
<head>

<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/jquery-3.2.1.min.js"></script>
<script src="bootstrap/js/popper.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

<style>
nav {
	z-index: 100;
	position: fixed;
}
</style>

</head>

<body>

	<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top" id="navbarToggleExternalContent">
		<ul class="nav navbar-nav mr-auto">
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<a class="navbar-brand" href="/kls/home"><img style="height: 30px;" src="/kls/img/home_logo.jpg" /></a>
			</div>
		</ul>
		<ul class="nav navbar-nav mr-auto">
			<c:if test="${sessionScope.membre==null}">
				<li class="nav-item active"><a class="nav-link" href="/kls/login">Se connecter</a></li>
			</c:if>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Réservations </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="/kls/nouvellereservation">Effectuer une réservation</a> <a class="dropdown-item" href="/kls/listereservations">Voir
						les réservations</a> <a class="dropdown-item" href="/kls/calendrierreservations">Calendrier</a>
				</div></li>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Materiel </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="/kls/materiel">Tout le materiel</a> <a class="dropdown-item" href="/kls/nouveaumateriel">Nouveau materiel</a>
					<!-- <a class="dropdown-item" href="#">Son</a> <a
						class="dropdown-item" href="#">Lumières</a> <a
						class="dropdown-item" href="#">Cablage</a>-->
				</div></li>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Utilisateurs </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="/kls/listeclients">Clients/Associations</a> <a class="dropdown-item" href="/kls/listemembres">Membres</a> <a
						class="dropdown-item" href="/kls/nouveauclient">Nouveau client</a>
				</div></li>
		</ul>
		<c:if test="${sessionScope.membre!=null}">
			<ul class="nav navbar-nav">
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> ${sessionScope.membre.getPrenom()} </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="/kls/profil?membreProfil=me">Profil</a> <a class="dropdown-item" href="/kls/logout">Deconnexion</a> <a
							class="dropdown-item" href="/kls/changementmotdepasse">Changer mot de passe</a>
					</div></li>
			</ul>
		</c:if>
	</nav>

	<nav class="navbar navbar-expand-lg navbar-light bg-light static-top" id="navbarToggleExternalContent">
		<ul class="nav navbar-nav mr-auto">
			<div class="collapse navbar-collapse" id="navbarNavDropdown">
				<a class="navbar-brand" href="/kls/home">K-Le-Son</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown"
					aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
		</ul>
		<ul class="nav navbar-nav mr-auto">
			<c:if test="${sessionScope.membre==null}">
				<li class="nav-item active"><a class="nav-link" href="/kls/login">Se connecter</a></li>
			</c:if>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Réservations </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="#">Effectuer une réservation</a> <a class="dropdown-item" href="#">Voir les réservations</a>
				</div></li>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Materiel </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="/kls/materiel">Tout le materiel</a> <a class="dropdown-item" href="/kls/nouveaumateriel">Nouveau materiel</a> <a
						class="dropdown-item" href="#">Son</a> <a class="dropdown-item" href="#">Lumières</a> <a class="dropdown-item" href="#">Cablage</a>
				</div></li>
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
				aria-haspopup="true" aria-expanded="false"> Utilisateurs </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
					<a class="dropdown-item" href="/kls/listeclients">Clients/Associations</a> <a class="dropdown-item" href="#">Membres</a>
				</div></li>
		</ul>
		<c:if test="${sessionScope.membre!=null}">
			<ul class="nav navbar-nav">
				<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false"> ${sessionScope.membre.getPrenom()} </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="/kls/profil?membreProfil=me">Profil</a> <a class="dropdown-item" href="/kls/logout">Deconnexion</a> <a
							class="dropdown-item" href="/kls/changementmotdepasse">Changer mot de passe</a>
					</div></li>
			</ul>
		</c:if>
	</nav>

</body>
</html>