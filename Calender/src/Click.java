import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;


// 테이블에서의 마우스 클릭 제어 클래스
class Click extends MouseAdapter {
	Schedule tb2;
	Object value;
	public static InputDialog textinput;
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// 마우스 클릭시 행의 인덱스를 얻어옴
		int row = tb2.tb.rowAtPoint(e.getPoint());
		// 마우스 클릭시 컬럼의 인덱스를 얻어옴
		int col = tb2.tb.columnAtPoint(e.getPoint());

		// 선택한 셀의 저장된 값
		// 첫번째 줄의 row에서 20이상의 수가 있을 경우 전월 값을 가져옴
		// 마지막 줄의 row에서 10이하의 수가 있을 경우 다음달 값을 가져옴
		if (row >= 0 && col >= 0) {
			value = tb2.tb.getValueAt(row, col); // 클릭한 cell의 클래스를 얻어옴
			System.out.println("value 값 : " + value);
			
			// System.out.println(tm1.getDate(row, col));
			// 다이얼로그를 열어서 텍스트정보를 입력하고
			// 선택한 월의 폴더를 생성
			// 선택한 일의 text를 생성
			int day = (int) value;
			// System.out.println("월 : " + CModel.calendar.get(Calendar.MONTH));
			// System.out.println("row 값은 : " + row + "day 값은 : " + day);
			if(day >= 20 && row == 0){
				CModel.getThismonth = CModel.calendar.get(Calendar.MONTH) + 1;
			}
			else if(day <= 10 && row == 4){
				CModel.getThismonth = CModel.calendar.get(Calendar.MONTH) + 1;
			}
			
			
			textinput = new InputDialog(CModel.getThisyear,
					CModel.getThismonth,day);
			
		}

	}

}
