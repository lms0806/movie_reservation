package Project;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class test {   
   
   public static void main(String[] args){
      try {
          String str = new String();
          Document doc = Jsoup.connect("https://www.superstari.co.kr/shop/shopbrand.html?xcode=003&type=Y").get();
          Elements contents = doc.select("em.check_price_sell ");
          Elements contents2 = doc.select("span.re_name ");
          for(Element e : contents) {
        	  str = contents.text().toString();
        	  System.out.println(str);
          }
          //str = contents2.text().toString();
          //System.out.println(str);

      } catch (Exception e) {
         System.out.println("Error!");
      }
   }
}