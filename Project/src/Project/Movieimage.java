/* ��ȭ ������ Ŭ���� Ȯ��� �̹��� ������*/

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
		super(moviename + " ��ȭ ������");
		try {
			URL url = new URL(imgs);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel imagelabel = new JLabel(new ImageIcon(image));
		add(imagelabel);
		setSize(image.getWidth(null), image.getHeight(null));
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}

}
