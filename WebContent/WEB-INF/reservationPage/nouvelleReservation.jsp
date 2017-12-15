<%@ page pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<title>KLS - Nouvelle reservation</title>
<%@ include file="/WEB-INF/header.jsp"%>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet" href="jquery/dist/css/pignose.calendar.css">
<link rel="stylesheet" href="api/datatables.css">
<script src="jquery/demo/js/moment.latest.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/semantic.ui.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/prism.min.js"></script>
<script src="jquery/dist/js/pignose.calendar.full.js"></script>
<script src="api/datatables.js"></script>
<script>
	$(document).ready(function() {

		$('input.calendar').pignoseCalendar({
		    week: '1',
			format : 'YYYY-MM-DD' // date format string. (2017-02-02)
		});

	});
</script>

</head>



<body>
	<div class="container">
		<form class="form-horizontal" role="form" method="POST"
			action="/kls/nouvellereservation">
			<div class="row pt-4">

				<div class="col-md-9 ml-auto mr-auto">
					<h4 class="text-center">Nouvelle réservation</h4>
					<hr>
				</div>

			</div>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Client/Locataire</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-smile-o"></i>
							</div>
							<select name="client" class="form-control" id="etat"
								placeholder="" autofocus>
								<c:forEach items="${opt_client}" var="client">
									<option value="${client.key}">${client.value.getNom_client()}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle"> <!-- Put name validation error messages here -->
						</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Commentaires</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-info"></i>
							</div>
							<textarea type="text" maxlength="200" name="commentaires"
								class="form-control" id="description" placeholder=""
								autofocus></textarea>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle"> <!-- Put name validation error messages here -->
						</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Date de départ</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" step="1" name="date_depart" min="0" max="100"
								class="form-control calendar" required autofocus>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle"> <!-- Put name validation error messages here -->
						</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Date de retour</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-calendar"></i>
							</div>
							<input type="text" step="1" name="date_retour" min="0" max="100"
								class="form-control calendar" required autofocus>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle"> <!-- Put name validation error messages here -->
						</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Durée de facturation</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-clock-o"></i>
							</div>
							<input type="number" step="1" name="duree" min="0" max="100"
								class="form-control" required autofocus>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle"> <!-- Put name validation error messages here -->
						</span>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-md-2 ml-auto"></div>
				<div class="col-md-5 mr-auto">
					<input style="cursor:pointer;" class="btn btn-success col-md-7" type="submit" value="Choisir le materiel"><a href="/kls/materiel"><div style="cursor:pointer;" class="btn btn-danger col-md-5">Annuler</div></a>
				</div>
				<div class="col-md-1"></div>
			</div>
		</form>
	</div>

</body>

</html>