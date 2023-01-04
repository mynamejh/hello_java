package co.gui;

import java.awt.BorderLayout;

import javax.swing.*;

public class BatchFrame2 extends JFrame {
//Borderlayout - 구체적인 자리 설정이 가능합니다
	public BatchFrame2() {
		setTitle("BorderLayout 관리자"); 
		setSize(300,250);
		
		setLayout(new BorderLayout(10,10)); //좌우, 아래위 여백 설정
		
		JButton top = new JButton("Top");
		JButton left = new JButton("left");
		JButton center = new JButton("center");
		JButton right = new JButton("right");
		JButton bottom = new JButton("bottom");
		
		add("North", top);
		add("West", left);
		add("Center", center);
		add("East", right);
		add("South", bottom);
		
		setVisible(true); //모든 컴포넌트를 다 배치하고 이 명령문을 써야함
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null); //화면의 중앙에서 실행하고 싶다.
		
	}
	
	public static void main(String[] args) {
		new BatchFrame2();
	}
}
