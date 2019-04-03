package student.kce.erp.model;

import lombok.Data;

@Data
public class CalculateDepartmentFeedPercentage {

	private String[] feeds;
	private int[] excellent;
	private int[] good;
	private int[] moderate;
	private int[] intermediate;
	private int[] poor;
	private int[] veryPoor;
}
