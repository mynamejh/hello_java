package co.edu.common;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DAO {
	public Connection conn;
	public Statement stmt;
	public ResultSet rs;
	public PreparedStatement psmt;


	public Connection getConnect() {
		try {
			
			Properties prop = new Properties();
			prop.load(new FileReader("C:/Temp/database.properties"));
			String driver = prop.getProperty("driver"); //key값 불러옴
			String url = prop.getProperty("url"); //key에 해당되는 값
			String id = prop.getProperty("user");
			String pass = prop.getProperty("pass");
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pass);
			
//			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "pih", "pih");
			System.out.println("success");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error");
		}
		return conn;
	}


	public void disconnect() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
