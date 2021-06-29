<%@ include file="header.jsp"%>

<div class="contact-box-main">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-sm-12">
				<div class="contact-form-right">
					<h2>GET IN TOUCH</h2>
					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed
						odio justo, ultrices ac nisl sed, lobortis porta elit. Fusce in
						metus ac ex venenatis ultricies at cursus mauris.</p>

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


					<sf:form action="contact" method="POST" modelAttribute="user">
						<div class="row">
							<div class="col-md-12">
								<div class="form-group">
									<sf:input type="text" class="form-control" path="name" value="${user.name}"
											placeholder="Enter Name"/>
									<sf:errors path="name" cssClass="error" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<sf:input type="text" placeholder="Enter Email" path="email" value="${user.email}"
											class="form-control"/>
									<sf:errors path="email" cssClass="error" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<sf:input type="text" class="form-control" path="subject" value="${user.subject}"
										placeholder="Enter Subject"/>
										<sf:errors path="subject" cssClass="error" />
								</div>
							</div>
							<div class="col-md-12">
								<div class="form-group">
									<sf:textarea class="form-control" path="message"
										placeholder="Enter Message" rows="4"></sf:textarea>
									<sf:errors path="message" cssClass="error" />
									<div class="help-block with-errors"></div>
								</div>
								<div class="submit-button text-center">
									<button class="btn hvr-hover" id="submit" type="submit">Send
										Message</button>
									<div id="msgSubmit" class="h3 text-center hidden"></div>
									<div class="clearfix"></div>
								</div>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
			<div class="col-lg-4 col-sm-12">
				<div class="contact-info-left">
					<h2>CONTACT INFO</h2>

					<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Praesent urna diam, maximus ut ullamcorper quis, placerat id eros.
						Duis semper justo sed condimentum rutrum. Nunc tristique purus
						turpis. Maecenas vulputate.</p>
					<ul>
						<li>
							<p>
								<i class="fas fa-map-marker-alt"></i>Address: Michael I. Days
								9000 <br>Preston Street Wichita,<br> KS 87213
							</p>
						</li>
						<li>
							<p>
								<i class="fas fa-phone-square"></i>Phone: <a
									href="tel:+1-888705770">+1-888 705 770</a>
							</p>
						</li>
						<li>
							<p>
								<i class="fas fa-envelope"></i>Email: <a
									href="mailto:contactinfo@gmail.com">contactinfo@gmail.com</a>
							</p>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- End Cart -->


<%@ include file="footer.jsp"%>