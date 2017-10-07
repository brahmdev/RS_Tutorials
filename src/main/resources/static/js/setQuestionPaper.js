var editor; // use a global for the submit and return data rendering in the
// examples
var table;
var boardsDropDownJSON = null;
var classLevelDropDownJSON = null;
var classLevelTypeDropDownJSON = null;
var nextIdToGenerate = 0;
$(document).ready(function() {
	var token = $("meta[name='_csrf']").attr("content");
	$.ajaxSetup({
		beforeSend : function(xhr) {
			xhr.setRequestHeader('X-CSRF-TOKEN', token);
		}
	});

	$( "#board" ).change(function() {
		  var selectedValue = $('#board').find(":selected")[0].value;
		  getClassNameDropDown(selectedValue);
	});
	
	$.ajax({
		type : 'POST',
		url : 'getBoards.do',
		contentType : 'application/json',
		dataType : 'json',
		data : {
			'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr("content")
		},
		error : function() {
			$('#info').html('<p>An error has occurred</p>');
		},
		success : function(data) {
			boardsDropDownJSON = $.parseJSON(JSON.stringify(data));
			
			
			$.each(boardsDropDownJSON, function (i, item) {
			    $('#board').append($('<option>', { 
			        value: item.value,
			        text : item.label 
			    }));
			});
		},
	});

	function getClassNameDropDown(selectedValue) {
		$.ajax({
			type : 'POST',
			url : 'getClassNameListByBoard.do',
			contentType : 'application/json',
			dataType : 'json',
			data : {
				'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr("content"),
				'keyToSearch' : selectedValue
			},
			error : function() {
				$('#info').html('<p>An error has occurred</p>');
			},
			success : function(data) {
				classLevelTypeDropDownJSON = $.parseJSON(JSON.stringify(data));
				$.each(classLevelTypeDropDownJSON, function (i, item) {
				    $('#standard').append($('<option>', { 
				        value: item.value,
				        text : item.label 
				    }));
				});
			}
		});
	}

});
