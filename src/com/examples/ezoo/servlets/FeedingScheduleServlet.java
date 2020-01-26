package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


/**
 * Servlet implementation class FeedingScheduleServlet
 */
@WebServlet(description = "This servlet is the main interface into the Feeding Schedule Home", urlPatterns = { "/feedingScheduleHome" })
public class FeedingScheduleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO daoFS = DAOUtilities.getFeedingScheduleDao();
		AnimalDAO daoAnimal = DAOUtilities.getAnimalDao();
		List<FeedingSchedule> feedingSchedules = daoFS.getAllFeedingSchedules();
		Map<Long, List<Long>> animalsForSchedule = new HashMap<Long, List<Long>>();
		// feedingSchedules
		// add stuff to map
		for (FeedingSchedule schedule : feedingSchedules) {
			Long id = schedule.getScheduleID();
			List<Long> animalIDList = daoAnimal.getAnimalsFromFeedingSchedule(id);
			animalsForSchedule.put(id, animalIDList);
		}

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		request.getSession().setAttribute("fsAnimalMap", animalsForSchedule);
		
		request.getRequestDispatcher("feedingScheduleHome.jsp").forward(request, response);
	}
}
