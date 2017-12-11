package org.openon.simpleui.theme.sb_admin;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletContext;

import org.openon.simpleui.SimpleUI;
import org.openon.simpleui.SimpleUIServlet;
import org.openon.simpleui.SimpleUIServlet.SimpleUIRequest;
import org.openon.simpleui.SimpleUIServlet.UIWriter;
import org.openon.simpleui.components.UIComponent;
import org.openon.simpleui.components.UIComponentInterface;
import org.openon.simpleui.components.UITypes.Tree;
import org.openon.simpleui.theme.bootstrap.BootstrapUI;

/**
 * 
 * Components:
 * 		- Page
 * 		- Nav (Menu)
 * 		- Message (Error/Info)
 * 		- Card (Panel/From)
 * 		- inputText/inputCheckbox,inputSelect,inputTextarea
 * 
 * 		- Search
 * 		- messsageBox / emailBox (info)
 * 		- inputEmail/inptuPassword/inputNumber
 *
 *
 * TODO: 
 * 		Menu not sorted, without index
 * 		multiply select not transferd - input onyl hande one er key
 * 		multiply checkboxed - in one row, like radio and transfer like select
 * 		inputFile - multipart missing for file
 * 
 * 
 */
public class SbAdminUI extends BootstrapUI {

	public SbAdminUI() { super(); } 
	
	//------------------------------
//	public static SbAdminUI instance() {
//		SbAdminUI ui=new SbAdminUI();
//		ui.ui=new SimpleUI();
//		return ui;
//	}
	
	@Override public void onStartup(ServletContext ctx) throws IOException  {
		ui=new SimpleUI();
		ui.onStartup(ctx);
	}
	
	//------------------------------
	
	/** instance new ui **/
	@Override public SbAdminUI instance(Writer wr,SimpleUIRequest request) throws IOException { 
		SbAdminUI ui=new SbAdminUI();
		ui.ui=ui.ui.instance(wr, request);
		return ui;
	}
		
	//-------------------------------------------------------------------------------------------
	
	@Override public SbAdminUI page() throws IOException  {	
		ui.isPage=true;
		String title=(String)ui.request().appMap().get(SimpleUIServlet.ATTR_APP_TITLE);
		ui.writeFile("ui/Page_start.html",new UIComponent("label",title));
		
		//<body class="bg-dark">

		return this;
	}
	        
	@Override public SbAdminUI head() throws IOException  {
		ui.isHead=true; if(!ui.isPage) { page(); }
boolean haveHead=true;		
		if(haveHead) {
			ui.write("<body class=\"fixed-nav sticky-footer bg-dark\" id=\"page-top\">");
			nav();
			ui.write("<div class=\"content-wrapper\">");			
		}else {			
			ui.write("<body class=\"bg-dark\">");
		}
//		sContainer(haveHead);
		return this;
	}
	
	
	
	@Override public SbAdminUI bottom() throws IOException  {
//		eContainer(ui.haveHead());
		if(ui.isHead) {
			ui.isHead=false;
			ui.writeFile("ui/Page_bottom.html",null); 
		}
		cMessage();
		ui.writeFile("ui/Page_script.html",null); 
		ui.write(ui.request().script()); 
		
		ui.writeFile("ui/Page_end.html",null); 
		ui.isPage=false; return this;
	}
	
	//-------------------------------------------------------------------------------------------
	
	@UIWriter public SbAdminUI nav()  throws IOException {
		String title=(String)ui.request().appMap().get(SimpleUIServlet.ATTR_APP_TITLE);
		Tree<UIComponentInterface> menus=ui.request().uiServlet().getPages();
		return nav(title, menus);
	}
	
//TODO: multi level tree	
//TODO: icon of tree	
	public SbAdminUI nav(String title,Tree<UIComponentInterface> menus)  throws IOException {
		String id=newId(),idAccordion=id+"Accordion";
		ui.writeFile("ui/Nav_start.html",new UIComponent(UIComponent.LABEL,title).set(UIComponent.ID, id));
		for(int i=0;i<menus.size();i++) { 	
			Object comp=menus.get(i);
			if(comp instanceof UIComponentInterface) {
//				writeUi("ui/Nav_item.html",comp); 
//				String label=(String)((UIComponentInterface)comp).get(UIComponent.LABEL);
//				Object href=((UIComponentInterface)comp).get(UIComponent.HREF);
//				Object icon=(String)((UIComponentInterface)comp).get(UIComponent.ICON);
				String template="<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\"?[label]\">"
						+"<a class=\"nav-link\" href=\"?[href]\">?[icon]\n<span class=\"nav-link-text\">?[label]</span></a></li>";
				ui.writeParse(template,comp);
			}else if(comp instanceof Tree) {
				Tree sub=(Tree)comp;
				String label=sub.getName(),icon=null;
//				String label=(String)((UIComponentInterface)comp).get(UIComponent.LABEL);	
//				String icon=(String)((UIComponentInterface)comp).get(UIComponent.ICON);
				String subId=id+"Collapse";
				ui.write("<li class=\"nav-item\" data-toggle=\"tooltip\" data-placement=\"right\" title=\""+label+"\">");
				ui.write("<a class=\"nav-link nav-link-collapse collapsed\" data-toggle=\"collapse\" href=\"#"+subId+"\" data-parent=\"#"+idAccordion+"\">");				
				if(icon!=null) { cIcon(icon); }
		        ui.write("<span class=\"nav-link-text\">"+label+"</span></a><ul class=\"sidenav-second-level collapse\" id=\""+subId+"\">");
		        for(int t=0;t<sub.size();t++) { 
					String link="<li><a href=\"?[href]\">?[label]</a></li>";
					ui.writeParse(link,sub.get(t));
				}
				ui.write("</ul></li>");			
			}
		}
		ui.writeFile("ui/Nav_end.html",null);
		return this;
	}
	
	
	//-------------------------------------------------------------------------------------------
	
	/** start a card (and form) **/
	public SbAdminUI sCard(String label,String icon) throws IOException {
		ui.sForm();
		String template="<div class=\"card mb-3\"><div class=\"card-header\">\n?[icon]\n?[label]</div><div class=\"card-body\">";
		ui.writeParse(template, UIComponent.labelLink(label, null,icon));
		return this;
	}
	
	/** start a card (and form) **/
	public SbAdminUI eCard(Object bottom) throws IOException {
		if(bottom!=null) { ui.write("</div><div class=\"card-footer small text-muted\">"); ui.write(bottom); ui.write("</div></div>"); }
		ui.write("</div></div>"); 
		ui.eForm();
	    return this;		
	}
	
	public SbAdminUI cChart(String label,String icon,Object bottom) throws IOException {		
//     	<iframe class="chartjs-hidden-iframe" style="display: block; overflow: hidden; border: 0px none; margin: 0px; top: 0px; left: 0px; bottom: 0px; right: 0px; height: 100%; width: 100%; position: absolute; pointer-events: none; z-index: -1;" tabindex="-1"></iframe>
//        <canvas id="myAreaChart" width="808" height="242" tyle="display: block; width: 808px; height: 242px;"></canvas>
		sCard(label,icon);
		writeFile("ui/chart.html",null);
		eCard(bottom);
		return this;
	}

	
}
