<%@ include file="header.jsp"%>

      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
           
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Add Product</h4>
                  <form action="${adminAddProductURL}" enctype="multipart/form-data"
                   method="POST" class="form-sample" >
                    <p class="card-description">
                      Product information
                    </p>
                    <c:if test="${message != null}">
					<script>
						swal({
						    title: "",
						    text: "Product created successfully",
						    type: "success"
						}).then(function() {
						    location.reload();
						});
						
						if ( window.history.replaceState ) {
							  window.history.replaceState( null, null, window.location.href );
							}
					</script>
					</c:if>
					<c:if test="${error != null}">
						<div class="alert alert-danger" role="alert">
							<c:out value="${error}" />
						</div>
					</c:if>
					<c:remove var="message" scope="request" />
					<c:remove var="error" scope="request" />
                    
                    <hr><br/>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Product Name</label>
                          <div class="col-sm-9">
                            <input type="text" name="name" placeholder="Enter name"
									value="${product.name}" class="form-control product" required="required"/>
                          		<%-- <errors path="name" cssClass="error" /> --%>
                          		<span class="error" id="productValid"></span>
                          </div>
                        </div>
                      </div>
                      
                      <div class="col-md-6">
						<div class="form-group row">
							<label class="col-sm-3 col-form-label">Top Product</label>
							<div class="col-sm-3">
								<input type="checkbox" name="topProduct" class="form-control" value="top product"/>
									<%-- <errors path="topProduct" cssClass="error" /> --%>
							</div>
						</div>
					</div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Price Type</label>
                          <div class="col-sm-9">
                          <select name="priceType" class="form-control" required="required">
	                          <option value="" label="Select type" />
	                          <option value="kg" label="Kg" />
	                          <option value="dozen" label="Dozen" />
	                          <option value="bundle" label="Bundle" />
	                          <option value="item" label="Item" />
                          </select>
                          <%-- <errors path="priceType" cssClass="error" /> --%>
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Price (Rs)</label>
                          <div class="col-sm-9">
                            <input type="number" name="price" placeholder="Enter price"
									value="${product.price}" class="form-control"  required="required"/>
							<%-- <errors path="price" cssClass="error" /> --%>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Product Type</label>
                          <div class="col-sm-9">
                          <select name="productType" class="form-control" required="required">
                          	<option value="" label="Select type"></option>
                          	<option value="fruits" label="Fruits"></option>
                          	<option value="vegetables" label="Vegetables"></option>
                          </select>
                          <!-- <errors path="productType" cssClass="error" /> -->
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Image</label>
                          <div class="col-sm-4">
                            <div class="form-check">
                              <label class="form-check-label">
                                <%-- <input type="file" path="image" class="form-control" required="required"/>
                              	<errors path="image" cssClass="error" /> --%>
                              	<input type="file" name="image" class="form-control" required="required"/>
                              	
                              </label>
                            </div>
                          </div>
                        </div>
                      </div>
                    </div>
                      <input type="submit" name="submit" value="Add Product" class="btn btn-default submit">
                  </form>
                </div>
              </div>
            </div>
          </div>
        </div>
        
<%@ include file="footer.jsp"%>