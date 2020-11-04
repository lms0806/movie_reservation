/* 사용자관련 데이터베이스 */

package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseUser {
	static Statement stmt;
	static ResultSet rs,rs1;
	static String result = "";
	public DatabaseUser(User user, int num) throws SQLException{
		Connection con = makeConnection();
		stmt = con.createStatement();
		if(num == 0) {
			check(user);
		}
		if(num == 1) {
			insert(user);
		}
	}
	
	private static Connection makeConnection() {
		String url = "jdbc:mysql://localhost:3306/movie?serverTimezone=UTC";
		String id = "root";
		String password = "3510";
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터베이스 연결 성공");
		} catch(ClassNotFoundException e) {
			System.out.println("드라이버를 찾을 수 없습니다.");
		} catch(SQLException e) {
			System.out.println("연결에 실패하였습니다.");
		}
		return con;
	}
	
	public static void check(User user) {
		try {
			if(!user.getid().equals("") && !user.getpassword().equals("")) {
				rs1 = stmt.executeQuery("select * From user where id = '" + user.getid() + "';");
				rs1.next();
				System.out.println(rs1.getString("password"));
				System.out.println(user.getpassword());
				if(rs1.getString("password").equals(user.getpassword())) {
					result = rs1.getString("id");
					user.setid(result);
				}
				else {
					result = "null";
				}
			}
		} catch(SQLException e) {
			result = "null";
			//System.out.println("존재하지않는 아이디입니다.");
			e.printStackTrace();
		}	
	}
	
	public static void insert(User user) {
		try {
			stmt.executeUpdate("INSERT INTO user VALUES ('" + user.getid() + "','" + user.getpassword() + "','" + user.getname() + "','" + user.getgender() + "'," + user.getphone() + "," + user.getpoint() + ")");
			System.out.println("아이디 생성");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}