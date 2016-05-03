package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import config.MenuTab;
import dao.BookDAO;
import dao.StudentDAO;
import entities.Student;

@Controller
//@RequestMapping("/")
public class FirstController extends GenericController {

	@Autowired
    private StudentDAO service;
	
	@Autowired
	private BookDAO bookService;
	
	
	@RequestMapping(value="/", method = RequestMethod.GET)
    public String showOtherIndex(ModelMap model){
        
        model.addAttribute("msg", "This was sent from the default controller");
        return "zafirstpage";
    }
	
	@RequestMapping(value="/firstpage", method = RequestMethod.GET)
    public String showIndex(ModelMap model){
		model.addAttribute("tabsList", MenuTab.tabList);
		bookService.getAvailableBooks();
        return "index";
    }
	
	@RequestMapping(value="/header*", method = RequestMethod.GET)
    public String showHeader(ModelMap model){
        
        model.addAttribute("msg", "This was sent from the default controller");
        model.addAttribute("tabsList", MenuTab.tabList);
        return "../template/header";
    }
}
