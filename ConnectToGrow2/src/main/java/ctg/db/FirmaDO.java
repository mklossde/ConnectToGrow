package ctg.db;

public class FirmaDO {

	public String id;
	public String name;
	
	public FirmaDO() {}
//	public FirmaDO setLabel(String label) { this.label=label; return this; }
	
	public String toString() {return "Firma id:"+id+" name:"+name; }
	
	//-----------------------------------
	
	public static FirmaDO read(String csvLine) {
		if(csvLine==null || csvLine.length()==0 || csvLine.startsWith("#")) { return null; }
		String a[]=csvLine.split(",");
		if(a.length<2 || a[0].length()==0) { return null; }
		FirmaDO f=new FirmaDO();
		f.id=a[0];
		f.name=a[1];
		return f; 
	}
}
