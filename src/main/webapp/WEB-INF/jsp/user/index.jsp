<%@ include file="header.jsp" %>

    <!-- Start Slider -->
    <div id="slides-shop" class="cover-slides">
        <ul class="slides-container">
            <li class="text-center">
                <img src="${userImgLink}/banner-01.jpg" alt="">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="m-b-20"><strong>Welcome To <br> Freshshop</strong></h1>
                            <p class="m-b-40">See how your users experience your website in realtime or view <br> trends to see any changes in performance over time.</p>
                            <p><a class="btn hvr-hover" href="#">Shop New</a></p>
                        </div>
                    </div>
                </div>
            </li>
            <li class="text-center">
                <img src="${userImgLink}/banner-02.jpg" alt="">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="m-b-20"><strong>Welcome To <br> Freshshop</strong></h1>
                            <p class="m-b-40">See how your users experience your website in realtime or view <br> trends to see any changes in performance over time.</p>
                            <p><a class="btn hvr-hover" href="#">Shop New</a></p>
                        </div>
                    </div>
                </div>
            </li>
            <li class="text-center">
                <img src="${userImgLink}/banner-03.jpg" alt="">
                <div class="container">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="m-b-20"><strong>Welcome To <br> Freshshop</strong></h1>
                            <p class="m-b-40">See how your users experience your website in realtime or view <br> trends to see any changes in performance over time.</p>
                            <p><a class="btn hvr-hover" href="#">Shop New</a></p>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
        <div class="slides-navigation">
            <a href="#" class="next"><i class="fa fa-angle-right" aria-hidden="true"></i></a>
            <a href="#" class="prev"><i class="fa fa-angle-left" aria-hidden="true"></i></a>
        </div>
    </div>
    <!-- End Slider -->

    <!-- Start Categories  -->
    <div class="categories-shop">
        <div class="container">
            <div class="row">
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="shop-cat-box">
                        <img class="img-fluid" src="${userImgLink}/categories_img_01.jpg" alt="" />
                        <a class="btn hvr-hover" href="#">Lorem ipsum dolor</a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="shop-cat-box">
                        <img class="img-fluid" src="${userImgLink}/categories_img_02.jpg" alt="" />
                        <a class="btn hvr-hover" href="#">Lorem ipsum dolor</a>
                    </div>
                </div>
                <div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
                    <div class="shop-cat-box">
                        <img class="img-fluid" src="${userImgLink}/categories_img_03.jpg" alt="" />
                        <a class="btn hvr-hover" href="#">Lorem ipsum dolor</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- End Categories -->
	
	<div class="box-add-products">
		<div class="container">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="offer-box-products">
						<img class="img-fluid" src="${userImgLink}/add-img-01.jpg" alt="" />
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-12">
					<div class="offer-box-products">
						<img class="img-fluid" src="${userImgLink}/add-img-02.jpg" alt="" />
					</div>
				</div>
			</div>
		</div>
	</div>

    <!-- Start Products  -->
    <div class="products-box">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <div class="title-all text-center">
                        <h1>Top Products</h1>
                    </div>
                </div>
            </div>
            
            <div class="row special-list">
            
              <c:forEach items="${products}" var="prod">
              
                <div class="col-lg-3 col-md-6 special-grid top-featured">
                    <div class="products-single fix">
                        <div class="box-img-hover">
                            <div class="type-lb">
                                <p class="sale">Sale</p>
                            </div>
                            <img src="${userImgLink}/${prod.imageName}" class="img-fluid1" alt="Image">
                            <div class="mask-icon">
                                <ul>
                                    <li><a href="#" data-toggle="tooltip" data-placement="right" title="View"><i class="fas fa-eye"></i></a></li>
                                    <!-- <li><a href="#" data-toggle="tooltip" data-placement="right" title="Compare"><i class="fas fa-sync-alt"></i></a></li> -->
                                    <li><a href="#" data-toggle="tooltip" data-placement="right" title="Add to Wishlist"><i class="far fa-heart"></i></a></li>
                                </ul>
								<sec:authorize access="!isAuthenticated()">
                                	<%-- <a id="${prod.id}" class="cart add_cart" href="javascript:void(0)">Add to Cart</a> --%>
								</sec:authorize>
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
    <!-- End Products  -->

    <!-- Start Instagram Feed  -->
    <div class="instagram-box">
        <div class="main-instagram owl-carousel owl-theme">
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-01.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-02.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-03.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-04.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-05.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-06.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-07.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-08.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-09.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
            <div class="item">
                <div class="ins-inner-box">
                    <img src="${userImgLink}/instagram-img-05.jpg" alt="" />
                    <div class="hov-in">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
<script>
$(function() {
	$(".add_cart").click(function(e) {
		var id = $(this).attr("id");
		
		$.ajax({
	        type: "POST",
	        url: "cartProcess",
	        data: "id="+id,
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

<%@ include file="footer.jsp" %>