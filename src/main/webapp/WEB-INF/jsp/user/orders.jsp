<%@ include file="header.jsp"%>

<div class="content-wrapper">
		<div class="row">

			<div class="col-lg-12 grid-margin stretch-card">
				<div class="card">
				<c:if test="${orders.size() > 0 }">
					<div class="card-body" style=" padding: 60px;">
						<h4 style=" text-align: center; font-size: 40px; ">Orders History</h4>
						<br/>
						<p class="card-description"></p>
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Order No</th>
										<th>Total Charges</th>
										<th>Status</th>
										<th>Shipping info</th>
										<th>Date</th>
										<th>Action</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${orders}" var="ord">
									<tr>
										<td>${ord.ordNo}</td>
										<td>${ord.charges}</td>
										<td>${ord.orderStatus}</td>
										<td><b>Name: </b>${ord.shipName}<br/><br/><b>Mobile No: </b>${ord.shipMobile}<br/><br/>
										 		<b>Address: </b>${ord.shipAddress}
										</td>
										<fmt:formatDate type = "date" value = "${now}" />										
										<td>${ord.date}</td>
										<td>
											<a href="detail?id=${ord.id}" class="detail">Detail</a>	
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					</c:if>
					<c:if test="${orders.size() == 0 }">
						<div style="text-align: center; margin:50px;"><h1>No orders</h1></div>
					</c:if>
				</div>
			</div>
		</div>
	</div>

<%@ include file="footer.jsp"%>