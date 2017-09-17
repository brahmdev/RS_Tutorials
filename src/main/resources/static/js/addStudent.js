var boardsDropDownJSON = null;
var classLevelDropDownJSON = null;
var classLevelTypeDropDownJSON = null;
var nextIdToGenerate = 0;

$(document).ready(
		function() {

			var token = $("meta[name='_csrf']").attr("content");
			$.ajaxSetup({
				beforeSend : function(xhr) {
					xhr.setRequestHeader('X-CSRF-TOKEN', token);
				}
			});
			
			$.ajax({
				type : 'POST',
				url : 'getBoards.do',
				contentType : 'application/json',
				dataType : 'json',
				data : {
					'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr(
							"content")
				},
				error : function() {
					$('#info').html('<p>An error has occurred</p>');
				},
				success : function(data) {
					boardsDropDownJSON = $.parseJSON(JSON.stringify(data));
					console.log(boardsDropDownJSON);
					$.each(boardsDropDownJSON, function (i, item) {
					    $('#board').append($('<option>', { 
					        value: item.value,
					        text : item.label 
					    }));
					});
					//$("#board").append(boardsDropDownJSON);
					getClassLevelDropDown();
				},
			});
			function getClassLevelDropDown() {
				$.ajax({
					url : 'getClassLevel.do',
					contentType : 'application/json',
					dataType : 'json',
					data : {
						'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr(
								"content")
					},
					error : function() {
						$('#info').html('<p>An error has occurred</p>');
					},
					success : function(data) {
						classLevelDropDownJSON = $.parseJSON(JSON.stringify(data));
						console.log(classLevelDropDownJSON);
						$.each(classLevelDropDownJSON, function (i, item) {
						    $('#classLevel').append($('<option>', { 
						        value: item.value,
						        text : item.label 
						    }));
						});
						getClassNameDropDown();
					},
					type : 'POST'
				});
			}
			
			function getClassNameDropDown() {
				$.ajax({
					type : 'POST',
					url : 'getClassNameList.do',
					contentType : 'application/json',
					dataType : 'json',
					data : {
						'csrfmiddlewaretoken' : $("meta[name='_csrf']").attr(
								"content")
					},
					error : function() {
						$('#info').html('<p>An error has occurred</p>');
					},
					success : function(data) {
						classLevelTypeDropDownJSON = $.parseJSON(JSON.stringify(data));
						console.log(classLevelTypeDropDownJSON);
						$.each(classLevelTypeDropDownJSON, function (i, item) {
						    $('#standard').append($('<option>', { 
						        value: item.value,
						        text : item.label 
						    }));
						});
						$("#language").append('<option value=Hindi>Hindi</option><option value=English>English</option>');
					}
				});
			}
			
			// Toolbar extra buttons
			var btnFinish = $('<button></button>').text('Finish').addClass(
					'btn btn-info').on('click', function() {
				if (!$(this).hasClass('disabled')) {
					var elmForm = $("#myForm");
					if (elmForm) {
						elmForm.validator('validate');
						var elmErr = elmForm.find('.has-error');
						if (elmErr && elmErr.length > 0) {
							alert('Oops we still have error in the form');
							return false;
						} else {
							alert('Great! we are ready to submit form');
							debugger;
							//elmForm.submit();
							var jsonToSubmit = JSON.stringify($('#myForm').serializeArray());
							submitForm($('#myForm').serializeArray());
							return false;
						}
					}
				}
			});
			
			
			var btnCancel = $('<button></button>').text('Cancel').addClass(
					'btn btn-danger').on('click', function() {
				$('#smartwizard').smartWizard("reset");
				$('#myForm').find("input, textarea").val("");
			});

			// Smart Wizard
			$('#smartwizard').smartWizard({
				selected : 0,
				theme : 'circles',
				transitionEffect : 'fade',
				toolbarSettings : {
					toolbarPosition : 'bottom',
					toolbarExtraButtons : [ btnFinish, btnCancel ]
				},
				anchorSettings : {
					markDoneStep : true, // add done css
					markAllPreviousStepsAsDone : true, // When a step selected
														// by url hash, all
														// previous steps are
														// marked done
					removeDoneStepOnNavigateBack : true, // While navigate
															// back done step
															// after active step
															// will be cleared
					enableAnchorOnDoneStep : true
				// Enable/Disable the done steps navigation
				}
			});

			$("#smartwizard").on("leaveStep",
					function(e, anchorObject, stepNumber, stepDirection) {
						if(stepNumber == 1 && stepDirection === 'forward') {
							var boardId = $("#board")[0].value;
							var classLevelId = $("#classLevel")[0].value;
							var standard = $("#standard")[0].value;
							var language = $("#language")[0].value;
							var fees = 0;
							$.ajax({
								type : "POST",
								contentType : "application/json",
								url : "getFees.do",
								data : "{\"boardId\":\"" + boardId + "\", \"classLevelId\":\"" + classLevelId + "\", \"standard\":\"" + standard+ "\", \"language\":\"" + language + "\"}",
								dataType : 'json',
								timeout : 100000,
								success : function(data) {
									console.log("SUCCESS: ", data);
									debugger;
									$("#totalFees").val(data.fees);
									//display(data);
								},
								error : function(e) {
									console.log("ERROR: ", e);
									//display(e);
								},
								done : function(e) {
									console.log("DONE");
								}
							});
						}
						var elmForm = $("#form-step-" + stepNumber);
						// stepDirection === 'forward' :- this condition allows
						// to do the form validation
						// only on forward navigation, that makes easy
						// navigation on backwards still do the validation when
						// going next
						if (stepDirection === 'forward' && elmForm) {
							elmForm.validator('validate');
							var elmErr = elmForm.children('.has-error');
							if (elmErr && elmErr.length > 0) {
								// Form validation failed
								return false;
							}
						}
						return true;
					});

			$("#smartwizard").on("showStep",
					function(e, anchorObject, stepNumber, stepDirection) {
						// Enable finish button only on last step
						if (stepNumber == 3) {
							$('.btn-finish').removeClass('disabled');
						} else {
							$('.btn-finish').addClass('disabled');
						}
					});
			
			$( "#feesPaid" ).keyup(function() {
				var feesPaid = $(this).val();
				var totalFees = $("#totalFees").val();
				if(parseInt(feesPaid) > parseInt(totalFees)) {
					alert("You can't paid more than TotalFees!");
					$("#feesPaid").val(0);
					$('#feesRemaining').removeAttr("disabled");
					$("#feesRemaining").val(totalFees);
					$('#feesRemaining').prop("disabled", true);
				} else {
					$("#feesRemaining").val(totalFees-feesPaid);
				}
			});

			function submitForm(jsonToSubmit) {
				$.ajax({
					type : "POST",
					contentType : "application/json",
					url : "addStudent.do",
					data : JSON.stringify(jsonToSubmit),
					dataType : 'json',
					timeout : 100000,
					success : function(data) {
						console.log("SUCCESS: ", data);
						//display(data);
					},
					error : function(e) {
						console.log("ERROR: ", e);
						//display(e);
					},
					done : function(e) {
						console.log("DONE");
					}
				});
			}
		});