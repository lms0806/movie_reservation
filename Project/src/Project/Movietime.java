/* ������ ��ȭ���� ��ȭ�鿡 ���� ������ ������ */

package Project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Movietime {
	public static String address = "http://www.cgv.co.kr/common/showtimes/iframeTheater.aspx";
	String str = new String();
	String strs = new String();
	String[][] strss;
	
	public Movietime(String[] s, String[] s1, String[] s2, int num, int times) {
		address+="?areacode="+s[num];
		address+="&theaterCOde="+s1[num];
		address+="&date="+times;
		address+="&screencodes=&screenratingcode=&regioncode=";//�Ľ��� �ּ�
		str = s2[num];//��ȭ�� �̸�
		System.out.println(str);
		System.out.println(times);
		try {
			Document doc = Jsoup.connect(address).get();
			int idx = 0;
			
			Elements checkage = doc.select("div").select(".info-movie").select(".ico-grade");
			int number = checkage.size();
			//System.out.println(number);
			String[][] movieinfo = new String[number][3];
			
			for(Element element : checkage) {
				movieinfo[idx][0] = element.text().toString();
				//System.out.println(agecheck[idx]);
				idx++;
			}//�̿밡 ����
			
			Elements moviename = doc.select("li").select("div").select(".info-movie").select("a");
			idx = 0;
			for(Element element : moviename) {
				movieinfo[idx][1] = element.text().toString();
				//System.out.println(movieinfo[idx][1]);
				idx++;
			}//��ȭ����
			
			Elements show = doc.select("div").select(".info-movie").select(".round ");
			idx = 0;
			for(Element element : show) {
				if(!element.text().toString().substring(0,1).equals("D")) {
					movieinfo[idx][2] = element.text().toString();
					//System.out.println(movieinfo[idx][2]);
					idx++;
				}
			}//�������� �ƴ���
			
			Elements movieinfomationsite = doc.select("li").select("div").select(".info-movie").select("a");
			idx = 0;
			String[] movieinfomationstring = new String[number];
			//System.out.println("hi");
			for(Element element : movieinfomationsite) {
				movieinfomationstring[idx] = element.getElementsByAttribute("href").attr("href");
				//System.out.println(movieinfomationstring[idx]);
				idx++;
			}//��ȭ�� ���� ������ �ִ� ����Ʈ
			
			
			Elements movieinfomation = doc.select("div").select(".info-movie").select("i");//
			int number1 = movieinfomation.size()/3;
			String[][] moviekind = new String[number1][3];
			idx = 0;
			int count1 = 0;
			for(Element element : movieinfomation) {
				moviekind[idx][count1] = element.text().toString();
				//System.out.println(moviekind[idx][count1]);
				count1++;
				if(count1 == 3) {
					count1 = 0;
					idx++;
				}
			}//�󿵰� �¼�
			
			/*Elements moviename = doc.select("li").select("div").select(".info-movie").select("a");
			idx = 0;
			for(Element element : moviename) {
				movieinfo[idx][1] = element.text().toString();
				System.out.println(movieinfo[idx][1]);
				idx++;
			}//��ȭ����*/
			
			Elements counttime = doc.select("div").select(".info-timetable").select("li");
			int number2 = counttime.size();
			//System.out.println(number2);
			String[] counttimeseat = new String[number2+1];
			idx = 0;
			for(Element element : counttime) {
				counttimeseat[idx+1] = element.text().toString();
				//System.out.println(counttimeseat[idx+1]);
				idx++;
			}//��ȭ �ð�,���� �¼�
			
			Elements moviewhere = doc.select("div").select(".info-hall").select("ul").select("li");//
			int number3 = moviewhere.size()/3;
			System.out.println(number3);
			String[][] wherehow = new String[number3][3];
			idx = 0;
			count1 = 0;
			for(Element element : moviewhere) {
				wherehow[idx][count1] = element.text().toString();
				//System.out.println(wherehow[idx][count1]);
				count1++;
				if(count1 == 3) {
					count1 = 0;
					idx++;
				}
			}//2d,3��,�� �
			
			new MakeMovietime(str, movieinfo, movieinfomationstring, wherehow, address);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
