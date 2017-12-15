<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<%@ include file="/WEB-INF/header.jsp"%>

<style>
.entreeTableSelect {
	background-color: gray;
}

img {
	display: block;
	max-width: 100%;
	max-height: 90%;
}

div.body {
	font-size: 0.9rem;
}
</style>

<link rel="stylesheet" href="jquery/dist/css/pignose.calendar.css">
<link rel="stylesheet" href="api/datatables.css">
<link href="bootstrap/css/bootstrap-toggle.css" rel="stylesheet">
<script src="jquery/demo/js/moment.latest.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/semantic.ui.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/prism.min.js"></script>
<script type="text/javascript" src="jquery/glDatePicker.js"></script>
<script src="jquery/dist/js/pignose.calendar.full.js"></script>
<script src="api/jquery.tablesorter.min.js"></script>
<script src="bootstrap/js/bootstrap-toggle.js"></script>
<script src="api/datatables.js"></script>
<script type="text/javascript" src="js/materiel.js"></script>


<title>KLS - Materiel</title>

</head>

<body>


	<div class="container body">

		<div class="row pt-2">
			<!--  <div class="col-sm-12 col-md-4" id="detail_materiel"></div>-->

			<div class="col-sm-12 col-md-9 panelMateriel" style="overflow: scroll; height: 600px">

				<div class="row">
					<c:forEach items="${sessionScope.materielList}" var="materiel">
						<c:choose>
							<c:when test="${materiel.value.getUtilisation_reservation() eq 1}">
								<div class="col-sm-6 col-md-4 col-lg-3 p-1 materielItem materiel_item_deja_reserve d-block">
									<div class="m-1 border h-100 w-100 card_conteneur">
										<div class="mb-1 p-1 image_conteneur" style="height: 150px;">
											<img data-toggle="modal" data-target="#detail_materiel_modal" src="${materiel.value.getPhoto_materiel()}"
												class="m-2 mx-auto AJAX_detail_materiel" style="object-fit: fill" /> <span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
										</div>
										<div class="text-center">${materiel.value.getNom_materiel()}</div>
										<div class="text-center font-weight-bold">
											<c:if test="${materiel.value.getExoneration() eq 1}">
												<fmt:formatNumber
													value="${materiel.value.getPrix_materiel()*(1.0-0.01*sessionScope.clientReservation.getType_client().getTaux_remise_client())}"
													maxFractionDigits="2" /> €
													</c:if>
											<c:if test="${materiel.value.getExoneration() eq 0}">
												<fmt:formatNumber value="${materiel.value.getPrix_materiel()}" maxFractionDigits="2" /> €
														<div class="badge badge-secondary" role="alert">Non exonéré</div>
											</c:if>
										</div>
										<div class="text-center bouton_reserver_conteneur">
											<button class="btn btn-danger btn-sm m-1 AJAX_bouton_reserver" disabled>Deja utilisé</button>
											<span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
										</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when
										test="${materiel.value.getEtat_materiel().getIntitule_etat_materiel() eq 'EN REPARATION' || materiel.value.getEtat_materiel().getIntitule_etat_materiel() eq 'HORS SERVICE'}">
										<div class="col-sm-6 col-md-4 col-lg-3 p-1 materielItem materiel_item_indisponible d-block">
											<div class="m-1 border h-100 w-100 card_conteneur">
												<div class="mb-1 p-1 image_conteneur" style="height: 150px;">
													<img data-toggle="modal" data-target="#detail_materiel_modal" src="${materiel.value.getPhoto_materiel()}"
														class="m-2 mx-auto AJAX_detail_materiel" style="object-fit: fill" /> <span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
												</div>
												<div class="text-center">${materiel.value.getNom_materiel()}</div>
												<div class="text-center font-weight-bold">
													<c:if test="${materiel.value.getExoneration() eq 1}">
														<fmt:formatNumber
															value="${materiel.value.getPrix_materiel()*(1.0-0.01*sessionScope.clientReservation.getType_client().getTaux_remise_client())}"
															maxFractionDigits="2" /> €
													</c:if>
													<c:if test="${materiel.value.getExoneration() eq 0}">
														<fmt:formatNumber value="${materiel.value.getPrix_materiel()}" maxFractionDigits="2" /> €
														<div class="badge badge-secondary" role="alert">Non exonéré</div>
													</c:if>
												</div>
												<div class="text-center bouton_reserver_conteneur">
													<button class="btn btn-danger btn-sm m-1 AJAX_bouton_reserver" disabled>Indisponible (HS)</button>
													<span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="${materiel.value.getLocation() eq '0'}">
										<div class="col-sm-6 col-md-4 col-lg-3 p-1 materielItem materiel_item_non_louable d-block">
											<div class="m-1 border h-100 w-100 card_conteneur">
												<div class="mb-1 p-1 image_conteneur" style="height: 150px;">
													<img data-toggle="modal" data-target="#detail_materiel_modal" src="${materiel.value.getPhoto_materiel()}"
														class="m-2 mx-auto AJAX_detail_materiel" style="object-fit: fill" /> <span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
												</div>
												<div class="text-center">${materiel.value.getNom_materiel()}</div>
												<div class="text-center font-weight-bold">
													<c:if test="${materiel.value.getExoneration() eq 1}">
														<fmt:formatNumber
															value="${materiel.value.getPrix_materiel()*(1.0-0.01*sessionScope.clientReservation.getType_client().getTaux_remise_client())}"
															maxFractionDigits="2" /> €
													</c:if>
													<c:if test="${materiel.value.getExoneration() eq 0}">
														<fmt:formatNumber value="${materiel.value.getPrix_materiel()}" maxFractionDigits="2" /> €
														<div class="badge badge-secondary" role="alert">Non exonéré</div>
													</c:if>
												</div>
												<div class="text-center bouton_reserver_conteneur">
													<button class="btn btn-danger btn-sm m-1 AJAX_bouton_reserver" disabled>Non louable</button>
													<span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="col-sm-6 col-md-4 col-lg-3 p-1 materielItem">
											<div class="m-1 border h-100 w-100 card_conteneur">
												<div class="mb-1 p-1 image_conteneur" style="height: 150px;">
													<img data-toggle="modal" data-target="#detail_materiel_modal" src="${materiel.value.getPhoto_materiel()}"
														class="m-2 mx-auto AJAX_detail_materiel" style="object-fit: fill" /> <span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
												</div>
												<div class="text-center">${materiel.value.getNom_materiel()}</div>
												<div class="text-center font-weight-bold">
													<c:if test="${materiel.value.getExoneration() eq 1}">
														<fmt:formatNumber
															value="${materiel.value.getPrix_materiel()*(1.0-0.01*sessionScope.clientReservation.getType_client().getTaux_remise_client())}"
															maxFractionDigits="2" /> €
													</c:if>
													<c:if test="${materiel.value.getExoneration() eq 0}">
														<fmt:formatNumber value="${materiel.value.getPrix_materiel()}" maxFractionDigits="2" /> €
														<div class="badge badge-secondary" role="alert">Non exonéré</div>
													</c:if>
												</div>
												<div class="text-center bouton_reserver_conteneur">
													<button class="btn btn-primary btn-sm m-1 AJAX_bouton_reserver">Réserver</button>
													<span class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>

			</div>

			<div class="col-sm col-md text-center">

				<div style="text-align:center;">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						Choix affichage </a>
					<div class="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdownMenuLink" style="text-align: center;">
						<input type="checkbox" class="dropdown-item" id="choix_affichage_deja_reserve" checked>Deja reservé
						<input type="checkbox" class="dropdown-item" id="choix_affichage_indisponible" checked>Indisponible
						<input type="checkbox" class="dropdown-item" id="choix_affichage_non_louable" checked>Non louable
					</div>
				</div>

				<div class="card text-white bg-primary w-100 m-1">
					<div class="card-header">Reservation</div>
					<div class="card-body">
						<h4 style="display: inline-block" class="card-text" id="reservation_prix_total">0</h4>
						<h4 style="display: inline-block">€</h4>
						<br>
						<div class="badge badge-secondary" role="alert">Remise: ${sessionScope.clientReservation.getType_client().getTaux_remise_client()} %</div>
						</br>
						<!-- <h4 style="display: inline-block" class="card-text" id="reservation_prix_total_exonere">0</h4>
						<h4 style="display: inline-block">€</h4>-->
						<p class="card-text">${sessionScope.clientReservation.getNom_client()}</p>
						<div class="text-dark" id="reservation_selection_materiel"></div>
					</div>
				</div>
				<a href="/kls/nouvellereservation?valider=1"><button class="btn btn-success w-100 m-1" style="cursor: pointer;">Valider</button></a> <br> <a
					href="/kls/nouvellereservation?annuler=1"><button class="btn btn-danger w-100 m-1" style="cursor: pointer;">Annuler</button></a>
			</div>

		</div>
	</div>

	<div class="modal fade" id="detail_materiel_modal" tabindex="-3" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content" id="detail_materiel_content"></div>
		</div>
	</div>

</body>

</html>