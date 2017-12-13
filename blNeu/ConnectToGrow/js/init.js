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

  }); // end of document ready
})(jQuery); // end of jQuery name space