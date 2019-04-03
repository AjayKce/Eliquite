package student.kce.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.kce.erp.dao.DeveloperDao;
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

@Service
public class DeveloperServiceImpl implements DeveloperService {

@Autowired
DeveloperDao developerDao;

@Override
@Transactional
public List<Attachment> getAttachment() {
	return developerDao.getAttachment();
}

@Override
@Transactional
public Attachment getAttachment(int id) {
	return developerDao.getAttachment(id);
}

@Override
@Transactional
public void saveAttachment(Attachment attachment) {
	developerDao.saveAttachment(attachment);
}

@Override
@Transactional
public void deleteAttachment(Attachment attachment) {
	developerDao.deleteAttachment(attachment);
}

@Override
@Transactional
public List<AttendanceResult> getAttendanceResult() {
	return developerDao.getAttendanceResult();
}

@Override
@Transactional
public AttendanceResult getAttendanceResult(int id) {
	return developerDao.getAttendanceResult(id);
}

@Override
@Transactional
public void saveAttendanceResult(AttendanceResult attendanceResult) {
	developerDao.saveAttendanceResult(attendanceResult);
}

@Override
@Transactional
public void deleteAttendanceResult(AttendanceResult attendanceResult) {
	developerDao.deleteAttendanceResult(attendanceResult);
}

@Override
@Transactional
public List<AttendanceScheduler> getAttendanceScheduler() {
	return developerDao.getAttendanceScheduler();
}

@Override
@Transactional
public AttendanceScheduler getAttendanceScheduler(int id) {
	return developerDao.getAttendanceScheduler(id);
}

@Override
@Transactional
public void saveAttendanceScheduler(AttendanceScheduler attendanceScheduler) {
	developerDao.saveAttendanceScheduler(attendanceScheduler);	
}

@Override
@Transactional
public void deleteAttendanceScheduler(AttendanceScheduler attendanceScheduler) {
	developerDao.deleteAttendanceScheduler(attendanceScheduler);
}

@Override
@Transactional
public List<Batch> getBatch() {
	return developerDao.getBatch();
}

@Override
@Transactional
public Batch getBatch(int id) {
	return developerDao.getBatch(id);
}

@Override
@Transactional
public void saveBatch(Batch batch) {
	developerDao.saveBatch(batch);
}

@Override
@Transactional
public void deleteBatch(Batch batch) {
	developerDao.deleteBatch(batch);
}

@Override
@Transactional
public List<StudentClass> getStudentClass() {
	return developerDao.getStudentClass();
}

@Override
@Transactional
public StudentClass getStudentClass(int id) {
	return developerDao.getStudentClass(id);
}

@Override
@Transactional
public void saveStudentClass(StudentClass studentClass) {
	developerDao.saveStudentClass(studentClass);
}

@Override
@Transactional
public void deleteStudentClass(StudentClass studentClass) {
	developerDao.deleteStudentClass(studentClass);
}

@Override
@Transactional
public List<College> getCollege() {
	return developerDao.getCollege();
}

@Override
@Transactional
public College getCollege(int id) {
	return developerDao.getCollege(id);
}

@Override
@Transactional
public void saveCollege(College college) {
	developerDao.saveCollege(college);
}

@Override
@Transactional
public void deleteCollege(College college) {
	developerDao.deleteCollege(college);
}

@Override
@Transactional
public List<Admin> getCollegeAdmin() {
	return developerDao.getCollegeAdmin();
}

@Override
@Transactional
public Admin getCollegeAdmin(int id) {
	return developerDao.getCollegeAdmin(id);
}

@Override
@Transactional
public void saveCollegeAdmin(Admin admin) {
	developerDao.saveCollegeAdmin(admin);
}

@Override
@Transactional
public void deleteCollegeAdmin(Admin admin) {
	developerDao.deleteCollegeAdmin(admin);
}

@Override
@Transactional
public List<CollegeFeed> getCollegeFeed() {
	return developerDao.getCollegeFeed();
}

@Override
@Transactional
public CollegeFeed getCollegeFeed(int id) {
	return developerDao.getCollegeFeed(id);
}

@Override
@Transactional
public void saveCollegeFeed(CollegeFeed collegeFeed) {
	developerDao.saveCollegeFeed(collegeFeed);
}

@Override
@Transactional
public void deleteCollegeFeed(CollegeFeed collegeFeed) {
	developerDao.deleteCollegeFeed(collegeFeed);
}

@Override
@Transactional
public List<CreateFeedback> getCreateFeedback() {
	return developerDao.getCreateFeedback();
}

@Override
@Transactional
public CreateFeedback getCreateFeedback(int id) {
	return developerDao.getCreateFeedback(id);
}

@Override
@Transactional
public void saveCreateFeedback(CreateFeedback createFeedback) {
	developerDao.saveCreateFeedback(createFeedback);
}

@Override
@Transactional
public void deleteCreateFeedback(CreateFeedback createFeedback) {
	developerDao.deleteCreateFeedback(createFeedback);
}

@Override
@Transactional
public List<DepartmentFeed> getDepartmentFeed() {
	return developerDao. getDepartmentFeed();
}

@Override
@Transactional
public DepartmentFeed getDepartmentFeed(int id) {
	return developerDao.getDepartmentFeed(id);
}

@Override
@Transactional
public void saveDepartmentFeed(DepartmentFeed departmentFeed) {
	developerDao.saveDepartmentFeed(departmentFeed);
}

@Override
@Transactional
public void deleteDepartmentFeed(DepartmentFeed departmentFeed) {
	developerDao.deleteDepartmentFeed(departmentFeed);
}

@Override
@Transactional
public List<DepartmentFeedbackSet> getDepartmentFeedbackSet() {
	return developerDao.getDepartmentFeedbackSet();
}

@Override
@Transactional
public DepartmentFeedbackSet getDepartmentFeedbackSet(int id) {
	return developerDao.getDepartmentFeedbackSet(id);
}

@Override
@Transactional
public void saveDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet) {
	developerDao.saveDepartmentFeedbackSet(departmentFeedbackSet);
}

@Override
@Transactional
public void deleteDepartmentFeedbackSet(DepartmentFeedbackSet departmentFeedbackSet) {
	developerDao.deleteDepartmentFeedbackSet(departmentFeedbackSet);
}

@Override
@Transactional
public List<DepartmentFeedbackResult> getDepartmentFeedbackResult() {
	return developerDao.getDepartmentFeedbackResult();
}

@Override
@Transactional
public DepartmentFeedbackResult getDepartmentFeedbackResult(int id) {
	return developerDao.getDepartmentFeedbackResult(id);
}

@Override
@Transactional
public void saveDepartmentFeedbackResult(DepartmentFeedbackResult departmentFeedbackResult) {
	developerDao.saveDepartmentFeedbackResult(departmentFeedbackResult);
}

@Override
@Transactional
public void deleteDepartmentFeedbackResult(DepartmentFeedbackResult departmentFeedbackResult) {
	developerDao.deleteDepartmentFeedbackResult(departmentFeedbackResult);
}

@Override
@Transactional
public List<Enrollment> getEnrollment() {
	return developerDao.getEnrollment();
}

@Override
@Transactional
public Enrollment getEnrollment(int id) {
	return developerDao.getEnrollment(id);
}

@Override
@Transactional
public void saveEnrollment(Enrollment enrollment) {
	developerDao.saveEnrollment(enrollment);
}

@Override
@Transactional
public void deleteEnrollment(Enrollment enrollment) {
	developerDao.deleteEnrollment(enrollment);
}

@Override
@Transactional
public List<FeedBackAlloc> getFeedBackAlloc() {
	return developerDao.getFeedBackAlloc();
}

@Override
@Transactional
public FeedBackAlloc getFeedBackAlloc(int id) {
	return developerDao.getFeedBackAlloc(id);
}

@Override
@Transactional
public void saveFeedBackAlloc(FeedBackAlloc feedBackAlloc) {
	developerDao.saveFeedBackAlloc(feedBackAlloc);
}

@Override
@Transactional
public void deleteFeedBackAlloc(FeedBackAlloc feedBackAlloc) {
	developerDao.deleteFeedBackAlloc(feedBackAlloc);
}

@Override
@Transactional
public List<FeedBackSet> getFeedBackSet() {
	return developerDao.getFeedBackSet();
}

@Override
@Transactional
public void saveFeedBackSet(FeedBackSet feedBackSet) {
	developerDao.saveFeedBackSet(feedBackSet);
}

@Override
@Transactional
public FeedBackSet getFeedBackSet(int id) {
	return developerDao.getFeedBackSet(id);
}

@Override
@Transactional
public void deleteFeedBackSet(FeedBackSet feedBackSet) {
	developerDao.deleteFeedBackSet(feedBackSet);
}

@Override
@Transactional
public List<GroupAttachment> getGroupAttachment() {
	return developerDao.getGroupAttachment();
}

@Override
@Transactional
public GroupAttachment getGroupAttachment(int id) {
	return developerDao.getGroupAttachment(id);
}

@Override
@Transactional
public void saveGroupAttachment(GroupAttachment groupAttachment) {
	developerDao.saveGroupAttachment(groupAttachment);
}

@Override
@Transactional
public void deleteGroupAttachment(GroupAttachment groupAttachment) {
	developerDao.deleteGroupAttachment(groupAttachment);
}

@Override
@Transactional
public List<SetExam> getSetExam() {
	return developerDao.getSetExam();
}

@Override
@Transactional
public SetExam getSetExam(int id) {
	return developerDao.getSetExam(id);
}

@Override
@Transactional
public void saveSetExam(SetExam setExam) {
	developerDao.saveSetExam(setExam);
}

@Override
@Transactional
public void deleteSetExam(SetExam setExam) {
	developerDao.deleteSetExam(setExam);
}

@Override
@Transactional
public List<ResourceGroup> getResourceGroup() {
	return developerDao.getResourceGroup();
}

@Override
@Transactional
public ResourceGroup getResourceGroup(int id) {
	return developerDao.getResourceGroup(id);
}

@Override
@Transactional
public void saveResourceGroup(ResourceGroup resourceGroup) {
	developerDao.saveResourceGroup(resourceGroup);
}

@Override
@Transactional
public void deleteResourceGroup(ResourceGroup resourceGroup) {
	developerDao.deleteResourceGroup(resourceGroup);
}

@Override
@Transactional
public List<Staff> getStaff() {
	return developerDao.getStaff();
}

@Override
@Transactional
public Staff getStaff(int id) {
	return developerDao.getStaff(id);
}

@Override
@Transactional
public void saveStaff(Staff staff) {
	developerDao.saveStaff(staff);
}

@Override
@Transactional
public void deleteStaff(Staff staff) {
	developerDao.deleteStaff(staff);
}

@Override
@Transactional
public List<Student> getStudent() {
	return developerDao.getStudent();
}

@Override
@Transactional
public Student getStudent(int id) {
	return developerDao.getStudent(id);
}

@Override
@Transactional
public void saveStudent(Student student) {
	developerDao.saveStudent(student);
}

@Override
@Transactional
public void deleteStudent(Student student) {
	developerDao.deleteStudent(student);
}

@Override
@Transactional
public List<StudentMark> getStudentMark() {
	return developerDao.getStudentMark();
}

@Override
@Transactional
public StudentMark getStudentMark(int id) {
	return developerDao.getStudentMark(id) ;
}

@Override
@Transactional
public void saveStudentMark(StudentMark studentMark) {
	developerDao.saveStudentMark(studentMark);
}

@Override
@Transactional
public void deleteStudentMark(StudentMark studentMark) {
	developerDao.deleteStudentMark(studentMark);
}

@Override
@Transactional
public List<DepartmentSubject> getDepartmentSubject() {
	return developerDao.getDepartmentSubject();
}

@Override
@Transactional
public DepartmentSubject getDepartmentSubject(int id) {
	return developerDao.getDepartmentSubject(id);
}

@Override
@Transactional
public void saveDepartmentSubject(DepartmentSubject departmentSubject) {
	developerDao.saveDepartmentSubject(departmentSubject);
}

@Override
@Transactional
public void deleteDepartmentSubject(DepartmentSubject departmentSubject) {
	developerDao.deleteDepartmentSubject(departmentSubject);
}

@Override
@Transactional
public List<SubjectAlloc> getSubjectAlloc() {
	return developerDao.getSubjectAlloc();
}

@Override
@Transactional
public SubjectAlloc getSubjectAlloc(int id) {
	return developerDao.getSubjectAlloc(id);
}

@Override
@Transactional
public void saveSubjectAlloc(SubjectAlloc subjectAlloc) {
	developerDao.saveSubjectAlloc(subjectAlloc);
}

@Override
@Transactional
public void deleteSubjectAlloc(SubjectAlloc subjectAlloc) {
	developerDao.deleteSubjectAlloc(subjectAlloc);
}

@Override
@Transactional
public List<SuperUser> getSuperUser() {
	return developerDao.getSuperUser();
}

@Override
@Transactional
public SuperUser getSuperUser(int id) {
	return developerDao.getSuperUser(id);
}

@Override
@Transactional
public void saveSuperUser(SuperUser superUser) {
	developerDao.saveSuperUser(superUser);
}

@Override
@Transactional
public void deleteSuperUser(SuperUser superUser) {
	developerDao.deleteSuperUser(superUser);
}

@Override
@Transactional
public List<Tutor> getTutor() {
	return developerDao.getTutor();
}

@Override
@Transactional
public Tutor getTutor(int id) {
	return developerDao.getTutor(id);
}

@Override
@Transactional
public void saveTutor(Tutor tutor) {
	developerDao.saveTutor(tutor);
}

@Override
@Transactional
public void deleteTutor(Tutor tutor) {
	developerDao.deleteTutor(tutor);
}
	
}
