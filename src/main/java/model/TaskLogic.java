package model;

import java.util.List;

import dao.TaskDAO;

/**
 * This class represents the business Logic for managing tasks.
 * @author yamaguchi yoshikazu
 *
 */

public class TaskLogic {
	private final TaskDAO dao;
	
	/**
	 * Constructs a new TaskLogic object.
	 */
	
	public TaskLogic() {
		this.dao = new TaskDAO();
	}
	
	/**
	 * Retrieves all tasks from the database.
	 * 
	 * @return A list of Task objects representing all tasks in the database.
	 */
	
	public List<Task> findAll() {
		return dao.findAll();
	}
	
	/**
	 * Inserts a new task into the database.
	 * 
	 * @param task The Task object to be inserted.
	 * @return A list of Task objects representing all tasks in the database after insertion.
	 */
	
	public List<Task> insert(Task task) {
		return dao.insertTask(task);
	}
	
	/**
	 * Updates an existing task in the database.
	 * 
	 * @param task The Task object containing updated information.
	 * @return A list of Task objects representing all tasks in the database after the update.
	 */
	
	public List<Task> update(Task task) {
		return dao.updateTask(task);
	}
	
	/**
	 * Deletes a task from the database.
	 * 
	 * @param id The ID of the task to be deleted.
	 * @return A list of Task objects representing all tasks in the database after deletion.
	 */
	
	public List<Task> delete(int id) {
		return dao.deleteTask(id);
	}
}
