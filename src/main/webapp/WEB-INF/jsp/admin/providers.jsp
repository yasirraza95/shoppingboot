<%@ include file="header.jsp"%>
<div class="main-panel">
	<div class="content-wrapper">
		<div class="row">

			<div class="col-lg-12 grid-margin stretch-card">
				<div class="card">
					<c:if test="${users.size() > 0 }">
						<div class="card-body">
							<h4 class="card-title">Providers</h4>
							<p class="card-description"></p>

							<div class="table-responsive">
								<table class="table table-striped">
									<thead>
										<tr>
											<th>Name</th>
											<th>Mobile No</th>
											<th>Address</th>
											<th>Action</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${users}" var="prod">
											<tr>
												<td>${prod.name}</td>
												<td>${prod.mobile}</td>
												<td>${prod.address}</td>
												<td>
													<button type="button" onclick="window.location.href='editprovider?id=${prod.userId}'" class="edit">Edit</button>
													<button type="button" id="${prod.userId}" class="delete">Delete</button>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</c:if>
					<c:if test="${users.size() == 0}">
						<div style="text-align: center; margin:50px;"><h1>No providers</h1></div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
	<script>
		$(function() {
			$('.delete').click(function() {
				var userId = $(this).attr('id');
				swal("Do you want to remove this provider ?", {
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
					 	        url: "UserProcess",
					 	        data: "user=del&userId="+userId,
					 	        success: function(result){
					 	        	if(result == "success") {
					 	        		swal("Provider removed successfully", "", "success");	
					 	        		location.reload();
					 	        	}
					 	        },
					 	        error: function (result){
					 	        	swal("Provider not removed", "", "error");
					 	        }
					 	    });
					    	
					    	
					      break;
					 
		  			  }
					});
			});
		});
	</script>
	<%@ include file="footer.jsp"%>