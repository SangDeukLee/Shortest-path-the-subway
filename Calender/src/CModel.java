import java.util.Calendar;

import javax.swing.table.AbstractTableModel;

public class CModel extends AbstractTableModel {

	public static Calendar calendar;
	public int year;
	private int month;
	private int days;
	public static int monthplus = 0;
	public static int thismonthresult = 0;
	public static int getThisyear = 0;
	public static int getThismonth = 0;

	//테이블에서 선택한 오늘의 날짜를 가지고 옴
	public int getDate(int row, int col) {
		calendar.set(Calendar.YEAR, this.year); //올해의 년도를 저장
		calendar.set(Calendar.MONTH, this.month + monthplus); // 이번달의 값을 저장
		
		getThisyear = calendar.get(Calendar.YEAR);
		getThismonth = calendar.get(Calendar.MONTH) + 1;
		thismonthresult = calendar.get(Calendar.MONTH) + 1;

		Schedule.nowMonth
				.setText(String.valueOf(calendar.get(Calendar.YEAR)) + "  "
						+ String.valueOf(thismonthresult) + "월");
		
		
		calendar.set(Calendar.WEDNESDAY, row + 1);   // 행의 값을 정리
		calendar.set(Calendar.DAY_OF_WEEK, col + 1); // 열의 값을 정리

		return calendar.get(Calendar.DATE);
	}

	public CModel() {
		calendar = Calendar.getInstance(); // 현재의 일시 가지고 있는 칼렌더 취득
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.days = calendar.get(Calendar.DATE);
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return getDate(row, col);
	}
	
	//테이블 상단바의 일주일 표시
	public String getColumnName(int columnIndex) {
		String[] dayOfTheWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri",
				"Sat" };
		return dayOfTheWeek[columnIndex];
	}

	public int getYear() {
		return this.year;
	}
	


}




