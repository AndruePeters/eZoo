package com.examples.ezoo.model;

public class FeedingSchedule {
	private long schedule_ID = 0L;
	
	private String feeding_time = "";
	private String recurrence = "";
	private String food = "";
	private String notes = "";
	
	public FeedingSchedule() {}

	public FeedingSchedule(long schedule_ID, String feeding_time, String recurrence, String food, String notes) {
		super();
		this.schedule_ID = schedule_ID;
		this.feeding_time = feeding_time;
		this.recurrence = recurrence;
		this.food = food;
		this.notes = notes;
	}

	public long getScheduleID() {
		return schedule_ID;
	}

	public void setScheduleID(long schedule_ID) {
		this.schedule_ID = schedule_ID;
	}

	public String getFeedingTime() {
		return feeding_time;
	}

	public void setFeedingTime(String feeding_time) {
		this.feeding_time = feeding_time;
	}

	public String getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(String recurrence) {
		this.recurrence = recurrence;
	}

	public String getFood() {
		return food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "Animal [schedule_ID=" + schedule_ID + ", feeding_time=" + feeding_time + ", recurrence=" + recurrence + ", food="
				+ food + ", notes=" + notes + "]";
	}
}
