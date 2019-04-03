package student.kce.erp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.kce.erp.model.Attachment;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.CollegeFeed;
import student.kce.erp.model.CreateFeedback;
import student.kce.erp.model.DepartmentFeed;
import student.kce.erp.model.DepartmentFeedSetList;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.DepartmentFeedbackSet;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.FeedBackSet;
import student.kce.erp.model.FeedSetList;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.ResourceGroup;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentMark;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.service.AdminService;
import student.kce.erp.service.StaffService;
import student.kce.erp.service.StudentService;
import student.kce.erp.service.SuperUserService;

@Controller
public class StudentController {

	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	SuperUserService superUserService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/studentHome")
	public String staffHome() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			return "redirect:/";
		}
		else if(user!=null && role.equals("College")) {
			return "redirect:/collegeHome";
		}
		else if(user!=null && role.equals("Admin")) {
			return "redirect:/adminHome";
		}
		else if(user!=null && role.equals("Staff")) {
			return "redirect:/staffHome";
		}
		else if(user!=null && role.equals("Student")) {
			return "student/studentHomePage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/processStudentLogin")
	public String processStudentLogin(Model theModel) {
		String collegeId =request.getParameter("collegeId");
		String department = request.getParameter("department");
		String semester = request.getParameter("semester");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Student student = studentService.getStudent(collegeId,department,semester,username,password);
		if(student.getId()!=0) {
			session.setAttribute("user", student.getFirstname());
			session.setAttribute("role", "Student");
			session.setAttribute("firstName", student.getFirstname());
			session.setAttribute("lastName", student.getLastname());
			session.setAttribute("studentId", Integer.toString(student.getId()));
			session.setAttribute("adminId", Integer.toString(student.getAdminId()));
			session.setAttribute("collegeId", Integer.toString(student.getCollegeId()));
			session.setAttribute("staffId", Integer.toString(student.getStaffId()));
			session.setAttribute("rollNo", student.getRollno());
			session.setAttribute("batch", student.getBatch());
			session.setAttribute("semester", student.getSemester());
			session.setAttribute("department", student.getDepartment());
			session.setAttribute("section", student.getSection());
			return "redirect:/studentHome";
		}
		else {
			request.setAttribute("invalidStudentLogin", "invalid");
			theModel.addAttribute("colleges",superUserService.listColleges());
			return "student/studentLoginPage";
		}
	}
	
	@RequestMapping("/studentEnrollment")
	public String studentEnrollment(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String adminId = session.getAttribute("adminId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			String studentId = session.getAttribute("studentId").toString();
			List<DepartmentSubject> subjects  = studentService.getDepartmentSubjects(adminId,batch,semester,department);
			List<Enrollment> enrollments = studentService.getEnrollments(studentId);
			theModel.addAttribute("enrollStatus",studentService.isEnrollmentActive(adminId,batch,semester,department,section));
			theModel.addAttribute("enrollment",new Enrollment());
			theModel.addAttribute("enrollments",enrollments);
			theModel.addAttribute("subjects",subjects);
			return "student/studentEnrollmentPage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/processStudentEnrollForm")
	public String processStudentEnrollForm(@ModelAttribute("enrollment") Enrollment enrollment) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String subjectId = Integer.toString(enrollment.getSubjectId());
		DepartmentSubject subject=adminService.getSingleSubject(Integer.parseInt(subjectId));
		String studentId = session.getAttribute("studentId").toString();
		String adminId = session.getAttribute("adminId").toString();
		String collegeId = session.getAttribute("collegeId").toString();
		String studentRoll = session.getAttribute("rollNo").toString();
		String batch = session.getAttribute("batch").toString();
		String semester = session.getAttribute("semester").toString();
		String department = session.getAttribute("department").toString();
		String section = session.getAttribute("section").toString();
		String subjectTitle = subject.getSubjectTitle();
		String subjectCode = subject.getSubjectCode();
		String studentName = session.getAttribute("firstName")+" "+session.getAttribute("lastName");
		String subjectGroup = subject.getSubjectGroup();
		String credit = Integer.toString(subject.getCredit());
		if(studentService.isCourseEnrolled(studentId,subjectId)) {
			return "redirect:/studentEnrollment";
		}
		else {
			enrollment.setCollegeId(Integer.parseInt(collegeId));
			enrollment.setAdminId(Integer.parseInt(adminId));
			enrollment.setStudentId(Integer.parseInt(studentId));
			enrollment.setStudentRoll(studentRoll);
			enrollment.setStudentName(studentName);
			enrollment.setBatch(batch);
			enrollment.setDepartment(department);
			enrollment.setSemester(semester);
			enrollment.setSection(section);
			enrollment.setSubjectCode(subjectCode);
			enrollment.setSubjectTitle(subjectTitle);
			enrollment.setSubjectGroup(subjectGroup);
			enrollment.setCredit(credit);
			studentService.createEnrollment(enrollment);
			return "redirect:/studentEnrollment";
		}
	}
	
	@RequestMapping("/studentCourse")
	public String studentCourse(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String studentId = session.getAttribute("studentId").toString();
			List<Enrollment> enrollments = studentService.getEnrollments(studentId);
			theModel.addAttribute("enrollments",enrollments);
			return "student/studentCoursePage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/studentResource")
	public String studentResource(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String subjectCode = null;
			if(session.getAttribute("subjectCode")==null) {
				subjectCode = request.getParameter("subjectCode");
			}
			else {
				subjectCode = session.getAttribute("subjectCode").toString();
			}
			String adminId = session.getAttribute("adminId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			List<Attachment> attachments = staffService.getAttachments(adminId,batch,semester,department,section,subjectCode);
			theModel.addAttribute("attachments",attachments);
			return "student/studentResourcePage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/studentMark")
	public String studentMark(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String subjectCode = null;
			if(session.getAttribute("subjectCode")==null) {
				subjectCode = request.getParameter("subjectCode");
			}
			else {
				subjectCode = session.getAttribute("subjectCode").toString();
			}
			String adminId = session.getAttribute("adminId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			String studentId = session.getAttribute("studentId").toString();
			String studentRoll = session.getAttribute("rollNo").toString();
			List<StudentMark> studentMarks = staffService.getStudentFullMark(adminId,studentId, batch, semester, department, section, studentRoll, subjectCode);
			theModel.addAttribute("studentMarks",studentMarks);
			return "student/studentMarkPage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/studentFeedback")
	public String studentFeedback() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			return "student/studentFeedBackPage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/createCollegeFeedback")
	public String createCollegeFeedback(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String collegeId=session.getAttribute("collegeId").toString();
		String adminId = session.getAttribute("adminId").toString();
		String batch = session.getAttribute("batch").toString();
		String semester = session.getAttribute("semester").toString();
		String department = session.getAttribute("department").toString();
		String section = session.getAttribute("section").toString();
		String studentId = session.getAttribute("studentId").toString();
		System.out.println(studentId+" "+adminId+" "+batch);
		String studentRoll = session.getAttribute("rollNo").toString();
		String feedsetId = studentService.getFeedSetId(collegeId,department);
		FeedBackSet theFeedBackSet = studentService.getFeedBackSet(feedsetId);
		String status = "";
		if(theFeedBackSet!=null) {
			status = theFeedBackSet.getStatus();
		}
		else {
			status = "INACTIVE";
		}
		List<CollegeFeed> collegeFeeds = studentService.getFullCollegeFeeds(feedsetId);
		List<SubjectAlloc> subjectAllocs = studentService.getFeedRequirement(adminId,batch,semester,department,section,studentId,studentRoll);
		String subjectTitle = "";
		if(subjectAllocs.size()!=0) {
			subjectTitle = studentService.getSubjectTitle(adminId,subjectAllocs.get(0).getSubjectCode());
		}
		theModel.addAttribute("status",status);
		theModel.addAttribute("subjectTitle",subjectTitle);
		theModel.addAttribute("feeds",collegeFeeds);
		theModel.addAttribute("handledStaffs",subjectAllocs);
		theModel.addAttribute("feedback",new FeedSetList());
		return "student/submitCollegeFeedback";
	}
	
	@RequestMapping("/processSubmitCollegeFeedback")
	public String processSubmitCollegeFeedback(@ModelAttribute("feedback")FeedSetList theFeedSetList) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		int i;
		for(i=0;i<theFeedSetList.getFeed().length;i++) {
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String subjectCode = theFeedSetList.getSubjectCode();
			String subjectTitle = theFeedSetList.getSubjectTitle();
			String staffName = theFeedSetList.getStaffName();
			String collegeId=session.getAttribute("collegeId").toString();
			String adminId = session.getAttribute("adminId").toString();
			String staffId=studentService.getStaffId(adminId,department,staffName);
			String studentId = session.getAttribute("studentId").toString();
			String subjectId = studentService.getSubjectId(adminId,batch,semester,department,subjectCode,subjectTitle);
			String studentName =  session.getAttribute("firstName").toString()+" "+session.getAttribute("lastName").toString();
			String studentRoll = session.getAttribute("rollNo").toString();
			String section = session.getAttribute("section").toString();
			String feed = theFeedSetList.getFeed()[i];
			String result = theFeedSetList.getResult()[i];
			
			CreateFeedback createFeedBack = new CreateFeedback();
			
			createFeedBack.setCollegeId(Integer.parseInt(collegeId));
			createFeedBack.setAdminId(Integer.parseInt(adminId));
			createFeedBack.setStaffId(Integer.parseInt(staffId));
			createFeedBack.setStudentId(Integer.parseInt(studentId));
			createFeedBack.setSubjectId(Integer.parseInt(subjectId));
			createFeedBack.setStudentName(studentName);
			createFeedBack.setStudentRoll(studentRoll);
			createFeedBack.setStaffName(staffName);
			createFeedBack.setSubjectCode(subjectCode);
			createFeedBack.setSubjectTitle(subjectTitle);
			createFeedBack.setBatch(batch);
			createFeedBack.setSemester(semester);
			createFeedBack.setDepartment(department);
			createFeedBack.setSection(section);
			createFeedBack.setFeed(feed);
			createFeedBack.setResult(result);
			
			studentService.createCollegeFeedBack(createFeedBack);
			
		}
		return "redirect:/createCollegeFeedback";
	}
	
	@RequestMapping("/createDepartmentFeedback")
	public String createDepartmentFeedback(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String collegeId=session.getAttribute("collegeId").toString();
			String adminId = session.getAttribute("adminId").toString();
			String department = session.getAttribute("department").toString();
			List<DepartmentFeedbackSet> departmentFeedsets = studentService.getDepartmentFeedbackSets(collegeId,adminId,department);
			theModel.addAttribute("departmentFeedsets",departmentFeedsets);
			return "student/studentDepartmentFeedBackPage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/DepartmentFeedSubmit")
	public String DepartmentFeedSubmit(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String feedSetId = request.getParameter("id");
			if(feedSetId!=null) {
				session.setAttribute("feedSetId", feedSetId);
			}
			else {
				feedSetId = session.getAttribute("feedSetId").toString();
			}
			
			String studentId = session.getAttribute("studentId").toString();
			List<DepartmentFeedbackResult> departmentFeedbackResults = studentService.getDepartmentFeedbackResults(feedSetId,studentId);
			String status = "ACTIVE";
			if(departmentFeedbackResults.size()!=0) {
				status = "INACTIVE";
			}
			List<DepartmentFeed> departmentFeeds = studentService.getDepartmentFeeds(feedSetId);
			theModel.addAttribute("status",status);
			theModel.addAttribute("departmentFeeds",departmentFeeds);
			theModel.addAttribute("feedSetList",new DepartmentFeedSetList());
			return "student/DepartmentFeedSubmitPage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("processSubmitDepartmentFeedback")
	public String processSubmitDepartmentFeedback(@ModelAttribute("feedSetList")DepartmentFeedSetList departmentFeedSetList) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		int i;
		String feedSetId = session.getAttribute("feedSetId").toString();
		String collegeId=session.getAttribute("collegeId").toString();
		String adminId = session.getAttribute("adminId").toString();
		String batch = session.getAttribute("batch").toString();
		String semester = session.getAttribute("semester").toString();
		String department = session.getAttribute("department").toString();
		String section = session.getAttribute("section").toString();
		String studentId = session.getAttribute("studentId").toString();
		String studentName =  session.getAttribute("firstName").toString()+" "+session.getAttribute("lastName").toString();
		String studentRoll = session.getAttribute("rollNo").toString();
		List<DepartmentFeedbackResult> departmentFeedbackResults = studentService.getDepartmentFeedbackResults(feedSetId,studentId);
		String status = "ACTIVE";
		if(departmentFeedbackResults.size()!=0) {
			status = "INACTIVE";
		}
		if(status.equals("ACTIVE")) {
			for(i=0;i<departmentFeedSetList.getFeed().length;i++) {
				DepartmentFeedbackResult departmentFeedbackResult = new DepartmentFeedbackResult();
				departmentFeedbackResult.setAdminId(Integer.parseInt(adminId));
				departmentFeedbackResult.setCollegeId(Integer.parseInt(collegeId));
				departmentFeedbackResult.setStudentId(Integer.parseInt(studentId));
				departmentFeedbackResult.setBatch(batch);
				departmentFeedbackResult.setSemester(semester);
				departmentFeedbackResult.setDepartment(department);
				departmentFeedbackResult.setSection(section);
				departmentFeedbackResult.setStudentName(studentName);
				departmentFeedbackResult.setRollno(studentRoll);
				departmentFeedbackResult.setFeedSetId(Integer.parseInt(feedSetId));
				departmentFeedbackResult.setFeed(departmentFeedSetList.getFeed()[i]);
				departmentFeedbackResult.setResult(departmentFeedSetList.getResult()[i]);
				studentService.saveDepartmentfeedBack(departmentFeedbackResult);
				
			}
			return "redirect:/DepartmentFeedSubmit";
		}
		else {
			return "redirect:/DepartmentFeedSubmit";
		}
		
	}
	@RequestMapping("/studentGroupResource")
	public String studentGroupResource(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = session.getAttribute("user").toString();
	String role = session.getAttribute("role").toString();
	if(user==null) {
		return "redirect:/";
	}
	if(user!=null && role.equals("Student")) {
		String studentId = session.getAttribute("studentId").toString();
		List<ResourceGroup> groupsets = studentService.getGroupsets(studentId);
		theModel.addAttribute("groupset",new ResourceGroup());
		theModel.addAttribute("groupsets",groupsets);
		return "student/studentResourceGroupPage";
	}
	else {
		return "redirect:/";
	}
	}
	
	@PostMapping("/processStudentGroupSetForm")
	public String processStudentGroupSetForm(@ModelAttribute("groupset")ResourceGroup groupset,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String studentId = session.getAttribute("studentId").toString();
		List<ResourceGroup> isgroupsets=studentService.isGroupCodeAvailable(groupset.getGroupCode());
		if(isgroupsets.size()!=0) {
			request.setAttribute("error","Group Already Exists !!!");
			List<ResourceGroup> groupsets = studentService.getGroupsets(studentId);
			theModel.addAttribute("groupset",new ResourceGroup());
			theModel.addAttribute("groupsets",groupsets);
			return "student/studentResourceGroupPage";
		}
		else {
			groupset.setStudentId(Integer.parseInt(studentId));
			studentService.createStudentResourceGroup(groupset);
			return "redirect:/studentGroupResource";
		}
	}
	
	@RequestMapping("/deleteStudentGroupSet")
	public String deleteStudentGroupSet(@RequestParam("groupId") int groupId) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		studentService.deleteGroup(groupId);
		return "redirect:/studentGroupResource";
	}
	
	@RequestMapping("/studentsAddResourcePage")
	public String studentsAddResourcePage(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			String studentId = session.getAttribute("studentId").toString();
			String groupCode=null;
			String groupId=null;
			if(request.getParameter("groupCode")!=null) {
				groupCode = request.getParameter("groupCode");
				groupId = request.getParameter("groupId").toString();
				session.setAttribute("groupCode",groupCode );
				session.setAttribute("groupId",groupId );
			}else {
				groupCode = session.getAttribute("groupCode").toString();
				groupId = session.getAttribute("groupId").toString();
			}
			List<GroupAttachment> attachments = studentService.getStudentAttachments(studentId,groupCode);
			theModel.addAttribute("groupAttachments",attachments);
			return "student/addStudentResourcePage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/downloadStudentResourceFile")
	public String downloadFile(@RequestParam("id") String id) throws ServletException, IOException {
		GroupAttachment attachment = studentService.getFile(id);
		request.setAttribute("attachment", attachment);
		RequestDispatcher rs = request.getRequestDispatcher("/downloadResourceAttachment");
		rs.forward(request, response);
		return "";
	}
	
	@RequestMapping("/deleteStudentResourceFile")
	public String deleteStudentResourceFile(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		studentService.deleteStudentResourceFile(id);
		return "redirect:/studentsAddResourcePage";
	}
	
	@RequestMapping("/editStudentGroupSet")
	public String editStudentGroupSet(Model theModel,@RequestParam("id")int id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String studentId = session.getAttribute("studentId").toString();
			ResourceGroup groupset = studentService.getSingleGroupsets(studentId,id);
			theModel.addAttribute("groupset",groupset);
			return "student/editStudentResourceGroup";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/attendanceStudentViewRecord")
	public String attendanceTutorViewRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Student")) {
			String batch=request.getParameter("batch");
			String semester=request.getParameter("semester");
			String department = session.getAttribute("department").toString();
			String section = request.getParameter("section");
			String subjectCode =  request.getParameter("subjectCode");
			String adminId = session.getAttribute("adminId").toString();
			String subjectTitle = request.getParameter("subjectTitle");
			String studentId = request.getParameter("studentId");
			String studentName = request.getParameter("studentName");
			String studentRoll = request.getParameter("studentRoll");
			List<AttendanceResult> presentDays = staffService.getPresentDays(adminId,batch,semester,department,section,subjectCode,studentId,"PRESENT");
			List<AttendanceResult> absentDays = staffService.getPresentDays(adminId,batch,semester,department,section,subjectCode,studentId,"ABSENT");
			
			float presentScore = (float)10;
			float absentSore = (float)5;
			int i=0;
			float allDay = staffService.getAttendanceAllValue(adminId,batch,semester,department,section,subjectCode,Integer.parseInt(studentId));
			float present = staffService.getAttendanceValue(adminId,batch,semester,department,section,subjectCode,Integer.parseInt(studentId),"PRESENT");
			float absent = staffService.getAttendanceValue(adminId,batch,semester,department,section,subjectCode,Integer.parseInt(studentId),"ABSENT");
			float full = allDay*presentScore;
			float cal = (present*presentScore)+(absent*absentSore);
			float res = (cal/full)*100;
			float overallPercentage=res;
			
			theModel.addAttribute("studentRoll",studentRoll);
			theModel.addAttribute("studentName",studentName);
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("section",section);
			theModel.addAttribute("subjectCode",subjectCode);
			theModel.addAttribute("subjectTitle",subjectTitle);
			theModel.addAttribute("presentDays",presentDays);
			theModel.addAttribute("absentDays",absentDays);
			theModel.addAttribute("overallPercentage",overallPercentage);
			

			
			return "student/attendanceStudentViewRecord";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/studentLogOut")
	public String studentLogOut() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		session.invalidate();
		return "redirect:/";
	}
	
}
