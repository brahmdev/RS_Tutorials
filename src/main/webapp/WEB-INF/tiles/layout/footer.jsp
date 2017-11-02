<footer>
	<div class="ct-footer">
		<div class="container text-center">
			<ul class="list-unstyled list-inline ct-copyright">
						<li>Copyright 2017</li>
						<li>Created by <a style="color:#fff;" href="http://www.rstutorials.edu">RS
								Tutorials</a></li>
					</ul>
		</div>
	</div>
</footer>


<!-- <script src="js/main-compiled.js"></script> -->

<script>
	if (isDashBoard) {
		var body = document.getElementsByTagName('body')[0];
		var script = document.createElement('script');

		script.type = 'text/javascript';

		script.src = "js/main-compiled.js";
		body.appendChild(script);

	}
</script>
<script src="js/header.js"></script>
<!-- switcher -->
<script src="js/demo.js"></script>

<script type="text/javascript">
	$('head').append(
			'<link rel="stylesheet" type="text/css" href="css/demo.css">');
	/* $('head').append('<link rel="stylesheet" type="text/css" href="php/generator.php">'); */
	$('head').append(
			'<link rel="stylesheet" type="text/css" href="css/generator.css">');
</script>
<div id="stylechooser">
	<div class="easyBox flat">
		<h6 class="subTitle">Style Switcher</h6>
		<a href="#" class="entypo cancel-squared" id="styleToggle"><i
			class="fa fa-cogs"></i></a>
	</div>

	<div class="easyBox">

		<div class="mkSpace">
			<label>Main Color</label>
			<ul class="demoList clearfix">
				<li><a href="#" title="Green" data-value="Green" class="demoActive"><span
						class="demoColor" style="background: #99cd4d;"></span></a></li>
				<li><a href="#" data-value="Red"><span class="demoColor"
						style="background: #ce0000;"></span></a></li>
				<li><a href="#" data-value="Orange"><span class="demoColor"
						style="background: #ff7f00;"></span></a></li>
				<li><a href="#" data-value="Yellow"><span class="demoColor"
						style="background: #ffd600;"></span></a></li>
				<li><a href="#" data-value="Purple"><span
						class="demoColor" style="background: #ad53c1;"></span></a></li>
				<li><a href="#" data-value="Sun"><span class="demoColor"
						style="background: #ffb400;"></span></a></li>
				<li><a href="#" data-value="LightPink"><span
						class="demoColor" style="background: #00726d;"></span></a></li>
				<li><a href="#" data-value="LightBlue"><span
						class="demoColor" style="background: #2b8be9;"></span></a></li>

			</ul>
		</div>
		<hr>

		<div class="mkSpace">
			<a href="#" id="demoReset"
				class="btn ct-btn--o btn-default btn-block demo-reset"><span>DEFAULT</span></a>
		</div>
	</div>

	<div class="easyBox">
		<div class="mkSpace">
			<div class="title">
				<label>Dropdown Style</label>
			</div>
			<div class="switch-animations">
				<div class="select">
					<select id="dropdownSelect" onchange="selectDropdown()">
						<option value="ct-navbar--fadeInLeft">fadeInLeft</option>
						<option value="ct-navbar--fadeInRight">fadeInRight</option>
						<option value="ct-navbar--fadeIn">fadeIn</option>
						<option value="ct-navbar--fadeInDown">fadeInDown</option>
						<option value="ct-navbar--fadeInUp">fadeInUp</option>
						<option value="ct-navbar--pulse">pulse</option>
						<option value="ct-navbar--bounceIn">bounceIn</option>
						<option value="ct-navbar--bounceInLeft">bounceInLeft</option>
						<option value="ct-navbar--bounceInRight">bounceInRight</option>
						<option value="ct-navbar--flipInX">flipInX</option>
						<option value="ct-navbar--flipInY">flipInY</option>
						<option value="ct-navbar--zoomIn">zoomIn</option>
						<option value="ct-navbar--zoomInDown">zoomInDown</option>
						<option value="ct-navbar--zoomInUp">zoomInUp</option>
					</select>
				</div>
			</div>
		</div>
	</div>
	<!-- 
    <div class="easyBox">
        <div class="mkSpace">
        <div class=" title">
            <label>Body Layout</label>
        </div>
        <div class="switch-toggle">
            <div class="onoffswitch">
                <input type="checkbox" data-refresh="true" data-value="boxed" data-clear-container-deselected="#patternContainer" name="onoffswitch" class="onoffswitch-checkbox demoActive" id="myonoffswitch" checked="checked">
                <label class="onoffswitch-label" for="myonoffswitch">
                    <span class="onoffswitch-inner"></span>
                </label>
            </div>
            </div>
        </div>
    </div>

    <div class="easyBox">
        <div class="mkSpace">
        <div class="title">
            <label>Background Pattern Style</label>
        </div>
        <div id="patternContainer">

                <ul class="demoList clearfix">
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn1" data-value="ptn1"><span class="demoPattern" style="background: url('assets/images/bg_pattern_01.png')"></span></a></li>
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn2" data-value="ptn2"><span class="demoPattern" style="background: url('assets/images/bg_pattern_02.png')"></span></a></li>
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn3" data-value="ptn3"><span class="demoPattern" style="background: url('assets/images/bg_pattern_03.png')"></span></a></li>
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn4" data-value="ptn4"><span class="demoPattern" style="background: url('assets/images/bg_pattern_09.png')"></span></a></li>
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn5" data-value="ptn5"><span class="demoPattern" style="background: url('assets/images/bg_pattern_10.png')"></span></a></li>
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn6" data-value="ptn6"><span class="demoPattern" style="background: url('assets/images/bg_pattern_11.jpg')"></span></a></li>
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn7" data-value="ptn7"><span class="demoPattern" style="background: url('assets/images/bg_pattern_12.jpg')"></span></a></li>
                    <li><a data-refresh="true" data-dependent="#myonoffswitch:not(.demoActive)" href="#" class="refresh" title="ptn8" data-value="ptn8"><span class="demoPattern" style="background: url('assets/images/bg_pattern_12.png')"></span></a></li>
                </ul>
            </div>
        </div>
    </div> -->
</div>