/* 영화 포스터 클릭시 확대된 이미지 보여줌*/

package Project;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Movieimage extends JFrame{
	Image image = null;
	public Movieimage(String moviename, String imgs) {
		super(moviename + " 영화 포스터");
		try {
			URL url = new URL(imgs);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel imagelabel = new JLabel(new ImageIcon(image));
		add(imagelabel);
		setSize(image.getWidth(null), image.getHeight(null));
		setLocationRelativeTo(null);//창가운데 띄우기
		setVisible(true);
	}

}
