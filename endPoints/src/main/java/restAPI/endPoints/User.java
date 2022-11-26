package restAPI.endPoints;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.google.api.client.json.Json;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/user")
public class User {
	
	DataStore DBInstance = new DataStore();
	
	
	//
	// Never mind this is basically useless currently
	//
	// BELOW SHOULD BE DONE
	//
	// This request is going to find someone's profile
	// This is intended to be used to search profiles
	// given only a single. This will try it's best to find 
	// as many relevant profiles as possible.
	// There could be a value for last name or first name
	@RequestMapping(value="/profiles", method = RequestMethod.GET)
	@ResponseBody
	public CreateProfileJson findProfile(@RequestBody Profiles request)
	{
		// Calls DBInstance to find all of the profiles
		// check DataStore.java to see what the function does
		CreateProfileJson retWrapper = DBInstance.getProfileString(request.UserName);
		return retWrapper;
	}
	
	
	// This section will create a new profile for the person. It takes in Json formatted data
	// Queries database, and returns something back, just use your brain
	@RequestMapping(value="/createProfile", method = RequestMethod.POST)
	@ResponseBody
    public CreateProfileJson createProfile(@RequestBody CreateProfileJson CPJ) 
	{
		//Database Query needs to be done with this
		
		// Once the query is over
		// endRes will have an error code and a ServerMessage, both of which will be sent
		// back to the front end along with the original content of the queried Json
		CreateProfileJson endRes = (CreateProfileJson) DBInstance.createProfile(CPJ);
		
		// We need to figure out what to send back, consult design documents
		System.out.println("CreateProfile Function was hit!");
		
		return endRes;
    }


	@RequestMapping(value="/changeMatch", method = RequestMethod.POST)
	@ResponseBody
    public void changeMatch(@RequestBody MatchPair person) 
	{
		//Database Query needs to be done with this
		System.out.println("here");
		// Once the query is over
		// endRes will have an error code and a ServerMessage, both of which will be sent
		// back to the front end along with the original content of the queried Json
		DBInstance.changeMatch(person.user, person.match);
    }


	
	
	//Retrieves the matches of a person. This might be a tough one to implement
	//
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	//
	// Need AAYUSHI and ANISHA consultation
	// WE SHOULD INCORPORATE MULTITHREDDING HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//
	// >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
	@RequestMapping(value="/matches/{usern}", method = RequestMethod.GET)
	// Currently takes in a basic info object, but this might need to change given people 
	// are assigned special tokens or something so that we do not have individual
	// Might want to change this so that it only takes in a username and generates matches
	// from there since we can just query the database for that.
	public CreateProfileJson match(@PathVariable("usern") String usern)
	{
		String toret = DBInstance.getMatch(usern);
		if (toret.equals("-5")){
			return null;
		}
		return DBInstance.getProfileString(toret);
	}
	
	
	// TODO:
	// This right here is supposed get someone's profile
	// still needs work, not really sure how to implement this currently
	// Will return the person's rankings of himself, his name, age, sexual orientation
	// instagram handle, and description
	@RequestMapping(value="/getProfile", method = RequestMethod.GET)
	public JsonFormats getProfile(@RequestBody Profiles singleProfile)
	// gets a single profile, not multiple Profiles
	// Needs a username, otherwise it does not work
	// returns the entire person's Stuff including their rankings/bio/name/etc.
	// should be implemented sometime by saturday
	{
		
		return DBInstance.getProfileString(singleProfile.UserName);
	}
	

	
	
	// FOR SANNJAN DROP DOWN BAR!!!!!!!!!!!!!
	//
	//
	// Returns all profiles from the database for the drop down menu thing in 
	// the react aplicatoin
	@RequestMapping(value="/allprofiles", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Profiles> allProfiles()
	{
		// Calls DBInstance to find all of the profiles
		// check DataStore.java to see what the function does
		return DBInstance.allProfiles();
	}
}


