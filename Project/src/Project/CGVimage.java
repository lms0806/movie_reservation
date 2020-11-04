/* cgv �ΰ� ������ ����ȭ�鿡 ǥ������ */

package Project;

import java.io.IOException;	

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CGVimage {
	String address = "http://www.cgv.co.kr/";
	String imgs;
	public CGVimage() {
		try {
			Document doc = Jsoup.connect(address).get();
			Element img = doc.select("div").select(".head").select("[href]").first();
			imgs = img.getElementsByAttribute("src").attr("src");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
