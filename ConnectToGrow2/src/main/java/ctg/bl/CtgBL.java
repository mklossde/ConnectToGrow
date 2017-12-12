package ctg.bl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openon.simpleui.SimpleUIServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ctg.db.FirmaDO;

public class CtgBL {
	private static final Logger LOG=LoggerFactory.getLogger(CtgBL.class);
	
	public static final String STAMMDATEN="Stammdaten.csv";
	public static final String REGISTER="Register.csv";
	
	public List<Map> registers;
	public List stammdaten;
	
	public boolean login(String user,String pas) {
		return user!=null && user.length()>1;
	}
	
	public Object getRegister(String name) throws IOException {
		return find(getRegisters(),"Firmenname",name);
	}
	
	public List getStartups() throws IOException {
		return readStammdaten();
	}
	
	public List<Map> getRegisters() throws IOException {
		if(registers==null) { registers=read(REGISTER,";"); }
		return registers;
	}
	
	//---------------------------------------------------------------------------
		
	public List readStammdaten() throws IOException {
		if(stammdaten!=null) { return stammdaten; }
		stammdaten=new ArrayList();
		InputStream in=SimpleUIServlet.getClassResourceStream(this, STAMMDATEN);
		if(in==null) { throw new IOException("cant read "+STAMMDATEN); }
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		while(reader.ready()) {
		     String csvLine = reader.readLine();
		     FirmaDO f=FirmaDO.read(csvLine);
		     if(f!=null) { stammdaten.add(f); LOG.debug("read "+f);}
		}
		reader.close();
		return stammdaten;
	}
	
	public List<Map> read(String dbName,String tr) throws IOException {
		List<Map> list=new ArrayList<Map>();
		InputStream in=SimpleUIServlet.getClassResourceStream(this, dbName);
		if(in==null) { throw new IOException("cant read "+dbName); }
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		
		String headLine = reader.readLine();
		String keys[]=headLine.split(tr);
				
		while(reader.ready()) {
		     String csvLine = reader.readLine();
		     String values[]=csvLine.split(tr);
		     Map map=new HashMap();
		     for(int i=0;i<keys.length && i<values.length;i++) {
		    	 map.put(keys[i], values[i]);
		     }
		     if(map!=null) { list.add(map); LOG.debug("read "+map);}
		}
		reader.close();
		return list;
	}
	
	/** find one element in list of maps **/
	public Map find(List<Map> list,String key,String value) throws IOException {
		for(int i=0;i<list.size();i++) {
			Map map=list.get(i);
			if(map!=null && value.equals(map.get(key))) { return map; }
		}
		return null;
	}
	
}
