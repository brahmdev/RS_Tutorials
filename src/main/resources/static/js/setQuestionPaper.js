var editor; // use a global for the submit and return data rendering in the
// examples
var table;
var boardsDropDownJSON = null;
var classLevelDropDownJSON = null;
var classLevelTypeDropDownJSON = null;
var nextIdToGenerate = 0;
$(document).ready(function() {

    $('#board').append($('<option>Select</option>'));
    $('#standard').append($('<option>Select</option>'));
	$('#subject').append($('<option>Select</option>'));
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

	$( "#standard" ).change(function() {
    	var selectedValue = $('#standard').find(":selected")[0].value;
    	getSubjectDropDown(selectedValue);
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
			$('#board').empty().append('<option>Select</option>');
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
			data : {
				csrfmiddlewaretoken : $("meta[name='_csrf']").attr("content"),
				keyToSearch : selectedValue
			},
			dataType : 'json',
			error : function() {
				$('#info').html('<p>An error has occurred</p>');
			},
			success : function(data) {
				classLevelTypeDropDownJSON = $.parseJSON(JSON.stringify(data));
				$('#standard').empty().append('<option>Select</option>');
				$.each(classLevelTypeDropDownJSON, function (i, item) {
				    $('#standard').append($('<option>', { 
				        value: item.value,
				        text : item.label 
				    }));
				});
			}
		});
	}

	function getSubjectDropDown(selectedValue) {
    		$.ajax({
    			type : 'POST',
    			url : 'getSubjectNameListByStandard.do',
    			data : {
    				csrfmiddlewaretoken : $("meta[name='_csrf']").attr("content"),
    				keyToSearch : selectedValue
    			},
    			dataType : 'json',
    			error : function() {
    				$('#info').html('<p>An error has occurred</p>');
    			},
    			success : function(data) {
    				subjectDropDownJSON = $.parseJSON(JSON.stringify(data));
    				$('#subject').empty().append('<option>Select</option>');
    				$.each(subjectDropDownJSON, function (i, item) {
    				    $('#subject').append($('<option>', {
    				        value: item.value,
    				        text : item.label
    				    }));
    				});
    			}
    		});
    	}

});
