package servlet;


import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Task;
import model.TaskLogic;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/task")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private List<Task> tasks = new ArrayList<>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		TaskLogic tlogic = new TaskLogic();
		List<Task> tlist = tlogic.findAll();
		
		HttpSession session = request.getSession();
		session.setAttribute("tasks", tlist);
		
		request.getRequestDispatcher("/task.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String description = request.getParameter("description");
		String dueDateString = request.getParameter("dueDate");
		String importance = request.getParameter("importance");
		
		Date dueDate = Date.valueOf(dueDateString);		// 本当はバリデーションが必要
		
		Task newTask = new Task(description, dueDate, importance);
		
		TaskLogic tlogic = new TaskLogic();
		List<Task> tlist = tlogic.insert(newTask);
		
		HttpSession session = request.getSession();
		session.setAttribute("tasks", tlist);
		request.getRequestDispatcher("/task.jsp").forward(request, response);
		
	}

}
