$(document).ready(function() {
    
    $(".panelMateriel").height($(window).height()-35);

    $(".AJAX_detail_materiel").click(function() {
	var idMaterielShow = $(this).parents(".image_conteneur").find(".AJAX_id_materiel").text();

	$.ajax({
	    url : 'materiel',
	    type : 'GET',
	    async : false,
	    data : 'id=' + idMaterielShow,
	    dataType : 'html',
	    timeout : 4000,
	    success : function(data) {
		console.log("SUCCES");
		$(data).replaceAll("#detail_materiel_content");
	    },
	    error : function() {
		console.log("ECHEC");
	    }
	});
    });

    $(".AJAX_bouton_reserver").click(function(){
	var idMaterielShow = $(this).parents(".bouton_reserver_conteneur").find(".AJAX_id_materiel").text();
	$.ajax({
	    url : 'materiel',
	    type : 'GET',
	    async : false,
	    data : 'id=' + idMaterielShow,
	    dataType : 'html',
	    timeout : 4000,
	    success : function(data) {
		console.log("SUCCES");
		$(data).replaceAll("#detail_materiel_content");
	    },
	    error : function() {
		console.log("ECHEC");
	    }
	});
	var titreT = $("#titre_materiel").text();
	var prixM = parseFloat($("#prix_materiel").text());
	var prixT = parseFloat($("#reservation_prix_total").text());
	console.log(prixT);
	$(this).toggleClass('btn-warning btn-primary');

	if ($(this).hasClass('btn-warning')) {
	    $(this).contents().replaceWith("Annuler");
	    var prixN = prixT + prixM;
	    $.ajax({
		url : 'nouvellereservation',
		type : 'GET',
		async : false,
		data : 'plus=' + idMaterielShow,
		dataType : 'html',
		timeout : 4000,
		success : function(data) {
		    console.log("SUCCES");
		},
		error : function() {
		    console.log("ECHEC");
		}
	    });
	    $("#reservation_prix_total").contents().replaceWith(prixN.toFixed(2));
	    $("#reservation_selection_materiel").append("<span class=\"d-block\" id=\"reservation_titre_materiel_".concat(idMaterielShow).concat("\">").concat(titreT).concat("</span>"));
	} else {
	    var prixN = prixT - prixM;
	    $(this).contents().replaceWith("Réserver");
	    $.ajax({
		url : 'nouvellereservation',
		type : 'GET',
		async : false,
		data : 'moins=' + idMaterielShow,
		dataType : 'html',
		timeout : 4000,
		success : function(data) {
		    console.log("SUCCES");
		},
		error : function() {
		    console.log("ECHEC");
		}
	    });
	    $("#reservation_prix_total").contents().replaceWith(prixN.toFixed(2));
	    $("#reservation_titre_materiel_".concat(idMaterielShow)).remove();
	}
	return false;
    });
    
    $("#choix_affichage_non_louable").click(function(){
	if($(this).attr("checked")){
	    console.log("jjj");
	    $(this).removeAttr("checked")
	    $(this).css("background-color","red");
	    $(".materiel_item_non_louable").toggleClass("d-block d-none");
	}
    });
    $("#choix_affichage_deja_reserve").change(function(){
	if($(this).attr("checked")){
	    $(".materiel_item_deja_reserve").toggleClass("d-block d-none");
	}
    });
    $("#choix_affichage_indisponible").change(function(){
	if($(this).attr("checked")){
	    $(".materiel_item_indisponible").toggleClass("d-block d-none");
	}
    });

    $(window).resize(function() {
	var h = $(window).height();
	$(".panelMateriel").height(h-35);
    });
});