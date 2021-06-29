<%@ include file="header.jsp"%>

<div class="products-box">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="title-all text-center">
					<h1>${fn:toUpperCase(prodName)}</h1>
					
				</div>
			</div>
		</div>

		<div class="row special-list">
			<c:forEach items="${products}" var="prod"> 
				<div class="col-lg-3 col-md-6 special-grid top-featured">
					<div class="products-single fix">
						<div class="box-img-hover">
							<!-- <div class="type-lb">
								<p class="new">New</p>
							</div> -->
							<img src="${userImgLink}/${prod.imageName}" class="img-fluid1"
								alt="Image">
							<div class="mask-icon">
								<ul>
									<li><a href="#" data-toggle="tooltip"
										data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>
									<!-- <li><a href="#" data-toggle="tooltip"
										data-placement="right" title="Compare"><i
											class="fas fa-sync-alt"></i></a></li> -->
									<li><a href="#" data-toggle="tooltip"
										data-placement="right" title="Add to Wishlist"><i
											class="far fa-heart"></i></a></li>
								</ul>
								
								<sec:authorize access="isAuthenticated()">
									<a id="${prod.id}" class="cart add_cart" href="javascript:void(0)">Add to Cart</a>
								</sec:authorize>
								
							</div>
						</div>
						<div class="why-text">
							<h4>${prod.name}</h4>
							<h5>Rs ${prod.price}</h5>
						</div>
					</div>
				</div>
			 </c:forEach> 
		</div>
	</div>
</div>

<script>
$(function() {
	$(".add_cart").click(function(e) {
		var prodId = $(this).attr("id");
		var json = {"id": prodId};
		
		$.ajax({
	        type: "POST",
	        url: "cartProcess",
	        data: json,
	        success: function(result){
	        	if(result == "CREATED") {
	        		swal("Product added to cart", "", "success");	
	        	} else if(result == "UPDATED") {
	        		swal("Product quantity increased", "", "success");
	        	}
	        },
	        error: function (result){
	        	swal("Product not added", "", "error");
	        }
	    });
	});
}); 

</script>

<%@ include file="footer.jsp"%>