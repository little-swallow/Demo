package com.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.demo.bean.SourceBean;

public class SourceDao {
	DBconnection dBconnection = new DBconnection();
	
	public boolean uploadfile(SourceBean sourceBean,int id) throws Exception{
		Connection conn = dBconnection.getConn();
		String sql = "insert into source_table values (?,?,?,?,?,?,?,?,?)";
		PreparedStatement prest = dBconnection.getprep(conn, sql);	
		prest.setString(1, sourceBean.getSname());
		prest.setString(2, sourceBean.getSuname());
		prest.setString(3, sourceBean.getDescribe());
		prest.setString(4, sourceBean.getSourcesort());
		prest.setString(5, sourceBean.getIntro());
		prest.setInt(6, id);
		prest.setInt(7, sourceBean.getViewcount());
		prest.setInt(8, sourceBean.getDlcount());
		prest.setString(9, sourceBean.getSdate());
		int i = prest.executeUpdate();
		if(i>0){
			return true;
		}
		return false;
	}

}
