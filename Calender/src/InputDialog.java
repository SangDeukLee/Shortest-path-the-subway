import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;



//텍스트를 입력받아 저장하기 위한 다이얼로그를 생성하는 클래스
class InputDialog extends JDialog{
	JLabel nowDate = new JLabel("2015년 01월 01일");
	JTextArea inputText = new JTextArea();
	JButton saveText = new JButton("저장");
	JButton cancle = new JButton("취소");
	String filename;
	//저장, 취소 버튼이 있어야 한다.
	//텍스트 입력란이 있어야 한다.
	public InputDialog(int year, int mounth, int date){
		JPanel panel2 = new JPanel();
		panel2.setLayout(new GridLayout(1, 2));
		panel2.add(cancle);
		panel2.add(saveText);
		
		File dir = new File(String.valueOf(mounth) + " 월"); 
		nowDate.setText(String.valueOf(year) + " 년"
		+ String.valueOf(mounth) + " 월"
		+ String.valueOf(date) + "일");
		
		filename = dir+ "\\" + String.valueOf(year) + " 년 "
				+ String.valueOf(mounth) + " 월 "
				+ String.valueOf(date) + "일.txt";
		
		File fileposition = new File(filename);
		
		//월 폴더가 없을 경우 생성 할것.
		if(!dir.isDirectory()){
			dir.mkdirs();
		}
		else if(fileposition.isFile()) {
			try {
				FileReader fileReader = new FileReader(fileposition);
	            BufferedReader reader = new BufferedReader(fileReader);
	            
	            String line = null;
	            while ((line = reader.readLine()) != null) {
	                this.inputText.append(line + "\n");
	               
	            }
	            reader.close(); 
				
			}
			catch(IOException e1) {
				e1.printStackTrace();
			}
		}
		
		saveText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// 입력된 텍스트를 월별 폴더에 일별로 저장해야 한다.
				try {
					
					//해당 경로에 파일 생성
					BufferedWriter fw = new BufferedWriter(new FileWriter(filename));
					
					
					fw.write(inputText.getText());
					//System.out.println(inputText.getText());
					fw.flush();
					fw.close();
					setVisible(false);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		cancle.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				setVisible(false);
			}
		});
		
		getContentPane().add(nowDate,BorderLayout.NORTH);
		getContentPane().add(inputText,BorderLayout.CENTER);
		getContentPane().add(panel2,BorderLayout.SOUTH);
		
		this.setSize(300,300);
		this.setModal(true);
		this.setVisible(true);
		
	}
}
