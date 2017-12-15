<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>KLS - Inscription membre</title>

<script src="/kls/bootstrap/js/prefixfree.min.js">
    
</script>
<script>
    $(document).ready(function() {
	if (!$("#mdp").val() === ("#mdpv").val()) {
	    $(':input[type="submit"]').prop('disabled', true);
	} else {
	    $(':input[type="submit"]').prop('disabled', false);
	}
    });
</script>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css"
	integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">


</head>

<body>
	<%@ include file="/WEB-INF/header.jsp"%>

	<div class="container">
		<form class="form-horizontal" role="form" method="POST" action="/kls/listemembres">
			<div class="row pt-4">

				<div class="col-md-9 ml-auto mr-auto">
					<h4 class="text-center">Modification membre</h4>
					<hr>
				</div>

			</div>
			<!-- <div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Photo de profil</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-image"></i>
							</div>
							<input type="file" name="photo_profil" class="form-control md-6" id="">
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
			<div class="row d-none">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Id</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-user"></i>
							</div>
							<input type="text" name="id" class="form-control" id="prenom" value="${membremodif.getId_user()}" required autofocus>
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
					<label for="name">Prenom</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-user"></i>
							</div>
							<input type="text" name="prenom" class="form-control" id="prenom" value="${membremodif.getPrenom()}" required autofocus>
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
							<input type="text" name="nom" class="form-control" id="nom" value="${membremodif.getNom()}" required autofocus>
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
							<input type="text" name="mail" class="form-control" id="addresse_mail" value="${membremodif.getAdresse_mail()}" required autofocus>
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
							<input type="tel" name="telephone" class="form-control" id="telephone" value="${membremodif.getNumero_tel()}" required autofocus>
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
					<label for="name">Département</label>
				</div>
				<div class="col-md-2">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-graduation-cap"></i>
							</div>
							<select type="text" name="departement_annee" class="form-control" id="" placeholder="Année" autofocus>
								<option value="1" <c:if test="${membremodif.getDepartement_annee() eq 1}">selected</c:if>>1</option>
								<option value="2" <c:if test="${membremodif.getDepartement_annee() eq 2}">selected</c:if>>2</option>
								<option value="3" <c:if test="${membremodif.getDepartement_annee() eq 3}">selected</c:if>>3</option>
								<option value="4" <c:if test="${membremodif.getDepartement_annee() eq 4}">selected</c:if>>4</option>
								<option value="5" <c:if test="${membremodif.getDepartement_annee() eq 5}">selected</c:if>>5</option>
								<option value="+" <c:if test="${membremodif.getDepartement_annee() eq '+'}">selected</c:if>>+</option>
								<option value="DIPLOME" <c:if test="${membremodif.getDepartement_annee() eq 'DIPLOME'}">selected</c:if>>DIPLOME</option>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-3 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-graduation-cap"></i>
							</div>
							<select type="text" name="departement_etude" class="form-control" id="" placeholder="Departement" autofocus>
								<option value="BS" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'BS'}">selected</c:if>>Biosciences</option>
								<option value="SGM" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'SGM'}">selected</c:if>>Sciences et Genie des
									Materiaux</option>
								<option value="GCU" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'GCU'}">selected</c:if>>Genie Civil et Urbanisme</option>
								<option value="GM" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'GM'}">selected</c:if>>Genie Mecanique</option>
								<option value="GEN" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'GEN'}">selected</c:if>>Genie Energetique
									Environnement</option>
								<option value="GE" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'GE'}">selected</c:if>>Genie Electrique</option>
								<option value="IF" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'IF'}">selected</c:if>>Informatique</option>
								<option value="TC" <c:if test="${sessionScope.membre.getDepartement_etude() eq 'TC'}">selected</c:if>>Telecommunications</option>
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
					<label for="name">Droits</label>
				</div>
				<div class="col-md-5 mr-auto">

					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-user"></i>
							</div>
							<select type="text" name="droits" class="form-control" id="" placeholder="Departement" autofocus>
								<c:forEach items="${requestScope.droitsmembre}" var="droits">
									<option value="${droits.getId_droits_membre()}"
										<c:if test="${membremodif.getDroits().getIntitule_droits_membre() eq droits.getIntitule_droits_membre()}">selected</c:if>>${droits.getIntitule_droits_membre()}</option>
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
					<label for="name">Statut</label>
				</div>
				<div class="col-md-5 mr-auto">

					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-user"></i>
							</div>
							<select type="text" name="statut" class="form-control" id="" placeholder="Departement" autofocus>
								<c:forEach items="${requestScope.statutmembre}" var="statut">
									<option value="${statut.getId_statut()}"
										<c:if test="${membremodif.getStatut().getIntitule_statut() eq statut.getIntitule_statut()}">selected</c:if>>${statut.getIntitule_statut()}</option>
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
					<label for="name">Pseudo artiste</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-music"></i>
							</div>
							<input type="text" name="pseudo_artiste" class="form-control" id="telephone" ${membremodif.getPseudo_artiste()} autofocus>
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

			<!-- <div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Mot de passe</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-unlock-alt"></i>
							</div>
							<input type="password" name="mot_de_passe" class="form-control" id="mdp"
								required autofocus>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle"> <!-- Put name validation error messages here
						</span>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Mot de passe (verification)</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-unlock-alt"></i>
							</div>
							<input type="password" name="mot_de_passe_verif" class="form-control" id="mdpv"
								placeholder="motdepasse" required autofocus>
						</div>
					</div>
				</div>
				<div class="col-md-1">
					<div class="form-control-feedback">
						<span class="text-danger align-middle" id="erreurMDP">
							<c:if test="${erreurMDP!=null}">
								${erreurMDP}
							</c:if>
						</span>
					</div>
				</div>
			</div>-->
			<div class="row">
				<div class="col-md-2 ml-auto field-label-responsive">
					<label for="name">Date de naissance</label>
				</div>
				<div class="col-md-5 mr-auto">
					<div class="form-group">
						<div class="input-group mb-2 mr-sm-2 mb-sm-0">
							<div class="input-group-addon" style="width: 2.6rem">
								<i class="fa fa-birthday-cake"></i>
							</div>
							<input type="date" name="date_de_naissance" autocomplete="bday" placeholder="01-01-2000" class="form-control" id="telephone"
								value="${membremodif.getDate_naissance()}" required autofocus>
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
						<i class="fa fa-user-plus"></i> Modifier
					</button>
					<a href="#" id="valid" class="btn btn-warning"> Annuler </a>
				</div>
			</div>
		</form>
	</div>

	<script src='http://codepen.io/assets/libs/fullpage/jquery.js'></script>

</body>

</html>