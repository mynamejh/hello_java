package co.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

//간단한 계산기 만들기.
public class ComponentExam3 extends JFrame {
	
	String[] keys = {"1", "2", "3", "+", "4", "5", "6", "-"
					,"7", "8", "9", "*", "0", "=", "C", "/"};
	JButton[] keyBtn = new JButton[16];
	String displayText = "";
	String left,right; //필드선언
	
	public ComponentExam3() {
		setTitle("계산기 UI");
		setBounds(100, 100, 300, 220); // x위치, y좌표, 가로길이, 세로길이
		
		setLayout(new FlowLayout());
		
		//display 정보를 출력위한 panel
		JPanel displayPanel = new JPanel();
		JTextField display = new JTextField("0", 20); //초기값과 크기
		display.setHorizontalAlignment(JTextField.RIGHT); //수평정렬 = right로
		displayPanel.add(display);
		
		// 버튼 생성하기 위한 panel
		JPanel keyPanel = new JPanel();
		keyPanel.setLayout(new GridLayout(4,4, 5, 5)); //행,열,공백,공백
		for(int i = 0; i<keyBtn.length; i++) {
			keyBtn[i] = new JButton(keys[i]);
			//버튼 이벤트
			keyBtn[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) { //동작 구현
					System.out.println(((JButton) e.getSource()).getText()); //casting
					String KeyOper = ((JButton) e.getSource()).getText();
					//계산처리
//					display.setText(KeyOper);
					if(KeyOper.equals("=")) {
						left = displayText.substring(0, displayText.indexOf("+")); //345+12
						right = displayText.substring(displayText.indexOf("+") + 1);
						displayText = String.valueOf(Integer.parseInt(left) + Integer.parseInt(right));
						display.setText(displayText);
						return;
					} else if(KeyOper.equals("C")){
						displayText = "";
						display.setText(displayText);
						return;
					}
					displayText +=KeyOper;
					display.setText(displayText);	
				}
			});
			keyPanel.add(keyBtn[i]);
			
		}
		
		add(displayPanel);
		add(keyPanel);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new ComponentExam3();
	}
}
