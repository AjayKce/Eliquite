package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="requesterp")
@Data
public class RequestErp {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="college_name")
	private String collegeName;
	
	@Column(name="college_email")
	private String collegeEmail;
	
	@Column(name="college_contact")
	private String collegeContact;
	
	
}
