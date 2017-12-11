package ctg.bl;

import java.util.ArrayList;
import java.util.List;

import ctg.db.StartupDO;

public class CtgBL {

	public List getStartups() {
		List list=new ArrayList();
		for(int i=0;i<5;i++) { list.add(new StartupDO().setLabel("Test AG "+i)); }
		return list;
	}
	
}
