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
import org.openon.simpleui.plugin.chartjs.ChartJsUI;
import org.openon.simpleui.plugin.chartjs.ChartJsUI.Type;
import org.openon.simpleui.utils.UtilUI;
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
	public void landing(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("landing",null,null);
		ui.writeFile("ui/landing-page.html", comp);
	}
	
	@UIWriter
	public void useCards(CtgUI ui) throws IOException {
		List list=bl().getUsecases(ui.request());
		for(int i=0;i<list.size();i++) {
			UIComponentInterface comp=toComp(list.get(i));
			if(bl().isLike((String)comp.get("name"))) {comp.set("like", "like"); }else { comp.set("like", ""); }
			ui.writeFile("ui/landingCard.html", comp);
		}
	}
	
	@UIPage(label="likes") 
	public void likePage(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("like",null,null);
		ui.writeFile("ui/like-page.html", comp);
	}
	
	@UIWriter
	public void likeCards(CtgUI ui) throws IOException {
		List list=bl().getUsecases(ui.request());
		for(int i=0;i<list.size();i++) {
			UIComponentInterface comp=toComp(list.get(i));
			if(bl().isLike((String)comp.get("name"))) {
				comp.set("like", "like"); 
				ui.writeFile("ui/landingCard.html", comp);
			}else {
				comp.set("like", ""); 
			}
		}
	}
	
	@UIAction()
	public void like(CtgUI ui) throws IOException {
		bl().addLike(ui.request().getValueString("like",null));
	}
	
	
	//------------------------------------------------------------
	// matches
	
	@UIPage() 
	public void matches(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("matches",null,null);
		ui.writeFile("ui/matches.html", comp);
	}
	
	@UIPage() 
	public void matchesFound(CtgUI ui) throws IOException {
		UIComponent comp=UIComponent.labelLink("matchesFound",null,null);
		ui.writeFile("ui/matchesFound.html", comp);
	}
	
	@UIAction
	public void match(CtgUI ui) throws IOException {
		bl().addMatch(ui.request().getValueString("match",null));
	}
	
	
	@UIPage() 
	public void profile(CtgUI ui) throws IOException {
		String name=ui.request().getValueString("name",null);
		Object bean=bl().getMatch(ui.request(),name);
//		UIComponentInterface comp=new UIComponentWrapper(bean);
		UIComponentInterface comp=toComp(bean);
		ui.writeFile("ui/profile-card.html", comp);
	}
	
	/** app cards **/ 
	@UIWriter 
	public void cCards(CtgUI ui) throws IOException {
		List list=bl().getMatches(ui.ui().request());
		
		int count=-1;
		ui.write("<div class=\"row\">");
		for(int i=0;i<list.size();i++) {
			if(++count>2){ count=0; ui.write("</div><div class=\"row\">"); }
			UIComponentInterface comp=toComp(list.get(i));
			
			if(bl().isMatch((String)comp.get("name"))) {
				comp.set("match", "match"); 
			}else { comp.set("match", ""); }
			
//			<div class="chip">Fintech</div>
			ui.writeFile("ui/card.html", comp);
		}
		ui.write("</div>");
	}
	
	/** app cards **/ 
	@UIWriter 
	public void matchCards(CtgUI ui) throws IOException {
		List list=bl().getMatches(ui.ui().request());
		
		int count=-1;
		ui.write("<div class=\"row\">");
		for(int i=0;i<list.size();i++) {
			if(++count>2){ count=0; ui.write("</div><div class=\"row\">"); }
			UIComponentInterface comp=toComp(list.get(i));
			
			if(bl().isMatch((String)comp.get("name"))) {
				comp.set("match", "match"); 
				ui.writeFile("ui/card.html", comp);
			}else { comp.set("match", ""); }
			
		}
		ui.write("</div>");
	}
	
	
	
	//------------------------------------------------------------
	// register
	
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
		if(comp!=null) { UtilUI.toBean(ui.request(), comp); }
		ui.ui().servlet().setPage(ui.request(), "landing");
	}
	
	//-----------------------------------------------------------------------------------------------------------
	
	

	

	
	public UIComponentInterface toComp(Object bean) {
		UIComponentInterface comp=new UIComponentWrapper(bean);			
//		if(i==0) { comp.set("active", "active"); } // active first
		
		StringBuilder sb=new StringBuilder();
		String tagsString=(String)comp.get("tags");
		if(tagsString!=null) {
			String tags[]=tagsString.toString().split(",");
			for (String tag : tags) { sb.append("<div class=\"chip\">"+tag+"</div>"); }
			comp.set("tagdiv",sb.toString());
		}
		return comp;
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
		ChartJsUI chart=new ChartJsUI();
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
