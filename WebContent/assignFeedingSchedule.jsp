	<!-- Header -->
	<jsp:include page="header.jsp" />
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	
<!-- 	Just some stuff you need -->
	<header>
	  <div class="container">
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
		<h1>eZoo <small>(un)Assign Feeding Schedule</small></h1>
		<hr class="paw-primary">
		
		<form action="assignFeedingSchedule" method="post" class="form-horizontal">
		
		  <div class="form-group">
		    <label for="animalID" class="col-sm-4 control-label">Animal ID</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="animalID" name="schedule_ID" placeholder="Animal ID" required="required"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <label for="(un)assign" class="col-sm-4 control-label">Assign or Remove</label>
		    <div class="col-sm-4">
					<select required="required" name="(un)assign" class="form-control">
						<option value="Assign">
							Assign
						</option>
						<option value="Remove">
							Remove
						</option>
					</select>
				</div>
			</div>
		  <div class="form-group">
		    <label for="scheduleID" class="col-sm-4 control-label">Schedule ID [Ignore if Removing]</label>
		    <div class="col-sm-4">
		      <input type="number" class="form-control" id="scheduleID" name="schedulID" placeholder="Schedule ID"/>
		    </div>
		  </div>
		  <div class="form-group">
		    <div class="col-sm-offset-4 col-sm-1">
		      <button type="submit" class="btn btn-primary">(un)Assign</button>
		    </div>
		  </div>
		</form>
	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />