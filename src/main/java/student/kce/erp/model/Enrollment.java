package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="enrollment")
@Data
public class Enrollment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="college_id")
	private int collegeId;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="subject_id")
	private int subjectId;
	
	@Column(name="student_roll")
	private String studentRoll;
	
	@Column(name="student_name")
	private String studentName;
	
	@Column(name="batch")
	private String batch;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="department")
	private String department;
	
	@Column(name="section")
	private String section;
	
	@Column(name="subject_title")
	private String subjectTitle;
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="subject_group")
	private String subjectGroup;
	
	@Column(name="credit")
	private String credit;
}
