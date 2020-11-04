/* 사용자가 예매한 영화의 종류들 가져옴 */

package Project;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HistoryTotal extends JFrame{
	JLabel title = new JLabel("예매한 영화 목록");
	JLabel[] list;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	public HistoryTotal(Movie movie) throws SQLException {
		super("영화 예매 목록");
		DatabaseMovie data = new DatabaseMovie(movie,1);
		
		for(int i = 0; i < data.list.size(); i++) {
			System.out.println(data.list.get(i).getmoviename());
		}
		
		panel.add(title);
		add(panel, BorderLayout.NORTH);
		
		list = new JLabel[data.list.size()];
		
		for(int i = 0; i < data.list.size(); i++) {
			list[i] = new JLabel("영화관 : " + data.list.get(i).gettheater() + ", 시간 : " + data.list.get(i).getmovietime());
		}
		for(int i = 0; i < data.list.size(); i++) {
			panel1.add(list[i]);
		}
		add(panel1);
		
		for(int i = 0; i < list.length; i++) {
			list[i].addMouseListener(new java.awt.event.MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	            	String spli = ((JLabel) e.getSource()).getText();
	            	for(int j = 0; j < data.list.size(); j++) {
    					if(data.list.get(j).gettheater().equals(spli.substring(6,spli.indexOf(",")))) {
    						new History(data.list.get(j));
    					}
    				}
	            }
	        });
		}
		
		setSize(300,400);
		setLocationRelativeTo(null);//창가운데 띄우기
		setVisible(true);
	}
}
