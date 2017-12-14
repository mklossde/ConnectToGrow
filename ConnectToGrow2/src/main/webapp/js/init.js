(function($){
  $(function(){

    $('.button-collapse').sideNav();
    // init selectboxes
    $('select').material_select();
    //init datepicker
    $('.datepicker').pickadate({
	    selectMonths: true, // Creates a dropdown to control month
	    selectYears: 15, // Creates a dropdown of 15 years to control year,
	    today: 'Today',
	    clear: 'Clear',
	    close: 'Ok',
	    closeOnSelect: false // Close upon selecting a date,
	  });

    // progress bars
	$(function() {
		$('progress').each(function() {
			var max = $(this).val();
			$(this).val(0).animate({ value: max }, { duration: 2000, easing: 'easeOutCirc' });
		});
	});

	$( ".ctg-save-story" ).click(function() {
		$(this).closest( "article" ).find(" .ctg-article-like ").show();
	});

	$( ".ctg-button-match-like" ).click(function() {
		$(this).closest( ".card-content" ).find(" .ctg-match-like ").show();
	});

	/*
	$( ".ctg-use-case-show-full-article" ).click(function() {
		$(this).closest( "article" ).find(" .ctg-article-content ").slideToggle();
		$(this).find("i").html("expand_less");
		$(this).html("<i class='large material-icons right'>expand_less</i>schliessen");
	});
	*/

	// init modal
	$(document).ready(function(){
		// the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
		$('.modal').modal();
	});

  }); // end of document ready
})(jQuery); // end of jQuery name space