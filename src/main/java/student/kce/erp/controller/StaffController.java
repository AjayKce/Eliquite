package student.kce.erp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import student.kce.erp.model.Attachment;
import student.kce.erp.model.AttendanceList;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentMark;
import student.kce.erp.model.StudentMarkList;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.TempArray;
import student.kce.erp.model.Tutor;
import student.kce.erp.service.AdminService;
import student.kce.erp.service.StaffService;
import student.kce.erp.service.StudentService;
import student.kce.erp.service.SuperUserService;

@Controller
public class StaffController {

	@Autowired
	HttpSession session;
	
	@Autowired
	HttpServletRequest request;
	
	@Autowired
	HttpServletResponse response;
	
	@Autowired
	SuperUserService superUserService;
	
	@Autowired
	StaffService staffService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/staffHome")
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
			return "staff/staffHomePage";
		}
		else if(user!=null && role.equals("Student")) {
			return "redirect:/studentHome";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/processStaffLogin")
	public String processStaffLogin(Model theModel) {
		String collegeId =request.getParameter("collegeId");
		String department = request.getParameter("department");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String adminId = staffService.getAdminId(collegeId,department);
		if(staffService.isAuthenticated(adminId,department,username,password)) {
			session.setAttribute("user",staffService.getStaffName(adminId,department,username,password));
			session.setAttribute("department",department);
			session.setAttribute("collegeId", collegeId);
			session.setAttribute("adminId", adminId);
			session.setAttribute("staffId", staffService.getStaffId(adminId,department,username,password));
			session.setAttribute("role","Staff");
			request.setAttribute("user",staffService.getStaffName(adminId,department,username,password));
			return "redirect:/staffHome";
		}
		else {
		theModel.addAttribute("colleges",superUserService.listColleges());
		request.setAttribute("invalidStaff", "invalid");
		return "staff/staffLoginPage";
		}
	}
	
	@RequestMapping("/staffClass")
	public String staffClass(Model theModel){
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String staffName = session.getAttribute("user").toString();
			String department = session.getAttribute("department").toString();
			List<Tutor> tutors = staffService.getTutors(adminId,staffName,department);
			theModel.addAttribute("tutors",tutors);
			return "staff/staffClass";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/addStudent")
	public String addStudent(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			request.setAttribute("batch",request.getParameter("batch"));
			request.setAttribute("semester",request.getParameter("semester"));
			request.setAttribute("section",request.getParameter("section"));
			Student student = new Student();
			student.setCollegeId(Integer.parseInt(session.getAttribute("collegeId").toString()));
			student.setAdminId(Integer.parseInt(session.getAttribute("adminId").toString()));
			student.setStaffId(Integer.parseInt(session.getAttribute("staffId").toString()));
			theModel.addAttribute("student",student);
			return "staff/addStudentForm";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/processAddStudentForm")
	public String processAddStudentForm(@ModelAttribute("student") Student theStudent) {
		if(session.getAttribute("user")==null) {
			return "redirect:/";
		}
		theStudent.setRollno(theStudent.getRollno().toUpperCase());
		theStudent.setFirstname(theStudent.getFirstname().toUpperCase());
		theStudent.setLastname(theStudent.getLastname().toUpperCase());
		theStudent.setEmail(theStudent.getEmail().toLowerCase());
		theStudent.setNationality(theStudent.getNationality().toUpperCase());
		theStudent.setFatherName(theStudent.getFatherName().toUpperCase());
		theStudent.setMotherName(theStudent.getMotherName().toUpperCase());
		theStudent.setCustodianName(theStudent.getCustodianName().toUpperCase());
		theStudent.setAccountHolder(theStudent.getAccountHolder().toUpperCase());
		theStudent.setState(theStudent.getState().toUpperCase());
		theStudent.setDistrict(theStudent.getDistrict().toUpperCase());
		staffService.createStudent(theStudent);
		return "redirect:/viewStudents?batch="+theStudent.getBatch()+"&semester="+theStudent.getSemester()+"&section="+theStudent.getSection()+"";
	}
	
	@RequestMapping("/viewStudents")
	public String viewStudents(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String batch = request.getParameter("batch");
			String semester = request.getParameter("semester");
			String section = request.getParameter("section");
			String collegeId = session.getAttribute("collegeId").toString();
			String adminId = session.getAttribute("adminId").toString();
			String staffId = session.getAttribute("staffId").toString();
			String department = session.getAttribute("department").toString();
			session.setAttribute("batch",batch);
			session.setAttribute("semester",semester);
			session.setAttribute("section",section);
			List<Student> students = staffService.getStudents(batch,semester,section,collegeId,adminId,staffId,department);
			theModel.addAttribute("students",students);
			return "staff/studentsViewPage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/viewStudentDetails")
	public String viewStudentDetails(@RequestParam("id") String id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			int collegeId = Integer.parseInt(session.getAttribute("collegeId").toString());
			int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
			int staffId = Integer.parseInt(session.getAttribute("staffId").toString());
			Student student = staffService.getSingleStudent(Integer.parseInt(id));
			if(student.getCollegeId()==collegeId && student.getAdminId()==adminId && student.getStaffId()==staffId) {
				theModel.addAttribute("student",student);
				return "staff/viewStudentDetail";
			}
			else {
				return "redirect:/";
			}
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/editStudent")
	public String editStudent(@RequestParam("id") String id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			int collegeId = Integer.parseInt(session.getAttribute("collegeId").toString());
			int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
			int staffId = Integer.parseInt(session.getAttribute("staffId").toString());
			Student student = staffService.getSingleStudent(Integer.parseInt(id));
			if(student.getCollegeId()==collegeId && student.getAdminId()==adminId && student.getStaffId()==staffId) {
				request.setAttribute("batch",student.getBatch());
				request.setAttribute("semester",student.getSemester());
				request.setAttribute("section",student.getSection());
				theModel.addAttribute("student",student);
				return "staff/editStudentForm";
			}
			else {
				return "redirect:/";
			}
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/deleteStudent")
	public String deleteStudent(@RequestParam("id") String id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			int collegeId = Integer.parseInt(session.getAttribute("collegeId").toString());
			int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
			int staffId = Integer.parseInt(session.getAttribute("staffId").toString());
			Student student = staffService.getSingleStudent(Integer.parseInt(id));
			if(student.getCollegeId()==collegeId && student.getAdminId()==adminId && student.getStaffId()==staffId) {
				theModel.addAttribute("student",student);
				return "staff/confirmDeleteStudent";
			}
			else {
				return "redirect:/";
			}
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/confirmDeleteStudent")
	public String confirmDeleteStudent(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			int collegeId = Integer.parseInt(session.getAttribute("collegeId").toString());
			int adminId = Integer.parseInt(session.getAttribute("adminId").toString());
			int staffId = Integer.parseInt(session.getAttribute("staffId").toString());
			Student student = staffService.getSingleStudent(Integer.parseInt(id));
			if(student.getCollegeId()==collegeId && student.getAdminId()==adminId && student.getStaffId()==staffId) {
				staffService.deleteStudent(student);
				return "redirect:/viewStudents?batch="+student.getBatch()+"&semester="+student.getSemester()+"&section="+student.getSection()+"";
			}
			else {
				return "redirect:/";
			}
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/viewStudentEnrollment")
	public String viewStudentEnrollment() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String studentId = request.getParameter("sid");
			session.setAttribute("studentId", studentId);
			return "redirect:/viewStudentEnrollmentDetail";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/viewStudentEnrollmentDetail")
	public String viewStudentEnrollmentDetail(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			int HS = 0;
			int BS = 0;
			int PC = 0;
			int PE = 0;
			int OE = 0;
			int ES = 0;
			int EEC = 0;
			int credits = 0;
			String studentId = session.getAttribute("studentId").toString();
			Student student = staffService.getSingleStudent(Integer.parseInt(studentId));
			List<Enrollment> enrollments = studentService.getEnrollments(studentId);
			for(Enrollment temp:enrollments) {
				credits+=Integer.parseInt(temp.getCredit());
				if(temp.getSubjectGroup().equals("HS")) {
					HS+=1;
				}
				if(temp.getSubjectGroup().equals("BS")) {
					BS+=1;
				}
				if(temp.getSubjectGroup().equals("PC")) {
					PC+=1;
				}
				if(temp.getSubjectGroup().equals("PE")) {
					PE+=1;
				}
				if(temp.getSubjectGroup().equals("OE")) {
					OE+=1;
				}
				if(temp.getSubjectGroup().equals("ES")) {
					ES+=1;
				}
				if(temp.getSubjectGroup().equals("EEC")) {
					EEC+=1;
				}
			}
			theModel.addAttribute("credit",credits);
			theModel.addAttribute("HS",HS);
			theModel.addAttribute("BS",BS);
			theModel.addAttribute("PC",PC);
			theModel.addAttribute("PE",PE);
			theModel.addAttribute("OE",OE);
			theModel.addAttribute("ES",ES);
			theModel.addAttribute("EEC",EEC);
			theModel.addAttribute("student",student);
			theModel.addAttribute("enrollments",enrollments);
			return "staff/viewStudentEnrollment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/tutorManageMark")
	public String tutorManageMark(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String subjectId = request.getParameter("subjectId");
			String studentId = request.getParameter("studentId");
			String studentRoll = request.getParameter("studentRoll");
			String subjectCode = request.getParameter("subjectCode");
			session.setAttribute("subjectId", subjectId);
			session.setAttribute("studentId", studentId);
			session.setAttribute("studentRoll", studentRoll);
			session.setAttribute("subjectCode", subjectCode);
			
			String adminId = session.getAttribute("adminId").toString();
			String staffId = session.getAttribute("staffId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			
			List<StudentMark> studentMarks = staffService.getStudentMarks(adminId,staffId,studentId,batch,semester,department,section,studentRoll,subjectCode);
			
			theModel.addAttribute("studentMarks",studentMarks);
			return "staff/viewStudentMarks";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/deleteStudentEnrollment")
	public String deleteStudentEnrollment(@RequestParam("eid") Integer enrollId ) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			Enrollment singleEnrollment = staffService.getSingleEnrollment(enrollId);
			staffService.deleteEnrollment(singleEnrollment);
			return "redirect:/viewStudentEnrollmentDetail";
		}
		else if(user!=null && role.equals("Student")) {
			Enrollment singleEnrollment = staffService.getSingleEnrollment(enrollId);
			staffService.deleteEnrollment(singleEnrollment);
			return "redirect:/studentEnrollment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/subjectsHandled")
	public String subjectsHandled(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String staffName = session.getAttribute("user").toString();
			String department = session.getAttribute("department").toString();
			List<SubjectAlloc> subjectsHandled = staffService.getSubjectAllocations(adminId,staffName,department);
			theModel.addAttribute("subjectsHandled",subjectsHandled);
			return "staff/subjectsHandled";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/manageSubject")
	public String manageSubject(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String department = session.getAttribute("department").toString();
			String batch = request.getParameter("batch");
			String semester = request.getParameter("semester");
			String section = request.getParameter("section");
			String subjectCode = request.getParameter("subjectCode");
			session.setAttribute("batch",batch );
			session.setAttribute("semester",semester );
			session.setAttribute("section",section );
			session.setAttribute("subjectCode",subjectCode );
			List<Enrollment> enrolledStudents = staffService.getEnrolledStudents(adminId,batch,semester,department,section,subjectCode);
			theModel.addAttribute("enrolledStudents",enrolledStudents);
			
			return "staff/manageSubject";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/manageMark")
	public String manageMark(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String subjectId = request.getParameter("subjectId");
			String studentId = request.getParameter("studentId");
			String studentRoll = request.getParameter("studentRoll");
			session.setAttribute("subjectId", subjectId);
			session.setAttribute("studentId", studentId);
			session.setAttribute("studentRoll", studentRoll);
			return "redirect:/manageStudentMark";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/manageStudentMark")
	public String manageStudentMark(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String staffId = session.getAttribute("staffId").toString();
			String studentId = session.getAttribute("studentId").toString();
			String subjectId = session.getAttribute("subjectId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			String subjectCode = session.getAttribute("subjectCode").toString();
			String studentRoll = session.getAttribute("studentRoll").toString();
			
			List<StudentMark> studentMarks = staffService.getStudentMarks(adminId,staffId,studentId,batch,semester,department,section,studentRoll,subjectCode);
			List<SetExam> setExams = staffService.getSetExams(adminId,subjectId);
			
			theModel.addAttribute("studentMarks",studentMarks);
			theModel.addAttribute("studentMark",new StudentMark());
			theModel.addAttribute("setExams",setExams);
			return "staff/manageStudentMark";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/processStudentMarkForm")
	public String processStudentMarkForm(@ModelAttribute("studentMark")StudentMark studentMark) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String staffId = session.getAttribute("staffId").toString();
			String studentId = session.getAttribute("studentId").toString();
			String subjectId = session.getAttribute("subjectId").toString();
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			String subjectCode = session.getAttribute("subjectCode").toString();
			String studentRoll = session.getAttribute("studentRoll").toString();
			String status = "";
			String examTitle = studentMark.getExamTitle();
			
			SetExam setSingleExam = staffService.getSingleSetExams(adminId,subjectId,examTitle);
			String fullMark = setSingleExam.getFullmark();
			String passMark = setSingleExam.getPassmark();
			String obtainedMark = studentMark.getObtainedMark();
			
			if(Integer.parseInt(obtainedMark)>=Integer.parseInt(passMark) && Integer.parseInt(obtainedMark)<=Integer.parseInt(fullMark)) {
				status = "PASS";
			}
			else {
				status = "FAIL";
			}
			
			StudentMark singleStudentMark = staffService.getSingleStudentMark(adminId,staffId,studentId,batch,semester,department,section,studentRoll,subjectCode,examTitle);
			
			if(singleStudentMark.getId()!=0) {
				singleStudentMark.setObtainedMark(obtainedMark);
				singleStudentMark.setStatus(status);
				staffService.saveStudentMark(singleStudentMark);
				return "redirect:/manageStudentMark";
			}
			else {
				singleStudentMark.setAdminId(Integer.parseInt(adminId));
				singleStudentMark.setBatch(batch);
				singleStudentMark.setDepartment(department);
				singleStudentMark.setExamTitle(examTitle);
				singleStudentMark.setFullMark(fullMark);
				singleStudentMark.setObtainedMark(obtainedMark);
				singleStudentMark.setSection(section);
				singleStudentMark.setSemester(semester);
				singleStudentMark.setStaffId(Integer.parseInt(staffId));
				singleStudentMark.setStatus(status);
				singleStudentMark.setStudentId(Integer.parseInt(studentId));
				singleStudentMark.setStudentRoll(studentRoll);
				singleStudentMark.setSubjectCode(subjectCode);
				staffService.saveStudentMark(singleStudentMark);
				return "redirect:/manageStudentMark";
			}
			
		}
		else {
			return "redirect:/";
		}
	}
	
	
	@PostMapping("/markList")
	public String markList(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String department = session.getAttribute("department").toString();
			String batch = request.getParameter("batch");
			String semester = request.getParameter("semester");
			String section = request.getParameter("section");
			String subjectCode = request.getParameter("subjectCode");
			session.setAttribute("batch",batch );
			session.setAttribute("semester",semester );
			session.setAttribute("section",section );
			session.setAttribute("subjectCode",subjectCode );
			List<Enrollment> enrolledStudents = staffService.getEnrolledStudents(adminId,batch,semester,department,section,subjectCode);
			String subjectId = staffService.getSubjectId(adminId,batch,semester,department,subjectCode);
			List<SetExam> setExams = staffService.getSetExams(adminId, subjectId);
			theModel.addAttribute("enrolledStudents",enrolledStudents);
			theModel.addAttribute("setExams",setExams);
			theModel.addAttribute("studentMarkList",new StudentMarkList());
			return "staff/studentMarkList";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/getStudentListMark")
	public String getStudentListMark(@ModelAttribute("studentMarkList") StudentMarkList theList) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
			int i;
			for(i=0;i<theList.getObtainedMark().length;i++) {
				String adminId = session.getAttribute("adminId").toString();
				String staffId = session.getAttribute("staffId").toString();
				String studentId = theList.getStudentId()[i];
				String subjectId = theList.getSubjectId()[i];
				String batch = session.getAttribute("batch").toString();
				String semester = session.getAttribute("semester").toString();
				String department = session.getAttribute("department").toString();
				String section = session.getAttribute("section").toString();
				String subjectCode = theList.getSubjectCode()[i];
				String studentRoll = theList.getStudentRoll()[i];
				String status = "";
				String examTitle = theList.getExamTitle();
				SetExam setSingleExam = staffService.getSingleSetExams(adminId,subjectId,examTitle);
				String fullMark = setSingleExam.getFullmark();
				String passMark = setSingleExam.getPassmark();
				String obtainedMark = theList.getObtainedMark()[i];
				
				if(Integer.parseInt(obtainedMark)>=Integer.parseInt(passMark) && Integer.parseInt(obtainedMark)<=Integer.parseInt(fullMark)) {
					status = "PASS";
				}
				else {
					status = "FAIL";
				}
				
				StudentMark singleStudentMark = staffService.getSingleStudentMark(adminId,staffId,studentId,batch,semester,department,section,studentRoll,subjectCode,examTitle);
			
				if(singleStudentMark.getId()!=0) {
					singleStudentMark.setObtainedMark(obtainedMark);
					singleStudentMark.setStatus(status);
					staffService.saveStudentMark(singleStudentMark);
				}
				else {
					singleStudentMark.setAdminId(Integer.parseInt(adminId));
					singleStudentMark.setBatch(batch);
					singleStudentMark.setDepartment(department);
					singleStudentMark.setExamTitle(examTitle);
					singleStudentMark.setFullMark(fullMark);
					singleStudentMark.setObtainedMark(obtainedMark);
					singleStudentMark.setSection(section);
					singleStudentMark.setSemester(semester);
					singleStudentMark.setStaffId(Integer.parseInt(staffId));
					singleStudentMark.setStatus(status);
					singleStudentMark.setStudentId(Integer.parseInt(studentId));
					singleStudentMark.setStudentRoll(studentRoll);
					singleStudentMark.setSubjectCode(subjectCode);
					staffService.saveStudentMark(singleStudentMark);
				}
			}
			return "redirect:/subjectsHandled";
		
	}
	
	
	
	
	@RequestMapping("/addResource")
	public String addResource(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		String batch = null;
		String semester = null;
		String section = null;
		String subjectCode = null;
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String department = session.getAttribute("department").toString();
			if(request.getParameter("batch")!=null) {
				batch = request.getParameter("batch");
				semester = request.getParameter("semester");
				section = request.getParameter("section");
				subjectCode = request.getParameter("subjectCode");
			session.setAttribute("batch",batch );
			session.setAttribute("semester",semester );
			session.setAttribute("section",section );
			session.setAttribute("subjectCode",subjectCode);
			}else {
				 batch = session.getAttribute("batch").toString();
				 semester = session.getAttribute("semester").toString();
				 section = session.getAttribute("section").toString();
				 subjectCode = session.getAttribute("subjectCode").toString();
			}
			List<Attachment> attachments = staffService.getAttachments(adminId,batch,semester,department,section,subjectCode);
			theModel.addAttribute("attachments",attachments);
			return "staff/addResourcePage";
		}
		else {
			return "redirect:/";
		}
	}
	
	
	@RequestMapping("/downloadFile")
	public String downloadFile(@RequestParam("id") String id) throws ServletException, IOException {
		Attachment attachment = staffService.getFile(id);
		request.setAttribute("attachment", attachment);
		RequestDispatcher rs = request.getRequestDispatcher("/downloadAttachment");
		rs.forward(request, response);
		return "redirect:/addResource";
	}
	
	@RequestMapping("/deleteFile")
	public String deleteFile(@RequestParam("id") String id) throws ServletException, IOException {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		staffService.deleteFile(id);
		return "redirect:/addResource";
	}
	
	@PostMapping("/stopEnrollment")
	public String stopEnrollment(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String tutorId = id;
		Tutor tutor = adminService.getSingleTutor(tutorId);
		tutor.setStatus("INACTIVE");
		adminService.createTutor(tutor);
		return "redirect:/staffClass";
	}
	
	@PostMapping("/startEnrollment")
	public String startEnrollment(@RequestParam("id") String id) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String Tutorid = id;
		Tutor tutor = adminService.getSingleTutor(Tutorid);
		tutor.setStatus("ACTIVE");
		adminService.createTutor(tutor);
		return "redirect:/staffClass";
	}
	
	@RequestMapping("/staffAttendancePage")
	public String staffAttendancePage(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String adminId = session.getAttribute("adminId").toString();
			String staffName = session.getAttribute("user").toString();
			String department = session.getAttribute("department").toString();
			List<SubjectAlloc> subjectsHandled = staffService.getSubjectAllocations(adminId,staffName,department);
			theModel.addAttribute("subjectsHandled",subjectsHandled);
			return "staff/staffAttendancePage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/attendanceList")
	public String attendanceList(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String batch=null;
			String semester=null;
			String section = null;
			String subjectCode = null;
			if(request.getParameter("message")!=null) {
			request.setAttribute("message",request.getParameter("message"));
			}
			String adminId = session.getAttribute("adminId").toString();
			String collegeId = session.getAttribute("collegeId").toString();
			String department = session.getAttribute("department").toString();
			batch = request.getParameter("batch");
			semester = request.getParameter("semester");
			section = request.getParameter("section");
			subjectCode = request.getParameter("subjectCode");
			if(batch!=null) {
			session.setAttribute("batch",batch );
			session.setAttribute("semester",semester );
			session.setAttribute("section",section );
			session.setAttribute("subjectCode",subjectCode );
			}
			else {
				batch = session.getAttribute("batch").toString();
				semester = session.getAttribute("semester").toString();
				section = session.getAttribute("section").toString();
				subjectCode = session.getAttribute("subjectCode").toString();
			}
			List<Enrollment> enrolledStudents = staffService.getEnrolledStudents(adminId,batch,semester,department,section,subjectCode);
			String subjectId = staffService.getSubjectId(adminId,batch,semester,department,subjectCode);
			List<AttendanceScheduler> schedules = staffService.getSchedules(collegeId,batch,semester,department);
			theModel.addAttribute("schedules",schedules);
			theModel.addAttribute("enrolledStudents",enrolledStudents);
			theModel.addAttribute("attendanceList",new AttendanceList());
			return "staff/staffAttendanceList";
		}
		else {
			return "redirect:/";
		}
	}
	
	@PostMapping("/processStaffAttendanceList")
	public String processStaffAttendanceList(Model theModel,@ModelAttribute("attendanceList")AttendanceList attendanceList) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		int i=0;
		for(i=0;i<attendanceList.getStudentId().length;i++) {
			String adminId = session.getAttribute("adminId").toString();
			String collegeId = session.getAttribute("collegeId").toString();
			String staffId = session.getAttribute("staffId").toString();
			String subjectId=attendanceList.getSubjectId()[i];
			String studentId=attendanceList.getStudentId()[i];
			String batch=session.getAttribute("batch").toString();
			String semester=session.getAttribute("semester").toString();
			String department=session.getAttribute("department").toString();
			String section=session.getAttribute("section").toString();
			String studentName=attendanceList.getStudentName()[i];
			String rollno=attendanceList.getRollno()[i];
			DepartmentSubject sub = adminService.getSingleSubject(Integer.parseInt(subjectId));
			String subjectTitle = sub.getSubjectTitle();
			String subjectCode = sub.getSubjectCode();
			String staffName=session.getAttribute("user").toString();
			String date=attendanceList.getDate();
			String period=attendanceList.getPeriod();
			List<AttendanceScheduler> schedules = staffService.getSchedules(collegeId,batch,semester,department);
			AttendanceScheduler singleSchedule=null;
			for(AttendanceScheduler temp:schedules) {
				if((temp.getHour()).equals(period)) {
					singleSchedule=temp;
					break;
				}
			}
			String startTime=singleSchedule.getStartTime();
			String endTime=singleSchedule.getEndTime();
			String status=attendanceList.getResult()[i];
			AttendanceResult attendanceResult = staffService.getNewAttendanceResult(date,batch,semester,department,period,studentId);
			if(attendanceResult.getId()==0) {
				attendanceResult.setAdminId(Integer.parseInt(adminId));
				attendanceResult.setCollegeId(Integer.parseInt(collegeId));
				attendanceResult.setStaffId(Integer.parseInt(staffId));
				attendanceResult.setSubjectId(Integer.parseInt(subjectId));
				attendanceResult.setStudentId(Integer.parseInt(studentId));
				attendanceResult.setBatch(batch);
				attendanceResult.setSemester(semester);
				attendanceResult.setDepartment(department);
				attendanceResult.setSection(section);
				attendanceResult.setStudentName(studentName);
				attendanceResult.setRollno(rollno);
				attendanceResult.setSubjectTitle(subjectTitle);
				attendanceResult.setSubjectCode(subjectCode);
				attendanceResult.setStaffName(staffName);
				attendanceResult.setDate(date);
				attendanceResult.setPeriod(period);
				attendanceResult.setStartTime(startTime);
				attendanceResult.setEndTime(endTime);
				attendanceResult.setStatus(status);
				staffService.addAttendanceResult(attendanceResult);
			}
		}
		return "redirect:/attendanceList?message=Successfully Submitted";
	}
	
	@RequestMapping("/staffAttendanceRecord")
	public String staffAttendanceRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String date = request.getParameter("date");
			String batch=request.getParameter("batch");
			String semester=request.getParameter("semester");
			String department = session.getAttribute("department").toString();
			String section = request.getParameter("section");
			String subjectCode =  request.getParameter("subjectCode");
			String adminId = session.getAttribute("adminId").toString();
			String subjectId = staffService.getSubjectId(adminId,batch,semester,department,subjectCode);
			String staffId = session.getAttribute("staffId").toString();
			String[] periods = staffService.getSubjectPeriods(date,adminId,subjectId,staffId,batch,semester,department,section);
			theModel.addAttribute("periods",periods);
			theModel.addAttribute("date",date);
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("section",section);
			theModel.addAttribute("subjectCode",subjectCode);
			theModel.addAttribute("subjectId",subjectId);
			return "staff/staffAttendanceRecord";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/showStaffAttendanceRecord")
	public String showStaffAttendanceRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String date = request.getParameter("date");
			System.out.println(date);
			String batch=request.getParameter("batch");
			String semester=request.getParameter("semester");
			String department = session.getAttribute("department").toString();
			String section = request.getParameter("section");
			String subjectCode =  request.getParameter("subjectCode");
			String adminId = session.getAttribute("adminId").toString();
			String subjectId = staffService.getSubjectId(adminId,batch,semester,department,subjectCode);
			String period = request.getParameter("period");
			String staffId = session.getAttribute("staffId").toString();
			String[] periods = staffService.getSubjectPeriods(date,adminId,subjectId,staffId,batch,semester,department,section);
			List<AttendanceResult> attendanceResults = staffService.getAttendanceResult(date,adminId,subjectId,staffId,batch,semester,department,section,period);
			theModel.addAttribute("date",date);
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("section",section);
			theModel.addAttribute("subjectCode",subjectCode);
			theModel.addAttribute("subjectId",subjectId);
			theModel.addAttribute("periods",periods);
			theModel.addAttribute("attendanceResults",attendanceResults);
			return "staff/showStaffAttendanceRecord";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/staffPercentageAttendanceRecord")
	public String staffPercentageAttendanceRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String batch=request.getParameter("batch");
			String semester=request.getParameter("semester");
			String department = session.getAttribute("department").toString();
			String section = request.getParameter("section");
			String subjectCode =  request.getParameter("subjectCode");
			String adminId = session.getAttribute("adminId").toString();
			String subjectId = staffService.getSubjectId(adminId,batch,semester,department,subjectCode);
			DepartmentSubject sub = adminService.getSingleSubject(Integer.parseInt(subjectId));
			String subjectTitle = sub.getSubjectTitle();
			List<Enrollment> enrolledStudents = staffService.getEnrolledStudents(adminId,batch,semester,department,section,subjectCode);
			float presentScore = (float)10;
			float absentSore = (float)5;
			float[] percentages = new float[enrolledStudents.size()];
			int i=0;
			for(Enrollment temp:enrolledStudents) {
				float allDay = staffService.getAttendanceAllValue(adminId,batch,semester,department,section,subjectCode,temp.getStudentId());
				float present = staffService.getAttendanceValue(adminId,batch,semester,department,section,subjectCode,temp.getStudentId(),"PRESENT");
				float absent = staffService.getAttendanceValue(adminId,batch,semester,department,section,subjectCode,temp.getStudentId(),"ABSENT");
				float full = allDay*presentScore;
				float cal = (present*presentScore)+(absent*absentSore);
				float res = (cal/full)*100;
				percentages[i]=res;
				i++;
			}
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("section",section);
			theModel.addAttribute("subjectCode",subjectCode);
			theModel.addAttribute("subjectTitle",subjectTitle);
			theModel.addAttribute("percentages",percentages);
			theModel.addAttribute("enrolledStudents",enrolledStudents);
			return "staff/staffPercentageAttendanceRecord";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/attendanceViewRecord")
	public String attendanceViewRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
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
			theModel.addAttribute("studentRoll",studentRoll);
			theModel.addAttribute("studentName",studentName);
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("section",section);
			theModel.addAttribute("subjectCode",subjectCode);
			theModel.addAttribute("subjectTitle",subjectTitle);
			theModel.addAttribute("presentDays",presentDays);
			theModel.addAttribute("absentDays",absentDays);
			return "staff/attendanceViewRecord";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/attendanceTutorViewRecord")
	public String attendanceTutorViewRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
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
			return "staff/attendanceTutorViewRecord";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/attendanceTutorStudentViewRecord")
	public String attendanceTutorStudentViewRecord(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
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
			

			
			return "staff/attendanceStudentViewRecord";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/getStudentExcel")
	public String getStudentExcel(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			
			String batch = request.getParameter("batch");
			String semester = request.getParameter("semester");
			String section = request.getParameter("section");
			String collegeId = session.getAttribute("collegeId").toString();
			String adminId = session.getAttribute("adminId").toString();
			String staffId = session.getAttribute("staffId").toString();
			String department = session.getAttribute("department").toString();
			
			File file = new File("src/main/resources/static/files/"+adminId+staffId+department+collegeId+".xls");
			file.delete();
			session.setAttribute("batch",batch);
			session.setAttribute("semester",semester);
			session.setAttribute("section",section);
	
			Workbook wb = new HSSFWorkbook();
	        try  (OutputStream fileOut = new FileOutputStream(file)) {  
	            Sheet sheet1 = wb.createSheet("student Sheet");  
	    		HSSFSheet excelSheet = (HSSFSheet) sheet1;
	    		setExcelHeader(excelSheet);
	    		List<Student> students = staffService.getStudents(batch,semester,section,collegeId,adminId,staffId,department);
	    		theModel.addAttribute("students",students);
	    		setExcelRows(excelSheet,students);    		
	            wb.write(fileOut);
	        }catch(Exception e) {  
	            System.out.println(e.getMessage());  
	        } 
			
			String staffName = session.getAttribute("user").toString();
			List<Tutor> tutors = staffService.getTutors(adminId,staffName,department);
			theModel.addAttribute("tutors",tutors);
			request.setAttribute("linkReady", "ready");
			return "staff/staffClass";
		}
		else {
			return "redirect:/";
		}
	}
	
	private void setExcelRows(HSSFSheet excelSheet, List<Student> students) {
		int record = 1;
		for (Student temp : students) {
			HSSFRow excelRow = excelSheet.createRow(record++);
			excelRow.createCell(0).setCellValue(temp.getBatch());
			excelRow.createCell(1).setCellValue(temp.getSemester());
			excelRow.createCell(2).setCellValue(temp.getDepartment()+" "+temp.getSection());
			excelRow.createCell(3).setCellValue(temp.getRollno());
			excelRow.createCell(4).setCellValue(temp.getFirstname()+" "+temp.getLastname());
			excelRow.createCell(5).setCellValue(temp.getEmail());
			excelRow.createCell(6).setCellValue(temp.getDateOfBirth());
			excelRow.createCell(7).setCellValue(temp.getGender());
			excelRow.createCell(8).setCellValue(temp.getPhone());
			excelRow.createCell(9).setCellValue(temp.getReligion());
			excelRow.createCell(10).setCellValue(temp.getBloodgroup());
			excelRow.createCell(11).setCellValue(temp.getNationality());
			excelRow.createCell(12).setCellValue(temp.getAccomodation());
			excelRow.createCell(13).setCellValue(temp.getQuota());
			excelRow.createCell(14).setCellValue(temp.getFatherName());
			excelRow.createCell(15).setCellValue(temp.getMotherName());
			excelRow.createCell(16).setCellValue(temp.getCustodianName());
			excelRow.createCell(17).setCellValue(temp.getFatherPhone());
			excelRow.createCell(18).setCellValue(temp.getMotherPhone());
			excelRow.createCell(19).setCellValue(temp.getCustodianPhone());
			excelRow.createCell(20).setCellValue(temp.getAadharNumber());
			excelRow.createCell(21).setCellValue(temp.getAccountNumber());
			excelRow.createCell(22).setCellValue(temp.getIfscCode());
			excelRow.createCell(23).setCellValue(temp.getAccountHolder());
			excelRow.createCell(24).setCellValue(temp.getPancardNumber());
			excelRow.createCell(25).setCellValue(temp.getRegisteredPhone());
			excelRow.createCell(26).setCellValue(temp.getWhatsappAccount());
			excelRow.createCell(27).setCellValue(temp.getFacebookAccount());
			excelRow.createCell(28).setCellValue(temp.getLinkedinAccount());
			excelRow.createCell(29).setCellValue(temp.getGithubAccount());
			excelRow.createCell(30).setCellValue(temp.getHackerrankAccount());
			excelRow.createCell(31).setCellValue(temp.getHackerearthAccount());
			excelRow.createCell(32).setCellValue(temp.getCodechefAccount());
			excelRow.createCell(33).setCellValue(temp.getInstagramAccount());
			excelRow.createCell(34).setCellValue(temp.getTwitterAccount());
			excelRow.createCell(35).setCellValue(temp.getState());
			excelRow.createCell(36).setCellValue(temp.getDistrict());
			excelRow.createCell(37).setCellValue(temp.getPermanentAddress1()+" "+temp.getPermanentAddress2()+" "+temp.getPermanentAddress3());
		}
	}

	private void setExcelHeader(HSSFSheet excelSheet) {
		HSSFRow excelHeader = excelSheet.createRow(0);
		excelHeader.createCell(0).setCellValue("Batch");
		excelHeader.createCell(1).setCellValue("Semester");
		excelHeader.createCell(2).setCellValue("Class");
		excelHeader.createCell(3).setCellValue("Roll Number");
		excelHeader.createCell(4).setCellValue("Name");
		excelHeader.createCell(5).setCellValue("Email");
		excelHeader.createCell(6).setCellValue("D.O.B");
		excelHeader.createCell(7).setCellValue("Gender");
		excelHeader.createCell(8).setCellValue("phone");
		excelHeader.createCell(9).setCellValue("Religion");
		excelHeader.createCell(10).setCellValue("Blood Group");
		excelHeader.createCell(11).setCellValue("Nationality");
		excelHeader.createCell(12).setCellValue("Accomadation");
		excelHeader.createCell(13).setCellValue("Quota");
		excelHeader.createCell(14).setCellValue("Fathers Name");
		excelHeader.createCell(15).setCellValue("Mothers Name");
		excelHeader.createCell(16).setCellValue("Gaurdians Name");
		excelHeader.createCell(17).setCellValue("Fathers Phone");
		excelHeader.createCell(18).setCellValue("Mothers Phone");
		excelHeader.createCell(19).setCellValue("Gaurdians Phone");
		excelHeader.createCell(20).setCellValue("Aadhar Number");
		excelHeader.createCell(21).setCellValue("Bank Account Number");
		excelHeader.createCell(22).setCellValue("Ifsc Code");
		excelHeader.createCell(23).setCellValue("Account Holder Name");
		excelHeader.createCell(24).setCellValue("Pancard Number");
		excelHeader.createCell(25).setCellValue("Account Registered Number");
		excelHeader.createCell(26).setCellValue("Whatsapp Number");
		excelHeader.createCell(27).setCellValue("Facebook Account");
		excelHeader.createCell(28).setCellValue("LinkedIn Account");
		excelHeader.createCell(29).setCellValue("Github Account");
		excelHeader.createCell(30).setCellValue("Hackerrank Account");
		excelHeader.createCell(31).setCellValue("Hackerearth Account");
		excelHeader.createCell(32).setCellValue("Codechef Account");
		excelHeader.createCell(33).setCellValue("Instagram Account");
		excelHeader.createCell(34).setCellValue("Twitter Account");
		excelHeader.createCell(35).setCellValue("State");
		excelHeader.createCell(36).setCellValue("District");
		excelHeader.createCell(37).setCellValue("Address");
	}
	
	@RequestMapping("/staffFilterStudents")
	public String staffFilterStudents(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String batch=request.getParameter("batch");
			String semester=request.getParameter("semester");
			String department = request.getParameter("department");
			String section = request.getParameter("section");
			TempArray tempArray = new TempArray();
			theModel.addAttribute("temp",tempArray);
			theModel.addAttribute("batch",batch);
			theModel.addAttribute("semester",semester);
			theModel.addAttribute("department",department);
			theModel.addAttribute("section",section);
			return "staff/staffFilterStudents";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/filterStudentResult")
	public String filterStudentResult(@ModelAttribute("temp")TempArray temparr,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String[] values = temparr.getValues();
		String batch = temparr.getBatch();
		String semester = temparr.getSemester();
		String section = temparr.getSection();
		String department = temparr.getDepartment();
		String collegeId = session.getAttribute("collegeId").toString();
		String adminId = session.getAttribute("adminId").toString();
		String staffId = session.getAttribute("staffId").toString();
		
		File file = new File("src/main/resources/static/files/"+adminId+staffId+department+collegeId+".xls");
		file.delete();
		
		Workbook wb = new HSSFWorkbook();
        try  (OutputStream fileOut = new FileOutputStream(file)) {  
            Sheet sheet1 = wb.createSheet("student Sheet");  
    		HSSFSheet excelSheet = (HSSFSheet) sheet1;
    		HSSFRow excelHeader = excelSheet.createRow(0);
    		excelHeader.createCell(0).setCellValue("Batch");
    		excelHeader.createCell(1).setCellValue("Semester");
    		excelHeader.createCell(2).setCellValue("Class");
    		excelHeader.createCell(3).setCellValue("Roll Number");
    		excelHeader.createCell(4).setCellValue("Name");
    		int j=5;
    		if(Arrays.asList(values).contains("email")) {
    			excelHeader.createCell(j++).setCellValue("Email");
    		}
    		if(Arrays.asList(values).contains("dob")) {
    			excelHeader.createCell(j++).setCellValue("D.O.B");
    		}
    		if(Arrays.asList(values).contains("gender")) {
    		excelHeader.createCell(j++).setCellValue("Gender");
    		}
    		if(Arrays.asList(values).contains("phone")) {
    		excelHeader.createCell(j++).setCellValue("phone");
    		}
    		if(Arrays.asList(values).contains("religion")) {
    		excelHeader.createCell(j++).setCellValue("Religion");
    		}
    		if(Arrays.asList(values).contains("bloodgroup")) {
    		excelHeader.createCell(j++).setCellValue("Blood Group");
    		}
    		if(Arrays.asList(values).contains("nationality")) {
    		excelHeader.createCell(j++).setCellValue("Nationality");
    		}
    		if(Arrays.asList(values).contains("accomadation")) {
    		excelHeader.createCell(j++).setCellValue("Accomadation");
    		}
    		if(Arrays.asList(values).contains("quota")) {
    		excelHeader.createCell(j++).setCellValue("Quota");
    		}
    		if(Arrays.asList(values).contains("fathersname")) {
    		excelHeader.createCell(j++).setCellValue("Fathers Name");
    		}
    		if(Arrays.asList(values).contains("mothersname")) {
    		excelHeader.createCell(j++).setCellValue("Mothers Name");
    		}
    		if(Arrays.asList(values).contains("gaurdiansname")) {
    		excelHeader.createCell(j++).setCellValue("Gaurdians Name");
    		}
    		if(Arrays.asList(values).contains("fathersphone")) {
    		excelHeader.createCell(j++).setCellValue("Fathers Phone");
    		}
    		if(Arrays.asList(values).contains("mothersphone")) {
    		excelHeader.createCell(j++).setCellValue("Mothers Phone");
    		}
    		if(Arrays.asList(values).contains("gaurdiansphone")) {
    		excelHeader.createCell(j++).setCellValue("Gaurdians Phone");
    		}
    		if(Arrays.asList(values).contains("aadharnumber")) {
    		excelHeader.createCell(j++).setCellValue("Aadhar Number");
    		}
    		if(Arrays.asList(values).contains("bankaccountnumber")) {
    		excelHeader.createCell(j++).setCellValue("Bank Account Number");
    		}
    		if(Arrays.asList(values).contains("ifsccode")) {
    		excelHeader.createCell(j++).setCellValue("Ifsc Code");
    		}
    		if(Arrays.asList(values).contains("accountholdername")) {
    		excelHeader.createCell(j++).setCellValue("Account Holder Name");
    		}
    		if(Arrays.asList(values).contains("pancardnumber")) {
    		excelHeader.createCell(j++).setCellValue("Pancard Number");
    		}
    		if(Arrays.asList(values).contains("registerednumber")) {
    		excelHeader.createCell(j++).setCellValue("Account Registered Number");
    		}
    		if(Arrays.asList(values).contains("whatsappnumber")) {
    		excelHeader.createCell(j++).setCellValue("Whatsapp Number");
    		}
    		if(Arrays.asList(values).contains("facebookaccount")) {
    		excelHeader.createCell(j++).setCellValue("Facebook Account");
    		}
    		if(Arrays.asList(values).contains("linkedinaccount")) {
    		excelHeader.createCell(j++).setCellValue("LinkedIn Account");
    		}
    		if(Arrays.asList(values).contains("githubaccount")) {
    		excelHeader.createCell(j++).setCellValue("Github Account");
    		}
    		if(Arrays.asList(values).contains("hackerankaccount")) {
    		excelHeader.createCell(j++).setCellValue("Hackerrank Account");
    		}
    		if(Arrays.asList(values).contains("hackerearthaccount")) {
    		excelHeader.createCell(j++).setCellValue("Hackerearth Account");
    		}
    		if(Arrays.asList(values).contains("codechefaccount")) {
    		excelHeader.createCell(j++).setCellValue("Codechef Account");
    		}
    		if(Arrays.asList(values).contains("instagramaccount")) {
    		excelHeader.createCell(j++).setCellValue("Instagram Account");
    		}
    		if(Arrays.asList(values).contains("twitteraccount")) {
    		excelHeader.createCell(j++).setCellValue("Twitter Account");
    		}
    		if(Arrays.asList(values).contains("state")) {
    		excelHeader.createCell(j++).setCellValue("State");
    		}
    		if(Arrays.asList(values).contains("district")) {
    		excelHeader.createCell(j++).setCellValue("District");
    		}
    		if(Arrays.asList(values).contains("address")) {
    		excelHeader.createCell(j++).setCellValue("Address");
    		}
    		
    		List<Student> students = staffService.getStudents(batch,semester,section,collegeId,adminId,staffId,department);
    		
    				int record = 1;
    		for (Student temp : students) {
    			HSSFRow excelRow = excelSheet.createRow(record++);
    			
    			excelRow.createCell(0).setCellValue(temp.getBatch());
    			excelRow.createCell(1).setCellValue(temp.getSemester());
    			excelRow.createCell(2).setCellValue(temp.getDepartment()+" "+temp.getSection());
    			excelRow.createCell(3).setCellValue(temp.getRollno());
    			excelRow.createCell(4).setCellValue(temp.getFirstname()+" "+temp.getLastname());
    			
    			j=5;
        		if(Arrays.asList(values).contains("email")) {
        			excelRow.createCell(j++).setCellValue(temp.getEmail());
        			
        		}
        		if(Arrays.asList(values).contains("dob")) {
        			excelRow.createCell(j++).setCellValue(temp.getDateOfBirth());
        			
        		}
        		if(Arrays.asList(values).contains("gender")) {
        			excelRow.createCell(j++).setCellValue(temp.getGender());
        		}
        		if(Arrays.asList(values).contains("phone")) {
        			excelRow.createCell(j++).setCellValue(temp.getPhone());
        		}
        		if(Arrays.asList(values).contains("religion")) {
        			excelRow.createCell(j++).setCellValue(temp.getReligion());
        		}
        		if(Arrays.asList(values).contains("bloodgroup")) {
        			excelRow.createCell(j++).setCellValue(temp.getBloodgroup());
        		}
        		if(Arrays.asList(values).contains("nationality")) {
        			excelRow.createCell(j++).setCellValue(temp.getNationality());
        		}
        		if(Arrays.asList(values).contains("accomadation")) {
        			excelRow.createCell(j++).setCellValue(temp.getAccomodation());
        		}
        		if(Arrays.asList(values).contains("quota")) {
        			excelRow.createCell(j++).setCellValue(temp.getQuota());
        		}
        		if(Arrays.asList(values).contains("fathersname")) {
        			excelRow.createCell(j++).setCellValue(temp.getFatherName());
        		}
        		if(Arrays.asList(values).contains("mothersname")) {
        			excelRow.createCell(j++).setCellValue(temp.getMotherName());
        		}
        		if(Arrays.asList(values).contains("gaurdiansname")) {
        			excelRow.createCell(j++).setCellValue(temp.getCustodianName());
        		}
        		if(Arrays.asList(values).contains("fathersphone")) {
        			excelRow.createCell(j++).setCellValue(temp.getFatherPhone());
        		}
        		if(Arrays.asList(values).contains("mothersphone")) {
        			excelRow.createCell(j++).setCellValue(temp.getMotherPhone());
        		}
        		if(Arrays.asList(values).contains("gaurdiansphone")) {
        			excelRow.createCell(j++).setCellValue(temp.getCustodianPhone());
        		}
        		if(Arrays.asList(values).contains("aadharnumber")) {
        			excelRow.createCell(j++).setCellValue(temp.getAadharNumber());
        		}
        		if(Arrays.asList(values).contains("bankaccountnumber")) {
        			excelRow.createCell(j++).setCellValue(temp.getAccountNumber());
        		}
        		if(Arrays.asList(values).contains("ifsccode")) {
        			excelRow.createCell(j++).setCellValue(temp.getIfscCode());
        		}
        		if(Arrays.asList(values).contains("accountholdername")) {
        			excelRow.createCell(j++).setCellValue(temp.getAccountHolder());
        		}
        		if(Arrays.asList(values).contains("pancardnumber")) {
        			excelRow.createCell(j++).setCellValue(temp.getPancardNumber());
        		}
        		if(Arrays.asList(values).contains("registerednumber")) {
        			excelRow.createCell(j++).setCellValue(temp.getRegisteredPhone());
        		}
        		if(Arrays.asList(values).contains("whatsappnumber")) {
        			excelRow.createCell(j++).setCellValue(temp.getWhatsappAccount());
        		}
        		if(Arrays.asList(values).contains("facebookaccount")) {
        			excelRow.createCell(j++).setCellValue(temp.getFacebookAccount());
        		}
        		if(Arrays.asList(values).contains("linkedinaccount")) {
        			excelRow.createCell(j++).setCellValue(temp.getLinkedinAccount());
        		}
        		if(Arrays.asList(values).contains("githubaccount")) {
        			excelRow.createCell(j++).setCellValue(temp.getGithubAccount());
        		}
        		if(Arrays.asList(values).contains("hackerankaccount")) {
        			excelRow.createCell(30).setCellValue(temp.getHackerrankAccount());
        		}
        		if(Arrays.asList(values).contains("hackerearthaccount")) {
        			excelRow.createCell(j++).setCellValue(temp.getHackerearthAccount());
        		}
        		if(Arrays.asList(values).contains("codechefaccount")) {
        			excelRow.createCell(j++).setCellValue(temp.getCodechefAccount());
        		}
        		if(Arrays.asList(values).contains("instagramaccount")) {
        			excelRow.createCell(j++).setCellValue(temp.getInstagramAccount());
        		}
        		if(Arrays.asList(values).contains("twitteraccount")) {
        			excelRow.createCell(j++).setCellValue(temp.getTwitterAccount());
        		}
        		if(Arrays.asList(values).contains("state")) {
        			excelRow.createCell(j++).setCellValue(temp.getState());
        		}
        		if(Arrays.asList(values).contains("district")) {
        			excelRow.createCell(j++).setCellValue(temp.getDistrict());
        		}
        		if(Arrays.asList(values).contains("address")) {
        			excelRow.createCell(j++).setCellValue(temp.getPermanentAddress1()+" "+temp.getPermanentAddress2()+" "+temp.getPermanentAddress3());
        		}    			
    			}
            wb.write(fileOut);
        }catch(Exception e) {  
            System.out.println(e.getMessage());  
        }
        
		request.setAttribute("linkReady", "ready");
		TempArray tempArray = new TempArray();
		theModel.addAttribute("temp",tempArray);
		theModel.addAttribute("batch",batch);
		theModel.addAttribute("semester",semester);
		theModel.addAttribute("department",department);
		theModel.addAttribute("section",section);
		return "staff/staffFilterStudents";
        }
	
	@RequestMapping("/uploadAddStudent")
	public String uploadAddStudent() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String batch = request.getParameter("batch");
			String semester = request.getParameter("semester");
			String section = request.getParameter("section");
			session.setAttribute("batch",batch);
			session.setAttribute("semester",semester);
			session.setAttribute("section",section);
			return "staff/addStudentUploadPage";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/processAddStudentUploadPage")
	public String processAddStudentUploadPage(MultipartFile file,Model theModel) throws IOException {
		String user = session.getAttribute("user").toString();
		String role = session.getAttribute("role").toString();
		if(user==null) {
			return "redirect:/";
		}
		if(user!=null && role.equals("Staff")) {
			String batch = session.getAttribute("batch").toString();
			String semester = session.getAttribute("semester").toString();
			String department = session.getAttribute("department").toString();
			String section = session.getAttribute("section").toString();
			String collegeId = session.getAttribute("collegeId").toString();
			String adminId = session.getAttribute("adminId").toString();
			String staffId = session.getAttribute("staffId").toString();
			
			String fileLocation;
		    InputStream in = file.getInputStream();
		    File currDir = new File(".");
		    String path = currDir.getAbsolutePath();
		    fileLocation = path.substring(0, path.length()-1) +"src\\main\\resources\\static\\files\\Read"+collegeId+adminId+department+staffId+".xls";
		    FileOutputStream f = new FileOutputStream(fileLocation);
		    int ch = 0;
		    while ((ch = in.read()) != -1) {
		        f.write(ch);
		    }
		    f.flush();
		    f.close();
		    
		    try (InputStream inp = new FileInputStream(fileLocation)) {  
	            Workbook wb = WorkbookFactory.create(inp);  
	            Sheet sheet = wb.getSheetAt(0);
	            int cols = 40,i,j;
	            int rows = sheet.getPhysicalNumberOfRows();
	            for(i=1;i<rows;i++) {
	            	int flag=0;
	            	Student student = new Student();
	            	student.setBatch(batch);
	            	student.setSemester(semester);
	            	student.setDepartment(department);
	            	student.setSection(section);
	            	student.setAdminId(Integer.parseInt(adminId));
	            	student.setCollegeId(Integer.parseInt(collegeId));
	            	student.setStaffId(Integer.parseInt(staffId));
	            	aa:
	            	for(j=0;j<cols;j++) {
	            		Row row = sheet.getRow(i);  
	                    Cell cell = row.getCell(j);
	                    String value;
	                    if(cell!=null) {
	                    	value= cell.toString();
	            		}
	            	else {
	            		value=" ";
	            	}
	                    //System.out.print(value+" ");
	                    
	                    if(j<11) {
	                    if(cell==null || value.equals(" ")) {
	                    	flag=1;
	                    	break aa;
	                    }
	                    }
	                    
	                    if(j==6) {
	                    	if(value.toUpperCase().equals("SWS")||value.toUpperCase().equals("MANAGEMENT")) {
	                    	}
	                    	else {
	                    		flag=1;
		                    	break aa;
	                    	}
	                    }
	                    
	                    if(j==7) {
	                    	if(value.toUpperCase().equals("HOSTELLER")||value.toUpperCase().equals("DAYSCHOLAR")) {
	                    	}
	                    	else {
	                    		flag=1;
		                    	break aa;
	                    	}
	                    }
	                    
	                    if(j==8) {
	                    	if(value.toUpperCase().equals("MALE")||value.toUpperCase().equals("FEMALE")) {
	                    	}
	                    	else {
	                    		flag=1;
		                    	break aa;
	                    	}
	                    }
	                    
	                    if(j==9) {
	                    	if(value.toUpperCase().equals("A+")||value.toUpperCase().equals("A-") || value.toUpperCase().equals("O+")||value.toUpperCase().equals("O-") || value.toUpperCase().equals("AB+")||value.toUpperCase().equals("AB-") || value.toUpperCase().equals("B+")||value.toUpperCase().equals("B-")) {
	                    	}
	                    	else {
	                    		flag=1;
		                    	break aa;
	                    	}
	                    }
	                    
	                    if(value==null || value.equals(" ")) {
                    		value=" ";
                    	}
	                    
	                    if(j==0) {
	                    	student.setRollno(value.toUpperCase());
	                    }
	                    if(j==1) {
	                    	student.setFirstname(value.toUpperCase());
	                    }
	                    if(j==2) {
	                    	student.setLastname(value.toUpperCase());
	                    }
	                    if(j==3) {
	                    	student.setEmail(value.toLowerCase());
	                    }
	                    if(j==4) {
	                    	student.setUsername(value);
	                    }
	                    if(j==5) {
	                    	student.setPassword(value);
	                    }
	                    if(j==6) {
	                    	student.setQuota(value.toUpperCase());
	                    }
	                    if(j==7) {
	                    	student.setAccomodation(value.toUpperCase());
	                    }
	                    if(j==8) {
	                    	student.setGender(value.toUpperCase());
	                    }
	                    if(j==9) {
	                    	student.setBloodgroup(value.toUpperCase());
	                    }
	                    if(j==10) {
	                    	student.setPhone(value);
	                    }
	                    if(j==11) {
	                    	student.setDateOfBirth(value);
	                    }
	                    if(j==12) {
	                    	if(value==null || value.equals(" ")) {
	                    		value="HINDU";
	                    	}
	                    	if(value.toUpperCase().equals("HINDU") || value.toUpperCase().equals("CHRISTIAN") || value.toUpperCase().equals("MUSLIM")||value.toUpperCase().equals("OTHER")) {
	                    	}
	                    	else {
	                    		value="HINDU";
	                    	}
	                    	student.setReligion(value);
	                    }
	                    if(j==13) {
	                    	student.setNationality(value);
	                    }
	                    if(j==14) {
	                    	student.setFatherName(value);
	                    }
	                    if(j==15) {
	                    	student.setMotherName(value);
	                    }
	                    if(j==16) {
	                    	student.setCustodianName(value);
	                    }
	                    if(j==17) {
	                    	student.setFatherPhone(value);
	                    }
	                    if(j==18) {
	                    	student.setMotherPhone(value);
	                    }
	                    if(j==19) {
	                    	student.setCustodianPhone(value);
	                    }
	                    if(j==20) {
	                    	student.setAadharNumber(value);
	                    }
	                    if(j==21) {
	                    	student.setAccountNumber(value);
	                    }
	                    if(j==22) {
	                    	student.setIfscCode(value);
	                    }
	                    if(j==23) {
	                    	student.setAccountHolder(value);
	                    }
	                    if(j==24) {
	                    	student.setPancardNumber(value);
	                    }
	                    if(j==25) {
	                    	student.setRegisteredPhone(value);
	                    }
	                    if(j==26) {
	                    	student.setWhatsappAccount(value);
	                    }
	                    if(j==27) {
	                    	student.setFacebookAccount(value);
	                    }
	                    if(j==28) {
	                    	student.setLinkedinAccount(value);
	                    }
	                    if(j==29) {
	                    	student.setGithubAccount(value);
	                    }
	                    if(j==30) {
	                    	student.setHackerrankAccount(value);
	                    }
	                    if(j==31) {
	                    	student.setHackerearthAccount(value);
	                    }
	                    if(j==32) {
	                    	student.setCodechefAccount(value);
	                    }
	                    if(j==33) {
	                    	student.setInstagramAccount(value);
	                    }
	                    if(j==34) {
	                    	student.setTwitterAccount(value);
	                    }
	                    if(j==35) {
	                    	student.setState(value);
	                    }
	                    if(j==36) {
	                    	student.setDistrict(value);
	                    }
	                    if(j==37) {
	                    	student.setPermanentAddress1(value);
	                    }
	                    if(j==38) {
	                    	student.setPermanentAddress2(value);
	                    }
	                    if(j==39) {
	                    	student.setPermanentAddress3(value);
	                    }
	                    
	            	}
	            	
	            	if(flag==0) {
	            		staffService.createStudent(student);
	            	}
	            	
	            }
	    }catch(Exception e) {  
	        System.out.println(e);  
	    }  
			
		    File file1 = new File(fileLocation);
		    file1.delete();
		
		    List<Student> students = staffService.getStudents(batch,semester,section,collegeId,adminId,staffId,department);
			theModel.addAttribute("students",students);
			return "staff/studentsViewPage";
		    
		}
		else {
			return "redirect:/";
		}
	}

	@RequestMapping("/staffLogOut")
	public String staffLogOut() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String collegeId = session.getAttribute("collegeId").toString();
		String adminId = session.getAttribute("adminId").toString();
		String staffId = session.getAttribute("staffId").toString();
		String department = session.getAttribute("department").toString();
		File file = new File("src/main/resources/static/files/"+adminId+staffId+department+collegeId+".xls");
		file.delete();
		
		session.invalidate();
		return "redirect:/";
	}

}
