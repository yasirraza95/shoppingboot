<%@ include file="header.jsp"%>
      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
            
           
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Password</h4>
                  
                  <sf:form action="${pageContext.request.contextPath}/AdminPassword" method="POST" class="form-sample" modelAttribute="user">
                    <p class="card-description">
                      Change Password
                    </p>
                    
                    <c:if test="${message != null}">
						<div class="alert alert-success" role="alert">
							${message}
						</div>
					</c:if>
					<c:if test="${error != null}">
						<div class="alert alert-danger" role="alert">
							${error}
						</div>>
					</c:if>
					<c:remove var="message" scope="request" />
					<c:remove var="error" scope="request" />
                    
                    <hr><br/>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Old Password</label>
                          <div class="col-sm-9">
                            <sf:input type="password" class="form-control" path="oldPassword" placeholder="Enter current password" />
                          	<sf:errors path="oldPassword" cssClass="error" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">New Password</label>
                          <div class="col-sm-9">
                            <sf:input type="password" placeholder="Enter new password" class="form-control" path="newPassword" />
                          	<sf:errors path="newPassword" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Confirm Password</label>
                          <div class="col-sm-9">
                            <sf:input type="password" class="form-control" path="cnfPassword" placeholder="Enter confirm password" />
                            <sf:errors path="cnfPassword" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                   
                      <input type="submit" name="submit" value="Update Password" class="btn btn-default">
                  </sf:form>
                </div>
              </div>
            </div>
          </div>
        </div>
<%@ include file="footer.jsp"%>