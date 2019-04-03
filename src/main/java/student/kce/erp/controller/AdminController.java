package student.kce.erp.controller;

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
import student.kce.erp.model.Batch;
import student.kce.erp.model.CalculateDepartmentFeedPercentage;
import student.kce.erp.model.DepartmentFeed;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.DepartmentFeedbackSet;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentClass;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.Tutor;
import student.kce.erp.service.AdminService;
import student.kce.erp.service.StudentService;
import student.kce.erp.service.SuperUserService;

@Controller
public class AdminController {

	@Autowired
	SuperUserService superUserService;

	@Autowired
	AdminService adminService;

	@Autowired
	StudentService studentService;

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@RequestMapping("/adminHome")
	public String adminHome() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if (user != null && role.equals("SuperUser")) {
			return "redirect:/";
		} else if (user != null && role.equals("College")) {
			request.setAttribute("user", session.getAttribute("user"));
			return "redirect:/collegeHome";
		} else if (user != null && role.equals("Admin")) {
			request.setAttribute("user", session.getAttribute("user"));
			return "admin/adminHomePage";
		} else if (user != null && role.equals("Staff")) {
			return "redirect:/staffHome";
		} else if (user != null && role.equals("Student")) {
			return "redirect:/studentHome";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processAdminLogin")
	public String processAdminLogin(Model theModel) {
		String collegeId = request.getParameter("collegeId");
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		String dept = request.getParameter("department");
		if (adminService.isAdmin(collegeId, user, pass, dept)) {
			Admin admin = adminService.getAdmin(collegeId, user, pass, dept);
			session.setAttribute("collegeId", admin.getCollegeId());
			session.setAttribute("user", admin.getAdminName());
			session.setAttribute("userId", admin.getId());
			session.setAttribute("department", dept);
			session.setAttribute("role", "Admin");
			request.setAttribute("user", session.getAttribute("user"));
			return "redirect:/adminHome";
		} else {
			theModel.addAttribute("colleges", superUserService.listColleges());
			request.setAttribute("invalidAdmin", "invalid");
			return "admin/adminLoginPage";
		}
	}

	@RequestMapping("/addBatch")
	public String addBatch(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("role").equals("Admin")) {
			request.setAttribute("user", session.getAttribute("user"));
			theModel.addAttribute("batches", adminService.getBatches(session.getAttribute("userId").toString()));
			theModel.addAttribute("batch", new Batch());
			return "admin/addBatchForm";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/processAddBatchForm")
	public String processAddBatchForm(@ModelAttribute("batch") Batch theBatch) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		List<Batch> batches = adminService.getBatches(session.getAttribute("userId").toString());
		int adminId = (int) session.getAttribute("userId");
		theBatch.setAdminId(adminId);

		// for duplicate year
		for (Batch temp : batches) {
			if (temp.getYear().equals(theBatch.getYear())) {
				return "redirect:/addBatch";
			}
		}
		adminService.addBatch(theBatch);
		return "redirect:/addBatch";
	}

	@RequestMapping("/deleteBatch")
	public String deleteBatch(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String adminId = (session.getAttribute("userId")).toString();
		Batch batch = adminService.getBatch(id, adminId);
		if (batch != null) {
			adminService.deleteBatch(batch);
		}
		return "redirect:/addBatch";
	}

	@RequestMapping("/addClass")
	public String addClass(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String adminId = (session.getAttribute("userId")).toString();
			List<StudentClass> classes = adminService.getDepartmentClasses(adminId);
			request.setAttribute("user", session.getAttribute("user"));
			List<Batch> batches = adminService.getBatches(session.getAttribute("userId").toString());
			StudentClass studentClass = new StudentClass();
			studentClass.setAdminId(Integer.parseInt(adminId));
			theModel.addAttribute("batches", batches);
			theModel.addAttribute("studentClass", studentClass);
			theModel.addAttribute("classes", classes);
			theModel.addAttribute("dept", session.getAttribute("department"));
			return "admin/addClassForm";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/processAddClassForm")
	public String processAddClassForm(@ModelAttribute("studentClass") StudentClass theClass) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (adminService.isClassAvailable(theClass.getAdminId(), theClass.getBatch(), theClass.getSection(),
				theClass.getSemester())) {
			return "redirect:/addClass";
		} else {
			adminService.addStudentClass(theClass);
			return "redirect:/addClass";
		}
	}

	@RequestMapping("/deleteClass")
	public String deleteClass(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String adminId = (session.getAttribute("userId")).toString();
		StudentClass singleClass = adminService.getSingleClass(id, adminId);
		if (singleClass != null) {
			adminService.deleteClass(singleClass);
		}
		return "redirect:/addClass";
	}

	@RequestMapping("/addStaff")
	public String addStaff(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String adminId = session.getAttribute("userId").toString();
			List<Staff> staffs = adminService.getAllStaffs(adminId);
			Staff staff = new Staff();
			staff.setAdminId(Integer.parseInt(adminId));
			staff.setDepartment(session.getAttribute("department").toString());
			theModel.addAttribute("staffs", staffs);
			theModel.addAttribute("staff", staff);
			request.setAttribute("user", session.getAttribute("user"));
			return "admin/addStaffForm";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processAddStaffForm")
	public String processAddStaffForm(@ModelAttribute("staff") Staff staff) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		staff.setStaffName(staff.getStaffName().toUpperCase());
		adminService.createStaff(staff);
		return "redirect:/addStaff";
	}

	@RequestMapping("/editStaff")
	public String editStaff(@RequestParam("id") String id, Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String adminId = session.getAttribute("userId").toString();
			Staff staff = adminService.getSingleStaff(adminId, id);
			theModel.addAttribute("staff", staff);
			request.setAttribute("user", session.getAttribute("user"));
			return "admin/editStaffForm";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/deleteStaff")
	public String deleteStaff(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String adminId = session.getAttribute("userId").toString();
			Staff staff = adminService.getSingleStaff(adminId, id);
			adminService.deleteStaff(staff);
			return "redirect:/addStaff";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/addSubject")
	public String addSubject(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String adminId = session.getAttribute("userId").toString();
			List<Batch> batches = adminService.getBatches(adminId);
			List<DepartmentSubject> subjects = adminService.getSubjects(adminId);
			DepartmentSubject subject = new DepartmentSubject();
			subject.setAdminId(Integer.parseInt(adminId));
			subject.setDepartment(session.getAttribute("department").toString());
			theModel.addAttribute("batches", batches);
			theModel.addAttribute("subjects", subjects);
			theModel.addAttribute("subject", subject);
			request.setAttribute("user", session.getAttribute("user"));
			return "admin/addSubjectForm";

		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processAddSubjectForm")
	public String processAddSubjectForm(@ModelAttribute("subject") DepartmentSubject theSubject) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		theSubject.setSubjectCode(theSubject.getSubjectCode().toUpperCase());
		theSubject.setSubjectTitle(theSubject.getSubjectTitle().toUpperCase());
		if (adminService.isSubjectAvailable(theSubject.getBatch(), theSubject.getSemester(), theSubject.getAdminId(),
				theSubject.getSubjectCode())) {
			return "redirect:/addSubject";
		} else {
			adminService.addSubject(theSubject);
			return "redirect:/addSubject";
		}
	}

	@RequestMapping("/addExam")
	public String addExam(@RequestParam("id") String id, Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			session.setAttribute("subjectId", id);
			String adminId = session.getAttribute("userId").toString();
			SetExam examset = new SetExam();
			examset.setSubjectId(Integer.parseInt(id));
			examset.setAdminId(Integer.parseInt(adminId));
			request.setAttribute("user", session.getAttribute("user"));
			List<SetExam> examsets = adminService.getExamSets(adminId, id);
			theModel.addAttribute("examsets", examsets);
			theModel.addAttribute("examset", examset);
			return "admin/addExamForm";

		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/addExams")
	public String addExam(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String subid = session.getAttribute("subjectId").toString();
			String adminId = session.getAttribute("userId").toString();
			SetExam examset = new SetExam();
			examset.setSubjectId(Integer.parseInt(subid));
			examset.setAdminId(Integer.parseInt(adminId));
			request.setAttribute("user", session.getAttribute("user"));
			List<SetExam> examsets = adminService.getExamSets(adminId, subid);
			theModel.addAttribute("examsets", examsets);
			theModel.addAttribute("examset", examset);
			return "admin/addExamForm";

		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/processAddExamForm")
	public String processAddExamForm(@ModelAttribute("examset") SetExam examset) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		examset.setExamTitle(examset.getExamTitle().toUpperCase());
		adminService.addExamSet(examset);
		return "redirect:/addExams";
	}

	@RequestMapping("/deleteExamSet")
	public String deleteExamSet(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			SetExam singleExamSet = adminService.getSingleExamSet(Integer.parseInt(id));
			adminService.deleteExamSet(singleExamSet);
			return "redirect:/addExams";

		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/deleteSubject")
	public String deleteSubject(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			DepartmentSubject singleSubject = adminService.getSingleSubject(Integer.parseInt(id));
			adminService.deleteSubject(singleSubject);
			return "redirect:/addSubject";

		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/setTutor")
	public String setTutor(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String adminId = session.getAttribute("userId").toString();
			List<Tutor> tutors = adminService.getTutors(adminId);
			List<Staff> staffs = adminService.getAllStaffs(adminId);
			List<Batch> batches = adminService.getBatches(adminId);
			Tutor tutor = new Tutor();
			tutor.setAdminId(Integer.parseInt(adminId));
			tutor.setDepartment(session.getAttribute("department").toString());
			theModel.addAttribute("tutors", tutors);
			theModel.addAttribute("staffs", staffs);
			theModel.addAttribute("batches", batches);
			theModel.addAttribute("tutor", tutor);
			request.setAttribute("user", session.getAttribute("user").toString());
			return "admin/setTutorForm";

		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processAddTutorForm")
	public String processAddTutorForm(@ModelAttribute("tutor") Tutor theTutor) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		adminService.createTutor(theTutor);
		return "redirect:/setTutor";
	}

	@RequestMapping("/deleteTutor")
	public String deleteTutor(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			Tutor tutor = adminService.getSingleTutor(id);
			adminService.deleteTutor(tutor);
			return "redirect:/setTutor";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/subjectAllocation")
	public String subjectAllocation(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			String adminId = session.getAttribute("userId").toString();
			List<Staff> staffs = adminService.getAllStaffs(adminId);
			List<Batch> batches = adminService.getBatches(adminId);
			List<DepartmentSubject> subjects = adminService.getSubjects(adminId);
			List<SubjectAlloc> subjectAllocs = adminService.getAllSubjectAlloc(adminId);
			SubjectAlloc subjectAlloc = new SubjectAlloc();
			subjectAlloc.setAdminId(Integer.parseInt(adminId));
			subjectAlloc.setDepartment(session.getAttribute("department").toString());
			theModel.addAttribute("staffs", staffs);
			theModel.addAttribute("batches", batches);
			theModel.addAttribute("subjects", subjects);
			theModel.addAttribute("subjectAllocs", subjectAllocs);
			theModel.addAttribute("subjectAlloc", subjectAlloc);
			request.setAttribute("user", session.getAttribute("user").toString());
			return "admin/addSubjectAllocationForm";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/processAddSubjectAllocationForm")
	public String processAddSubjectAllocationForm(@ModelAttribute("subjectAlloc") SubjectAlloc subjectAlloc) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			adminService.createSubjectAllocation(subjectAlloc);
			return "redirect:/subjectAllocation";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/deleteSubjectAlloc")
	public String deleteSubjectAlloc(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		if (session.getAttribute("user") != null && session.getAttribute("role").equals("Admin")) {
			SubjectAlloc subjectAlloc = adminService.getSingleSubjectAlloc(id);
			adminService.deleteSubjectAlloc(subjectAlloc);
			return "redirect:/subjectAllocation";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping("/departmentFeedback")
	public String departmentFeedback(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		String collegeId = session.getAttribute("collegeId").toString();
		String adminId = session.getAttribute("userId").toString();
		if (user == null) {
			return "redirect:/";
		}
		if (role.equals("Admin")) {
			List<DepartmentFeedbackSet> departmentFeedbackSets = adminService.getDepartmentFeedSets(collegeId, adminId);
			theModel.addAttribute("departmentFeedsets", departmentFeedbackSets);
			theModel.addAttribute("departmentFeedset", new DepartmentFeedbackSet());
			return "admin/addDepartmentFeedBackSet";
		} else {
			return "redirect:/";
		}
	}

	@PostMapping("/processDepartmentFeedSetForm")
	public String processDepartmentFeedSetForm(	@ModelAttribute("departmentFeedset") DepartmentFeedbackSet departmentFeedbackSet) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		int adminId = Integer.parseInt(session.getAttribute("userId").toString());
		int collegeId = Integer.parseInt(session.getAttribute("collegeId").toString());
		String department = session.getAttribute("department").toString();
		departmentFeedbackSet.setFeedSet(departmentFeedbackSet.getFeedSet().toUpperCase());
		departmentFeedbackSet.setAdminId(adminId);
		departmentFeedbackSet.setCollegeId(collegeId);
		departmentFeedbackSet.setDepartment(department);
		adminService.saveDepartmentFeedbackSet(departmentFeedbackSet);
		return "redirect:/departmentFeedback";
	}

	@PostMapping("/deleteDepartmentFeedSet")
	public String deleteDepartmentFeedSet() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String departmentFeedSetId = request.getParameter("id");
		adminService.deleteDepartmentFeedSet(departmentFeedSetId);
		return "redirect:/departmentFeedback";
	}

	@RequestMapping("/addDepartmentFeed")
	public String addDepartmentFeed(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String departmentFeedSetId = request.getParameter("id");
		String adminId = session.getAttribute("userId").toString();
		if (departmentFeedSetId != null) {
			session.setAttribute("departmentFeedSetId", departmentFeedSetId);
		} else {
			departmentFeedSetId = session.getAttribute("departmentFeedSetId").toString();
		}

		List<DepartmentFeed> departmentFeeds = adminService.getDepartmentFeeds(departmentFeedSetId, adminId);
		theModel.addAttribute("feeds", departmentFeeds);
		theModel.addAttribute("departmentFeed", new DepartmentFeed());
		return "admin/departmentFeedPage";
	}

	@PostMapping("/processDepartmentFeedForm")
	public String processDepartmentFeedForm(@ModelAttribute("departmentFeed") DepartmentFeed departmentFeed) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		int adminId = Integer.parseInt(session.getAttribute("userId").toString());
		String departmentFeedSetId = session.getAttribute("departmentFeedSetId").toString();
		departmentFeed.setAdminId(adminId);
		departmentFeed.setFeedSetId(Integer.parseInt(departmentFeedSetId));
		adminService.saveDepartmentFeed(departmentFeed);
		return "redirect:/addDepartmentFeed";
	}

	@PostMapping("/deleteDepartmentFeed")
	public String deleteDepartmentFeed() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String departmentFeedId = request.getParameter("id");
		adminService.deleteDepartmentFeed(departmentFeedId);
		return "redirect:/addDepartmentFeed";
	}

	@RequestMapping("/DepartmentFeedReportBatch")
	public String DepartmentFeedReportBatch(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String feedSetId = request.getParameter("id");
		session.setAttribute("feedSetId", feedSetId);
		String adminId = session.getAttribute("userId").toString();
		List<Batch> batches = adminService.getBatches(adminId);
		theModel.addAttribute("batches", batches);
		return "admin/DepartmentFeedReportBatch";
	}

	@PostMapping("/getDepartmentFeedBackStudents")
	public String getDepartmentFeedBackStudents(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String adminId = session.getAttribute("userId").toString();
		String batch = request.getParameter("batch");
		String semester = request.getParameter("semester");
		String department = request.getParameter("department");
		String section = request.getParameter("section");
		List<Student> students = adminService.getStudents(adminId, batch, semester, department, section);
		List<Batch> batches = adminService.getBatches(adminId);
		theModel.addAttribute("batches", batches);
		theModel.addAttribute("students", students);
		return "admin/DepartmentFeedBackStudents";
	}

	@RequestMapping("/viewDepartmentFeedBackReport")
	public String viewDepartmentFeedBackReport(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String studentId = request.getParameter("studentId");
		String feedSetId = session.getAttribute("feedSetId").toString();
		List<DepartmentFeedbackResult> departmentFeedbackResults = studentService
				.getDepartmentFeedbackResults(feedSetId, studentId);
		theModel.addAttribute("feedResult", departmentFeedbackResults);
		return "admin/viewDepartmentFeedBackReport";
	}

	@RequestMapping("/DepartmentFeedFullReport")
	public String DepartmentFeedFullReport(Model model) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		int i=0;
		float excellentScore = (float)15;
		float goodScore = (float)10;
		float moderateScore = (float)8;
		float intermediateScore = (float)5;
		float poorScore = (float)3;
		float veryPoorScore = (float)1;
		String feedSetId = request.getParameter("id");
		String adminId = session.getAttribute("userId").toString();
		List<DepartmentFeed> departmentFeed = adminService.getDepartmentFeeds(feedSetId, adminId);
		List<DepartmentFeedbackResult> departmentFeedbackResults = adminService.getDepartmentFeedbackResults(feedSetId,adminId);
		int feedAcquired = departmentFeedbackResults.size();

		float excellentCount = (adminService.getDepartmentFeedPercentage(adminId, feedSetId, "Excellent"));
		float goodCount = (adminService.getDepartmentFeedPercentage(adminId, feedSetId, "Good"));
		float moderateCount = (adminService.getDepartmentFeedPercentage(adminId, feedSetId, "Moderate"));
		float intermediateCount = (adminService.getDepartmentFeedPercentage(adminId, feedSetId, "Intermediate"));
		float poorCount = (adminService.getDepartmentFeedPercentage(adminId, feedSetId, "Poor"));
		float veryPoorCount = (adminService.getDepartmentFeedPercentage(adminId, feedSetId, "Very Poor"));

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
		String[] feeds= new String[departmentFeed.size()];
		int[] excellent= new int[departmentFeed.size()];
		int[] good= new int[departmentFeed.size()];;
		int[] moderate= new int[departmentFeed.size()];;
		int[] intermediate= new int[departmentFeed.size()];;
		int[] poor= new int[departmentFeed.size()];;
		int[] veryPoor= new int[departmentFeed.size()];;
		
		for(i=0;i<departmentFeed.size();i++) {
			String feed = departmentFeed.get(i).getFeed();
			feeds[i]=feed;
			
			List<DepartmentFeedbackResult> eachDepartmentFeedbackResults = adminService.getEachDepartmentFeedbackResults(feedSetId,adminId,feed);
			feedAcquired = eachDepartmentFeedbackResults.size();

			excellentCount = (adminService.getEachDepartmentFeedPercentage(adminId, feedSetId, "Excellent",feed));
			goodCount = (adminService.getEachDepartmentFeedPercentage(adminId, feedSetId, "Good",feed));
			moderateCount = (adminService.getEachDepartmentFeedPercentage(adminId, feedSetId, "Moderate",feed));
			intermediateCount = (adminService.getEachDepartmentFeedPercentage(adminId, feedSetId, "Intermediate",feed));
			poorCount = (adminService.getEachDepartmentFeedPercentage(adminId, feedSetId, "Poor",feed));
			veryPoorCount = (adminService.getEachDepartmentFeedPercentage(adminId, feedSetId, "Very Poor",feed));

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
		
		return "admin/DepartmentFeedFullReport";
	}

	@RequestMapping("/AdminLogOut")
	public String AdminLogOut() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		session.invalidate();
		return "redirect:/";
	}

}
