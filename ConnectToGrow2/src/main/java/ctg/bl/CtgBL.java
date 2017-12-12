package ctg.bl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.openon.simpleui.SimpleUIServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ctg.db.FirmaDO;
import ctg.db.StartupDO;

public class CtgBL {
	private static final Logger LOG=LoggerFactory.getLogger(CtgBL.class);
	
//	public static final String STAMMDATEN="/data/Stammdaten.csv";
	public static final String STAMMDATEN="Stammdaten.csv";
	
//	public static void main(String[] args) throws Exception {
//		CtgBL bl=new CtgBL();
//		bl.readStartups();
//		System.out.println("end");
//	}
	
	public boolean login(String user,String pas) {
		return user!=null && user.length()>1;
	}
	
	public List getStartups() throws IOException {
//		List list=new ArrayList();
//		for(int i=0;i<6;i++) { list.add(new FirmaDO()); }
//		return list;
		return readStammdaten();
	}
	
	public List readStammdaten() throws IOException {
		List list=new ArrayList();
		InputStream in=SimpleUIServlet.getClassResourceStream(this, STAMMDATEN);
		if(in==null) { throw new IOException("cant read "+STAMMDATEN); }
		BufferedReader reader=new BufferedReader(new InputStreamReader(in));
		while(reader.ready()) {
		     String csvLine = reader.readLine();
		     FirmaDO f=FirmaDO.read(csvLine);
		     if(f!=null) { list.add(f); LOG.debug("read "+f);}
		}
		reader.close();
		return list;
	}
}
