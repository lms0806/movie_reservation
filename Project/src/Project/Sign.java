/* ȸ������ â */
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
		panel.add(new JLabel("�̸�", JLabel.CENTER));
		panel.add(name = new JTextField());
		panel.add(new JLabel("���̵�", JLabel.CENTER));
		panel.add(id = new JTextField());
		panel.add(new JLabel("��й�ȣ", JLabel.CENTER));
		panel.add(password = new JTextField());
		panel.add(new JLabel("��й�ȣ Ȯ��", JLabel.CENTER));
		panel.add(password1 = new JTextField());
		panel.add(new JLabel("��ȭ��ȣ", JLabel.CENTER));
		panel.add(phone = new JTextField());
		panel.add(new JLabel("����", JLabel.CENTER));
		gender = new Choice();
		gender.addItem("��");
		gender.addItem("��");
		panel.add(gender);
		
		add(panel);
		
		input = new JButton("�Է�");
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
						JOptionPane.showMessageDialog(null, "��й�ȣ�� ���� ��ġ���� �ʽ��ϴ�.");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ���̵��Դϴ�.");
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		panel1.add(input);
		panel1.setLayout(new GridLayout(0,1));
		add(panel1,BorderLayout.SOUTH);
		
		setSize(350, 200);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}
