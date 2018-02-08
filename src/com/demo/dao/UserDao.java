package com.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import com.demo.bean.UserBean;
import com.demo.dao.DBconnection;
import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class UserDao {
	DBconnection dBconnection = new DBconnection();
	
	public boolean registe(UserBean userbean) throws Exception{
		Connection conn = dBconnection.getConn();
		String sql = "insert into user_table values (?,?,?,?)";
		PreparedStatement prest = dBconnection.getprep(conn, sql);	
		prest.setString(1, userbean.getName());
		prest.setString(2, userbean.getPwd());
		prest.setString(3, userbean.getEmail());
		prest.setString(4, userbean.getPhone());
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}
	
//	public boolean login(UserBean userBean) throws Exception{
//		Connection connection = dBconnection.getConn();
//		String sql = "select * from user_table where Uname=? and Upass=?";
//		ResultSet rs = null;
//		PreparedStatement prest = dBconnection.getprep(connection, sql);
//		prest.setString(1, userBean.getName());
//		prest.setString(2, userBean.getPwd());
//		rs = prest.executeQuery();
//		while(rs.next()){
//			return true;
//		}
//		return false;
//	}
	public int login(UserBean userBean) throws Exception{
		Connection connection = dBconnection.getConn();
		UserBean user = new  UserBean();
		String sql = "select * from user_table where Uname=? and Upass=?";
		ResultSet rs = null;
		PreparedStatement prest = dBconnection.getprep(connection, sql);
		prest.setString(1, userBean.getName());
		prest.setString(2, userBean.getPwd());
		rs = prest.executeQuery();
		while(rs.next()){
			user.setCid(rs.getInt("Uid"));
		}
		return user.getCid();
	}
	
	public UserBean selectuserinfo(int id) throws Exception{
		UserBean user = new  UserBean();
		Connection connection = dBconnection.getConn();
		String sql = "select * from user_table where Uid=?";
		ResultSet rs = null;
		PreparedStatement prest = dBconnection.getprep(connection, sql);
		prest.setInt(1, id);
		rs = prest.executeQuery();
		while(rs.next()){
			user.setCid(rs.getInt("Uid"));
			user.setName(rs.getString("Uname"));
			user.setEmail(rs.getString("Uemail"));
			user.setPhone(rs.getString("Uphone"));
			user.setPwd(rs.getString("Upass"));
		}
		return user;
	}
	
	public boolean updateuserinfo(UserBean userBean)throws Exception{
		Connection connection = dBconnection.getConn();
		String sql = "update user_table set Uname=?,Upass=?,Uemail=?,Uphone=? where Uid=?";
		PreparedStatement prest = dBconnection.getprep(connection, sql);
		prest.setString(1, userBean.getName());
		prest.setString(2, userBean.getPwd());
		prest.setString(3, userBean.getEmail());
		prest.setString(4, userBean.getPhone());
		prest.setInt(5, userBean.getCid());
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;	
	}
}
