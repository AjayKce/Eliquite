package student.kce.erp.dao;

import java.util.List;

import student.kce.erp.model.Admin;
import student.kce.erp.model.College;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.RequestErp;
import student.kce.erp.model.SuperUser;

public interface SuperUserDao {

	boolean isAvailable();

	void createSuperUser(SuperUser theSuperUser);

	boolean isSuperUser(String username, String password);

	Integer getSuperId(String username, String password);

	boolean isCollegeAvailable(String collegeName);

	void addCollege(College theCollege);

	List<College> listColleges();

	College getCollege(String collegeId);

	void deleteCollege(College college);

	void sendRequestErp(RequestErp theRequestErp);

	List<RequestErp> getAllRequestErp();

	RequestErp getSingleRequestErp(String id);

	void deleteErp(RequestErp singleRequest);

	Integer getCollegeId(String college, String username, String password);

	List<Admin> listAdmins();

	List<GroupAttachment> getStudentAttachments(String groupCode);


}
