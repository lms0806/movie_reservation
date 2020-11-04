/* cgv 상영시간표를 cgv 사이트에서 크롤링해와 보여줌 */
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
	private final static String address = "http://www.cgv.co.kr/reserve/show-times/";//파싱하고자 하는 사이트
	String str = new String();
	String[] title = {"서울", "경기", "인천", "강원", "대전/충청", "대전/충청", "대구", "부산/울산", "부산/울산", "경상", "광주/전라/제주", "광주/전라/제주", "광주/전라/제주"};
	String[] titles = {"서울", "경기", "인천", "강원", "대전/충청", "대구", "부산/울산", "경상", "광주/전라/제주"};
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
	JPanel paneljeju = new JPanel();//지역마다 패널들 지정
	JTabbedPane tp = new JTabbedPane();//패널전환할떄 사용
	
	public MovieSchedule()  {
		setTitle("상영시간표");
		try {
			Document doc = Jsoup.connect(address).get();
			Elements contents = doc.select("div").select("#contents").select("script");
			
			str = contents.toString();
			String[] regioncode = str.split("RegionCode\":");
			String[] theatercode = str.split("TheaterCode\":");//해당하는 var 부분 가져옴
			String[] theatername = str.split("TheaterName\":");
			
			s = new String[regioncode.length-2];//극장 위치(ex)서울, 경기)
			s1 = new String[theatercode.length-1];//극장 코드 번호
			String[] s2 = new String[theatername.length-1];//극장 위치
			
			for(int i = 0; i < s.length; i++) {
				s[i] = regioncode[i+1].substring(regioncode[i+1].indexOf("\"")+1, regioncode[i+1].indexOf("\","));//대분류(ex) 서울 : 01);
				
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
			}//지역명 가져옴
			
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
			}//지역명 1개씩 삭제(더 가져옴)
			
			for(int i = 0; i < s1.length; i++) {
				s1[i] = theatercode[i+1].substring(1,5);//영화관 코드
			}//영화관 코드 가져오기
			for(int i = 0; i < s2.length; i++) {
				s2[i] = theatername[i+1].substring(theatername[i+1].indexOf("\"")+1, theatername[i+1].indexOf("\","));//영화관 명
			}//영화관 명 가져오기
			
			makebtn(s,s1,s2);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void makebtn(String[] s, String[] s1, String[] s2) {
		JLabel[] lbl = new JLabel[s2.length];//버튼 생성(영화관 갯수에 맞춰 크기 지정)
		JButton[] close = new JButton[lbl.length];
		
		for(int i = 0; i < close.length; i++) {
			close[i] = new JButton("닫기");
			close[i].setBorderPainted(false);
			close[i].setContentAreaFilled(false);
		}
		
		for(int i = 0; i < s2.length; i++) {
			lbl[i] = new JLabel(s2[i]);// + "(" + s1[i] + ")");
		}
		
		for(int i = 0; i < s2.length; i++) {
			if(s[i].equals("서울")) {
				panelseoul.add(lbl[i]);
				panelseoul.add(new JLabel(" / "));
			}
			else if(s[i].equals("경기")) {
				panelgye.add(lbl[i]);
				panelgye.add(new JLabel(" / "));
			}
			else if(s[i].equals("인천")) {
				panelincheon.add(lbl[i]);
				panelincheon.add(new JLabel(" / "));
			}
			else if(s[i].equals("강원")) {
				panelgangwon.add(lbl[i]);
				panelgangwon.add(new JLabel(" / "));
			}
			else if(s[i].equals("대전/충청")) {
				panelchung.add(lbl[i]);
				panelchung.add(new JLabel(" / "));
			}
			else if(s[i].equals("대구")) {
				paneldaegu.add(lbl[i]);
				paneldaegu.add(new JLabel(" / "));
			}
			else if(s[i].equals("부산/울산")) {
				panelbusan.add(lbl[i]);
				panelbusan.add(new JLabel(" / "));
			}
			else if(s[i].equals("경상")) {
				panelcurrent.add(lbl[i]);
				panelcurrent.add(new JLabel(" / "));
			}
			else if(s[i].equals("광주/전라/제주")) {
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
		}//패널에 버튼추가
		
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
		tp.add("서울",panelseoul);
		tp.add("경기",panelgye);
		tp.add("인천",panelincheon);
		tp.add("강원",panelgangwon);
		tp.add("대전/충청",panelchung);
		tp.add("대구",paneldaegu);
		tp.add("부산/울산",panelbusan);
		tp.add("경상",panelcurrent);
		tp.add("광주/전라/제주",paneljeju);//각 영화관 지역별 패널 추가
		add(tp);
		
		for(int i = 0; i < lbl.length; i++) {
			close[i].addActionListener(e -> {
				dispose();
			});
		}
		setSize(600, 400);
		setLocationRelativeTo(null);//창가운데 띄우기
		setVisible(true);
	}
}