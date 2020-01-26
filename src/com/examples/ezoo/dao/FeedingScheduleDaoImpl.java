package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class FeedingScheduleDaoImpl implements FeedingScheduleDAO {

	@Override
	public void addFeedingSchedule(FeedingSchedule schedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULES VALUES (?,?,?,?,?)";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from schedule into PreparedStatement
			stmt.setLong(1, schedule.getScheduleID());
			stmt.setString(2, schedule.getFeedingTime());
			stmt.setString(3, schedule.getRecurrence());
			stmt.setString(4, schedule.getFood());
			stmt.setString(5, schedule.getNotes());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Insert feeding schedule failed: " + schedule);
		}
	}

	@Override
	public void deleteFeedingSchedule(long schedule_ID) throws Exception {
		Connection connection = null;
		PreparedStatement stmtUp = null;
		PreparedStatement stmtDel = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET feeding_schedule = ? WHERE feeding_schedule = ?";
			// Setup PreparedStatement
			stmtUp = connection.prepareStatement(sql);
			stmtUp.setLong(1, 0L);
			stmtUp.setLong(2, schedule_ID);
			success = stmtUp.executeUpdate();
			
			// delete feeding_schedule from table
			// Setup PreparedStatement
			sql = "DELETE FROM FEEDING_SCHEDULES WHERE schedule_ID=?";
			stmtDel = connection.prepareStatement(sql);
			// Add parameters from schedule into PreparedStatement
			stmtDel.setLong(1, schedule_ID);
			
			success += stmtDel.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmtUp != null)
					stmtUp.close();
				if (stmtDel != null)
					stmtDel.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Delete FeedingScheduleID[" + schedule_ID + "] failed");
		}
	}

	@Override
	public List<FeedingSchedule> getAllFeedingSchedules() {
		List<FeedingSchedule> schedules = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM FEEDING_SCHEDULES";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				FeedingSchedule fs = new FeedingSchedule();

				fs.setScheduleID(rs.getLong("schedule_ID"));
				fs.setFeedingTime(rs.getString("feeding_time"));
				fs.setRecurrence(rs.getString("recurrence"));
				fs.setFood(rs.getString("food"));
				fs.setNotes(rs.getString("notes"));
				
				schedules.add(fs);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return schedules;
	}

	@Override
	public FeedingSchedule getFeedingSchedule(Animal animal) {
		Connection connection = null;
		Statement stmt = null;
		FeedingSchedule fs = new FeedingSchedule();

		try {
			connection = DAOUtilities.getConnection();

			stmt = connection.createStatement();

			String sql = "SELECT * FROM ANIMALS";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				if (rs.getLong("animalid") == animal.getAnimalID()) {
					fs.setScheduleID(rs.getLong("schedule_ID"));
					fs.setFeedingTime(rs.getString("feeding_time"));
					fs.setRecurrence(rs.getString("recurrence"));
					fs.setFood(rs.getString("food"));
					fs.setNotes(rs.getString("notes"));
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return fs;
	}

	@Override
	public void assignFeedingSchedule(long schedule_ID, long animalID) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET feeding_schedule = ? WHERE animalid = ?";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from schedule into PreparedStatement
			stmt.setLong(1, schedule_ID);
			stmt.setLong(2, animalID);
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Assign FeedingScheduleID[" + schedule_ID + "] to AnimalID[" + animalID + "] failed");
		}
	}

	@Override
	public void removeFeedingSchedule(long animalID) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET feeding_schedule = ? WHERE animalid = ?";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from schedule into PreparedStatement
			stmt.setLong(1, 0L);
			stmt.setLong(2, animalID);
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Remove AnimalID[" + animalID + "] feeding schedule failed");
		}
	}
	@Override
	public void updateFeedingSchedule(FeedingSchedule schedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE FEEDING_SCHEDULES SET feeding_time=?, recurrence=?, food=?, notes=? WHERE schedule_ID=?";

			// Setup PreparedStatement
			stmt = connection.prepareStatement(sql);

			// Add parameters from schedule into PreparedStatement
			stmt.setString(1, schedule.getFeedingTime());
			stmt.setString(2, schedule.getRecurrence());
			stmt.setString(3, schedule.getFood());
			stmt.setString(4, schedule.getNotes());
			stmt.setLong(5, schedule.getScheduleID());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		if (success == 0) {
			// then update didn't occur, throw an exception
			throw new Exception("Update feeding schedule failed: " + schedule);
		}
	}
}
