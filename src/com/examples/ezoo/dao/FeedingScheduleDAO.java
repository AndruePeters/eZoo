package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public interface FeedingScheduleDAO {

	void addFeedingSchedule(FeedingSchedule schedule) throws Exception;		// A method to add a given feeding schedule to the database
	void deleteFeedingSchedule(long schedule_ID) throws Exception;	// A method to delete a given feeding schedule to the database ->
															// This method should first remove all references to that feeding schedule from the ANIMALS table.	
	List<FeedingSchedule> getAllFeedingSchedules();			// A method to retrieve all feeding schedules from the database
	FeedingSchedule getFeedingSchedule(Animal animal);		// A method to retrieve a single feeding schedule from the database for a given animal
	void assignFeedingSchedule(long schedule_ID, long animalID) throws Exception;	// A method to assign a given feeding schedule to a given animal
	void removeFeedingSchedule(long animalID) throws Exception;				// A method to remove a feeding schedule from a given animal
	void updateFeedingSchedule(FeedingSchedule schedule) throws Exception;	// A method to update a feeding schedule
}
