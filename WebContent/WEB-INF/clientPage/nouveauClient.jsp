<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>KLS - Inscription membre</title>

<script src="js/prefixfree.min.js">
</script>
<script>
	$(document).ready(function(){
		if(!$("#mdp").val()===("#mdpv").val()){
			$(':input[type="submit"]').prop('disabled', true);
		}else{
			$(':input[type="submit"]').prop('disabled', false);
		}
	});

</script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M"
	crossorigin="anonymous">


</head>

<body>
	<%@ include file="/WEB-INF/header.jsp"%>

	<div class="container">
		<form class="form-horizontal" role="form" method="POST"
			action="/kls/nouveauclient" enctype="multipart/form-data">
			<div class="row pt-4">
				
				<div class="col-md-9 ml-auto mr-auto">
					<h4 class="text-center">Nouveau Client</h4>
					<hr>
				</div>
				
			</div>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Photo</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-image"></i>
							</div>
							<input type="file" name="photo_client" class="form-control md-6" id="">
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
					<label for="name">Nom</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-user"></i>
							</div>
							<input type="text" name="nom" class="form-control" id="Nom"
								placeholder="Nom" required autofocus>
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
					<label for="name">Description</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-info"></i>
							</div>
							<textarea type="text" maxlength="200" name="description" class="form-control" id="description"
								placeholder="Description" required autofocus></textarea>
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
					<label for="name">Adresse-mail</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-at"></i>
							</div>
							<input type="text" name="mail" class="form-control" id="addresse_mail"
								placeholder="xxx@xxx.xxx" required autofocus>
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
					<label for="name">Téléphone</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-phone"></i>
							</div>
							<input type="tel" name="telephone" class="form-control" id="telephone"
								placeholder="xxxxxxxxxx" required autofocus>
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
					<label for="name">Adresse</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-bars"></i>
							</div>
							<input type="text" name="facturation_adresse" class="form-control" id="telephone"
								placeholder="20 Avenue Albert Einstein" required autofocus>
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
					<label for="name">Code Postal et Ville</label>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-bars"></i>
							</div>
							<input type="text" name="facturation_code_postal" class="form-control" id=""
								placeholder="69100" autofocus>
						</div>
					</div>
				</div>
                 <div class="col-md-3 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-bars"></i>
							</div>
							<input type="text" name="facturation_ville" class="form-control" id=""
								placeholder="Villeurbanne" autofocus>
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
					<label for="name">Type</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-graduation-cap"></i>
							</div>
							<select type="text" name="type_client" class="form-control" id=""
								placeholder="Type" autofocus>
								<option value="1">ASSOCIATION INSA</option>
								<option value="2">ASSOCIATION EXTERIEUR</option>
								<option value="3">PARTICULIER</option>
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
				<div class="col-md-2 ml-auto"></div>
				<div class="col-md-4 mr-auto">
					<button type="submit" id="valid" class="btn btn-success">
						<i class="fa fa-user-plus"></i> Ajouter
					</button>
				</div>
			</div>
		</form>
	</div>

	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

</body>

</html>