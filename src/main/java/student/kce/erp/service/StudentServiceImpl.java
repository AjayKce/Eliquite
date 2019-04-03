package student.kce.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.kce.erp.dao.StudentDao;
import student.kce.erp.model.CollegeFeed;
import student.kce.erp.model.CreateFeedback;
import student.kce.erp.model.DepartmentFeed;
import student.kce.erp.model.DepartmentFeedSetList;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.DepartmentFeedbackSet;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.FeedBackSet;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.ResourceGroup;
import student.kce.erp.model.Student;
import student.kce.erp.model.SubjectAlloc;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDao studentDao;

	@Override
	@Transactional
	public Student getStudent(String collegeId, String department, String semester, String username, String password) {
		return studentDao.getStudent(collegeId,department,semester,username,password);
	}

	@Override
	@Transactional
	public List<DepartmentSubject> getDepartmentSubjects(String adminId, String batch, String semester,
			String department) {
		return studentDao.getDepartmentSubjects(adminId,batch,semester,department);
	}

	@Override
	@Transactional
	public List<Enrollment> getEnrollments(String studentId) {
		return studentDao.getEnrollments(studentId);
	}

	@Override
	@Transactional
	public boolean isCourseEnrolled(String studentId, String subjectId) {
		return studentDao.isCourseEnrolled(studentId,subjectId);
	}

	@Override
	@Transactional
	public void createEnrollment(Enrollment enrollment) {
		studentDao.createEnrollment(enrollment);
	}

	@Override
	@Transactional
	public String isEnrollmentActive(String adminId, String batch, String semester, String department, String section) {
		return studentDao.isEnrollmentActive(adminId,batch,semester,department,section);
	}

	@Override
	@Transactional
	public List<SubjectAlloc> getFeedRequirement(String adminId, String batch, String semester, String department,
			String section, String studentId, String studentRoll) {
		return studentDao.getFeedRequirement(adminId,batch,semester,department,section,studentId,studentRoll);
	}

	@Override
	@Transactional
	public String getFeedSetId(String collegeId, String department) {
		return studentDao.getFeedSetId(collegeId,department);
	}

	@Override
	@Transactional
	public List<CollegeFeed> getFullCollegeFeeds(String feedsetId) {
		return studentDao.getFullCollegeFeeds(feedsetId);
	}

	@Override
	@Transactional
	public String getSubjectTitle(String adminId, String subjectCode) {
		return studentDao.getSubjectTitle(adminId,subjectCode);
	}

	@Override
	@Transactional
	public FeedBackSet getFeedBackSet(String feedsetId) {
		return studentDao.getFeedBackSet(feedsetId);
	}

	@Override
	@Transactional
	public String getStaffId(String adminId, String department, String staffName) {
		return studentDao.getStaffId(adminId,department,staffName);
	}

	@Override
	@Transactional
	public String getSubjectId(String adminId, String batch, String semester, String department, String subjectCode,
			String subjectTitle) {
		return studentDao.getSubjectId(adminId,batch,semester,department,subjectCode,subjectTitle);
	}

	@Override
	@Transactional
	public void createCollegeFeedBack(CreateFeedback createFeedBack) {
		studentDao.createCollegeFeedBack(createFeedBack);
	}

	@Override
	@Transactional
	public List<DepartmentFeedbackSet> getDepartmentFeedbackSets(String collegeId, String adminId, String department) {
		return studentDao.getDepartmentFeedbackSets(collegeId,adminId,department);
	}

	@Override
	@Transactional
	public List<DepartmentFeedbackResult> getDepartmentFeedbackResults(String feedSetId, String studentId) {
		return studentDao.getDepartmentFeedbackResults(feedSetId,studentId) ;
	}

	@Override
	@Transactional
	public List<DepartmentFeed> getDepartmentFeeds(String feedSetId) {
		return studentDao.getDepartmentFeeds(feedSetId);
	}

	@Override
	@Transactional
	public void saveDepartmentfeedBack(DepartmentFeedbackResult departmentFeedbackResult) {
		studentDao.saveDepartmentfeedBack(departmentFeedbackResult);
	}

	@Override
	@Transactional
	public List<ResourceGroup> getGroupsets(String studentId) {
		return studentDao.getGroupsets(studentId);
	}

	@Override
	@Transactional
	public List<ResourceGroup> isGroupCodeAvailable(String groupCode) {
		return studentDao.isGroupCodeAvailable(groupCode);
	}

	@Override
	@Transactional
	public void createStudentResourceGroup(ResourceGroup groupset) {
		studentDao.createStudentResourceGroup(groupset);
	}

	@Override
	@Transactional
	public void deleteGroup(int groupId) {
		studentDao.deleteGroup(groupId);
	}

	@Override
	@Transactional
	public List<GroupAttachment> getStudentAttachments(String studentId, String groupCode) {
		return studentDao.getStudentAttachments(studentId,groupCode);
	}

	@Override
	@Transactional
	public GroupAttachment getFile(String id) {
		return studentDao.getFile(id);
	}

	@Override
	@Transactional
	public void deleteStudentResourceFile(String id) {
		studentDao.deleteStudentResourceFile(id);	
	}

	@Override
	@Transactional
	public ResourceGroup getSingleGroupsets(String studentId, int id) {
		return studentDao.getSingleGroupsets(studentId,id);
	}

}
