/* ����ڰ��� �����ͺ��̽� */

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
			System.out.println("����̹� ���� ����");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("�����ͺ��̽� ���� ����");
		} catch(ClassNotFoundException e) {
			System.out.println("����̹��� ã�� �� �����ϴ�.");
		} catch(SQLException e) {
			System.out.println("���ῡ �����Ͽ����ϴ�.");
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
			//System.out.println("���������ʴ� ���̵��Դϴ�.");
			e.printStackTrace();
		}	
	}
	
	public static void insert(User user) {
		try {
			stmt.executeUpdate("INSERT INTO user VALUES ('" + user.getid() + "','" + user.getpassword() + "','" + user.getname() + "','" + user.getgender() + "'," + user.getphone() + "," + user.getpoint() + ")");
			System.out.println("���̵� ����");
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}