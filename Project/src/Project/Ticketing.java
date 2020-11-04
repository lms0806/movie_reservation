/* 인원수, 카드번호, 카드 유효기간 입력하여 영화예매 */
package Project;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Ticketing extends JFrame{
	Movie movie = new Movie();
	String time, seat;
	int totalprice, price, price1, totalperson;
	Choice personnumber, personnumber1;
	JTextField cardnumber, cardtime;
	JPanel panel,panel1, paneltop;
	JButton btn = new JButton("입력");
	public Ticketing(String idstring, String theater, String name, String timeseat) {
		super("예약");
		String time = timeseat.substring(0,timeseat.indexOf("잔"));
		System.out.println(time);
		String seat = timeseat.substring(timeseat.indexOf("잔"));
		System.out.println(seat);
		
		panel = new JPanel();
		panel1 = new JPanel();
		paneltop = new JPanel();
		
		paneltop.setLayout(new GridLayout(0,1));
		if(Integer.parseInt(time.substring(0,2)) < 12) {
			paneltop.add(new JLabel("조조", JLabel.CENTER));
			paneltop.add(new JLabel("성인, 청소년  : 6000원", JLabel.CENTER));
		}
		else {
			paneltop.add(new JLabel("일반", JLabel.CENTER));
			paneltop.add(new JLabel("성인 : 9000원 , 청소년  : 7000원", JLabel.CENTER));
		}//조조,일반
		
		panel.setLayout(new GridLayout(0, 2));
		panel.add(new JLabel("성인 인원수", JLabel.CENTER));
		
		personnumber = new Choice();
		for(int i = 0; i < 10; i++) {
			personnumber.addItem(Integer.toString(i));
		}
		panel.add(personnumber);
		
		panel.add(new JLabel("청소년 인원수", JLabel.CENTER));
		personnumber1 = new Choice();
		for(int i = 0; i < 10; i++) {
			personnumber1.addItem(Integer.toString(i));
		}
		panel.add(personnumber1);
		
		if(Integer.parseInt(time.substring(0,2)) < 12) {
			price  = Integer.parseInt(personnumber.getSelectedItem()) * 6000;
			price1 = Integer.parseInt(personnumber1.getSelectedItem()) * 6000;
		}
		else {
			price  = Integer.parseInt(personnumber.getSelectedItem()) * 9000;
			price1 = Integer.parseInt(personnumber1.getSelectedItem()) * 7000;
		}//조조,일반
		totalprice = price+price1;
		
		panel.add(new JLabel("카드 번호 : ", JLabel.CENTER));
		panel.add(cardnumber = new JTextField());
		
		panel.add(new JLabel("카드 유효기간 : ", JLabel.CENTER));
		panel.add(cardtime = new JTextField());
		
		panel1.setLayout(new GridLayout(0, 1));
		panel1.add(btn);
		
		btn.addActionListener(e -> {
			totalperson = Integer.parseInt(personnumber.getSelectedItem()) + Integer.parseInt(personnumber1.getSelectedItem());
			movie.setid(idstring);
			movie.settheater(theater);
			movie.setmoviename(name);
			movie.setmovietime(time);
			movie.setmovieperson(totalperson);
			movie.setmovieprice(totalprice);
			movie.setcardnumber(Integer.parseInt(cardnumber.getText()));
			new movieseat(movie);
			System.out.println("입력완료");
			dispose();
		});
		
		add(panel);
		add(panel1, BorderLayout.SOUTH);
		add(paneltop, BorderLayout.NORTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(350, 200);
		setLocationRelativeTo(null);//창가운데 띄우기
		setVisible(true);
	}
}
