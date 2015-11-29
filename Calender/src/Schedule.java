import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;


//메인 클래스
public class Schedule extends JFrame {
	Date dt;
	static JTable tb;
	public static CModel tm1;
	public static JLabel nowMonth;
	public static int todaysValue = 0;
	JButton beforeM,nextM;
	JLabel nowDate;

	public Schedule() {
		JButton beforeM = new JButton("이전");
		JButton nextM = new JButton("다음");
		nowDate = new JLabel("현재시각",JLabel.CENTER);
		
		//시간을 1초마다 최신화 하기 위한 스레드 클래스
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true){
				dt = new Date();
				try {
					nowDate.setText(String.valueOf(dt));
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}                        
				}
			}
		}).start();
		
		nowMonth = new JLabel("월",JLabel.CENTER);
		
		Container pane = getContentPane();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(2, 1));
		
		pane.setLayout(new BorderLayout());
		
		
		//시간은 스레드를 통해 1초당 한번씩 갱신해줘야 합니다.
		
		panel.add(nowMonth);
		panel.add(nowDate);
		pane.add(panel,BorderLayout.NORTH);
		pane.add(nextM,BorderLayout.EAST);
		pane.add(beforeM,BorderLayout.WEST);
		
		nextMonthListener nm = new nextMonthListener();
		beforeMonthListener bm = new beforeMonthListener();
		nextM.addActionListener(nm);
		beforeM.addActionListener(bm);
		
		tm1 = new CModel();
		/*
		 * 테이블의 모양(모델)을 형성하는 클래스는
		 * TableModel, AbstractTableModel. DefaultTableModel이 있음
		 * AbstactTableModel
		 * - TableModel를 구현한 클래스 
		 * - 직접 모델을 정의해서 사용하려면 이 클래스를 상속받아 사용
		 * 
		 * JTable(TableModel dm)
		 * - 데이터 모델 dm으로 초기화 되는 JTable 생성
		 */
		tb = new JTable(tm1);
		
		JScrollPane jsp = new JScrollPane(tb);
		pane.add(jsp, BorderLayout.CENTER);
		tb.addMouseListener(new Click());
		
		tm1.fireTableDataChanged();
	}

	public static void main(String[] args) {
		Schedule tjt = new Schedule();

		tjt.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tjt.setTitle("Schedule Calender");
		tjt.setSize(500, 180);
		tjt.setVisible(true);

		Rendring tr = new Rendring();
		
		try {
			// ??
			tb.setDefaultRenderer(Class.forName("java.lang.Object"), tr);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
//다음달로 이동하기 위한 버튼 리스너
class nextMonthListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("다음달로 이동");
		CModel.monthplus = CModel.monthplus + 1;
		Schedule.tm1.fireTableDataChanged();
		
	}
}

//이전달로 이동하기 위한 버튼 리스너
class beforeMonthListener implements ActionListener {
	CModel tm1;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		System.out.println("이전달로 이동");
		CModel.monthplus = CModel.monthplus - 1;
		
		Schedule.tm1.fireTableDataChanged();
	}
}
