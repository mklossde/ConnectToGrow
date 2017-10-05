package collabothon.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path( "login" )
public class UserService {

	  @GET
	  public String message() {
	    return "Yea! ";
	  }
}
