package co.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.*;

public class ComponentExam extends JFrame {
	public ComponentExam() {
			setTitle("라벨 예제");
			setSize(new Dimension(300, 250));
			//라벨 - 제목등의 정보등을 사용
			//컨테이너 - 컴포넌트 묶어서 하나의 덩어리로 만듬. ex.JPanel
			//아무거도 지정안하면 기본 = Flowlayout 
			
			JPanel panel = new JPanel(); 
			JLabel label1 = new JLabel("Color Label");
			label1.setFont(new Font("Arial", Font.ITALIC, 20));
			label1.setForeground(Color.YELLOW);
			label1.setText("♣ Yellow Font");
			
			JLabel label2 = new JLabel("Font Lable");
			label2.setFont(new Font("Arial", Font.BOLD, 25)); //글자체,스타일,크기
			label2.setForeground(Color.GREEN);
			
			JLabel label3 = new JLabel();
			ImageIcon icon = new ImageIcon("c:/Temp/먼작귀.jpg");
			//이미지 크기 지정??
			label3.setIcon(icon);
			label3.setPreferredSize(new Dimension(200,180));
			
			
			panel.add(label1);
			panel.add(label2);
			panel.add(label3);
			
			add(panel);
			
			setVisible(true);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setLocationRelativeTo(null);
	}
	
	public static void main(String[] args) {
		new ComponentExam();
	}
}
