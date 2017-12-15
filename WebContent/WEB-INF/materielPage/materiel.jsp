<%@ page pageEncoding="UTF-8"%>

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
	height: auto;
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
<script type="text/javascript">
    $(document).ready(function() {
	var h = $(window).height();
	$(".tableauListe").height(h);

	$(".entreeTable").click(function() {
	    $("tr").removeClass("table-primary");
	    var idMaterielShow = $(this).find(".entreeId").text();
	    $(this).first("tr").addClass("table-primary");

	    $.ajax({
		url : 'materiel',
		type : 'GET',
		async : false,
		data : 'id=' + idMaterielShow,
		dataType : 'html',
		timeout : 4000,
		success : function(data) {
		    console.log("SUCCES");
		    $(data).replaceAll("#detail_materiel");
		},
		error : function() {
		    console.log("ECHEC");
		}
	    });
	});

	$('[data-toggle="buttons"] .btn').on('click', function() {
	    //Find the child check box.
	    var $input = $(this);
	    var idMateriel = $(this).parents("label").find(".idMaterielChoix").text();
	    var titreT = $("#titre_materiel").text();
	    var prixT = parseFloat($("#prix_materiel").text());
	    var prixC = parseFloat($(".choixListePrix").text());
	    console.log("SUCCES".concat(titreT));
	    
	    //Remove the attribute if the button is "disabled"
	    if ($(this).hasClass('btn-danger')) {
			$input.removeAttr('checked');
			var prixN = prixC - prixT;
			$(".intitule_bouton").replaceWith("<span class=\"intitule_bouton\">Réserver</span>");
			console.log("NO>");
			$.ajax({
		    url : 'nouvellereservation',
		    type : 'GET',
		    async : false,
		    data : 'moins=' + idMateriel,
		    dataType : 'html',
		    timeout : 4000,
		    success : function(data) {
			console.log("SUCCES");
		    },
		    error : function() {
			console.log("ECHEC");
		    }
		});
	    } else {
		$input.attr('checked', '');
		var prixN = prixT + prixC;
		$(".intitule_bouton").replaceWith("<span class=\"intitule_bouton\">Annuler</span>");
		console.log("-6>".concat(titreT));
		console.log("YES>");
		$.ajax({
		    url : 'nouvellereservation',
		    type : 'GET',
		    async : false,
		    data : 'plus=' + idMateriel,
		    dataType : 'html',
		    timeout : 4000,
		    success : function(data) {
			console.log("SUCCES");
		    },
		    error : function() {
			console.log("ECHEC");
		    }
		});
	    }

	    return false; //Click event is triggered twice and this prevents re-toggling of classes
	});

	/*$('[data-toggle="buttons"] .btn').on('click', function() {
	    // toggle stylewa
	    $(this).toggleClass('btn-success btn-warning active');
	    // toggle checkbox
	    var $chk = $(this).find('[type=checkbox]');
	    $chk.prop('checked', !$chk.prop('checked'));

	    return false;
	});*/

	$('form').on('submit', function(e) {
	    // watch form values
	    $('#formValues').html(($('form').serialize()));
	    e.preventDefault();
	});

	$('#tableau').tablesorter();


	$(window).resize(function() {
	    var h = $(window).height();
	    $(".tableauListe").height(h);
	});

    });
</script>

<script>
    
</script>


<title>KLS - Materiel</title>

</head>

<body>


	<div class="container">
		<div class="row pt-2" id="">

			<div class="col-sm-12 col-md-4" id="detail_materiel"></div>


			<div class="col-sm-12 col-md-6 tableauListe"
				style="overflow: scroll; height: 600px;">

				<table class="table table-hover" id="tableau">
					<thead class="thead-inverse">
						<tr style="cursor: pointer;">
							<th>Id</th>
							<th>Matériel</th>
							<th>Prix</th>
							<th>Type</th>
							<c:if test="${sessionScope.currentReservation==null}">
								<th>Réservation</th>
							</c:if>
						</tr>
					</thead>

					<tbody id="listMateriel">
						<c:forEach items="${sessionScope.materielList}" var="materiel">
							<tr data-toggle="collapse" href="" class="entreeTable">
								<th scope="row" class="entreeId"><div
										class="badge badge-secondary" role="alert">${materiel.key}</div></th>
								<td class="pl-2">${materiel.value.getNom_materiel()}</td>
								<td class="pl-2"><fmt:formatNumber
										value="${materiel.value.getPrix_materiel()*(1.0-0.01*sessionScope.clientReservation.getType_client().getTaux_remise_client())}"
										maxFractionDigits="2" /> €</td>
								<td class="pl-2"><div class="badge badge-secondary"
										role="alert">${materiel.value.getType_materiel().getIntitule_type_materiel()}</div></td>
								<c:if test="${sessionScope.currentReservation==null}">
									<td class="text-center choixReserver"><c:choose>
											<c:when
												test="${materiel.value.getEtat_materiel().getIntitule_etat_materiel() eq 'EN REPARATION' || materiel.value.getEtat_materiel().getIntitule_etat_materiel() eq 'HORS SERVICE'}">
												<input class="materielSelect" type="checkbox"
													data-on="Annuler" data-off="Indisponible"
													data-toggle="toggle" data-onstyle="danger"
													data-offstyle="danger" data-size="small" disabled>
												<span class="d-none idMaterielChoix">${materiel.key}</span>
											</c:when>
											<c:otherwise>
												<div data-toggle="buttons">
													<label class="btn btn-block btn-info active materielSelect">
														<input type="checkbox" name="breakfluid"
														autocomplete="off">
														<span class="d-none idMaterielChoix">${materiel.key}</span><span class="intitule_bouton">Réserver</span>
													</label>
												</div>
											</c:otherwise>
										</c:choose></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>

			</div>
			<div class="col-sm col-md text-center">
				<div class="card text-white bg-primary w-100 m-1">
					<div class="card-header">Reservation</div>
					<div class="card-body">
						<h4 style="display: inline-block" class="card-text choixListePrix">0</h4>
						<h4 style="display: inline-block">€</h4>
						<br>
						<div class="badge badge-secondary" role="alert">Remise:
							${sessionScope.clientReservation.getType_client().getTaux_remise_client()}
							%</div>
						<p class="card-text">${sessionScope.clientReservation.getNom_client()}</p>
						<div class="text-dark choixListeTitre"></div>
					</div>
				</div>
				<a href="/kls/nouvellereservation?valider=1"><button
						class="btn btn-success w-100 m-1" style="cursor: pointer;">Valider</button></a>
				<br> <a href="/kls/nouvellereservation?annuler=1"><button
						class="btn btn-danger w-100 m-1" style="cursor: pointer;">Annuler</button></a>
			</div>

		</div>
	</div>


</body>

</html>