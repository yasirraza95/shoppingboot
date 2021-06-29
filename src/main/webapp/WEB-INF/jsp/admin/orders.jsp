<%@ include file="header.jsp"%>

<div class="main-panel">
	<div class="content-wrapper">
		<div class="row">

			<div class="col-lg-12 grid-margin stretch-card">
				<div class="card">
				<c:if test="${orders.size() > 0 }">
					<div class="card-body">
						<h4 class="card-title">${param.type } Orders</h4>
						<p class="card-description"></p>
						<div class="table-responsive">
							<table class="table table-striped">
								<thead>
									<tr>
										<th>Order No</th>
										<th>Total Charges</th>
										<th>Status</th>
										<th>Account info</th>
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
										<td>${ord.status}</td>
										<td>Name:<br /> Mobile No:<br />
										</td>
										<td><b>Name: </b>${ord.shipName}<br/><br/><b>Mobile No: </b>${ord.shipMobile}<br/><br/>
										 		<b>Address: </b>${ord.shipAddress}
										</td>
										<td>${ord.date}</td>
										<td>
										
											<button type="button" id="${ord.id}" class="detail">Detail</button>
											<c:if test="${param.type == 'pending' }">
												<button type="button" id="${ord.id}" class="process">Process
												Order</button>
												<input type="hidden" class="status" value="processing">
											</c:if>
											<c:if test="${param.type == 'processing' }">
												<button type="button" id="${ord.id}" class="complete">Complete
												Order</button>
												<input type="hidden" class="status" value="delivered">
											</c:if>
										</td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					</c:if>
					<c:if test="${orders.size() == 0 }">
						<div style="text-align: center; margin:50px;"><h1>No ${param.type} orders</h1></div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<script>
        	$(function() {
        		$('.detail').click(function() {
        			var id = $(this).attr('id');
        		});
        		$('.process').click(function() {
        			var id = $(this).attr('id');
        			var status = $('.status').val();
        			
        			swal("Do you want to process this order ?", {
      					 buttons: {
      						    cancel: "No",
      						    
      						    defeat: "Yes",
      						  },
      					})
      					.then((value) => {
      						switch (value) {
      						 
      					    case "defeat":
      					
      					    	 $.ajax({
      					 	        type: "POST",
      					 	        url: "OrderProcess",
      					 	        data: "id="+id+"&status="+status,
      					 	        success: function(result){
      					 	        	if(result == "success") {
      					 	        		swal("Order processed successfully", "", "success");	
      					 	        		location.reload();
      					 	        	}
      					 	        },
      					 	        error: function (result){
      					 	        	swal("Order cannot be processed at this time", "", "error");
      					 	        }
      					 	    });
      					    	
      					    	
      					      break;
      					 
      		  			  }
      					});
        		});
        		$('.complete').click(function() {
        			var id = $(this).attr('id');
        			var status = $('.status').val();
        			
        			swal("Do you want to complete this order ?", {
   					 buttons: {
   						    cancel: "No",
   						    
   						    defeat: "Yes",
   						  },
   					})
   					.then((value) => {
   						switch (value) {
   						 
   					    case "defeat":
   					
   					    	 $.ajax({
   					 	        type: "POST",
   					 	        url: "OrderProcess",
   					 	        data: "id="+id+"&status="+status,
   					 	        success: function(result){
   					 	        	if(result == "success") {
   					 	        		swal("Order delivered successfully", "", "success");	
   					 	        		location.reload();
   					 	        	}
   					 	        },
   					 	        error: function (result){
   					 	        	swal("Order cannot be delivered at this time", "", "error");
   					 	        }
   					 	    });
   					    	
   					    	
   					      break;
   					 
   		  			  }
   					});
        		});
        	});
        </script>
	<%@ include file="footer.jsp"%>