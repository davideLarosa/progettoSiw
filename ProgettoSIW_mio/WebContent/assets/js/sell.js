/**
 * 
 */

function singleCheckbox() {
	$('input[type="checkbox"]').on('change', function() {
		if ($('input[type="checkbox"]').not(!this).prop('checked', true)) {
			$('input[type="checkbox"]').not(this).prop('checked', false);
		}
	});

}

function init() {
	$("a[rel='prettyPhoto[galleria]']").prettyPhoto(); 
	singleCheckbox();
}

$(document).ready(init);