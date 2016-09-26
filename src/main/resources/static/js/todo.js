/**
 * todo画面用js
 */
$(document).ready(function() {

	$('.delete-modal-trigger').leanModal();

	$('#todoDelete').on('click', function() {
		var deleteId = $('#deleteId').val();

		$.ajax({
			type : 'DELETE',
			url : '/schedule/api/todo/delete/' + deleteId,
			cache : false,
		}).done(function(data, textStatus, jqXHR) {
			Materialize.toast('complate!!!', 3000, 'rounded')
		}).fail(function(data, textStatus, errorThrown) {
			Materialize.toast('miss!!!', 3000, 'rounded')
		}).always(function() {
			$('#deleteId').val('');
		});
	});

	$('.delete-modal-trigger').on('click', function() {
		$('#deleteId').val($(this).data('id'));
	});

	$('#deleteCancel').on('click', function() {
		$('#deleteId').val('');
	});

});

// モーダル出すときに、hiddenかなにかで値をセットする。
