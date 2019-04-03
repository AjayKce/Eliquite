package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="college_feed")
@Data
public class CollegeFeed {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="college_id")
	private int collegeId;
	
	@Column(name="feedset_id")
	private int feedsetId;
	
	@Column(name="feed")
	private String feed;

}
