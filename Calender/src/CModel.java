import java.util.Calendar;

import javax.swing.table.AbstractTableModel;

public class CModel extends AbstractTableModel {
	
	/* Calendar는 추상클래스이기 때문에 직접 객체를 생성할 수 없다.
	 * 메서드를 통해서 완전히 구현된 클래스의 인스턴스를 얻어야 한다.
	 */
	public static Calendar calendar;
	public int year;
	private int month;
	private int days;
	public static int monthplus = 0;
	public static int thismonthresult = 0;
	public static int getThisyear = 0;
	public static int getThismonth = 0;
	
	
	
	public CModel() {
		/*
		 * getInstance()는 static메서드
		 * Calendar은 추상 클래스이기 때문에 객체를 생성할 수 없기 때문
		 * getInstance()를 통해서 얻은 인스턴스는 기본적으로 현재 시스템의 날짜와 시간에 대한 정보를 담고있다.
		 * 원하는 날짜나 시간으로 설정하려면 set 메서드 사용하면 된다.
		 */
		calendar = Calendar.getInstance(); // 현재의 일시 가지고 있는 칼렌더 취득
		// get메서드의 매개변수로 사용되는 int값들은 Calendar에 정의된 static상수
		this.year = calendar.get(Calendar.YEAR);
		this.month = calendar.get(Calendar.MONTH);
		this.days = calendar.get(Calendar.DATE);
	}
	/*
	 * TableModel에 있는 메소드
	 */
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		// 컬럼의 개수를 리턴
		return 7;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		// 행의 개수를 리턴
		return 5;
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		// 선택된 위치의 값을 얻어온다.
		return getDate(row, col);
	}
	
	/*
	 *  이들 메서드들은 각 리턴 값을 JTable에게 넘겨줘서 JTable이 화면에 표시하게 해 줌.
	 *  JTable는 TableModel 인터페이스를 직접 사용하지 않고 이를 미리 구현해놓은 AbstractTableModel 클래스를 사용
	 *  AbstractTableModel 클래스를 상속하고 필요한 메서드만 오버라이딩 해주었음
	 *  getRowCount(), getColumnCount(), getValueAt(int rowIndex, int columnIndex) 이 세 매서드는 
	 *  AbstractTableModel클래스가 구현하지 않기 때문에 반드시 직접 구현을 해 줘야 한다.
	 */
	
	
	//테이블에서 선택한 오늘의 날짜를 가지고 옴
	public int getDate(int row, int col) {
		// getInstance()를 통해 얻은 인스턴스는 기본적으로 현재 시스템의 날짜와 시간을 담고있다.
		// 원하는 날짜나 시간으로 설정하기위해 set메서드 사용
		calendar.set(Calendar.YEAR, this.year); //올해의 년도를 저장
		calendar.set(Calendar.MONTH, this.month + monthplus); // 이번달의 값을 저장
		
		getThisyear = calendar.get(Calendar.YEAR);
		
		// get(Calendar.MONTH)로 얻어오는 값의 범위가 0 ~ 11이기때문에  + 1 해주었음(0이 1월)
		getThismonth = calendar.get(Calendar.MONTH) + 1;
		thismonthresult = calendar.get(Calendar.MONTH) + 1;
		Schedule.nowMonth.setText(String.valueOf(calendar.get(Calendar.YEAR)) + "  "+ String.valueOf(thismonthresult) + "월");
		
		
		calendar.set(Calendar.WEDNESDAY, row + 1);   // 행의 값을 정리
		calendar.set(Calendar.DAY_OF_WEEK, col + 1); // 열의 값을 정리
		
		return calendar.get(Calendar.DATE);
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




