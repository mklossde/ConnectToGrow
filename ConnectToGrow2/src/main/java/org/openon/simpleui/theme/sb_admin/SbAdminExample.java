package org.openon.simpleui.theme.sb_admin;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.openon.simpleui.SimpleUIServlet;
import org.openon.simpleui.io.IOWriterJson;
import org.openon.simpleui.utils.UtilUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Define a UI-Servlet via @WebListener and @WebServlet and extends class UIServlet 
 * 		@WebListener @WebServlet(urlPatterns={"/SERVLETPATH/*"},loadOnStartup=2)
 *		public class MYSERVLET extends SimpleUIServlet {.. }
 *
 * or via web.xml
 *    <servlet>	
 *		<servlet-name>uiexample</servlet-name>
 *      <servlet-class>test.example.ExampleUIServlet</servlet-class>
 *	  </servlet>
 *    <servlet-mapping>
 *		<servlet-name>uiexample</servlet-name>
 *		<url-pattern>/SERVLETPATH/*</url-pattern>
 *	  </servlet-mapping>
 *    <listener>
 *       <listener-class>test.example.ExampleUIServlet</listenerclass>
 *    </listener>
 * 
 * Call the application via http://localhsot:8080/APPNAME/SERVLETPATH
 * 
UI Basics:
 * 		SimpleUIServlet => Servlet to handle request/repsonse
 * 		SimpleUI => Painter for UI style
 * 
 * 		@UIPage define pages and added it to menu
 *  	@UIAction define actions (or Lamda => new Action(){ public boolean cmd(SimpleUI cp) throws Exception { ... } )
 *  	@UIInput define input-handler/validators 
 * 		@UISecure secures class/pages/actions via user/permissions/stage
 * 
 * 
 */
//@WebListener @WebServlet(urlPatterns={"/sbadmin/*"}) // add this as a servlet for "/CONTEXTROOT/example"
public class SbAdminExample extends SimpleUIServlet {
	private static final Logger LOG=LoggerFactory.getLogger(SbAdminExample.class);
	
	/** change ui **/
	@Override public SbAdminUI instanceUI() { return new SbAdminUI(); } 
	
	@UIPage (label="example/xxx")
	public void Info2(SbAdminUI ui) throws IOException {
		ui.page().head();	
		ui.h1("info2",null);
		ui.close();
	}
	
	@UIPage (label="example/info")
	public void Info(SbAdminUI ui) throws IOException {
//		SbAdminUI ui=(SbAdminUI)req.ui();
		ui.page().head();				
		
//		ui.write("<div class=\"alert alert-primary\" role=\"alert\">MyAlert</div>");
//		ui.cMessage("myError here");
		
//		new UIButton().label("myButton").write(ui);
//		new UIButton().href(ui.toLink("action")).label("myLink").size("sm").disabled(true).write(ui);
		
//		ui.addScriptSrc("https://code.jquery.com/ui/1.12.1/jquery-ui.js"); 		
//		ui.addScript("$( function() { var availableTags = [\"ActionScript\",\"BASIC\"]; $( \"#tags\" ).autocomplete({ source: availableTags }); } );");				
//		ui.write("<div class=\"ui-widget\"><label for=\"tags\">Tags: </label><input id=\"tags\"></div>");
		
//		ui.addScriptSrc("https://code.jquery.com/ui/1.12.0-rc.1/jquery-ui.min.js");
//		ui.write("<select class=\"select-tree\"><option>Trees</option><option>Pine</option><option>Oak</option> <option>Maple</option></select>;");
//		ui.addScript("$(function(){ $( \".select-tree\" ).selectmenu({ icons: { button: \"glyphicon glyphicon-tree-conifer\" } });)");
		
//		ui.write("<button id=\"button_1\" value=\"val_1\" name=\"but1\">button 1</button>");
//		ui.addScript("$(\"button\").click(function(e) { e.preventDefault(); "
//				+"$.getJSON(\""+ui.toApp("myFirst")+"\",{'id': 'qwert'} ,function( data ) { alert('x:'+data.result); });"
//        	+"});");
		
//		ui.sCard();
//		ui.eCard();
		
		ui.ui().hH1("Info");
		
		ui.sCard("InfoCard", null);
		
		ui.sRow();
		ui.sCol(); ui.cInputText("Text",null, null,false); ui.eCol();
		ui.sCol(); ui.cInputText("Text2",null, null,false); ui.eCol();
		ui.eRow();
//		ui.inputText("Text",null, null,true);
//		ui.plainText("Text",null, null);
//		ui.inputEmail("Email", null, null);
//		ui.inputPassword("Pas", null, null);
//		ui.inputCheckbox("Check", null);
//		ui.inputCheckbox("Check2", null, false);
//		ui.inputFile("myFile", "pp", "des");
//		ui.inputRadio("radio","aa",null,false);
//		ui.inputRadio("radio","bb", null,false);
//		ui.inputRadio("radio","cc", null,true);
//		ui.inputRadio("rr",new Number[]{1,2,3}, null,false);
//		
//		ui.size("sm");
//		ui.inputSelect("mySel", new String[]{"aaa", "bbb","ccc"},false,"info","description",true);
//		ui.inputTextarea("myarea", 3, "place", "dddd",true,false);

		ui.cButton("doit");
		
//		ui.writeComponent("form", null);
		
		ui.eCard(null);
		
		ui.close();
	}
	
	@UIAction(param={"req","search"})
	public void search(SimpleUIRequest req,String searchFor) throws IOException {
		LOG.debug("unimplemented search for "+searchFor);
	}
	
	@UIAction
	public void doit(SimpleUIRequest req) throws IOException {
		ExampleBean bean=new ExampleBean();
		UtilUI.toBean(req,bean);
System.out.println("bean:"+bean);		
	}
	
//	@UIWriter
//	public void nav()  throws IOException {
//System.out.println("nav...");
//	}
	
	@UIAction(param={"REQUEST","id","1"})
	public void myFirst(SimpleUIRequest ui,String id,String qqq) throws IOException {
System.out.println("action:"+id+" qqq:"+qqq);
		Writer wr=ui.getWriter("application/json");
		wr.write(IOWriterJson.toString("result","myResult "+id));
		wr.close();ui.close();
	}
	
	@UIPage(order=-1) /** login page on empty page with no menu entry **/
	public void login(SbAdminUI ui) throws IOException {
		ui.page();
		ui.ui().write("login");
		ui.writeFile("ui/cardLogin.html", null);
		ui.close();
	}
	
	@UIPage(label="My Test",icon="area-chart")
	public void myTest(SbAdminUI ui) throws IOException {
		ui.page().head();
		
		ui.sCard("Card_start", ui.toIcon("dashboard"));
		ui.ui().write("myTest");
		ui.eCard(null);
		
		ui.cChart("MyChart",ui.toIcon("area-chart"),"aktuell");
		
		ui.close();
	}
	
	@UIAction
	public void action(SimpleUIRequest ui) throws IOException {
System.out.println("action:");		
	}
}
