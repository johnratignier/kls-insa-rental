<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>KLS - Profil client</title>

<script src="js/prefixfree.min.js">
</script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">

<style>
.titre {
	color: dimgrey;
}

.reponse {
	font-weight: 600;
}
</style>


</head>

<body>
	<%@ include file="/WEB-INF/header.jsp"%>

	<div class="container">
		<div class="row pt-4">
			<div class="col-md-9 ml-auto mr-auto">
				<h4 class="text-center">Profil client</h4>
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 ml-auto mr-auto">

				<div class="panel panel-info">
					<div class="panel-heading p-2 mt-2 mb-2 text-center">
						<h3 class="panel-title d-inline">${requestScope.client.getNom_client()}</h3>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-md-4 col-lg-4 " align="center">
								<img alt="Photo de profil" src="${requestScope.client.getPhoto_client()}" class="rounded-circle img-fluid height-100">
								<c:if test="${sessionScope.membre.getDroits().getIntitule_droits_membre() eq 'ADMIN'}">
									<a href="/kls/listeclients?modifierclient=${requestScope.client.getId_client()}" class="btn btn-warning col-md-10 ml-auto md-auto mt-4">Modifier ses infos</a>
								</c:if>
							</div>

							<div class=" col-md-8 col-lg-8">
								<table class="table">
									<tbody>
										<tr>
											<td class="titre" style="width:30%">Description</td>
											<td class="reponse" style="width:70%">${requestScope.client.getDescription_client()}</td>
										</tr>
										<tr>
											<td class="titre">Adresse-Mail</td>
											<td class="reponse">${requestScope.client.getMail_client()}</td>
										</tr>
										<tr>
											<td class="titre">Telephone</td>
											<td class="reponse">${requestScope.client.getNumero_telephone_client()}</td>
										</tr>
										<tr>
											<td class="titre">Adresse</td>
											<td class="reponse">${requestScope.client.getFacturation_adresse()}</br> ${requestScope.client.getFacturation_code_postal()} ${requestScope.client.getFacturation_ville()}</td>
										</tr>
										<tr>
											<td class="titre">Type</td>
											<td class="reponse">${requestScope.client.getType_client().getIntitule_type_client()} (remise ${requestScope.client.getType_client().getTaux_remise_client()}%)</td>
										</tr>
										<tr>
											<td class="titre">Ses locations</td>
											<td class="reponse"><a href="#">Voir les précédentes locations</a></td>
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