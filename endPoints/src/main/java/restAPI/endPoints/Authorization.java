package restAPI.endPoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Authorization {

	// might need to change constructor later on
	// maybe make this static?
	// also private
	private static DataStore DBConnection = new DataStore();
	
	//Authorizes a new login from a person
	@RequestMapping(value="/OAuth")
	@ResponseBody
	
	// Currently requestes a general JsonFormats
	// should be revised to include a json with username and password
	// Not exactly sure what this should return back after the function
	// call
	// will check docs and maybe consult frontend
	public String OAuth(@RequestBody JsonFormats JF)
	{
		DBConnection.authorize(JF);
		return "Authorization";
	}
	
	
	
}
