package co.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//메일발송화면
public class MailScreen extends JFrame {
	
	Dimension lbl1 = new Dimension(65, 15);
	Dimension lbl2 = new Dimension(45,15);
	
	JPanel center, bottom; //컴포넌트를 모아두기 위한 컨테이너
	JLabel fromLbl, toLbl, subjectLbl, contentLbl; //레이블.
	JTextField fromTxt, toTxt, subjectTxt; //텍스트필드
	JTextArea contentTxt;
	JButton send, cancel;
	
	MailApp app = new MailApp();
	public MailScreen() {
		setTitle("메일 보내기 UI");
		setSize(340, 300);
		setLayout(new BorderLayout());
		
		//panel..
		center = new JPanel();
		bottom = new JPanel();
		//Label..
		toLbl = new JLabel("받는 사람");
		fromLbl = new JLabel("보내는 사람");
		subjectLbl = new JLabel("제목 : ");
		contentLbl = new JLabel("내용 : ");
		toLbl.setPreferredSize(lbl1);
		fromLbl.setPreferredSize(lbl1);
		subjectLbl.setPreferredSize(lbl1);
		contentLbl.setSize(lbl2);
		
		//textField
		toTxt= new JTextField(22);
		fromTxt = new JTextField(22);
		subjectTxt = new JTextField(22);
		contentTxt = new JTextArea(10, 25);
		
		//button.
		send = new JButton("보내기");
		send.addActionListener(new MyActionListener()); //괄호값에 actionListner(인터페이스)가 구현된 객체필요.
		cancel = new JButton("취소");
		cancel.addActionListener(new MyActionListener());
		
		//container setting
		center.add(fromLbl);
		center.add(fromTxt);
		center.add(toLbl);
		center.add(toTxt);
		center.add(subjectLbl);
		center.add(subjectTxt);
		center.add(contentLbl);
		center.add(contentTxt);
		
		bottom.add(send);
		bottom.add(cancel);
		
		//윈도우에 컨테이너 배치 
		add("Center",center);
		add("South", bottom);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}
	
	 //ActionListner 구현 클래스
	//기능을 실행하는 구현객체 생성.
	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource(); //어떤 객체에 이벤트 발생했는지 알랴줌
			if(src == send) {
				System.out.println("보내기 버튼 ACtionPerformed");
				String from = fromTxt.getText();//텍스트 필드에 있는 값 불러들임
				String to = toTxt.getText();
				String subject = subjectTxt.getText();
				String content = contentTxt.getText();
				
				
				
				if(app.sendMail(from, to,subject, content).equals("Success")) {
					//콘솔에 "성공" => 입력값을 지워주기
					JOptionPane.showMessageDialog(null, "발송성공!", "전송결과",JOptionPane.DEFAULT_OPTION);
					fromTxt.setText(null);
					toTxt.setText(null);
					subjectTxt.setText(null);
					contentTxt.setText(null);
				} else{
					JOptionPane.showMessageDialog(null, "발송실패!", "에러",JOptionPane.WARNING_MESSAGE);
				}
				
			} else if(src == cancel) {
				System.out.println("취소 버튼 ACtionPerformed");	
			}
		}
	}
	
	//화면시작
	public static void main(String[] args) {
		new MailScreen();
	}
}
