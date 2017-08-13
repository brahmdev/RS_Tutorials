<div class="ct-site--map ct-u-backgroundGradient">
	<div class="container">
		<div class="ct-u-displayTableVertical text-capitalize">
			<div class="ct-u-displayTableCell">
				<span class="ct-u-textNormal">Staff Wizard</span>
			</div>
			<div class="ct-u-displayTableCell text-right">
				<span class="ct-u-textNormal ct-u-textItalic"> <a
					href="index.html">Admin</a> / Add Staff<a href="#"></a>
				</span>
			</div>
		</div>
	</div>
</div>
<br>

<div class="container">
	<br />
	<form action="#" id="myForm" role="form" data-toggle="validator"
		method="post" accept-charset="utf-8">

		<!-- SmartWizard html -->
		<div id="smartwizard">
			<ul>
				<li><a href="#step-1">Step 1<br /> <small>Personal Details</small></a></li>
				<li><a href="#step-2">Step 2<br /> <small>Roles/Payment Details</small></a></li>
				<li><a href="#step-4">Step 4<br /> <small>Terms	&amp; Conditions</small></a></li>
			</ul>

			<div>
				<div id="step-1">
					<h3>Staff Personal Details</h3>
					<hr>
					<div id="form-step-0" role="form" data-toggle="validator">
						<div class="form-group">
							<label for="firstName">First Name:</label> <input type="text"
								class="form-control" name="firstName" id="firstName"
								placeholder="First Name" required>
							<div class="help-block with-errors"></div>

							<label for="lastName">Last Name:</label> <input type="text"
								class="form-control" name="lastName" id="lastName"
								placeholder="Last Name">
							<div class="help-block with-errors"></div>

							<label for="email">Email address:</label> <input type="email"
								class="form-control" name="email" id="email"
								placeholder="Email Address" required>
							<div class="help-block with-errors"></div>

							<label for="postalAddress">Postal Address:</label>
							<textarea class="form-control" name="postalAddress"
								id="postalAddress" rows="5" placeholder="Postal Address..."
								required></textarea>
							<div class="help-block with-errors"></div>

							<label for="mobile">Mobile:</label> <input type="text"
								class="form-control" name="mobile" id="mobile"
								placeholder="Mobile Number" required>
							<div class="help-block with-errors"></div>

							<label for="dob">DOB:</label> <input type="date"
								class="form-control" name="dob" id="dob"
								placeholder="Date Of Birth" required>
							<div class="help-block with-errors"></div>
						</div>
					</div>

				</div>
				<div id="step-2">
					<h3>Staff Role/Payment Details</h3>
					<div id="form-step-1" role="form" data-toggle="validator">
						<div class="form-group">
							<label for="role">Role:</label> <select class="form-control"
								name="role" id="role" required></select>
							<div class="help-block with-errors"></div>

							<label for="charges">Payment Per Hour:</label> <select
								class="form-control" name="charges" id="charges" required></select>
							<div class="help-block with-errors"></div>

							<br>
							<fieldset>
								<legend>Teacher Configuration <button class="btn btn-primary add_config_button">Add More Configuration</button></legend>
								<div class="input_fields_wrap">
									<fieldset class="majorpoints_1">
										<legend class="majorpointslegend"><span class="expand">Teacher Class Detail:1 (Click me to see Detail)</span>
											    
										</legend>
										<div class="hiders" style="display: none">
											<div class="form-group padding10px">
					                            <label for="board_1">Board:</label>
					                            <select class="form-control" name="board_1" id="board_1" required></select>
					                            <div class="help-block with-errors"></div>
					                            
					                            <label for="classLevel_1">Class Level:</label>
					                            <select class="form-control" name="classLevel_1" id="classLevel_1" required></select>
					                            <div class="help-block with-errors"></div>
					                            
					                            <label for="standard_1">Standard:</label>
					                            <select class="form-control" name="standard_1" id="standard_1" required></select>
					                            <div class="help-block with-errors"></div>
					                            
					                            <label for="language_1">Language:</label>
					                            <select class="form-control" name="language_1" id="language_1" required></select>
					                            <div class="help-block with-errors"></div>
					                            
					                            <label for="subject_1">Subject:</label>
					                            <select class="form-control" name="subject_1" id="subject_1" required></select>
					                            <div class="help-block with-errors"></div>
					                            
					                            <label for="chapter_1">Chapter:</label>
					                            <select class="form-control" name="chapter_1" id="chapter_1" required></select>
					                            <div class="help-block with-errors"></div>
					                        </div>
										</div>
									</fieldset>
								</div>
							</fieldset>

						</div>
					</div>
				</div>
				<div id="step-3">
					<h3>Staff Role Details</h3>
					<div id="form-step-2" role="form" data-toggle="validator">
						<div class="form-group">

							<label for="totalFees">Total Fees for Selected Class</label> <input
								type="text" class="form-control" name="totalFees" id="totalFees"
								disabled="disabled">
							<div class="help-block with-errors"></div>

							<label for="feesPaid">Fees Paid on Admission</label> <input
								type="text" class="form-control" name="feesPaid" id="feesPaid"
								placeholder="Fees Paid on Admission" required>
							<div class="help-block with-errors"></div>

							<label for="feesRemaining">Fees Remaining</label> <input
								type="text" class="form-control" name="feesRemaining"
								id="feesRemaining" disabled="disabled">
							<div class="help-block with-errors"></div>
						</div>
					</div>
				</div>
				<div id="step-4" class="">
					<h3>Terms and Conditions</h3>
					<p>Terms and conditions: Keep your smile :)</p>
					<div id="form-step-3" role="form" data-toggle="validator">
						<div class="form-group">
							<label for="terms">I agree with the T&amp;C</label> <input
								type="checkbox" id="terms"
								data-error="Please accept the Terms and Conditions" required>
							<div class="help-block with-errors"></div>
						</div>
					</div>


				</div>
			</div>
		</div>

	</form>

</div>

<!-- Include jQuery Validator plugin -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>

<!-- Include SmartWizard JavaScript source -->
<script type="text/javascript" src="js/jquery.smartWizard.js"></script>

<script type="text/javascript" src="js/addStaff.js"></script>

