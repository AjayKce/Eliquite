package student.kce.erp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="setexam")
@Data
public class SetExam {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="subject_id")
	private int subjectId;
	
	@Column(name="admin_id")
	private int adminId;
	
	@Column(name="exam_title")
	private String examTitle;
	
	@Column(name="fullmark")
	private String fullmark;
	
	@Column(name="passmark")
	private String passmark;

}
