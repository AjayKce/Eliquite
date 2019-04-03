package student.kce.erp.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import student.kce.erp.model.CollegeFeed;
import student.kce.erp.model.CreateFeedback;
import student.kce.erp.model.DepartmentFeed;
import student.kce.erp.model.DepartmentFeedSetList;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.DepartmentFeedbackSet;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.FeedBackAlloc;
import student.kce.erp.model.FeedBackSet;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.ResourceGroup;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.Tutor;

@Repository
public class StudentDaoImpl implements StudentDao {

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Override
	public Student getStudent(String collegeId, String department, String semester, String username, String password) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from Student s where s.collegeId='" + collegeId + "' and s.department='" + department
				+ "' and s.semester='" + semester + "' and s.username='" + username + "' and s.password='" + password
				+ "'";
		Query query = session.createQuery(sql);
		List<Student> students = query.list();
		if (students.size() != 0) {
			return students.get(0);
		} else {
			return new Student();
		}
	}

	@Override
	public List<DepartmentSubject> getDepartmentSubjects(String adminId, String batch, String semester,
			String department) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from DepartmentSubject s where s.adminId='" + adminId + "' and s.batch='" + batch
				+ "' and s.semester='" + semester + "' and s.department='" + department + "'";
		Query query = session.createQuery(sql);
		List<DepartmentSubject> subjects = query.list();
		return subjects;
	}

	@Override
	public List<Enrollment> getEnrollments(String studentId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from Enrollment e where e.studentId='" + studentId + "' order by e.subjectCode";
		Query query = session.createQuery(sql);
		List<Enrollment> enrollments = query.list();
		return enrollments;
	}

	@Override
	public boolean isCourseEnrolled(String studentId, String subjectId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from Enrollment e where e.studentId='" + studentId + "' and e.subjectId='" + subjectId + "'";
		Query query = session.createQuery(sql);
		List<Enrollment> enrollments = query.list();
		if (enrollments.size() != 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void createEnrollment(Enrollment enrollment) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(enrollment);
	}

	@Override
	public String isEnrollmentActive(String adminId, String batch, String semester, String department, String section) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from Tutor s where s.adminId='" + adminId + "' and s.batch='" + batch + "' and s.semester='"
				+ semester + "' and s.department='" + department + "' and s.section='" + section + "'";
		Query query = session.createQuery(sql);
		List<Tutor> tutor = query.list();
		if (tutor.size() != 0) {
			return tutor.get(0).getStatus();
		} else {
			return "INACTIVE";
		}
	}

	@Override
	public List<SubjectAlloc> getFeedRequirement(String adminId, String batch, String semester, String department,
			String section, String studentId, String studentRoll) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from SubjectAlloc s where s.adminId='"+adminId+"' and s.batch='"+batch+"' and s.semester='"+semester+"' and s.department='"+department+"' and s.section='"+section+"' and s.subjectCode in(select e.subjectCode from Enrollment e where e.studentId='"
				+ studentId + "') and s.subjectCode not in(select f.subjectCode from CreateFeedback f where f.studentId='"+studentId+"')";
		Query query = session.createQuery(sql);
		List<SubjectAlloc> subjectAllocs = query.list();
		return subjectAllocs;
	}

	@Override
	public String getFeedSetId(String collegeId, String department) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from FeedBackAlloc f where f.collegeId='"+collegeId+"' and f.department='"+department+"'";
		Query query = session.createQuery(sql);
		List<FeedBackAlloc> feedBackAlloc = query.list();
		if(feedBackAlloc.size()!=0) {
			return Integer.toString(feedBackAlloc.get(0).getFeedsetId());
		}
		else {
		return "0";
		}
	}

	@Override
	public List<CollegeFeed> getFullCollegeFeeds(String feedsetId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from CollegeFeed f where f.feedsetId='"+feedsetId+"'";
		Query query = session.createQuery(sql);
		List<CollegeFeed> feeds = query.list();
		return feeds;
	}

	@Override
	public String getSubjectTitle(String adminId, String subjectCode) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from DepartmentSubject s where s.adminId='" + adminId + "' and s.subjectCode='"+subjectCode+"'";
		Query query = session.createQuery(sql);
		List<DepartmentSubject> subjects = query.list();
		return subjects.get(0).getSubjectTitle();
	}

	@Override
	public FeedBackSet getFeedBackSet(String feedsetId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		FeedBackSet theFeedBackSet = session.get(FeedBackSet.class, Integer.parseInt(feedsetId));
		return theFeedBackSet;
	}

	@Override
	public String getStaffId(String adminId, String department, String staffName) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from Staff s where s.adminId='" + adminId + "' and s.department='"+department+"' and s.staffName='"+staffName+"'";
		Query query = session.createQuery(sql);
		List<Staff> staffs = query.list();
		return Integer.toString(staffs.get(0).getId());
	}

	@Override
	public String getSubjectId(String adminId, String batch, String semester, String department, String subjectCode,
			String subjectTitle) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from DepartmentSubject s where s.adminId='" + adminId + "' and s.batch='" + batch
				+ "' and s.semester='" + semester + "' and s.department='" + department + "' and s.subjectCode='"+subjectCode+"' and s.subjectTitle='"+subjectTitle+"'";
		Query query = session.createQuery(sql);
		List<DepartmentSubject> subjects = query.list();
		return Integer.toString(subjects.get(0).getId());
	}

	@Override
	public void createCollegeFeedBack(CreateFeedback createFeedBack) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(createFeedBack);
	}

	@Override
	public List<DepartmentFeedbackSet> getDepartmentFeedbackSets(String collegeId, String adminId, String department) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from DepartmentFeedbackSet s where s.adminId='" + adminId + "' and s.collegeId='"+collegeId+"' and s.department='"+department+"' order by s.feedSet";
		Query query = session.createQuery(sql);
		List<DepartmentFeedbackSet> departmentFeedbackSets = query.list();
		return departmentFeedbackSets;
	}

	@Override
	public List<DepartmentFeedbackResult> getDepartmentFeedbackResults(String feedSetId, String studentId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from DepartmentFeedbackResult e where e.studentId='"+studentId +"' and e.feedSetId='"+feedSetId+"'  order by e.rollno";
		Query query = session.createQuery(sql);
		List<DepartmentFeedbackResult> departmentFeedbackResults = query.list();
		return departmentFeedbackResults;
	}

	@Override
	public List<DepartmentFeed> getDepartmentFeeds(String feedSetId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		String sql = "from DepartmentFeed e where e.feedSetId='" + feedSetId + "' order by e.feed";
		Query query = session.createQuery(sql);
		List<DepartmentFeed> departmentFeeds = query.list();
		return departmentFeeds;
	}

	@Override
	public void saveDepartmentfeedBack(DepartmentFeedbackResult departmentFeedbackResult) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(departmentFeedbackResult);
	}

	@Override
	public List<ResourceGroup> getGroupsets(String studentId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		List<ResourceGroup> resources = session.createQuery("from ResourceGroup r where r.studentId='"+studentId+"' order by r.groupCode").list();
		return resources;
	}

	@Override
	public List<ResourceGroup> isGroupCodeAvailable(String groupCode) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		List<ResourceGroup> resources = session.createQuery("from ResourceGroup r where r.groupCode='"+groupCode+"'").list();
		return resources;
	}

	@Override
	public void createStudentResourceGroup(ResourceGroup groupset) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(groupset);
	}

	@Override
	public void deleteGroup(int groupId) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		ResourceGroup resource = session.get(ResourceGroup.class, groupId);
		List<GroupAttachment> attachments = session.createQuery("from GroupAttachment g where g.groupId='"+groupId+"'").list();
		System.out.println(attachments.size());
		for(GroupAttachment temp:attachments) {
			session.delete(temp);
		}
		session.delete(resource);
	}

	@Override
	public List<GroupAttachment> getStudentAttachments(String studentId, String groupCode) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		List<GroupAttachment> attachments = session.createQuery("from GroupAttachment g where g.groupCode='"+groupCode+"' and g.studentId='"+studentId+"' order by g.fileName").list();
		return attachments;
	}

	@Override
	public GroupAttachment getFile(String id) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		GroupAttachment resource = session.get(GroupAttachment.class, Integer.parseInt(id));
		return resource;
	}

	@Override
	public void deleteStudentResourceFile(String id) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		GroupAttachment attachment = session.get(GroupAttachment.class, Integer.parseInt(id));
		session.delete(attachment);
	}

	@Override
	public ResourceGroup getSingleGroupsets(String studentId, int id) {
		SessionFactory factory = entityManagerFactory.unwrap(SessionFactory.class);
		Session session = factory.getCurrentSession();
		List<ResourceGroup> res = session.createQuery("from ResourceGroup r where r.id='"+id+"' and r.studentId='"+studentId+"'").list();
		return res.get(0);
	}

}
