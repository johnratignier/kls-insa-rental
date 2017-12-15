<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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

			<div class="col-sm-12 col-md-12">

				<div class="row">
					<c:forEach items="${sessionScope.materielList}" var="materiel">
						<div
							class="col-sm-6 col-md-4 col-lg-3 p-1 materielItem materiel_item_deja_reserve d-block">
							<div class="m-1 border h-100 w-100 card_conteneur">
								<div class="mb-1 p-1 image_conteneur" style="height: 150px;">
									<img data-toggle="modal" data-target="#detail_materiel_modal"
										src="${materiel.value.getPhoto_materiel()}"
										class="m-2 mx-auto AJAX_detail_materiel"
										style="object-fit: fill" /> <span
										class="AJAX_id_materiel d-none">${materiel.value.getId_materiel()}</span>
								</div>
								<div class="text-center">${materiel.value.getNom_materiel()}</div>
								<div class="text-center font-weight-bold">
									<c:if test="${materiel.value.getExoneration() eq 1}">
										<fmt:formatNumber
											value="${materiel.value.getPrix_materiel()*(1.0-0.01*sessionScope.clientReservation.getType_client().getTaux_remise_client())}"
											maxFractionDigits="2" /> €
													</c:if>
									<c:if test="${materiel.value.getExoneration() eq 0}">
										<fmt:formatNumber value="${materiel.value.getPrix_materiel()}"
											maxFractionDigits="2" /> €
														<div class="badge badge-secondary" role="alert">Non
											exonéré</div>
									</c:if>
								</div>
								<div class="text-center bouton_reserver_conteneur"></div>
							</div>
						</div>
					</c:forEach>
				</div>

			</div>

		</div>
	</div>

	<div class="modal fade" id="detail_materiel_modal" tabindex="-3"
		role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" role="document">
			<div class="modal-content" id="detail_materiel_content"></div>
		</div>
	</div>

</body>

</html>