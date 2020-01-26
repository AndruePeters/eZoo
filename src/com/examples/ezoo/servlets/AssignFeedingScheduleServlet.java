package com.examples.ezoo.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;

/**
 * Servlet implementation class AssignFeedingScheduleServlet
 */
@WebServlet("/assignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		long animalID = Long.parseLong(request.getParameter("animalID"));
		long schedule_ID = Long.parseLong(request.getParameter("schedule_ID"));
		String assign = request.getParameter("(un)assign");
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			if (assign.equals("Assign")) {
				dao.assignFeedingSchedule(schedule_ID, animalID);
				request.getSession().setAttribute("message", "FeedingSchedule successfully assigned");
			}
			else {
				dao.removeFeedingSchedule(animalID);
				request.getSession().setAttribute("message", "FeedingSchedule successfully removed");
			}
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("animalCareHome");
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem (un)assigning the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
		}
	}
}
