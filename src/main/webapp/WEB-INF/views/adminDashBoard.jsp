<link class="include" rel="stylesheet" type="text/css"
	href="css/adminComponent.css" />
<link class="include" rel="stylesheet" type="text/css"
	href="css/charts/jquery.jqplot.css" />
<link type="text/css" rel="stylesheet"
	href="css/charts/syntaxhighlighter/styles/shCoreDefault.min.css" />
<link type="text/css" rel="stylesheet"
	href="css/charts/syntaxhighlighter/styles/shThemejqPlot.min.css" />
<body>
	<section class="ct-u-paddingBoth30 ct-u-backgroundLightGreen">
		<div class="container">
			<div class="container">
				<div class="row">
					<div class="col-sm-6 col-md-6 col-lg-3">
						<div class="ct-priceBox text-center light-green">
							<div class="ct-priceSection" style="padding: 11px 15px;">
								<h5>Total 1000 Students</h5>
								<!-- <div class="ct-priceBox-fullPrice">
									<a href="/courses.do"><span
										class="ct-main-price ct-fw-700 fa fa-user"></span></a>
								</div> -->
								<div
									class="ct-iconBox ct-iconBox--small ct-iconBox--transparent">
									<div class="ct-icon">
										<i class="fa fa-user"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-sm-6 col-md-6 col-lg-3">
						<div class="ct-priceBox text-center golden-color">
							<div class="ct-priceSection" style="padding: 11px 15px;">
								<h5>Total 5 Messages</h5>
								<!-- <div class="ct-priceBox-fullPrice">
									<span class="ct-main-price ct-fw-700 fa fa-envelope-o"></span>
								</div> -->
								<div
									class="ct-iconBox ct-iconBox--small ct-iconBox--transparent">
									<div class="ct-icon">
										<i class="fa fa-envelope-o"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix visible-md visible-sm"></div>
					<div class="col-sm-6 col-md-6 col-lg-3">
						<div class="ct-priceBox text-center bright-pink-color">
							<div class="ct-priceSection" style="padding: 11px 15px;">
								<h5>Outstanding Payments</h5>
								<div
									class="ct-iconBox ct-iconBox--small ct-iconBox--transparent">
									<div class="ct-icon">
										<i class="fa fa-inr"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="clearfix visible-md visible-sm"></div>
					<div class="col-sm-6 col-md-6 col-lg-3">
						<div class="ct-priceBox text-center dodger-blue-color">
							<div class="ct-priceSection" style="padding: 11px 15px;">
								<h5>Total Complaints</h5>
								<div
									class="ct-iconBox ct-iconBox--small ct-iconBox--transparent">
									<div class="ct-icon">
										<i class="fa fa-search"></i>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="ct-u-paddingBoth20 ct-u-backgroundLightGreen">
		<div class="container">
			<div class="row">
				<div class="col-sm-6">
					<div class="panel-group panel-default" id="accordion"
						role="tablist" aria-multiselectable="true">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingOne">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseOne"
										aria-expanded="true" aria-controls="collapseOne"> <i
										class="fa fa-signal"></i> Your Yearly Revenue
									</a>
								</h4>
							</div>

							<div id="collapseOne" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingOne">
								<div class="panel-body">

									<div class="list-group">
										<a href="#" class="list-group-item"
											style="padding: 24px; background-color: #66ccff;">
											<h3 class="list-group-item-heading fa fa-bar-chart fa-3x"
												style="float: left; clear: none; padding: 8px 121px 37px 40px;"></h3>
											<p class="list-group-item-text">Total Income</p>
											<p class="" style="font-size: 12px;">Total amount of
												income earned annually.</p>
										</a> <a href="#" class="list-group-item" style="padding: 24px;">
											<h3 class="list-group-item-heading fa fa-inr fa-3x"
												style="float: left; clear: none; padding: 8px 136px 37px 50px"></h3>
											<p class="list-group-item-text">Yearly Investment</p>
											<p class="" style="font-size: 12px;">Investment Done for
												Infrastructure or Fees Paid to Teachers.</p>
										</a> <a href="#" class="list-group-item"
											style="padding: 24px; background-color: #e0ffff;">
											<h3 class="list-group-item-heading fa fa-line-chart fa-3x"
												style="float: left; clear: none; padding: 8px 121px 37px 40px;"></h3>
											<p class="list-group-item-text">Profit</p>
											<p class="" style="font-size: 12px;">Revenue minus cost
												of Yarly Investment</p>
										</a> <a href="#" class="list-group-item" style="padding: 24px;">
											<h3 class="list-group-item-heading fa fa-area-chart fa-3x"
												style="float: left; clear: none; padding: 8px 121px 37px 40px;"></h3>
											<p class="list-group-item-text">Loss</p>
											<p class="" style="font-size: 12px;">Amount of loss due
												to different factors.</p>
										</a>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingTwo">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion" href="#collapseTwo"
										aria-expanded="false" aria-controls="collapseTwo"> <i
										class="fa fa-star"></i> Most Liked Teachers
									</a>
								</h4>
							</div>
							<div id="collapseTwo" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingTwo"
								aria-expanded="false">
								<div class="panel-body">
									<div class="col-md-8">
										<ul class="ct-commentList list-unstyled">
											<li>
												<div class="ct-u-displayTable">
													<div class="ct-u-displayTableCell">
														<div class="ct-userName ct-u-marginBottom20">
															<h4 class="ct-u-textNormal ct-fw-700 ct-u-marginBottom20">Rahul
																Singh</h4>
															<div class="starrr" data-rating="5">
																<i class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa-star-o fa"></i>
															</div>
														</div>
														<div class="ct-userText">
															<p class="ct-fw-600">12th Std. Physics</p>
														</div>
													</div>
												</div>
											</li>
											<li>
												<div class="ct-u-displayTable">
													<div class="ct-u-displayTableCell">
														<div class="ct-userName ct-u-marginBottom20">
															<h4 class="ct-u-textNormal ct-fw-700 ct-u-marginBottom20">Brahmdev
																Pandey</h4>
															<div class="starrr" data-rating="3">
																<i class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa-star-o fa"></i>
															</div>
														</div>
														<div class="ct-userText">
															<p class="ct-fw-600">Java EE</p>
														</div>
													</div>
												</div>
											</li>
											<li>
												<div class="ct-u-displayTable">

													<div class="ct-u-displayTableCell">
														<div class="ct-userName ct-u-marginBottom20">
															<h4 class="ct-u-textNormal ct-fw-700 ct-u-marginBottom20">Rahul
																Singh</h4>
															<div class="starrr" data-rating="5">
																<i class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa-star-o fa"></i>
															</div>
														</div>
														<div class="ct-userText">
															<p class="ct-fw-600">10th Board Marathi</p>
														</div>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
				<div class="col-sm-6">
					<div class="panel-group panel-default" id="accordion2"
						role="tablist" aria-multiselectable="true">
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingFive">
								<h4 class="panel-title">
									<a role="button" data-toggle="collapse"
										data-parent="#accordion2" href="#collapseFive"
										aria-expanded="true" aria-controls="collapseFive"> <i
										class="fa fa-list-alt"></i> Annual Investment(Graph Based)
									</a>
								</h4>
							</div>
							<div id="collapseFive" class="panel-collapse collapse in"
								role="tabpanel" aria-labelledby="headingFive">
								<div class="panel-body">
									<div class="col-md-8 col-md-offset-2">
										<div class="row">
											<div class="col-md-8">
												<div id="chart1"
													style="margin-top: 10px; margin-left: -20px; width: 300px; height: 350px;"></div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="panel panel-default">
							<div class="panel-heading" role="tab" id="headingSix">
								<h4 class="panel-title">
									<a class="collapsed" role="button" data-toggle="collapse"
										data-parent="#accordion2" href="#collapseSix"
										aria-expanded="false" aria-controls="collapseSix"> <i
										class="fa fa-graduation-cap"></i> Top Performer Students
									</a>
								</h4>
							</div>
							<div id="collapseSix" class="panel-collapse collapse"
								role="tabpanel" aria-labelledby="headingSix">
								<div class="panel-body">
									<div class="col-md-8">
										<ul class="ct-commentList list-unstyled">
											<li>
												<div class="ct-u-displayTable">
													<div class="ct-u-displayTableCell">
														<div class="ct-userName ct-u-marginBottom20">
															<h4 class="ct-u-textNormal ct-fw-700 ct-u-marginBottom20">MS
																Dhoni</h4>
															<div class="starrr" data-rating="5">
																<i class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa-star-o fa"></i>
															</div>
														</div>
														<div class="ct-userText">
															<p class="ct-fw-600">Biology</p>
														</div>
													</div>
												</div>
											</li>
											<li>
												<div class="ct-u-displayTable">
													<div class="ct-u-displayTableCell">
														<div class="ct-userName ct-u-marginBottom20">
															<h4 class="ct-u-textNormal ct-fw-700 ct-u-marginBottom20">Chris
																Gayle</h4>
															<div class="starrr" data-rating="3">
																<i class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa-star-o fa"></i>
															</div>
														</div>
														<div class="ct-userText">
															<p class="ct-fw-600">Marathi</p>
														</div>
													</div>
												</div>
											</li>
											<li>
												<div class="ct-u-displayTable">

													<div class="ct-u-displayTableCell">
														<div class="ct-userName ct-u-marginBottom20">
															<h4 class="ct-u-textNormal ct-fw-700 ct-u-marginBottom20">Lasith
																Mallinga</h4>
															<div class="starrr" data-rating="5">
																<i class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa fa-star"></i><i class="fa fa-star"></i><i
																	class="fa-star-o fa"></i>
															</div>
														</div>
														<div class="ct-userText">
															<p class="ct-fw-600">Chemistry</p>
														</div>
													</div>
												</div>
											</li>
										</ul>
									</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</section>

	<section class="ct-u-paddingBoth30 ct-u-backgroundLightGreen">
		<div class="container">
			<div class="container">
				<div class="row">
					<div class="dashboard_v4_advertise_block">
						<div class="col-xl-7 col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-xl-7 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="row">
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12 tile-active">
											<div class="dashboard_4_advertise_box dodger-blue-color">
												<h5>Test Series</h5>
												<p>Test Series Batches for 10th & 12th Board</p>
												<div class="float-xs-right">
													<i class="fa fa-desktop"></i>
												</div>
											</div>
										</div>
										<div class="divider15"></div>
										<div class="dashboard_v4_xs_space"></div>
										<div
											class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-12 tile-active">
											<div
												class="tile dashboard_4_advertise_box light-red-color text-xs-center dashboard_4_advertise_box2">
												<div class="advertise_box_icon">
													<i class="fa fa-send"></i>
												</div>
												<h5>(Information Coming Soon...)</h5>
											</div>
										</div>
										<div class="dashboard_v4_xs_space"></div>
										<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-12 tile-active">
											<div
												class="dashboard_4_advertise_box bright-pink-color text-xs-center dashboard_4_advertise_box2">
												<div class="advertise_box_icon">
													<i class="fa fa-cart-plus"></i>
												</div>
												<h5>(Information Coming Soon...)</h5>
											</div>
										</div>
									</div>
								</div>
								<div class="dashboard_v4_sm_space"></div>
								<div class="col-xl-5 col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="row">
										<div
											class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12 tile-active">
											<div
												class="tile dashboard_4_advertise_box spice-color text-xs-center dashboard_4_advertise_box2">
												<div class="advertise_box_icon">
													<i class="fa fa-envelope"></i>
												</div>
												<h5>Email(Information Coming Soon...)</h5>
											</div>
										</div>
										<div class="divider15"></div>
										<div class="dashboard_v4_xs_space"></div>
										<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12 tile-active">
											<div
												class="dashboard_4_advertise_box3 purple-color text-xs-center">
												<div class="float-xs-left">
													<i class="fa fa-send"></i>
												</div>
												<div class="float-xs-right text-xs-right">
													<h4>16,964</h4>
													<h5>Page visit(Information Coming Soon...)</h5>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="dashboard_v4_sm_space"></div>
						<div class="col-xl-5 col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="row">
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div
										class="dashboard_4_advertise_box3 google_bg text-xs-center">
										<div class="float-xs-left">
											<i class="fa fa-google-plus"></i>
										</div>
										<div class="float-xs-right text-xs-right">
											<h4>2.6K</h4>
											<h5>Connect(Information Coming Soon...)</h5>
										</div>
									</div>
								</div>
								<div class="dashboard_v4_xs_space"></div>
								<div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-xs-12">
									<div
										class="dashboard_4_advertise_box3 twitter_bg text-xs-center">
										<div class="float-xs-left">
											<i class="fa fa-twitter"></i>
										</div>
										<div class="float-xs-right text-xs-right">
											<h4>10,894</h4>
											<h5>Followers(Information Coming Soon...)</h5>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
<script class="code" type="text/javascript">
	$(document).ready(
			function() {
				$.jqplot.config.enablePlugins = true;
				var s1 = [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul',
						'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ];
				var ticks = [ 2, 6, 7, 10, 5, 4, 2, 10, 12, 15, 12, 10 ];

				plot1 = $.jqplot('chart1', [ ticks ], {
					// Only animate if we're not using excanvas (not in IE 7 or IE 8)..
					animate : !$.jqplot.use_excanvas,
					seriesDefaults : {
						renderer : $.jqplot.BarRenderer,
						shadowAngle : 135,
						rendererOptions : {
							barDirection : 'horizontal',
							highlightMouseDown : true
						},
						pointLabels : {
							show : true
						}
					},
					axes : {
						yaxis : {
							renderer : $.jqplot.CategoryAxisRenderer,
							ticks : s1
						}
					},
					highlighter : {
						show : true
					}
				});

				$('#chart1').bind(
						'jqplotDataClick',
						function(ev, seriesIndex, pointIndex, data) {
							$('#info1').html(
									'series: ' + seriesIndex + ', point: '
											+ pointIndex + ', data: ' + data);
						});
			});
</script>

<script class="include" type="text/javascript"
	src="js/charts/jquery.jqplot.js"></script>
<script type="text/javascript"
	src="js/charts/syntaxhighlighter/scripts/shCore.min.js"></script>
<script type="text/javascript"
	src="js/charts/syntaxhighlighter/scripts/shBrushJScript.min.js"></script>
<script type="text/javascript"
	src="js/charts/syntaxhighlighter/scripts/shBrushXml.min.js"></script>

<script class="include" type="text/javascript"
	src="js/charts/plugins/jqplot.barRenderer.js"></script>
<script class="include" type="text/javascript"
	src="js/charts/plugins/jqplot.categoryAxisRenderer.js"></script>
<script class="include" type="text/javascript"
	src="js/charts/plugins/jqplot.pointLabels.js"></script>