package org.openon.simpleui.plugin.message;

import java.io.IOException;

import org.openon.simpleui.SimpleUIServlet.UIAction;
import org.openon.simpleui.SimpleUIServlet.UIWriter;
import org.openon.simpleui.theme.bootstrap.BootstrapUI;

public class MessageUI {
 
	public MessageUI() {  }

	//------------------------------------------------------------
	
	@UIAction
	public void messageSend(BootstrapUI ui) {
System.out.println("send message");	
	}
	
	//------------------------------------------------------------
	
	/** create a Message Send Card **/
	@UIWriter
	public MessageUI messageNew(BootstrapUI ui) throws IOException {
		ui.sCard("?[label|Message]");
		ui.cInputText("?[subject|Subject]", "?[info|Info]", "?[describtion]", true);
		ui.cInputText("?[from|From]", "from@addr.com", "?[fromDescribtion]", true);
		ui.cInputText("?[to|To]", "to@addr.com", "?[toDescribtion]", true);
		ui.cInputTextarea("?[text|Text", 10, "some text", null, true);
		ui.cButton("?[send|Send]");
		return this;
	}
	
}
