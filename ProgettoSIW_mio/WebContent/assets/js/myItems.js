/**
 * 
 */

function confirmDelete() {
	$('#delete').click(function() {
		return false;
	}).dblclick(function() {
		window.location = this.href;
		return false;
	});
}

function init() {
	confirmDelete();
}

$(document).ready(init);