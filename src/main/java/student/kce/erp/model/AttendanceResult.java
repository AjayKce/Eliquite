package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="attendance_result")
@Data
public class AttendanceResult {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="admin_id")
	private int adminId;

	@Column(name="college_id")
	private int collegeId;

	@Column(name="staff_id")
	private int staffId;
	
	@Column(name="subject_id")
	private int subjectId;
	
	@Column(name="student_id")
	private int studentId;

	@Column(name="batch")
	private String batch;

	@Column(name="semester")
	private String semester;

	@Column(name="department")
	private String department;

	@Column(name="section")
	private String section;
	
	@Column(name="studentName")
	private String studentName;
	
	@Column(name="rollno")
	private String rollno;
	
	@Column(name="subject_title")
	private String subjectTitle;
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="staff_name")
	private String staffName;
	
	@Column(name="date")
	private String date;
	
	@Column(name="period")
	private String period;
	
	@Column(name="start_time")
	private String startTime;
	
	@Column(name="end_time")
	private String endTime;
	
	@Column(name="status")
	private String status;
	
}
