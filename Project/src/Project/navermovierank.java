/* 네이버 영화 랭킹에서 순위를 긁어와 보여줌 */

package Project;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class navermovierank extends JFrame {
	private final static String address = "https://search.naver.com/search.naver?sm=top_hty&fbm=0&ie=utf8&query=%EC%98%81%ED%99%94%EC%88%9C%EC%9C%84";//파싱하고자 하는 사이트
	   String[] title = {"등수", "영화 제목", "나이제한", "개봉", "일간", "누적"};
	   String[][] strss = new String[20][6];
	   JTable table;
	   JScrollPane scroll;
	   JButton btn = new JButton("닫기");
	   
	   public navermovierank()  {
		   try {
		   Document doc = Jsoup.connect(address).get();
		   Elements contents = doc.select("strong").select(".scm_ellipsis_text");
		   
		   int idx = 0;
		   for(Element element : contents) {
			   if(idx < 20) {
				   strss[idx][1] = element.text().toString();
				   strss[idx][0] = Integer.toString(idx+1);
			   }
			   idx++;
		   }
		   Elements content = doc.select("div").select(".thumb").select("span").select(".blind");

		   idx = 0;
		   for(Element element : content) {
			   if(idx < 20) {
				   strss[idx][2] = element.text().toString();
			   }
			   idx++;
		   }	
		   
		   Elements cont = doc.select("dl").select(".movie_item").select("dd");
		   idx = 0;
		   int num = 3;
		   for(Element element : cont) {
			   if(idx < 20) {
				   strss[idx][num]= element.text().toString();
				   num++;
				   if(num == 6) {
					   num = 3;
					   idx++;
				   }
			   }
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

		   setSize(500, 500);
		   setLocationRelativeTo(null);//창가운데 띄우기
		   setVisible(true);
		   
		   } catch (Exception e) {
	           System.out.println(e.getMessage());
	       }
	   }
}
