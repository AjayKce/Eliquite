package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="subject_alloc")
@Data
public class SubjectAlloc {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="staff_name")
	private String staffName;
	
	@Column(name="batch")
	private String batch;
	
	@Column(name="department")
	private String department;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="section")
	private String section;
}
