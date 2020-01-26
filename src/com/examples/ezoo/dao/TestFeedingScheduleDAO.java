package com.examples.ezoo.dao;

import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class TestFeedingScheduleDAO {
	public static void main(String[] args){
		FeedingSchedule schedule0 = new FeedingSchedule(1,"noon","3","pellets","variety");
		FeedingSchedule schedule1 = new FeedingSchedule(2,"morning","2","meat","variety");
		FeedingSchedule schedule2 = new FeedingSchedule(3,"night","3","pellets","variety");
		FeedingSchedule schedule3 = new FeedingSchedule(4,"afternoon","10","pellets","variety");
		FeedingSchedule schedule4 = new FeedingSchedule(5,"midnight","1","meat","variety");
		Animal animal0 = new Animal(1,"Leo","Animalia","Chordata","Mammalia","Carnivora","Felidae","Panthera","P. leo",120.88,400.67,"Mammal (Terrestrial)","Healthy",1);
		FeedingScheduleDAO dao = new FeedingScheduleDaoImpl();
		try {
			dao.addFeedingSchedule(schedule0);
			dao.addFeedingSchedule(schedule1);
			dao.addFeedingSchedule(schedule2);
			dao.addFeedingSchedule(schedule3);
			dao.addFeedingSchedule(schedule4);
			dao.deleteFeedingSchedule(schedule4.getScheduleID());
			dao.assignFeedingSchedule(schedule0.getScheduleID(), animal0.getAnimalID());
			dao.removeFeedingSchedule(animal0.getAnimalID());
		} catch (Exception e) {
			e.printStackTrace();
		}
		FeedingSchedule scheduleGET = dao.getFeedingSchedule(animal0);
		System.out.println(scheduleGET);
		List<FeedingSchedule> list = dao.getAllFeedingSchedules();
		for (int i = 0; i < list.size(); i++){
			FeedingSchedule fs = list.get(i);
			System.out.println(fs);
		}
	}
}
