package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="feedback_alloc")
@Data
public class FeedBackAlloc {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="college_id")
	private int collegeId;
	
	@Column(name="feedset_id")
	private int feedsetId;
	
	@Column(name="feedset_title")
	private String feedsetTitle;
	
	@Column(name="department")
	private String department;
}
