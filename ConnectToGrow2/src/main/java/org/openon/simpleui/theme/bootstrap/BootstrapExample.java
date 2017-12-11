package org.openon.simpleui.theme.bootstrap;

import java.io.IOException;

import org.openon.simpleui.SimpleUIServlet;
import org.openon.simpleui.components.UITypes.VisibleType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleUI with Bootstrap - Example
 * 
 * 
 * 
 */
//@WebListener @WebServlet(urlPatterns={"/bootstrap/*"}) // add this as a servlet for "/CONTEXTROOT/example"
public class BootstrapExample extends SimpleUIServlet {
	private static final Logger LOG=LoggerFactory.getLogger(BootstrapExample.class);
	
	/** change ui **/
	@Override public BootstrapUI instanceUI() { return new BootstrapUI(); } 
	
	//-----------------------------------------------------------------------------------------------------------
	
//	@UIPage(index=true) /** start page **/
//	public void Start(BootstrapUI ui) throws IOException {
//		ui.sPage(true); // start a new Page
//		ui.sCard(null,null);
//		ui.h1(this.getClass().getSimpleName(), null);
//		ui.eCard(null);
//		ui.ePage().close(); // End of page and close 
//	}
	
	@UIPage(index=true) 
	public void test(BootstrapUI ui) throws IOException {
		ui.page().head().sContainer(false); // start a new Page
//		ui.sContainer(false); 
		ui.sCard(null);
		ui.h1(this.getClass().getSimpleName(), null);
		ui.eCard(null);
		ui.eContainer(false).bottom().close(); // End of page and close
//		ui.close();
	}
	
	
	
	@UIPage(label="Examples/Formular")
	public void formPage(BootstrapUI ui) throws IOException {
		ui.page().head().sContainer(false); // start a new Page
		
		ui.sCard("FormCard","Exampel of forumlar inputs",null); // Card with form
		
		ui.cInputText("Text",null, null,true);
		ui.cInputEmail("Email", "enter email here", "Email as prim. kontakt", VisibleType.normal);
		ui.cInputPassword("Password", "password", "password for login", VisibleType.normal);
		ui.cInputCheckbox("Checkbox", "Choxbox-input", false, VisibleType.normal);
		ui.cInputFile("File-Upload", "given file", "for file uploads", VisibleType.normal);
		ui.cInputRadio("RadioOne", new Object[]{"one","two","three"}, "radio selection", VisibleType.normal);
		
		ui.eCard(null); 
		
		ui.eContainer(false).bottom().close(); // End of page and close		
	}
	
	@UIPage(label="Examples/Messages")
	public void messages(BootstrapUI ui) throws IOException {
		ui.page().head().sContainer(false); // start a new Page
		ui.ui().request().addMesassge("Message");
		ui.ui().request().addMesassge("Error");
		ui.eContainer(false).bottom().close(); // End of page and close	
	}
	
	@UIPage(label="Examples/Base")
	public void exmaple(BootstrapUI ui) throws IOException {
		ui.page().head().sContainer(false); // start a new Page
		
		ui.sCard("Examples"); 
		ui.h1("H1 text", "with info");
		ui.h2("H2 text", "with info");
		ui.h3("H3 text", "with info");
		ui.h4("H4 text", "with info");
		ui.h5("H5 text", "with info");
		ui.h6("H6 text", "with info");
		ui.eCard(null);
		
		ui.sCard("Button"); 
		ui.cButton("a button");
		ui.eCard(null);
		
		ui.sCard("Text"); 
		ui.cPlainText("Plaintext",null, null);
		ui.eCard(null);
		
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

	
		
//		ui.sRow();
//		ui.sCol(); ui.cInputText("Text",null, null,false); ui.eCol();
//		ui.sCol(); ui.cInputText("Text2",null, null,false); ui.eCol();
//		ui.eRow();
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

//		ui.cButton("doit");
		
//		ui.writeComponent("form", null);
		
//		ui.eCard(null);
		
		ui.eContainer(false).bottom().close(); // End of page and close	
	}
	

}
