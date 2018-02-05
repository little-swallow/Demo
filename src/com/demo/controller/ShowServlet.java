package com.demo.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowServlet
 */
@WebServlet("/ShowServlet")
public class ShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String storeDirectory = getServletContext().getRealPath("/WEB-INF/files");  
	        File root = new File(storeDirectory);  
	          
	        //用Map保存递归的文件名：key:UUID文件名   value:老文件名  
	        Map<String, String> map = new HashMap<String, String>();  
	        treeWalk(root,map);           
	        request.setAttribute("map", map);  
	        request.getRequestDispatcher("/view/testshowfile.jsp").forward(request, response);  

	    }  
	 private void treeWalk(File root, Map<String, String> map) {  
	        if(root.isFile()){  
	            String fileName = root.getName();//文件名       
	            String oldFileName = fileName.substring(fileName.indexOf("_")+1);  
	            map.put(fileName, oldFileName);  
	        }else{  
	            File fs[] = root.listFiles();  
	            for(File file:fs){  
	                treeWalk(file, map);  
	            }  
	        }  
	          
	    }  


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
