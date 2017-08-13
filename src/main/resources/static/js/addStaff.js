$(document).ready(
		function() {

			var max_fields = 10; // maximum input boxes allowed
			var wrapper = $(".input_fields_wrap"); // Fields wrapper
			var add_button = $(".add_config_button"); // Add button ID

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
							elmForm.submit();
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
				theme : 'dots',
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

			//$('.expand').click(function() {
			$(wrapper).on("click",".expand", function(e){ //user click on remove text
				debugger;
				var indexValue = $(this).parent().parent()[0].className.split('_')[1];
				$('.majorpoints_'+indexValue).find('.hiders').slideToggle(500);
			});

			var x = 1; //initlal text box count
		    $(add_button).click(function(e){ //on add input button click
		        e.preventDefault();
		        if(x < max_fields){ //max input box allowed
		            x++; //text box increment
		            $(wrapper).append('<div class="marginTop10px secondaryConfiguration_'+x+'"><fieldset class=majorpoints_'+ x +'><legend class=majorpointslegend><span class=expand>Teacher Class Detail:' + x + ' (Click me to see Detail)</span> <button class="remove_config_button btn btn-danger">Remove Configuration</button></legend><div class=hiders style=display:none><div class="form-group padding10px"><label for=board'+ x +'>Board:</label><select class=form-control id=board'+ x +' name=board required></select><div class="help-block with-errors"></div><label for=classLevel'+ x +'>Class Level:</label><select class=form-control id=classLevel'+ x +' name=classLevel required></select><div class="help-block with-errors"></div><label for=standard'+ x +'>Standard:</label><select class=form-control id=standard'+ x +' name=standard required></select><div class="help-block with-errors"></div><label for=language'+ x +'>Language:</label><select class=form-control id=language'+ x +' name=language required></select><div class="help-block with-errors"></div><label for=subject'+ x +'>Subject:</label><select class=form-control id=subject'+ x +' name=subject required></select><div class="help-block with-errors"></div><label for=chapter'+ x +'>Chapter:</label><select class=form-control id=chapter'+ x +' name=chapter required></select><div class="help-block with-errors"></div></div></div></fieldset></div>'); //add input box
		        }
		    });
		    
		    $(wrapper).on("click",".remove_config_button", function(e){ //user click on remove text
		        e.preventDefault(); 
		        $(this).parent().parent().remove(); 
		        //x--;
		    })
		});