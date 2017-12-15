<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>KLS - Reservation</title>

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
				$(".changee").toggle();
				//document.getElementById('profilFormPseudo').style.display = 'inline'
				$(".profilFormPseudo").toggle();
			});
		});
	</script>

	<div class="container">
		<div class="row pt-4">
			<div class="col-md-9 ml-auto mr-auto">
				<h4 class="text-center">Page Reservation</h4>
				<hr>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-9 col-sm-9 col-md-9 col-lg-9 ml-auto mr-auto">
				<form method="POST" action="/kls/listereservations?id=${reservation.getId_reservation()}" enctype="multipart/form-data">
					<div class="panel panel-info">
						<div class="panel-body">
							<div class="row">
								<div class=" col-md-12 col-lg-12">
									<table class="table">
										<tbody>
											<tr>
												<td class="">Client</td>
												<td class="">${reservation.getClient_reservation().getNom_client()}</td>
											</tr>
											<tr>
												<td class="">Date de départ :</td>
												<td class="">${reservation.getDepart_reservation()}</td>
											</tr>
											<tr>
												<td class="">Date de retour :</td>
												<td class="">${reservation.getRetour_reservation()}</td>
											</tr>
											<tr>
												<td class="">Nb de jour facturé :</td>
												<td class="">${reservation.getDuree_reservation()}</td>
											</tr>
											<tr>
												<td class="">Commentaires :</td>
												<td class="">${reservation.getCommentaires()}</td>
											</tr>
											<tr>
												<td class="">Liste matériel :</td>
												<td class=""><c:forEach items="${reservation.getList_materiel_reservation()}" var="materiel">
														${materiel.getNom_materiel()}<br/></option>
													</c:forEach></td>
											</tr>
											<tr>
												<td class="">Facture :</td>
												<td class=""><a target="_blank" href="/kls/facturePDF/${reservation.getFacture_reservation().getChemin_facture()}.pdf">${reservation.getFacture_reservation().getChemin_facture()}.pdf</a></td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="col-md-12 col-lg-12 " align="center">
									<a href="#" class="btn btn-warning col-md-4 ml-auto md-auto mt-4 disabled">Modifier</a>
									<a href="/kls/listereservations?supprimerreservation=${reservation.getId_reservation()}" class="btn btn-danger col-md-4 ml-auto md-auto mt-4 mod">Supprimer</a>
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