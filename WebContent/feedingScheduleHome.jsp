<!-- A page to view all feeding schedules that have been created,
	 and the animals to which they have been assigned. A delete
	 button next to each feeding schedule should remove it and all
	 references to it from the DB. -->

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
 
		<h1>eZoo <small>Feeding Schedules Home</small></h1>
		<hr class="paw-primary">
		<table class="table table-striped table-hover table-responsive ezoo-datatable">
			<thead>
				<tr>
					<th class="text-center">ID</th>
					<th class="text-center">Feeding Time</th>
					<th class="text-center">Recurrence</th>
					<th class="text-center">Food</th>
					<th class="text-center">Notes</th>
					<th class="text-center">Animals</th>
					<th class="text-center">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="fs" items="${feedingSchedules}">
					<tr>
						<td><fmt:formatNumber value="${fs.scheduleID}"/></td>
						<td><c:out value="${fs.feedingTime}" /></td>
						<td><c:out value="${fs.recurrence}" /></td>
						<td><c:out value="${fs.food}" /></td>
						<td><c:out value="${fs.notes}" /></td>
						<!-- view all animals assigned to feeding schedule -->
						<td><c:out value = "${fsAnimalMap[fs.scheduleID] }"/> </td>
						<td>
							<form action="deleteFeedingSchedule" method="post">
								<button type="submit" name="delete" value="${fs.scheduleID}">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	

	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />
