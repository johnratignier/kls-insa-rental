<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>KLS - Profil membre</title>

<script src="js/prefixfree.min.js">
</script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">

<style>
	.titre{
        color: dimgrey;
	}
    .reponse{
        font-weight: 600;
    }
</style>


</head>

<body>
	<%@ include file="/WEB-INF/header.jsp"%>

	<div class="container">
		<div class="row pt-4">
			<div class="col-md-9 ml-auto mr-auto">
				<h4 class="text-center">Profil membre</h4>
				<hr>
			</div>	
		</div>
		<div class="row">
			<div
				class="col-xs-9 col-sm-9 col-md-9 col-lg-9 ml-auto mr-auto">

				<div class="panel panel-info">
					<div class="panel-heading p-2 mt-2 mb-2">
                        <h3 class="panel-title d-inline">${requestScope.membreCurrent.getPrenom()} ${requestScope.membreCurrent.getNom()}
                        <c:if test="${requestScope.membreCurrent.getPseudo_artiste() != ''}">
                        <h5 class="d-inline"> aka </h5>
                        <h3 class="d-inline font-weight-bold text-primary">${requestScope.membreCurrent.getPseudo_artiste()}</h3>
                        </c:if>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4 col-lg-4 " align="center">
								<img alt="Photo de profil"
									src="${requestScope.membreCurrent.getPhoto_profil()}"
									class="rounded-circle img-fluid height-100">
								<c:if test="${sessionScope.membre.getDroits().getIntitule_droits_membre() eq 'ADMIN'}">
								<a href="/kls/listemembres?modifiermembre=${membreCurrent.getId_user() }" class="btn btn-warning col-md-10 ml-auto md-auto mt-4">Modifier ses infos</a>
								</c:if>
							</div>

							<div class=" col-md-8 col-lg-8">
								<table class="table">
									<tbody>
										<tr>
											<td class="titre">Adresse-Mail</td>
											<td class="reponse">${requestScope.membreCurrent.getAdresse_mail()}</td>
										</tr>
										<tr>
											<td class="titre">Telephone</td>
											<td class="reponse">${requestScope.membreCurrent.getNumero_tel()}</td>
										</tr>
                                        <tr>
											<td class="titre">Date de naissance</td>
											<td class="reponse">${requestScope.membreCurrent.getDate_naissance()}</td>
										</tr>
                                        <tr>
											<td class="titre">Departement</td>
											<td class="reponse">${requestScope.membreCurrent.getDepartement_annee()} ${requestScope.membreCurrent.getDepartement_etude()}</td>
										</tr>
										<tr>
											<td class="titre">Droits</td>
											<td class="reponse">${requestScope.membreCurrent.getDroits().getIntitule_droits_membre()}</td>
										</tr>
										<tr>
											<td class="titre">Statut</td>
											<td class="reponse">${requestScope.membreCurrent.getStatut().getIntitule_statut()}</td>
										</tr>
                                        <tr>
											<td class="titre">Mes Reservation</td>
											<td class="reponse"><a href="#">Voir ses réservations</a></td>
										</tr>
										 
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

</body>

</html>