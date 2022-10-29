package restAPI.endPoints;

import java.util.ArrayList;

public class JsonFormats {
	public int statusCode;
}


class CreateProfileJson extends JsonFormats
{
	public String first;
	public String last;
	public int age;
	public int SexOrient;
	public String insta;
	public String description;
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
