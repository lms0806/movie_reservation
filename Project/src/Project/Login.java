/* �α���, ȸ������ â(������ ��) */
package Project;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame{
	JTextField id;
	JPasswordField password;
	JButton login, sign;
	String idstring = "";
	User user = new User();
	public Login(String theater, String name, String timeseat) {
		User user = new User();
		setLayout(new GridLayout(0, 2));
		add(new JLabel("���̵�", JLabel.CENTER));
		add(id = new JTextField());
		add(new JLabel("��й�ȣ", JLabel.CENTER));
		add(password = new JPasswordField());
		password.setEchoChar('*');
		
		sign = new JButton("ȸ������");
		sign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				new Sign();
			}
		});
		
		login = new JButton("�α���");
		login.addActionListener(e -> {
			try {
				user.setid(id.getText());
				user.setpassword(password.getText());
				DatabaseUser login = new DatabaseUser(user,0);
				idstring = login.result;
				new Ticketing(idstring, theater, name, timeseat);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		add(sign);
		add(login);
		setSize(350, 200);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}
