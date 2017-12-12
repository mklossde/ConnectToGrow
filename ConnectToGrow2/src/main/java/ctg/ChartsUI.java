package ctg;

import java.io.IOException;

import org.openon.simpleui.SimpleUI;

public class ChartsUI {

	public ChartsUI() {}
	
	protected int width,height;
	protected String label;
	
	protected String[] labels;
	protected Object[] datas;
	
	public ChartsUI set(int width,int height) { this.width=width;this.height=height; return this; }
	public ChartsUI setLabel(String label) { this.label=label; return this; }
	
	public ChartsUI setLabels(String labels[]) { this.labels=labels; return this; }
	public ChartsUI setData(Object datas[]) { this.datas=datas; return this; }
	
	public void write(SimpleUI ui) throws IOException {
		ui.write("<script src=\"https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.min.js\"></script>");
		String id=ui.newId();
		ui.write("<canvas id=\""+id+"\"");
		if(width>0) { ui.write(" width=\""+width+"\""); }
		if(height>0) { ui.write(" height=\""+height+"\""); }
		ui.write("></canvas>");
		
		StringBuilder script=new StringBuilder("var ctx = document.getElementById('"+id+"').getContext('2d');"
			+"var chart = new Chart(ctx, {type: 'line',"
		    +"data: {");
//		        +"labels: [\"January\", \"February\", \"March\", \"April\", \"May\", \"June\", \"July\"],"
		
//		if(labels!=null) { 
//			script.append("labels: [");
//			for(int i=0;i<labels.length;i++) {
//				if(i>0) { script.append(','); }
//				script.append("\"").append(labels[i]).append("\"");
//			}
//			script.append("],");
//		}
		add(script,"labels",labels);
		script.append("datasets: [{"
		            +"label: \""+label+"\","
		            +"backgroundColor: 'rgb(255, 99, 132)',"
		            +"borderColor: 'rgb(255, 99, 132)',");
//		+"data: [0, 10, 5, 2, 20, 30, 45],"
		add(script,"data",datas);
		script.append("}]"
		       +"},"
		    +"options: {}"
		    +"});");
		
		ui.hScript(script.toString());
	}
	
	protected void add(StringBuilder script,String name,Object values[]) {
		if(values!=null) { 
			script.append(name).append(": [");
			for(int i=0;i<values.length;i++) {
				if(i>0) { script.append(','); }
				script.append("\"").append(values[i]).append("\"");
			}
			script.append("],");
		}
	}
}
