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
import org.openon.simpleui.SimpleUIServlet.SimpleUIRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ctg.db.FirmaDO;

public class CtgBL {
	private static final Logger LOG=LoggerFactory.getLogger(CtgBL.class);
	
	public static final String STAMMDATEN="Stammdaten.csv";
	public static final String REGISTER="/data/Register.csv";
	public static final String MATCHES="/data/Matches.csv";
	public static final String KOOPERATIONEN="/data/Kooperationen.csv";
	
	public List<Map> registers;
	
	public List<Map> usecases;
	public List<String> likeList=new ArrayList<String>();
	
	public List<Map> matches;
	public List<String> matchList=new ArrayList<String>();
	
//	public List stammdaten;
	
	public boolean login(String user,String pas) {
		return user!=null && user.length()>1;
	}
	
	public Object getRegister(SimpleUIRequest req,String name) throws IOException {
		return find(getRegisters(req),"Firmenname",name);
	}
	
	public Object getMatch(SimpleUIRequest req,String name) throws IOException {
		return find(getMatches(req),"name",name);
	}
	
	public List<String> getMatch() { return matchList; }
	public void addMatch(String match) { if(!isMatch(match)) { this.matchList.add(match); } else { remove(matchList,match); }}
	public boolean isMatch(String match) {  return contains(matchList,match); }
	
	public List<String> getLikes() { return likeList; }
	public void addLike(String like) { 
		if(!isLike(like)) { 
			this.likeList.add(like); 
		} else { 
			remove(likeList,like); }}
	public boolean isLike(String like) { return contains(likeList,like); }

	//---------------------------------------------------------------------------
	
	
	public List<Map> getRegisters(SimpleUIRequest req) throws IOException {
		if(registers==null) { registers=read(req,REGISTER,";"); }
		return registers;
	}
	
	public List<Map> getMatches(SimpleUIRequest req) throws IOException {
		if(matches==null) { matches=read(req,MATCHES,";"); }
		return matches;
	}
	
	public List<Map> getUsecases(SimpleUIRequest req) throws IOException {
		if(usecases==null) { usecases=read(req,KOOPERATIONEN,";"); }
		return usecases;
	}
	
	//---------------------------------------------------------------------------
		
//	public List readStammdaten() throws IOException {
//		if(stammdaten!=null) { return stammdaten; }
//		stammdaten=new ArrayList();
//		InputStream in=SimpleUIServlet.getClassResourceStream(this, STAMMDATEN);
//		if(in==null) { throw new IOException("cant read "+STAMMDATEN); }
//		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
//		while(reader.ready()) {
//		     String csvLine = reader.readLine();
//		     FirmaDO f=FirmaDO.read(csvLine);
//		     if(f!=null) { stammdaten.add(f); LOG.debug("read "+f);}
//		}
//		reader.close();
//		return stammdaten;
//	}
	
	public List<Map> read(SimpleUIRequest req,String dbName,String tr) throws IOException {
		List<Map> list=new ArrayList<Map>();
//		InputStream in=SimpleUIServlet.getClassResourceStream(this, dbName);
		InputStream in=SimpleUIServlet.getThreadResourceStream(req, dbName);
		if(in==null) { throw new IOException("cant read "+dbName); }
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		
		String headLine = reader.readLine();
		String keys[]=headLine.trim().split(tr);
				
		while(reader.ready()) {
		     String csvLine = reader.readLine();
		     String values[]=csvLine.trim().split(tr);
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
	
	protected boolean contains(List<String> list,String name) {
		if(name==null) { return false; }
		for(int i=0;list!=null && i<list.size();i++) {
			if(name.equalsIgnoreCase(list.get(i))) { return true; }
		}
		return false;
	}
	
	protected boolean remove(List<String> list,String name) {
		if(name==null) { return false; }
		for(int i=0;list!=null && i<list.size();i++) {
			if(name.equalsIgnoreCase(list.get(i))) { list.remove(i); return true; }
		}
		return false;
	}
}
