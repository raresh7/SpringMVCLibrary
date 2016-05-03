package config;

import org.springframework.stereotype.Component;


@Component
public class TesterClass {
	
//	@Autowired
//	static StudentDAO service;
	
	public static void main(String[] args){		
		
		MenuTab.readTabs();
		System.out.println(MenuTab.tabList.size());
	}
	
}