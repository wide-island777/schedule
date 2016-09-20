/**
 * todo画面用js
 */
$(document).ready(function() {
	$('#todoDelete').click(deleteTodo());

	$('.delete-modal-trigger').click(function() {
		$('deleteId').val($(this).data('id'));
	});
});

function deleteTodo() {
	var deleteId = $('#deleteId').val();

	$.ajax({
		type : 'delete',
		url : '/schedule/todo/delete',
		data : {
			id : deleteId
		}
	}).done();
}

// モーダル出すときに、hiddenかなにかで値をセットする。
