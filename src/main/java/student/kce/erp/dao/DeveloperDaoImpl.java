package student.kce.erp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

@Repository
public class DeveloperDaoImpl implements DeveloperDao {

@Autowired
SessionFactory factory;

@Override
public List<Attachment> getAttachment() {
	Session session  = factory.getCurrentSession();
	List<Attachment> attachments = session.createQuery("from Attachment").list();
	return attachments;
}

@Override
public Attachment getAttachment(int id) {
	Session session  = factory.getCurrentSession();
	Attachment attachment = session.get(Attachment.class, id);
	return attachment;
}

@Override
public void saveAttachment(Attachment attachment) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(attachment);
}

@Override
public void deleteAttachment(Attachment attachment) {
	Session session  = factory.getCurrentSession();
	session.delete(attachment);
}

@Override
public List<AttendanceResult> getAttendanceResult() {
	Session session  = factory.getCurrentSession();
	List<AttendanceResult> attendanceResults = session.createQuery("from AttendanceResult").list();
	return attendanceResults;
}

@Override
public AttendanceResult getAttendanceResult(int id) {
	Session session  = factory.getCurrentSession();
	AttendanceResult attendanceResult = session.get(AttendanceResult.class, id);
	return attendanceResult;
}

@Override
public void saveAttendanceResult(AttendanceResult attendanceResult) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(attendanceResult);
	
}

@Override
public void deleteAttendanceResult(AttendanceResult attendanceResult) {
	Session session  = factory.getCurrentSession();
	session.delete(attendanceResult);
}

@Override
public List<AttendanceScheduler> getAttendanceScheduler() {
	Session session  = factory.getCurrentSession();
	List<AttendanceScheduler> attendanceSchedulers = session.createQuery("from AttendanceScheduler").list();
	return attendanceSchedulers;
}

@Override
public AttendanceScheduler getAttendanceScheduler(int id) {
	Session session  = factory.getCurrentSession();
	AttendanceScheduler attendanceScheduler = session.get(AttendanceScheduler.class, id);
	return attendanceScheduler;
}

@Override
public void saveAttendanceScheduler(AttendanceScheduler attendanceScheduler) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(attendanceScheduler);
	
}

@Override
public void deleteAttendanceScheduler(AttendanceScheduler attendanceScheduler) {
	Session session  = factory.getCurrentSession();
	session.delete(attendanceScheduler);
}

@Override
public List<Batch> getBatch() {
	Session session  = factory.getCurrentSession();
	List<Batch> batches = session.createQuery("from Batch").list();
	return batches;
}

@Override
public Batch getBatch(int id) {
	Session session  = factory.getCurrentSession();
	Batch batch = session.get(Batch.class, id);
	return batch;
}

@Override
public void saveBatch(Batch batch) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(batch);
}

@Override
public void deleteBatch(Batch batch) {
	Session session  = factory.getCurrentSession();
	session.delete(batch);
}

@Override
public List<StudentClass> getStudentClass() {
	Session session  = factory.getCurrentSession();
	List<StudentClass> studentClasses = session.createQuery("from StudentClass").list();
	return studentClasses;
}

@Override
public StudentClass getStudentClass(int id) {
	Session session  = factory.getCurrentSession();
	StudentClass studentClass = session.get(StudentClass.class, id);
	return studentClass;
}

@Override
public void saveStudentClass(StudentClass studentClass) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(studentClass);
}

@Override
public void deleteStudentClass(StudentClass studentClass) {
	Session session  = factory.getCurrentSession();
	session.delete(studentClass);
}

@Override
public List<College> getCollege() {
	Session session  = factory.getCurrentSession();
	List<College> college = session.createQuery("from College").list();
	return college;
}

@Override
public College getCollege(int id) {
	Session session  = factory.getCurrentSession();
	College college = session.get(College.class, id);
	return college;
}

@Override
public void saveCollege(College college) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(college);
}

@Override
public void deleteCollege(College college) {
	Session session  = factory.getCurrentSession();
	session.delete(college);
}

@Override
public List<Admin> getCollegeAdmin() {
	Session session  = factory.getCurrentSession();
	List<Admin> admins = session.createQuery("from Admin").list();
	return admins;
}

@Override
public Admin getCollegeAdmin(int id) {
	Session session  = factory.getCurrentSession();
	Admin admin = session.get(Admin.class, id);
	return admin;
}

@Override
public void saveCollegeAdmin(Admin admin) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(admin);
}

@Override
public void deleteCollegeAdmin(Admin admin) {
	Session session  = factory.getCurrentSession();
	session.delete(admin);
}

@Override
public List<CollegeFeed> getCollegeFeed() {
	Session session  = factory.getCurrentSession();
	List<CollegeFeed> collegeFeeds = session.createQuery("from CollegeFeed").list();
	return collegeFeeds;
}

@Override
public CollegeFeed getCollegeFeed(int id) {
	Session session  = factory.getCurrentSession();
	CollegeFeed collegeFeed = session.get(CollegeFeed.class, id);
	return collegeFeed;
}

@Override
public void saveCollegeFeed(CollegeFeed collegeFeed) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(collegeFeed);
}

@Override
public void deleteCollegeFeed(CollegeFeed collegeFeed) {
	Session session  = factory.getCurrentSession();
	session.delete(collegeFeed);
}

@Override
public List<CreateFeedback> getCreateFeedback() {
	Session session  = factory.getCurrentSession();
	List<CreateFeedback> createFeedbacks = session.createQuery("from CreateFeedback").list();
	return createFeedbacks;
}

@Override
public CreateFeedback getCreateFeedback(int id) {
	Session session  = factory.getCurrentSession();
	CreateFeedback createFeedback = session.get(CreateFeedback.class, id);
	return createFeedback;
}

@Override
public void saveCreateFeedback(CreateFeedback createFeedback) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(createFeedback);
}

@Override
public void deleteCreateFeedback(CreateFeedback createFeedback) {
	Session session  = factory.getCurrentSession();
	session.delete(createFeedback);
}

@Override
public List<DepartmentFeed> getDepartmentFeed() {
	Session session  = factory.getCurrentSession();
	List<DepartmentFeed> departmentFeedbacks = session.createQuery("from DepartmentFeed").list();
	return departmentFeedbacks;
}

@Override
public DepartmentFeed getDepartmentFeed(int id) {
	Session session  = factory.getCurrentSession();
	DepartmentFeed departmentFeedback = session.get(DepartmentFeed.class, id);
	return departmentFeedback;
}

@Override
public void saveDepartmentFeed(DepartmentFeed departmentFeed) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(departmentFeed);
}

@Override
public void deleteDepartmentFeed(DepartmentFeed departmentFeed) {
	Session session  = factory.getCurrentSession();
	session.delete(departmentFeed);
}

@Override
public List<DepartmentFeedbackSet> getDepartmentFeedbackSet() {
	Session session  = factory.getCurrentSession();
	List<DepartmentFeedbackSet> departmentFeedbackSets = session.createQuery("from DepartmentFeedbackSet").list();
	return departmentFeedbackSets;
}

@Override
public DepartmentFeedbackSet getDepartmentFeedbackSet(int id) {
	Session session  = factory.getCurrentSession();
	DepartmentFeedbackSet departmentFeedbackSet = session.get(DepartmentFeedbackSet.class, id);
	return departmentFeedbackSet;
}

@Override
public void saveDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(departmentFeedbackSet);
}

@Override
public void deleteDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet) {
	Session session  = factory.getCurrentSession();
	session.delete(departmentFeedbackSet);
}

@Override
public List<DepartmentFeedbackResult> getDepartmentFeedbackResult() {
	Session session  = factory.getCurrentSession();
	List<DepartmentFeedbackResult> departmentFeedbackResults = session.createQuery("from DepartmentFeedbackResult").list();
	return departmentFeedbackResults;
}

@Override
public DepartmentFeedbackResult getDepartmentFeedbackResult(int id) {
	Session session  = factory.getCurrentSession();
	DepartmentFeedbackResult departmentFeedbackResult = session.get(DepartmentFeedbackResult.class, id);
	return departmentFeedbackResult;
}

@Override
public void saveDepartmentFeedbackResult(DepartmentFeedbackResult departmentFeedbackResult) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(departmentFeedbackResult);
}

@Override
public void deleteDepartmentFeedbackResult(DepartmentFeedbackResult departmentFeedbackResult) {
	Session session  = factory.getCurrentSession();
	session.delete(departmentFeedbackResult);
}

@Override
public List<Enrollment> getEnrollment() {
	Session session  = factory.getCurrentSession();
	List<Enrollment> enrollments = session.createQuery("from Enrollment").list();
	return enrollments;
}

@Override
public Enrollment getEnrollment(int id) {
	Session session  = factory.getCurrentSession();
	Enrollment enrollment = session.get(Enrollment.class, id);
	return enrollment;
}

@Override
public void saveEnrollment(Enrollment enrollment) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(enrollment);
}

@Override
public void deleteEnrollment(Enrollment enrollment) {
	Session session  = factory.getCurrentSession();
	session.delete(enrollment);
}

@Override
public List<FeedBackAlloc> getFeedBackAlloc() {
	Session session  = factory.getCurrentSession();
	List<FeedBackAlloc> feedBackAllocs = session.createQuery("from FeedBackAlloc").list();
	return feedBackAllocs;
}

@Override
public FeedBackAlloc getFeedBackAlloc(int id) {
	Session session  = factory.getCurrentSession();
	FeedBackAlloc feedBackAlloc = session.get(FeedBackAlloc.class, id);
	return feedBackAlloc;
}

@Override
public void saveFeedBackAlloc(FeedBackAlloc feedBackAlloc) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(feedBackAlloc);
}

@Override
public void deleteFeedBackAlloc(FeedBackAlloc feedBackAlloc) {
	Session session  = factory.getCurrentSession();
	session.delete(feedBackAlloc);
}

@Override
public List<FeedBackSet> getFeedBackSet() {
	Session session  = factory.getCurrentSession();
	List<FeedBackSet> feedBackSets = session.createQuery("from FeedBackSet").list();
	return feedBackSets;
}

@Override
public FeedBackSet getFeedBackSet(int id) {
	Session session  = factory.getCurrentSession();
	FeedBackSet feedBackSet = session.get(FeedBackSet.class, id);
	return feedBackSet;
}

@Override
public void saveFeedBackSet(FeedBackSet feedBackSet) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(feedBackSet);
}

@Override
public void deleteFeedBackSet(FeedBackSet feedBackSet) {
	Session session  = factory.getCurrentSession();
	session.delete(feedBackSet);
}

@Override
public List<GroupAttachment> getGroupAttachment() {
	Session session  = factory.getCurrentSession();
	List<GroupAttachment> groupAttachments = session.createQuery("from GroupAttachment").list();
	return groupAttachments;
}

@Override
public GroupAttachment getGroupAttachment(int id) {
	Session session  = factory.getCurrentSession();
	GroupAttachment groupAttachment = session.get(GroupAttachment.class, id);
	return groupAttachment;
}

@Override
public void saveGroupAttachment(GroupAttachment groupAttachment) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(groupAttachment);
}

@Override
public void deleteGroupAttachment(GroupAttachment groupAttachment) {
	Session session  = factory.getCurrentSession();
	session.delete(groupAttachment);
}

@Override
public List<SetExam> getSetExam() {
	Session session  = factory.getCurrentSession();
	List<SetExam> setExams = session.createQuery("from SetExam").list();
	return setExams;
}

@Override
public SetExam getSetExam(int id) {
	Session session  = factory.getCurrentSession();
	SetExam setExam = session.get(SetExam.class, id);
	return setExam;
}

@Override
public void saveSetExam(SetExam setExam) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(setExam);
}

@Override
public void deleteSetExam(SetExam setExam) {
	Session session  = factory.getCurrentSession();
	session.delete(setExam);
}

@Override
public List<ResourceGroup> getResourceGroup() {
	Session session  = factory.getCurrentSession();
	List<ResourceGroup> resourceGroups = session.createQuery("from ResourceGroup").list();
	return resourceGroups;
}

@Override
public ResourceGroup getResourceGroup(int id) {
	Session session  = factory.getCurrentSession();
	ResourceGroup resourceGroup = session.get(ResourceGroup.class, id);
	return resourceGroup;
}

@Override
public void saveResourceGroup(ResourceGroup resourceGroup) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(resourceGroup);
}

@Override
public void deleteResourceGroup(ResourceGroup resourceGroup) {
	Session session  = factory.getCurrentSession();
	session.delete(resourceGroup);
}

@Override
public List<Staff> getStaff() {
	Session session  = factory.getCurrentSession();
	List<Staff> staffs = session.createQuery("from Staff").list();
	return staffs;
}

@Override
public Staff getStaff(int id) {
	Session session  = factory.getCurrentSession();
	Staff staff = session.get(Staff.class, id);
	return staff;
}

@Override
public void saveStaff(Staff staff) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(staff);
}

@Override
public void deleteStaff(Staff staff) {
	Session session  = factory.getCurrentSession();
	session.delete(staff);
}

@Override
public List<Student> getStudent() {
	Session session  = factory.getCurrentSession();
	List<Student> students = session.createQuery("from Student").list();
	return students;
}

@Override
public Student getStudent(int id) {
	Session session  = factory.getCurrentSession();
	Student student = session.get(Student.class, id);
	return student;
}

@Override
public void saveStudent(Student student) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(student);
}

@Override
public void deleteStudent(Student student) {
	Session session  = factory.getCurrentSession();
	session.delete(student);
}

@Override
public List<StudentMark> getStudentMark() {
	Session session  = factory.getCurrentSession();
	List<StudentMark> studentMarks = session.createQuery("from StudentMark").list();
	return studentMarks;
}

@Override
public StudentMark getStudentMark(int id) {
	Session session  = factory.getCurrentSession();
	StudentMark studentMark = session.get(StudentMark.class, id);
	return studentMark;
}

@Override
public void saveStudentMark(StudentMark studentMark) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(studentMark);
}

@Override
public void deleteStudentMark(StudentMark studentMark) {
	Session session  = factory.getCurrentSession();
	session.delete(studentMark);
}

@Override
public List<DepartmentSubject> getDepartmentSubject() {
	Session session  = factory.getCurrentSession();
	List<DepartmentSubject> departmentSubjects = session.createQuery("from DepartmentSubject").list();
	return departmentSubjects;
}

@Override
public DepartmentSubject getDepartmentSubject(int id) {
	Session session  = factory.getCurrentSession();
	DepartmentSubject departmentSubject = session.get(DepartmentSubject.class, id);
	return departmentSubject;
}

@Override
public void saveDepartmentSubject(DepartmentSubject departmentSubject) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(departmentSubject);
}

@Override
public void deleteDepartmentSubject(DepartmentSubject departmentSubject) {
	Session session  = factory.getCurrentSession();
	session.delete(departmentSubject);
}

@Override
public List<SubjectAlloc> getSubjectAlloc() {
	Session session  = factory.getCurrentSession();
	List<SubjectAlloc> subjectAllocs = session.createQuery("from SubjectAlloc").list();
	return subjectAllocs;
}

@Override
public SubjectAlloc getSubjectAlloc(int id) {
	Session session  = factory.getCurrentSession();
	SubjectAlloc subjectAlloc = session.get(SubjectAlloc.class, id);
	return subjectAlloc;
}

@Override
public void saveSubjectAlloc(SubjectAlloc subjectAlloc) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(subjectAlloc);
}

@Override
public void deleteSubjectAlloc(SubjectAlloc subjectAlloc) {
	Session session  = factory.getCurrentSession();
	session.delete(subjectAlloc);
}

@Override
public List<SuperUser> getSuperUser() {
	Session session  = factory.getCurrentSession();
	List<SuperUser> superUsers = session.createQuery("from SuperUser").list();
	return superUsers;
}

@Override
public SuperUser getSuperUser(int id) {
	Session session  = factory.getCurrentSession();
	SuperUser superUser = session.get(SuperUser.class, id);
	return superUser;
}

@Override
public void saveSuperUser(SuperUser superUser) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(superUser);
}

@Override
public void deleteSuperUser(SuperUser superUser) {
	Session session  = factory.getCurrentSession();
	session.delete(superUser);
}

@Override
public List<Tutor> getTutor() {
	Session session  = factory.getCurrentSession();
	List<Tutor> tutors = session.createQuery("from Tutor").list();
	return tutors;
}

@Override
public Tutor getTutor(int id) {
	Session session  = factory.getCurrentSession();
	Tutor tutor = session.get(Tutor.class, id);
	return tutor;
}

@Override
public void saveTutor(Tutor tutor) {
	Session session  = factory.getCurrentSession();
	session.saveOrUpdate(tutor);
}

@Override
public void deleteTutor(Tutor tutor) {
	Session session  = factory.getCurrentSession();
	session.delete(tutor);
}
	
}
