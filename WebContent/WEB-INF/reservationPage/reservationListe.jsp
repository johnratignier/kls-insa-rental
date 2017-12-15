<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<%@ include file="/WEB-INF/header.jsp"%>
<link rel="stylesheet" href="api/listeCard.css">

</script>
<script src="api/listeCard.js"></script>

<script>
    $(document).ready(function() {
	$(".lien").click(function() {
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
    });
</script>


<title>KLS - Liste des reservations</title>
</head>

<body>

	<div class="container pt-4">

		<div class="col-md-9 ml-auto mr-auto">
			<h4 class="text-center">Liste des reservations</h4>
			<hr>
		</div>

		<div class="col-sm-12 col-md-11 ml-auto mr-auto tableauListe" style="overflow: scroll; height: 600px;">

			<table class="table table-hover" id="tableau">
				<thead class="thead-inverse">
					<tr style="cursor: pointer;">
						<th>Id</th>
						<th>Client</th>
						<th>Depart</th>
						<th>Retour</th>
						<th>Materiel</th>
						<th>Prix</th>
						<th>Gerer</th>
					</tr>
				</thead>

				<tbody id="listMateriel">
					<c:forEach items="${listeReservation}" var="resa">
						<tr data-toggle="collapse" href="" class="">
							<th scope="row" class="entreeId">${resa.key}</th>
							<td class="pl-2">${resa.value.getClient_reservation().getNom_client()}</td>
							<td class="pl-2">${resa.value.getDepart_reservation()}</td>
							<td class="pl-2">${resa.value.getRetour_reservation()}</td>
							<td class="pl-2"><c:forEach items="${resa.value.getList_materiel_reservation()}" var="mat">${mat.getNom_materiel()}, </c:forEach></td>
							<td class="pl-2"><fmt:formatNumber value="${resa.value.getTotal_prix_reservation()}" maxFractionDigits="2" /> €</td>
							<td class="text-center choixReserver" style="cursor:pointer" onclick="location.href='/kls/listereservations?id=${resa.key}'"><button class="btn btn-primary voir" style="cursor: pointer;">Voir</button></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>

	</div>
</body>

</html>