$(document).ready(function() {

    $('.calendar').pignoseCalendar({
	lang : 'fr',
	week: 1,
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

    $(".panelMateriel").height($(window).height()-35);

    $(window).resize(function() {
	var h = $(window).height();
	$(".panelMateriel").height(h-35);
    });
});