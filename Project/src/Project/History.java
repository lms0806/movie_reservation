/* ����ڰ� ������ ��ȭ�� Ȯ�� �� ���� */
package Project;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class History extends JFrame {
	JTextField id, theater, name, time, seat, price, person, cardnum;
	JButton previous, next, close, cancel;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	int count = 0;
	public History(Movie movie) {
		super("��ȭ ���� ����");
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("���̵�", JLabel.CENTER));
		panel.add(id = new JTextField());
		panel.add(new JLabel("����", JLabel.CENTER));
		panel.add(theater = new JTextField());
		panel.add(new JLabel("��ȭ�̸�", JLabel.CENTER));
		panel.add(name = new JTextField());
		panel.add(new JLabel("�ð�", JLabel.CENTER));
		panel.add(time = new JTextField());
		panel.add(new JLabel("�¼�", JLabel.CENTER));
		panel.add(seat = new JTextField());
		panel.add(new JLabel("����", JLabel.CENTER));
		panel.add(price = new JTextField());
		panel.add(new JLabel("�ο� ��", JLabel.CENTER));
		panel.add(person = new JTextField());
		panel.add(new JLabel("ī�� ��ȣ", JLabel.CENTER));
		panel.add(cardnum = new JTextField());
		add(panel);
		
		cancel = new JButton("���");
		close = new JButton("�ݱ�");

		panel1.add(cancel);
		panel1.add(close);
		panel1.setLayout(new GridLayout(0,2));
		add(panel1,BorderLayout.SOUTH);

		cancel.addActionListener(e -> {
			try {
				new DatabaseMovie(movie,3);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		close.addActionListener(e -> {
			dispose();
		});
		
		id.setText(movie.getid());
		theater.setText(movie.gettheater());
		name.setText(movie.getmoviename());
		time.setText(movie.getmovietime());
		seat.setText(movie.getmovieseat());
		price.setText(Integer.toString(movie.getmovieprice()));
		person.setText(Integer.toString(movie.getmovieperson()));
		cardnum.setText(Integer.toString(movie.getcardnumber()));
		
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(400, 250);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}
