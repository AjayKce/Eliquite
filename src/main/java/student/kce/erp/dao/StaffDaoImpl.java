package student.kce.erp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import student.kce.erp.model.Admin;
import student.kce.erp.model.Attachment;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentMark;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.Tutor;

@Repository
public class StaffDaoImpl implements StaffDao {

	@Autowired
	SessionFactory factory;

	@Override
	public String getAdminId(String collegeId, String department) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Admin a where a.collegeId='"+collegeId+"' and a.department='"+department+"'");
		List<Admin> admins = query.list();
		if(admins.size()!=0) {
		return Integer.toString(admins.get(0).getId());
		}
		else {
			return Integer.toString(0);
		}
	}

	@Override
	public boolean isAuthenticated(String adminId, String department, String username, String password) {
		Session session = factory.getCurrentSession();
		String sql = "from Staff s where s.adminId='"+adminId+"' and s.department='"+department+"' and s.username='"+username+"' and s.password='"+password+"'";
		List<Staff> staffs = session.createQuery(sql).list();
		if(staffs.size()==0) {
			return false;
		}
		else {
		return true;
		}
	}

	@Override
	public String getStaffName(String adminId, String department, String username, String password) {
		Session session = factory.getCurrentSession();
		String sql = "from Staff s where s.adminId='"+adminId+"' and s.department='"+department+"' and s.username='"+username+"' and s.password='"+password+"'";
		List<Staff> staffs = session.createQuery(sql).list();
		if(staffs.size()!=0) {
			return staffs.get(0).getStaffName();
		}
		else {
			return "NoStaff";
		}
	}

	@Override
	public List<Tutor> getTutors(String adminId, String staffName, String department) {
		Session session = factory.getCurrentSession();
		String sql = "from Tutor t where t.adminId='"+adminId+"' and t.staffName='"+staffName+"'and t.department='"+department+"' order by t.batch,t.semester,t.section";
		Query query = session.createQuery(sql);
		List<Tutor> tutors = query.list();
		return tutors;
	}

	@Override
	public String getStaffId(String adminId, String department, String username, String password) {
		Session session = factory.getCurrentSession();
		String sql = "from Staff s where s.adminId='"+adminId+"' and s.department='"+department+"' and s.username='"+username+"' and s.password='"+password+"'";
		List<Staff> staffs = session.createQuery(sql).list();
		if(staffs.size()!=0) {
			return Integer.toString(staffs.get(0).getId());
		}
		else {
			return "0";
		}
	}

	@Override
	public void createStudent(Student theStudent) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(theStudent);
	}

	@Override
	public List<Student> getStudents(String batch, String semester, String section, String collegeId, String adminId,String staffId, String department) {
		Session session = factory.getCurrentSession();
		String sql = "from Student s where s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.collegeId='"+collegeId+"' and s.adminId='"+adminId+"' and s.staffId='"+staffId+"' and s.department='"+department+"' order by s.rollno";
		Query query = session.createQuery(sql);
		List<Student> students = query.list();
		return students;
	}

	@Override
	public Student getSingleStudent(int id) {
		Session session = factory.getCurrentSession();
		Student student = session.get(Student.class, id);
		return student;
	}

	@Override
	public void deleteStudent(Student student) {
		Session session = factory.getCurrentSession();
		session.delete(student);
	}

	@Override
	public Enrollment getSingleEnrollment(Integer enrollId) {
		Session session = factory.getCurrentSession();
		Enrollment enrollment = session.get(Enrollment.class, enrollId);
		return enrollment;
	}

	@Override
	public void deleteEnrollment(Enrollment singleEnrollment) {
		Session session = factory.getCurrentSession();
		session.delete(singleEnrollment);
	}

	@Override
	public List<SubjectAlloc> getSubjectAllocations(String adminId, String staffName, String department) {
		Session session = factory.getCurrentSession();
		String sql = "from SubjectAlloc s where s.adminId='"+adminId+"' and s.staffName='"+staffName+"' and s.department='"+department+"' order by s.batch,s.semester,s.section";
		Query query = session.createQuery(sql);
		List<SubjectAlloc> subjectAllocs = query.list();
		return subjectAllocs;
	}

	@Override
	public List<Enrollment> getEnrolledStudents(String adminId, String batch, String semester, String department,
			String section, String subjectCode) {
		Session session = factory.getCurrentSession();
		String sql = "from Enrollment s where s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.adminId='"+adminId+"' and s.subjectCode='"+subjectCode+"' and s.department='"+department+"' order by s.studentRoll";
		Query query = session.createQuery(sql);
		List<Enrollment> enrollments = query.list();
		return enrollments;
	}

	@Override
	public List<SetExam> getSetExams(String adminId, String subjectId) {
		Session session = factory.getCurrentSession();
		String sql = "from SetExam t where t.adminId='"+adminId+"' and t.subjectId='"+subjectId+"' order by t.examTitle";
		Query query = session.createQuery(sql);
		List<SetExam> setExams = query.list();
		return setExams;
	}

	@Override
	public List<StudentMark> getStudentMarks(String adminId, String staffId, String studentId, String batch,String semester, String department, String section, String studentRoll,String subjectCode) {
		Session session = factory.getCurrentSession();
		String sql = "from StudentMark s where s.adminId='"+adminId+"' and s.staffId='"+staffId+"' and s.studentId='"+studentId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' and s.studentRoll='"+studentRoll+"' and s.subjectCode='"+subjectCode+"' order by s.examTitle";
		Query query = session.createQuery(sql);
		List<StudentMark> studentMarks = query.list();
		return studentMarks;
	}

	@Override
	public SetExam getSingleSetExams(String adminId, String subjectId, String examTitle) {
		Session session = factory.getCurrentSession();
		String sql = "from SetExam t where t.adminId='"+adminId+"' and t.subjectId='"+subjectId+"' and t.examTitle='"+examTitle+"'";
		Query query = session.createQuery(sql);
		List<SetExam> setExams = query.list();
		if(setExams.size()!=0) {
		return setExams.get(0);
		}
		else {
			return null;
		}
	}

	@Override
	public StudentMark getSingleStudentMark(String adminId, String staffId, String studentId, String batch,
			String semester, String department, String section, String studentRoll, String subjectCode,
			String examTitle) {
		Session session = factory.getCurrentSession();
		String sql = "from StudentMark s where s.adminId='"+adminId+"' and s.staffId='"+staffId+"' and s.studentId='"+studentId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' and s.studentRoll='"+studentRoll+"' and s.subjectCode='"+subjectCode+"' and s.examTitle='"+examTitle+"' order by s.examTitle";
		Query query = session.createQuery(sql);
		List<StudentMark> studentMarks = query.list();
		if(studentMarks.size()!=0) {
			return studentMarks.get(0);
		}
		else {
			return new StudentMark();
		}
	}

	@Override
	public void saveStudentMark(StudentMark singleStudentMark) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(singleStudentMark);
	}

	@Override
	public List<Attachment> getAttachments(String adminId, String batch, String semester, String department,
			String section, String subjectCode) {
		Session session = factory.getCurrentSession();
		String sql = "from Attachment s where s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.adminId='"+adminId+"' and s.subjectCode='"+subjectCode+"' and s.department='"+department+"'";
		Query query = session.createQuery(sql);
		List<Attachment> attachments = query.list();
		return attachments;
	}

	@Override
	public Attachment getFile(String id) {
		Session session = factory.getCurrentSession();
		Attachment attachment = session.get(Attachment.class, Integer.parseInt(id));
		return attachment;
	}

	@Override
	public void deleteFile(String id) {
		Session session = factory.getCurrentSession();
		Attachment attachment = session.get(Attachment.class, Integer.parseInt(id));
		session.delete(attachment);
	}

	@Override
	public List<StudentMark> getStudentFullMark(String adminId, String studentId, String batch, String semester,
			String department, String section, String studentRoll, String subjectCode) {
		Session session = factory.getCurrentSession();
		String sql = "from StudentMark s where s.adminId='"+adminId+"' and s.studentId='"+studentId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' and s.studentRoll='"+studentRoll+"' and s.subjectCode='"+subjectCode+"' order by s.examTitle";
		Query query = session.createQuery(sql);
		List<StudentMark> studentMarks = query.list();
		return studentMarks;
	}

	@Override
	public String getSubjectId(String adminId, String batch, String semester, String department, String subjectCode) {
		Session session = factory.getCurrentSession();
		String sql = "from DepartmentSubject s where s.adminId='"+adminId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.subjectCode='"+subjectCode+"'";
		Query query = session.createQuery(sql);
		List<DepartmentSubject> subjectList = query.list();
		if(subjectList.size()!=0) {
		return Integer.toString(subjectList.get(0).getId());
		}
		else {
			return "0";
		}
	}

	@Override
	public List<AttendanceScheduler> getSchedules(String collegeId, String batch, String semester, String department) {
		Session session = factory.getCurrentSession();
		String sql = "from AttendanceScheduler s where s.collegeId='"+collegeId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' order by s.hour";
		Query query = session.createQuery(sql);
		List<AttendanceScheduler> schedules = query.list();
		return schedules;
	}

	@Override
	public AttendanceResult getNewAttendanceResult(String date, String batch, String semester, String department,
			String period,String studentId) {
		Session session = factory.getCurrentSession();
		String sql = "from AttendanceResult s where s.date='"+date+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.period='"+period+"' and s.studentId='"+studentId+"'";
		Query query = session.createQuery(sql);
		List<AttendanceResult> attendanceResults = query.list();
		if(attendanceResults.size()!=0) {
			return attendanceResults.get(0);
		}
		else {
			return new AttendanceResult();
		}
	}

	@Override
	public void addAttendanceResult(AttendanceResult attendanceResult) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(attendanceResult);
	}

	@Override
	public String[] getSubjectPeriods(String date, String adminId, String subjectId, String staffId, String batch,
			String semester, String department, String section) {
		Session session = factory.getCurrentSession();
		String sql = "select distinct s.period from attendance_result s where s.date='"+date+"' and s.admin_id='"+adminId+"' and s.subject_id='"+subjectId+"' and s.staff_id='"+staffId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.department='"+department+"' order by s.period";
		Query query = session.createSQLQuery(sql);
		List<String> periods = query.list();
		String[] arr = new String[periods.size()]; 
		arr = periods.toArray(arr);
		return arr;
	}

	@Override
	public List<AttendanceResult> getAttendanceResult(String date, String adminId, String subjectId, String staffId,
			String batch, String semester, String department, String section, String period) {
		Session session = factory.getCurrentSession();
		String sql = "from AttendanceResult s where s.date='"+date+"' and s.adminId='"+adminId+"' and s.subjectId='"+subjectId+"' and s.staffId='"+staffId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.department='"+department+"' and s.period='"+period+"' order by s.rollno";
		Query query = session.createQuery(sql);
		List<AttendanceResult> attendanceResults = query.list();
		return attendanceResults;
	}

	@Override
	public float getAttendanceAllValue(String adminId, String batch, String semester, String department, String section,
			String subjectCode, int studentId) {
		Session session = factory.getCurrentSession();
		String sql = "from AttendanceResult s where s.studentId='"+studentId+"' and s.adminId='"+adminId+"' and s.subjectCode='"+subjectCode+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.department='"+department+"'";
		Query query = session.createQuery(sql);
		List<AttendanceResult> results = query.list();
		return (float)results.size();
	}

	@Override
	public float getAttendanceValue(String adminId, String batch, String semester, String department, String section,
			String subjectCode, int studentId, String status) {
		Session session = factory.getCurrentSession();
		String sql = "from AttendanceResult s where s.studentId='"+studentId+"' and s.adminId='"+adminId+"' and s.subjectCode='"+subjectCode+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.department='"+department+"' and s.status='"+status+"'";
		Query query = session.createQuery(sql);
		List<AttendanceResult> results = query.list();
		return (float)results.size();
	}

	@Override
	public List<AttendanceResult> getPresentDays(String adminId, String batch, String semester, String department,
			String section, String subjectCode, String studentId, String status) {
		Session session = factory.getCurrentSession();
		String sql = "from AttendanceResult s where s.studentId='"+studentId+"' and s.adminId='"+adminId+"' and s.subjectCode='"+subjectCode+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.section='"+section+"' and s.department='"+department+"' and s.status='"+status+"' order by s.date,s.period";
		Query query = session.createQuery(sql);
		List<AttendanceResult> results = query.list();
		return results;
	}

	@Override
	public List<Student> getFullStudents() {
		Session session = factory.getCurrentSession();
		List<Student> students = session.createQuery("from Student order by rollno").list();
		return students;
	}
	
}
