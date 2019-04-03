package student.kce.erp.model;

import java.sql.Blob;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="group_attachment")
@Data
public class GroupAttachment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="group_id")
	private String groupId;
	
	@Column(name="group_code")
	private String groupCode;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="college_id")
	private int collegeId;
	
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
	
	@Column(name="file_name")
	private String fileName;
	
	@Lob
	@Column(name="file_data")
	private Blob fileData;
}
