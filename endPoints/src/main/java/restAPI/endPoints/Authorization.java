package restAPI.endPoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins="*")
public class Authorization {

	// might need to change constructor later on
	// maybe make this static?
	// also private
	private static DataStore DBInstance = new DataStore(65);
	
	//Authorizes a new login from a person
	@RequestMapping(value="/OAuth")
	@ResponseBody
	
	// Currently requestes a general JsonFormats
	// should be revised to include a json with username and password
	// Not exactly sure what this should return back after the function
	// call
	// will check docs and maybe consult frontend
	public JsonFormats OAuth(@RequestBody CreateProfileJson JF)
	{
		// returns a plain JsonFormats with nothing except a status code and a
		// a ServerMessage
		JsonFormats returned = DBInstance.authorize(JF);
		if (returned.statusCode != 200)
		{
			return returned;
		}
		Profiles finding = new Profiles();
		finding.UserName = JF.UserName;
		
		CreateProfileJson CPJ = DBInstance.getProfileString(JF.UserName);
		CPJ.statusCode = 200;
		CPJ.ServerMessage = "Profile found and retrieved";
		return CPJ;
	}
	
	
	// Checks if a profile already exists
	@RequestMapping(value="/CPE", method = RequestMethod.GET)
	@ResponseBody
	public JsonFormats check(@RequestBody String user)
	{
		boolean ret = DBInstance.profileExists(user);
		if(ret)
		{
			return new JsonFormats(200, "Username is not taken");
		}
		
		return new JsonFormats(404, "Username is taken");
	}
	
}
