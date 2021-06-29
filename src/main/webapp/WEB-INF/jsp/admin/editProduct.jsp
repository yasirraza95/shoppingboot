<%@ include file="header.jsp"%>

      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
           
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Edit Product</h4>
                  <sf:form action="${adminEditProductURL}"
                   method="POST" class="form-sample" modelAttribute="product" >
                    <p class="card-description">
                      Product information
                    </p>
                    
                    <c:if test="${message != null}">
						<div class="alert alert-success" role="alert">
						  ${message }
						</div>		
					</c:if>
					<c:if test="${error != null}">
						<div class="alert alert-danger" role="alert">
						  ${error }
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
                            <sf:input type="text" path="name" class="form-control" value="${product.name}" />
                          	<sf:errors path="name" cssClass="error" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Product Type</label>
                          <div class="col-sm-9">
                          
                          <sf:select path="productType" class="form-control">
                          <c:if test="${product != null }">
                               	<sf:option value="vegetables" label="vegetables" />
                               	<sf:option value="fruits" label="fruits" />

							</c:if>
                          </sf:select>
                          <sf:errors path="productType" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Price Type</label>
                          <div class="col-sm-9">
                          
          					<sf:select path="priceType" class="form-control">
	                          <c:if test="${product != null }">
          					    	<sf:option value="kg" label="kg" />
          					    	<sf:option value="dozen" label="dozen" />
          					    	<sf:option value="bundle" label="bundle" />
          					    	<sf:option value="item" label="item" />
								</c:if>
                          </sf:select>
                          <sf:errors path="priceType" cssClass="error" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Price (Rs)</label>
                          <div class="col-sm-9">
                          
                          	<sf:input type="text" path="price" class="form-control" value="${product.price}" />
                            <sf:errors path="price" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                    
                      <input type="submit" name="submit" value="Update Product" class="btn btn-default">
                  </sf:form>
                </div>
              </div>
            </div>
          </div>
        </div>
        
<%@ include file="footer.jsp"%>