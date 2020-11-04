/* cgv �󿵽ð�ǥ�� cgv ����Ʈ���� ũ�Ѹ��ؿ� ������ */
package Project;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MovieSchedule extends JFrame{
	private final static String address = "http://www.cgv.co.kr/reserve/show-times/";//�Ľ��ϰ��� �ϴ� ����Ʈ
	String str = new String();
	String[] title = {"����", "���", "��õ", "����", "����/��û", "����/��û", "�뱸", "�λ�/���", "�λ�/���", "���", "����/����/����", "����/����/����", "����/����/����"};
	String[] titles = {"����", "���", "��õ", "����", "����/��û", "�뱸", "�λ�/���", "���", "����/����/����"};
	int[] titlenum = {1, 2, 202, 12, 3, 205, 11, 5, 207, 204, 4, 6, 206};
	
	String[] s;
	String[] s1;
	
	JLabel[] lbl = null;
	
	JPanel panelseoul = new JPanel();
	JPanel panelgye = new JPanel();
	JPanel panelincheon = new JPanel();
	JPanel panelgangwon = new JPanel();
	JPanel panelchung = new JPanel();
	JPanel paneldaegu = new JPanel();
	JPanel panelbusan = new JPanel();
	JPanel panelcurrent = new JPanel();
	JPanel paneljeju = new JPanel();//�������� �гε� ����
	JTabbedPane tp = new JTabbedPane();//�г���ȯ�ҋ� ���
	
	public MovieSchedule()  {
		setTitle("�󿵽ð�ǥ");
		try {
			Document doc = Jsoup.connect(address).get();
			Elements contents = doc.select("div").select("#contents").select("script");
			
			str = contents.toString();
			String[] regioncode = str.split("RegionCode\":");
			String[] theatercode = str.split("TheaterCode\":");//�ش��ϴ� var �κ� ������
			String[] theatername = str.split("TheaterName\":");
			
			s = new String[regioncode.length-2];//���� ��ġ(ex)����, ���)
			s1 = new String[theatercode.length-1];//���� �ڵ� ��ȣ
			String[] s2 = new String[theatername.length-1];//���� ��ġ
			
			for(int i = 0; i < s.length; i++) {
				s[i] = regioncode[i+1].substring(regioncode[i+1].indexOf("\"")+1, regioncode[i+1].indexOf("\","));//��з�(ex) ���� : 01);
				
				for(int j = 0; j < titlenum.length; j++) {
					if(s[i].substring(0,1).equals("0")) {
						s[i] = s[i].substring(1);
					}
					if (s[i].indexOf(",") >= 0){
						s[i] = s[i].substring(0,1);
					}
					if(s[i].equals(Integer.toString(titlenum[j]))){
						s[i] = title[j];
					}
				}
			}//������ ������
			
			for(int i = 1; i < titles.length; i++) {
				for(int j = 0; j < s.length; j++) {
					if(s[j].equals(titles[i])) {
						s[j] = " ";
						break;
					}
				}
			}
			s[0] = " ";
			int j = 0;
			for(int i = 0; i < s.length; i++) {
				if(!s[i].equals(" ")) {
					s[j] = s[i];
					j++;
				}
			}//������ 1���� ����(�� ������)
			
			for(int i = 0; i < s1.length; i++) {
				s1[i] = theatercode[i+1].substring(1,5);//��ȭ�� �ڵ�
			}//��ȭ�� �ڵ� ��������
			for(int i = 0; i < s2.length; i++) {
				s2[i] = theatername[i+1].substring(theatername[i+1].indexOf("\"")+1, theatername[i+1].indexOf("\","));//��ȭ�� ��
			}//��ȭ�� �� ��������
			
			makebtn(s,s1,s2);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void makebtn(String[] s, String[] s1, String[] s2) {
		JLabel[] lbl = new JLabel[s2.length];//��ư ����(��ȭ�� ������ ���� ũ�� ����)
		JButton[] close = new JButton[lbl.length];
		
		for(int i = 0; i < close.length; i++) {
			close[i] = new JButton("�ݱ�");
			close[i].setBorderPainted(false);
			close[i].setContentAreaFilled(false);
		}
		
		for(int i = 0; i < s2.length; i++) {
			lbl[i] = new JLabel(s2[i]);// + "(" + s1[i] + ")");
		}
		
		for(int i = 0; i < s2.length; i++) {
			if(s[i].equals("����")) {
				panelseoul.add(lbl[i]);
				panelseoul.add(new JLabel(" / "));
			}
			else if(s[i].equals("���")) {
				panelgye.add(lbl[i]);
				panelgye.add(new JLabel(" / "));
			}
			else if(s[i].equals("��õ")) {
				panelincheon.add(lbl[i]);
				panelincheon.add(new JLabel(" / "));
			}
			else if(s[i].equals("����")) {
				panelgangwon.add(lbl[i]);
				panelgangwon.add(new JLabel(" / "));
			}
			else if(s[i].equals("����/��û")) {
				panelchung.add(lbl[i]);
				panelchung.add(new JLabel(" / "));
			}
			else if(s[i].equals("�뱸")) {
				paneldaegu.add(lbl[i]);
				paneldaegu.add(new JLabel(" / "));
			}
			else if(s[i].equals("�λ�/���")) {
				panelbusan.add(lbl[i]);
				panelbusan.add(new JLabel(" / "));
			}
			else if(s[i].equals("���")) {
				panelcurrent.add(lbl[i]);
				panelcurrent.add(new JLabel(" / "));
			}
			else if(s[i].equals("����/����/����")) {
				paneljeju.add(lbl[i]);
				paneljeju.add(new JLabel(" / "));
			}
			panelseoul.add(close[0], BorderLayout.SOUTH);
			panelgye.add(close[1]);
			panelincheon.add(close[2]);
			panelgangwon.add(close[3]);
			panelchung.add(close[4]);
			paneldaegu.add(close[5]);
			panelbusan.add(close[6]);
			panelcurrent.add(close[7]);
			paneljeju.add(close[8]);
		}//�гο� ��ư�߰�
		
		for(int i = 0; i < lbl.length; i++) {
			lbl[i].addMouseListener(new java.awt.event.MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                    String spli = ((JLabel) e.getSource()).getText();
	                    for(int j = 0; j < s2.length; j++) {
	    					if(spli.equals(s2[j])) {
	    						new selectmovietime(s,s1,s2,j);
	    						//System.out.println(s[j] + "\n" + s1[j] + "\n" + s2[j]);
	    					}
	    				}
	            }
	        });
		}
		tp.add("����",panelseoul);
		tp.add("���",panelgye);
		tp.add("��õ",panelincheon);
		tp.add("����",panelgangwon);
		tp.add("����/��û",panelchung);
		tp.add("�뱸",paneldaegu);
		tp.add("�λ�/���",panelbusan);
		tp.add("���",panelcurrent);
		tp.add("����/����/����",paneljeju);//�� ��ȭ�� ������ �г� �߰�
		add(tp);
		
		for(int i = 0; i < lbl.length; i++) {
			close[i].addActionListener(e -> {
				dispose();
			});
		}
		setSize(600, 400);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}