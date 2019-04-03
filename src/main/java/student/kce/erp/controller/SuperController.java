package student.kce.erp.controller;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import student.kce.erp.model.Attachment;
import student.kce.erp.model.College;
import student.kce.erp.model.GroupAttachment;
import student.kce.erp.model.RequestErp;
import student.kce.erp.model.SuperUser;
import student.kce.erp.service.SuperUserService;

@Controller
public class SuperController {
	
	
@Autowired
SuperUserService superUserService;

@Autowired
HttpServletRequest request;

@Autowired
HttpSession session;

@RequestMapping("/")
public String home(Model theModel) throws MessagingException {
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(superUserService.isAvailable()==false) {
		theModel.addAttribute("superuser",new SuperUser());
		return "superuser/createSuperUser";
	}
	else if(user!=null && role.equals("SuperUser")) {
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/superUserHomePage";
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
		return "redirect:/studentHome";
	}
	else {
		theModel.addAttribute("colleges",superUserService.listColleges());
		return "home";
	}
}

@PostMapping("/processCreateSuperUser")
public String processCreateSuperUser(@ModelAttribute("superuser") SuperUser theSuperUser) {
	superUserService.createSuperUser(theSuperUser);
	return "redirect:/";
}

@RequestMapping("/requestErp")
public String requestErp(Model theModel) {
	String user = (String) session.getAttribute("user");
	if(user!=null) {
		return "redirect:/";
	}
	else {
		theModel.addAttribute("requestErp",new RequestErp());
		return "superuser/requestErpPage";
	}
}

@RequestMapping("/viewRequisition")
public String viewRequisition(Model theModel) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		request.setAttribute("user",session.getAttribute("user"));
		List<RequestErp> requestErps = superUserService.getAllRequestErp();
		theModel.addAttribute("requestErps",requestErps);
		return "superuser/viewRequisitionPage";
	}
	else {
		return "redirect:/";
	}
}

@RequestMapping("/deleteRequestErps")
public String deleteRequestErps(@RequestParam("rid") String id) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		RequestErp singleRequest = superUserService.getSingleRequestErp(id);
		superUserService.deleteErp(singleRequest);
		return "redirect:/viewRequisition";
	}
	else {
		return "redirect:/";
	}
}

@RequestMapping("/processRequestErp")
public String processRequestErp(@ModelAttribute("requestErp") RequestErp theRequestErp,Model theModel) {
	theRequestErp.setCollegeName(theRequestErp.getCollegeName().toUpperCase());
	if(superUserService.isCollegeAvailable(theRequestErp.getCollegeName())) {
		request.setAttribute("invalidRequest","invalid");
		theModel.addAttribute("requestErp",new RequestErp());
		return "superuser/requestErpPage";
	}
	else {
		superUserService.sendRequestErp(theRequestErp);
		return "redirect:/";
	}
}

@RequestMapping("/superUserLogin")
public String superUserLogin() {
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/superUserHomePage";
	}
	else if(user!=null && role.equals("College")) {
		return "redirect:/";
	}
	else if(user!=null && role.equals("Admin")) {
		return "redirect:/adminHome";
	}
	else if(user!=null && role.equals("Staff")) {
		return "";
	}
	else if(user!=null && role.equals("Student")) {
		return "";
	}
	else {
	return "superuser/superUserLoginForm";
	}
}



@PostMapping("/processSuperUserLogin")
public String processSuperUserLogin() {
	String username = request.getParameter("username");
	String password = request.getParameter("password");
	if(superUserService.isSuperUser(username,password)) {
		session.setAttribute("user", username);
		session.setAttribute("userId", (superUserService.getSuperId(username,password)).toString());
		session.setAttribute("role", "SuperUser");
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/superUserHomePage";
	}
	else {
		request.setAttribute("invalidSuperUser","invalid");
		return "superuser/superUserLoginForm";
	}
}

@RequestMapping("/extendSuperUser")
public String extendSuperUser(Model theModel) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		theModel.addAttribute("superuser",new SuperUser());
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/extendSuperUser";
	}
	else {
		return "redirect:/";
	}
}

@RequestMapping("/addCollege")
public String addCollege(Model theModel) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		theModel.addAttribute("college",new College());
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/createCollegeForm";
	}
	else {
		return "redirect:/";
	}
}

@RequestMapping("/processCreateCollegeForm")
public String processCreateCollegeForm(@ModelAttribute("college") College theCollege,Model theModel) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	if(user==null) {
		return "redirect:/";
	}
	theCollege.setCollegeName(theCollege.getCollegeName().toUpperCase());
	if(superUserService.isCollegeAvailable(theCollege.getCollegeName())) {
		request.setAttribute("invalidCollege","invalid");
		theModel.addAttribute("college",new College());
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/createCollegeForm";
	}
	else {
		superUserService.addCollege(theCollege);
		return "redirect:/manageColleges";
	}
}

@RequestMapping("/manageColleges")
public String manageColleges(Model theModel) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		List<College> collegeList = superUserService.listColleges();
		theModel.addAttribute("colleges",collegeList);
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/manageColleges";
	}
	else {
		return "redirect:/";
	}
}

@RequestMapping("/editCollege")
public String editCollege(Model theModel,@RequestParam("cid") String collegeId) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		theModel.addAttribute("college",superUserService.getCollege(collegeId));
		request.setAttribute("user",session.getAttribute("user"));
		return "superuser/editCollegeForm";
	}
	else {
		return "redirect:/";
	}
}

@RequestMapping("/processEditCollegeForm")
public String processCreateCollegeForm(@ModelAttribute("college") College theCollege) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	if(user==null) {
		return "redirect:/";
	}
		superUserService.addCollege(theCollege);
		return "redirect:/manageColleges";
	}

@RequestMapping("/deleteCollege")
public String deleteCollege(@RequestParam("cid") String collegeId) {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	String user = (String) session.getAttribute("user");
	String role = (String) session.getAttribute("role");
	if(user!=null && role.equals("SuperUser")) {
		College college = superUserService.getCollege(collegeId);
		superUserService.deleteCollege(college);
		return "redirect:/manageColleges";
	}
	else {
		return "redirect:/";
	}
}

@RequestMapping("/getResourcePage")
public String getResourcePage() {
	return "superuser/getResourcePage";
}

@PostMapping("/getStudentResources")
public String getStudentResources(Model theModel) {
	String groupCode = request.getParameter("groupCode");
	List<GroupAttachment> attachments = superUserService.getStudentAttachments(groupCode);
	theModel.addAttribute("groupAttachments",attachments);
	return"superuser/viewStudentResourcePage";
}

@RequestMapping("/superUserLogOut")
public String superUserLogOut() {
	if (session.getAttribute("user") == null) {
			return "redirect:/";
		}
	session.invalidate();
	return "redirect:/";
}

	
}
