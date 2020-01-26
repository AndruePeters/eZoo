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
				<c:forEach var="feeding_schedule" items="${feeding_schedules}">
					<tr>
						<td><fmt:formatNumber value="${feeding_schedule.schedule_ID}"/></td>
						<td><c:out value="${feeding_schedule.feeding_time}" /></td>
						<td><c:out value="${feeding_schedule.recurrence}" /></td>
						<td><c:out value="${feeding_schedule.food}" /></td>
						<td><c:out value="${feeding_schedule.notes}" /></td>
						<!-- view all animals assigned to feeding schedule -->
						<td><button type="button" onclick="" value="${feeding_schedule.schedule_ID}">delete</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>	

	  </div>
	</header>


	<!-- Footer -->
	<jsp:include page="footer.jsp" />
