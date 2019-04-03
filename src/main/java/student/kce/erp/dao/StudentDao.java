package student.kce.erp.dao;

import java.util.List;

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

public interface StudentDao {

	Student getStudent(String collegeId, String department, String semester, String username, String password);

	List<DepartmentSubject> getDepartmentSubjects(String adminId, String batch, String semester, String department);

	List<Enrollment> getEnrollments(String studentId);

	boolean isCourseEnrolled(String studentId, String subjectId);

	void createEnrollment(Enrollment enrollment);

	String isEnrollmentActive(String adminId, String batch, String semester, String department, String section);

	List<SubjectAlloc> getFeedRequirement(String adminId, String batch, String semester, String department,
			String section, String studentId, String studentRoll);

	String getFeedSetId(String collegeId, String department);

	List<CollegeFeed> getFullCollegeFeeds(String feedsetId);

	String getSubjectTitle(String adminId, String subjectCode);

	FeedBackSet getFeedBackSet(String feedsetId);

	String getStaffId(String adminId, String department, String staffName);

	String getSubjectId(String adminId, String batch, String semester, String department, String subjectCode,
			String subjectTitle);

	void createCollegeFeedBack(CreateFeedback createFeedBack);

	List<DepartmentFeedbackSet> getDepartmentFeedbackSets(String collegeId, String adminId, String department);

	List<DepartmentFeedbackResult> getDepartmentFeedbackResults(String feedSetId, String studentId);

	List<DepartmentFeed> getDepartmentFeeds(String feedSetId);

	void saveDepartmentfeedBack(DepartmentFeedbackResult departmentFeedbackResult);

	List<ResourceGroup> getGroupsets(String studentId);

	List<ResourceGroup> isGroupCodeAvailable(String groupCode);

	void createStudentResourceGroup(ResourceGroup groupset);

	void deleteGroup(int groupId);

	List<GroupAttachment> getStudentAttachments(String studentId, String groupCode);

	GroupAttachment getFile(String id);

	void deleteStudentResourceFile(String id);

	ResourceGroup getSingleGroupsets(String studentId, int id);

}
