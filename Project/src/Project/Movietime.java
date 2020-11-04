/* 선택한 영화관의 영화들에 관한 정보를 가져옴 */

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
		address+="&screencodes=&screenratingcode=&regioncode=";//파싱할 주소
		str = s2[num];//영화관 이름
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
			}//이용가 제한
			
			Elements moviename = doc.select("li").select("div").select(".info-movie").select("a");
			idx = 0;
			for(Element element : moviename) {
				movieinfo[idx][1] = element.text().toString();
				//System.out.println(movieinfo[idx][1]);
				idx++;
			}//영화제목
			
			Elements show = doc.select("div").select(".info-movie").select(".round ");
			idx = 0;
			for(Element element : show) {
				if(!element.text().toString().substring(0,1).equals("D")) {
					movieinfo[idx][2] = element.text().toString();
					//System.out.println(movieinfo[idx][2]);
					idx++;
				}
			}//상영중인지 아닌지
			
			Elements movieinfomationsite = doc.select("li").select("div").select(".info-movie").select("a");
			idx = 0;
			String[] movieinfomationstring = new String[number];
			//System.out.println("hi");
			for(Element element : movieinfomationsite) {
				movieinfomationstring[idx] = element.getElementsByAttribute("href").attr("href");
				//System.out.println(movieinfomationstring[idx]);
				idx++;
			}//영화에 대한 정보가 있는 사이트
			
			
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
			}//상영관 좌석
			
			/*Elements moviename = doc.select("li").select("div").select(".info-movie").select("a");
			idx = 0;
			for(Element element : moviename) {
				movieinfo[idx][1] = element.text().toString();
				System.out.println(movieinfo[idx][1]);
				idx++;
			}//영화제목*/
			
			Elements counttime = doc.select("div").select(".info-timetable").select("li");
			int number2 = counttime.size();
			//System.out.println(number2);
			String[] counttimeseat = new String[number2+1];
			idx = 0;
			for(Element element : counttime) {
				counttimeseat[idx+1] = element.text().toString();
				//System.out.println(counttimeseat[idx+1]);
				idx++;
			}//영화 시간,남은 좌석
			
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
			}//2d,3관,총 몇석
			
			new MakeMovietime(str, movieinfo, movieinfomationstring, wherehow, address);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
