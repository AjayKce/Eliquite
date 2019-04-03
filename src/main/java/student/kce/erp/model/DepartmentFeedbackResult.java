package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="department_feedback_result")
@Data
public class DepartmentFeedbackResult {

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
	
	@Column(name="batch")
	private String batch;

	@Column(name="semester")
	private String semester;

	@Column(name="department")
	private String department;

	@Column(name="section")
	private String section;
	
	@Column(name="student_name")
	private String studentName;

	@Column(name="rollno")
	private String rollno;
	
	@Column(name="feed_set_id")
	private int feedSetId;
	
	@Column(name="feed")
	private String feed;
	
	@Column(name="result")
	private String result;

}
