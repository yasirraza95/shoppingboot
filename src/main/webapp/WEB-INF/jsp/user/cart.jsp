<%@ include file="header.jsp"%>

<script>
function quantityChange(prodId, qty, type) {
	if(qty <= '1' && type == 'down') {	
		swal("Minimum quantity is 1", "", "warning");
	} else if(qty >= '20' && type == 'up') {
		swal("Maximum quantity is 20", "", "warning");
	} else {
		
		var json = {"prodId":prodId, "type":type};
		$.ajax({
		        type: "POST",
		        url: "updateCart",
		        data: json,
		        success: function(result){
		        	if(result == "UPDATED") {
		        		swal("Quantity updated", "", "success");	
		        		location.reload();
		        	}
		        },
		        error: function (result){
		        	swal("Quantity not updated", "", "error");
		        }
		    });	
	}
}
</script>

<c:if test="${cartData.size() > 0 }">
	<div class="cart-box-main">
		<sf:form action="checkout" modelAttribute="cart">
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
										<th>Remove</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="price" value="0" scope="page" />

									<c:forEach items="${cartData}" var="cart">

										<c:set var="price" value="${price+(cart.price*cart.quantity)}"
											scope="page" />
										<tr>
											<td class="thumbnail-img"><a href="#"> <img
													class="img-fluid" src="${userImgLink}/${cart.image}" alt="" />
											</a></td>
											<td class="name-pr"><a href="#"> ${cart.name} </a></td>
											<td class="price-pr">
												<p>${cart.price}</p>
											</td>
											<td class="total-pr"><sf:input type="button" value="-"
													onclick="quantityChange(${cart.prodId},${cart.quantity}, 'down')"
													path="down_qty" /> <input type="text" readonly
												style="text-align: center;" value="${cart.quantity}"
												class="c-input-text qty text"> <sf:input
													type="button" value="+"
													onclick="quantityChange(${cart.prodId},${cart.quantity}, 'up')"
													path="up_qty" /></td>
											<td class="total-pr">
												<p>${cart.quantity * cart.price}</p>
											</td>
											<td class="remove-pr"><a class="del_cart"
												id="${cart.prodId}" href="javascript:void(0)"> <i
													class="fas fa-times"></i>
											</a></td>
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
					<div class="col-12 d-flex shopping-box">
						<input type="button" name="submit" value="Checkout"
							onclick="location.href='checkout'" class="ml-auto btn hvr-hover">
					</div>
				</div>

			</div>
		</sf:form>
	</div>
</c:if>
<c:if test="${cartData.size() == 0 }">
	<br />
	<br />
	<h1 style="text-align: center;">No products in cart</h1>
	<br />
	<br />
</c:if>

<script>
$(function() {
	$(".del_cart").click(function(e) {
		var prodId = $(this).attr("id");
		
		swal("Do you want to remove this product ?", {
			 buttons: {
				    cancel: "No",
				    
				    defeat: "Yes",
				  },
			})
			.then((value) => {
				switch (value) {
				 
			    case "defeat":
			    	
			    	var json = {"id": prodId};
			
			    	 $.ajax({
			 	        type: "POST",
			 	        url: "removeCart",
			 	        data: json,
			 	        success: function(result){
			 	        	if(result == "DELETED") {
			 	        		swal("Product removed from cart", "", "success");	
			 	        		location.reload();
			 	        	}
			 	        },
			 	        error: function (result){
			 	        	swal("Product not removed", "", "error");
			 	        }
			 	    });
			    	
			    	
			      break;
			 
  			  }
			});
		
	});
}); 

</script>

<%@ include file="footer.jsp"%>