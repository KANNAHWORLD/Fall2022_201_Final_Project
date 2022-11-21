package restAPI.endPoints;

import java.util.ArrayList;


// ALSO FOR ALL OF THESE
// WE NEED TO FIGURE OUT HOW TO DO 
// USERNAMES AND PASSWORDS

public class JsonFormats {
	public int statusCode;
	// just to create this so that
	// status code is just set easily
	public JsonFormats(int code)
	{
		statusCode = code;
	}
	
	// Constructor so that you can 
	// add a description from the constructor
	public JsonFormats(int code, String desc)
	{
		statusCode = code;
		ServerMessage = desc;
	}
	
	// so that errors will shut-up
	public JsonFormats(){}

	public String ServerMessage;
}

// some bio information
class BasicInfo extends JsonFormats
{
	public String first;
	public String last;
	public int age;
	public int SexOrient;
	public int gender;
	public String insta;
	public String description;
}

class MatchObject extends BasicInfo
{
	Rank self;
}

class Matches extends BasicInfo
{
	ArrayList<MatchObject> matches;
}

class ProfileWrapper extends JsonFormats
{
	public ArrayList<Profiles> profiles = new ArrayList<Profiles>();
	ProfileWrapper(int code)
	{
		super(code);
	}
	ProfileWrapper()
	{
	}
}

class Profiles extends JsonFormats
{
	public String FirstName;
	public String LastName;
	public String UserName;
}


class CreateProfileJson extends BasicInfo
{
	CreateProfileJson(){
		selfRank = new Rank();
		preferRank = new Rank();
		prefered = new Preferences();
	}
	
	// Constructor for testing
	// basicall adds garbage data
	CreateProfileJson(int as)
	{
		selfRank = new Rank();
		preferRank = new Rank();
		prefered = new Preferences();
		
		first = "sid";
		last = "bansal";
		age = 69;
		gender = 666;
		SexOrient = 5;
		insta = "Kill me";
		description = "AAAHHHHH";
		UserName = "Sloth";
		Password = "Stick";
		
		selfRank.extroverted = 3;
		selfRank.humor = 3;
		selfRank.adventure = 3;
		selfRank.ambition = 3;
		selfRank.artistic = 3;
		selfRank.wOfAff = 3;
		selfRank.physTouch = 3;
		selfRank.gifts = 3;
		selfRank.qualTime = 3;
		selfRank.service = 3;
		
		preferRank.extroverted = 3;
		preferRank.humor = 3;
		preferRank.adventure = 3;
		preferRank.ambition = 3;
		preferRank.artistic = 3;
		preferRank.wOfAff = 3;
		preferRank.physTouch = 3;
		preferRank.gifts = 3;
		preferRank.qualTime = 3;
		preferRank.service = 3;
		
		
	}
	
	
	public String UserName;
	public String Password;
	public Preferences prefered;
	public Rank selfRank;
	public Rank preferRank;
}

class Preferences
{
	Preferences()
	{
		people = new ArrayList<String>();
	}
	ArrayList<String> people;
}

class Rank
{
	public int extroverted;
	public int humor;
	public int adventure;
	public int ambition;
	public int artistic;
	public int wOfAff;
	public int physTouch;
	public int gifts;
	public int qualTime;
	public int service;
}
