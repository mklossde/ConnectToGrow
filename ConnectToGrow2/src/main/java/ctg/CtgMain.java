package ctg;

import java.io.IOException;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.openon.simpleui.SimpleUIInterface;
import org.openon.simpleui.SimpleUIServlet;
import org.openon.simpleui.SimpleUIServlet.UIAction;
import org.openon.simpleui.components.UIComponent;
import org.openon.simpleui.components.UIComponentInterface;
import org.openon.simpleui.components.UIComponentWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ctg.bl.CtgBL;

/**
 * ConnectToGrow Main
 * 
 * 
 * 
 */
@WebListener @WebServlet(urlPatterns={"/ctg/*"}) // add this as a servlet for "/CONTEXTROOT/example"
public class CtgMain extends SimpleUIServlet {
	private static final Logger LOG=LoggerFactory.getLogger(CtgMain.class);
	
	protected CtgBL bl;
	public CtgBL bl() { if(bl==null) { bl=new CtgBL(); } return bl; }
	
	/** change ui **/
	@Override public CtgUI instanceUI() { return CtgUI.instance(); }
//	@Override public BootstrapUI instanceUI() { return BootstrapUI.instance(); }
	
/**
 * TODO:
 *		index=true => login page ??
 */
	//----------------------------------------------------------------------------------------
	
	@UIPage(index=true,order=0) 
	public void login(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("login",	null,null);
		ui.writeFile("ui/login.html", comp);
	}
	
	@UIAction(cmd="Get Started")
	public void doLogin(CtgUI ui) throws IOException {
		ui.ui().servlet().setPage(ui.ui().request(), "matches");		
	}
	
	@UIPage() 
	public void error(CtgUI ui) throws IOException {
		String message=ui.ui().request().message();
		UIComponent comp=UIComponent.labelLink(message,null,null);
		ui.writeFile("ui/error.html", comp);
	}
	
	//------------------------------------------------------------
	
	@UIPage() 
	public void matches(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("login",	null,null);
		ui.writeFile("ui/matches.html", comp);
	}
	
	
	@UIPage() 
	public void profil(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("login",	null,null);
		ui.writeFile("ui/cPage.html", comp);
	}
	
	@UIPage() 
	public void select(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("login",	null,null);
		ui.writeFile("ui/cPage.html", comp);
	}
	

	
	@UIPage() 
	public void setcard(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("login",	null,null);
		ui.writeFile("ui/cPage.html", comp);
	}
	
	//-----------------------------------------------------------------------------------------------------------
	

	
	//-----------------------------------------------------------------------------------------------------------
	
	/** app cards **/ 
	@UIWriter 
	public void cCards(CtgUI ui) throws IOException {
		ui.writeFile("ui/sCards.html", null);
		List list=bl().getStartups();
		for(int i=0;i<list.size();i++) {
//			UIComponent comp=UIComponent.labelLink("Test "+i, null, null);
			UIComponentInterface comp=new UIComponentWrapper(list.get(i));			
			if(i==0) { comp.set("active", "active"); } // active first
			ui.writeFile("ui/card.html", comp);
		}
		ui.writeFile("ui/eCards.html", null);
	}
	
	//-----------------------------------------------------------------------------------------------------------

	

	
}
