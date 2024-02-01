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
 * Servlet implementation class ChangeTaskServlet
 */
@WebServlet("/ChangeTaskServlet")
public class ChangeTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("/changetask.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		int id = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		String dueDateString = request.getParameter("dueDate");
		String importance = request.getParameter("importance");
		String status = request.getParameter("status");
		
		Date dueDate = Date.valueOf(dueDateString);
		
		if(request.getParameter("change") != null) {
			
			Task updateTask = new Task(id, description, dueDate, importance, status);
		
			TaskLogic tlogic = new TaskLogic();
			List<Task> tlist = tlogic.update(updateTask);
		
			HttpSession session = request.getSession();
			session.setAttribute("tasks", tlist);
			request.getRequestDispatcher("/changetask.jsp").forward(request, response);
			
		} else if (request.getParameter("delete") != null) {
			TaskLogic tlogic = new TaskLogic();
			List<Task> tlist = tlogic.delete(id);
			
			HttpSession session = request.getSession();
			session.setAttribute("tasks", tlist);
			request.getRequestDispatcher("/changetask.jsp").forward(request, response);
		}
	}

}
