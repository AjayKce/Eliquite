package student.kce.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.kce.erp.dao.CollegeDao;
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

@Service
public class CollegeServiceImpl implements CollegeService {

	@Autowired
	CollegeDao collegeDao;
	
	@Override
	@Transactional
	public boolean isCollege(String college, String username, String password) {
		return collegeDao.isCollege(college,username,password);
	}

	@Override
	@Transactional
	public boolean isAdminAvailable(int collegeId, String department) {
		return collegeDao.isAdminAvailable(collegeId,department);
	}

	@Override
	@Transactional
	public void addAdmin(Admin admin) {
		collegeDao.addAdmin(admin);
	}

	@Override
	@Transactional
	public List<Admin> getAdmins(String collegeId) {
		// TODO Auto-generated method stub
		return collegeDao.getAdmins(collegeId);
	}

	@Override
	@Transactional
	public Admin getAdmin(String id) {
		return collegeDao.getAdmin(id);
	}

	@Override
	@Transactional
	public void deleteAdmin(Admin admin) {
		collegeDao.deleteAdmin(admin);
	}

	@Override
	@Transactional
	public List<FeedBackSet> getFeedSets(String collegeId) {
		return collegeDao.getFeedSets(collegeId);
	}

	@Override
	@Transactional
	public boolean isFeedSetAvailable(String collegeId, String feedSet) {
		return collegeDao.isFeedSetAvailable(collegeId,feedSet);
	}

	@Override
	@Transactional
	public void addFeedSet(FeedBackSet theFeedSet) {
		collegeDao.addFeedSet(theFeedSet);
	}

	@Override
	@Transactional
	public FeedBackSet getSingleFeedSet(String id) {
		return collegeDao.getSingleFeedSet(id);
	}

	@Override
	@Transactional
	public void deleteFeedSet(FeedBackSet feedSet) {
		collegeDao.deleteFeedSet(feedSet);
	}

	@Override
	@Transactional
	public List<CollegeFeed> getCollegeFeeds(String feedsetId, String collegeId) {
		return collegeDao.getCollegeFeeds(feedsetId,collegeId);
	}

	@Override
	@Transactional
	public void createCollegeFeed(CollegeFeed theCollegeFeed) {
		collegeDao.createCollegeFeed(theCollegeFeed);
	}

	@Override
	@Transactional
	public CollegeFeed getSingleCollegeFeed(String id) {
		return collegeDao.getSingleCollegeFeed(id);
	}

	@Override
	@Transactional
	public void deleteCollegeFeed(CollegeFeed singleCollegeFeed) {
		collegeDao.deleteCollegeFeed(singleCollegeFeed);
	}

	@Override
	@Transactional
	public List<FeedBackAlloc> getFeedBackAllocs(String collegeId) {
		return collegeDao.getFeedBackAllocs(collegeId);
	}

	@Override
	@Transactional
	public boolean isFeedAllocAvailable(String collegeId, String department) {
		return collegeDao.isFeedAllocAvailable(collegeId,department);
	}

	@Override
	@Transactional
	public void addFeedBackAlloc(FeedBackAlloc theFeedBackAlloc) {
		collegeDao.addFeedBackAlloc(theFeedBackAlloc);
	}

	@Override
	@Transactional
	public void deleteFeedAlloc(String id) {
		collegeDao.deleteFeedAlloc(id);
	}

	@Override
	@Transactional
	public String getAdminIdForFeedBack(String collegeId, String department) {
		return collegeDao.getAdminIdForFeedBack(collegeId,department);
	}

	@Override
	@Transactional
	public List<StudentClass> getStudentClasses(String adminId, String department) {
		return collegeDao.getStudentClasses(adminId,department);
	}

	@Override
	@Transactional
	public List<SubjectAlloc> getSubjectAllocations(String adminId, String batch, String semester, String department,
			String section) {
		return collegeDao.getSubjectAllocations(adminId,batch,semester,department,section);
	}

	@Override
	@Transactional
	public List<Enrollment> getStudentEnrollments(String adminId, String batch, String semester, String department,
			String section, String subjectCode) {
		return collegeDao.getStudentEnrollments(adminId,batch,semester,department,section,subjectCode);
	}

	@Override
	@Transactional
	public List<CreateFeedback> getFeeds(String collegeId, String adminId, String batch, String semester,
			String department, String section, String subjectCode, String studentId) {
		return collegeDao.getFeeds(collegeId,adminId,batch,semester,department,section,subjectCode,studentId);
	}

	@Override
	@Transactional
	public String getFeedBackAllocsSetId(String collegeId, String department) {
		return collegeDao.getFeedBackAllocsSetId(collegeId,department);
	}

	@Override
	@Transactional
	public List<Staff> getStaffs(String adminId, String department) {
		return collegeDao.getStaffs(adminId,department);
	}

	@Override
	@Transactional
	public List<CreateFeedback> getFullFeeds(String adminId, String staffId) {
		return collegeDao.getFullFeeds(adminId,staffId);
	}

	@Override
	@Transactional
	public float getCollegeFeedPercentage(String collegeId, String staffId, String adminId, String result) {
		return collegeDao.getCollegeFeedPercentage(collegeId,staffId,adminId,result);
	}

	@Override
	@Transactional
	public List<CreateFeedback> getEachDepartmentFeedbackResults(String collegeId, String staffId, String adminId,
			String feed) {
		return collegeDao.getEachDepartmentFeedbackResults(collegeId,staffId,adminId,feed);
	}

	@Override
	@Transactional
	public float getEachDepartmentFeedPercentage(String collegeId, String staffId, String adminId, String result,
			String feed) {
		return collegeDao.getEachDepartmentFeedPercentage(collegeId,staffId,adminId,result,feed);
	}

	@Override
	@Transactional
	public String getAdminIdForAttendance(String collegeId, String department) {
		return collegeDao.getAdminIdForAttendance(collegeId, department);
	}

	@Override
	@Transactional
	public List<Batch> getBatches(String adminId) {
		return collegeDao.getBatches(adminId);
	}

	@Override
	@Transactional
	public List<AttendanceScheduler> getSchedules(String collegeId, String department) {
		return collegeDao.getSchedules(collegeId,department);
	}

	@Override
	@Transactional
	public int getAttendanceRegisteredId(String collegeId, String department, String batch, String semester,
			String hour) {
		return collegeDao.getAttendanceRegisteredId(collegeId,department,batch,semester,hour);
	}

	@Override
	@Transactional
	public void saveAttendanceSet(AttendanceScheduler schedule) {
		collegeDao.saveAttendanceSet(schedule);
	}

	@Override
	@Transactional
	public void deleteScheduler(String schedulerId) {
		collegeDao.deleteScheduler(schedulerId) ;
	}

	@Override
	@Transactional
	public List<AttendanceScheduler> getAttendancePeriods(String collegeId, String department, String batch) {
		return collegeDao.getAttendancePeriods(collegeId, department,batch);
	}

	@Override
	@Transactional
	public AttendanceResult getSinglePeriodResult(String collegeId, int studentId, String date, String period) {
		return collegeDao.getSinglePeriodResult(collegeId,studentId,date,period);
	}

	@Override
	@Transactional
	public List<Student> getStudents(String adminId, String batch, String semester, String department, String section) {
		return collegeDao.getStudents(adminId, batch, semester, department, section);
	}

}
