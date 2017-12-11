package ctg;

import java.awt.Component;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;

import org.openon.simpleui.SimpleUIServlet;
import org.openon.simpleui.SimpleUIServlet.UIWriter;
import org.openon.simpleui.components.UIComponent;
import org.openon.simpleui.components.UIComponentInterface;
import org.openon.simpleui.components.UIComponentWrapper;
import org.openon.simpleui.theme.bootstrap.BootstrapUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ctg.bl.CtgBL;
import ctg.db.StartupDO;

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
	
	//-----------------------------------------------------------------------------------------------------------
	
	@UIPage(label="Wunsch AG",icon="gift",order=0) 
	public void Dashboard(CtgUI ui) throws IOException {
//		ui.sPage(true); // start a new Page
//		ui.sCard(null);
//		ui.h1(this.getClass().getSimpleName(), null);
//		ui.eCard(null);
//		ui.ePage().close(); // End of page and close
		ui.writeFile("ui/dashboard.html", null);
		
		//?[component.head]
	}

	@UIPage(icon="thumbs-o-up",order=1) 
	public void Matches(CtgUI ui) throws IOException {
		ui.writeFile("ui/dashboard.html", null);
	}
	
	@UIPage(icon="star",order=2) 
	public void Favoriten(CtgUI ui) throws IOException {
		ui.writeFile("ui/dashboard.html", null);
	}
	
	@UIPage(icon="search",order=3) 
	public void Suche(CtgUI ui) throws IOException {
		ui.writeFile("ui/dashboard.html", null);
	}
	
	@UIPage(icon="envelope",order=4) 
	public void Postbox(CtgUI ui) throws IOException {
		ui.writeFile("ui/dashboard.html", null);
	}
	
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
