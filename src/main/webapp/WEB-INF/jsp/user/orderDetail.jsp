<%@ include file="header.jsp"%>

<div class="ml-auto h5" style="text-align:center; margin-top:15px;">Order No: ${orderDetail.ordNo }</div>

<c:if test="${orderProducts.size() > 0 }">
	<div class="cart-box-main">
		<div class="container">
			<div class="row">
				<div class="col-lg-12">
					<div class="table-main table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Images</th>
									<th>Product Name</th>
									<th>Price (Rs)</th>
									<th>Quantity</th>
									<th>Total</th>
								</tr>
							</thead>
							<tbody>
							<c:set var="price" value="0" scope="page" />
							
								<c:forEach items="${orderProducts}" var="orders">
			
									<c:set var="price" value="${price+(orders.charges*orders.quantity)}" scope="page" />
									<tr>
										<td class="thumbnail-img"><a href="#"> <img
												class="img-fluid" src="${userImgLink}/${orders.image}" alt="" />
										</a></td>
										<td class="name-pr"><a href="#"> ${orders.prodName} </a></td>
										<td class="price-pr">
											<p>${orders.charges}</p>
										</td>
										<td class="total-pr">
										<span>${orders.quantity}</span>
										</td>
										<td class="total-pr">
											<p>${orders.quantity * orders.charges}</p>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>

			<div class="row my-5">
				<div class="col-lg-8 col-sm-12"></div>
				<div class="col-lg-4 col-sm-12">
					<div class="order-box">
						<div class="d-flex gr-total">
							<h5>Grand Total</h5>
							<div class="ml-auto h5">Rs ${price}</div>
						</div>
						<hr>
					</div>
				</div>
			</div>

		</div>
	</div>
</c:if>
<c:if test="${cartData.size() == 0 }">
	<br/><br/>
	<h1 style="text-align:center;">No products against this order</h1>
	<br/><br/>
</c:if>

<%@ include file="footer.jsp"%>