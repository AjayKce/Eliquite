package student.kce.erp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.kce.erp.model.Admin;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.Batch;
import student.kce.erp.model.CalculateDepartmentFeedPercentage;
import student.kce.erp.model.CollegeFeed;
import student.kce.erp.model.CreateFeedback;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.FeedBackAlloc;
import student.kce.erp.model.FeedBackSet;
import student.kce.erp.model.FullAttendanceResult;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentClass;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.service.AdminService;
import student.kce.erp.service.CollegeService;
import student.kce.erp.service.StaffService;
import student.kce.erp.service.SuperUserService;

@Controller
public class CollegeController {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@Autowired
	CollegeService collegeService;
	
	@Autowired
	AdminService adminService;

	@Autowired
	SuperUserService superUserService;
	
	@Autowired
	StaffService staffService;

	@RequestMapping("/collegeHome")
	public String collegeHome() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user != null && role.equals("SuperUser")) {
			request.setAttribute("user", session.getAttribute("user"));
			return "redirect:/";
		} else if (user != null && role.equals("College")) {
			request.setAttribute("user", session.getAttribute("user"));
			return "college/collegeHomePage";
		} else if (user != null && role.equals("Admin")) {
			return "redirect:/adminHome";
		} else if (user != null && role.equals("Staff")) {
			return "redirect:/staffHome";
		} else if (user != null && role.equals("Student")) {
			return "redirect:/studentHome";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/processCollege")
	public String processCollege(Model theModel) {
		if (session.getAttribute("user") != null) {
			return "redirect:/";
		}
		String college = request.getParameter("college");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (collegeService.isCollege(college, username, password)) {
			session.setAttribute("user", college);
			session.setAttribute("collegeId", (superUserService.getCollegeId(college, username, password)).toString());
			session.setAttribute("role", "College");
			request.setAttribute("user", session.getAttribute("user"));
			return "college/collegeHomePage";
		} else {
			theModel.addAttribute("colleges", superUserService.listColleges());
			request.setAttribute("invalidCollege", "invalid");
			return "college/collegeLoginPage";
		}
	}

	@RequestMapping("/addAdmin")
	public String addAdmin(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (((String) session.getAttribute("role")).equals("College")) {
			theModel.addAttribute("admin", new Admin());
			request.setAttribute("user", session.getAttribute("user"));
			return "college/createAdminForm";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processCreateAdminForm")
	public String processCreateAdminForm(@ModelAttribute("admin") Admin admin, Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		admin.setCollegeId(Integer.parseInt((String) session.getAttribute("collegeId")));
		if (collegeService.isAdminAvailable(admin.getCollegeId(), admin.getDepartment())) {
			request.setAttribute("invalidAdmin", "invalid");
			theModel.addAttribute("admin", new Admin());
			return "college/createAdminForm";
		} else {
			collegeService.addAdmin(admin);
			return "redirect:/manageAdmin";
		}
	}

	@RequestMapping("/manageAdmin")
	public String manageAdmin(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		String collegeId = (String) session.getAttribute("collegeId");
		if (user != null && role.equals("College")) {
			List<Admin> admins = collegeService.getAdmins(collegeId);
			theModel.addAttribute("admins", admins);
			request.setAttribute("user", user);
			return "college/manageAdminPage";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/editAdmin")
	public String editAdmin(@RequestParam("aid") String id, Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			theModel.addAttribute("admin", collegeService.getAdmin(id));
			request.setAttribute("user", user);
			return "college/editAdminForm";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processEditAdminForm")
	public String processEditAdminForm(@ModelAttribute("admin") Admin admin, Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		admin.setCollegeId(Integer.parseInt((String) session.getAttribute("collegeId")));
		collegeService.addAdmin(admin);
		return "redirect:/manageAdmin";
	}

	@RequestMapping("/deleteAdmin")
	public String deleteAdmin(@RequestParam("aid") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			Admin admin = collegeService.getAdmin(id);
			collegeService.deleteAdmin(admin);
			request.setAttribute("user", user);
			return "redirect:/manageAdmin";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/collegeFeedback")
	public String collegeFeedback(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		String collegeId = (String) session.getAttribute("collegeId");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			List<FeedBackSet> feedsets = collegeService.getFeedSets(collegeId);
			theModel.addAttribute("feedsets", feedsets);
			theModel.addAttribute("feedset", new FeedBackSet());
			return "college/addFeedBackSet";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processFeedSetForm")
	public String processFeedSetForm(@ModelAttribute("feedset") FeedBackSet theFeedSet) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String collegeId = (String) session.getAttribute("collegeId");
		String status = "INACTIVE";
		theFeedSet.setFeedSet(theFeedSet.getFeedSet().toUpperCase());
		if (collegeService.isFeedSetAvailable(collegeId, theFeedSet.getFeedSet())) {
			return "redirect:/collegeFeedback";
		} else {
			theFeedSet.setCollegeId(Integer.parseInt(collegeId));
			theFeedSet.setStatus(status);
			collegeService.addFeedSet(theFeedSet);
			return "redirect:/collegeFeedback";
		}
	}
	
	@RequestMapping("/startFeed")
	public String startFeed(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		FeedBackSet feedSet = collegeService.getSingleFeedSet(id);
		feedSet.setStatus("ACTIVE");
		collegeService.addFeedSet(feedSet);
		return "redirect:/collegeFeedback";
	}
	
	@RequestMapping("/stopFeed")
	public String stopFeed(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		FeedBackSet feedSet = collegeService.getSingleFeedSet(id);
		feedSet.setStatus("INACTIVE");
		collegeService.addFeedSet(feedSet);
		return "redirect:/collegeFeedback";
	}
	
	@RequestMapping("/deleteFeedSet")
	public String deleteFeedSet(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		FeedBackSet feedSet = collegeService.getSingleFeedSet(id);
		collegeService.deleteFeedSet(feedSet);
		return "redirect:/collegeFeedback";
	}
	
	@RequestMapping("/addFeed")
	public String addFeed(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			session.setAttribute("feedSetId", id);
			return "redirect:/addFeeds";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/addFeeds")
	public String addFeeds(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		String collegeId = (String) session.getAttribute("collegeId");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String feedsetId = session.getAttribute("feedSetId").toString();
			List<CollegeFeed> feeds = collegeService.getCollegeFeeds(feedsetId,collegeId);
			theModel.addAttribute("feeds",feeds);
			theModel.addAttribute("collegeFeed",new CollegeFeed());
			return "college/collegeFeedPage";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/processCollegeFeedForm")
	public String processCollegeFeedForm(@ModelAttribute("collegeFeed")CollegeFeed theCollegeFeed) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String collegeId = (String) session.getAttribute("collegeId");
		String feedsetId = session.getAttribute("feedSetId").toString();
		theCollegeFeed.setCollegeId(Integer.parseInt(collegeId));
		theCollegeFeed.setFeedsetId(Integer.parseInt(feedsetId));
		collegeService.createCollegeFeed(theCollegeFeed);
		return "redirect:/addFeeds";
	}
	
	@RequestMapping("/deleteCollegeFeed")
	public String deleteCollegeFeed(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		CollegeFeed singleCollegeFeed = collegeService.getSingleCollegeFeed(id);
		collegeService.deleteCollegeFeed(singleCollegeFeed);
		return "redirect:/addFeeds";
	}
	
	@RequestMapping("/setFeedback")
	public String setFeedback(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		String collegeId = (String) session.getAttribute("collegeId");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			List<FeedBackSet> feedBackSet = collegeService.getFeedSets(collegeId);
			List<FeedBackAlloc> feedBackAllocs = collegeService.getFeedBackAllocs(collegeId);
			theModel.addAttribute("feedBackAllocs",feedBackAllocs);
			theModel.addAttribute("feedbackSets",feedBackSet);
			theModel.addAttribute("feedAlloc",new FeedBackAlloc());
			return "college/setFeedBackPage";
		} else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/processSetFeedbackAlloc")
	public String processSetFeedbackAlloc(@ModelAttribute("feedAlloc")FeedBackAlloc theFeedBackAlloc) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String collegeId = (String) session.getAttribute("collegeId");
		FeedBackSet theFeedBackSet =collegeService.getSingleFeedSet(Integer.toString(theFeedBackAlloc.getFeedsetId()));
		theFeedBackAlloc.setCollegeId(Integer.parseInt(collegeId));
		theFeedBackAlloc.setFeedsetTitle(theFeedBackSet.getFeedSet());
		if(collegeService.isFeedAllocAvailable(collegeId,theFeedBackAlloc.getDepartment())) {
			return "redirect:/setFeedback";
		}
		else {
			collegeService.addFeedBackAlloc(theFeedBackAlloc);
			return "redirect:/setFeedback";
		}
	}
	
	@RequestMapping("/deleteFeedAlloc")
	public String deleteFeedAlloc(@RequestParam("id")String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		collegeService.deleteFeedAlloc(id);
		return "redirect:/setFeedback";
	}
	
	@RequestMapping("/BatchesForfeedBack")
	public String BatchesForfeedBack(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String department = request.getParameter("department");
			String collegeId = (String) session.getAttribute("collegeId");
			String adminId = collegeService.getAdminIdForFeedBack(collegeId,department);
			session.setAttribute("adminId",adminId);
			session.setAttribute("department",department);
			List<StudentClass> studentClasses = collegeService.getStudentClasses(adminId,department);
			theModel.addAttribute("studentClasses",studentClasses);
			return "college/batchesForfeedBackPage";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/subjectsHandledForFeedBack")
	public String subjectsHandledForFeedBack(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String adminId = session.getAttribute("adminId").toString();
			String batch = request.getParameter("batch");
			String semester = request.getParameter("semester");
			String section = request.getParameter("section");
			String department = session.getAttribute("department").toString();
			session.setAttribute("batch",batch);
			session.setAttribute("semester",semester);
			session.setAttribute("section",section);
			List<SubjectAlloc> subjectAllocs = collegeService.getSubjectAllocations(adminId,batch,semester,department,section);
			theModel.addAttribute("subjectAllocs",subjectAllocs);
			return "college/subjectsHandledForFeedBack";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/viewStudentsForFeedBack")
	public String viewStudentsForFeedBack(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String adminId = session.getAttribute("adminId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String section = session.getAttribute("section").toString();
			String department = session.getAttribute("department").toString();
			String subjectCode = request.getParameter("subjectCode");
			String staffName = request.getParameter("staffName");
			session.setAttribute("subjectCode",subjectCode);
			session.setAttribute("staffName",staffName);
			List<Enrollment> enrollments = collegeService.getStudentEnrollments(adminId,batch,semester,department,section,subjectCode);
			theModel.addAttribute("enrollments",enrollments);
			return "college/viewStudentsForFeedBack";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("viewFeedBackReport")
	public String viewFeedBackReport(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String collegeId = session.getAttribute("collegeId").toString();
			String adminId = session.getAttribute("adminId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String section = session.getAttribute("section").toString();
			String department = session.getAttribute("department").toString();
			String subjectCode = session.getAttribute("subjectCode").toString();
//			String staffName = session.getAttribute("staffName").toString();
			String studentName = request.getParameter("studentName");
			String studentId = request.getParameter("studentId");
			String studentRoll = request.getParameter("studentRoll");
			session.setAttribute("studentName",studentName);
			session.setAttribute("studentId",studentId);
			session.setAttribute("studentRoll",studentRoll);
			List<CreateFeedback> feeds = collegeService.getFeeds(collegeId,adminId,batch,semester,department,section,subjectCode,studentId);
			theModel.addAttribute("feeds",feeds);
			return "college/viewFeedBackReport";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/feedbackStaffs")
	public String feedbackStaffs(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String department = request.getParameter("department");
			session.setAttribute("department", department);
			String collegeId = session.getAttribute("collegeId").toString();
			String adminId = collegeService.getAdminIdForFeedBack(collegeId,department);
			session.setAttribute("adminId", adminId);
			String feedSetId = collegeService.getFeedBackAllocsSetId(collegeId,department);
			session.setAttribute("feedSetId", feedSetId);
			List<Staff> staffs = collegeService.getStaffs(adminId,department);
			theModel.addAttribute("staffs",staffs);
			return "college/feedbackStaffs";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/feedbackFullReportForStaff")
	public String feedbackFullReportForStaff() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String adminId = session.getAttribute("adminId").toString(); 
			String staffId = request.getParameter("staffId");
			String feedSetId  = session.getAttribute("feedSetId").toString();
			String collegeId = session.getAttribute("collegeId").toString();
			
			int i=0;
			float excellentScore = (float)15;
			float goodScore = (float)10;
			float moderateScore = (float)8;
			float intermediateScore = (float)5;
			float poorScore = (float)3;
			float veryPoorScore = (float)1;
			List<CollegeFeed> collegeFeed = collegeService.getCollegeFeeds(feedSetId, collegeId);
			List<CreateFeedback> fullFeeds = collegeService.getFullFeeds(adminId,staffId);
			int feedAcquired = fullFeeds.size();

			float excellentCount = (collegeService.getCollegeFeedPercentage(collegeId,staffId,adminId,"Excellent"));
			float goodCount = (collegeService.getCollegeFeedPercentage(collegeId,staffId,adminId, "Good"));
			float moderateCount = (collegeService.getCollegeFeedPercentage(collegeId,staffId,adminId, "Moderate"));
			float intermediateCount = (collegeService.getCollegeFeedPercentage(collegeId,staffId,adminId, "Intermediate"));
			float poorCount = (collegeService.getCollegeFeedPercentage(collegeId,staffId,adminId, "Poor"));
			float veryPoorCount = (collegeService.getCollegeFeedPercentage(collegeId,staffId,adminId, "Very Poor"));

			float excellentPercentage = (excellentCount / (float) feedAcquired) * 100;
			float goodPercentage = (goodCount / (float) feedAcquired) * 100;
			float moderatePercentage = (moderateCount / (float) feedAcquired) * 100;
			float intermediatePercentage = (intermediateCount / (float) feedAcquired) * 100;
			float poorPercentage = (poorCount / (float) feedAcquired) * 100;
			float veryPoorPercentage = (veryPoorCount / (float) feedAcquired) * 100;

			float totalMark = ((excellentCount * excellentScore) + (goodCount * goodScore) + (moderateCount * moderateScore)+ (intermediateCount * intermediateScore)+(poorCount * poorScore)+(veryPoorCount * veryPoorScore));
			totalMark = (totalMark/(float)feedAcquired)*((float)1/excellentScore)*(float)100;
			session.setAttribute("excellentPercentage", (int) excellentPercentage);
			session.setAttribute("goodPercentage", (int) goodPercentage);
			session.setAttribute("moderatePercentage", (int) moderatePercentage);
			session.setAttribute("intermediatePercentage", (int) intermediatePercentage);
			session.setAttribute("poorPercentage", (int) poorPercentage);
			session.setAttribute("veryPoorPercentage", (int) veryPoorPercentage);
			session.setAttribute("totalMark", (int)totalMark);
			
			CalculateDepartmentFeedPercentage eachfeedPercentage = new CalculateDepartmentFeedPercentage();
			String[] feeds= new String[collegeFeed.size()];
			int[] excellent= new int[collegeFeed.size()];
			int[] good= new int[collegeFeed.size()];;
			int[] moderate= new int[collegeFeed.size()];;
			int[] intermediate= new int[collegeFeed.size()];;
			int[] poor= new int[collegeFeed.size()];;
			int[] veryPoor= new int[collegeFeed.size()];;
			
			for(i=0;i<collegeFeed.size();i++) {
				String feed = collegeFeed.get(i).getFeed();
				feeds[i]=feed;
				
				List<CreateFeedback> eachDepartmentFeedbackResults = collegeService.getEachDepartmentFeedbackResults(collegeId,staffId,adminId,feed);
				feedAcquired = eachDepartmentFeedbackResults.size();

				excellentCount = (collegeService.getEachDepartmentFeedPercentage(collegeId,staffId,adminId,"Excellent",feed));
				goodCount = (collegeService.getEachDepartmentFeedPercentage(collegeId,staffId,adminId,"Good",feed));
				moderateCount = (collegeService.getEachDepartmentFeedPercentage(collegeId,staffId,adminId,"Moderate",feed));
				intermediateCount = (collegeService.getEachDepartmentFeedPercentage(collegeId,staffId,adminId,"Intermediate",feed));
				poorCount = (collegeService.getEachDepartmentFeedPercentage(collegeId,staffId,adminId,"Poor",feed));
				veryPoorCount = (collegeService.getEachDepartmentFeedPercentage(collegeId,staffId,adminId,"Very Poor",feed));

				excellentPercentage = (excellentCount / (float) feedAcquired) * 100;
				goodPercentage = (goodCount / (float) feedAcquired) * 100;
				moderatePercentage = (moderateCount / (float) feedAcquired) * 100;
				intermediatePercentage = (intermediateCount / (float) feedAcquired) * 100;
				poorPercentage = (poorCount / (float) feedAcquired) * 100;
				veryPoorPercentage = (veryPoorCount / (float) feedAcquired) * 100;

				totalMark = ((excellentCount * excellentScore) + (goodCount * goodScore) + (moderateCount * moderateScore)+ (intermediateCount * intermediateScore)+(poorCount * poorScore)+(veryPoorCount * veryPoorScore));
				totalMark = (totalMark/(float)feedAcquired)*((float)1/excellentScore)*(float)100;
				
				excellent[i]=(int)excellentPercentage;
				good[i]=(int) goodPercentage;
				moderate[i]=(int) moderatePercentage;
				intermediate[i]=(int) intermediatePercentage;
				poor[i]=(int) poorPercentage;
				veryPoor[i]=(int) veryPoorPercentage;			
			}
			
			eachfeedPercentage.setFeeds(feeds);
			eachfeedPercentage.setExcellent(excellent);
			eachfeedPercentage.setGood(good);
			eachfeedPercentage.setModerate(moderate);
			eachfeedPercentage.setIntermediate(intermediate);
			eachfeedPercentage.setPoor(poor);
			eachfeedPercentage.setVeryPoor(veryPoor);
			
			session.setAttribute("eachFeedPercentages", eachfeedPercentage);
			
			return "college/feedbackFullReportForStaff";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/Attendance")
	public String Attendance(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String dept[] = {"CSE","IT","EEE","ECE","ETE","CIVIL","MECH","AUTOMOBILE"};
			theModel.addAttribute("dept",dept);
			return "college/attandanceDepartmentPage";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/setAttendanceSet")
	public String setAttendanceSet(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String department = request.getParameter("department");
		String collegeId = session.getAttribute("collegeId").toString();
		if(department!=null) {
			session.setAttribute("department", department);
		}
		else {
			department  =session.getAttribute("department").toString();
		}
		String adminId = collegeService.getAdminIdForAttendance(collegeId, department);
		List<Batch> batches = collegeService.getBatches(adminId);
		List<AttendanceScheduler> schedules = collegeService.getSchedules(collegeId,department);
		theModel.addAttribute("batches",batches);
		theModel.addAttribute("schedules",schedules);
		theModel.addAttribute("schedule",new AttendanceScheduler());
		return "college/setAttendancePage";
	}
	
	@PostMapping("/processAttendanceSetPage")
	public String processAttendanceSetPage(@ModelAttribute("schedule")AttendanceScheduler schedule) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String department = session.getAttribute("department").toString();
		String collegeId = session.getAttribute("collegeId").toString();
		int schedulerId = collegeService.getAttendanceRegisteredId(collegeId,department,schedule.getBatch(),schedule.getSemester(),schedule.getHour());
		schedule.setDepartment(department);
		schedule.setCollegeId(Integer.parseInt(collegeId));
		schedule.setId(schedulerId);
		collegeService.saveAttendanceSet(schedule);
		return "redirect:/setAttendanceSet";
	}
	
	@RequestMapping("/deleteAttendanceSet")
	public String deleteAttendanceSet() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String schedulerId = request.getParameter("id");
		collegeService.deleteScheduler(schedulerId);
		return "redirect:/setAttendanceSet";
	}
	
	@RequestMapping("/fullAttendanceReportClass")
	public String fullAttendanceReportClass(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String department = request.getParameter("department");
			String collegeId = session.getAttribute("collegeId").toString();
			String adminId = collegeService.getAdminIdForAttendance(collegeId, department);
			List<StudentClass> classes = adminService.getDepartmentClasses(adminId);
			theModel.addAttribute("classes",classes);
			return "college/attandanceDepartmentClassPage";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/classAttendanceRecord")
	public String classAttendanceRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			String collegeId = session.getAttribute("collegeId").toString();
			String batch=request.getParameter("batch");
			String semester=request.getParameter("semester");
			String department=request.getParameter("department");
			String section=request.getParameter("section");
			String date = request.getParameter("date");
			String adminId = collegeService.getAdminIdForAttendance(collegeId, department);
			List<AttendanceScheduler> periods = collegeService.getAttendancePeriods(collegeId, department,batch);
			List<AttendanceResult> attendanceResult = new ArrayList<AttendanceResult>();
			List<Student> students = collegeService.getStudents(adminId, batch, semester, department, section);
			
			List<FullAttendanceResult> finalAttendance = new ArrayList<FullAttendanceResult>();
			for(Student tempStudent:students) {
				FullAttendanceResult singleAttendanceResult = new FullAttendanceResult();
				singleAttendanceResult.setAdminId(tempStudent.getAdminId());
				singleAttendanceResult.setCollegeId(tempStudent.getCollegeId());
				singleAttendanceResult.setStudentId(tempStudent.getId());
				singleAttendanceResult.setStudentName(tempStudent.getFirstname()+" "+tempStudent.getLastname());
				singleAttendanceResult.setStudentRoll(tempStudent.getRollno());
				singleAttendanceResult.setBatch(tempStudent.getBatch());
				singleAttendanceResult.setSemester(tempStudent.getSemester());
				singleAttendanceResult.setDepartment(tempStudent.getDepartment());
				singleAttendanceResult.setSection(tempStudent.getSection());
				singleAttendanceResult.setDate(date);
				
				int studentId=tempStudent.getId();
				int dummyAttendanceResultId[] = new int[periods.size()];
				int dummyStaffId[] = new int[periods.size()];
				int dummySubjectId[]=new int[periods.size()];
				String dummyStaffName[]= new String[periods.size()];
				String dummySubjectTitle[]= new String[periods.size()];
				String dummySubjectCode[]= new String[periods.size()];
				String dummyStartTime[]= new String[periods.size()];
				String dummyEndTime[]= new String[periods.size()];
				String dummyPeriod[] = new String[periods.size()];
				String dummystatus[] = new String[periods.size()];
				int i=0;
				for(AttendanceScheduler tempSchedule:periods) {
					dummyPeriod[i]=tempSchedule.getHour();
					AttendanceResult singleResult = collegeService.getSinglePeriodResult(collegeId,studentId,date,dummyPeriod[i]);
					if(singleResult.getId()!=0) {
						dummyAttendanceResultId[i] = singleResult.getId();
						dummyStaffId[i] = singleResult.getStaffId();
						dummySubjectId[i]=singleResult.getSubjectId();
						dummyStaffName[i]=singleResult.getStaffName();
						dummySubjectTitle[i]= singleResult.getSubjectTitle();
						dummySubjectCode[i]= singleResult.getSubjectCode();
						dummyStartTime[i]= singleResult.getStartTime();
						dummyEndTime[i]= singleResult.getEndTime();
						dummystatus[i]=singleResult.getStatus();
					}
					else {
						dummystatus[i]="NA";
					}
					i++;
				}
				singleAttendanceResult.setAttendanceResultId(dummyAttendanceResultId);
				singleAttendanceResult.setStaffId(dummyStaffId);
				singleAttendanceResult.setSubjectId(dummySubjectId);
				singleAttendanceResult.setStaffName(dummyStaffName);
				singleAttendanceResult.setSubjectTitle(dummySubjectTitle);
				singleAttendanceResult.setSubjectCode(dummySubjectCode);
				singleAttendanceResult.setStartTime(dummyStartTime);
				singleAttendanceResult.setEndTime(dummyEndTime);
				singleAttendanceResult.setPeriod(dummyPeriod);
				singleAttendanceResult.setStatus(dummystatus);
				
				finalAttendance.add(singleAttendanceResult);
			}
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("department",department);
			theModel.addAttribute("section",section);
			theModel.addAttribute("date",date);
			theModel.addAttribute("periods",periods);
			theModel.addAttribute("resultAttendance",finalAttendance);
			theModel.addAttribute("attendanceResult",new AttendanceResult());
			return "college/attendanceCollegeReport";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/changePresent")
	public String changePresent(Model theModel,@ModelAttribute("attendanceResult") AttendanceResult theAttendanceResult) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			theAttendanceResult.setStatus("ABSENT");
			staffService.addAttendanceResult(theAttendanceResult);
			String collegeId = Integer.toString(theAttendanceResult.getCollegeId());
			String batch=theAttendanceResult.getBatch();
			String semester=theAttendanceResult.getSemester();
			String department=theAttendanceResult.getDepartment();
			String section=theAttendanceResult.getSection();
			String date = theAttendanceResult.getDate();
			String adminId = Integer.toString(theAttendanceResult.getAdminId());
			List<AttendanceScheduler> periods = collegeService.getAttendancePeriods(collegeId, department,batch);
			List<AttendanceResult> attendanceResult = new ArrayList<AttendanceResult>();
			List<Student> students = collegeService.getStudents(adminId, batch, semester, department, section);
			
			List<FullAttendanceResult> finalAttendance = new ArrayList<FullAttendanceResult>();
			for(Student tempStudent:students) {
				FullAttendanceResult singleAttendanceResult = new FullAttendanceResult();
				singleAttendanceResult.setAdminId(tempStudent.getAdminId());
				singleAttendanceResult.setCollegeId(tempStudent.getCollegeId());
				singleAttendanceResult.setStudentId(tempStudent.getId());
				singleAttendanceResult.setStudentName(tempStudent.getFirstname()+" "+tempStudent.getLastname());
				singleAttendanceResult.setStudentRoll(tempStudent.getRollno());
				singleAttendanceResult.setBatch(tempStudent.getBatch());
				singleAttendanceResult.setSemester(tempStudent.getSemester());
				singleAttendanceResult.setDepartment(tempStudent.getDepartment());
				singleAttendanceResult.setSection(tempStudent.getSection());
				singleAttendanceResult.setDate(date);
				
				int studentId=tempStudent.getId();
				int dummyAttendanceResultId[] = new int[periods.size()];
				int dummyStaffId[] = new int[periods.size()];
				int dummySubjectId[]=new int[periods.size()];
				String dummyStaffName[]= new String[periods.size()];
				String dummySubjectTitle[]= new String[periods.size()];
				String dummySubjectCode[]= new String[periods.size()];
				String dummyStartTime[]= new String[periods.size()];
				String dummyEndTime[]= new String[periods.size()];
				String dummyPeriod[] = new String[periods.size()];
				String dummystatus[] = new String[periods.size()];
				int i=0;
				for(AttendanceScheduler tempSchedule:periods) {
					dummyPeriod[i]=tempSchedule.getHour();
					AttendanceResult singleResult = collegeService.getSinglePeriodResult(collegeId,studentId,date,dummyPeriod[i]);
					if(singleResult.getId()!=0) {
						dummyAttendanceResultId[i] = singleResult.getId();
						dummyStaffId[i] = singleResult.getStaffId();
						dummySubjectId[i]=singleResult.getSubjectId();
						dummyStaffName[i]=singleResult.getStaffName();
						dummySubjectTitle[i]= singleResult.getSubjectTitle();
						dummySubjectCode[i]= singleResult.getSubjectCode();
						dummyStartTime[i]= singleResult.getStartTime();
						dummyEndTime[i]= singleResult.getEndTime();
						dummystatus[i]=singleResult.getStatus();
					}
					else {
						dummystatus[i]="NA";
					}
					i++;
				}
				singleAttendanceResult.setAttendanceResultId(dummyAttendanceResultId);
				singleAttendanceResult.setStaffId(dummyStaffId);
				singleAttendanceResult.setSubjectId(dummySubjectId);
				singleAttendanceResult.setStaffName(dummyStaffName);
				singleAttendanceResult.setSubjectTitle(dummySubjectTitle);
				singleAttendanceResult.setSubjectCode(dummySubjectCode);
				singleAttendanceResult.setStartTime(dummyStartTime);
				singleAttendanceResult.setEndTime(dummyEndTime);
				singleAttendanceResult.setPeriod(dummyPeriod);
				singleAttendanceResult.setStatus(dummystatus);
				
				finalAttendance.add(singleAttendanceResult);
			}
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("department",department);
			theModel.addAttribute("section",section);
			theModel.addAttribute("date",date);
			theModel.addAttribute("periods",periods);
			theModel.addAttribute("resultAttendance",finalAttendance);
			theModel.addAttribute("attendanceResult",new AttendanceResult());
			return "college/attendanceCollegeReport";
		} else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/changeAbsent")
	public String changeAbsent(Model theModel,@ModelAttribute("attendanceResult") AttendanceResult theAttendanceResult) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("College")) {
			theAttendanceResult.setStatus("PRESENT");
			staffService.addAttendanceResult(theAttendanceResult);
			String collegeId = Integer.toString(theAttendanceResult.getCollegeId());
			String batch=theAttendanceResult.getBatch();
			String semester=theAttendanceResult.getSemester();
			String department=theAttendanceResult.getDepartment();
			String section=theAttendanceResult.getSection();
			String date = theAttendanceResult.getDate();
			String adminId = Integer.toString(theAttendanceResult.getAdminId());
			List<AttendanceScheduler> periods = collegeService.getAttendancePeriods(collegeId, department,batch);
			List<AttendanceResult> attendanceResult = new ArrayList<AttendanceResult>();
			List<Student> students = collegeService.getStudents(adminId, batch, semester, department, section);
			
			List<FullAttendanceResult> finalAttendance = new ArrayList<FullAttendanceResult>();
			for(Student tempStudent:students) {
				FullAttendanceResult singleAttendanceResult = new FullAttendanceResult();
				singleAttendanceResult.setAdminId(tempStudent.getAdminId());
				singleAttendanceResult.setCollegeId(tempStudent.getCollegeId());
				singleAttendanceResult.setStudentId(tempStudent.getId());
				singleAttendanceResult.setStudentName(tempStudent.getFirstname()+" "+tempStudent.getLastname());
				singleAttendanceResult.setStudentRoll(tempStudent.getRollno());
				singleAttendanceResult.setBatch(tempStudent.getBatch());
				singleAttendanceResult.setSemester(tempStudent.getSemester());
				singleAttendanceResult.setDepartment(tempStudent.getDepartment());
				singleAttendanceResult.setSection(tempStudent.getSection());
				singleAttendanceResult.setDate(date);
				
				int studentId=tempStudent.getId();
				int dummyAttendanceResultId[] = new int[periods.size()];
				int dummyStaffId[] = new int[periods.size()];
				int dummySubjectId[]=new int[periods.size()];
				String dummyStaffName[]= new String[periods.size()];
				String dummySubjectTitle[]= new String[periods.size()];
				String dummySubjectCode[]= new String[periods.size()];
				String dummyStartTime[]= new String[periods.size()];
				String dummyEndTime[]= new String[periods.size()];
				String dummyPeriod[] = new String[periods.size()];
				String dummystatus[] = new String[periods.size()];
				int i=0;
				for(AttendanceScheduler tempSchedule:periods) {
					dummyPeriod[i]=tempSchedule.getHour();
					AttendanceResult singleResult = collegeService.getSinglePeriodResult(collegeId,studentId,date,dummyPeriod[i]);
					if(singleResult.getId()!=0) {
						dummyAttendanceResultId[i] = singleResult.getId();
						dummyStaffId[i] = singleResult.getStaffId();
						dummySubjectId[i]=singleResult.getSubjectId();
						dummyStaffName[i]=singleResult.getStaffName();
						dummySubjectTitle[i]= singleResult.getSubjectTitle();
						dummySubjectCode[i]= singleResult.getSubjectCode();
						dummyStartTime[i]= singleResult.getStartTime();
						dummyEndTime[i]= singleResult.getEndTime();
						dummystatus[i]=singleResult.getStatus();
					}
					else {
						dummystatus[i]="NA";
					}
					i++;
				}
				singleAttendanceResult.setAttendanceResultId(dummyAttendanceResultId);
				singleAttendanceResult.setStaffId(dummyStaffId);
				singleAttendanceResult.setSubjectId(dummySubjectId);
				singleAttendanceResult.setStaffName(dummyStaffName);
				singleAttendanceResult.setSubjectTitle(dummySubjectTitle);
				singleAttendanceResult.setSubjectCode(dummySubjectCode);
				singleAttendanceResult.setStartTime(dummyStartTime);
				singleAttendanceResult.setEndTime(dummyEndTime);
				singleAttendanceResult.setPeriod(dummyPeriod);
				singleAttendanceResult.setStatus(dummystatus);
				
				finalAttendance.add(singleAttendanceResult);
			}
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("department",department);
			theModel.addAttribute("section",section);
			theModel.addAttribute("date",date);
			theModel.addAttribute("periods",periods);
			theModel.addAttribute("resultAttendance",finalAttendance);
			theModel.addAttribute("attendanceResult",new AttendanceResult());
			return "college/attendanceCollegeReport";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/collegeLogOut")
	public String collegeLogOut() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		session.invalidate();
		return "redirect:/";
	}

}
