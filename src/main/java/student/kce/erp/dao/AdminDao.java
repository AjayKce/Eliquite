package student.kce.erp.dao;

import java.util.List;

import javax.security.auth.Subject;

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

public interface AdminDao {

	boolean isAdmin(String collegeId, String user, String pass, String dept);

	Admin getAdmin(String collegeId, String user, String pass, String dept);

	List<Batch> getBatches(String adminId);

	void addBatch(Batch theBatch);

	Batch getBatch(String id,String adminId);

	void deleteBatch(Batch batch);

	List<StudentClass> getDepartmentClasses(String adminId);

	boolean isClassAvailable(int adminId, String batch, String section, String semester);

	void addStudentClass(StudentClass theClass);

	StudentClass getSingleClass(String id, String adminId);

	void deleteClass(StudentClass singleClass);

	List<Staff> getAllStaffs(String adminId);

	void createStaff(Staff staff);

	Staff getSingleStaff(String adminId, String id);

	void deleteStaff(Staff staff);

	boolean isSubjectAvailable(String batch,String semester,int adminId, String subjectCode);

	void addSubject(DepartmentSubject theSubject);

	List<DepartmentSubject> getSubjects(String adminId);

	List<SetExam> getExamSets(String adminId, String id);

	void addExamSet(SetExam examset);

	SetExam getSingleExamSet(int setId);

	void deleteExamSet(SetExam singleExamSet);

	DepartmentSubject getSingleSubject(int subjectId);

	void deleteSubject(DepartmentSubject singleSubject);

	List<Tutor> getTutors(String adminId);

	void createTutor(Tutor theTutor);

	Tutor getSingleTutor(String id);

	void deleteTutor(Tutor tutor);

	List<SubjectAlloc> getAllSubjectAlloc(String adminId);

	void createSubjectAllocation(SubjectAlloc subjectAlloc);

	SubjectAlloc getSingleSubjectAlloc(String id);

	void deleteSubjectAlloc(SubjectAlloc subjectAlloc);

	List<DepartmentFeedbackSet> getDepartmentFeedSets(String collegeId, String adminId);

	void saveDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet);

	List<DepartmentFeed> getDepartmentFeeds(String departmentFeedSetId, String adminId);

	void saveDepartmentFeed(DepartmentFeed departmentFeed);

	void deleteDepartmentFeedSet(String departmentFeedSetId);

	void deleteDepartmentFeed(String departmentFeedId);

	List<Student> getStudents(String adminId, String batch, String semester, String department, String section);

	List<DepartmentFeedbackResult> getDepartmentFeedbackResults(String feedSetId, String adminId);

	float getDepartmentFeedPercentage(String adminId, String feedSetId, String result);

	List<DepartmentFeedbackResult> getEachDepartmentFeedbackResults(String feedSetId, String adminId, String feed);

	float getEachDepartmentFeedPercentage(String adminId, String feedSetId, String result, String feed);

}
