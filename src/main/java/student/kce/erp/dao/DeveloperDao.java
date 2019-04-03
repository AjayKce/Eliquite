package student.kce.erp.dao;

import java.util.List;

import student.kce.erp.model.Admin;
import student.kce.erp.model.Attachment;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.Batch;
import student.kce.erp.model.College;
import student.kce.erp.model.CollegeFeed;
import student.kce.erp.model.CreateFeedback;
import student.kce.erp.model.DepartmentFeed;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.DepartmentFeedbackSet;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.FeedBackAlloc;
import student.kce.erp.model.FeedBackSet;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.ResourceGroup;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentClass;
import student.kce.erp.model.StudentMark;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.SuperUser;
import student.kce.erp.model.Tutor;

public interface DeveloperDao {

	List<Attachment> getAttachment();

	Attachment getAttachment(int id);

	void saveAttachment(Attachment attachment);

	void deleteAttachment(Attachment attachment);

	List<AttendanceResult> getAttendanceResult();

	AttendanceResult getAttendanceResult(int id);

	void saveAttendanceResult(AttendanceResult attendanceResult);

	void deleteAttendanceResult(AttendanceResult attendanceResult);

	List<AttendanceScheduler> getAttendanceScheduler();

	AttendanceScheduler getAttendanceScheduler(int id);

	void saveAttendanceScheduler(AttendanceScheduler attendanceScheduler);

	void deleteAttendanceScheduler(AttendanceScheduler attendanceScheduler);

	List<Batch> getBatch();

	Batch getBatch(int id);

	void saveBatch(Batch batch);

	void deleteBatch(Batch batch);

	List<StudentClass> getStudentClass();

	StudentClass getStudentClass(int id);

	void saveStudentClass(StudentClass studentClass);

	void deleteStudentClass(StudentClass studentClass);

	List<College> getCollege();

	College getCollege(int id);

	void saveCollege(College college);

	void deleteCollege(College college);

	List<Admin> getCollegeAdmin();

	Admin getCollegeAdmin(int id);

	void saveCollegeAdmin(Admin admin);

	void deleteCollegeAdmin(Admin admin);

	List<CollegeFeed> getCollegeFeed();

	CollegeFeed getCollegeFeed(int id);

	void saveCollegeFeed(CollegeFeed collegeFeed);

	void deleteCollegeFeed(CollegeFeed collegeFeed);

	List<CreateFeedback> getCreateFeedback();

	CreateFeedback getCreateFeedback(int id);

	void saveCreateFeedback(CreateFeedback createFeedback);

	void deleteCreateFeedback(CreateFeedback createFeedback);

	List<DepartmentFeed> getDepartmentFeed();

	DepartmentFeed getDepartmentFeed(int id);

	void saveDepartmentFeed(DepartmentFeed departmentFeed);

	void deleteDepartmentFeed(DepartmentFeed departmentFeed);

	List<DepartmentFeedbackSet> getDepartmentFeedbackSet();

	DepartmentFeedbackSet getDepartmentFeedbackSet(int id);

	void saveDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet);

	void deleteDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet);

	List<DepartmentFeedbackResult> getDepartmentFeedbackResult();

	DepartmentFeedbackResult getDepartmentFeedbackResult(int id);

	void saveDepartmentFeedbackResult(DepartmentFeedbackResult departmentFeedbackResult);

	void deleteDepartmentFeedbackResult(DepartmentFeedbackResult departmentFeedbackResult);

	List<Enrollment> getEnrollment();

	Enrollment getEnrollment(int id);

	void saveEnrollment(Enrollment enrollment);

	void deleteEnrollment(Enrollment enrollment);

	List<FeedBackAlloc> getFeedBackAlloc();

	FeedBackAlloc getFeedBackAlloc(int id);

	void saveFeedBackAlloc(FeedBackAlloc feedBackAlloc);

	void deleteFeedBackAlloc(FeedBackAlloc feedBackAlloc);

	List<FeedBackSet> getFeedBackSet();

	FeedBackSet getFeedBackSet(int id);

	void saveFeedBackSet(FeedBackSet feedBackSet);

	void deleteFeedBackSet(FeedBackSet feedBackSet);

	List<GroupAttachment> getGroupAttachment();

	GroupAttachment getGroupAttachment(int id);

	void saveGroupAttachment(GroupAttachment groupAttachment);

	void deleteGroupAttachment(GroupAttachment groupAttachment);

	List<SetExam> getSetExam();

	SetExam getSetExam(int id);

	void saveSetExam(SetExam setExam);

	void deleteSetExam(SetExam setExam);

	List<ResourceGroup> getResourceGroup();

	ResourceGroup getResourceGroup(int id);

	void saveResourceGroup(ResourceGroup resourceGroup);

	void deleteResourceGroup(ResourceGroup resourceGroup);

	List<Staff> getStaff();

	Staff getStaff(int id);

	void saveStaff(Staff staff);

	void deleteStaff(Staff staff);

	List<Student> getStudent();

	Student getStudent(int id);

	void saveStudent(Student student);

	void deleteStudent(Student student);

	List<StudentMark> getStudentMark();

	StudentMark getStudentMark(int id);

	void saveStudentMark(StudentMark studentMark);

	void deleteStudentMark(StudentMark studentMark);

	List<DepartmentSubject> getDepartmentSubject();

	DepartmentSubject getDepartmentSubject(int id);

	void saveDepartmentSubject(DepartmentSubject departmentSubject);

	void deleteDepartmentSubject(DepartmentSubject departmentSubject);

	List<SubjectAlloc> getSubjectAlloc();

	SubjectAlloc getSubjectAlloc(int id);

	void saveSubjectAlloc(SubjectAlloc subjectAlloc);

	void deleteSubjectAlloc(SubjectAlloc subjectAlloc);

	List<SuperUser> getSuperUser();

	SuperUser getSuperUser(int id);

	void saveSuperUser(SuperUser superUser);

	void deleteSuperUser(SuperUser superUser);

	List<Tutor> getTutor();

	Tutor getTutor(int id);

	void saveTutor(Tutor tutor);

	void deleteTutor(Tutor tutor);



}
