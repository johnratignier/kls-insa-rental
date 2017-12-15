<%@ page pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<%@ include file="/WEB-INF/header.jsp"%>
<link rel="stylesheet" href="api/listeCard.css">
<link rel="stylesheet" href="api/fullcalendar/fullcalendar.css">
<script src="api/fullcalendar/lib/moment.min.js"></script>
<script src="api/fullcalendar/fullcalendar.js"></script>
<script src="api/listeCard.js"></script>
<!-- <script src="js/reservationCalendrier.js"></script>-->
<script>
$(document).ready(function() {

	$(".calendar").fullCalendar({
	    firstDay:1,
	    themeSystem: 'bootstrap3',
	    lang: 'fr',
	    monthNames: ['Janvier', 'Février', 'Mars', 'Avril', 'Mai', 'Juin', 'Juillet', 'Août','Septembre','Octobre','Novembre','Décembre'],
	    dayNames: ['Lundi','Mardi','Mercredi','Jeudi','Vendredi','Samedi','Dimanche'],
	    dayNamesShort: ['DIM','LUN','MAR','MER','JEU','VEN','SAM'],
	    events: [
	             <c:forEach items="${reservation}" var="resa">
	             {
	                 title  : '${resa.value.getClient_reservation().getNom_client()}',
	                 start  : '${resa.value.getDepart_reservation()}',
	                 end : '${resa.value.getRetour_reservation()}',
	                 color : '#0000A0',
	                 textColor : '#FFFFFF',
	                 url : '/kls/listereservations?id=${resa.key}'
	             },
	             </c:forEach>
	         ]
	});
    
    $(".panelMateriel").height($(window).height()-35);

    $(window).resize(function() {
	var h = $(window).height();
	$(".panelMateriel").height(h-35);
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

		<div class="col-sm-12 col-md-12 ml-auto mr-auto">

			<div class="calendar col-sm-8 col-md-8 ml-auto mr-auto"></div>

		</div>

	</div>
</body>

</html>