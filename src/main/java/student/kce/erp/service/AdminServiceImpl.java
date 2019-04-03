package student.kce.erp.service;

import java.util.List;

import javax.security.auth.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.kce.erp.dao.AdminDao;
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

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDao adminDao;
	
	@Override
	@Transactional
	public boolean isAdmin(String collegeId, String user, String pass, String dept) {
		return adminDao.isAdmin(collegeId,user,pass,dept);
	}

	@Override
	@Transactional
	public Admin getAdmin(String collegeId, String user, String pass, String dept) {
		return adminDao.getAdmin(collegeId,user,pass,dept);
	}

	@Override
	@Transactional
	public List<Batch> getBatches(String adminId) {
		return adminDao.getBatches(adminId) ;
	}

	@Override
	@Transactional
	public void addBatch(Batch theBatch) {
		adminDao.addBatch(theBatch);		
	}

	@Override
	@Transactional
	public Batch getBatch(String id,String adminId) {
		return adminDao.getBatch(id,adminId);
	}

	@Override
	@Transactional
	public void deleteBatch(Batch batch) {
		adminDao.deleteBatch(batch);
	}

	@Override
	@Transactional
	public List<StudentClass> getDepartmentClasses(String adminId) {
		// TODO Auto-generated method stub
		return adminDao.getDepartmentClasses(adminId);
	}

	@Override
	@Transactional
	public boolean isClassAvailable(int adminId, String batch, String section, String semester) {
		return adminDao.isClassAvailable(adminId,batch,section,semester);
	}

	@Override
	@Transactional
	public void addStudentClass(StudentClass theClass) {
		adminDao.addStudentClass(theClass);
	}

	@Override
	@Transactional
	public StudentClass getSingleClass(String id, String adminId) {
		return adminDao.getSingleClass(id,adminId);
	}

	@Override
	@Transactional
	public void deleteClass(StudentClass singleClass) {
		adminDao.deleteClass(singleClass);
	}

	@Override
	@Transactional
	public List<Staff> getAllStaffs(String adminId) {
		return adminDao.getAllStaffs(adminId);
	}

	@Override
	@Transactional
	public void createStaff(Staff staff) {
		adminDao.createStaff(staff);
	}

	@Override
	@Transactional
	public Staff getSingleStaff(String adminId, String id) {
		return adminDao.getSingleStaff(adminId,id);
	}

	@Override
	@Transactional
	public void deleteStaff(Staff staff) {
		adminDao.deleteStaff(staff);
	}

	@Override
	@Transactional
	public boolean isSubjectAvailable(String batch,String semester,int adminId, String subjectCode) {
		return adminDao.isSubjectAvailable(batch,semester,adminId,subjectCode) ;
	}

	@Override
	@Transactional
	public void addSubject(DepartmentSubject theSubject) {
		adminDao.addSubject(theSubject);
	}

	@Override
	@Transactional
	public List<DepartmentSubject> getSubjects(String adminId) {
		return adminDao.getSubjects(adminId);
	}

	@Override
	@Transactional
	public List<SetExam> getExamSets(String adminId, String id) {
		return adminDao.getExamSets(adminId,id);
	}

	@Override
	@Transactional
	public void addExamSet(SetExam examset) {
		adminDao.addExamSet(examset);
	}

	@Override
	@Transactional
	public SetExam getSingleExamSet(int setId) {
		return adminDao.getSingleExamSet(setId);
	}

	@Override
	@Transactional
	public void deleteExamSet(SetExam singleExamSet) {
		adminDao.deleteExamSet(singleExamSet);
	}

	@Override
	@Transactional
	public DepartmentSubject getSingleSubject(int subjectId) {
		return adminDao.getSingleSubject(subjectId);
	}

	@Override
	@Transactional
	public void deleteSubject(DepartmentSubject singleSubject) {
		adminDao.deleteSubject(singleSubject);
	}

	@Override
	@Transactional
	public List<Tutor> getTutors(String adminId) {
		return adminDao.getTutors(adminId);
	}

	@Override
	@Transactional
	public void createTutor(Tutor theTutor) {
		adminDao.createTutor(theTutor);
	}

	@Override
	@Transactional
	public Tutor getSingleTutor(String id) {
		return adminDao.getSingleTutor(id);
	}

	@Override
	@Transactional
	public void deleteTutor(Tutor tutor) {
		adminDao.deleteTutor(tutor);
	}

	@Override
	@Transactional
	public List<SubjectAlloc> getAllSubjectAlloc(String adminId) {
		return adminDao.getAllSubjectAlloc(adminId);
	}

	@Override
	@Transactional
	public void createSubjectAllocation(SubjectAlloc subjectAlloc) {
		adminDao.createSubjectAllocation(subjectAlloc);
	}

	@Override
	@Transactional
	public SubjectAlloc getSingleSubjectAlloc(String id) {
		return adminDao.getSingleSubjectAlloc(id);
	}

	@Override
	@Transactional
	public void deleteSubjectAlloc(SubjectAlloc subjectAlloc) {
		adminDao.deleteSubjectAlloc(subjectAlloc);
	}

	@Override
	@Transactional
	public List<DepartmentFeedbackSet> getDepartmentFeedSets(String collegeId, String adminId) {
		return adminDao.getDepartmentFeedSets(collegeId,adminId);
	}

	@Override
	@Transactional
	public void saveDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet) {
		adminDao.saveDepartmentFeedbackSet(departmentFeedbackSet);
	}

	@Override
	@Transactional
	public List<DepartmentFeed> getDepartmentFeeds(String departmentFeedSetId, String adminId) {
		return adminDao.getDepartmentFeeds(departmentFeedSetId,adminId);
	}

	@Override
	@Transactional
	public void saveDepartmentFeed(DepartmentFeed departmentFeed) {
		adminDao.saveDepartmentFeed(departmentFeed);
	}

	@Override
	@Transactional
	public void deleteDepartmentFeedSet(String departmentFeedSetId) {
		adminDao.deleteDepartmentFeedSet(departmentFeedSetId);
	}

	@Override
	@Transactional
	public void deleteDepartmentFeed(String departmentFeedId) {
		adminDao.deleteDepartmentFeed(departmentFeedId);
	}

	@Override
	@Transactional
	public List<Student> getStudents(String adminId, String batch, String semester, String department, String section) {
		return adminDao.getStudents(adminId,batch,semester,department,section);
	}

	@Override
	@Transactional
	public List<DepartmentFeedbackResult> getDepartmentFeedbackResults(String feedSetId, String adminId) {
		return adminDao.getDepartmentFeedbackResults(feedSetId,adminId);
	}

	@Override
	@Transactional
	public float getDepartmentFeedPercentage(String adminId, String feedSetId, String result) {
		return adminDao.getDepartmentFeedPercentage(adminId,feedSetId,result);
	}

	@Override
	@Transactional
	public List<DepartmentFeedbackResult> getEachDepartmentFeedbackResults(String feedSetId, String adminId,
			String feed) {
		return adminDao.getEachDepartmentFeedbackResults(feedSetId,adminId,feed);
	}

	@Override
	@Transactional
	public float getEachDepartmentFeedPercentage(String adminId, String feedSetId, String result, String feed) {
		return adminDao.getEachDepartmentFeedPercentage(adminId,feedSetId,result,feed);
	}

}
