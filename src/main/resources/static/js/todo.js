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
			// データ削除成功したらTODOカードを削除する
			$('.todo-card[data-id="' + deleteId + '"]').remove();
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

	/**
	$('#todoEntry').validate({
		reles: {
			name: {
				required: true,
				maxlength: 50
			},
			place: {
				maxlength: 100
			},
			status: {
				required: true,
				maxlength: 5
			}
			
		},
		errorElement: 'div',
		errorPlacement: function(error, element) {
			var placement = $(element).data('error');
			if (placement) {
				$(placement).append(error)
			} else {
				error.insertAfter(element)
			}
		}
	});
	*/
	
	// 登録モーダル作成
	$('.modal-trigger').leanModal({
		dismissible: false
	});
	
	$('#entryCancel').on('click', function() {
		$('.modal-trigger').closeModal();
	})
	


});

// モーダル出すときに、hiddenかなにかで値をセットする。
