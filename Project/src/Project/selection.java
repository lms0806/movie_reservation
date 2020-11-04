/* ���� ����â�� ����  �ҽ��� */
package Project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class selection extends JFrame{
	Image image = null;
	JButton[] btn = new JButton[3];
	JLabel title = new JLabel("��ȭ ���� ���α׷�");
	JPanel panel = new JPanel();
	JButton close = new JButton("�ݱ�");
	int y = 150;
	CGVimage cgvimage = new CGVimage();
	public selection() {
		setTitle("�μ��� ��ȭ���� ���α׷�");
		
		try {
			URL url = new URL(cgvimage.imgs);
			image = ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel imagelabel = new JLabel(new ImageIcon(image));
		imagelabel.setBounds(60,-10,400,100);
		btn[0] = new JButton("����");
		btn[1] = new JButton("�󿵽ð�ǥ");
		btn[2] = new JButton("����Ȯ��");
		
		for(int i = 0; i < btn.length; i++) {
			btn[i].setBounds(170, y, 150, 50);
			btn[i].setFont(new Font("�������",Font.BOLD, 15));
			y+=100;
		}
		close.setBounds(170, 450, 150, 50);
		title.setBounds(70,50,400,100);
		title.setFont(new Font("�������", Font.BOLD, 40));
		title.setForeground(Color.white);
		panel.add(imagelabel);
		panel.add(title);
		panel.add(btn[0]);
		panel.add(btn[1]);
		panel.add(btn[2]);
		panel.add(close);
		panel.setLayout(null);

		panel.setBackground(Color.BLACK);
		btn[0].addActionListener(e -> {
			new rankselect();
		});
		btn[1].addActionListener(e -> {
			new MovieSchedule();
		});
		btn[2].addActionListener(e -> {
			new LoginFirst();
		});
		close.addActionListener(e -> {
			dispose();
		});
		
		add(panel);
		setSize(500, 600);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}

class rankselect extends JFrame{
	JButton[] btn = new JButton[2];
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JButton close = new JButton("�ݱ�");
	public rankselect(){
		setTitle("����");
		
		btn[0] = new JButton("�ڽ����ǽ� ����");
		btn[1] = new JButton("���̹���ȭ ����");
		panel.add(btn[0]);
		panel.add(btn[1]);
		panel1.add(close);
		
		btn[0].addActionListener(e -> {
			new boxofficerankselect();
		});
		btn[1].addActionListener(e -> {
			new navermovierank();
		});
		close.addActionListener(e -> {
			dispose();
		});
		
		add(panel, BorderLayout.NORTH);
		add(panel1, BorderLayout.SOUTH);
		setSize(300, 200);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}

class boxofficerankselect extends JFrame{
	JButton[] btn = new JButton[2];
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JButton close = new JButton("�ݱ�");
	public boxofficerankselect(){
		setTitle("�ְ�/�ϰ� ����");
		
		btn[0] = new JButton("�ְ�");
		btn[1] = new JButton("�ϰ�");
		panel.add(btn[0]);
		panel.add(btn[1]);
		panel1.add(close);
		
		btn[0].addActionListener(e -> {
			new boxofficeselect(0);
		});
		btn[1].addActionListener(e -> {
			new boxofficeselect(1);
		});
		close.addActionListener(e -> {
			dispose();
		});
		
		add(panel, BorderLayout.NORTH);
		add(panel1, BorderLayout.SOUTH);
		setSize(300, 150);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
}

class selectmovietime extends JFrame{
	JButton[] btn = new JButton[3];
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JButton close = new JButton("�ݱ�");
	public selectmovietime(String[] s, String[] s1, String[] s2, int j) {
		setTitle("��¥ ����");
		int time = time();
		btn[0] = new JButton(Integer.toString(time).substring(6)+"��");
		btn[1] = new JButton(Integer.toString(time+1).substring(6)+"��");
		btn[2] = new JButton(Integer.toString(time+2).substring(6)+"��");
		
		for(int i = 0; i < btn.length; i++) {
			panel.add(btn[i]);
		}
		add(panel);
		
		panel1.add(close);
		add(panel1, BorderLayout.SOUTH);
		
		btn[0].addActionListener(e -> {
			new Movietime(s,s1,s2,j,time);
		});
		btn[1].addActionListener(e -> {
			new Movietime(s,s1,s2,j,time+1);
		});
		btn[2].addActionListener(e -> {
			new Movietime(s,s1,s2,j,time+2);
		});
		close.addActionListener(e -> {
			dispose();
		});
		setSize(300,150);
		setLocationRelativeTo(null);//â��� ����
		setVisible(true);
	}
	
	public static int time() {
		SimpleDateFormat format1 = new SimpleDateFormat ("yyyyMMdd");
		
		Date time = new Date();
				
		String time1 = format1.format(time);
		int num = Integer.parseInt(time1);
		
		return num;
	}
}