package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import config.MenuTab;
import dao.UserDAO;
import entities.User;


@Controller
@Scope("session")
public class UserController extends GenericController {

	@Autowired
	private UserDAO userService;
	
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, ModelMap model){
		HttpSession session = request.getSession();
		User loggedUser;
		loggedUser = userService.getUser(request.getParameter("user"));		
		if(loggedUser != null){
			session.setAttribute("loggedUser", loggedUser);	
			
		}
		else{
			model.addAttribute("msg", "The username you have entered is not valid!");	
		}

		model.addAttribute("tabsList", MenuTab.tabList);
		return "index";
	}
	
	@RequestMapping(value="/signout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, ModelMap model){
		MenuTab.tabList.removeAll(tabsList);
		request.getSession().invalidate();
		return "index";
	}
	
	@RequestMapping(value="/userinfo")
	public String viewUser(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		return "viewuser";
	}
	
	@RequestMapping(value="/allusers*")
	public String viewAllUsers(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			if(request.getParameter("delete") != null)
				userService.deteleUser(Integer.parseInt(request.getParameter("delete")));
			model.addAttribute("users", userService.getAllUsers());
			return "allusers";
		}
			else
				return "index";
	}
	
	@RequestMapping(value="/edituser*", method = RequestMethod.GET)
	public String editUser(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			User edituser = userService.getUserById(Integer.parseInt(request.getParameter("id")));
			if(edituser != null){
				model.addAttribute("user", edituser);			
			}
			return "edituser";
		}
			else
				return "redirect:allusers";
	}
	
	@RequestMapping(value="/edituser*", method = RequestMethod.POST)
	public String submitEditUser(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			User modifiedUser = new User();
			modifiedUser.setId(Integer.parseInt(request.getParameter("id")));
			modifiedUser.setAccessLevel(Integer.parseInt(request.getParameter("accessLevel")));
			modifiedUser.setAddress(request.getParameter("address"));
			modifiedUser.setAdmin(Boolean.parseBoolean(request.getParameter("isAdmin")));
			modifiedUser.setSsn(request.getParameter("ssn"));
			modifiedUser.setUserName(request.getParameter("name"));
						
			userService.editUser(modifiedUser);
			return "redirect:edituser?id=" + Integer.parseInt(request.getParameter("id"));
		}
			else
				return "redirect:allusers";
	}

	@RequestMapping(value="/adduser", method = RequestMethod.GET)
	public String addUser(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			return "adduser";
		}
			else
				return "redirect:index";
	}
	
	@RequestMapping(value="/adduser", method = RequestMethod.POST)
	public String submitNewUser(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			User modifiedUser = new User();
			modifiedUser.setAccessLevel(Integer.parseInt(request.getParameter("accessLevel")));
			modifiedUser.setAddress(request.getParameter("address"));
			modifiedUser.setAdmin(Boolean.parseBoolean(request.getParameter("isAdmin")));
			modifiedUser.setSsn(request.getParameter("ssn"));
			modifiedUser.setUserName(request.getParameter("name"));
						
			userService.addUser(modifiedUser);
			return "redirect:adduser";
		}
			else
				return "redirect:index";
	}

	@RequestMapping(value="/borrowed*")
	public String fetchBorrowedBooks(HttpServletRequest request, ModelMap model){
		if(request.getParameter("all") != null){
			model.addAttribute("borrowedBooks", userService.getTransForUser((User)request.getSession().getAttribute("loggedUser")));
			model.addAttribute("all", true);
		}
		else{
			model.addAttribute("borrowedBooks", userService.getActiveTransForUser((User)request.getSession().getAttribute("loggedUser")));
			model.addAttribute("all", false);
		}
		
		return "borrowedbooks";
	}

	
	
}
