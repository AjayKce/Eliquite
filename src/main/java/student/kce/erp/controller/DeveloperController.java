package student.kce.erp.controller;

import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.kce.erp.model.Admin;
import student.kce.erp.model.Attachment;
import student.kce.erp.model.AttendanceResult;
import student.kce.erp.model.AttendanceScheduler;
import student.kce.erp.model.Batch;
import student.kce.erp.model.College;
import student.kce.erp.model.CollegeFeed;
import student.kce.erp.model.CreateFeedback;
import student.kce.erp.model.DepartmentFeed;
import student.kce.erp.model.DepartmentFeedbackResult;
import student.kce.erp.model.DepartmentFeedbackSet;
import student.kce.erp.model.DepartmentSubject;
import student.kce.erp.model.Enrollment;
import student.kce.erp.model.FeedBackAlloc;
import student.kce.erp.model.FeedBackSet;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.ResourceGroup;
import student.kce.erp.model.SetExam;
import student.kce.erp.model.Staff;
import student.kce.erp.model.Student;
import student.kce.erp.model.StudentClass;
import student.kce.erp.model.StudentMark;
import student.kce.erp.model.SubjectAlloc;
import student.kce.erp.model.SuperUser;
import student.kce.erp.model.Tutor;
import student.kce.erp.service.DeveloperService;

@Controller
public class DeveloperController {
	
	@Autowired
	DeveloperService developerService;
	
	
	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@RequestMapping("/console")
	public String console() {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			return "developer/console";
		}
		else {
			return "redirect:/";
		}
	}

	@RequestMapping("/console/attachment")
	public String allAttachments(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<Attachment> attachments = developerService.getAttachment();
			theModel.addAttribute("attachments",attachments);
			return "developer/attachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editAttachment")
	public String editAttachment(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Attachment attachment = developerService.getAttachment(id);
			session.setAttribute("fileData", attachment.getFileData());
			theModel.addAttribute("attachment",attachment);
			return "developer/editAttachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditAttachment")
	public String processEditAttachment(@ModelAttribute("attachment")Attachment attachment) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Blob fileData = (Blob)session.getAttribute("fileData");
			attachment.setFileData(fileData);
			developerService.saveAttachment(attachment);
			return "redirect:/console/attachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteAttachment")
	public String deleteAttachment(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Attachment attachment = developerService.getAttachment(id);
			developerService.deleteAttachment(attachment);
			return "redirect:/console/attachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	
	@RequestMapping("/console/attendanceResult")
	public String attendanceResult(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<AttendanceResult> attendanceResults = developerService.getAttendanceResult();
			theModel.addAttribute("attendanceResults",attendanceResults);
			return "developer/attendanceResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editAttendanceResult")
	public String editAttendanceResult(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			AttendanceResult attendanceResult = developerService.getAttendanceResult(id);
			theModel.addAttribute("attendanceResult",attendanceResult);
			return "developer/editAttendanceResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditAttendanceResult")
	public String processEditAttendanceResult(@ModelAttribute("attendanceResult")AttendanceResult attendanceResult) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveAttendanceResult(attendanceResult);
			return "redirect:/console/attendanceResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteAttendanceResult")
	public String deleteAttendanceResult(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			AttendanceResult attendanceResult = developerService.getAttendanceResult(id);
			developerService.deleteAttendanceResult(attendanceResult);
			return "redirect:/console/attendanceResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/attendanceScheduler")
	public String attendanceScheduler(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<AttendanceScheduler> attendanceSchedulers = developerService.getAttendanceScheduler();
			theModel.addAttribute("attendanceSchedulers",attendanceSchedulers);
			return "developer/attendanceScheduler";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editAttendanceScheduler")
	public String editAttendanceScheduler(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			AttendanceScheduler attendanceScheduler = developerService.getAttendanceScheduler(id);
			theModel.addAttribute("attendanceScheduler",attendanceScheduler);
			return "developer/editAttendanceScheduler";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditAttendanceScheduler")
	public String processEditAttendanceScheduler(@ModelAttribute("attendanceResult")AttendanceScheduler attendanceScheduler) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveAttendanceScheduler(attendanceScheduler);
			return "redirect:/console/attendanceScheduler";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteAttendanceScheduler")
	public String deleteAttendanceScheduler(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			AttendanceScheduler attendanceScheduler = developerService.getAttendanceScheduler(id);
			developerService.deleteAttendanceScheduler(attendanceScheduler);
			return "redirect:/console/attendanceScheduler";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/batch")
	public String batch(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<Batch> batches = developerService.getBatch();
			theModel.addAttribute("batches",batches);
			return "developer/batch";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editBatch")
	public String editBatch(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Batch batch = developerService.getBatch(id);
			theModel.addAttribute("batch",batch);
			return "developer/editBatch";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditBatch")
	public String processEditBatch(@ModelAttribute("batch")Batch batch) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveBatch(batch);
			return "redirect:/console/batch";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteBatch")
	public String deleteBatch(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Batch batch = developerService.getBatch(id);
			developerService.deleteBatch(batch);
			return "redirect:/console/batch";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/studentClass")
	public String studentClass(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<StudentClass> studentClasses = developerService.getStudentClass();
			theModel.addAttribute("studentClasses",studentClasses);
			return "developer/studentClass";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editStudentClass")
	public String editStudentClasses(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			StudentClass studentClass = developerService.getStudentClass(id);
			theModel.addAttribute("studentClass",studentClass);
			return "developer/editStudentClass";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditStudentClass")
	public String processEditStudentClass(@ModelAttribute("studentClass")StudentClass studentClass) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveStudentClass(studentClass);
			return "redirect:/console/studentClass";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteStudentClass")
	public String deleteStudentClass(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			StudentClass studentClass = developerService.getStudentClass(id);
			developerService.deleteStudentClass(studentClass);
			return "redirect:/console/studentClass";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/college")
	public String college(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<College> colleges = developerService.getCollege();
			theModel.addAttribute("colleges",colleges);
			return "developer/college";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editCollege")
	public String editCollege(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			College college = developerService.getCollege(id);
			theModel.addAttribute("college",college);
			return "developer/editCollege";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditCollege")
	public String processEditCollege(@ModelAttribute("college")College college) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveCollege(college);
			return "redirect:/console/college";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteCollege")
	public String deleteCollege(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			College college = developerService.getCollege(id);
			developerService.deleteCollege(college);
			return "redirect:/console/college";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/collegeAdmin")
	public String collegeAdmin(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<Admin> admins = developerService.getCollegeAdmin();
			theModel.addAttribute("admins",admins);
			return "developer/collegeAdmin";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editCollegeAdmin")
	public String editCollegeAdmin(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Admin admin = developerService.getCollegeAdmin(id);
			theModel.addAttribute("admin",admin);
			return "developer/editCollegeAdmin";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditCollegeAdmin")
	public String processEditCollegeAdmin(@ModelAttribute("admin")Admin admin) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveCollegeAdmin(admin);
			return "redirect:/console/collegeAdmin";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteCollegeAdmin")
	public String deleteCollegeAdmin(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Admin admin = developerService.getCollegeAdmin(id);
			developerService.deleteCollegeAdmin(admin);
			return "redirect:/console/collegeAdmin";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/collegeFeed")
	public String collegeFeed(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<CollegeFeed> collegeFeeds = developerService.getCollegeFeed();
			theModel.addAttribute("collegeFeeds",collegeFeeds);
			return "developer/collegeFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editCollegeFeed")
	public String editCollegeFeed(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			CollegeFeed collegeFeed = developerService.getCollegeFeed(id);
			theModel.addAttribute("collegeFeed",collegeFeed);
			return "developer/editCollegeFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditCollegeFeed")
	public String processEditCollegeFeed(@ModelAttribute("collegeFeed")CollegeFeed collegeFeed) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveCollegeFeed(collegeFeed);
			return "redirect:/console/collegeFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteCollegeFeed")
	public String deleteCollegeFeed(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			CollegeFeed collegeFeed = developerService.getCollegeFeed(id);
			developerService.deleteCollegeFeed(collegeFeed);
			return "redirect:/console/collegeFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/createFeedback")
	public String createFeedback(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<CreateFeedback> createFeedbacks = developerService.getCreateFeedback();
			theModel.addAttribute("createFeedbacks",createFeedbacks);
			return "developer/createFeedback";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editCreateFeedback")
	public String editCreateFeedback(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			CreateFeedback createFeedback = developerService.getCreateFeedback(id);
			theModel.addAttribute("createFeedback",createFeedback);
			return "developer/editCreateFeedback";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditCreateFeedback")
	public String processEditCreateFeedback(@ModelAttribute("createFeedback")CreateFeedback createFeedback) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveCreateFeedback(createFeedback);
			return "redirect:/console/createFeedback";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteCreateFeedback")
	public String deleteCreateFeedback(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			CreateFeedback createFeedback = developerService.getCreateFeedback(id);
			developerService.deleteCreateFeedback(createFeedback);
			return "redirect:/console/createFeedback";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/departmentFeed")
	public String departmentFeed(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<DepartmentFeed> departmentFeeds = developerService.getDepartmentFeed();
			theModel.addAttribute("departmentFeeds",departmentFeeds);
			return "developer/departmentFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editDepartmentFeed")
	public String editDepartmentFeed(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentFeed departmentFeed = developerService.getDepartmentFeed(id);
			theModel.addAttribute("departmentFeed",departmentFeed);
			return "developer/editDepartmentFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditDepartmentFeed")
	public String processEditCreateFeedback(@ModelAttribute("departmentFeed")DepartmentFeed departmentFeed) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveDepartmentFeed(departmentFeed);
			return "redirect:/console/departmentFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteDepartmentFeed")
	public String deleteDepartmentFeed(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentFeed departmentFeed = developerService.getDepartmentFeed(id);
			developerService.deleteDepartmentFeed(departmentFeed);
			return "redirect:/console/departmentFeed";
		}
		else {
			return "redirect:/";
		}
	}
	
	
	@RequestMapping("/console/departmentFeedbackSet")
	public String departmentFeedbackSet(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<DepartmentFeedbackSet> departmentFeedbackSets = developerService.getDepartmentFeedbackSet();
			theModel.addAttribute("departmentFeedbackSets",departmentFeedbackSets);
			return "developer/departmentFeedbackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editDepartmentFeedbackSet")
	public String editDepartmentFeedbackSet(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentFeedbackSet departmentFeedbackSet = developerService.getDepartmentFeedbackSet(id);
			theModel.addAttribute("departmentFeedbackSet",departmentFeedbackSet);
			return "developer/editDepartmentFeedbackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditDepartmentFeedbackSet")
	public String processEditDepartmentFeedbackSet(@ModelAttribute("departmentFeedbackSet")DepartmentFeedbackSet departmentFeedbackSet) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveDepartmentFeedbackSet(departmentFeedbackSet);
			return "redirect:/console/departmentFeedbackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteDepartmentFeedbackSet")
	public String deleteDepartmentFeedbackSet(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentFeedbackSet departmentFeedbackSet = developerService.getDepartmentFeedbackSet(id);
			developerService.deleteDepartmentFeedbackSet(departmentFeedbackSet);
			return "redirect:/console/departmentFeedbackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/departmentFeedbackResult")
	public String departmentFeedbackResult(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<DepartmentFeedbackResult> departmentFeedbackResults = developerService.getDepartmentFeedbackResult();
			theModel.addAttribute("departmentFeedbackResults",departmentFeedbackResults);
			return "developer/departmentFeedbackResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editDepartmentFeedbackResult")
	public String editDepartmentFeedbackResult(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentFeedbackResult departmentFeedbackResult = developerService.getDepartmentFeedbackResult(id);
			theModel.addAttribute("departmentFeedbackResult",departmentFeedbackResult);
			return "developer/editDepartmentFeedbackResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditDepartmentFeedbackResult")
	public String processEditDepartmentFeedbackResult(@ModelAttribute("departmentFeedbackResult")DepartmentFeedbackResult departmentFeedbackResult) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveDepartmentFeedbackResult(departmentFeedbackResult);
			return "redirect:/console/departmentFeedbackResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteDepartmentFeedbackResult")
	public String deleteDepartmentFeedbackResult(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentFeedbackResult departmentFeedbackResult = developerService.getDepartmentFeedbackResult(id);
			developerService.deleteDepartmentFeedbackResult(departmentFeedbackResult);
			return "redirect:/console/departmentFeedbackResult";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/enrollment")
	public String enrollment(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<Enrollment> enrollments = developerService.getEnrollment();
			theModel.addAttribute("enrollments",enrollments);
			return "developer/enrollment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editEnrollment")
	public String editEnrollment(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Enrollment enrollment = developerService.getEnrollment(id);
			theModel.addAttribute("enrollment",enrollment);
			return "developer/editEnrollment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditEnrollment")
	public String processEditEnrollment(@ModelAttribute("enrollment")Enrollment enrollment) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveEnrollment(enrollment);
			return "redirect:/console/enrollment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteEnrollment")
	public String deleteEnrollment(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Enrollment enrollment = developerService.getEnrollment(id);
			developerService.deleteEnrollment(enrollment);
			return "redirect:/console/enrollment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/feedBackAlloc")
	public String feedBackAlloc(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<FeedBackAlloc> feedBackAllocs = developerService.getFeedBackAlloc();
			theModel.addAttribute("feedBackAllocs",feedBackAllocs);
			return "developer/feedBackAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editFeedBackAlloc")
	public String editFeedBackAlloc(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			FeedBackAlloc feedBackAlloc = developerService.getFeedBackAlloc(id);
			theModel.addAttribute("feedBackAlloc",feedBackAlloc);
			return "developer/editFeedBackAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditFeedBackAlloc")
	public String processEditFeedBackAlloc(@ModelAttribute("feedBackAlloc")FeedBackAlloc feedBackAlloc) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveFeedBackAlloc(feedBackAlloc);
			return "redirect:/console/feedBackAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteFeedBackAlloc")
	public String deleteFeedBackAlloc(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			FeedBackAlloc feedBackAlloc = developerService.getFeedBackAlloc(id);
			developerService.deleteFeedBackAlloc(feedBackAlloc);
			return "redirect:/console/feedBackAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/feedBackSet")
	public String FeedBackSet(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<FeedBackSet> feedBackSets = developerService.getFeedBackSet();
			theModel.addAttribute("feedBackSets",feedBackSets);
			return "developer/feedBackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editFeedBackSet")
	public String editFeedBackSet(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			FeedBackSet feedBackSet = developerService.getFeedBackSet(id);
			theModel.addAttribute("feedBackSet",feedBackSet);
			return "developer/editFeedBackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditFeedBackSet")
	public String processEditFeedBackSet(@ModelAttribute("feedBackSet")FeedBackSet feedBackSet) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveFeedBackSet(feedBackSet);
			return "redirect:/console/feedBackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteFeedBackSet")
	public String deleteFeedBackSet(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			FeedBackSet feedBackSet = developerService.getFeedBackSet(id);
			developerService.deleteFeedBackSet(feedBackSet);
			return "redirect:/console/feedBackSet";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/groupAttachment")
	public String groupAttachment(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<GroupAttachment> groupAttachments = developerService.getGroupAttachment();
			theModel.addAttribute("groupAttachments",groupAttachments);
			return "developer/groupAttachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editGroupAttachment")
	public String editGroupAttachment(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			GroupAttachment groupAttachment = developerService.getGroupAttachment(id);
			session.setAttribute("fileData", groupAttachment.getFileData());
			theModel.addAttribute("groupAttachment",groupAttachment);
			return "developer/editGroupAttachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditGroupAttachment")
	public String processEditGroupAttachment(@ModelAttribute("groupAttachment")GroupAttachment groupAttachment) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Blob fileData = (Blob)session.getAttribute("fileData");
			groupAttachment.setFileData(fileData);
			developerService.saveGroupAttachment(groupAttachment);
			return "redirect:/console/groupAttachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteGroupAttachment")
	public String deleteGroupAttachment(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			GroupAttachment groupAttachment = developerService.getGroupAttachment(id);
			developerService.deleteGroupAttachment(groupAttachment);
			return "redirect:/console/groupAttachment";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/resourceGroup")
	public String resourceGroup(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<ResourceGroup> resourceGroups = developerService.getResourceGroup();
			theModel.addAttribute("resourceGroups",resourceGroups);
			return "developer/resourceGroup";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editResourceGroup")
	public String editResourceGroup(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			ResourceGroup resourceGroup = developerService.getResourceGroup(id);
			theModel.addAttribute("resourceGroup",resourceGroup);
			return "developer/editResourceGroup";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditResourceGroup")
	public String processEditResourceGroup(@ModelAttribute("resourceGroup")ResourceGroup resourceGroup) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveResourceGroup(resourceGroup);
			return "redirect:/console/resourceGroup";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteResourceGroup")
	public String deleteResourceGroup(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			ResourceGroup resourceGroup = developerService.getResourceGroup(id);
			developerService.deleteResourceGroup(resourceGroup);
			return "redirect:/console/resourceGroup";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/setExam")
	public String setExam(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<SetExam> setExams = developerService.getSetExam();
			theModel.addAttribute("setExams",setExams);
			return "developer/setExam";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editSetExam")
	public String editSetExam(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			SetExam setExam = developerService.getSetExam(id);
			theModel.addAttribute("setExam",setExam);
			return "developer/editSetExam";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditSetExam")
	public String processEditSetExam(@ModelAttribute("setExam")SetExam setExam) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveSetExam(setExam);
			return "redirect:/console/setExam";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteSetExam")
	public String deleteSetExam(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			SetExam setExam = developerService.getSetExam(id);
			developerService.deleteSetExam(setExam);
			return "redirect:/console/setExam";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/staff")
	public String staff(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<Staff> staffs = developerService.getStaff();
			theModel.addAttribute("staffs",staffs);
			return "developer/staff";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editStaff")
	public String editStaff(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Staff staff = developerService.getStaff(id);
			theModel.addAttribute("staff",staff);
			return "developer/editStaff";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditStaff")
	public String processEditStaff(@ModelAttribute("staff")Staff staff) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveStaff(staff);
			return "redirect:/console/staff";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteStaff")
	public String deleteStaff(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Staff staff = developerService.getStaff(id);
			developerService.deleteStaff(staff);
			return "redirect:/console/staff";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/student")
	public String student(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<Student> students = developerService.getStudent();
			theModel.addAttribute("students",students);
			return "developer/student";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editStudent")
	public String editStudent(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Student student = developerService.getStudent(id);
			theModel.addAttribute("student",student);
			return "developer/editStudent";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditStudent")
	public String processEditStudent(@ModelAttribute("student")Student student) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveStudent(student);
			return "redirect:/console/student";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteStudent")
	public String deleteStudent(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Student student = developerService.getStudent(id);
			developerService.deleteStudent(student);
			return "redirect:/console/student";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/studentMark")
	public String studentMark(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<StudentMark> studentMarks = developerService.getStudentMark();
			theModel.addAttribute("studentMarks",studentMarks);
			return "developer/studentMark";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editStudentMark")
	public String editStudentMark(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			StudentMark studentMark = developerService.getStudentMark(id);
			theModel.addAttribute("studentMark",studentMark);
			return "developer/editStudentMark";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditStudentMark")
	public String processEditStudentMark(@ModelAttribute("studentMark")StudentMark studentMark) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveStudentMark(studentMark);
			return "redirect:/console/studentMark";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteStudentMark")
	public String deleteStudentMark(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			StudentMark studentMark = developerService.getStudentMark(id);
			developerService.deleteStudentMark(studentMark);
			return "redirect:/console/studentMark";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/departmentSubject")
	public String departmentSubject(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<DepartmentSubject> departmentSubjects = developerService.getDepartmentSubject();
			theModel.addAttribute("departmentSubjects",departmentSubjects);
			return "developer/departmentSubject";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editDepartmentSubject")
	public String editDepartmentSubject(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentSubject departmentSubject = developerService.getDepartmentSubject(id);
			theModel.addAttribute("departmentSubject",departmentSubject);
			return "developer/editDepartmentSubject";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditDepartmentSubject")
	public String processEditDepartmentSubject(@ModelAttribute("departmentSubject")DepartmentSubject departmentSubject) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveDepartmentSubject(departmentSubject);
			return "redirect:/console/departmentSubject";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteDepartmentSubject")
	public String deleteDepartmentSubject(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			DepartmentSubject departmentSubject = developerService.getDepartmentSubject(id);
			developerService.deleteDepartmentSubject(departmentSubject);
			return "redirect:/console/departmentSubject";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/subjectAlloc")
	public String subjectAlloc(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<SubjectAlloc> subjectAllocs = developerService.getSubjectAlloc();
			theModel.addAttribute("subjectAllocs",subjectAllocs);
			return "developer/subjectAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editSubjectAlloc")
	public String editSubjectAlloc(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			SubjectAlloc subjectAlloc = developerService.getSubjectAlloc(id);
			theModel.addAttribute("subjectAlloc",subjectAlloc);
			return "developer/editSubjectAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditSubjectAlloc")
	public String processEditSubjectAlloc(@ModelAttribute("subjectAlloc")SubjectAlloc subjectAlloc) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveSubjectAlloc(subjectAlloc);
			return "redirect:/console/subjectAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteSubjectAlloc")
	public String deleteSubjectAlloc(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			SubjectAlloc subjectAlloc = developerService.getSubjectAlloc(id);
			developerService.deleteSubjectAlloc(subjectAlloc);
			return "redirect:/console/subjectAlloc";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/superUser")
	public String superUser(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<SuperUser> superUsers = developerService.getSuperUser();
			theModel.addAttribute("superUsers",superUsers);
			return "developer/superUser";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editSuperUser")
	public String editSuperUser(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			SuperUser superUser = developerService.getSuperUser(id);
			theModel.addAttribute("superUser",superUser);
			return "developer/editSuperUser";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditSuperUser")
	public String processSuperUser(@ModelAttribute("superUser")SuperUser superUser) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveSuperUser(superUser);
			return "redirect:/console/superUser";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteSuperUser")
	public String deleteSuperUser(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			SuperUser superUser = developerService.getSuperUser(id);
			developerService.deleteSuperUser(superUser);
			return "redirect:/console/superUser";
		}
		else {
			return "redirect:/";
		}
	}
	
	
	@RequestMapping("/console/tutor")
	public String tutor(Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			List<Tutor> tutors = developerService.getTutor();
			theModel.addAttribute("tutors",tutors);
			return "developer/tutor";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/editTutor")
	public String editTutor(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Tutor tutor = developerService.getTutor(id);
			theModel.addAttribute("tutor",tutor);
			return "developer/editTutor";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/processEditTutor")
	public String processTutor(@ModelAttribute("tutor")Tutor tutor) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			developerService.saveTutor(tutor);
			return "redirect:/console/tutor";
		}
		else {
			return "redirect:/";
		}
	}
	
	@RequestMapping("/console/deleteTutor")
	public String deleteTutor(@RequestParam("id")int id,Model theModel) {
		if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
		String user = (String) session.getAttribute("user");
		String role = (String) session.getAttribute("role");
		if(user!=null && role.equals("SuperUser")) {
			Tutor tutor = developerService.getTutor(id);
			developerService.deleteTutor(tutor);
			return "redirect:/console/tutor";
		}
		else {
			return "redirect:/";
		}
	}
	
}
