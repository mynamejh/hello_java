package co.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

//문자발송화면
public class SmsScreen extends JFrame {
	Dimension lbl1 = new Dimension(65, 15);
	Dimension lbl2 = new Dimension(45,15);

	JPanel center, bottom;
	JLabel fromLbl, toLbl, contentLbl;
	JTextField fromTxt, toTxt;
	JTextArea contentTxt;
	JButton send, cancel;

	SmsApp app = new SmsApp();

	public SmsScreen() {
		setTitle("문자 보내기 UI");
		setSize(340,300);
		setLayout(new BorderLayout());
		
		//pannel
		center = new JPanel();
		bottom = new JPanel();
		//label
		toLbl = new JLabel("받는 사람");
		fromLbl = new JLabel("보내는 사람");
		contentLbl = new JLabel("내용 : ");
		toLbl.setPreferredSize(lbl1);
		fromLbl.setPreferredSize(lbl1);
		contentLbl.setSize(lbl2);
		
		//textField
		fromTxt = new JTextField(22);
		toTxt = new JTextField(22);
		contentTxt = new JTextArea(10, 25);
		
		//button
		send = new JButton("전송");
		send.addActionListener(new MyAction());
		cancel = new JButton("취소");
		cancel.addActionListener(new MyAction());
		
		//container setting
		center.add(fromLbl);
		center.add(fromTxt);
		center.add(toLbl);
		center.add(toTxt);
		center.add(contentLbl);
		center.add(contentTxt);
		
		bottom.add(send);
		bottom.add(cancel);
		
		//윈도우에 컨테이너 배치
		add("Center", center);
		add("South", bottom);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	// ActionListener 구현클래스
	class MyAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			if (src == send) {
				System.out.println("전송 버튼 클릭");
				String from = fromTxt.getText();
				String to = toTxt.getText();
				String content = contentTxt.getText();

				if (app.sendSms(from, to, content).equals("Success")) {
					JOptionPane.showMessageDialog(null, "발송성공!", "전송결과", JOptionPane.DEFAULT_OPTION);
					fromTxt.setText(null);
					toTxt.setText(null);
					contentTxt.setText(null);
				} else {
					JOptionPane.showMessageDialog(null, "발송실패!", "에러", JOptionPane.WARNING_MESSAGE);
				}
			} else if (src == cancel) {
				System.out.println("취소 버튼 클릭");
			}
		}
	}
	public static void main(String[] args) {
		new SmsScreen();
	}

}
