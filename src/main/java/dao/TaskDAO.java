package dao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Task;

/**
 * The Data Access Object (DAO) class for Task entities.
 * This class handles the interaction with the database for Task-related operations.
 */

public class TaskDAO {
	private final String JDBC_URL = "jdbc:postgresql://localhost:5432/final";
	private final String DB_USER = "postgres";
	private final String DB_PASS = "admin";
	
	/**
	 * Retrieves a list of all tasks from the database.
	 * 
	 * @return A list of Task objects representing tasks in the database.
	 */
	
	public List<Task> findAll() {
		List<Task> tasklist = new ArrayList<Task>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "SELECT ID, DESCRIPTION, DUEDATE, IMPORTANCE, STATUS FROM TASKS ORDER BY DUEDATE;";
			try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
				return executeQuery(pStmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Inserts a new task into the database.
	 * 
	 * @param task The Task object to be inserted.
	 * @return A list of Task objects representing tasks in the database after insertion.
	 */
	
	public List<Task> insertTask(Task task) {
//		List<Task> tasklist = new ArrayList<Task>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "INSERT INTO TASKS (DESCRIPTION, DUEDATE, IMPORTANCE, STATUS) VALUES (?, ?, ?, ?);";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
			try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
				setTaskParameters(pStmt, task);
				pStmt.executeUpdate();
				return findAll();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * Updates an existing task in the database.
	 * 
	 * @param task The Task object containing updated information.
	 * @return A list of Task objects representing tasks in the database after the update.
	 */
	
	public List<Task> updateTask(Task task) {
		List<Task> tasklist = new ArrayList<Task>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "UPDATE TASKS SET DESCRIPTION = ?, DUEDATE = ?, IMPORTANCE = ?, STATUS = ? WHERE ID = ?;";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
			try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
				setTaskParameters(pStmt, task);
				pStmt.setInt(5,  task.getId());
				pStmt.executeUpdate();
				return findAll();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Deletes a task from the database based on its ID.
	 * 
	 * @param id The ID of the task to be deleted.
	 * @return A list of Task objects representing tasks in the database after deletion.
	 */
	
	public List<Task> deleteTask(int id) {
		List<Task> tasklist = new ArrayList<Task>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS)) {
			String sql = "DELETE FROM TASKS WHERE ID = ?;";
//			PreparedStatement pStmt = conn.prepareStatement(sql);
			try (PreparedStatement pStmt = conn.prepareStatement(sql)) {
				pStmt.setInt(1,  id);
				pStmt.executeUpdate();
				return findAll();
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private List<Task> executeQuery(PreparedStatement pStmt) throws SQLException {
		List<Task> taskList = new ArrayList<>();
		try (ResultSet rs = pStmt.executeQuery()) {
			while (rs.next()) {
				int id = rs.getInt("ID");
				String description =rs.getString("DESCRIPTION");
				Date dueDate = rs.getDate("DUEDATE");
				String importance = rs.getString("IMPORTANCE");
				String status = rs.getString("STATUS");
				
				Task task = new Task(id, description, dueDate, importance, status);
				taskList.add(task);
			}
		}
		return taskList;
	}
	
	private void setTaskParameters(PreparedStatement pStmt, Task task) throws SQLException {
		pStmt.setString(1,  task.getDescription());
		pStmt.setDate(2,  task.getDueDate());
		pStmt.setString(3, task.getImportance());
		pStmt.setString(4, task.getStatus());
	}
}
