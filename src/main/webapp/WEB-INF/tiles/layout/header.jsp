<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<body class="cssAnimate ct-headroom--scrollUpMenu">
	<div class="ct-preloader">
		<div class="ct-preloader-content"></div>
	</div>
	<nav class="ct-menuMobile">
		<ul class="ct-menuMobile-navbar">
			<li class="active"><a href="/">Home</a>
			<li class="dropdown"><a>Courses</a>
				<ul class="dropdown-menu">		
					<li><a href="courses.do">Course Detail</a></li>
				</ul>
			</li>
			<li class="dropdown"><a>Admission</a>
				<ul class="dropdown-menu">
					<li><a href="admission.do">Admission Process</a></li> 
				</ul>
			<li class="dropdown"><a>Results</a>
				<ul class="dropdown-menu">
					<li><a href="#">Hall Of Fame</a></li>
					<li><a href="#">Testimonials</a></li>
				</ul>
			</li>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
			<li class="dropdown"><a>Management</a>
				<ul class="dropdown-menu">
					<li><a href="admin/calendar.do">Event</a></li>
					<li><a href="admin/calendar.do">Student</a></li>
					<li><a href="admin/calendar.do">Staff</a></li>
					<li><a href="admin/calendar.do">Attendance</a></li>
				</ul>
			</li>
			
			<li class="dropdown"><a>Admin</a>
				<ul class="dropdown-menu">
					<li><a href="calendar.do">Roles</a></li>
					<li><a href="calendar.do">Finance</a></li>
				</ul>
			</li>
			</sec:authorize>
			<li class="dropdown"><a>About Us</a>
				<ul class="dropdown-menu">
					<li><a href="salientFeature.do">Salient Features</a></li>
					<li><a href="missionVision.do">Mission &amp; Vision</a></li>
					<li><a href="foundersMessage.do">Message from Founder(s)</a></li>
				</ul>
			</li>
			<li class="dropdown"><a href="contact.do">Contact</a></li>
		</ul>
	</nav>

	<div id="ct-js-wrapper" class="ct-pageWrapper">
		<div class="ct-navbarMobile">
			<a class="navbar-brand" href="/"  style="margin-left: 14px;"><img alt="RS Tutorials" src="img/logo.jpg" style="max-height:70px;"></a>
			<button type="button" class="navbar-toggle">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		
		<div class="ct-topBar text-center">
			<div class="container">
				<ul class="ct-panel--user list-inline text-uppercase pull-left">
					<sec:authorize access="!isAuthenticated()">
						<li><a href="login" class="ct-js-login">login<i
								class="fa fa-lock"></i></a></li>
						<li><a href="login" class="ct-js-signup">sign up<i
								class="fa fa-user"></i></a></li>
					</sec:authorize>
					<sec:authorize access="isAuthenticated()">
						<li>Welcome <b><c:out value="${pageContext.request.remoteUser}"></c:out></b></li>
					</sec:authorize>
				</ul>
				<div class="ct-widget--group pull-right">
					<ul class="ct-widget--socials list-inline text-uppercase">
						<li><a href="https://www.facebook.com/rhlsingh432/?hc_ref=SEARCH&fref=nf"><i
								class="fa fa-facebook"></i></a></li>
						<li><a href="https://twitter.com/createitpl"><i
								class="fa fa-twitter"></i></a></li>
						<li><a href="http://www.createit.pl/"><i
								class="fa fa-wordpress"></i></a></li>
					<sec:authorize access="isAuthenticated()">
					<li>
						<form id="logoutForm" action="/logout" method="post">
							<input
								type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
							<a href="#" onclick="document.getElementById('logoutForm').submit();"><i class="fa fa-sign-out"></i></a></li>
						</form>								 
					
					</sec:authorize>
					</ul>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		
		<nav class="navbar navbar-default navbar-fixed-top yamm "
			data-heighttopbar="60px" data-startnavbar="0">
			<div class="container">
				<div class="navbar-header" style="margin-left: 78px; margin-top:5px;">
					<a href="/"><img alt="RS Tutorials" src="img/logo.jpg"></a>
				</div>
				<div class="ct-navbar--fluid pull-right">
					<ul class="nav navbar-nav ct-navbar--fadeInUp">
						<li class="dropdown active"><a href="/">Home</a></li>
						<li class="dropdown"><a>Courses</a>
							<ul class="dropdown-menu">		
								<li><a href="courses.do">Course Detail</a></li>
							</ul>
						</li>
						<li class="dropdown"><a>Admission</a>
							<ul class="dropdown-menu">		
								<li><a href="admission.do">Admission Process</a></li>
							</ul>
						</li>
								
						<li class="dropdown"><a>Results</a>
							<ul class="dropdown-menu">
								<li><a href="#">Hall Of Fame</a></li>
								<li><a href="#">Testimonials</a></li>
							</ul>
						</li>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<li class="dropdown"><a>Management</a>
							<ul class="dropdown-menu">
								<li><a href="calendar.do">Event</a></li>
								<li><a href="calendar.do">Student</a></li>
								<li><a href="calendar.do">Staff</a></li>
								<li><a href="calendar.do">Attendance</a></li>
							</ul>
						</li>
						<li class="dropdown"><a>Admin</a>
							<ul class="dropdown-menu">
								<li><a href="calendar.do">Roles</a></li>
								<li><a href="calendar.do">Finance</a></li>
							</ul>
						</li>
						</sec:authorize>
						<li class="dropdown"><a>About Us</a>
							<ul class="dropdown-menu">
								<li><a href="salientFeature.do">Salient Features</a></li>
								<li><a href="missionVision.do">Mission &amp; Vision</a></li>
								<li><a href="foundersMessage.do">Message from Founder(s)</a></li>
							</ul>
						</li>
						<li class="dropdown"><a href="contact.do">Contact</a></li>
					</ul>

				</div>
			</div>
		</nav>
		<!-- Modal -->
		<div class="modal ct-js-searchModal fade" id="myModal" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true"><i class="fa fa-close"></i></span>
						</button>
						<h3 class="modal-title" id="myModalLabel">Search Results</h3>
					</div>
					<div class="modal-body"></div>
				</div>
			</div>
		</div>

		<div class="modal ct-modal ct-js-modal-login fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true"><i class="fa fa-close"></i></span>
						</button>
						<h3 class="modal-title text-uppercase">Login</h3>
					</div>
					<div class="modal-body">
						<form class="ct-u-paddingBottom100" name="loginForm" 
							action="/login" method='POST'>
							<div class="form-group ct-u-marginBottom50">
								<input placeholder="E-mail" type="text" required
									class="form-control ct-input--type1 input-hg ct-u-marginBottom50"
									title="Login" name='username'> 
								<input placeholder="Password"
									type="password" required
									class="form-control ct-input--type1 input-hg ct-u-marginBottom50"
									title="Password" name='password'>
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />
								<div class="ct-checbox--custom">
									<input id="remember" type="checkbox" name="remember"
										value="remember"> <label for="remember">remember
										me</label>
								</div>
							</div>
							<div class="text-center">
								<button type="submit"
									class="btn btn-primary btn-lg text-uppercase">
									<span>Sign In</span>
								</button>
								<div class="ct-u-marginTop50">
									<a href="#"><i class="fa fa-info-circle"></i> Forgot
										Password ?</a> <a href="#"><i class="fa fa-long-arrow-right"></i>
										Register Now ?</a>
								</div>
							</div>
							
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="modal ct-modal ct-js-modal-signup fade" tabindex="-1"
			role="dialog" aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true"><i class="fa fa-close"></i></span>
						</button>
						<h3 class="modal-title text-uppercase">Signup</h3>
					</div>
					<div class="modal-body">
						<form class="ct-u-paddingBottom100">
							<div class="form-group ct-u-marginBottom50">
								<div class="row">
									<div class="col-md-6">
										<input placeholder="Name" type="text" required=""
											name="field[]"
											class="form-control ct-input--type1 input-hg ct-u-marginBottom50"
											title="Name"> <input placeholder="Password"
											type="text" required="" name="field[]"
											class="form-control ct-input--type1 input-hg ct-u-marginBottom50"
											title="Password">
									</div>
									<div class="col-md-6">
										<input placeholder="Email" type="text" required=""
											name="field[]"
											class="form-control ct-input--type1 input-hg ct-u-marginBottom50"
											title="Email"> <input placeholder="Repeat Password"
											type="text" required="" name="field[]"
											class="form-control ct-input--type1 input-hg ct-u-marginBottom50"
											title="Repeat">
									</div>
								</div>


								<div class="ct-checbox--custom">
									<input id="signup" type="checkbox" name="signup" value="signup">
									<label for="signup">I agree with <a href="#"
										class="ct-u-textUnderline">The terms of use</a>
									</label>
								</div>
							</div>
							<div class="text-center">
								<button type="submit"
									class="btn btn-primary btn-lg text-uppercase">
									<span>Sign Up</span>
								</button>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>