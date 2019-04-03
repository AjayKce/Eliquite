package student.kce.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import student.kce.erp.config.DataConnect;
import student.kce.erp.dao.SuperUserDao;
import student.kce.erp.model.Admin;
import student.kce.erp.model.College;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.RequestErp;
import student.kce.erp.model.SuperUser;

@Service
public class SuperUserServiceImpl implements SuperUserService {

	@Autowired
	SuperUserDao superUserDao;
	
	
	@Autowired
	DataConnect dataConnect;
	
	@Override
	@Transactional
	public boolean isAvailable() {
		return superUserDao.isAvailable();
	}

	@Override
	@Transactional
	public void createSuperUser(SuperUser theSuperUser) {
		superUserDao.createSuperUser(theSuperUser);
	}

	@Override
	@Transactional
	public boolean isSuperUser(String username, String password) {
		return superUserDao.isSuperUser(username,password);
	}

	@Override
	@Transactional
	public Integer getSuperId(String username, String password) {
		return superUserDao.getSuperId(username,password);
	}

	@Override
	@Transactional
	public boolean isCollegeAvailable(String collegeName) {
		return superUserDao.isCollegeAvailable(collegeName);
	}

	@Override
	@Transactional
	public void addCollege(College theCollege) {
		superUserDao.addCollege(theCollege);
	}

	@Override
	@Transactional
	public List<College> listColleges() {
		return superUserDao.listColleges();
	}

	@Override
	@Transactional
	public College getCollege(String collegeId) {
		return superUserDao.getCollege(collegeId);
	}

	@Override
	@Transactional
	public void deleteCollege(College college) {
		superUserDao.deleteCollege(college);	
	}

	@Override
	@Transactional
	public void sendRequestErp(RequestErp theRequestErp) {
		superUserDao.sendRequestErp(theRequestErp);
		
	}

	@Override
	@Transactional
	public List<RequestErp> getAllRequestErp() {
		return superUserDao.getAllRequestErp();
	}

	@Override
	@Transactional
	public RequestErp getSingleRequestErp(String id) {
		return superUserDao.getSingleRequestErp(id);
	}

	@Override
	@Transactional
	public void deleteErp(RequestErp singleRequest) {
		superUserDao.deleteErp(singleRequest);
	}

	@Override
	@Transactional
	public Integer getCollegeId(String college, String username, String password) {
		return superUserDao.getCollegeId(college,username,password);
	}

	@Override
	@Transactional
	public List<Admin> listAdmins() {
		return superUserDao.listAdmins();
	}

	@Override
	public DataConnect getDataConnection() {
		return dataConnect;
	}

	@Override
	@Transactional
	public List<GroupAttachment> getStudentAttachments(String groupCode) {
		return superUserDao.getStudentAttachments(groupCode);
	}

}
