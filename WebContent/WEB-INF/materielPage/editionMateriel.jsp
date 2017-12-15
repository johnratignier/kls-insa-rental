<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>KLS - Nouveau materiel</title>

<script src="js/prefixfree.min.js">
</script>
<script>

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
		<form class="form-horizontal" role="form" method="POST" action="/kls/editionmateriel" accept-charset="UTF-8">
			<div class="row pt-4">
				
				<div class="col-md-9 ml-auto mr-auto">
					<h4 class="text-center">Modification materiel</h4>
					<hr>
				</div>
				
			</div>
			<!-- <div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Photo</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-image"></i>
							</div>
							<input type="file" name="photo_materiel" class="form-control md-6" id="">
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle"> <!-- Put name validation error messages here
						</span>
					</div>
				</div>
			</div>-->
			<span class="d-none"><input type="text" maxlength="3" name="id_materiel" class="form-control" id="label"
								value="${materiel.getId_materiel()}" required autofocus></span>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Label</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-play"></i>
							</div>
							<input type="text" maxlength="45" name="label" class="form-control" id="label"
								value="${materiel.getNom_materiel()}" required autofocus>
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
							<textarea maxlength="200" name="description" class="form-control" id="description"
								required autofocus>${materiel.getDescription_materiel()}</textarea>
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
					<label for="name">Prix</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-eur"></i>
							</div>
							<input type="number" step="0.01" name="prix" min="0" max="1000" class="form-control" id="prix"
								value="${materiel.getPrix_materiel()}" required autofocus>
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
					<label for="name">Caution</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-eur"></i>
							</div>
							<input type="number" step="0.01" min="0" max="10000" name="caution" class="form-control" id="caution"
								value="${materiel.getCaution_materiel()}" required autofocus>
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
					<label for="name">Etat</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-exclamation"></i>
							</div>
							<select name="etat" class="form-control" id="etat"
								placeholder="" autofocus>
								<c:forEach items="${opt_etat_materiel}" var="etat_materiel">
									<option value="${etat_materiel.getId_etat_materiel()}" <c:if test="${materiel.getEtat_materiel().getId_etat_materiel() eq etat_materiel.getId_etat_materiel()}">selected</c:if>>${etat_materiel.getIntitule_etat_materiel()}</option>
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
					<label for="name">Type</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-transgender"></i>
							</div>
							<select name="type" class="form-control" id="type"
								autofocus>
								
								<c:forEach items="${opt_type_materiel}" var="type_materiel">
									<option value="${type_materiel.getId_type_materiel()}" <c:if test="${materiel.getType_materiel().getId_type_materiel() eq type_materiel.getId_type_materiel()}">selected</c:if>>${type_materiel.getIntitule_type_materiel()}</option>
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
					<label for="name">Exoneration</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-exclamation"></i>
							</div>
							<select name="exoneration" class="form-control" id="exoneration" autofocus>
									<option value="1" <c:if test="${materiel.getExoneration() eq 1}">selected</c:if>>OUI</option>
									<option value="0" <c:if test="${materiel.getExoneration() eq 0}">selected</c:if>>NON</option>
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
					<label for="name">Location</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.4rem">
								<i class="fa fa-exclamation"></i>
							</div>
							<select name="location" class="form-control" id="exoneration"
								autofocus>
									<option value="1" <c:if test="${materiel.getLocation() eq 1}">selected</c:if>>OUI</option>
									<option value="0" <c:if test="${materiel.getLocation() eq 0}">selected</c:if>>NON</option>
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
				<div class="col-md-5 mr-auto">
					<a><button type="submit" id="valid" class="btn btn-success">
						<i class="fa fa-user-plus"></i> Valider les modifications
					</button></a>
					<a href="home"><button class="btn btn-danger">
						<i class=""></i> Annuler
					</button></a>
				</div>
			</div>
		</form>
	</div>

	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

</body>

</html>