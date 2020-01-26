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
 * Servlet implementation class DeleteFeedingScheduleServlet
 */
@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("feedingScheduleHome.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Get Parameters
		//We MUST convert to a Long since parameters are always Strings
		long scheduleID = Long.parseLong(request.getParameter("scheduleID"));
		
		//Call DAO method
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.deleteFeedingSchedule(scheduleID);
			request.getSession().setAttribute("message", "FeedingSchedule successfully deleted");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingScheduleHome");
		}catch (Exception e){
			e.printStackTrace();
			
			//change the message
			request.getSession().setAttribute("message", "There was a problem deleting the feeding schedule at this time");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("feedingScheduleHome.jsp").forward(request, response);
		}
	}
}
