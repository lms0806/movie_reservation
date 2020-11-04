/* 영화제목 클릭시 영화와 관련된 정보들을 표시해줌 */

package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Movieinfomation extends JFrame{
	JLabel lbl;
	JLabel[] lbls,lblss,lbls1,lbls2,lbls3;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JButton btn = new JButton("닫기");
	
	public Movieinfomation(String moviename,String movieinfomationstring) {
		super(moviename + " 영화 정보");
		String address = "http://www.cgv.co.kr/" + movieinfomationstring;
		
		try {
			Document doc = Jsoup.connect(address).get();
			
			Elements percentelment = doc.select("strong").select(".percent");
			String percent = percentelment.text().toString();
			lbl = new JLabel(percent);
			//System.out.println(percent);//예매율
			
			Elements specelement = doc.select("div").select(".spec").select("dt");
			String[] spec = new String[5];
			lbls = new JLabel[5];
			int idx = 0;
			for(Element element : specelement ) {
				spec[idx] = element.text().toString();
				if(spec[idx].indexOf("/ ") != -1) {
					spec[idx] = spec[idx].substring(2);
				}
				idx++;
			}
			for(int i = 0; i < spec.length; i++) {
				lbls[i] = new JLabel(spec[i]);
			}
			Elements specelement1 = doc.select("div").select(".spec").select("dd").not(".on");
			String spec1String = specelement1.text().toString();
			lbls[0].setText(lbls[0].getText() + " " + spec1String);
			
			Elements specelement2 = doc.select("div").select(".spec").select("dd").select(".on");
			String[] spec1 = new String[4];
			idx = 0;
			for(Element element : specelement2 ) {
				if(idx >= 1) {
					spec1[idx+1] = element.text().toString();
				}
				else {
					spec1[idx] = element.text().toString();
				}
				idx++;
			}
			lbls1 = new JLabel[5];
			idx = 1;
			for(int i = 0; i < spec1.length; i++) {
				if(spec1[i] != null){
					lbls[idx].setText(lbls[idx].getText() + " " + spec1[i]);
				}
				idx++;
				System.out.println(spec1[i]);//구성원, 나이제한, 시간, 개봉일
			}
			
			Elements stroyelement = doc.select("div").select(".sect-story-movie");
			String story = stroyelement.text().toString();
			lbls1 = new JLabel[story.length()/31];
			int size = story.length();
			String story2 = new String();
			int num = 0;
			while(size > 31) {
				story2 = story.substring(0,30) + "\n";
				story = story.substring(30);
				if(num == 0) {
					lbls1[num] = new JLabel("줄거리 : " + story2);
				}
				else {
					lbls1[num] = new JLabel(story2);
				}
				size-=31;
				num++;
			}
			
			Elements evaluationelement = doc.select("span").select(".tooltip");
			String[] evaluation = new String[2];
			lbls2 = new JLabel[2];
			idx = 0;
			for(Element element : evaluationelement){
				if(idx < 2) {
					evaluation[idx] = element.text().toString();
					lbls2[idx] = new JLabel(evaluation[idx]);
				}
				idx++;
			}//사전기대자수, 실관람평지수
			
			Elements evaluationnumelement = doc.select("span").select(".percent");
			String[] evaluationnum = new String[2];
			lbls3 = new JLabel[2];
			idx = 0;
			for(Element element : evaluationnumelement){
				if(idx > 0) {
					evaluationnum[idx-1] = element.text().toString();
					lbls3[idx-1] = new JLabel(evaluationnum[idx-1]);
					//System.out.println(evaluationnum[idx-1]);
				}
				idx++;
			}//사전기대자수%, 실관람평지수%
			
			Element img = doc.select("span").select(".thumb-image").first();
			//.getElementsByTag("img")
			String imgs = img.getElementsByAttribute("src").attr("src");
			System.out.println(imgs);
			Image image = null;
			try {
				URL url = new URL(imgs);
				image = ImageIO.read(url);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Image resizeImage = image.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
			JButton imagebutton = new JButton(new ImageIcon(resizeImage));
			imagebutton.setBorderPainted(false);
			imagebutton.setContentAreaFilled(false);
			int number = 3;
			panel.add(lbl);
			Font font = new Font("나눔고딕",Font.BOLD, 13);
			lbl.setFont(font);
			for(int i = 0; i < spec.length; i++) {
				lbls[i].setFont(font);
				panel.add(lbls[i]);
				number++;
			}
			panel.add(new JLabel("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"));
			for(int i = 0; i < num; i++) {
				lbls1[i].setFont(font);
				panel.add(lbls1[i]);
				number++;
			}
			panel.add(new JLabel("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ"));
			for(int i = 0; i < 2; i++) {
				lbls2[i].setFont(font);
				lbls3[i].setFont(font);
				panel.add(lbls2[i]);
				panel.add(lbls3[i]);
				number+=2;
			}
			add(panel);
			
			btn.addActionListener(e -> {
				dispose();
			});
			panel.setLayout(new GridLayout(number,1,1,7));
			
			panel1.add(imagebutton);
			imagebutton.addActionListener(e -> {
				new Movieimage(moviename, imgs);
			});
			add(panel1, BorderLayout.EAST);
			
			panel2.add(btn);
			panel2.setLayout(new GridLayout(1,0));
			add(panel2, BorderLayout.SOUTH);
			
			panel.setBackground(new Color(240,240,240));
			panel1.setBackground(new Color(240,240,240));
			panel2.setBackground(new Color(240,240,240));
			setSize(700, 600);
			setLocationRelativeTo(null);//창가운데 띄우기
			setVisible(true);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
