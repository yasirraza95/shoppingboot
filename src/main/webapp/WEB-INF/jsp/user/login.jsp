<%@ include file="header.jsp"%>


<div class="contact-box-main">
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-lg-4 col-sm-12">
				<div class="contact-form-right">
					<h2>Login</h2>
					
					<c:url value="/user/spring_security_login" var="loginProcessingURL" />
					
					<form action="${loginProcessingURL}" method="POST">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<input type="text" class="form-control" name="username"
										placeholder="Username" required="required" />
									<%-- <sf:errors path="username" cssClass="error" /> --%>
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<input type="password" placeholder="Password"
										class="form-control" name="password" required="required" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="submit-button text-center">
									<button class="btn hvr-hover" id="submit" type="submit">Login</button>
									<div id="msgSubmit" class="h3 text-center hidden"></div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
						<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					</form>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>