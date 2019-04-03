package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="department_feed_set")
@Data
public class DepartmentFeedbackSet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="college_id")
	private int collegeId;

	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="department")
	private String department;

	@Column(name="feed_set")
	private String feedSet;

}
