package config;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


@Component
public class MenuTab {
	
	public static List<MenuTab> tabList = new ArrayList<MenuTab>();
	private String url;
	private String label;
	private int rank;
	private Boolean forAdmin;
	private int accessLevel;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	
	public static void readTabs(){
		try{
			File fXmlFile = new File("D:/raresr/Eclipse Workspace/testapp/src/main/java/config/Tabs.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			System.out.println(doc.getDocumentElement().getNodeName());
			
			NodeList tabListXml = doc.getElementsByTagName("tab");
			System.out.println("size: "+tabListXml.getLength());
			tabList.removeAll(tabList);
			for(int i = 0; i<tabListXml.getLength(); i++){
				System.out.println("\n");
				Node tab = tabListXml.item(i);
				if(tab.getNodeType() == Node.ELEMENT_NODE){
					Element elTab = (Element) tab;
					MenuTab newTab = new MenuTab();
					newTab.setAccessLevel(Integer.parseInt(elTab.getElementsByTagName("accessLevel").item(0).getTextContent()));
					newTab.setForAdmin(Boolean.parseBoolean(elTab.getElementsByTagName("forAdmin").item(0).getTextContent().toString()));
					newTab.setLabel(elTab.getElementsByTagName("label").item(0).getTextContent());
					newTab.setRank(Integer.parseInt(elTab.getElementsByTagName("rank").item(0).getTextContent()));
					newTab.setUrl(elTab.getElementsByTagName("url").item(0).getTextContent());
					tabList.add(newTab);			
					
				}
				else
					System.out.println("spanac");
				
			}
			
		}
	   catch (Exception e) {
			e.printStackTrace();
		    }
		
	}
	public Boolean getForAdmin() {
		return forAdmin;
	}
	public void setForAdmin(Boolean forAdmin) {
		this.forAdmin = forAdmin;
	}
	public int getAccessLevel() {
		return accessLevel;
	}
	public void setAccessLevel(int accessLevel) {
		this.accessLevel = accessLevel;
	}
}
