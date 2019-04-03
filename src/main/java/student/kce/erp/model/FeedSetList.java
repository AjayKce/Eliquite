package student.kce.erp.model;

import lombok.Data;

@Data
public class FeedSetList {

	private String staffName;
	private String subjectCode;
	private String subjectTitle;
	private String[] feed;
	private String[] result;
	
}
