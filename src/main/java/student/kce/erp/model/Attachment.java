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

@Data
@Entity
@Table(name="attachment")
public class Attachment {

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

	@Column(name="section")
	private String section;
	
	@Column(name="subject_code")
	private String subjectCode;
	
	@Column(name="file_name")
	private String fileName;
	
	@Lob
	@Column(name="file_data")
	private Blob fileData;

	public Attachment() {
	}
	
}
