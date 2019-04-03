package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="student_mark")
@Data
public class StudentMark {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="staff_id")
	private int staffId;
	
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
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="student_roll")
	private String studentRoll;
	
	@Column(name="exam_title")
	private String examTitle;
	
	@Column(name="obtained_mark")
	private String obtainedMark;
	
	@Column(name="full_mark")
	private String fullMark;
	
	@Column(name="status")
	private String status;

}
