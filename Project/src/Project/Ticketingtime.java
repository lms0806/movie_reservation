/* ������ ��ȭ�� ���� �ð��� ���� �¼��� ������ */
package Project;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Ticketingtime extends JFrame{
	JButton[] btn;
	JPanel panel = new JPanel();
	
	public Ticketingtime(String theater, String age, String name, String show, String address) {
		System.out.println(address);
		
		try {
			Document doc = Jsoup.connect(address).get();
			int idx = 0;
			int size = 0;
			int num = 0;
			int bt = 0;
			
			Elements test = doc.select("div.col-times");
			String[] tests = new String[test.size()];
			String[] testname = new String[tests.length];
			String[] testsplit;
			
			for(Element element : test) {
				tests[idx] = element.text().toString();
				testname[idx] = tests[idx].substring(tests[idx].indexOf("�� ")+2, tests[idx].indexOf("��")-3);
				tests[idx] = tests[idx].split("�� ")[1];
				idx++;
			}
			
			for(int i = 0; i < testname.length; i++) {
				if(testname[i].equals(name)) {
					num = i;
					break;
				}
			}
			
			testsplit = tests[num].split(" ");
			for(int i = 0; i < testsplit.length; i++) {
				if(testsplit[i].contains("�ܿ��¼�") && !testsplit[i].contains("����")) {
					//System.out.println(testsplit[i]);
					size++;
				}
			}
			JButton[] btn = new JButton[size];
			
			for(int i = 0; i < testsplit.length; i++) {
				if(testsplit[i].contains("�ܿ��¼�") && !testsplit[i].contains("����")) {
					System.out.println(testsplit[i]);
					btn[bt] = new JButton(testsplit[i]);
					bt++;
				}
			}
			
			for(int i = 0; i < size; i++) {
				panel.add(btn[i]);
				btn[i].addActionListener(e -> {
					JButton text = (JButton)e.getSource();
					new Login(theater, name, text.getText());
				});
			}
			add(panel);
			
			panel.setLayout(new GridLayout(size,1));//���ٷ� �ϱ�
			setSize(400, 300);
			setLocationRelativeTo(null);//â��� ����
			setVisible(true);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
