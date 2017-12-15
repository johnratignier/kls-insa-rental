<!-- <link rel="stylesheet" href="jquery/dist/css/pignose.calendar.css">
<script src="jquery/demo/js/moment.latest.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/semantic.ui.min.js"></script>
<script type="text/javascript" src="jquery/demo/js/prism.min.js"></script>
<script src="jquery/dist/js/pignose.calendar.full.js"></script>-->
<script>
	$(document).ready(function() {
		$('.calendar').pignoseCalendar({
			lang : 'fr',
			week: 1,
			disabledDates : [${requestScope.listedate}],
			scheduleOptions : {
				colors : {
					offer : '#2fabb7',
					ad : '#5c6270'
				}
			},
			select : function(date, context) {
				console.log('events for this date', context.storage.schedules);
			}
		});
	});
</script>

<div class="col-sm-12 col-md-4" id="detail_materiel">
	<div id="idd" class="card">
		<div class="card-img">
			<img src="${requestScope.materielCurrent.getPhoto_materiel()}" alt="Photo materiel"
			class="img-fluid col-md-12 p-5 mr-3 mh-75">
		</div>
		<div class="card-body">
			<h4 class="card-title text-justify" id="titre_materiel">${requestScope.materielCurrent.getNom_materiel()}</h4>
			<h4 class="card-text text-right"><span id="prix_materiel">${requestScope.materielCurrent.getPrix_materiel()}</span> &euro;</h4>
		</div>
		<ul class="list-group list-group-flush">
			<li class="list-group-item">${requestScope.materielCurrent.getDescription_materiel()} <br>Caution: ${requestScope.materielCurrent.getCaution_materiel()} &euro;
			</li>
			<li class="list-group-item">Type: <div class="badge badge-primary" role="alert">${requestScope.materielCurrent.getType_materiel().getIntitule_type_materiel()}</div> Etat:
				<div class="badge badge-primary" role="alert">${requestScope.materielCurrent.getEtat_materiel().getIntitule_etat_materiel()}</div> R&#233;f&#233;rence: <div class="badge badge-secondary" role="alert">${requestScope.materielCurrent.getId_materiel()}</div>
			</li>
		</ul>
		<div class="calendar pb-2 pt-3"></div>
		<div class="card-body"></div>
	</div>
</div>