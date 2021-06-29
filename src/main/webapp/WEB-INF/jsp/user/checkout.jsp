<%@ include file="header.jsp"%>

<!-- Start Cart  -->
<div class="cart-box-main">
	<div class="container">

		<c:if test="${message != null}">
			<script>
				swal("Order Placed successfully", "", "success" );
				window.location = "/"; //place inside then of swal
			</script>	
		</c:if>
		
		<c:if test="${error != null}">
			<div class="alert alert-danger" role="alert">
			  <c:out value="${error}" />
			</div>
		</c:if>
		<c:remove var="message" scope="request" />
		<c:remove var="error" scope="request" />
		

		<sf:form action="placeorder" method="POST" modelAttribute="user">
			<div class="row">
				<div class="col-sm-6 col-lg-6 mb-3">
					<div class="checkout-address">
						<div class="title-left">
							<h3>Billing address</h3>
						</div>

						<div class="row">
							<div class="col-md-12 mb-3">
								<label for="name">Name *</label>
								 	
								<sf:input type="text" value="${user.name }" path="name" class="form-control"
									placeholder="Enter name" />
									<sf:errors path="name" cssClass="error" />
								
							</div>

						</div>
						<div class="mb-3">
							<label for="username">Mobile No *</label>
							<div class="input-group">
								<sf:input type="text" value="${user.mobile }" path="mobile" class="form-control" 
									placeholder="Enter mobile no" />
									<sf:errors path="mobile" cssClass="error" />
							</div>
						</div>
						<div class="mb-3">
							<label for="email">Email Address *</label>
							 
							 <sf:input type="email" value="${user.email}" path="email" class="form-control" 
									placeholder="Enter email address" />
									<sf:errors path="email" cssClass="error" />
									
							<div class="invalid-feedback">Please enter a valid email
								address for shipping updates.</div>
						</div>
						<div class="mb-3">
							<label for="address">Address *</label> 
							<sf:input type="text" value="${user.address}" path="address" class="form-control" 
									placeholder="Enter address" />
							<sf:errors path="address" cssClass="error" />
						</div>
						
					</div>
				</div>
				<div class="col-sm-6 col-lg-6 mb-3">
					<div class="row">
						<div class="col-md-12 col-lg-12">
							<div class="odr-box">
								<div class="title-left">
									<h3>Products</h3>
								</div>

								<c:set var="price" value="0" scope="page" />

								<c:forEach items="${cartData}" var="cart">
									<c:set var="price" value="${price+(cart.price*cart.quantity)}"
										scope="page" />
									<div class="rounded p-2 bg-light">
										<div class="media mb-2 border-bottom">
											<div class="media-body">
												<a href="detail.html"> ${cart.name}</a>
												<div class="small text-muted">
													Price: Rs ${cart.price } <span class="mx-2">|</span> Qty:
													${cart.quantity } <span class="mx-2">|</span> Subtotal: Rs
													${cart.quantity * cart.price}
												</div>
											</div>
										</div>

									</div>
								</c:forEach>

							</div>
						</div>

						<div class="col-md-12 col-lg-12">
							<div class="order-box">
								<br />
								<div class="d-flex gr-total">
									<h5>Grand Total</h5>
									<div class="ml-auto h5">Rs ${price}</div>
								</div>
							</div>
							<br />
						</div>
						<div class="col-12 d-flex shopping-box">
							<input type="submit" name="submit" value="Place Order"
								class="ml-auto btn hvr-hover">
						</div>
					</div>
				</div>
			</div>
		</sf:form>
	</div>
</div>
<!-- End Cart -->


<%@ include file="footer.jsp"%>