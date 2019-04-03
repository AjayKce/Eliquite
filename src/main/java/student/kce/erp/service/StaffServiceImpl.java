package student.kce.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.kce.erp.dao.StaffDao;
import student.kce.erp.model.Attachment;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentMark;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.Tutor;

@Service
public class StaffServiceImpl implements StaffService {
	
	@Autowired
	StaffDao staffDao;

	@Override
	@Transactional
	public String getAdminId(String collegeId, String department) {
		return staffDao.getAdminId(collegeId,department);
	}

	@Override
	@Transactional
	public boolean isAuthenticated(String adminId, String department, String username, String password) {
		return staffDao.isAuthenticated(adminId,department,username,password);
	}

	@Override
	@Transactional
	public String getStaffName(String adminId, String department, String username, String password) {
		return staffDao.getStaffName(adminId,department,username,password);
	}

	@Override
	@Transactional
	public List<Tutor> getTutors(String adminId, String staffName, String department) {
		return staffDao.getTutors(adminId,staffName,department);
	}

	@Override
	@Transactional
	public String getStaffId(String adminId, String department, String username, String password) {
		return staffDao.getStaffId(adminId,department,username,password);
	}

	@Override
	@Transactional
	public void createStudent(Student theStudent) {
		staffDao.createStudent(theStudent);
	}

	@Override
	@Transactional
	public List<Student> getStudents(String batch, String semester, String section, String collegeId, String adminId,
			String staffId, String department) {
		return staffDao.getStudents(batch,semester,section,collegeId,adminId,staffId,department);
	}

	@Override
	@Transactional
	public Student getSingleStudent(int id) {
		return staffDao.getSingleStudent(id);
	}

	@Override
	@Transactional
	public void deleteStudent(Student student) {
		staffDao.deleteStudent(student);
	}

	@Override
	@Transactional
	public Enrollment getSingleEnrollment(Integer enrollId) {
		return staffDao.getSingleEnrollment(enrollId);
	}

	@Override
	@Transactional
	public void deleteEnrollment(Enrollment singleEnrollment) {
		staffDao.deleteEnrollment(singleEnrollment);
	}

	@Override
	@Transactional
	public List<SubjectAlloc> getSubjectAllocations(String adminId, String staffName, String department) {
		return staffDao.getSubjectAllocations(adminId,staffName,department);
	}

	@Override
	@Transactional
	public List<Enrollment> getEnrolledStudents(String adminId, String batch, String semester, String department,
			String section, String subjectCode) {
		return staffDao.getEnrolledStudents(adminId,batch,semester,department,section,subjectCode);
	}

	@Override
	@Transactional
	public List<SetExam> getSetExams(String adminId, String subjectId) {
		return staffDao.getSetExams(adminId,subjectId);
	}

	@Override
	@Transactional
	public List<StudentMark> getStudentMarks(String adminId, String staffId, String studentId, String batch,
			String semester, String department, String section, String studentRoll,String subjectCode) {
		return staffDao.getStudentMarks(adminId,staffId,studentId,batch,semester,department,section,studentRoll,subjectCode);
	}

	@Override
	@Transactional
	public SetExam getSingleSetExams(String adminId, String subjectId, String examTitle) {
		return staffDao.getSingleSetExams(adminId,subjectId,examTitle);
	}

	@Override
	@Transactional
	public StudentMark getSingleStudentMark(String adminId, String staffId, String studentId, String batch,
			String semester, String department, String section, String studentRoll, String subjectCode,
			String examTitle) {
		return staffDao.getSingleStudentMark(adminId,staffId,studentId,batch,semester,department,section,studentRoll,subjectCode,examTitle);
	}

	@Override
	@Transactional
	public void saveStudentMark(StudentMark singleStudentMark) {
		staffDao.saveStudentMark(singleStudentMark);
	}

	@Override
	@Transactional
	public List<Attachment> getAttachments(String adminId, String batch, String semester, String department,
			String section, String subjectCode) {
		return staffDao.getAttachments(adminId,batch,semester,department,section,subjectCode);
	}

	@Override
	@Transactional
	public Attachment getFile(String id) {
		return staffDao.getFile(id);
	}

	@Override
	@Transactional
	public void deleteFile(String id) {
		staffDao.deleteFile(id);
	}

	@Override
	@Transactional
	public List<StudentMark> getStudentFullMark(String adminId, String studentId, String batch, String semester,
			String department, String section, String studentRoll, String subjectCode) {
		return staffDao.getStudentFullMark(adminId,studentId,batch,semester,department,section,studentRoll,subjectCode);
	}

	@Override
	@Transactional
	public String getSubjectId(String adminId, String batch, String semester, String department, String subjectCode) {
		return staffDao.getSubjectId(adminId,batch,semester,department,subjectCode);
	}

	@Override
	@Transactional
	public List<AttendanceScheduler> getSchedules(String collegeId, String batch, String semester, String department) {
		return staffDao.getSchedules(collegeId,batch,semester,department);
	}

	@Override
	@Transactional
	public AttendanceResult getNewAttendanceResult(String date, String batch, String semester, String department,
			String period,String studentId) {
		return staffDao.getNewAttendanceResult(date,batch,semester,department,period,studentId);
	}

	@Override
	@Transactional
	public void addAttendanceResult(AttendanceResult attendanceResult) {
		staffDao.addAttendanceResult(attendanceResult);
	}

	@Override
	@Transactional
	public String[] getSubjectPeriods(String date, String adminId, String subjectId, String staffId, String batch,
			String semester, String department, String section) {
		return staffDao.getSubjectPeriods(date,adminId,subjectId,staffId,batch,semester,department,section);
	}

	@Override
	@Transactional
	public List<AttendanceResult> getAttendanceResult(String date, String adminId, String subjectId, String staffId,
			String batch, String semester, String department, String section, String period) {
		return staffDao.getAttendanceResult(date,adminId,subjectId,staffId,batch,semester,department,section,period);
	}

	@Override
	@Transactional
	public float getAttendanceAllValue(String adminId, String batch, String semester, String department, String section,
			String subjectCode, int studentId) {
		return staffDao.getAttendanceAllValue(adminId,batch,semester,department,section,subjectCode,studentId);
	}

	@Override
	@Transactional
	public float getAttendanceValue(String adminId, String batch, String semester, String department, String section,
			String subjectCode, int studentId, String status) {
		return staffDao.getAttendanceValue(adminId,batch,semester,department,section,subjectCode,studentId,status);
	}

	@Override
	@Transactional
	public List<AttendanceResult> getPresentDays(String adminId, String batch, String semester, String department,
			String section, String subjectCode, String studentId, String status) {
		return staffDao.getPresentDays(adminId,batch,semester,department,section,subjectCode,studentId,status);
	}

	@Override
	@Transactional
	public List<Student> getFullStudents() {
		return staffDao.getFullStudents();
	}

}
