package student.kce.erp.service;

import java.util.List;

import student.kce.erp.model.Admin;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.Batch;
import student.kce.erp.model.CollegeFeed;
import student.kce.erp.model.CreateFeedback;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.FeedBackAlloc;
import student.kce.erp.model.FeedBackSet;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentClass;
import student.kce.erp.model.SubjectAlloc;

public interface CollegeService {

	boolean isCollege(String college, String username, String password);

	boolean isAdminAvailable(int collegeId, String department);

	void addAdmin(Admin admin);

	List<Admin> getAdmins(String collegeId);

	Admin getAdmin(String id);

	void deleteAdmin(Admin admin);

	List<FeedBackSet> getFeedSets(String collegeId);

	boolean isFeedSetAvailable(String collegeId, String feedSet);

	void addFeedSet(FeedBackSet theFeedSet);

	FeedBackSet getSingleFeedSet(String id);

	void deleteFeedSet(FeedBackSet feedSet);

	List<CollegeFeed> getCollegeFeeds(String feedsetId, String collegeId);

	void createCollegeFeed(CollegeFeed theCollegeFeed);

	CollegeFeed getSingleCollegeFeed(String id);

	void deleteCollegeFeed(CollegeFeed singleCollegeFeed);

	List<FeedBackAlloc> getFeedBackAllocs(String collegeId);

	boolean isFeedAllocAvailable(String collegeId, String department);

	void addFeedBackAlloc(FeedBackAlloc theFeedBackAlloc);

	void deleteFeedAlloc(String id);

	String getAdminIdForFeedBack(String collegeId, String department);

	List<StudentClass> getStudentClasses(String adminId, String department);

	List<SubjectAlloc> getSubjectAllocations(String adminId, String batch, String semester, String department,
			String section);

	List<Enrollment> getStudentEnrollments(String adminId, String batch, String semester, String department,
			String section, String subjectCode);

	List<CreateFeedback> getFeeds(String collegeId, String adminId, String batch, String semester, String department,
			String section, String subjectCode, String studentId);

	String getFeedBackAllocsSetId(String collegeId, String department);

	List<Staff> getStaffs(String adminId, String department);

	List<CreateFeedback> getFullFeeds(String adminId, String staffId);

	float getCollegeFeedPercentage(String collegeId, String staffId, String adminId, String result);

	List<CreateFeedback> getEachDepartmentFeedbackResults(String collegeId, String staffId, String adminId,
			String feed);

	float getEachDepartmentFeedPercentage(String collegeId, String staffId, String adminId, String result, String feed);

	String getAdminIdForAttendance(String collegeId, String department);

	List<Batch> getBatches(String adminId);

	List<AttendanceScheduler> getSchedules(String collegeId, String department);

	int getAttendanceRegisteredId(String collegeId, String department, String batch, String semester, String hour);

	void saveAttendanceSet(AttendanceScheduler schedule);

	void deleteScheduler(String schedulerId);

	List<AttendanceScheduler> getAttendancePeriods(String collegeId, String department, String batch);

	AttendanceResult getSinglePeriodResult(String collegeId, int studentId, String date, String period);

	List<Student> getStudents(String adminId, String batch, String semester, String department, String section);


}
