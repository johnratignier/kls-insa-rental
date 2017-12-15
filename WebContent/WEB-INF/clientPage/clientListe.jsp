<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<%@ include file="/WEB-INF/header.jsp"%>
<link rel="stylesheet" href="api/listeCard.css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous">
    
</script>
<script src="api/listeCard.js"></script>
<title>KLS - Liste des clients</title>
</head>

<body>

	<div class="container pt-4">

		<div class="col-md-9 ml-auto mr-auto">
			<h4 class="text-center">Liste des clients</h4>
			<hr>
		</div>


		<div class="row">
			<c:forEach items="${sessionScope.clientListe}" var="client">
				<div class="col-md-4">
					<a id="clickMembre" href="/kls/listeclients?id=${client.key}">
						<div class="card">
							<canvas class="header-bg" width="250" height="70" id="header-blur"></canvas>
							<div class="avatar">
								<img src="${client.value.getPhoto_client()}" alt="" />
							</div>
							<div class="content">
								<h5>
									<b>${client.value.getNom_client()}</b>
								</h5>
								<h6>${client.value.getNumero_telephone_client()}</h6>
							</div>
						</div>
					</a>
				</div>
			</c:forEach>
		</div>
</body>

</html>