/* 로그인, 회원가입 창(내역확인할 때) */
package Project;

import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginFirst extends JFrame {
	JTextField id;
	JPasswordField password;
	JButton login, sign;
	String idstring = "";
	User user = new User();
	Movie movie = new Movie();
	public LoginFirst() {
		super("로그인");
		setLayout(new GridLayout(0, 2));
		add(new JLabel("아이디", JLabel.CENTER));
		add(id = new JTextField());
		add(new JLabel("비밀번호", JLabel.CENTER));
		add(password = new JPasswordField());
		password.setEchoChar('*');
		
		sign = new JButton("회원가입");
		sign.addActionListener(e -> {
			new Sign();
		});
		
		login = new JButton("로그인");
		login.addActionListener(e -> {
			try {
				user.setid(id.getText());
				System.out.println(user.getid());
				user.setpassword(password.getText());
				DatabaseUser login = new DatabaseUser(user,0);
				if(!login.result.equals("null")) {
					movie.setid(user.getid());
					if(user.getid().equals(movie.getid())) {
						//new History(movie);
						new HistoryTotal(movie);
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		add(sign);
		add(login);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 200);
		setLocationRelativeTo(null);//창가운데 띄우기
		setVisible(true);
	}
}
