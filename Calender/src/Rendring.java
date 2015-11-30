import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

// 테이블의 각 셀의 색상을 상황에 따라 지정하기 위한 클래스
class Rendring extends DefaultTableCellRenderer {
	// 셀렌더러  생성
    //적용된 셀의 Component 를 얻어와서 수정후 수정된 Component 를 리턴하는
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
			Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
			CModel tm1 = new CModel();
			Calendar dt_1;
			Date dt = new Date();

			// 선택된 셀만 밝은 회색으로 처리
			if (row == Schedule.tb.getSelectedRow() && column == Schedule.tb.getSelectedColumn()) {
				cell.setBackground(Color.LIGHT_GRAY);
			} 
			else {
				dt_1 = Calendar.getInstance();
				// Date를 Calendar로 변환하기 위해
				dt_1.setTime(dt);
				cell.setBackground(Color.white);
			}
			
	
			// 오늘 날짜에 해당하는 셀을 오렌지색으로 처리
			if (tm1.getDate(row, column) == dt.getDate() && tm1.thismonthresult == dt.getMonth()+1) {


				if(row == 0 && dt.getDate() > 20){
				//첫번째 줄의 날짜가 20이상일 경우 색칠하지 않는다.	
				}
				else if(row == 4 && dt.getDate() < 10){
				//마지막 줄의 날짜가 10이하일 경우 색칠하지 않는다.	
				}
				else{
					cell.setBackground(Color.orange);
				}
			}
		return cell;

	}
}