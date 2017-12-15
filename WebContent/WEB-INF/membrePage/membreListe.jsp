<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<%@ include file="/WEB-INF/header.jsp"%>
<link rel="stylesheet" href="api/listeCard.css">
<script src="api/listeCard.js"></script>

<script>
$(document).ready(function() {
});
</script>


<title>KLS - Liste des membres</title>
</head>

<body>

	<div class="container pt-4">

		<div class="col-md-9 ml-auto mr-auto">
			<h4 class="text-center">Liste des membres</h4>
			<hr>
		</div>


		<div class="row">
			<c:forEach items="${sessionScope.membreListe}" var="membre">
				<div class="col-md-4">
					<a id="clickMembre" href="/kls/listemembres?id=${membre.key}">
						<div class="card">
							<canvas class="header-bg" width="250" height="70" id="header-blur"></canvas>
							<span id="membreID" class="d-none">${membre.key}</span>
							<div class="avatar">
								<img src="${membre.value.getPhoto_profil()}" alt="" />
							</div>
							<div class="content">
								<h5>
									<b>${membre.value.getPrenom()} ${membre.value.getNom()}</b>
								</h5>
								<h6>${membre.value.getNumero_tel()}</h6>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>

	</div>
</body>

</html>