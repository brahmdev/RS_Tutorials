<div class="ct-site--map ct-u-backgroundGradient">
	<div class="container">
		<div class="ct-u-displayTableVertical text-capitalize">
			<div class="ct-u-displayTableCell">
				<span class="ct-u-textNormal"> Contact Us </span>
			</div>
			<div class="ct-u-displayTableCell text-right">
				<span class="ct-u-textNormal ct-u-textItalic"> <a
					href="index.html">Home</a> / <a href="#">Contact Us</a>
				</span>
			</div>
		</div>
	</div>
</div>
<section class="ct-u-backgroundLightGreen">
    <div id="map" style="width:100%;height:500px;background:yellow"></div>
	<div class="ct-u-paddingBoth100">
		<div class="container">
			<div class="row">
				<div class="col-md-8">
					<h4
						class="text-uppercase ct-fw-700 ct-u-marginBottom100 ct-u-textNormal">Leave
						a reply</h4>
					<div class="successMessage alert alert-success ct-u-marginTop20"
						style="display: none;">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true"></button>
						Message Sent.
					</div>
					<div class="errorMessage alert alert-danger ct-u-marginTop20"
						style="display: none;">
						<button type="button" class="close" data-dismiss="alert"
							aria-hidden="true"></button>
						An error occured. Please try again later.
					</div>
					<form action="assets/form/send.php" method="POST"
						class="validateIt">
						<div class="row">
							<div class="col-sm-6">
								<div class="form-group ct-u-marginBottom30">
									<input id="contact_fname" data-error-message="First Name"
										placeholder="First Name" type="text" required=""
										name="field[]" class="form-control ct-input--type1 input-hg"
										title="First Name"> <label for="contact_fname"
										class="sr-only"></label>
								</div>
							</div>
							<div class="col-sm-6">
								<div class="form-group ct-u-marginBottom30">
									<input id="contact_lname" data-error-message="Last Name"
										placeholder="Last Name" required="" name="field[]"
										type="email"
										class="form-control ct-input--type1 input-hg h5-email"
										title="Last Name"> <label for="contact_lname"
										class="sr-only"></label>
								</div>
							</div>
						</div>
						<div class="form-group  ct-u-marginBottom30">
							<input id="contact_phone" placeholder="contact_phone"
								name="field[]" type="tel"
								class="form-control h5-phone ct-input--type1 input-hg"
								title="Phone"> <label for="contact_phone"
								class="sr-only"></label>
						</div>
						<div class="form-group  ct-u-marginBottom30">
							<textarea id="contact_message"
								data-error-message="Message is required"
								placeholder="Your Message" class="form-control ct-input--type1"
								rows="6" name="field[]" required="" title="Message"></textarea>
							<label for="contact_message" class="sr-only"></label>
						</div>

						<button type="submit" class="btn btn-primary btn-lg pull-right">
							<span>Send Message</span>
						</button>
						<div class="clearfix"></div>
					</form>
				</div>
				<div class="col-md-4">
					<div class="ct-addressInformation">
						<h4
							class="text-uppercase ct-fw-700 ct-u-marginBottom20 ct-u-textNormal">Headquarters
							:</h4>
						<p class="ct-u-marginBottom20">C/7, Balaji Bhavan, First
							floor, Kajupada Safedpool pipe line, sakinaka, Mumbai - 400072,
							2min from st. Jude and Eden high school</p>
						<h4
							class="text-uppercase ct-fw-700 ct-u-marginBottom20 ct-u-textNormal">Phone
							:</h4>
						<span>Phone Number: +(91)-8976303528, 9930611247</span>
						<h4
							class="text-uppercase ct-fw-700 ct-u-marginBottom20 ct-u-textNormal">Email
							:</h4>
						<span>Support: <a href="#">rhlsingh432@gmail.com</a></span> <span>Support:
							<a href="#">pandey.dev25@gmail.com </a>
						</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script>
function myMap() {
/*center: new google.maps.LatLng(19.094947, 72.885728),*/
var mapOptions = {
    center: new google.maps.LatLng(19.094633, 72.885717),
    zoom: 18,
    mapTypeId: google.maps.MapTypeId.roadmap
}
var map = new google.maps.Map(document.getElementById("map"), mapOptions);
}
</script>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBUFaDZad_wg5SsYzDwiGgWBY5HtwI27bM&callback=myMap"></script>
<!--
To use this code on your website, get a free API key from Google.
Read more at: https://www.w3schools.com/graphics/google_maps_basic.asp
-->