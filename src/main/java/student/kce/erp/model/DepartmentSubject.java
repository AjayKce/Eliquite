package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="subject")
@Data
public class DepartmentSubject {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="batch")
	private String batch;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="department")
	private String department;
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="subject_title")
	private String subjectTitle;
	
	@Column(name="subject_group")
	private String subjectGroup;
	
	@Column(name="credit")
	private int credit;
}
