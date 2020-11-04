/* 사용자 관련 정보들을 다룸*/
package Project;

public class User {
	String id, password, name, gender;
	int phone, point;
	
	public String getid() {
		return id;
	}
	public String getpassword() {
		return password;
	}
	public String getname() {
		return name;
	}
	public String getgender() {
		return gender;
	}
	public int getphone() {
		return phone;
	}
	public int getpoint() {
		return point;
	}
	
	public void setid(String id) {
		this.id = id;
	}
	public void setpassword(String password) {
		this.password = password;
	}
	public void setname(String name) {
		this.name = name;
	}
	public void setgender(String gender) {
		this.gender = gender;
	}
	public void setphone(int phone) {
		this.phone = phone;
	}
	public void setpoint(int point) {
		this.point = point;
	}
}
