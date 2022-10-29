package restAPI.endPoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/user")
public class User {
	
	
	//This request is going to find someone's profile
	@RequestMapping(value="/profile", method = RequestMethod.GET)
	@ResponseBody
	public String profileCreation()
	{
		return "Profile Creation";
	}
	
	
	//This section will create a new profile for the person. It takes in Json formatted data
	// Queries database, and returns something back, just use your brain
	@RequestMapping(value="/createProfile", method = RequestMethod.GET)
	@ResponseBody
    public String createProfile(@RequestBody CreateProfileJson CPJ) 
	{
		System.out.println("Hello World! line hit by requests");
        return "Hello World!";
    }
	
	//Retrieves the matches of a person. Requires a simple request 
	@RequestMapping(value="matches")
	@ResponseBody
	public String match()
	{
		return "Matching";
	}

}
