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
import org.openon.simpleui.utils.UtilUI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ctg.ChartjsUI.Type;
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
		String user=ui.ui().request().getValueString("name", null);
		String pas=ui.ui().request().getValueString("pas", null);
		if(bl().login(user,pas)) {
			ui.ui().request().setUserId(user);
			ui.ui().servlet().setPage(ui.ui().request(), "matches");		
		}else {
			ui.ui().request().setUserId(null);
			ui.ui().servlet().setPage(ui.ui().request(), "login");
		}
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
	
	//------------------------------------------------------------
	
	@UIPage() 
	public void register(CtgUI ui) throws IOException {
//		UIComponent comp=UIComponent.labelLink("register",	null,null);
		String name=ui.ui().request().getValueString("name", "my");
		ui.ui().request().set("selected", name);
		Object comp=bl().getRegister(ui.request(),name);
		ui.writeFile("ui/register.html", comp);
	}
	
	@UIAction
	public void Speichern(CtgUI ui) throws IOException {
		String name=ui.ui().request().get("selected");
		Object comp=bl().getRegister(ui.request(),name);
		UtilUI.toBean(ui.ui().request(), comp);
		ui.ui().servlet().setPage(ui.ui().request(), "matches");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	/** app cards **/ 
	@UIWriter 
	public void cCards(CtgUI ui) throws IOException {
		List list=bl().getMatches(ui.ui().request());
		
		int count=-1;
		ui.write("<div class=\"row\">");
		for(int i=0;i<list.size();i++) {
			if(++count>2){ count=0; ui.write("</div><div class=\"row\">"); }
			UIComponentInterface comp=new UIComponentWrapper(list.get(i));			
			if(i==0) { comp.set("active", "active"); } // active first
			ui.writeFile("ui/card.html", comp);
		}
		ui.write("</div>");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	@UIPage() 
	public void test(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("login",	null,null);
		ui.writeFile("ui/page.html", comp);
	}
	
	@UIWriter
	public void cChart(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("Charts",	null,null);
//		ui.writeFile("ui/chart.html", comp);
		ChartjsUI chart=new ChartjsUI();
		chart.setLabels(new String[]{"eins","zwei","drei"});
		chart.setType(Type.doughnut);
		chart.set(400, 400);
		chart.setData(new Object[]{50, 10, 5});
		chart.setBackgroundColors(new Object[]{"rgba(255, 99, 132, 0.2)","rgba(54, 162, 235, 0.2)"});
		chart.setBorderColors(new Object[]{"black"});
		chart.setLabel("myChart");
		chart.write(ui.ui());
	}
	
	//-----------------------------------------------------------------------------------------------------------

	

	
}
