<%@ include file="header.jsp"%>
<% String error = (String)session.getAttribute("error");  %>
<%-- <c:if test="${userId != null}">
<c:redirect url="/index.jsp" />
</c:if> --%>
<div class="contact-box-main">
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10 col-sm-12">
				<div class="contact-form-right">
					<h2>Create Account</h2>
					
					<c:if test="${not empty status}">
						<div class="alert alert-danger" role="alert">
						  	${status}
						</div>
					</c:if>
					
					<c:url value="/user/signup" var="signupURL" />
					
					<sf:form action="${signupURL}" method="POST" modelAttribute="user">
						<div class="row">
						<div class="col-md-6">
								<div class="form-group">
									<sf:input class="form-control" value="${user.name}"
										path="name" placeholder="Full Name"  />
									<sf:errors path="name" cssClass="error" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<sf:input type="email" placeholder="Email Address" value="${user.email}"
										class="form-control email" path="email"  />
									<span class="error" id="emailValid"></span>
									<sf:errors path="email" cssClass="error" />
								</div>
							</div>
							<div class="clearfix"></div>
							
							
							<div class="col-md-6">
								<div class="form-group"> 
									<sf:input value="${user.username}" path="username"
										 class="form-control username" placeholder="Username"  />
									<!-- <span class="error" id="usernameValid"></span> -->
									<sf:errors path="username" cssClass="error" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<sf:password placeholder="Password" path="password"
										class="form-control"  />
									<sf:errors path="password" cssClass="error" />
								</div>
							</div>
							<div class="clearfix"></div>
							
							
							<div class="col-md-6">
								<div class="form-group">
									<sf:password class="form-control" path="cnfPassword"
										placeholder="Confirm Password"  />
									<div class="help-block with-errors"></div>
									<sf:errors path="cnfPassword" cssClass="error" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<sf:input type="number" placeholder="Mobile No" value="${user.mobile}"
										class="form-control" path="mobile"  />
									<div class="help-block with-errors"></div>
									<sf:errors path="mobile" cssClass="error" />
								</div>
							</div>
							<div class="clearfix"></div>
							
							<div class="col-md-12">
								<div class="form-group">
									<sf:input placeholder="Address" value="${user.address}"
										class="form-control" path="address"  />
									<div class="help-block with-errors"></div>
									<sf:errors path="address" cssClass="error" />
								</div>
							</div>
							<div class="clearfix"></div>
							
							<div class="col-md-12">
								<div class="submit-button text-center">
									<button class="btn hvr-hover submit" id="submit" type="submit">Create Account</button>
									<div id="msgSubmit" class="h3 text-center hidden"></div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
			<div class="col-md-1"></div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>