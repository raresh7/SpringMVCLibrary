package controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import config.MenuTab;
import dao.BookDAO;
import entities.Book;
import entities.User;

@Controller
public class BookController {
	
	@Autowired
	private BookDAO bookService;
	
	@RequestMapping(value="/allbooks*")
	public String logout(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			if(request.getParameter("delete")!=null)
				bookService.deleteBook(Integer.parseInt(request.getParameter("delete")));
			model.addAttribute("books", bookService.getAllBooks());
			return "allbooks";
		}
		else
			return "index";
	}
	
	@RequestMapping(value="/editbook*", method = RequestMethod.GET)
	public String editBook(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			Book editBook = bookService.getBookById(Integer.parseInt(request.getParameter("id")));
			if(editBook != null){
				model.addAttribute("book", editBook);			
				return "editbook";
			}
			
		}
		return "redirect:allbooks";
	}
	
	@RequestMapping(value="/addbook", method = RequestMethod.GET)
	public String addBook(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){			
				return "addbook";
			}
			
		return "redirect:index";
	}
	
	@RequestMapping(value="/addbook*", method = RequestMethod.POST)
	public String submitEditBook(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			Book newBook = new Book();
			newBook.setAuthor(request.getParameter("author"));
			newBook.setIsbn(request.getParameter("isbn"));
			newBook.setState(request.getParameter("state"));
			newBook.setTitle(request.getParameter("title"));
			bookService.addBook(newBook);
			return "redirect:addbook";
		}
		return "redirect:allbooks";
	}
	
	@RequestMapping(value="/editbook*", method = RequestMethod.POST)
	public String submitNewBook(HttpServletRequest request, ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		if(((User)request.getSession().getAttribute("loggedUser")).getAdmin()){
			Book modifiedBook = bookService.getBookById(Integer.parseInt(request.getParameter("id")));
			if(modifiedBook != null){
				modifiedBook.setAuthor(request.getParameter("author"));
				modifiedBook.setIsbn(request.getParameter("isbn"));
				modifiedBook.setState(request.getParameter("state"));
				modifiedBook.setTitle(request.getParameter("title"));
				bookService.editBook(modifiedBook);
				return "redirect:editbook?id=" + Integer.parseInt(request.getParameter("id"));
			}
		}
		return "redirect:allbooks";
	}
	
}
