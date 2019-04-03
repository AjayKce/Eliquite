package student.kce.erp.model;

import lombok.Data;

@Data
public class AttendanceList {

	private String period;
	private String date;
	private String department;
	
	private String[] studentId;
	private String[] rollno;
	private String[] subjectId;
	private String[] subjectCode;
	private String[] studentName;
	private String[] result;
	public AttendanceList() {
	}
}
