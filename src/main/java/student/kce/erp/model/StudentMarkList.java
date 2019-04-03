package student.kce.erp.model;


import lombok.Data;

@Data
public class StudentMarkList {

private String examTitle;
private String[] studentRoll;
private String[] subjectCode;
private String[] subjectId;
private String[] studentId;
private String[] obtainedMark;

}