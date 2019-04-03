package student.kce.erp.dao;

import java.util.List;

import student.kce.erp.model.Attachment;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentMark;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.Tutor;

public interface StaffDao {

	String getAdminId(String collegeId, String department);

	boolean isAuthenticated(String adminId, String department, String username, String password);

	String getStaffName(String adminId, String department, String username, String password);

	List<Tutor> getTutors(String adminId, String staffName, String department);

	String getStaffId(String adminId, String department, String username, String password);

	void createStudent(Student theStudent);

	List<Student> getStudents(String batch, String semester, String section, String collegeId, String adminId,
			String staffId, String department);

	Student getSingleStudent(int id);

	void deleteStudent(Student student);

	Enrollment getSingleEnrollment(Integer enrollId);

	void deleteEnrollment(Enrollment singleEnrollment);

	List<SubjectAlloc> getSubjectAllocations(String adminId, String staffName, String department);

	List<Enrollment> getEnrolledStudents(String adminId, String batch, String semester, String department,
			String section, String subjectCode);

	List<SetExam> getSetExams(String adminId, String subjectId);

	List<StudentMark> getStudentMarks(String adminId, String staffId, String studentId, String batch, String semester,
			String department, String section, String studentRoll,String subjectCode);

	SetExam getSingleSetExams(String adminId, String subjectId, String examTitle);

	StudentMark getSingleStudentMark(String adminId, String staffId, String studentId, String batch, String semester,
			String department, String section, String studentRoll, String subjectCode, String examTitle);

	void saveStudentMark(StudentMark singleStudentMark);

	List<Attachment> getAttachments(String adminId, String batch, String semester, String department, String section,
			String subjectCode);

	Attachment getFile(String id);

	void deleteFile(String id);

	List<StudentMark> getStudentFullMark(String adminId, String studentId, String batch, String semester,
			String department, String section, String studentRoll, String subjectCode);

	String getSubjectId(String adminId, String batch, String semester, String department, String subjectCode);

	List<AttendanceScheduler> getSchedules(String collegeId, String batch, String semester, String department);

	AttendanceResult getNewAttendanceResult(String date, String batch, String semester, String department,
			String period,String studentId);

	void addAttendanceResult(AttendanceResult attendanceResult);

	String[] getSubjectPeriods(String date, String adminId, String subjectId, String staffId, String batch,
			String semester, String department, String section);

	List<AttendanceResult> getAttendanceResult(String date, String adminId, String subjectId, String staffId,
			String batch, String semester, String department, String section, String period);

	float getAttendanceAllValue(String adminId, String batch, String semester, String department, String section,
			String subjectCode, int studentId);

	float getAttendanceValue(String adminId, String batch, String semester, String department, String section,
			String subjectCode, int studentId, String status);

	List<AttendanceResult> getPresentDays(String adminId, String batch, String semester, String department,
			String section, String subjectCode, String studentId, String status);

	List<Student> getFullStudents();

}
