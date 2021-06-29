<%@ include file="header.jsp"%>

      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
            
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Provider</h4>
                  <sf:form action="${adminEditProviderURL}"
                    method="POST" class="form-sample" modelAttribute="user" >
                    <p class="card-description">
                      Provider information
                    </p>
                    <c:if test="${message != null}">
                    <div class="alert alert-success" role="alert">
						${message}
					</div>
					</c:if>
					<c:if test="${error != null}">
						<div class="alert alert-danger" role="alert">
							${error}
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
                            	<sf:input type="text" path="name" placeholder="Enter name" class="form-control" value="${provider.name}" />
                           		<sf:errors path="name" cssClass="error" />
                           </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Mobile No</label>
                          <div class="col-sm-9">
                            	<sf:input type="text" path="mobile" placeholder="Enter mobile" class="form-control" value="${provider.mobile}" />
                           		<sf:errors path="mobile" cssClass="error" />
                           </div>
                        </div>
                      </div>
                    </div>
                      
                    	<sf:input type="hidden" path="id" value="${param.id}" />
                    	<sf:input type="hidden" path="type" value="edit" />
                    	<input type="submit" name="submit" value="Update Provider" class="btn btn-default" >
                  </sf:form>
                </div>
              </div>
            </div>
          </div>
        </div>
        
<%@ include file="footer.jsp"%>