package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="create_feedback")
@Data
public class CreateFeedback {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="college_id")
	private int collegeId;

	@Column(name="admin_id")
	private int adminId;

	@Column(name="staff_id")
	private int staffId;
	
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="subject_id")
	private int subjectId;
	
	@Column(name="student_name")
	private String studentName;
	
	@Column(name="student_roll")
	private String studentRoll;
	
	@Column(name="staff_name")
	private String staffName;
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="subject_title")
	private String subjectTitle;

	@Column(name="batch")
	private String batch;

	@Column(name="semester")
	private String semester;

	@Column(name="department")
	private String department;

	@Column(name="section")
	private String section;
	
	@Column(name="feed")
	private String feed;
	
	@Column(name="result")
	private String result;
	
}
