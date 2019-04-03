package student.kce.erp.dao;

import java.util.List;

import javax.security.auth.Subject;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import student.kce.erp.model.Admin;
import student.kce.erp.model.Batch;
import student.kce.erp.model.DepartmentFeed;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.DepartmentFeedbackSet;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentClass;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.Tutor;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Autowired
	SessionFactory factory;
	
	@Autowired
	HttpSession pageSession;

	@Override
	public boolean isAdmin(String collegeId, String user, String pass, String dept) {
		Session session = factory.getCurrentSession();
		String sql = "from Admin a where a.collegeId='"+collegeId+"' and a.username='"+user+"' and a.password='"+pass+"' and a.department='"+dept+"'";
		Query query = session.createQuery(sql);
		List<Admin> admins = query.list();
		if(admins.size()!=0) {
			return true;
		}
		return false;
	}

	@Override
	public Admin getAdmin(String collegeId, String user, String pass, String dept) {
		Session session = factory.getCurrentSession();
		String sql = "from Admin a where a.collegeId='"+collegeId+"' and a.username='"+user+"' and a.password='"+pass+"' and a.department='"+dept+"'";
		Query query = session.createQuery(sql);
		List<Admin> admins = query.list();
		Admin admin = null;
		for(Admin temp:admins) {
			admin=temp;
		}
		return admin;
	}

	@Override
	public List<Batch> getBatches(String adminId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Batch b where b.adminId='"+adminId+"' order by b.year");
		List<Batch> batches = query.list();
		return batches;
	}

	@Override
	public void addBatch(Batch theBatch) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(theBatch);
	}

	@Override
	public Batch getBatch(String id,String adminId) {
		Session session = factory.getCurrentSession();
		Batch batch = session.get(Batch.class, Integer.parseInt(id));
		if(batch.getAdminId()==Integer.parseInt(adminId)) {
			return batch;
		}
		else {
		return null;
		}
	}

	@Override
	public void deleteBatch(Batch batch) {
		Session session = factory.getCurrentSession();
		session.delete(batch);
	}

	@Override
	public List<StudentClass> getDepartmentClasses(String adminId) {
		Session session = factory.getCurrentSession();
		Query query  = session.createQuery("from StudentClass c where c.adminId='"+adminId+"' order by c.batch,c.semester");
		List<StudentClass> classes = query.list();
		return classes;
	}

	@Override
	public boolean isClassAvailable(int adminId, String batch, String section, String semester) {
		Session session = factory.getCurrentSession();
		String sql = "from StudentClass c where c.adminId='"+adminId+"' and c.batch='"+batch+"' and c.section='"+section+"' and c.semester='"+semester+"'";
		Query query  = session.createQuery(sql);
		List<StudentClass> classes= query.list();
		if(classes.size()==0) {
			return false;
		}
		else {
		return true;
		}
	}

	@Override
	public void addStudentClass(StudentClass theClass) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(theClass);
	}

	@Override
	public StudentClass getSingleClass(String id, String adminId) {
		Session session = factory.getCurrentSession();
		StudentClass singleClass = session.get(StudentClass.class, Integer.parseInt(id));
		if(singleClass.getAdminId()==Integer.parseInt(adminId)) {
			return singleClass;
		}
		else {
		return null;
		}
	}

	@Override
	public void deleteClass(StudentClass singleClass) {
		Session session = factory.getCurrentSession();
		session.delete(singleClass);
	}

	@Override
	public List<Staff> getAllStaffs(String adminId) {
		Session session = factory.getCurrentSession();
		 Query query= session.createQuery("from Staff s where s.adminId='"+adminId+"' order by s.staffName");
		 List<Staff> staffs = query.list();
		return staffs;
	}

	@Override
	public void createStaff(Staff staff) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(staff);
	}

	@Override
	public Staff getSingleStaff(String adminId, String id) {
		Session session = factory.getCurrentSession();
		Staff staff = session.get(Staff.class,Integer.parseInt(id));
		if(staff.getAdminId()==Integer.parseInt(adminId)) {
			return staff;
		}
		else {
		return null;
		}
	}

	@Override
	public void deleteStaff(Staff staff) {
		Session session = factory.getCurrentSession();
		session.delete(staff);
	}

	@Override
	public boolean isSubjectAvailable(String batch,String semester,int adminId, String subjectCode) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentSubject s where s.batch='"+batch+"' and s.semester='"+semester+"' and s.adminId='"+adminId+"' and s.subjectCode='"+subjectCode+"'");
		List<Subject> subjects = query.list();
		if(subjects.size()==0) {
			return false;
		}
		else {
		return true;
		}
	}

	@Override
	public void addSubject(DepartmentSubject theSubject) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(theSubject);
	}

	@Override
	public List<DepartmentSubject> getSubjects(String adminId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentSubject s where s.adminId='"+adminId+"' order by s.batch,s.semester");
		List<DepartmentSubject> subjects = query.list();
		return subjects;
	}

	@Override
	public List<SetExam> getExamSets(String adminId, String id) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from SetExam e where e.adminId='"+adminId+"' and e.subjectId='"+id+"' order by e.examTitle");
		List<SetExam> examsets = query.list();
		return examsets;
	}

	@Override
	public void addExamSet(SetExam examset) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(examset);
	}

	@Override
	public SetExam getSingleExamSet(int setId) {
		Session session = factory.getCurrentSession();
		SetExam examset = session.get(SetExam.class,setId);
		return examset;
	}

	@Override
	public void deleteExamSet(SetExam singleExamSet) {
		Session session = factory.getCurrentSession();
		session.delete(singleExamSet);
	}

	@Override
	public DepartmentSubject getSingleSubject(int subjectId) {
		Session session = factory.getCurrentSession();
		DepartmentSubject subject = session.get(DepartmentSubject.class, subjectId); 
		return subject;
	}

	@Override
	public void deleteSubject(DepartmentSubject singleSubject) {
		Session session = factory.getCurrentSession();
		session.delete(singleSubject);
	}

	@Override
	public List<Tutor> getTutors(String adminId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from Tutor t where t.adminId='"+adminId+"' order by t.batch,t.semester,t.section");
		List<Tutor> tutors = query.list();
		return tutors;
	}

	@Override
	public void createTutor(Tutor theTutor) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(theTutor);
	}

	@Override
	public Tutor getSingleTutor(String id) {
		Session session = factory.getCurrentSession();
		Tutor tutor = session.get(Tutor.class, Integer.parseInt(id));
		return tutor;
	}

	@Override
	public void deleteTutor(Tutor tutor) {
		Session session = factory.getCurrentSession();
		session.delete(tutor);
	}

	@Override
	public List<SubjectAlloc> getAllSubjectAlloc(String adminId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from SubjectAlloc s where s.adminId='"+adminId+"' order by s.batch,s.semester,s.section");
		List<SubjectAlloc> subjectAllocs = query.list();
		return subjectAllocs;
	}

	@Override
	public void createSubjectAllocation(SubjectAlloc subjectAlloc) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(subjectAlloc);
	}

	@Override
	public SubjectAlloc getSingleSubjectAlloc(String id) {
		Session session = factory.getCurrentSession();
		SubjectAlloc subjectAlloc = session.get(SubjectAlloc.class, Integer.parseInt(id));
		return subjectAlloc;
	}

	@Override
	public void deleteSubjectAlloc(SubjectAlloc subjectAlloc) {
		Session session = factory.getCurrentSession();
		session.delete(subjectAlloc);
	}

	@Override
	public List<DepartmentFeedbackSet> getDepartmentFeedSets(String collegeId, String adminId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentFeedbackSet d where d.adminId='"+adminId+"' and d.collegeId='"+collegeId+"' order by d.feedSet");
		List<DepartmentFeedbackSet> departmentFeedbackSets = query.list();
		return departmentFeedbackSets;
	}

	@Override
	public void saveDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(departmentFeedbackSet);
	}

	@Override
	public List<DepartmentFeed> getDepartmentFeeds(String departmentFeedSetId, String adminId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentFeed d where d.adminId='"+adminId+"' and d.feedSetId='"+departmentFeedSetId+"' order by d.feed");
		List<DepartmentFeed> departmentFeeds = query.list();
		return departmentFeeds;
	}

	@Override
	public void saveDepartmentFeed(DepartmentFeed departmentFeed) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(departmentFeed);
	}

	@Override
	public void deleteDepartmentFeedSet(String departmentFeedSetId) {
		Session session = factory.getCurrentSession();
		DepartmentFeedbackSet departmentFeedbackSet = session.get(DepartmentFeedbackSet.class, Integer.parseInt(departmentFeedSetId));
		session.delete(departmentFeedbackSet);
		Query query = session.createQuery("from DepartmentFeed d where d.feedSetId='"+departmentFeedSetId+"'");
		List<DepartmentFeed> departmentFeeds = query.list();
		for(DepartmentFeed temp:departmentFeeds) {
			session.delete(temp);
		}
	}

	@Override
	public void deleteDepartmentFeed(String departmentFeedId) {
		Session session = factory.getCurrentSession();
		DepartmentFeed departmentFeed = session.get(DepartmentFeed.class, Integer.parseInt(departmentFeedId));
		session.delete(departmentFeed);
	}

	@Override
	public List<Student> getStudents(String adminId, String batch, String semester, String department, String section) {
		Session session = factory.getCurrentSession();
		String feedSetId = pageSession.getAttribute("feedSetId").toString();
		Query query = session.createQuery("from Student d where d.rollno in(select e.rollno from DepartmentFeedbackResult e where e.feedSetId='"+feedSetId+"' and e.batch='"+batch+"' and e.semester='"+semester+"' and e.department='"+department+"' and e.adminId='"+adminId+"' and e.section='"+section+"') order by d.rollno");
		List<Student> students = query.list();
		return students;
		
	}

	@Override
	public List<DepartmentFeedbackResult> getDepartmentFeedbackResults(String feedSetId, String adminId) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentFeedbackResult d where d.adminId='"+adminId+"' and d.feedSetId='"+feedSetId+"' order by d.feed");
		List<DepartmentFeedbackResult> departmentFeedResults = query.list();
		return departmentFeedResults;
	}

	@Override
	public float getDepartmentFeedPercentage(String adminId, String feedSetId, String result) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentFeedbackResult d where d.adminId='"+adminId+"' and d.feedSetId='"+feedSetId+"' and d.result='"+result+"'");
		List<DepartmentFeedbackResult> departmentFeedResults = query.list();
		return departmentFeedResults.size();
	}

	@Override
	public List<DepartmentFeedbackResult> getEachDepartmentFeedbackResults(String feedSetId, String adminId,String feed) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentFeedbackResult d where d.adminId='"+adminId+"' and d.feedSetId='"+feedSetId+"' and d.feed='"+feed+"'");
		List<DepartmentFeedbackResult> departmentFeedResults = query.list();
		return departmentFeedResults;
	}

	@Override
	public float getEachDepartmentFeedPercentage(String adminId, String feedSetId, String result, String feed) {
		Session session = factory.getCurrentSession();
		Query query = session.createQuery("from DepartmentFeedbackResult d where d.adminId='"+adminId+"' and d.feedSetId='"+feedSetId+"' and d.result='"+result+"' and d.feed='"+feed+"'" );
		List<DepartmentFeedbackResult> departmentFeedResults = query.list();
		return departmentFeedResults.size();
	}

}
