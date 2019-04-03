package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="tutor")
@Data
public class Tutor {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="batch")
	private String batch;
	
	@Column(name="staff_name")
	private String staffName;
	
	@Column(name="department")
	private String department;
	
	@Column(name="section")
	private String section;
	
	@Column(name="semester")
	private String semester;
	
	@Column(name="status")
	private String status;
	
}
