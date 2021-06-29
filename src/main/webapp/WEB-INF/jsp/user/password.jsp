<%@ include file="header.jsp"%>
<% String error = (String)session.getAttribute("error");  %>

<%-- <c:if test="${userId == null}">
<c:redirect url="/Index" />
</c:if> --%>
<div class="contact-box-main">
	<div class="container">
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-lg-4 col-sm-12">
				<div class="contact-form-right">
					<h2>Password</h2>
					<c:if test="${message != null}">
						<div class="alert alert-success" role="alert">
							<c:out value="${message}" />
						</div>
					</c:if>
					<c:if test="${error != null}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${error}" />
						</div>>
					</c:if>
					<c:remove var="message" scope="request" />
					<c:remove var="error" scope="request" />
					
					<sf:form action="password" method="POST" modelAttribute="user">
						<div class="row">
						<div class="col-md-12">
							<div class="form-group">
								
								<sf:input type="password" class="form-control" path="oldPassword"
										placeholder="Enter current password"/>
								<sf:errors path="oldPassword" cssClass="error" />			
							</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
							
									<sf:input type="password" placeholder="Enter new password" 
										class="form-control" path="newPassword"/>
										<sf:errors path="newPassword" cssClass="error" />
									<div class="help-block with-errors"></div>
								</div>
							</div>
							
							
							<div class="col-md-12">
								<div class="form-group">
								
									<sf:input type="password" class="form-control"
										path="confirmPassword" placeholder="Enter confirm password" />
									<sf:errors path="confirmPassword" cssClass="error" />
								</div>
							</div>
							
							
							<div class="col-md-12">
								<div class="submit-button text-center">
									<button class="btn hvr-hover" id="submit" type="submit">Update Password</button>
									<div id="msgSubmit" class="h3 text-center hidden"></div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>
</div>

<%@ include file="footer.jsp"%>