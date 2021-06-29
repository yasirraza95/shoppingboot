<%@ include file="header.jsp"%>

<%-- <c:if test="${userId == null}">
<c:redirect url="/Index" />
</c:if> --%>
<div class="contact-box-main">
	<div class="container">
		<div class="row">
			<div class="col-md-1"></div>
			<div class="col-md-10 col-sm-12">
				<div class="contact-form-right">
					<h2>Profile</h2>
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

					<sf:form action="profile" method="POST" modelAttribute="user">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label>Name</label>
									<sf:input type="text" class="form-control" path="name"
										placeholder="Enter name" value="${user.name}" />
									<sf:errors path="name" cssClass="error" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Email</label>
									<sf:input type="email" placeholder="Enter email address"
										path="email" class="form-control" name="email"
										value="${user.email}" />
									<sf:errors path="email" cssClass="error" />
								</div>
							</div>
							<div class="clearfix"></div>


							<div class="col-md-6">
								<div class="form-group">
									<label>Username</label>
									<sf:input type="text" class="form-control" path="username"
										placeholder="Enter username" value="${user.username}" readonly="true" />
									<sf:errors path="username" cssClass="error" />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label>Mobile No</label>
									<sf:input type="text" placeholder="Enter mobile no"
										path="mobile" class="form-control" value="${user.mobile}" />
									<sf:errors path="mobile" cssClass="error" />
								</div>
							</div>
							<div class="clearfix"></div>

							<div class="col-md-12">
								<div class="form-group">
									<label>Address</label>
									<sf:input type="text" placeholder="Enter address"
										class="form-control" path="address" value="${user.address}" />
									<div class="help-block with-errors"></div>
									<sf:errors path="address" cssClass="error" />
								</div>
							</div>
							<div class="clearfix"></div>

							<div class="col-md-12">
								<div class="submit-button text-center">
									<button class="btn hvr-hover" id="submit" type="submit">Update
										Profile</button>
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