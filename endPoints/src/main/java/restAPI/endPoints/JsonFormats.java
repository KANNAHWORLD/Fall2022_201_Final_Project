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
	
	// so that errors will shut-up
	public JsonFormats(){}

}

// some bio information
class BasicInfo extends JsonFormats
{
	public String first;
	public String last;
	public int age;
	public int SexOrient;
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

class Profiles
{
	public String FirstName;
	public String LastName;
	public String UserName;
}


class CreateProfileJson extends BasicInfo
{
	public Preferences prefered;
	public Rank selfRank;
	public Rank preferRanks;
}

class Preferences
{
	ArrayList<String> people;
}

class Rank
{
	public int introverted;
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
