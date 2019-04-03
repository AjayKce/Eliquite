package student.kce.erp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import student.kce.erp.model.Admin;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.Batch;
import student.kce.erp.model.College;
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

@Repository
public class CollegeDaoImpl implements CollegeDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean isCollege(String college, String username, String password) {
		boolean result=false;
		Session session = sessionFactory.getCurrentSession();
		String sql = "from College c where c.collegeName='"+college+"' and c.username='"+username+"'and c.password='"+password+"'";
		Query query = session.createQuery(sql);
		List<College> colleges = query.list();
		if(colleges.size()!=0) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean isAdminAvailable(int collegeId, String department) {
		boolean result=false;
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Admin c where c.collegeId='"+collegeId+"' and c.department='"+department+"'";
		Query query = session.createQuery(sql);
		List<Admin> admins = query.list();
		if(admins.size()!=0) {
			result = true;
		}
		return result;
	}

	@Override
	public void addAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(admin);
	}

	@Override
	public List<Admin> getAdmins(String collegeId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Admin a where a.collegeId='"+collegeId+"'";
		Query query = session.createQuery(sql);
		List<Admin> admins = query.list();
		return admins;
	}

	@Override
	public Admin getAdmin(String id) {
		Session session = sessionFactory.getCurrentSession();
		Admin admin = session.get(Admin.class, Integer.parseInt(id));
		return admin;
	}

	@Override
	public void deleteAdmin(Admin admin) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(admin);
	}

	@Override
	public List<FeedBackSet> getFeedSets(String collegeId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from FeedBackSet f where f.collegeId='"+collegeId+"' order by f.feedSet";
		Query query = session.createQuery(sql);
		List<FeedBackSet> feedsets = query.list();
		return feedsets;
	}

	@Override
	public boolean isFeedSetAvailable(String collegeId, String feedSet) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from FeedBackSet f where f.collegeId='"+collegeId+"' and f.feedSet='"+feedSet+"'";
		Query query = session.createQuery(sql);
		List<FeedBackSet> feedsets = query.list();
		if(feedsets.size()!=0) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public void addFeedSet(FeedBackSet theFeedSet) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(theFeedSet);
	}

	@Override
	public FeedBackSet getSingleFeedSet(String id) {
		Session session = sessionFactory.getCurrentSession();
		FeedBackSet feedset = session.get(FeedBackSet.class, Integer.parseInt(id));
		return feedset;
	}

	@Override
	public void deleteFeedSet(FeedBackSet feedSet) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(feedSet);
	}

	@Override
	public List<CollegeFeed> getCollegeFeeds(String feedsetId, String collegeId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from CollegeFeed c where c.collegeId='"+collegeId+"' and c.feedsetId='"+feedsetId+"'";
		Query query = session.createQuery(sql);
		List<CollegeFeed> feeds = query.list();
		return feeds;
	}

	@Override
	public void createCollegeFeed(CollegeFeed theCollegeFeed) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(theCollegeFeed);
	}

	@Override
	public CollegeFeed getSingleCollegeFeed(String id) {
		Session session = sessionFactory.getCurrentSession();
		CollegeFeed collegeFeed = session.get(CollegeFeed.class, Integer.parseInt(id));
		return collegeFeed;
	}

	@Override
	public void deleteCollegeFeed(CollegeFeed singleCollegeFeed) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(singleCollegeFeed);
	}

	@Override
	public List<FeedBackAlloc> getFeedBackAllocs(String collegeId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from FeedBackAlloc f where f.collegeId='"+collegeId+"' order by f.department";
		Query query = session.createQuery(sql);
		List<FeedBackAlloc> feedBackAllocs = query.list();
		return feedBackAllocs;
	}

	@Override
	public boolean isFeedAllocAvailable(String collegeId, String department) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from FeedBackAlloc f where f.collegeId='"+collegeId+"' and f.department='"+department+"'";
		Query query = session.createQuery(sql);
		List<FeedBackAlloc> feedBackAllocs = query.list();
		if(feedBackAllocs.size()!=0) {
			return true;
		}
		else {
		return false;
		}
	}

	@Override
	public void addFeedBackAlloc(FeedBackAlloc theFeedBackAlloc) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(theFeedBackAlloc);
	}

	@Override
	public void deleteFeedAlloc(String id) {
		Session session = sessionFactory.getCurrentSession();
		FeedBackAlloc feedbackAlloc = session.get(FeedBackAlloc.class, Integer.parseInt(id));
		session.delete(feedbackAlloc);
	}

	@Override
	public String getAdminIdForFeedBack(String collegeId, String department) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Admin a where a.collegeId='"+collegeId+"' and a.department='"+department+"'";
		Query query = session.createQuery(sql);
		List<Admin> admins = query.list();
		if(admins.size()!=0) {
			return Integer.toString(admins.get(0).getId());
		}
		else {
		return "0";
		}
	}

	@Override
	public List<StudentClass> getStudentClasses(String adminId, String department) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from StudentClass s where s.adminId='"+adminId+"' and s.department='"+department+"' order by s.batch,s.semester,s.section";
		Query query = session.createQuery(sql);
		List<StudentClass> studentClasses = query.list();
		return studentClasses;
	}

	@Override
	public List<SubjectAlloc> getSubjectAllocations(String adminId, String batch, String semester, String department,
			String section) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from SubjectAlloc s where s.adminId='"+adminId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' order by s.batch,s.semester,s.section,s.subjectCode";
		Query query = session.createQuery(sql);
		List<SubjectAlloc> subjectAllocs = query.list();
		return subjectAllocs;
	}

	@Override
	public List<Enrollment> getStudentEnrollments(String adminId, String batch, String semester, String department,
			String section, String subjectCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Enrollment s where s.adminId='"+adminId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' and s.subjectCode='"+subjectCode+"' order by s.batch,s.semester,s.section,s.studentRoll";
		Query query = session.createQuery(sql);
		List<Enrollment> enrollments = query.list();
		return enrollments;
	}

	@Override
	public List<CreateFeedback> getFeeds(String collegeId, String adminId, String batch, String semester,
			String department, String section, String subjectCode, String studentId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from CreateFeedback s where s.collegeId='"+collegeId+"' and s.adminId='"+adminId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' and s.subjectCode='"+subjectCode+"' and s.studentId='"+studentId+"' order by s.feed";
		Query query = session.createQuery(sql);
		List<CreateFeedback> feeds = query.list();
		return feeds;
	}

	@Override
	public String getFeedBackAllocsSetId(String collegeId, String department) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from FeedBackAlloc s where s.collegeId='"+collegeId+"' and s.department='"+department+"'";
		Query query = session.createQuery(sql);
		FeedBackAlloc feedBackAlloc = (FeedBackAlloc) query.getSingleResult();
		return Integer.toString(feedBackAlloc.getFeedsetId());
	}

	@Override
	public List<Staff> getStaffs(String adminId, String department) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Staff s where s.adminId='"+adminId+"' and s.department='"+department+"' order by s.staffName";
		Query query = session.createQuery(sql);
		List<Staff> staffs = query.list();
		return staffs;
	}

	@Override
	public List<CreateFeedback> getFullFeeds(String adminId, String staffId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from CreateFeedback s where s.adminId='"+adminId+"' and s.staffId='"+staffId+"'";
		Query query = session.createQuery(sql);
		List<CreateFeedback> feeds = query.list();
		return feeds;
	}

	@Override
	public float getCollegeFeedPercentage(String collegeId, String staffId, String adminId, String result) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CreateFeedback d where d.adminId='"+adminId+"' and d.collegeId='"+collegeId+"' and d.result='"+result+"' and d.staffId='"+staffId+"'" );
		List<CreateFeedback> feedbacks = query.list();
		return feedbacks.size();
	}

	@Override
	public List<CreateFeedback> getEachDepartmentFeedbackResults(String collegeId, String staffId, String adminId,
			String feed) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CreateFeedback d where d.adminId='"+adminId+"' and d.collegeId='"+collegeId+"' and d.feed='"+feed+"' and d.staffId='"+staffId+"'" );
		List<CreateFeedback> feedbacks = query.list();
		return feedbacks;
	}

	@Override
	public float getEachDepartmentFeedPercentage(String collegeId, String staffId, String adminId, String result,
			String feed) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from CreateFeedback d where d.adminId='"+adminId+"' and d.collegeId='"+collegeId+"' and d.result='"+result+"' and d.staffId='"+staffId+"' and d.feed='"+feed+"'" );
		List<CreateFeedback> feedbacks = query.list();
		return feedbacks.size();
	}

	@Override
	public String getAdminIdForAttendance(String collegeId, String department) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Admin c where c.collegeId='"+collegeId+"' and c.department='"+department+"'";
		Query query = session.createQuery(sql);
		List<Admin> admins = query.list();
		if(admins.size()!=0) {
			return Integer.toString(admins.get(0).getId());
		}
		else {
			return "0";
		}
	}

	@Override
	public List<Batch> getBatches(String adminId) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Batch d where d.adminId='"+adminId+"' order by d.year" );
		List<Batch> batches = query.list();
		return batches;
	}

	@Override
	public List<AttendanceScheduler> getSchedules(String collegeId, String department) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from AttendanceScheduler c where c.collegeId='"+collegeId+"' and c.department='"+department+"' order by c.batch,c.semester,c.hour";
		Query query = session.createQuery(sql);
		List<AttendanceScheduler> schedules = query.list();
		return schedules;
	}

	@Override
	public int getAttendanceRegisteredId(String collegeId, String department, String batch, String semester,
			String hour) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from AttendanceScheduler s where s.collegeId='"+collegeId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.hour='"+hour+"'";
		Query query = session.createQuery(sql);
		List<AttendanceScheduler> schedules = query.list();
		if(schedules.size()==0) {
			return 0;
		}
		else {
			return schedules.get(0).getId();
		}
	}

	@Override
	public void saveAttendanceSet(AttendanceScheduler schedule) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(schedule);
	}

	@Override
	public void deleteScheduler(String schedulerId) {
		Session session = sessionFactory.getCurrentSession();
		AttendanceScheduler scheduler = session.get(AttendanceScheduler.class, Integer.parseInt(schedulerId));
		session.delete(scheduler);
	}

	@Override
	public List<AttendanceScheduler> getAttendancePeriods(String collegeId, String department, String batch) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from AttendanceScheduler c where c.collegeId='"+collegeId+"' and c.department='"+department+"' and batch='"+batch+"' order by c.batch,c.semester,c.hour";
		Query query = session.createQuery(sql);
		List<AttendanceScheduler> schedules = query.list();
		return schedules;
	}

	@Override
	public AttendanceResult getSinglePeriodResult(String collegeId, int studentId, String date, String period) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from AttendanceResult c where c.collegeId='"+collegeId+"' and c.studentId='"+Integer.toString(studentId)+"' and c.date='"+date+"' and c.period='"+period+"'";
		Query query = session.createQuery(sql);
		List<AttendanceResult> scheduleResult = query.list();
		if(scheduleResult.size()!=0) {
			return scheduleResult.get(0);
		}
		else {
		return new AttendanceResult();
		}
	}

	@Override
	public List<Student> getStudents(String adminId, String batch, String semester, String department, String section) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "from Student s where s.adminId='"+adminId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' order by s.rollno";
		Query query = session.createQuery(sql);
		List<Student> students = query.list();
		return students;
	}

}
