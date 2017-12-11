package ctg;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;

import javax.servlet.ServletContext;

import org.openon.simpleui.SimpleUIServlet;
import org.openon.simpleui.SimpleUIServlet.SimpleUIRequest;
import org.openon.simpleui.SimpleUIServlet.UIWriter;
import org.openon.simpleui.components.UIComponent;
import org.openon.simpleui.components.UIComponentInterface;
import org.openon.simpleui.components.UITypes.Tree;
import org.openon.simpleui.theme.bootstrap.BootstrapUI;

public class CtgUI extends BootstrapUI {
	
	public static CtgUI instance() { return new CtgUI();}
	@Override public BootstrapUI instance(Writer wr,SimpleUIRequest request) throws IOException { 
		CtgUI newui=CtgUI.instance(); 
		newui.ui=this.ui.instance(wr, request); return newui; 
	} 
	
	protected CtgUI() { super();}
	
	@Override public void onStartup(ServletContext ctx) throws IOException {
		super.onStartup(ctx);
		ui.addResourcesWeb(ctx,"/css").addResourcesWeb(ctx, "/img").addResourcesWeb(ctx, "/js").addResourcesWeb(ctx, "/fonts");
		ui.addTopResource("/css/ctg.css");
		ui.addTopResource("/css/card.css");
		ui.addTopResource("/css/font-awesome.min.css");	
		
//		ui.addBottomResource("/js/jquery.js");
//		ui.addBottomResource("/js/popper.min.js");
//		ui.addBottomResource("/js/bootstrap.min.js");
		ui.addBottomResource("/js/ie10-viewport-bug-workaround.js");
	}
	
	//---------------------------------------------------------------------
	
	@UIWriter
	public void cNavMenu() throws IOException { 
		cNavMenu(ui.request().uiServlet().getPages());
	}
	
	public void cNavMenu(Tree<UIComponentInterface> menu) throws IOException {
		String menStart="<nav class=\"col-sm-3 col-md-2 d-none d-sm-block bg-light sidebar\"><ul class=\"nav nav-pills flex-column\">";
		ui.writeParse(menStart,this);
		
		for(int i=0;menu!=null && i<menu.size();i++) { 
			//Menu item
			if(menu.get(i) instanceof UIComponent) {
				UIComponent comp=(UIComponent)menu.get(i);
				String active=""; // "active";			
				ui.writeParse("<li class=\"nav-item\"><a class=\"nav-link ?[active]\" href=\"?[href|#]\">?[icon]",comp);
				ui.writeParse(" ?[label]<span class=\"sr-only\">(current)</span></a></li>",comp);
				
			}else if(menu.get(i) instanceof Tree) {
				Tree sub=(Tree)menu.get(i);
				String label=sub.getName();
				ui.write("<li class=\"nav-item dropdown\"><a class=\"nav-link dropdown-toggle\" data-toggle=\"dropdown\" href=\"#\" role=\"button\" aria-haspopup=\"true\" aria-expanded=\"false\">"+label+"</a><div class=\"dropdown-menu\">");
				for(int t=0;t<sub.size();t++) { 
					String link="<a class=\"dropdown-item\" href=\"?[href|#]\">?[label]</a>";
					ui.writeParse(link,sub.get(t));
				}
				ui.write("</div></li>");
			}
		}
		ui.write("</ul></nav> ");
	}
	
	@UIWriter
	public void cCard() throws IOException {
		ui().writeFile("ui/card.html", null);
	}

//      <li class="nav-item">
//        <a class="nav-link active" href="#"><i class="fa fa-gift" aria-hidden="true"></i> Wunsch AG <span class="sr-only">(current)</span></a>
//      </li>
//      <li class="nav-item">
//        <a class="nav-link" href="matches.html"><i class="fa fa-thumbs-o-up" aria-hidden="true"></i> Matches</a>
//      </li>
//      <li class="nav-item">
//        <a class="nav-link" href="#"><i class="fa fa-star" aria-hidden="true"></i>Favoriten</a>
//      </li>
//      <li class="nav-item">
//        <a class="nav-link" href="#"><i class="fa fa-search" aria-hidden="true"></i> Suche</a>
//      </li>
//      <li class="nav-item">
//        <a class="nav-link" href="#"><i class="fa fa-envelope" aria-hidden="true"></i> Postbox</a>
//      </li>

  
}
