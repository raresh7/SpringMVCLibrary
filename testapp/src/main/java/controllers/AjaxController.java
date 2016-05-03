package controllers;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxController extends GenericController {
	
	@RequestMapping("/ajax")
	public String initAjax(ModelMap map){
		map.addAttribute("msg", "hei");
		return "smartselect";
	}
	
	@RequestMapping(value="/ajaxtest", method = RequestMethod.GET)
	public @ResponseBody String respondAjax(@RequestParam String param, ModelMap map){
		System.out.println("<br/> it is :" + new Date().toString());
		return "<br/>param is:" + param + ", it is :" + new Date().toString();
	}
	
	
//	@RequestMapping(value="/ajaxtest", method = RequestMethod.GET)
//	public @ResponseBody void respondAjax(@RequestParam String param, ModelMap model){
//		model.addAttribute("msg", "<br/>param is:" + param + ", it is :" + new Date().toString());
//	}
}
