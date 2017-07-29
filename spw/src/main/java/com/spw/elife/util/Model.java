package com.spw.elife.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * @author Administrator
 *
 */
public class Model {
	private String id;
	
	private Date createTime;
	
	private Date updateTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static  String DB_URL = "jdbc:mysql://localhost:3306/mysql?useUnicode=true&characterEncoding=UTF-8";

	
	static final String USER = "root";
	static final String PASS = "123456";
	

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		int ip_1 = 128;
		int ip_2 = 0;
		int ip_3 = 0;
		int ip_4 = 0;
		while(true){
		try {
				Class.forName("com.mysql.jdbc.Driver");
				String ip = ip_1+"."+ip_2+"."+ip_3+"."+ip_4;
				System.out.println("ip-->"+ip);
				DB_URL = DB_URL.replace("localhost", ip);
				conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
				stmt = (Statement) conn.createStatement();
				stmt.close();
				conn.close();
				System.out.println("Success!");
				if(ip_1<255)
				ip_1++;
				else if(ip_2<255)
				ip_2++;
				else if(ip_3<255)
				ip_3++;
				else if(ip_4<255)
				ip_4++;
		} catch (Exception e) {
			System.out.println("Fail!");
			if(ip_1<255)
			ip_1++;
			else if(ip_2<255)
			ip_2++;
			else if(ip_3<255)
			ip_3++;
			else if(ip_4<255)
			ip_4++;
			continue;
		}
		
	}
  }
	
	
}
