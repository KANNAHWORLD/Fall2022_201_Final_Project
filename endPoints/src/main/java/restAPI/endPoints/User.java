package restAPI.endPoints;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.client.json.Json;

import ch.qos.logback.core.util.FileUtil;

@RestController
@CrossOrigin(origins="*")
@RequestMapping(value="/user")
public class User {
	
	DataStore DBInstance = new DataStore(1234);
	
	
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
			return new CreateProfileJson(77);
		}
		return DBInstance.getProfileString(toret);
	}
	
	
	// TODO:
	// This right here is supposed get someone's profile
	// still needs work, not really sure how to implement this currently
	// Will return the person's rankings of himself, his name, age, sexual orientation
	// instagram handle, and description
	@RequestMapping(value="/getProfile", method = RequestMethod.POST)
	public JsonFormats getProfile(@RequestBody Profiles singleProfile)
	// gets a single profile, not multiple Profiles
	// Needs a username, otherwise it does not work
	// returns the entire person's Stuff including their rankings/bio/name/etc.
	// should be implemented sometime by saturday
	{
		
		return DBInstance.getProfileStringNoMatches(singleProfile.UserName);
	}
	

	// TODO: need to find a way to make a directory, and read the files to test it out
	// the request can be made by 
	// for example 
	// curl -F files=@pom.xml localhost:8082/user/upload -F username="hello"
	// -F must be mentioned before any parameter for the reason of multipartness or whatever

	
	@PostMapping("/upload")
    public JsonFormats uploadFiles(@RequestParam("files") MultipartFile[] files, @RequestParam("username") String username) {
        try {
        	
        	String imageType = ".png";
            
            byte[] filebytes = files[0].getBytes();
            String dir = System.getProperty("user.dir");
            dir += "/images";
            File direct = new File(dir);
            direct.mkdir();
            
            dir += "/" + username + imageType;
            
            File newFile = new File(dir);
            newFile.createNewFile();
            //newFile.createNewFile();
            
            OutputStream os = new FileOutputStream(newFile);
           
            os.write(filebytes);
            
            os.close();
            System.out.println("Created image for" + username);
            System.out.println("Adding to database");
            
            String table = "ImageMap";
            String values = "VALUES(\"%s\", \"%s\")";
            values = String.format(values, username, dir);
            String SQLquery = "INSERT INTO " + table + " " + values + ";";
            DBInstance.noexceptQuery(SQLquery);
            
        	} catch (Exception e)
	        {
        		e.printStackTrace();
        		System.out.println("HEHEHEH");
        		return new JsonFormats(417, "An error occured");
	        }
        
       return new JsonFormats(200, "Image submitted");
	}
	

	@GetMapping(value = "/getImg/{usern}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public @ResponseBody byte[] getFile(@PathVariable("usern") String usern) throws IOException {
		
		String table = "ImageMap";
		String neededComponents = "path";
		String conditions = "username=\""+usern+ "\"";
		String query = "SELECT " + neededComponents + " FROM " + table + " WHERE " + conditions + ";";
		ResultSet rs = DBInstance.getQuery(query);
		
		
		String path = null;
		try {
			rs.next();
			path = rs.getString(neededComponents);
		} catch (SQLException e) {
			System.out.println("There was an error retrieving file path, maybe userimage does not exist");
			e.printStackTrace();
			return null;
		}
		
		File img = new File(path);
		FileInputStream fl = new FileInputStream(img);
		byte[] arr = new byte[(int)img.length()];
		  
        // Reading file content to byte array
        // using standard read() method
        fl.read(arr);
		fl.close();
		return arr;
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


