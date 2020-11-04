/* ����â���� ���� ������ Ȱ���� �ְ�,�ϰ� �ڽ����ǽ� ���� ������ */
package Project;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class boxofficerank extends JFrame {
	static int sel;
	String[] title = {"����", "��ȭ ����", "������", "����������"};
	String[][] strss = new String[10][4];
	JTable table;
	JScrollPane scroll;
	JButton btn = new JButton("�ݱ�");
	   
	public boxofficerank(int num, String address) {
		try {
			   Document doc = Jsoup.connect(address).timeout(12000).get();
			   Elements maincontents;
			   
			   if(num == 0) {
				   maincontents = doc.select("weeklyBoxOffice");
				   setTitle("�ְ��ڽ����ǽ�");
			   }
			   else {
				   maincontents = doc.select("dailyBoxOffice");
				   setTitle("�ϰ��ڽ����ǽ�");
			   }
			   Elements contents = maincontents.select("movieNm");
			   
			   int idx = 0;
			   for(Element element : contents) {
				   if(idx < 10) {
					   strss[idx][1] = element.text().toString();
					   strss[idx][0] = Integer.toString(idx+1);
				   }
				   else {
					   break;
				   }
				   idx++;
			   }
			   
			   Elements content = maincontents.select("openDt");
			   idx = 0;
			   for(Element element : content) {
				   if(idx < 10) {
					   strss[idx][2] = element.text().toString();
				   }
				   else {
					   break;
				   }
				   idx++;
			   }
			   
			   Elements cont = maincontents.select("audiAcc");
			   idx = 0;
			   for(Element element : cont) {
				   if(idx < 10) {
					   strss[idx][3] = element.text().toString();
				   }
				   else {
					   break;
				   }
				   idx++;
			   }
			   
		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
		
		DefaultTableModel model = new DefaultTableModel(strss,title);
		table = new JTable(model);
		scroll = new JScrollPane(table);
		add(scroll);
		add(btn, BorderLayout.SOUTH);
		btn.addActionListener(e -> {
			dispose();
		});
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setSize(450,225);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}

class boxofficeselect{
	private static String addressday = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.xml?key=430156241533f1d058c603178cc3ca0e&targetDt=";//�Ľ��ϰ��� �ϴ� ����Ʈ
	private static String addressweek = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchWeeklyBoxOfficeList.xml?key=430156241533f1d058c603178cc3ca0e&targetDt=";//�Ľ��ϰ��� �ϴ� ����Ʈ
	String address = "";
	Time time = new Time();
	public boxofficeselect(int num) {
		if(num == 0) {
			address+=addressweek;
			address+=Integer.toString(time.getnum()-7);
		}
		else {
			address+=addressday;
			address+=Integer.toString(time.getnum());
		}
		new boxofficerank(num, address);
	}
}

class Time{
	int num;
	public Time() {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
			
		Date time = new Date();
					
		String time1 = format1.format(time);
		int num = Integer.parseInt(time1)-1;
		setnum(num);
	}
	public int getnum() {
		return num;
	}
	public void setnum(int num) {
		this.num = num;
	}
}
