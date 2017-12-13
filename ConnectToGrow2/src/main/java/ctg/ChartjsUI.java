package ctg;

import java.io.IOException;

import org.openon.simpleui.SimpleUI;

public class ChartjsUI {

	public ChartjsUI() {}
	
	public enum Type { line,bar,radar,pie,doughnut,polarArea,bubble }
	
	protected int width,height;
	protected String label;
	
	protected String[] labels;
	protected Object[] datas;
	protected Object[] backgroundColors;
	protected Object[] borderColors;
	protected String type;
	
	protected String data="";
	protected String options="";;
	
	public void setOptions(String options) { this.options=options; }
	public void setData(String data) { this.data=data; }
	
	public void setType(String type) { this.type=type; }
	public void setType(Type type) { this.type=type.toString(); }
	
	public ChartjsUI set(int width,int height) { this.width=width;this.height=height; return this; }
	public ChartjsUI setLabel(String label) { this.label=label; return this; }
	
	public ChartjsUI setLabels(String labels[]) { this.labels=labels; return this; }
	public ChartjsUI setData(Object datas[]) { this.datas=datas; return this; }
	
	public ChartjsUI setBackgroundColors(Object backgroundColors[]) { this.backgroundColors=backgroundColors; return this; }
	public ChartjsUI setBorderColors(Object borderColors[]) { this.borderColors=borderColors; return this; }
	
	public void write(SimpleUI ui) throws IOException {
		ui.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js\"></script>");
		String id=ui.newId();
		ui.write("<div style=\"width: "+width+"px;height: "+height+"px;\">");
		ui.write("<canvas id=\""+id+"\"");
		if(width>0) { ui.write(" width=\""+width+"\""); }
		if(height>0) { ui.write(" height=\""+height+"\""); }
		ui.write("></canvas>");
		ui.write("</div>");
		
		StringBuilder script=new StringBuilder("var ctx = document.getElementById('"+id+"').getContext('2d');"
			+"var chart = new Chart(ctx, {type: '"+type+"',data: {");
		add(script,"labels",labels);
		script.append("datasets: [{"
		            +"label: \""+label+"\",");
//		+"backgroundColor: 'rgb(255, 99, 132)',"
		add(script,"backgroundColor",backgroundColors);
//		+"borderColor: 'rgb(255, 99, 132)',");
		add(script,"borderColor",borderColors);
		            
		add(script,"data",datas);
		if(data!=null) { script.append(data); }
		script.append("}]},options: {}");
		if(options!=null) { script.append(options); }
		script.append("});");
		
		ui.hScript(script.toString());
	}
	
	protected void add(StringBuilder script,String name,Object values[]) {
		if(values==null) {	
		}else if(values.length==1) {
			script.append(name).append(": \""+values[0]+"\",");
		}else {
			script.append(name).append(": [");
			for(int i=0;i<values.length;i++) {
				if(i>0) { script.append(','); }
				script.append("\"").append(values[i]).append("\"");
			}
			script.append("],");
		}
	}
}
