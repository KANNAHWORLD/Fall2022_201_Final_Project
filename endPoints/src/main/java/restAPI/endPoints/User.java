package restAPI.endPoints;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/user")
public class User {
	
	DataStore DBInstance = new DataStore();
	
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
		//Database Query needs to be done with this
		
		DBInstance.createProfile(CPJ);
		
		// We need to figure out what to send back, consult design documents
		
		System.out.println("Hello World! line hit by requests");
        return "Hello World!";
    }
	
	
	
	
	
	
	//Retrieves the matches of a person. This might be a tough one to implement
	// Need AAYUSHI and ANISHA consultation
	@RequestMapping(value="/matches")
	@ResponseBody
	
	// Currently takes in a basic info object, but this might need to change given people 
	// are assigned special tokens or something so that we do not have individual
	// Might want to change this so that it only takes in a username and generates matches
	// from there since we can just query the database for that.
	public String match(@RequestBody BasicInfo str)
	{
		
		System.out.println(str);
		return "Matching";
	}
	
	
	// This right here is supposed get someone's profile
	// still needs work, not really sure how to implement this currently
	public JsonFormats getProfile(JsonFormats JF)
	{
		return new JsonFormats();
	}
	
	
	
	// This was just for testing purposes, should be eventually phased out or made private
	@RequestMapping(value="/test", method = RequestMethod.POST)
	@ResponseBody
	public String test(@RequestBody String str)
	{
		System.out.println(str);
		
		System.out.println(str);
		
		return "Matching";
	}
	
}


