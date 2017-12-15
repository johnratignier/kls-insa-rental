<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>KLS - Mon Profil</title>

<script src="/kls/bootstrap/js/prefixfree.min.js"></script>



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

	<script>
		$(document).ready(function() {
			$(".mod").click(function() {
				$(".profilForm").toggle();
				//document.getElementById('profilFormPseudo').style.display = 'inline'
				$(".profilFormPseudo").toggle();
			});
		});
	</script>

	<div class="container">
		<div class="row pt-4">
			<div class="col-md-9 ml-auto mr-auto">
				<h4 class="text-center">Mon Profil</h4>
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 ml-auto mr-auto">
				<form method="POST" action="/kls/profil"
					enctype="multipart/form-data">
					<div class="panel panel-info">
						<div class="panel-heading p-2 mt-2 mb-2" style="display:inline">
							<h3 class="panel-title d-inline">${sessionScope.membre.getPrenom()}
								${sessionScope.membre.getNom()}</h3>
							<c:if test="${sessionScope.membre.getPseudo_artiste() != ''}">
								<h5 class="d-inline">aka</h5>
								<h3 class="font-weight-bold text-primary profilForm" style="display:inline">${sessionScope.membre.getPseudo_artiste()}</h3>
								<input type="text" name="pseudo" class="form-control profilFormPseudo" id="profilFormPseudo"
									style="display:none"
									value="${sessionScope.membre.getPseudo_artiste()}" required
									autofocus>
							</c:if>
						</div>
						<div class="panel-body">
							<div class="row">
								<div class="col-md-4 col-lg-4 " align="center">
									<img alt="Photo de profil"
										src="${sessionScope.membre.getPhoto_profil()}"
										class="rounded-circle img-fluid height-100"> <a href="#"
										class="btn btn-warning col-md-10 ml-auto md-auto mt-4 profilForm mod">Modifier
										mes infos</a>
									<button style="display: none"
										class="btn btn-primary col-md-10 ml-auto md-auto mt-4 profilForm"
										type="submit">Valider</button>
									<a href="#" style="display: none"
										class="btn btn-danger col-md-10 ml-auto md-auto mt-4 profilForm mod">Annuler</a>
								</div>

								<div class=" col-md-8 col-lg-8">
									<table class="table">
										<tbody>
											<tr>
												<td class="titre">Adresse-Mail</td>
												<td class="reponse profilForm">${sessionScope.membre.getAdresse_mail()}</td>
												<td class="reponse profilForm" style="display: none"><input
													type="text" name="mail" class="form-control"
													value="${sessionScope.membre.getAdresse_mail()}" required
													autofocus></td>
											</tr>
											<tr>
												<td class="titre">Telephone</td>
												<td class="reponse profilForm">${sessionScope.membre.getNumero_tel()}</td>
												<td class="reponse profilForm" style="display: none"><input
													type="text" name="telephone" class="form-control"
													value="${sessionScope.membre.getNumero_tel()}" required
													autofocus></td>
											</tr>
											<tr>
												<td class="titre">Date de naissance</td>
												<td class="reponse profilForm">${sessionScope.membre.getDate_naissance()}</td>
												<td class="reponse profilForm" style="display: none"><input
													type="date" name="date_naissance" class="form-control"
													value="${sessionScope.membre.getDate_naissance()}" required
													autofocus></td>
											</tr>
											</tr>
											<tr>
												<td class="titre">Departement</td>
												<td class="reponse profilForm">${sessionScope.membre.getDepartement_annee()}
													${sessionScope.membre.getDepartement_etude()}</td>
												<td class="reponse profilForm" style="display: none"><select
													type="text" name="departement_annee" class="form-control col-md-4" style="display:inline-block"
													id="" autofocus>
														<option value="1"
															<c:if test="${sessionScope.membre.getDepartement_annee() eq 1}">selected</c:if>>1</option>
														<option value="2"
															<c:if test="${sessionScope.membre.getDepartement_annee() eq 2}">selected</c:if>>2</option>
														<option value="3"
															<c:if test="${sessionScope.membre.getDepartement_annee() eq 3}">selected</c:if>>3</option>
														<option value="4"
															<c:if test="${sessionScope.membre.getDepartement_annee() eq 4}">selected</c:if>>4</option>
														<option value="5"
															<c:if test="${sessionScope.membre.getDepartement_annee() eq 5}">selected</c:if>>5</option>
														<option value="+"
															<c:if test="${sessionScope.membre.getDepartement_annee() eq '+'}">selected</c:if>>+</option>
														<option value="DIPLOME"
															<c:if test="${sessionScope.membre.getDepartement_annee() eq 'DIPLOME'}">selected</c:if>>DIPLOME</option>
												</select><select type="text" name="departement_etude"
													class="form-control col-md-8" id="" style="display:inline-block"
													value="${sessionScope.membre.getDepartement_etude()}"
													autofocus>
														<option value="BS"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'BS'}">selected</c:if>>Biosciences</option>
														<option value="SGM"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'SGM'}">selected</c:if>>Sciences
															et Genie des Materiaux</option>
														<option value="GCU"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'GCU'}">selected</c:if>>Genie
															Civil et Urbanisme</option>
														<option value="GM"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'GM'}">selected</c:if>>Genie
															Mecanique</option>
														<option value="GEN"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'GEN'}">selected</c:if>>Genie
															Energetique Environnement</option>
														<option value="GE"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'GE'}">selected</c:if>>Genie
															Electrique</option>
														<option value="IF"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'IF'}">selected</c:if>>Informatique</option>
														<option value="TC"
															<c:if test="${sessionScope.membre.getDepartement_etude() eq 'TC'}">selected</c:if>>Telecommunications</option>
												</select></td>
											</tr>
											<tr>
												<td class="titre">Droits</td>
												<td class="reponse profilForm">${sessionScope.membre.getDroits().getIntitule_droits_membre()}</td>
												<c:choose>
												<c:when
													test="${sessionScope.membre.getDroits().getIntitule_droits_membre() eq 'ADMIN'}">
													<td class="reponse profilForm" style="display: none"><select
														type="text" name="droits" class="form-control" id=""
														autofocus>
															<c:forEach items="${requestScope.droitsmembre}"
																var="droits">
																<option value="${droits.getId_droits_membre()}"
																	<c:if test="${sessionScope.membre.getDroits().getIntitule_droits_membre() eq droits.getIntitule_droits_membre()}">selected</c:if>>${droits.getIntitule_droits_membre()}</option>
															</c:forEach>
													</select></td>
												</c:when>
												<c:otherwise>
													<td class="reponse profilForm" style="display:none">${sessionScope.membre.getDroits().getIntitule_droits_membre()}</td>
												</c:otherwise>
												</c:choose>
											</tr>
											<tr>
												<td class="titre">Statut</td>
												<td class="reponse profilForm">${sessionScope.membre.getStatut().getIntitule_statut()}</td>
												<c:choose>
													<c:when
														test="${sessionScope.membre.getDroits().getIntitule_droits_membre() eq 'ADMIN'}">
														<td class="reponse profilForm" style="display: none"><select
															type="text" name="statut" class="form-control" id=""
															autofocus>
																<c:forEach items="${requestScope.statutmembre}"
																	var="statut">
																	<option value="${statut.getId_statut()}"
																		<c:if test="${sessionScope.membre.getStatut().getIntitule_statut() eq statut.getIntitule_statut()}">selected</c:if>>${statut.getIntitule_statut()}</option>
																</c:forEach>
														</select></td>
													</c:when>
													<c:otherwise>
														<td class="reponse profilForm" style="display: none">${sessionScope.membre.getStatut().getIntitule_statut()}</td>
													</c:otherwise>
												</c:choose>
											</tr>
											<tr class="profilForm" style="display: none">
												<td class="titre">Nouveau mot de passe</td>
												<td class="reponse"><input type="text" name="mail"
													class="form-control" autofocus></td>
											</tr>
											<tr class="profilForm">
												<td class="titre">Mes Reservations</td>
												<td class="reponse"><a href="#">Voir mes
														réservations</a></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

</body>

</html>