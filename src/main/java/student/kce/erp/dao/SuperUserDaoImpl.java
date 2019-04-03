package student.kce.erp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import student.kce.erp.model.Admin;
import student.kce.erp.model.College;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.RequestErp;
import student.kce.erp.model.SuperUser;

@Repository
public class SuperUserDaoImpl implements SuperUserDao {

@Autowired
SessionFactory factory;

@Override
public boolean isAvailable() {
	Session session = factory.getCurrentSession();
	boolean result = false;
	Query query = session.createQuery("from SuperUser");
	List<SuperUser> superUsers = query.list();
	for(SuperUser temp:superUsers) {
		result=true;
		break;
	}
	return result;
}

@Override
public void createSuperUser(SuperUser theSuperUser) {
	Session session = factory.getCurrentSession();
	session.saveOrUpdate(theSuperUser);
}

@Override
public boolean isSuperUser(String username, String password) {
	Session session = factory.getCurrentSession();
	Query query = session.createQuery("from SuperUser s where s.username='"+username+"' and s.password='"+password+"'");
	List<SuperUser> s1 = query.list();
	boolean result = false;
	for(SuperUser temp:s1) {
		result=true;
	}
	return result;
}

@Override
public Integer getSuperId(String username, String password) {
	Session session = factory.getCurrentSession();
	Query query = session.createQuery("from SuperUser s where s.username='"+username+"' and s.password='"+password+"'");
	List<SuperUser> s1 = query.list();
	int result=0;
	for(SuperUser temp:s1) {
		result=temp.getId();
	}
	return result;
}

@Override
public boolean isCollegeAvailable(String collegeName) {
	Session session = factory.getCurrentSession();
	boolean result = false;
	Query query = session.createQuery("from College c where c.collegeName='"+collegeName+"'");
	List<College> c = query.list();
	for(College temp:c) {
		result=true;
	}
	return result;
}

@Override
public void addCollege(College theCollege) {
	Session session = factory.getCurrentSession();
	session.saveOrUpdate(theCollege);
}

@Override
public List<College> listColleges() {
	Session session = factory.getCurrentSession();
	List<College> colleges = session.createQuery("from College s order by s.collegeName").list();
	return colleges;
}

@Override
public College getCollege(String collegeId) {
	Session session = factory.getCurrentSession();
	College college = session.get(College.class,Integer.parseInt(collegeId));
	return college;
}

@Override
public void deleteCollege(College college) {
	Session session = factory.getCurrentSession();
	session.delete(college);
}

@Override
public void sendRequestErp(RequestErp theRequestErp) {
	Session session = factory.getCurrentSession();
	session.save(theRequestErp);
}

@Override
public List<RequestErp> getAllRequestErp() {
	Session session = factory.getCurrentSession();
	Query query = session.createQuery("from RequestErp");
	List<RequestErp> requestErps = query.list();
	return requestErps;
}

@Override
public RequestErp getSingleRequestErp(String id) {
	Session session = factory.getCurrentSession();
	RequestErp singleRequestErp = session.get(RequestErp.class, Integer.parseInt(id));
	return singleRequestErp;
}

@Override
public void deleteErp(RequestErp singleRequest) {
	Session session = factory.getCurrentSession();
	session.delete(singleRequest);
}

@Override
public Integer getCollegeId(String college, String username, String password) {
	int id=0;
	Session session = factory.getCurrentSession();
	String sql = "from College c where c.collegeName='"+college+"' and c.username='"+username+"'and c.password='"+password+"'";
	Query query = session.createQuery(sql);
	List<College> colleges = query.list();
	for(College temp:colleges) {
		id=temp.getId();
	}
	return id;
}

@Override
public List<Admin> listAdmins() {
	Session session = factory.getCurrentSession();
	Query query = session.createQuery("from Admin a order by a.adminName");
	List<Admin> admins = query.list();
	return admins;
}

@Override
public List<GroupAttachment> getStudentAttachments(String groupCode) {
	Session session = factory.getCurrentSession();
	Query query = session.createQuery("from GroupAttachment a where a.groupCode='"+groupCode+"' order by a.fileName");
	List<GroupAttachment> attachments = query.list();
	return attachments;
}
	
}
