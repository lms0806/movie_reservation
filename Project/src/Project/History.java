/* 사용자가 예매한 영화의 확인 및 삭제 */
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
		super("영화 예매 정보");
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("아이디", JLabel.CENTER));
		panel.add(id = new JTextField());
		panel.add(new JLabel("극장", JLabel.CENTER));
		panel.add(theater = new JTextField());
		panel.add(new JLabel("영화이름", JLabel.CENTER));
		panel.add(name = new JTextField());
		panel.add(new JLabel("시간", JLabel.CENTER));
		panel.add(time = new JTextField());
		panel.add(new JLabel("좌석", JLabel.CENTER));
		panel.add(seat = new JTextField());
		panel.add(new JLabel("가격", JLabel.CENTER));
		panel.add(price = new JTextField());
		panel.add(new JLabel("인원 수", JLabel.CENTER));
		panel.add(person = new JTextField());
		panel.add(new JLabel("카드 번호", JLabel.CENTER));
		panel.add(cardnum = new JTextField());
		add(panel);
		
		cancel = new JButton("취소");
		close = new JButton("닫기");

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
		setLocationRelativeTo(null);//창가운데 띄우기
		setVisible(true);
	}
}
