/* �α���, ȸ������ â(����Ȯ���� ��) */
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
		super("�α���");
		setLayout(new GridLayout(0, 2));
		add(new JLabel("���̵�", JLabel.CENTER));
		add(id = new JTextField());
		add(new JLabel("��й�ȣ", JLabel.CENTER));
		add(password = new JPasswordField());
		password.setEchoChar('*');
		
		sign = new JButton("ȸ������");
		sign.addActionListener(e -> {
			new Sign();
		});
		
		login = new JButton("�α���");
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
					JOptionPane.showMessageDialog(null, "��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		add(sign);
		add(login);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 200);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}
