package student.kce.erp.model;

import lombok.Data;

@Data
public class FullAttendanceResult {
	private int adminId;
	private int collegeId;
	private int studentId;
	private String studentName;
	private String studentRoll;
	private String batch;
	private String semester;
	private String department;
	private String section;
	private String date;

	private int attendanceResultId[];
	private int staffId[];
	private int subjectId[];
	private String staffName[];
	private String subjectTitle[];
	private String subjectCode[];
	private String startTime[];
	private String endTime[];
	private String period[];
	private String status[];
}
