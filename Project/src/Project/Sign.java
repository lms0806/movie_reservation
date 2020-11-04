/* 회원가입 창 */
package Project;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Sign extends JFrame{
	User user = new User();
	String idstring = "";
	JTextField name, id, password, password1, phone;
	Choice gender;
	JButton input;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	public Sign() {
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("이름", JLabel.CENTER));
		panel.add(name = new JTextField());
		panel.add(new JLabel("아이디", JLabel.CENTER));
		panel.add(id = new JTextField());
		panel.add(new JLabel("비밀번호", JLabel.CENTER));
		panel.add(password = new JTextField());
		panel.add(new JLabel("비밀번호 확인", JLabel.CENTER));
		panel.add(password1 = new JTextField());
		panel.add(new JLabel("전화번호", JLabel.CENTER));
		panel.add(phone = new JTextField());
		panel.add(new JLabel("성별", JLabel.CENTER));
		gender = new Choice();
		gender.addItem("남");
		gender.addItem("여");
		panel.add(gender);
		
		add(panel);
		
		input = new JButton("입력");
		input.addActionListener(e -> {
			try {
				user.setid(id.getText());
				user.setpassword(password.getText());
				new DatabaseUser(user,0);
				idstring = user.getid();
				if(!user.getid().equals("")){
					if(password.getText().equals(password1.getText())) {
						try {
							user.setid(id.getText());
							user.setpassword(password.getText());
							user.setname(name.getText());
							user.setgender(gender.getSelectedItem());
							user.setphone(Integer.parseInt(phone.getText()));
							user.setpoint(0);
							new DatabaseUser(user,1);
							dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "비밀번호가 서로 일치하지 않습니다.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "이미 존재하는 아이디입니다.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		panel1.add(input);
		panel1.setLayout(new GridLayout(0,1));
		add(panel1,BorderLayout.SOUTH);
		
		setSize(350, 200);
		setLocationRelativeTo(null);//창가운데 띄우기
		setVisible(true);
	}
}
