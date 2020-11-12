/* 영화관련 데이터베이스 */

package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DatabaseMovie {
	static Statement stmt;
	static Connection con;
	static ResultSet rs,rs1;
	static String result = "";
	static ArrayList<Movie> list = new ArrayList<Movie>();
	public DatabaseMovie(Movie movie, int num) throws SQLException{
		con = makeConnection();
		stmt = con.createStatement();
		if(num == 0) {
			insert(movie);
		}
		if(num == 1) {
			search(movie.getid());
		}
		if(num == 2) {
			updateuserpoint(movie);
		}
		if(num == 3) {
			delete(movie);
		}
	}
	
	private static Connection makeConnection() {
		String url = "jdbc:mysql://localhost:****/movie?serverTimezone=UTC";
		String id = "****";
		String password = "******";
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
	
	public static void insert(Movie movie) {
		try {
			stmt.executeUpdate("INSERT INTO reservation VALUES ('" + movie.getid() + "','" + movie.gettheater()+ "','" + movie.getmoviename() 
					+ "','" + movie.getmovietime() + "','" + movie.getmovieseat() + "'," + movie.getmovieprice() + "," + movie.getmovieperson() + "," + movie.getcardnumber() + ")");
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public static ArrayList<Movie> search(String s) {
		try {
			rs1 = stmt.executeQuery("select * From reservation where id = '" + s + "';");
			list.clear();
			 while(rs1.next())
	         {
				Movie movie1 = new Movie();
	            movie1.setid(rs1.getString("id"));
	            movie1.settheater(rs1.getString("movietheater"));
	            movie1.setmoviename(rs1.getString("moviename"));
	            movie1.setmovietime(rs1.getString("movietime"));
	            movie1.setmovieseat(rs1.getString("movieseat"));
	            movie1.setmovieprice(rs1.getInt("movieprice"));
	            movie1.setmovieperson(rs1.getInt("movieperson"));
	            movie1.setcardnumber(rs1.getInt("cardnum"));
	            list.add(movie1);
	         }
			 if(rs1 != null) rs1.close();
		     if(stmt != null) stmt.close();
		     if(con != null) con.close();

			 return list;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void updateuserpoint(Movie movie) {
		try {
			stmt.executeUpdate("Update user set point = point + " + movie.getmovieprice()/100 + " where id  = '" + movie.getid() + "';");
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
	public static void delete(Movie movie) {
		try {
			stmt.executeUpdate("Delete from reservation where id = '" + movie.getid() + "' and movietheater = '" + movie.gettheater() + "' and moviename = '" + movie.getmoviename() + "' and movietime = '" + movie.getmovietime() + "';");
		} catch(SQLException e) {
			e.printStackTrace();
		}	
	}
}
