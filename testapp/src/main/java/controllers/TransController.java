package controllers;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import dao.BookDAO;
import dao.TransDAO;
import dao.UserDAO;
import entities.Transaction;
import entities.User;

@Controller
public class TransController extends GenericController {
	
	@Autowired
	private TransDAO transService; 
	@Autowired
	private UserDAO userService;
	@Autowired
	private BookDAO bookService;
	
	@RequestMapping(value="/lend*", method = RequestMethod.GET)
	public String showLentBooks(HttpServletRequest request, ModelMap model){
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			if(request.getParameter("delete")!=null)
				transService.deleteTrans(Integer.parseInt(request.getParameter("delete")));
			model.addAttribute("trans", transService.getAllTrans());
			
			return "lentbooks";
		}
		return "index";
	}
	
	@RequestMapping(value="/edittrans*", method = RequestMethod.GET)
	public String editTrans(HttpServletRequest request, ModelMap model){
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){			
			Transaction trans = transService.getTransById(Integer.parseInt(request.getParameter("id")));
			model.addAttribute("trans", trans);
			model.addAttribute("users", userService.getAllUsers());
			model.addAttribute("books", (bookService.getAvailableBooks())); //.add(trans.getBook())
			return "edittrans";
		}
		return "index";
	}
	
	@RequestMapping(value="/edittrans*", method = RequestMethod.POST)
	public String submitModifiedTrans(HttpServletRequest request, ModelMap model){
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){			
			Transaction modifiedTrans = transService.getTransById(Integer.parseInt(request.getParameter("id")));
			if(modifiedTrans != null){
				model.addAttribute("users", userService.getAllUsers());
				model.addAttribute("books", bookService.getAvailableBooks()); //.add(modifiedTrans.getBook())
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
				modifiedTrans.setBook(bookService.getBookById(Integer.parseInt(request.getParameter("book"))));
				modifiedTrans.setUser(userService.getUserById(Integer.parseInt(request.getParameter("user"))));
				modifiedTrans.setDateOfBorrow(LocalDate.parse(request.getParameter("dateOfBorrow"), format));
				modifiedTrans.setExpectedDateOfReturn(LocalDate.parse(request.getParameter("expectedDateOfReturn"), format));
				transService.editTrans(modifiedTrans);
				return "redirect:edittrans?id=" + Integer.parseInt(request.getParameter("id"));	
			}
		}
		return "index";
	}
	
	
	@RequestMapping(value="/addtrans", method = RequestMethod.GET)
	public String newTrans(HttpServletRequest request, ModelMap model){
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){			
			model.addAttribute("users", userService.getAllUsers());
			model.addAttribute("books", (bookService.getAvailableBooks())); //.add(trans.getBook())
			return "addtrans";
		}
		return "index";
	}
	
	@RequestMapping(value="/addtrans", method = RequestMethod.POST)
	public String submitNewTrans(HttpServletRequest request, ModelMap model){
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){			
			Transaction newTrans = new Transaction();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
			newTrans.setBook(bookService.getBookById(Integer.parseInt(request.getParameter("book"))));
			newTrans.setUser(userService.getUserById(Integer.parseInt(request.getParameter("user"))));
			newTrans.setDateOfBorrow(LocalDate.parse(request.getParameter("dateOfBorrow"), format));
			newTrans.setExpectedDateOfReturn(LocalDate.parse(request.getParameter("expectedDateOfReturn"), format));
			transService.addTrans(newTrans);
			model.addAttribute("users", userService.getAllUsers());
			model.addAttribute("books", bookService.getAvailableBooks());
			return "redirect:addtrans";	
			
		}
		return "index";
	}
	
	@RequestMapping(value="/receive", method = RequestMethod.GET)
	public String receiveBooks(HttpServletRequest request, ModelMap model){
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){			
			model.addAttribute("trans", transService.activeTrans());
			return "returnbook";
		}
		return "index";
	}
	
	@RequestMapping(value="/receive", method = RequestMethod.POST)
	public String submitReceiveBooks(HttpServletRequest request, ModelMap model){
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){	
			if(request.getParameter("dateOfReturn").length() >= 8 && request.getParameterValues("receive")!=null){
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyy");
				LocalDate date = LocalDate.parse(request.getParameter("dateOfReturn"), format);
				String[] idString = request.getParameterValues("receive");
				List <Integer> ids = new ArrayList<Integer>();
				for(int i=0;i<idString.length;i++){
					ids.add(Integer.parseInt(idString[i]));
					//System.out.println(ids.get(i) + " - " + date);
				}

				transService.returnBooks(ids, date);
				return "redirect:receive";	
			}
			else{
				model.addAttribute("msg", "Please select the received books!");
				}
			
		}
		return "index";
	}
}
