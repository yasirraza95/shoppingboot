<%@ include file="header.jsp"%>
      <div class="main-panel">        
        <div class="content-wrapper">
          <div class="row">
            
            <div class="col-12 grid-margin">
              <div class="card">
                <div class="card-body">
                  <h4 class="card-title">Profile</h4>
                  <sf:form action="${pageContext.request.contextPath}/AdminProfile" method="POST" class="form-sample" modelAttribute="user">
                    <p class="card-description">
                      Account Information
                    </p>
                    <c:if test="${message != null}">
						<div class="alert alert-success" role="alert">
						  <c:out value="${message}" />
						</div>
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
                            <sf:input type="text" class="form-control" path="name" placeholder="Enter name" value="${adminData.name}"/>
                          	<sf:errors path="name" cssClass="error" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Email address</label>
                          <div class="col-sm-9">
                            <sf:input type="text" class="form-control" path="email" placeholder="Enter email address" value="${adminData.email}"/>
                          	<sf:errors path="email" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="row">
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Username</label>
                          <div class="col-sm-9">
                            <sf:input type="text" class="form-control" path="username" placeholder="Enter username" value="${adminData.username}"/>
                          	<sf:errors path="username" cssClass="error" />
                          </div>
                        </div>
                      </div>
                      <div class="col-md-6">
                        <div class="form-group row">
                          <label class="col-sm-3 col-form-label">Mobile No</label>
                          <div class="col-sm-9">
                            <sf:input type="text" class="form-control" path="mobile" placeholder="Enter mobile no" value="${adminData.mobile}"/>
                          	<sf:errors path="mobile" cssClass="error" />
                          </div>
                        </div>
                      </div>
                    </div>
                   
                      <input type="submit" name="submit" value="Update Profile" class="btn btn-default">
                  </sf:form>
                </div>
              </div>
            </div>
          </div>
        </div>
<%@ include file="footer.jsp"%>