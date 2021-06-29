<%@ include file="header.jsp"%>
      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
            
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Login</h4>
                  <c:if test="${error != null}">
						<p>${error}<p>
					</c:if>
					<c:remove var="error" scope="request" />
                  <sf:form action="${pageContext.request.contextPath}/AdminLogin" method="POST" class="form-sample" modelAttribute="user">
                    <hr><br/>
                    <div class="row">
                    <div class="col-md-2"></div>
                      <div class="col-md-8">
                        <div class="form-group row">                          
                          <div class="col-sm-9">
                            <sf:input type="text" placeholder="Enter username" path="username" class="form-control" />
                          	<sf:errors path="username" cssClass="error" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-2"></div>
                    </div>
                    <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                    	<div class="form-group row">
                          <div class="col-sm-9">
                            <sf:input type="password" placeholder="Enter password" path="password" class="form-control" />
                          	<sf:errors path="password" cssClass="error" />
                          </div>
                        </div>
                    </div>
                    <div class="col-md-2"></div>
                    </div>
                    <div class="row">
                    	<div class="col-md-2"></div>
                    	<div class="col-md-8">
                    		<input type="submit" name="submit" value="Login" class="btn btn-default">
                    	</div>
                    	<div class="col-md-2"></div>
                    </div>
                  </sf:form>
                </div>
              </div>
            </div>
          </div>
        </div>
<%@ include file="footer.jsp"%>