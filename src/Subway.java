import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

class Fukuoka_Subway extends JFrame implements ActionListener {
	
	public int now_Time;
	Dimension dimen, dimen1; // 화면에 값을 얻어오는 변수
	int xpos, ypos;
	
	
	String[] Airport_line = new String[6];			// 텐진 -> 후쿠오카 공항
	int[] Airport_Subway = new int[5];				// 역 사이 걸리는 거리
	
	String[] Meinohama_line = new String[8];		// 텐진 -> 메이노하마
	int[] Meinohama_Subway = new int[7];			// 역 사이 걸리는 거리
	
	//////////////////////////////////////////////////////////////
	
	String[] Hakozaki_line = new String[8];			// 텐진 -> 가이즈카
	int[] Hakozaki_Subway = new int[7];				// 역 사이 걸리는 거리

	String[] Nanakuma_line = new String[17];		// 텐진 -> 하시모토
	int[] Nanakuma_Subway = new int[16];			// 역 사이 걸리는 거리
	
	JLabel title = new JLabel("Metro Station", JLabel.CENTER);
	JLabel start = new JLabel("출발역", JLabel.CENTER);
	JLabel end = new JLabel("도착역", JLabel.CENTER);
	
	JTextField startTextField = new JTextField();
	JTextField endTextField = new JTextField();
	JTextArea resultText = new JTextArea();
	
	JButton search = new JButton("検索");
	
	ImageIcon img = new ImageIcon("D:/Java/Project/src/Fukuoka.jpg");
	JLabel ImgBox = new JLabel(img);
	
	/*프레임*/
	public Fukuoka_Subway() {
		super("Fukuoka Subway");
		init(); // 라벨 삽입
		close(); // 프레임 종료

		setSize(1419, 800);
		setResizable(false);

		// 가운데 위치
		dimen = Toolkit.getDefaultToolkit().getScreenSize();
		dimen1 = getSize();

		xpos = (int) (dimen.getWidth() / 2 - dimen1.getWidth() / 2);
		ypos = (int) (dimen.getHeight() / 2 - dimen1.getHeight() / 2);

		setLocation(xpos, ypos);
		setVisible(true);
	}
	
	/*붙이는 부분*/
	public void init() {

		ImgBox.setBounds(0, 0, img.getIconWidth(), img.getIconHeight()); // 이미지

		start.setBounds(20, 700, 85, 50); // 출발역 라벨
		start.setVerticalTextPosition(JLabel.CENTER);

		startTextField.setBounds(130, 710, 120, 30); // 출발역 입력
		
		////////////////
		
		end.setBounds(350, 700, 85, 50); // 도착역 라벨
		end.setVerticalTextPosition(JLabel.CENTER);

		endTextField.setBounds(460, 710, 120, 30); // 도착역 입력
		
		//////////////////
		
		search.setBounds(700, 710, 100, 30); // 검색 버튼

		resultText.setBounds(850, 30, 540, 600); // 결과 텍스트 출력

		// 화면 구성 넣을 부분
		
		setLayout(null);
		add(ImgBox);
		// 이미지

		add(start);
		add(end);
		add(resultText);
		add(startTextField);
		startTextField.setHorizontalAlignment(JTextField.CENTER);
		add(endTextField);
		endTextField.setHorizontalAlignment(JTextField.CENTER);

		add(search);
		search.addActionListener(this);

	}

	// 종료 메소드
	public void close() {

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == search) {

			/* 시간 함수 */
			SimpleDateFormat simpleDateFormat = null;
			String format = "a h:mm ";
			simpleDateFormat = new SimpleDateFormat(format, Locale.KOREA);
			String date = simpleDateFormat.format(new java.util.Date());
	

			/* 텍스트 필드 값 읽어오기 */
			String x = new String(startTextField.getText());
			String y = new String(endTextField.getText());
		
			Airport_line[0] = "天神";
			Airport_line[1] = "中洲川端";
			Airport_line[2] = "祇園";
			Airport_line[3] = "博多";
			Airport_line[4] = "東比恵";
			Airport_line[5] = "福岡空港";
			
			Meinohama_line[0] = "天神";
			Meinohama_line[1] = "赤坂";
			Meinohama_line[2] = "大濠公園";
			Meinohama_line[3] = "唐人町";
			Meinohama_line[4] = "西新";
			Meinohama_line[5] = "藤崎";
			Meinohama_line[6] = "室見";
			Meinohama_line[7] = "姪浜";
			
			Hakozaki_line[0] = "天神";
			Hakozaki_line[1] = "中洲川端";
			Hakozaki_line[2] = "江北町";
			Hakozaki_line[3] = "千代県庁口";
			Hakozaki_line[4] = "馬出九大病院前";
			Hakozaki_line[5] = "箱崎宮前";
			Hakozaki_line[6] = "箱崎九大前";
			Hakozaki_line[7] = "貝塚";

			Nanakuma_line[0] = "天神";
			Nanakuma_line[1] = "天神南";
			Nanakuma_line[2] = "渡辺通";
			Nanakuma_line[3] = "薬院";
			Nanakuma_line[4] = "薬院大通";
			Nanakuma_line[5] = "桜坂";
			Nanakuma_line[6] = "六本松";
			Nanakuma_line[7] = "別府";
			Nanakuma_line[8] = "茶山";
			Nanakuma_line[9] = "金山";
			Nanakuma_line[10] = "七隈";
			Nanakuma_line[11] = "福大前";
			Nanakuma_line[12] = "梅林";
			Nanakuma_line[13] = "野芥";
			Nanakuma_line[14] = "賀茂";
			Nanakuma_line[15] = "次郎丸";
			Nanakuma_line[16] = "橋本";

			
			Airport_Subway[0] = 2;		 
			Airport_Subway[1] = 2;		
			Airport_Subway[2] = 2;		
			Airport_Subway[3] = 2;		
			Airport_Subway[4] = 2;	
			
			Meinohama_Subway[0] = 2;		 
			Meinohama_Subway[1] = 2;		
			Meinohama_Subway[2] = 2;		
			Meinohama_Subway[3] = 2;		
			Meinohama_Subway[4]	= 2;		
			Meinohama_Subway[5]	= 2;		
			Meinohama_Subway[6]	= 1;		
			Meinohama_Subway[7]	= 3;		

			Hakozaki_Subway[0]	= 2;	// 나카스카와바타		
			Hakozaki_Subway[1]	= 1;	// 고후쿠마치	
			Hakozaki_Subway[2]	= 2;		
			Hakozaki_Subway[3]	= 1;		
			Hakozaki_Subway[4]	= 2;		
			Hakozaki_Subway[5]	= 2;		
			Hakozaki_Subway[6]	= 2;		

			Nanakuma_Subway[0]	= 0;	//텐진미나미		
			Nanakuma_Subway[1]	= 1;		
			Nanakuma_Subway[2]	= 2;		
			Nanakuma_Subway[3]	= 1;		
			Nanakuma_Subway[4]	= 2;		
			Nanakuma_Subway[5]	= 2;		
			Nanakuma_Subway[6]	= 1;		
			Nanakuma_Subway[7]	= 2;		
			Nanakuma_Subway[8]	= 2;		
			Nanakuma_Subway[9]	= 1;		
			Nanakuma_Subway[10]	= 2;		
			Nanakuma_Subway[11]	= 1;		
			Nanakuma_Subway[12]	= 2;		
			Nanakuma_Subway[13]	= 2;		
			Nanakuma_Subway[14]	= 1;		
			Nanakuma_Subway[15]	= 2;
			
			int 	sum_subway_first  	= 0;
			int 	sum_subway_second 	= 0;
			String	sum_course_first	= "";
			String	sum_course_second 	= "";
			
			if (x.equals("天神")) {// 검색
				sum_subway_first = 0;
				sum_course_first = "";
				
				for (int i1 = 0; i1 < Airport_line.length; i1++) {
					if (y.equals(Airport_line[i1])) {
						for (int k = 0; k < i1; k++) {
							sum_subway_second += Airport_Subway[k];
							sum_course_second += Airport_line[k];
						}
					}
				}
				for (int i1 = 0; i1 < Meinohama_line.length; i1++) {
					if (y.equals(Meinohama_line[i1])) {
						for (int k = 0; k < i1; k++) {
							sum_subway_second += Meinohama_Subway[k];
							sum_course_second += Meinohama_line[k];
						}
					}
				}
				for (int i1 = 0; i1 < Hakozaki_line.length; i1++) {
					if (y.equals(Hakozaki_line[i1])) {
						for (int k = 0; k < i1; k++) {
							sum_subway_second += Hakozaki_Subway[k];
							sum_course_second += Hakozaki_line[k];
						}
					}
				}
				for (int i1 = 0; i1 < Nanakuma_line.length; i1++) {
					if (y.equals(Nanakuma_line[i1])) {
						for (int k = 0; k < i1; k++) {
							sum_subway_second += Nanakuma_Subway[k];
							sum_course_second += Nanakuma_line[k];
						}
					}
				}
			}
			else {
				sum_subway_second = 0;
			}
			
			// 예외발생
			if (x.equals("")) {
				JOptionPane.showMessageDialog(this, "출발역을 입력하세요");
			} 
			
			else if (y.equals("")) {
				JOptionPane.showMessageDialog(this, "도착역을 입력하세요");
			} 
			
			else {
				resultText.setText("지하철 이용 시간 : "
										+ Math.abs(sum_subway_first + sum_subway_second) / 60
										+ "시간 " + Math.abs(sum_subway_first + sum_subway_second)
										% 60 + "분\n" + "현재시각\n" + date.toString()
									
									);
			}
			
			
		}
	}
}
public class Subway {
	public static void main(String [] args) {		
		Fukuoka_Subway frame = new Fukuoka_Subway();		
	}
}
