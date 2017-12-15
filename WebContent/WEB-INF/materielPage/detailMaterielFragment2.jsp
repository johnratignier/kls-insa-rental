<!-- <link rel="stylesheet" href="jquery/dist/css/pignose.calendar.css">
<script src="jquery/demo/js/moment.latest.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/semantic.ui.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/prism.min.js"></script>
<script src="jquery/dist/js/pignose.calendar.full.js"></script>-->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script>
	$(document).ready(function() {
		$('.calendar').pignoseCalendar({
			lang : 'fr',
			week: 1,
			disabledDates : [${requestScope.listedate}],
			scheduleOptions : {
				colors : {
					offer : '#2fabb7',
					ad : '#5c6270'
				}
			},
			select : function(date, context) {
				console.log('events for this date', context.storage.schedules);
			}
		});
	});
</script>

<div class="modal-content" id="detail_materiel_content">

	<div class=modal-body">

		<div class="row pt-2 pb-2">
			<div class="text-center col-md-10 col-lg-10 ml-auto mr-auto mt-3">
				<h4 class="text-center" id="titre_materiel">${requestScope.materielCurrent.getNom_materiel()}</h4>
				<h5 class="text-center text-primary">
					<span id="prix_materiel_non_exoneree">${requestScope.materielCurrent.getPrix_materiel()}</span>
					&euro;
					<c:if test="${requestScope.materielCurrent.getExoneration() eq 0}">
						<div class="badge badge-secondary" role="alert">Non
							exon&#233;r&#233;</div>
					</c:if>
					<span id="prix_materiel" class="d-none"> <c:if
							test="${requestScope.materielCurrent.getExoneration() eq 1}">
							${requestScope.materielCurrent.getPrix_materiel()*(1.0-0.01*sessionScope.clientReservation.getType_client().getTaux_remise_client())}"
					</c:if> <c:if test="${requestScope.materielCurrent.getExoneration() eq 0}">
							${requestScope.materielCurrent.getPrix_materiel()}
					</c:if>
					</span>
				</h5>
				<ul class="list-group list-group-flush">
					<li class="list-group-item">${requestScope.materielCurrent.getDescription_materiel()}
						<br>Caution:
						${requestScope.materielCurrent.getCaution_materiel()} &euro;
					</li>
					<li class="list-group-item">Type:
						<div class="badge badge-primary" role="alert">${requestScope.materielCurrent.getType_materiel().getIntitule_type_materiel()}</div>
						Etat:
						<c:choose>
							<c:when test="${requestScope.materielCurrent.getEtat_materiel().getIntitule_etat_materiel() eq 'EN REPARATION'}"><div class="badge badge-warning" role="alert">${requestScope.materielCurrent.getEtat_materiel().getIntitule_etat_materiel()}</div></c:when>
							<c:when test="${requestScope.materielCurrent.getEtat_materiel().getIntitule_etat_materiel() eq 'HORS SERVICE'}"><div class="badge badge-danger" role="alert">${requestScope.materielCurrent.getEtat_materiel().getIntitule_etat_materiel()}</div></c:when>
							<c:otherwise><div class="badge badge-primary" role="alert">${requestScope.materielCurrent.getEtat_materiel().getIntitule_etat_materiel()}</div></c:otherwise>
						</c:choose>
						R&#233;f&#233;rence:
						<div class="badge badge-secondary" role="alert">${requestScope.materielCurrent.getId_materiel()}</div>
					</li>
				</ul>
				<c:if test="${sessionScope.membre != null}">
				<div class="m-2">
					<a href="/kls/editionmateriel?id=${requestScope.materielCurrent.getId_materiel()}"><button class="btn btn-primary btn-sm">Modifier</button></a>
					<a href="#"><button class="btn btn-danger btn-sm">Supprimer</button></a>
				</div>
				</c:if>
			</div>
		</div>

		<div id="idd" class="row ml-2 mb-3 mt-3">
			<div class="col-sm-6 col-md-8 col-lg-6 mx-auto">
				<img src="${requestScope.materielCurrent.getPhoto_materiel()}"
					alt="Photo materiel"
					class="col-md-12 mx-auto p-3 m-2 mh-75 my-auto"
					style="object-fit: contain">
			</div>
			<div class="col-sm-9 col-md-9 col-lg-6 mb-2 mx-auto ml-auto">
				<div class="calendar pb-2 pt-3"></div>
			</div>
		</div>

	</div>
</div>