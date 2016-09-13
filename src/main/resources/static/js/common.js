/**
 * 
 */

$(document).ready(function() {
	// datepicker作成
	$('.datepicker').pickadate({
		selectMonths: true, // Creates a dropdown to control month
		selectYears: 15, // Creates a dropdown of 15 years to control year
		format: 'yyyy/mm/dd',
		formatSubmit: 'yyyy/mm/dd',
		closeOnSelect: true
	});
	// セレクトボックス作成
	$('select').material_select();

	// モーダル作成
	$('.modal-trigger').leanModal();
	
});
