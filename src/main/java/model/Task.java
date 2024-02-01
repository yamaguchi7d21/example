package model;
import java.sql.Date;

public class Task {
	private int id;
	private String description;
	private Date dueDate;
	private String importance;
	private String status;
	
	public Task(String description, Date dueDate, String importance) {
		this.description = description;
		this.dueDate = dueDate;
		this.importance = importance;
		status = "未着手";
	}
	
	public Task(int id,String description, Date dueDate, String importance, String status) {
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.importance = importance;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Date getDueDate() {
		return dueDate;
	}
	
	public String getImportance() {
		return importance;
	}
	
	public String getStatus() {
		return status;
	}
}
