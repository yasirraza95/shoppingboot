<%@ include file="header.jsp"%>

      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
            
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Provider</h4>
                  <sf:form action="${pageContext.request.contextPath}/AdminAddProvider"  method="POST" class="form-sample" modelAttribute="user">
                    <p class="card-description">
                      Provider information
                    </p>
                    <c:if test="${message != null}">
						<script>
						swal({
						    title: "",
						    text: message,
						    type: "success"
						}).then(function() {
						    window.location = "AdminProviders";
						});
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
                          <label class="col-sm-3 col-form-label">Name</label>
                          <div class="col-sm-9">
                         		<sf:input type="text" path="name" placeholder="Enter name" class="form-control" />
                         		<sf:errors path="name" cssClass="error" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Mobile No</label>
                          <div class="col-sm-9">
                            <sf:input type="text" path="mobile" placeholder="Enter mobile" class="form-control" />
                          	<sf:errors path="mobile" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                    
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Username</label>
                          <div class="col-sm-9">
                            <sf:input type="text" path="username" placeholder="Enter username" class="form-control provider" />
                          	<span class="error" id="providerValid"></span>
                          	<sf:errors path="username" cssClass="error" />
                          </div>
                          
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Password</label>
                          <div class="col-sm-9">
                            <sf:input type="password" path="password" placeholder="Enter password" class="form-control" />
                          	<sf:errors path="password" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                      
                    	<input type="submit" name="submit" value="Add Provider" class="btn btn-default submit">
                    	
                    </sf:form>
                </div>
              </div>
            </div>
          </div>
        </div>
        
<%@ include file="footer.jsp"%>