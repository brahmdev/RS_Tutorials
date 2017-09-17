<div class="ct-site--map ct-u-backgroundGradient">
	<div class="container">
		<div class="ct-u-displayTableVertical text-capitalize">
			<div class="ct-u-displayTableCell">
				<span class="ct-u-textNormal">Student Wizard</span>
			</div>
			<div class="ct-u-displayTableCell text-right">
				<span class="ct-u-textNormal ct-u-textItalic"> <a
					href="index.html">Admin</a> / Add Student<a href="#"></a>
				</span>
			</div>
		</div>
	</div>
</div>
<br>

<div class="container">
        <br />
        <form action="#" id="myForm" role="form" data-toggle="validator" method="post" accept-charset="utf-8">
                    
        <!-- SmartWizard html -->
        <div id="smartwizard">
            <ul>
                <li><a href="#step-1">Step 1<br /><small>Personal Details</small></a></li>
                <li><a href="#step-2">Step 2<br /><small>Academic Details</small></a></li>
                <li><a href="#step-3">Step 3<br /><small>Fees Details</small></a></li>
                <li><a href="#step-4">Step 4<br /><small>Terms and Conditions</small></a></li>
            </ul>
            
            <div>
                <div id="step-1">
                    <h3>Student Personal Details</h3>
                    <hr>
                    <div id="form-step-0" role="form" data-toggle="validator">
                        <div class="form-group">
                        	<label for="firstName">First Name:</label>
                            <input type="text" class="form-control" name="firstName" id="firstName" placeholder="First Name" required>
                            <div class="help-block with-errors"></div>
                            
                           <label for="lastName">Last Name:</label>
                            <input type="text" class="form-control" name="lastName" id="lastName" placeholder="Last Name">
                            <div class="help-block with-errors"></div>
                            
                           <label for="email">Email address:</label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="Email Address" required>
                            <div class="help-block with-errors"></div>
                            
                           <label for="postalAddress">Postal Address:</label>
                           <textarea class="form-control" name="address" id="address" rows="5" placeholder="Postal Address..." required></textarea>
                            <div class="help-block with-errors"></div>
                            
                        	<label for="mobile">Mobile:</label>
                            <input type="text" class="form-control" name="mobile" id="mobile" placeholder="Mobile Number" required>
                            <div class="help-block with-errors"></div>
                            
                            <label for="dob">DOB:</label>
                            <input type="date" class="form-control" name="dob" id="dob" placeholder="Date Of Birth" required>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    
                </div>
                <div id="step-2">
                    <h3>Students Academic Details</h3>
                    <div id="form-step-1" role="form" data-toggle="validator">
                        <div class="form-group padding10px">
                            <label for="board">Board:</label>
                            <select class="form-control" name="board" id="board" required></select>
                            <div class="help-block with-errors"></div>
                            
                            <label for="classLevel">Class Level:</label>
                            <select class="form-control" name="classLevel" id="classLevel" required></select>
                            <div class="help-block with-errors"></div>
                            
                            <label for="standard">Standard:</label>
                            <select class="form-control" name="standard" id="standard" required></select>
                            <div class="help-block with-errors"></div>
                            
                            <label for="language">Language:</label>
                            <select class="form-control" name="language" id="language" required></select>
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                </div>
                <div id="step-3">
                    <h3>Student Fees Details</h3>
                    <div id="form-step-2" role="form" data-toggle="validator">
                        <div class="form-group">
                        
                        	<label for="totalFees">Total Fees for Selected Class</label>
                            <input type="text" class="form-control" name="totalFees" id="totalFees" disabled="disabled">
                            <div class="help-block with-errors"></div>
                            
                            <label for="feesPaid">Fees Paid on Admission</label>
                            <input type="text" class="form-control" name="feesPaid" id="feesPaid"  placeholder="Fees Paid on Admission" required>
                            <div class="help-block with-errors"></div>
                            
                            <label for="feesRemaining">Fees Remaining</label>
                            <input type="text" class="form-control" name="feesRemaining" id="feesRemaining" disabled="disabled">
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                </div>
                <div id="step-4" class="">
                    <h3>Terms and Conditions</h3>
                    <p>
                        Terms and conditions: Keep your smile :) 
                    </p>
                    <div id="form-step-3" role="form" data-toggle="validator">
                        <div class="form-group">
                            <label for="terms">I agree with the T&amp;C</label>
                            <input type="checkbox" id="terms" data-error="Please accept the Terms and Conditions" required>  
                            <div class="help-block with-errors"></div>
                        </div>
                    </div>
                    
                    
                </div>
            </div>
        </div>
        
        </form>
        
    </div>
    
<!-- Include jQuery Validator plugin -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/1000hz-bootstrap-validator/0.11.5/validator.min.js"></script>

<!-- Include SmartWizard JavaScript source -->
<script type="text/javascript" src="js/jquery.smartWizard.js"></script>

<script type="text/javascript" src="js/addStudent.js"></script>

