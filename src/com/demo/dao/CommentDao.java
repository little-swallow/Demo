package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.demo.bean.CommentBean;

public class CommentDao {
	DBconnection dBconnection = new DBconnection();
	
	public boolean commentsource(CommentBean commentBean,int uid,int sid) throws Exception{
		Connection conn = dBconnection.getConn();
		String sql = "insert into com_table values (?,?,?,?)";
		PreparedStatement prest = dBconnection.getprep(conn, sql);	
		prest.setString(1, commentBean.getScont());
		prest.setString(2, commentBean.getSctime());
		prest.setInt(3, uid);
		prest.setInt(4, sid);
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}

	public ArrayList<CommentBean> selectbysid(int id) throws Exception{
		ArrayList<CommentBean> commentinfo = new ArrayList<CommentBean>();
		Connection connection  = dBconnection.getConn();
		PreparedStatement prest =  null;
		ResultSet rs = null;
		String sql = "select Cid,Ctext,Ctime,Uname from source_table,user_table,com_table "
				+ "where com_table.Uid = user_table.Uid and com_table.Sid = source_table.Sid and "
				+ "source_table.Sid=?";
		prest = dBconnection.getprep(connection, sql);
		prest.setInt(1, id);
		rs = prest.executeQuery();
		while (rs.next()) {
			CommentBean commentBean = new CommentBean();
			commentBean.setScid(rs.getInt("Cid"));
			commentBean.setScont(rs.getString("Ctext"));
			commentBean.setSctime(rs.getString("Ctime"));
			commentBean.setScname(rs.getString("Uname"));
			commentinfo.add(commentBean);
		}
		return commentinfo;
	} 
}
