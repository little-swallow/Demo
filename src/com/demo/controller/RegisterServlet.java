package com.demo.controller;

import java.io.Console;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.demo.bean.UserBean;
import com.demo.dao.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String name = request.getParameter("rname");
		String pwd = request.getParameter("rpwd");
		String email = request.getParameter("remail");
		String phone = request.getParameter("rphone");

		UserBean user = new UserBean();
		user.setName(name);
		user.setPwd(pwd);
		user.setEmail(email);
		user.setPhone(phone);
		
		boolean flag = false;
		String msg = "";
		UserDao userDao = new UserDao();
		try {
			flag = userDao.registe(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if(flag) {
			msg="×¢²á³É¹¦";
			session.setAttribute("Msg", msg);
			response.sendRedirect("../../../view/login.jsp");
		}else {
			msg="×¢²áÊ§°Ü";
			session.setAttribute("Msg", msg);
			response.sendRedirect("../../../view/login.jsp");
		}
		
		
		
	}

}
