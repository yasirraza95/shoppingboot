<%@ include file="header.jsp"%>
<% String type = request.getParameter("type"); %>

      <div class="main-panel">
        <div class="content-wrapper">
          <div class="row">
           
            <div class="col-lg-12 grid-margin stretch-card">
              <div class="card">
              <c:if test="${products.size() > 0 }">
                <div class="card-body">
                  <h4 class="card-title"><%=request.getParameter("type") %></h4>
                  <p class="card-description">
                  </p>
                  <div class="table-responsive">
                    <table class="table table-striped">
                      <thead>
                        <tr>
                          <th>Name</th>
                          <th>Price</th>
                          <th>Action</th>
                        </tr>
                      </thead>
                      <tbody>
                      
                      <c:forEach items="${products}" var="prod">
                        <tr>
                          <td>${prod.name}</td>
                          <td>${prod.price}</td>
                          <td>
                            <button type="button" onclick="window.location.href='editproduct?id=${prod.id}'" class="edit">Edit</button>
                            <button type="button" id="${prod.id}" class="delete">Delete</button>
                          </td>
                          
                        </tr>
                        </c:forEach>
                      </tbody>
                    </table>
                  </div>
                </div>
                </c:if>
					<c:if test="${products.size() == 0}">
						<div style="text-align: center; margin:50px;"><h1>No products</h1></div>
					</c:if>
              </div>
            </div>
            </div>
        </div>
        <script>
        	$(function() {
        		$('.delete').click(function() {
        			var id = $(this).attr('id');
        			swal("Do you want to remove this product ?", {
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
   					 	        url: "ProductProcess",
   					 	        data: "prod=del&id="+id,
   					 	        success: function(result){
   					 	        	if(result == "success") {
   					 	        		swal("Product removed successfully", "", "success");	
   					 	        		location.reload();
   					 	        	} else {
   					 	        		swal("Product not removed", "", "error");
   					 	        	}
   					 	        },
   					 	        error: function (result){
   					 	        	swal("Error occurred, try again", "", "error");
   					 	        }
   					 	    });
   					    	
   					    	
   					      break;
   					 
   		  			  }
   					});
        		});
        	});
        </script>
<%@ include file="footer.jsp"%>