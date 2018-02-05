package com.demo.controller;

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
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String name = request.getParameter("logname");
		String pwd = request.getParameter("logpass");
		UserBean user = new UserBean();
		user.setName(name);
		user.setPwd(pwd);
		
		UserDao userDao = new UserDao();
		boolean flag = false;
		String msg = "" ;
		try {
			flag = userDao.login(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag) {
			msg = "登录成功";
			UserBean userinfo = new UserBean();
			try {
				userinfo = userDao.selectuserinfo(user);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			session.setAttribute("Msg", msg);
			session.setAttribute("Userinfo", userinfo);
			session.setAttribute("Userid",userinfo.getCid());
			response.sendRedirect("../../../view/main.jsp");
		}else {
			msg = "用户名或密码错误";
			session.setAttribute("Msg", msg);
			response.sendRedirect("../../../view/login.jsp");
		}
	}
}
